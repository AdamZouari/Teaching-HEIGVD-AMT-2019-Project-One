#!/bin/bash

if [ $# = 0 ]; then
	echo "You must provide an argument. 'prod' or 'dev' "
	exit 1;
fi
echo "Environnement $1 is running ....."
mvn clean install -q -Dmaven.test.skip=true

if [ $1 = "prod" ]; then
	mv target/projectone.war images/payara/
fi
cd docker/topologies/chillout-${1} && docker-compose down
docker-compose up --build && cd ../../
