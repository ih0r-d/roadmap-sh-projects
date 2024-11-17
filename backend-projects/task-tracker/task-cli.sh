#!/bin/bash


./mvnw clean package -q
java -jar target/task-cli.jar "$1" "$2" "$3"
