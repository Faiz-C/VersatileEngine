#!/bin/bash

 SAGE="USAGE:\n
    run_mvn -i [--install] -c [--compile] -t [--test] path.to.mainClass\n
    optional arguments: [-t -i -c -r]\n
    
    -i, --install : clean install before running\n
    -c, --compile : compile maven project and java code before running\n
    -t, --test : specifies whether the main class is in project src or test directories"

# Check if pom.xml is available, if not then we stop
if [ ! -f "pom.xml" ]; then
  echo "Cannot execute, pom.xml is not found..."
fi 

INSTALL=false
COMPILE=false
TESTING=false
MAINCLASSPATH=$BASH_ARGV

while [[ $# -gt 0 ]];
do
  key=$1
  case $key in
    -i|--install)
      INSTALL=true
      ;;
    -c|--compile)
      COMPILE=true
      ;;
    -t|--test)
      TESTING=true
      ;;
  esac
  shift
  shift
done

# CHECK THE PATH
if [ -z $MAINCLASSPATH ] || [ -f $MAINCLASSPATH ]; then 
  echo "Cannot execute, path error..."
  echo -e $USAGE
fi

# Maven Clean Install
if [ $INSTALL = true ]; then
  mvn clean install
fi 

# Maven Compile
if [ $COMPILE = true ]; then
  mvn compile
fi

# Exec the main class file now
if [ $TESTING = true ]; then
  mvn exec:java -Dexec.mainClass="$MAINCLASSPATH" -Dexec.classpathScope="test"
else
  mvn exec:java -Dexec.mainClass="$MAINCLASSPATH" 
fi



