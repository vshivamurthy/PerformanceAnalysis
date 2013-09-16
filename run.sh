#!/bin/sh
export 'CLASSPATH=/etc/hadoop/conf:/usr/lib/hadoop/lib/*:/usr/lib/hadoop/.//*:/usr/lib/hadoop-hdfs/./:/usr/lib/hadoop-hdfs/lib/*:/usr/lib/hadoop-hdfs/.//*:/usr/lib/hive/lib/*:/usr/lib/hive/./:/usr/lib/hadoop-yarn/lib/*:/usr/lib/hadoop-yarn/.//*:/usr/lib/hadoop-mapreduce/lib/*:/usr/lib/hadoop-mapreduce/.//*:.'
while getopts "r:t:d:s:" OPTION
do
    case $OPTION in
    r) RUN=$OPTARG;;
    t) TYPE=$OPTARG;;
    d) DAY=$OPTARG;;
    s) STACK=$OPTARG;;
    esac
done

java RunStats $STACK $DAY $RUN $TYPE 
