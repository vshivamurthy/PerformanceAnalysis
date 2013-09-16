import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created with IntelliJ IDEA.
 * User: vshivamurthy
 * Date: 9/10/13
 * Time: 12:20 PM
 * To change this template use File | Settings | File Templates.
 */
public abstract class Summarize {
    public abstract String groupbyparmeters();
    public abstract String groupbyclause();
    public abstract void printResults(ResultSet res, String run, String type) throws SQLException;
}
