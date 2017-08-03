#!/bin/bash
clear
echo "Creating docker network between mongo and backend containers"
docker network create xyincnet
echo "Running mongodb docker container"
docker run --name xyincmongodbcontainer --network=xyincnet -p 27017:27017 -d mongo
echo "Building an image of our backend project's Dockerfile"
docker build --tag=xyincbackend .
echo "Running backend docker container"
docker run -d --name xyincbackendcontainer --network=xyincnet -p 8080:8080  xyincbackend