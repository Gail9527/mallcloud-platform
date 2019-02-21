#!/bin/bash
echo -- -- begin init mallcloud... -- --

COMPOSE_FILE=./mallcloud-docs/docker/docker-compose.yml
JAR_DIR=./mallcloud-docs/docker/jar

echo -- -- stop and remove old docker-compose containers -- --
if docker-compose -f ${COMPOSE_FILE} ps
    then
        docker-compose -f ${COMPOSE_FILE} stop
        docker-compose -f ${COMPOSE_FILE} rm --force
fi

echo -- -- building jar -- --
mvn clean package -Dmaven.test.skip=true

echo -- -- move jar to ${JAR_DIR} -- --
if [ ! -d ${JAR_DIR} ];then
   mkdir -p ${JAR_DIR}
fi

rm -rf ${JAR_DIR}/mallcloud-authentication*.jar
rm -rf ${JAR_DIR}/mallcloud-rbac*.jar
rm -rf ${JAR_DIR}/cloud-admin*.jar
rm -rf ${JAR_DIR}/cloud-monitor*.jar
rm -rf ${JAR_DIR}/mallcloud-gateway*.jar

cp ./mallcloud-authentication/target/mallcloud-authentication*.jar ${JAR_DIR}
cp ./mallcloud-rbac/target/mallcloud-rbac*.jar ${JAR_DIR}
cp ./mallcloud-cloud/cloud-admin/target/cloud-admin*.jar ${JAR_DIR}
cp ./mallcloud-cloud/cloud-monitor/target/cloud-monitor*.jar ${JAR_DIR}
cp ./mallcloud-gateway/target/mallcloud-gateway*.jar ${JAR_DIR}

echo -- -- run docker-compose up -- --
docker-compose -f ${COMPOSE_FILE} up -d --build

docker images|grep none|awk '{print $3 }'|xargs docker rmi
