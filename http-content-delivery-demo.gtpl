<html>
   <head>
      <title>✉️ HTTP Content Delivery Demo ✉️</title>
   </head>
   <body>
      <h1>✉️ HTTP Content Delivery Demo ✉️</h1>
      <h2>Introduction</h2>
      If you are looking at this page, you're likely looking at it locally.
      Please make sure you have run the shell script `./touch-all` in this
      repository to make sure historical dates are set for all the files.
      <br><br>
      Otherwise, this site serves as a method of testing different HTTP headers
      and the behavior of tools interacting with those.
      <br><br>
      The work is part of a blog for the Open Preservation Foundation, and
      perhaps the basis for other work in the future:
      <a href="https://openpreservation.org/blogs/content-disposition-archival-repatriating-dates-on-access-to-born-digital-records-online/" target="_blank">
        Content-disposition: Archival – Repatriating Dates on Access to Born-digital Records Online
      </a>
      <h2>Browser download options</h2>
      <div>
         The browser does not have access to the file system and so it will
         not change last modified dates to those on the remote server.
      </div>
      <form action="/filedownload" method="post">
         Disposition:
         <select name="disposition">
            <option value="inline">inline</option>
            <option value="attach">attachment</option>
            <option value='attachment; modification-date="Thu, 01 Jan 1970 00:00:01 GMT"'>attachment; modification-date="Thu, 01 Jan 1970 00:00:01 GMT"</option>
            <option value='attachment; modification-date="Tue, 15 Nov 1994 12:45:26 GMT"'>attachment; modification-date="Tue, 15 Nov 1994 12:45:26 GMT"</option>
            <option value='attachment; modification-date="Wed, 25 Dec 1982 09:15:55 GMT"'>attachment; modification-date="Wed, 25 Dec 1982 09:15:55 GMT"</option>
         </select>
         <br>MIMEType:
         <select name="mimetype">
            <option value="application/octet-stream">application/octet-stream</option>
            <option value="">None</option>
            <option value="*/*">Any</option>
            <option value="application/rtf">application/rtf</option>
            <!--PDF types-->
            <option value="application/pdf">application/pdf</option>
            <option value="application/postscript">application/postscript</option>
            <option value="application/illustrator">application/illustrator</option>
            <!--Others...-->
            <option value="application/octet-stream">application/octet-stream</option>
            <option value="audio/x-flac">audio/x-flac</option>
            <!--text plain-->
            <option value="text/plain">text/plain</option>
            <option value="text/plain; charset=utf-8">text/plain; charset=utf-8</option>
            Content-Type:
         </select>
         <br>Content-Transfer-Encoding:
         <select name="transferencoding">
            <option value="binary">binary</option>
            <option value="8bit">8bit</option>
            <option value="7bit">7bit</option>
            <option value="QUOTED-PRINTABLE">QUOTED-PRINTABLE</option>
            <option value="x-token">x-token</option>
            <option value="BASE64">BASE64</option>
         </select>
         <br>Content-Description:
         <select name="contentdescription">
            <option value="File Transfert">File Transfert</option>
            <option value="File Transfer">File Transfer</option>
         </select>
         <br>Last-Modified:
         <!--E.G. Tue, 15 Nov 1994 12:45:26 GMT-->
         <select name="lastmodified">
            <option value="Thu, 01 Jan 1970 00:00:01 GMT">Thu, 01 Jan 1970 00:00:01 GMT</option>
            <option value="Tue, 15 Nov 1994 12:45:26 GMT">Tue, 15 Nov 1994 12:45:26 GMT</option>
            <option value="Wed, 25 Dec 1982 09:15:55 GMT">Wed, 25 Dec 1982 09:15:55 GMT</option>
            <!--do not set the last-modified date-->
            <option value="Today">Today (do not set header)</option>
         </select>
         <br>File:
         <select name="file">
            <option value="file1.tga">file1.tga</option>
            <option value="file2.rtf">file2.rtf</option>
            <option value="file3.txt">file3.txt</option>
            <option value="file4.ppp">file4.ppp</option>
            <option value="file5.ai">file5.ai</option>
            <option value="file6.flac">file6.flac</option>
         </select>
         <br><br><input type="submit" value="Download">
      </form>
      <h2>cURL and wget</h2>
      <div>
         On the other hand cURL and wget are just two utilities that will
         attempt to set the local date and time to that on the remote server.
         Useful for looking at archived records!
         <br><br>
         Try downloading  using cURL and wget with the following links.
         <br><br>
         Use the following commands to preserve the dates on the server.
         <pre>
   curl -vsR http://0.0.0.0:2040/&#60;filename&#62;
   wget -S http://0.0.0.0:2040/&#60;filename&#62;
</pre>
         <ul>
            <li>
               <a href="file1.tga">file1.tga</a>
               <pre>curl -vsR http://0.0.0.0:2040/file1.tga --output file1.tga</pre>
               | or
               <pre>wget -S http://0.0.0.0:2040/file1.tga</pre>
            </li>
            <li>
               <a href="file2.rtf">file2.rtf</a>
               <pre>curl -vsR http://0.0.0.0:2040/file2.rtf --output file2.rtf</pre>
               | or
               <pre>wget -S http://0.0.0.0:2040/file2.rtf</pre>
            </li>
            <li>
               <a href="file3.txt">file3.txt</a>
               <pre>curl -vsR http://0.0.0.0:2040/file3.txt --output file3.txt</pre>
               | or
               <pre>wget -S http://0.0.0.0:2040/file3.txt</pre>
            </li>
            <li>
               <a href="file4.ppp">file4.ppp</a>
               <pre>curl -vsR http://0.0.0.0:2040/file4.ppp --output file4.ppp</pre>
               | or
               <pre>wget -S http://0.0.0.0:2040/file4.ppp</pre>
            </li>
            <li>
               <a href="file5.ai">file5.ai</a>
               <pre>curl -vsR http://0.0.0.0:2040/file5.ai --output file5.ai</pre>
               | or
               <pre>wget -S http://0.0.0.0:2040/file5.ai</pre>
            </li>
         </ul>
      </div>
      <h2>Bulk download</h2>
      Bulk download using cURL (copy and paste into terminal):
      <pre>
curl -vsR http://0.0.0.0:2040/file1.tga --output file1.tga
curl -vsR http://0.0.0.0:2040/file2.rtf --output file2.rtf
curl -vsR http://0.0.0.0:2040/file3.txt --output file3.txt
curl -vsR http://0.0.0.0:2040/file4.ppp --output file4.ppp
curl -vsR http://0.0.0.0:2040/file5.ai --output file5.ai
      </pre>
      Bulk download using wget (copy and paste into terminal):
      <pre>
wget -S http://0.0.0.0:2040/file1.tga
wget -S http://0.0.0.0:2040/file2.rtf
wget -S http://0.0.0.0:2040/file3.txt
wget -S http://0.0.0.0:2040/file4.ppp
wget -S http://0.0.0.0:2040/file5.ai
      </pre>
      <h2>More information</h2>
      <div>
      For more information about the commands here check out <a href="https://explainshell.com/" target="_blank">explainshell.com</a>
      </div>
      <br>
      <br>
   </body>
</html>
