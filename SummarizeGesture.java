import java.io.PrintStream;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created with IntelliJ IDEA.
 * User: vshivamurthy
 * Date: 9/11/13
 * Time: 3:31 PM
 * To change this template use File | Settings | File Templates.
 */
public class SummarizeGesture extends Summarize{
    public String groupbyclause() {
        return " group by gesturename ";
    }

    public String groupbyparmeters()
    {
        return " gesturename, ";
    }

    public void printResults(ResultSet res, String run, String metric_name, PrintStream printStream)  throws SQLException
    {
        while (res.next()) {
            printStream.println( metric_name + "\t" + res.getString(1) + "\t" + res.getDouble(2) + "\t" + res.getDouble(3) + "\t" + res.getDouble(4) + "\t" + res.getDouble(5) + "\t" + res.getDouble(6) + "\t" + res.getDouble(7) + "\t" + res.getDouble(8) + "\t" + res.getDouble(9) + "\t" + res.getDouble(10) + "\t" + res.getDouble(11) + "\t" + res.getDouble(12));
        }
    }

    public String getTableName()
    {
        return "gesture_summary";
    }
}
