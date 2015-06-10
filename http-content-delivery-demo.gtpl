<html>
<head>
<title></title>
</head>
<body>
<form action="/filedownload" method="post">
   Disposition: 
      <select name="disposition">
         <option value="inline">inline</option>
         <option value="attach">attachment</option>
      </select>
   MIMEType:
      <select name="mimetype">
         <option value="">none</option>
         <option value="*/*">Any</option>
         <option value="application/rtf">application/rtf</option>
         <option value="application/pdf">application/pdf</option>
         <option value="application/octet-stream">application/octet-stream</option>         
         <option value="application/postscript">application/postscript</option>
         <option value="text/plain">text/plain</option>
         <option value="text/plain; charset=utf-8">text/plain; charset=utf-8</option>         
         Content-Type: 
      </select>
   Content-Transfer-Encoding:
      <select name="transferencoding">
         <option value="binary">binary</option>
         <option value="8bit">8bit</option>
         <option value="7bit">7bit</option>
         <option value="QUOTED-PRINTABLE">QUOTED-PRINTABLE</option>
         <option value="x-token">x-token</option>
         <option value="BASE64">BASE64</option>
      </select>
   Content-Description:
      <select name="contentdescription">
         <option value="File Transfert">File Transfert</option>
         <option value="File Transfer">File Transfer</option>
      </select>
   File: 
      <select name="file">
         <option value="file1.tga">file1.tga</option>
         <option value="file2.rtf">file2.rtf</option>
         <option value="file3.txt">file3.txt</option>
         <option value="file4.ppp">file4.ppp</option>
      </select>
   <input type="submit" value="Login">
</form>
</body>
</html>