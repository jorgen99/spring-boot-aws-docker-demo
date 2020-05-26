#!/bin/bash
set -e
source dockerimage.config
docker build -t $IMAGE_NAME .
docker tag ${IMAGE_NAME}:${TAG} ${ECR_URI}:${TAG}
