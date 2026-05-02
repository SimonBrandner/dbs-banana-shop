#! /usr/bin/env python

import subprocess
import os

MERGED_FILE_NAME = "merged.sql"
SCHEMA_DIRECTORY = "../../cp3/schema/"
SCHEMA_MERGE_SCRIPT = SCHEMA_DIRECTORY + "/merge.py"
SCHEMA_MERGED = SCHEMA_DIRECTORY + "/merged.sql"

concatenated = ""

result = subprocess.run(
    ["python", SCHEMA_MERGE_SCRIPT],
    cwd=SCHEMA_DIRECTORY,
)
if result.stderr:
    print("There were errors when merging the schema")
    raise Exception

with open(SCHEMA_MERGED, "r") as f:
    concatenated += f.read()
    concatenated += "\n"


for file in sorted(os.listdir("./")):
    if not file.endswith(".sql"):
        continue
    if file == MERGED_FILE_NAME:
        continue
    if file == "main.sql":
        continue

    with open(file, "r") as f:
        concatenated += f.read()
        concatenated += "\n"

concatenated = concatenated[: len(concatenated) - 1]

with open(MERGED_FILE_NAME, "w") as f:
    f.write(concatenated)
