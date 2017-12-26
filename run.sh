#!/bin/bash
echo "********************************"
echo "*            RUNNING....       *"
echo "********************************"

CUR_DIR=`pwd`
MAINCLASS="MainWindow"
NEWCLASSPATH="$CUR_DIR/output:$CLASSPATH"
echo $NEWCLASSPATH
cd output && java $MAINCLASS
