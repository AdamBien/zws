#!/bin/sh
BASEDIR=$(dirname $0)
java --source 25 ${BASEDIR}/zws "$@"
