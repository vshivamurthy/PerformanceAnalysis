#!/bin/sh
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
