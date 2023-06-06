#!/bin/sh
set -e

mkdir -p bin
sources=$(find * -name "*.java")
javac $sources -d bin --release 17

java -cp bin com.clsaad.avaj.Main ${1:-scenario.txt}