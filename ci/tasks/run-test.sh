#!/usr/bin/env bash

set -e -u -x
echo "start................"
pushd resource-git-cookbook_api

  echo "Compiling................"
  mvn clean compile
  echo "Running Tests................"
  mvn test

popd

  echo "Testing Done !!!"