import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintStream;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.MessageFormat;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static java.text.MessageFormat.format;

public class RunStats {


    /**
     * @param args
     * @throws SQLException
     */
    public static void main(String[] args) throws IOException {

        String stack = null;
        String runday = null;
        String run = null;
        String summaryType = null;

        if (args.length != 4) {
            System.out.println("Usage RunStats <stack> <runday> <run> <SummaryType>");
        } else {
            stack = args[0];
            runday = args[1];
            run = args[2];
            summaryType = args[3];
        }


        Summarize summarize = new SummaryType().getSummaryType(summaryType);

        String all_metrics[] = new Metrics().read();
        ExecutorService executor = Executors.newFixedThreadPool(all_metrics.length);

        PrintStream printStream = new PrintStream(new FileOutputStream("temp.txt"));

        for (String metric : all_metrics) {
            String sql = new SummarizeSQL().getSummarizeSQL(summarize, metric, run, stack, runday);
            // System.out.println(sql);
            MetricSummarize ms_worker = new MetricSummarize(sql, summarize, run, metric, printStream);
            executor.execute(ms_worker);
        }
        executor.shutdown();
        while (!executor.isTerminated()) {
        }

        printStream.close();

        FileCopyForHive fileCopyForHive = new FileCopyForHive();
        fileCopyForHive.copy("temp.txt","hdfs://vshivamurthy@bld-hadoop-06/temp_hive/temp_hive_run.txt");

        StatementHolder hiveStatement = StatementFactory.getHiveStatement();
        try {
            System.out.println(format("load data inpath ''hdfs:///temp_hive/temp_hive_run.txt'' into table run_summary partition (run=''{0}'')", run));
            hiveStatement.statement().execute(format("load data inpath ''hdfs:///temp_hive/temp_hive_run.txt'' into table run_summary partition (run=''{0}'')", run));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            hiveStatement.close();
        }
    }


}