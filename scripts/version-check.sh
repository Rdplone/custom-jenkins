#!/usr/bin/env bash
set -euo pipefail

VERSION_FILE="VERSION"
VERSION=$(cat $VERSION_FILE)

if [[ -z "$VERSION" ]]; then
    echo "ERROR: VERSION file is empty!"
    exit 1
fi

if [[ ! "$VERSION" =~ ^[0-9]+\.[0-9]+\.[0-9]+$ ]]; then
    echo "ERROR: VERSION must follow SemVer (X.Y.Z)"
    exit 1
fi

echo "✔ VERSION $VERSION is valid."
