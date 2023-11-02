#! /usr/bin/bash

set -eux

echo "setting time and date on files"
 $(TZ=UTC touch -a -m -t 197001010000.01 file1.tga)
 $(TZ=UTC touch -a -m -t 197001010000.01 file2.rtf)
 $(TZ=UTC touch -a -m -t 197001010000.01 file3.txt)
 $(TZ=UTC touch -a -m -t 197001010000.01 file4.ppp)
 $(TZ=UTC touch -a -m -t 197001010000.01 file5.ai)
echo "done"
