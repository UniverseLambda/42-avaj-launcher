#!/bin/sh
mkdir -p bin
sources=$(find * -name "*.java")
javac $sources -d bin --release 17

java -cp bin com.clsaad.avaj.Main scenario.txt