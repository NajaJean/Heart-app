#!/bin/bash
set -e

mvn spring-boot:run &

sleep 5

pushd vue-material-dashboard-master

npm install
npm run build
npm install -g serve  
serve -s dist && fg

pop
pop