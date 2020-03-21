#!/bin/bash

THIS_DIR="$(readlink -f $(dirname "$0"))"

docker run --rm \
	-p 8080:8080 \
	-p 9990:9990 \
	--volume "$THIS_DIR":/opt/code/ \
	-it jboss/wildfly \
	/opt/jboss/wildfly/bin/standalone.sh -b 0.0.0.0 -bmanagement 0.0.0.0

