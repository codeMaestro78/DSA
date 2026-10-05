#!/usr/bin/env bash

set -euo pipefail

ROOT_DIR="$(cd "$(dirname "${BASH_SOURCE[0]}")" && pwd)"
BUILD_DIR="$ROOT_DIR/.cp-build"
DEFAULT_INPUT="$ROOT_DIR/input.txt"

usage() {
    cat <<'EOF'
Usage:
  ./cp-run.sh <Java-file>                 Run with input.txt
  ./cp-run.sh <Java-file> <input-file>    Run with a specific input file
  ./cp-run.sh <Java-file> --stdin         Read input from the terminal
  ./cp-run.sh <Java-file> --all <folder>  Run every *.in file in a folder

Examples:
  ./cp-run.sh Codeforces/Q200B_Drinks.java
  ./cp-run.sh Codeforces/Q200B_Drinks.java tests/Q200B
  ./cp-run.sh Codeforces/Q200B_Drinks.java --all tests/Q200B
EOF
}

if [[ $# -lt 1 || "$1" == "-h" || "$1" == "--help" ]]; then
    usage
    exit 0
fi

SOURCE="$1"
shift

if [[ "$SOURCE" != /* ]]; then
    SOURCE="$ROOT_DIR/$SOURCE"
fi

if [[ ! -f "$SOURCE" || "${SOURCE##*.}" != "java" ]]; then
    echo "Error: Java source file not found: ${SOURCE#$ROOT_DIR/}" >&2
    exit 1
fi

SOURCE_RELATIVE="${SOURCE#$ROOT_DIR/}"
PACKAGE_PATH="$(dirname "$SOURCE_RELATIVE")"
CLASS_NAME="$(basename "$SOURCE_RELATIVE" .java)"

if [[ "$PACKAGE_PATH" == "." ]]; then
    MAIN_CLASS="$CLASS_NAME"
else
    MAIN_CLASS="${PACKAGE_PATH//\//.}.$CLASS_NAME"
fi

rm -rf "$BUILD_DIR"
mkdir -p "$BUILD_DIR"

echo "Compiling $SOURCE_RELATIVE..."
javac -encoding UTF-8 -d "$BUILD_DIR" -cp "$BUILD_DIR" "$SOURCE"

run_input() {
    local input_file="$1"
    echo ""
    if [[ -n "$input_file" ]]; then
        echo "--- ${input_file#$ROOT_DIR/} ---"
        java -cp "$BUILD_DIR" "$MAIN_CLASS" < "$input_file"
    else
        java -cp "$BUILD_DIR" "$MAIN_CLASS"
    fi
}

if [[ $# -eq 0 ]]; then
    if [[ ! -f "$DEFAULT_INPUT" ]]; then
        : > "$DEFAULT_INPUT"
    fi
    run_input "$DEFAULT_INPUT"
elif [[ "$1" == "--stdin" ]]; then
    run_input ""
elif [[ "$1" == "--all" && $# -eq 2 ]]; then
    TEST_DIR="$2"
    shopt -s nullglob
    TEST_FILES=("$TEST_DIR"/*.in)
    if [[ ${#TEST_FILES[@]} -eq 0 ]]; then
        echo "Error: no .in files found in $TEST_DIR" >&2
        exit 1
    fi
    for test_file in "${TEST_FILES[@]}"; do
        run_input "$test_file"
    done
else
    if [[ ! -f "$1" ]]; then
        echo "Error: input file not found: $1" >&2
        exit 1
    fi
    run_input "$1"
fi
