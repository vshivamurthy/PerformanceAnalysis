DROP TABLE GESTURE_SUB_SUMMARY;
CREATE TABLE GESTURE_SUB_SUMMARY (metric_name string, gesture_name string, sub string, count double, avg double, stddev double, min double, max double, sum double, p50 double, p75 double, p90 double, p95 double, p99 double)
PARTITIONED BY (run string)
ROW FORMAT DELIMITED
FIELDS TERMINATED BY '\t'
LINES TERMINATED BY '\n'
STORED AS TEXTFILE
