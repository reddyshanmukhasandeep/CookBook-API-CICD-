#!/usr/bin/env bash

set -ex
echo "Building................"
pushd resource-git-cookbook_api
  export TERM=dumb
    pwd
    ls -lart
    mvn clean install -e -DskipTests=true
    echo "Moving binary ................"
    pwd
    ls -lart
    cd target
    ls -lart
    mv *.jar ../../jarFile/
    cd ../../jarFile
    ls -lart
popd
echo "Build Completed !!!"
