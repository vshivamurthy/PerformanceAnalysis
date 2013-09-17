PerformanceAnalysis
===================

Command reference

 sh run.sh -s "loadtest_loadtest" -d "20130915" -r "master-alm-nightly-performance-1536" -t "run" > data.txt
 cp data.txt /hdfs/user/vshivamurthy/temp/
 hive -f create_run_summary.sql
 hive -e 'load data inpath "/user/vshivamurthy/temp/data.txt" into table run_summary partition (run="master-alm-nightly-performance-1536")'
 hive -e 'select count from run_summary where run="master-alm-nightly-performance-1536" and metric_name="cachemiss"'