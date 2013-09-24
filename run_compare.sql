select base.metric_name, base.p90 as baseline_90th, compare.p90 as compare_90th, base.avg as baseline_avg, compare.avg as compare_avg, (compare.p90 - base.p90) as difference_90th, (compare.avg - base.avg) as difference_avg, (compare.p90 - base.p90)/base.p90 * 100 as percent_change_90th, (compare.avg - base.avg)/base.avg * 100 as percent_change_avg FROM ( select  metric_name, avg, p90 from run_summary where run="master-alm-nightly-performance-1535"  ) base join ( select  metric_name, avg, p90 from run_summary where run="master-alm-nightly-performance-1536" ) compare on ( base.metric_name = compare.metric_name );