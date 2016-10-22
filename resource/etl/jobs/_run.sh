#!/bin/sh
ETL_BASE=/data/scctbi
KETTLE_DIR=$ETL_BASE/data-integration
REP_PARAMS="-rep=SCCTBI -user=admin -pass=admin"
LOG_DIR=$ETL_BASE/log/`date +"%Y%m%d"`/$JOB_DIR/
mkdir -p $LOG_DIR
LOG_FILE=$LOG_DIR$JOB_NAME_`date +"%H%M"`.log
JOB_DIR=$1
JOB_NAME=$2
shift;shift
$KETTLE_DIR/kitchen.sh $REP_PARAMS -logfile=$LOG_FILE -dir=$JOB_DIR -job=$JOB_NAME $*
