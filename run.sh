#!/bin/bash

kill -9 $(ps ax | grep dista_cours.jar | fgrep -v grep | awk '{ print $1 }')
nohup java -jar dista_cours.jar --spring.datasource.username=$DATABASE_USERNAME --spring.datasource.password=$DATABASE_PASSWORD &