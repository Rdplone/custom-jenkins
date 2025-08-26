#!/usr/bin/env bash
set -euo pipefail

VERSION=$(cat VERSION)

echo "📝 Generating changelog for v$VERSION ..."
git cliff --tag "v$VERSION" --output CHANGELOG.md

echo "✔ CHANGELOG.md updated."
