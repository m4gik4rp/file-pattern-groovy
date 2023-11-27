#!/bin/bash

# Specify the path to your Groovy script
SCRIPT_PATH="app/src/main/java/file/pattern/groovy/CLFilePattern.groovy"

# Pass command line arguments to the Groovy script
groovy "$SCRIPT_PATH" "$@"
