#!/bin/bash

# Movie Manager - Run script
# Uses Maven's javafx-maven-plugin which properly configures JavaFX modules

set -e

echo "🎬 Starting Movie Manager Application..."
echo ""
echo "Building and launching application (this may take a moment)..."
echo ""

./mvnw javafx:run

echo ""
echo "Application closed."
