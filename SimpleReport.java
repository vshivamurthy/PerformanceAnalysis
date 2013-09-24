import java.io.FileOutputStream;
import java.io.PrintStream;
import java.sql.ResultSet;

import static java.text.MessageFormat.format;

/**
 * Created with IntelliJ IDEA.
 * User: vshivamurthy
 * Date: 9/19/13
 * Time: 12:32 PM
 * To change this template use File | Settings | File Templates.
 */
public class SimpleReport {

    public static void main(String[] args) throws Exception{
        PrintStream printStream = new PrintStream(new FileOutputStream("simple_report.html"));
        StatementHolder hiveStatement = StatementFactory.getHiveStatement();
        try {
            RunCompareSQL runCompareSQL = new RunCompareSQL();
            String sql = runCompareSQL.getRunCompareSQL("master-alm-nightly-performance-1537", "master-alm-nightly-performance-1538");
            ResultSet res = hiveStatement.statement().executeQuery(sql);
            CreateHTMLTable createHTMLTable = new CreateHTMLTable();
            createHTMLTable.dumpData(res,printStream,"SERVER RESPONSE TIME");
            sql = runCompareSQL.getGestureCompareSQL("master-alm-nightly-performance-1537", "master-alm-nightly-performance-1538", "slow");
            res = hiveStatement.statement().executeQuery(sql);
            createHTMLTable.dumpData(res,printStream,"SLOW GESTURES");
            sql = runCompareSQL.getGestureCompareSQL("master-alm-nightly-performance-1537", "master-alm-nightly-performance-1538", "fast");
            res = hiveStatement.statement().executeQuery(sql);
            createHTMLTable.dumpData(res,printStream,"FAST GESTURES");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            hiveStatement.close();
            printStream.close();
        }
    }

}
