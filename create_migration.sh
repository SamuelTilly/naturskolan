#!/bin/bash

# Check if a description argument is provided
if [ -z "$1" ]; then
    echo "Usage: $0 <description>"
    exit 1
fi

# Get the current timestamp in ISO 8601 format
timestamp=$(date -u +"%Y-%m-%dT%H:%M:%S")

# Create an empty migration script file with the timestamp and description
folder=src/main/resources/db/migration
filename="${timestamp}_migration_$1.sql"
touch "${folder}/$filename"

echo "Empty migration script '$filename' created with description '$1'."
