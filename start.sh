#!/bin/sh
echo "Trying to connect to MySQL at ${DATABASE_HOST}:${DATABASE_PORT}..."
#while ! [ -d /var/lib/mysql/${DATABASE_NAME} ] ; do
#    echo "Trying to connect to MySQL at ${DATABASE_HOST}:${DATABASE_PORT}..."
#    sleep 10
#done
echo ">> connected to MySQL database! <<"
java -Djava.security.egd=file:/dev/./urandom -Dspring.profiles.active=container -jar /app.jar