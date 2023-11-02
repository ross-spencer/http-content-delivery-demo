# 📬 HTTP Content Delivery Demo 📬

A demo of server content delivery enabling users to select HTTP response headers
for themselves.

Primary purpose is to observe the effect of different HTTP response headers in
various browsers after discovering quite wildly different behaviours in Internet
Explorer vs. Chrome when delivering binary content 'inline'. This approximates
the delivery of files from the Rosetta Long Term Preservation system v4.0.1 at
least.

## To run

### Set the file modification dates

The content server should deliver files with dates older than `$TODAY`. We can
achieve that using the `touch` command. More info [here][touch-1].

[touch-1]: https://www.thegeekstuff.com/2012/11/linux-touch-command/

There is a small script included in this demo which will set all the dates for
the sample files to `1970-01-01`:

```sh
#! /usr/bin/bash

set -eux

echo "setting time and date on files"
 $(TZ=UTC touch -a -m -t 197001010000.01 file1.tga)
 $(TZ=UTC touch -a -m -t 197001010000.01 file2.rtf)
 $(TZ=UTC touch -a -m -t 197001010000.01 file3.txt)
 $(TZ=UTC touch -a -m -t 197001010000.01 file4.ppp)
 $(TZ=UTC touch -a -m -t 197001010000.01 file5.ai)
echo "done"
```

Run `./touch-all.sh` which already includes these commands. If you need to set
the script's permissions, simply: `chmod +x touch-all.sh`.

### Building and running the server

A simple golang app can be built and run with the commands below:

```sh
$go build
$http-content-delivery-demo
```

You should see output such as:

```txt
2023/11/02 21:40:47 Server listening on port 2040.
```

Access the site in a web-browser with: http://127.0.0.1:2040

## Content

Content here is either licensed under Archives New Zealand Catalogue terms, or
provided as fair use for the purpose of archival research.

## Content-disposition: Archival

This small app is also in support of the blog: *Content-disposition: Archival –
Repatriating Dates on Access to Born-digital Records Online* on the
[OPF website][opf-1].

[opf-1]: https://openpreservation.org/blogs/content-disposition-archival-repatriating-dates-on-access-to-born-digital-records-online/?page=3&q=33

## License

Copyright (c) 2015 Ross Spencer

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
