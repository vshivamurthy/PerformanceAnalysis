DROP TABLE RUN_SUMMARY;
CREATE EXTERNAL TABLE RUN_SUMMARY (metric_name string, count double, avg double, stddev double, min double, max double, sum double, p50 double, p75 double, p90 double, p95 double, p99 double)
PARTITIONED BY (run string)
ROW FORMAT DELIMITED
FIELDS TERMINATED BY '\t'
LINES TERMINATED BY '\n'
STORED AS TEXTFILE
LOCATION '/user/vshivamurthy/run_summary';

