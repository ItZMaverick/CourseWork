#!/bin/bash

# Compile the code and tests
javac -cp .:junit-4.10.jar:hamcrest-core-1.3.jar *.java

# Run the tests
java -cp .:junit-4.10.jar:hamcrest-core-1.3.jar org.junit.runner.JUnitCore TestRunner
