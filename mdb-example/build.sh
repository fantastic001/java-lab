#!/bin/sh

THIS_DIR=$(dirname $0)
PROJECT_DIR="$(readlink -f $THIS_DIR)"

function run_docker() {
	docker run \
		-it --rm \
		-v "$PROJECT_DIR/":/usr/src/mymaven/ \
		-v "$PROJECT_DIR/.m2/":/root/.m2 \
		-w /usr/src/mymaven \
		maven mvn -e -X $*
}

if [ $2 = "test" ]; then
	run_docker test
else
	run_docker clean package 
fi

pushd . 
cd $THIS_DIR


if [ -d deploy ]; then 
	rm -rf deploy
fi 

mkdir -p deploy
cp target/*.war deploy/

popd 
