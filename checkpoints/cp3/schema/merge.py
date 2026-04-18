#! /usr/bin/env python

import os

MERGED_FILE_NAME = "merged.sql"

concatenated = ""
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
