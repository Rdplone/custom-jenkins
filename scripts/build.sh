#!/usr/bin/env bash
set -euo pipefail

REGISTRY_URL="your.registry.local:5000"
IMAGE_NAME="custom-jenkins"

VERSION=$(cat VERSION)

echo "Building Docker image: $REGISTRY_URL/$IMAGE_NAME:$VERSION"

docker build -t $REGISTRY_URL/$IMAGE_NAME:$VERSION -t $REGISTRY_URL/$IMAGE_NAME:latest .
