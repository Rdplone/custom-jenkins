#!/usr/bin/env bash
set -euo pipefail

REGISTRY_URL="your.registry.local:5000"
IMAGE_NAME="custom-jenkins"
VERSION=$(cat VERSION)

echo "Pushing Docker image..."
docker push $REGISTRY_URL/$IMAGE_NAME:$VERSION
docker push $REGISTRY_URL/$IMAGE_NAME:latest
