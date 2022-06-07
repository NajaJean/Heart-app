#!/bin/bash
set -e

mvn spring-boot:run &

sleep 5

pushd vue-material-dashboard-master

npm install
npm run build && fg

pop