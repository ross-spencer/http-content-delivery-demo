package main

import (
    "fmt"
    "html/template"
    "log"
    "net/http"
)

func filedownload(w http.ResponseWriter, r *http.Request) {
    fmt.Println("method:", r.Method) //get request method
    if r.Method == "GET" {
        fmt.Println("path", r.URL.Path)
        t, _ := template.ParseFiles("http-content-delivery-demo.gtpl")
        t.Execute(w, nil)
    } else {
        r.ParseForm()

        // logic part of log in
        fmt.Println(r.Form)

        file := r.Form.Get("file")
        dis := r.Form.Get("disposition") + ";filename=" + file
        mime := r.Form.Get("mimetype")
        description := r.Form.Get("contentdescription")
        encoding := r.Form.Get("transferencoding")

        fmt.Println(dis)
        fmt.Println(mime)
        fmt.Println(description)
        fmt.Println(encoding)

        w.Header().Set("Content-Disposition", dis)
        w.Header().Set("Content-Type", mime)
        w.Header().Set("Content-Description", description)
        w.Header().Set("Content-Transfer-Encoding", encoding)

        fmt.Println(w.Header())

        http.ServeFile(w, r, file)
    }
}

func main() {
    http.HandleFunc("/", filedownload) // setting router rule
    err := http.ListenAndServe(":2040", nil) // setting listening port
    if err != nil {
        log.Fatal("ListenAndServe: ", err)
    }
}