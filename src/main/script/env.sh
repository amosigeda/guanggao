#!/bin/bash  
  
#Config your java home  
#JAVA_HOME=/opt/jdk/  
  
if [ -z "$JAVA_HOME" ]; then  
  export JAVA=`which java`  
else  
  export JAVA="$JAVA_HOME/bin/java"  
fi
  
UEAP_HOME=$BASE_DIR  
SERVER_NAME="feeServer"  
STARTUP_CLASS="com.ymei_inc.feeserver.App"  
  
#Ueap JMX port  
export JMX_PORT=9123  
export CLASSPATH=$BASE_DIR/conf:$(ls $BASE_DIR/lib/*.jar | tr '\n' :)  
  
#UEAP jvm args  
UEAP_JVM_ARGS="-Xmx512m -Xms256m -server"  
UEAP_JVM_ARGS="$UEAP_JVM_ARGS -cp $CLASSPATH -Dueap.home=$ueap_home -Dcollect.start.worker=false"  
  
if [ -z "$UEAP_ARGS" ]; then  
  export UEAP_ARGS="$UEAP_JVM_ARGS"  
fi