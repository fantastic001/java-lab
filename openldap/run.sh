#!/bin/sh

docker run -it --rm \
	-p 389:389 -p 636:636 \
	--name my-openldap-container  osixia/openldap:1.3.0

