import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * Created with IntelliJ IDEA.
 * User: vshivamurthy
 * Date: 9/4/13
 * Time: 8:04 PM
 * To change this template use File | Settings | File Templates.
 */
public class MetricSummarize implements Runnable {

    private String sql = null;
    private String run = null;
    private String metric = null;
    private static HiveConnection hiveConnection = new HiveConnection();
    private Summarize summarize;

    MetricSummarize(String sql, Summarize summarize, String run, String metric)
    {
        this.sql = sql;
        this.summarize = summarize;
        this.run = run;
        this.metric = metric;
    }

    public void run()
    {

        try
        {

        Connection con = HiveConnection.getConnection();
        String type = "run";
        Statement stmt = con.createStatement();
        ResultSet res = stmt.executeQuery(sql);
        summarize.printResults(res,run,metric);

        stmt.close();
        con.close();
        }
        catch(Exception e)
        {
            System.out.println("Error occurred for " + metric);
            e.printStackTrace();
        }
    }

}
