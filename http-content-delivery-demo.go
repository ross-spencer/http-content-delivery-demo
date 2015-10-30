package main

import (
    "os"
    "html/template"
    "log"
    "net/http"
    "strings"
    "time"
)

func filedownload(w http.ResponseWriter, r *http.Request) {
   log.Println("Method:", r.Method) //get request method
   if r.Method == "GET" {
      log.Println("path", r.URL.Path)
      t, _ := template.ParseFiles("http-content-delivery-demo.gtpl")
      t.Execute(w, nil)
   } else {
      r.ParseForm()

      // logic part of log in
      log.Println("Form: ", r.Form)

      file := r.Form.Get("file")
      log.Println(r.Form.Get("disposition"))
      dis := r.Form.Get("disposition") + "; filename=" + file 
      mime := r.Form.Get("mimetype")
      description := r.Form.Get("contentdescription")
      encoding := r.Form.Get("transferencoding")
      lastmodified := r.Form.Get("lastmodified")

      log.Println("Disposition:", dis)
      log.Println("Mime:", mime)
      log.Println("Description:", description)
      log.Println("Encoding:", encoding)

      w.Header().Set("Content-Disposition", dis)
      w.Header().Set("Content-Type", mime)
      w.Header().Set("Content-Description", description)
      w.Header().Set("Content-Transfer-Encoding", encoding)

      if strings.Compare("Today", lastmodified) != 0 {
         log.Println("Last modified date:", lastmodified)
         t, _ := time.Parse(time.RFC1123, lastmodified)
         log.Println("Time converted from browser:", t) 
         os.Chtimes(file, t, t)
         w.Header().Set("Last-Modified", lastmodified)
      } else {
        currenttime := time.Now().Local()
        os.Chtimes(file, currenttime, currenttime)

        //Don't set header - Golang will set it - here as a note
        //w.Header().Set("Last-Modified", currenttime.Format(time.RFC1123))
      }

      log.Println(w.Header())
      http.ServeFile(w, r, file)
    }
}

func main() {
   log.Println("Server listening on port 2040.")
   http.HandleFunc("/", filedownload) // setting router rule
   err := http.ListenAndServe(":2040", nil) // setting listening port
   if err != nil {
      log.Fatal("ListenAndServe: ", err)
   }
}