#!/bin/sh

if [ $1 == "" ];then 
	echo "Usage: $0 backend_path"
	echo "backend_path - path to root where backend is located"
fi

THIS_DIR="$(dirname $0)"
BACKEND_DIR="$(readlink -f $THIS_DIR)"
SOURCE_DIR="$BACKEND_DIR/src/main/java"
RESOURCES_DIR="$BACKEND_DIR/src/main/resources"
TEST_DIR="$BACKEND_DIR/src/test"

function run_docker() {
	docker run \
		-it --rm \
		--name soap_tryout \
		-v "$BACKEND_DIR/pom.xml":/usr/src/mymaven/pom.xml \
		-v "$SOURCE_DIR":/usr/src/mymaven/src/main/java \
		-v "$TEST_DIR":/usr/src/mymaven/src/test \
		-v "$RESOURCES_DIR/":/usr/src/mymaven/src/main/resources/ \
		-v "$BACKEND_DIR/.m2/":/root/.m2 \
		-w /usr/src/mymaven \
		--publish 9000:8080 \
		maven mvn $1
}

if [ $2 = "test" ]; then
	run_docker test
else
	run_docker spring-boot:run
fi
