#!/bin/bash
set -e
source dockerimage.config
IMAGE_EXPOSED_PORT=8080
PORT=80
echo "----------------------------------------------------------------"
echo "| The application will be available at: http://localhost:$PORT  "
echo "----------------------------------------------------------------"
docker run -p $PORT:"$IMAGE_EXPOSED_PORT" "$IMAGE_NAME":"$TAG"
