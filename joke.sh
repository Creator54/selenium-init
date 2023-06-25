#!/usr/bin/env bash

output_file=$(mktemp)

./gradlew run 2>&1 | tee "${output_file}"

joke=$(sed -n '/INFO/,$p' "${output_file}" | head -n +2 | tail -n +2)

sed -i "s/$(sed -n '/joke/,/```/p' README.md | grep -v '`')/$joke/g" README.md

rm "${output_file}"
