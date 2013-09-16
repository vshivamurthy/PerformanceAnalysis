import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: vshivamurthy
 * Date: 9/10/13
 * Time: 10:10 AM
 * To change this template use File | Settings | File Templates.
 */
public class Metrics {
    public String[] read() throws IOException
    {
       FileReader fileReader = new FileReader("metrics.txt");
       BufferedReader bufferedReader = new BufferedReader(fileReader);
       String line = null;
       List<String> lines = new ArrayList<String>();
       while((line = bufferedReader.readLine()) != null)
       {
           lines.add(line);
       }
       return lines.toArray(new String[lines.size()]);
    }
}
