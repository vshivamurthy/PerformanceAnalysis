import java.io.PrintStream;
import java.sql.ResultSet;

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
    private PrintStream printStream;
    private static HiveConnection hiveConnection = new HiveConnection();
    private Summarize summarize;

    MetricSummarize(String sql, Summarize summarize, String run, String metric, PrintStream printStream) {
        this.sql = sql;
        this.summarize = summarize;
        this.run = run;
        this.metric = metric;
        this.printStream = printStream;
    }

    public void run() {
        StatementHolder hiveStatement = StatementFactory.getHiveStatement();
        try {

            System.out.println(sql);
            ResultSet res = hiveStatement.statement().executeQuery(sql);
            summarize.printResults(res, run, metric, printStream);

        } catch (Exception e) {
            System.out.println("Error occurred for " + metric);
            e.printStackTrace();
        } finally {
            hiveStatement.close();
        }
    }

}
