#!/usr/bin/env bash
set -e

cd -- "$(dirname -- "$0")"

if [ -d build ]; then
    find build -type f -name "*.class" -delete
fi

find * -name "*.java" > sources.txt
javac -d build @sources.txt