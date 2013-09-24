import java.io.PrintStream;
import java.sql.ResultSet;

/**
 * Created with IntelliJ IDEA.
 * User: vshivamurthy
 * Date: 9/20/13
 * Time: 11:18 AM
 * To change this template use File | Settings | File Templates.
 */
public class MetricCompareThread implements Runnable{

    private String sql = null;
    private String metric = null;
    private PrintStream printStream;
    private static HiveConnection hiveConnection = new HiveConnection();
    private String type = null;

    MetricCompareThread(String sql, PrintStream printStream, String metric, String type) {
        this.sql = sql;
        this.printStream = printStream;
        this.metric = metric;
        this.type = type;
    }

    public void run() {
        StatementHolder hiveStatement = StatementFactory.getHiveStatement();
        try {
            String header = type + " " + metric;
            System.out.println(sql);
            ResultSet res = hiveStatement.statement().executeQuery(sql);
            CreateHTMLTable createHTMLTable = new CreateHTMLTable();
            createHTMLTable.dumpData(res,printStream,header);
        } catch (Exception e) {
            System.out.println("Error occurred for " + metric);
            e.printStackTrace();
        } finally {
            hiveStatement.close();
        }
    }
}
