/**
 * Created with IntelliJ IDEA.
 * User: vshivamurthy
 * Date: 9/20/13
 * Time: 11:05 AM
 * To change this template use File | Settings | File Templates.
 */
public class MetricCompareSQL {
    public String getGestureCompareSQL(String baseline, String compare, String metric, String threshold, String type)
    {
        String sql = "select " +
                "base.gesture_name, " +
                "base.p90 as baseline_90th, compare.p90 as compare_90th, " +
                "base.avg as baseline_avg, compare.avg as compare_avg, " +
                "(compare.p90 - base.p90) as difference_90th, (compare.avg - base.avg) as difference_avg, " +
                "(compare.p90 - base.p90)/base.p90 * 100 as percent_change_90th, (compare.avg - base.avg)/base.avg * 100 as percent_change_avg " +
                "FROM " +
                "( select  gesture_name, avg, p90 from gesture_summary where run=\"" + baseline + "\" and metric_name = '" + metric + "' ) base " +
                "join " +
                "( select  gesture_name, avg, p90 from gesture_summary where run=\"" + compare + "\" and metric_name = '" + metric + "' ) compare " +
                "on ( base.gesture_name = compare.gesture_name ) "
                + whereCluase(type,threshold)
                ;
        return sql;
    }

    private String whereCluase(String type, String threshold)
    {
        String whereclause = "";
        if(type.contentEquals("slow"))
        {
            return "where ( ((compare.avg - base.avg)/base.avg * 100 > "  +  threshold + ") and ((compare.avg - base.avg)/base.avg * 100 != 'NaN') and ((compare.avg - base.avg)/base.avg * 100 != 'Infinity'))";
        }
        else if(type.contentEquals("fast"))
        {
            return "where ( ((compare.avg - base.avg)/base.avg * 100 < -"  +  threshold + ") and ((compare.avg - base.avg)/base.avg * 100 != 'NaN') and ((compare.avg - base.avg)/base.avg * 100 != 'Infinity'))";
        }
        return whereclause;
    }
}
