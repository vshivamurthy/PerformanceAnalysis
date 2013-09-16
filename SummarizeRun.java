import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created with IntelliJ IDEA.
 * User: vshivamurthy
 * Date: 9/11/13
 * Time: 3:19 PM
 * To change this template use File | Settings | File Templates.
 */
public class SummarizeRun extends Summarize {
    @Override
    public String groupbyclause() {
        return "";
    }

    public String groupbyparmeters()
    {
       return "";
    }

    public void printResults(ResultSet res, String run, String metric_name)  throws SQLException
    {
        while (res.next()) {
            System.out.println( run + "\t" + metric_name + "\t" + res.getDouble(1) + "\t" + res.getDouble(2) + "\t" + res.getDouble(3) + "\t" + res.getDouble(4) + "\t" + res.getDouble(5) + "\t" + res.getDouble(6) + "\t" + res.getDouble(7) + "\t" + res.getDouble(8) + "\t" + res.getDouble(9) + "\t" + res.getDouble(10) + "\t" + res.getDouble(11));
        }
    }

}
