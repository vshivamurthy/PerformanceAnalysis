import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created with IntelliJ IDEA.
 * User: vshivamurthy
 * Date: 9/10/13
 * Time: 12:27 PM
 * To change this template use File | Settings | File Templates.
 */
public class HiveConnection {
    private static String driverName = "org.apache.hive.jdbc.HiveDriver";

    private static HiveConfig hiveConfig = new HiveConfig();

    public static Connection getConnection() throws ClassNotFoundException,SQLException
    {
        Class.forName(driverName);
        String jdbcUrl = hiveConfig.getProperty("jdbcUrl");
        String username = hiveConfig.getProperty("username");
        String password = hiveConfig.getProperty("password");
        return DriverManager.getConnection(jdbcUrl, username, password);
    }
}
