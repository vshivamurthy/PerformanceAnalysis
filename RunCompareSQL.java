/**
 * Created with IntelliJ IDEA.
 * User: vshivamurthy
 * Date: 9/18/13
 * Time: 2:35 PM
 * To change this template use File | Settings | File Templates.
 */
public class RunCompareSQL {

    public String getRunCompareSQL(String baseline, String compare)
    {
        String sql = "select base.metric_name, " +
                " base.p90 as baseline_90th, compare.p90 as compare_90th, " +
                " base.avg as baseline_avg, compare.avg as compare_avg, " +
                " (compare.p90 - base.p90) as difference_90th, " +
                " (compare.avg - base.avg) as difference_avg, " +
                " (compare.p90 - base.p90)/base.p90 * 100 as percent_change_90th, " +
                " (compare.avg - base.avg)/base.avg * 100 as percent_change_avg " +
                " FROM " +
                " ( select metric_name, avg, p90 from run_summary where run=\"" + baseline + "\"  ) base " +
                "join " +
                " ( select  metric_name, avg, p90 from run_summary where run=\"" + compare +  "\" ) " +
                " compare on ( base.metric_name = compare.metric_name ) where base.metric_name = 'requestspan.spanresponsetimems'";
        return sql;
    }

    public String getGestureCompareSQL(String baseline, String compare, String type)
    {
        String sql = "select " +
                     "base.gesture_name, " +
                     "base.p90 as baseline_90th, compare.p90 as compare_90th, " +
                     "base.avg as baseline_avg, compare.avg as compare_avg, " +
                     "(compare.p90 - base.p90) as difference_90th, (compare.avg - base.avg) as difference_avg, " +
                     "(compare.p90 - base.p90)/base.p90 * 100 as percent_change_90th, (compare.avg - base.avg)/base.avg * 100 as percent_change_avg " +
                     "FROM " +
                     "( select  gesture_name, avg, p90 from gesture_summary where run=\"" + baseline + "\" and metric_name = \"requestspan.spanresponsetimems\" ) base " +
                     "join " +
                     "( select  gesture_name, avg, p90 from gesture_summary where run=\"" + compare + "\" and metric_name = \"requestspan.spanresponsetimems\" ) compare " +
                     "on ( base.gesture_name = compare.gesture_name ) "
                     + whereCluase(type)
                     ;
        return sql;
    }

    private String whereCluase(String type)
    {
        String whereclause = "";
        if(type.contentEquals("slow"))
        {
            return "where (compare.avg - base.avg)/base.avg * 100 > 20 and (compare.avg - base.avg) > 50";
        }
        else if(type.contentEquals("fast"))
        {
            return "where (compare.avg - base.avg)/base.avg * 100 < -20 and (compare.avg - base.avg) < -50";
        }
        return whereclause;
    }
}
