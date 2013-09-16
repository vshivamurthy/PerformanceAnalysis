import java.util.Calendar;

/**
 * Created with IntelliJ IDEA.
 * User: vshivamurthy
 * Date: 9/10/13
 * Time: 2:47 PM
 * To change this template use File | Settings | File Templates.
 */
public class SummarizeSQL {
    public String getSummarizeSQL(Summarize summarize, String metric_name, String run, String stack, String RunDay)
    {
       String sql = "select "  +  summarize.groupbyparmeters() +
                " count(*), " +
                " avg(cast(" + metric_name +" as BIGINT)), " +
                " stddev_pop(cast(" + metric_name +" as BIGINT))," +
                " min(cast(" + metric_name +" as BIGINT))," +
                " max(cast(" + metric_name +" as BIGINT))," +
                " sum(cast(" + metric_name +" as BIGINT))," +
                " percentile(cast(" + metric_name +" as BIGINT), 0.5)," +
                " percentile(cast(" + metric_name +" as BIGINT), 0.75)," +
                " percentile(cast(" + metric_name +" as BIGINT), 0.90)," +
                " percentile(cast(" + metric_name +" as BIGINT), 0.95)," +
                " percentile(cast(" + metric_name +" as BIGINT), 0.99)" +
                " from alm_spans where stack = '" + stack + "' and " +
                getThreeDayInterval(RunDay) +
                " and testrun = '" + run + "'" +
                summarize.groupbyclause();
        return sql;
    }

    private String getThreeDayInterval(String RunDay)
    {
        String yearClause =  "";
        int year = Integer.parseInt(RunDay.substring(0, 4));
        int month = Integer.parseInt(RunDay.substring(4, 6));
        int day = Integer.parseInt(RunDay.substring(6, 8));
        Calendar cal = Calendar.getInstance();
        cal.set(year, (month-1), day);

        yearClause = "(( year = '" + cal.get(Calendar.YEAR) + "' and month = '" + String.format("%02d",(cal.get(Calendar.MONTH)+1)) + "' and day = '" + String.format("%02d",cal.get(Calendar.DAY_OF_MONTH)) + "') OR ";
        cal.add(Calendar.DAY_OF_MONTH, 1);
        yearClause = yearClause + "( year = '" + cal.get(Calendar.YEAR) + "' and month = '" + String.format("%02d",(cal.get(Calendar.MONTH)+1)) + "' and day = '" + String.format("%02d",cal.get(Calendar.DAY_OF_MONTH)) + "') OR ";
        cal.add(Calendar.DAY_OF_MONTH, -2);
        yearClause = yearClause + "( year = '" + cal.get(Calendar.YEAR) + "' and month = '" + String.format("%02d",(cal.get(Calendar.MONTH)+1)) + "' and day = '" + String.format("%02d",cal.get(Calendar.DAY_OF_MONTH)) + "')) ";
        return yearClause;
    }
}
