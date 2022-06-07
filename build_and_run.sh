#!/bin/bash
set -e

mvn spring-boot:run &

sleep 5

pushd vue-material-dashboard-master

npm install
npm run build
pushd dist
tar -zcvf  dist.tar.gz  *Compress all files in the current directory && fg

pop
pop