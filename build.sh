#!/bin/bash
echo "**************************************"
echo "*         BUILD JAVA PROJECT         *"
echo "**************************************"

CUR_DIR=`pwd`
OUT_DIR="$CUR_DIR/output"
SOURCE_DIR="$CUR_DIR"
SOURCE_FILE_LIST="$OUT_DIR/java_file.list"

if [ ! -d $OUT_DIR ]
then
	mkdir $OUT_DIR;
else
	rm -r $OUT_DIR;
	mkdir $OUT_DIR;
fi

touch "$SOURCE_FILE_LIST"
find $SOURCE_DIR -name "*.java" > $SOURCE_FILE_LIST
cat $SOURCE_FILE_LIST
javac -d $OUT_DIR "@$SOURCE_FILE_LIST"
if [[ $? != 0 ]]
then 
	exit;
fi

echo "**************************************"
echo "*             SUCCESS                *"
echo "**************************************"

