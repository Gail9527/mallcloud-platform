#!/bin/bash
echo -- -- begin init mallcloud eureka... -- --

COMPOSE_FILE=./mallcloud-docs/docker/docker-compose-eureka.yml
JAR_DIR=./mallcloud-docs/docker/jar

echo -- -- stop and remove old docker-compose containers -- --
if docker-compose -f ${COMPOSE_FILE} ps
    then
        docker-compose -f ${COMPOSE_FILE} stop
        docker-compose -f ${COMPOSE_FILE} rm --force
fi

echo -- -- building jar -- --
cd mallcloud-cloud
mvn clean package -Dmaven.test.skip=true -pl cloud-registry,cloud-config -am
cd ..

echo -- -- move jar to ${JAR_DIR} -- --
if [ ! -d ${JAR_DIR} ];then
   mkdir -p ${JAR_DIR}
fi

rm -rf ${JAR_DIR}/cloud-registry*.jar
rm -rf ${JAR_DIR}/cloud-config*.jar

cp ./mallcloud-cloud/cloud-registry/target/cloud-registry*.jar ${JAR_DIR}
cp ./mallcloud-cloud/cloud-config/target/cloud-config*.jar ${JAR_DIR}

echo -- -- run docker-compose up -- --
docker-compose -f ${COMPOSE_FILE} up -d --build

docker images|grep none|awk '{print $3 }'|xargs docker rmi
