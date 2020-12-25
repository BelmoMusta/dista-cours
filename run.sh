#!/bin/bash
PROCESS_ID=$(ps ax | grep dista_cours.jar | fgrep -v grep | awk '{ print $1 }')
if [[  "$PROCESS_ID" ]]
then
    kill -9 "$PROCESS_ID"
fi
nohup java -jar ./dista_cours.jar --spring.datasource.username=$DATABASE_USERNAME --spring.datasource.password=$DATABASE_PASSWORD &