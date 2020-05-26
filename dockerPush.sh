#!/bin/bash
set -e
source dockerimage.config
aws ecr get-login-password --region eu-north-1 \
| docker login --username AWS --password-stdin $ECR_URI
docker push "${ECR_URI}:${TAG}"
