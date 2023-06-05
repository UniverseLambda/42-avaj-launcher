#!/bin/sh
mkdir -p bin
sources=$(find * -name "*.java")
javac $sources -d bin --release 17

pushd bin
java com.clsaad.avaj.Main
popd