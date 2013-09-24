import java.io.FileOutputStream;
import java.io.PrintStream;
import java.sql.ResultSet;
import java.util.Hashtable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static java.text.MessageFormat.format;

/**
 * Created with IntelliJ IDEA.
 * User: vshivamurthy
 * Date: 9/20/13
 * Time: 10:19 AM
 * To change this template use File | Settings | File Templates.
 */
public class MetricCompareReport {
    public static void main(String[] args) throws Exception{
        PrintStream printStream = new PrintStream(new FileOutputStream("metric_compare_report.html"));

        String baseline = null;
        String run = null;
        if(args.length == 2)
        {
        baseline = args[0];
        run = args[1];
        }
        else
        {
            System.out.println("Usage java MetricCompareReport <baseline> <run>");
        }
        printSummary(run,printStream);
        runComparision(baseline,run,"slow",printStream);
        runComparision(baseline,run,"fast",printStream);
        printStream.close();
    }

    public static void runComparision(String baseline, String run, String type, PrintStream printStream)
    {
        StatementHolder hiveStatement = StatementFactory.getHiveStatement();
        try {

        MetricCompareSQL metricCompareSQL = new MetricCompareSQL();
        String all_metrics[] = new Metrics().read();

        Hashtable<String,String> metric_table = new MetricThreshold().getMetricTable();
        ExecutorService executor = Executors.newFixedThreadPool(all_metrics.length);
        for (String metric : all_metrics) {
            String sql = metricCompareSQL.getGestureCompareSQL(baseline,run,metric, metric_table.get(metric),type);
            System.out.println(sql);
            MetricCompareThread mc_worker = new MetricCompareThread(sql,printStream, metric,type);
            executor.execute(mc_worker);
        }
        executor.shutdown();
        while (!executor.isTerminated()) {
        }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            hiveStatement.close();
        }
    }

    public static void printSummary(String run, PrintStream printStream)
    {
        StatementHolder hiveStatement = StatementFactory.getHiveStatement();
        try {
            ResultSet res = hiveStatement.statement().executeQuery(format("select * from run_summary where run=''{0}''", run));
            CreateHTMLTable createHTMLTable = new CreateHTMLTable();
            createHTMLTable.dumpData(res,printStream,"RUN SUMMARY");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            hiveStatement.close();
        }
    }
}
