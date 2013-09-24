import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Hashtable;

/**
 * Created with IntelliJ IDEA.
 * User: vshivamurthy
 * Date: 9/20/13
 * Time: 10:46 AM
 * To change this template use File | Settings | File Templates.
 */
public class MetricThreshold {

    public Hashtable<String, String> getMetricTable()
    {
        Hashtable<String, String> metric_table = new Hashtable<String, String>();
        BufferedReader br = null;
        try
        {

        String line;
        br = new BufferedReader(new FileReader("metric_threshold.txt"));
        while((line = br.readLine()) != null)
        {
            String metric = line.split(",")[0];
            String threshold = line.split(",")[1];
            metric_table.put(metric,threshold);
        }
        br.close();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return metric_table;

    }
}
