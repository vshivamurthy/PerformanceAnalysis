import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

/**
 * Created with IntelliJ IDEA.
 * User: vshivamurthy
 * Date: 9/10/13
 * Time: 12:26 PM
 * To change this template use File | Settings | File Templates.
 */
public class HiveConfig {
    private Properties hiveProperties = new Properties();

    HiveConfig()
    {
        try
        {
        loadconfigFile();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    private void loadconfigFile() throws IOException {
        File hiveConfig = new File("hive.cfg");
        FileInputStream hiveStream = new FileInputStream(hiveConfig);
        hiveProperties.load(hiveStream);
    }

    public String getProperty(String property)
    {
        return hiveProperties.getProperty(property);
    }
}
