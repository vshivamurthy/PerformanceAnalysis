import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created with IntelliJ IDEA.
 * User: vshivamurthy
 * Date: 9/11/13
 * Time: 3:37 PM
 * To change this template use File | Settings | File Templates.
 */
public class SummarizeGestureSub extends Summarize {
    public String groupbyclause() {
        return " group by gesturename,  subscriptionid ";
    }

    public String groupbyparmeters()
    {
        return " gesturename,  subscriptionid, ";
    }

    public void printResults(ResultSet res, String run, String metric_name)  throws SQLException
    {
        while (res.next()) {
            System.out.println( run + "\t" + metric_name + "\t" + res.getString(1) + "\t" + res.getString(2) + "\t" + res.getDouble(3) + "\t" + res.getDouble(4) + "\t" + res.getDouble(5) + "\t" + res.getDouble(6) + "\t" + res.getDouble(7) + "\t" + res.getDouble(8) + "\t" + res.getDouble(9) + "\t" + res.getDouble(10) + "\t" + res.getDouble(11) + "\t" + res.getDouble(12) + "\t" + res.getDouble(13));
        }
    }
}
