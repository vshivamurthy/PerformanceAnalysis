import java.io.IOException;
import java.sql.SQLException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class RunStats {


    /**
     * @param args
     * @throws SQLException
     */
    public static void main(String[] args)  throws IOException {

        String stack = null;
        String runday = null;
        String run = null;
        String summaryType = null;

        if(args.length != 4)
        {
            System.out.println("Usage RunStats <stack> <runday> <run> <SummaryType>");
        }
        else
        {
            stack = args[0];
            runday = args[1];
            run = args[2];
            summaryType = args[3];
        }


        Summarize summarize = new SummaryType().getSummaryType(summaryType);

        String all_metrics[] = new Metrics().read();
        ExecutorService executor = Executors.newFixedThreadPool(all_metrics.length);

        for(String metric : all_metrics)
        {
            String sql = new SummarizeSQL().getSummarizeSQL(summarize,metric,run,stack,runday);
           // System.out.println(sql);
            MetricSummarize ms_worker = new MetricSummarize(sql,summarize,run,metric);
            executor.execute(ms_worker);
        }
        executor.shutdown();
        while (!executor.isTerminated()) {

        }

    }


}