package main

import (
	"encoding/json"
	"html/template"
	"log"
	"net/http"
	"os"
	"strings"
	"time"
)

// handlePath returns files requested through GET.
func handlePath(w http.ResponseWriter, r *http.Request) {
	if r.URL.Path == "/favicon.ico" {
		// ignore.
		return
	}
	log.Println("path", r.URL.Path)
	if r.URL.Path == "/" {
		t, _ := template.ParseFiles("http-content-delivery-demo.gtpl")
		t.Execute(w, nil)
		return
	}
	file := strings.Replace(r.URL.Path, "/", "", 1)
	if _, err := os.Stat(file); os.IsNotExist(err) {
		log.Println(r.URL.Path, "does not exist")
	} else {
		log.Println(file, "exists")
		a, _ := os.Stat(file)
		log.Println("Last-modified-time on the server:", a.ModTime())
		http.ServeFile(w, r, file)
	}
}

func filedownload(w http.ResponseWriter, r *http.Request) {
	log.Println("Method:", r.Method) //get request method
	if r.Method == "GET" {
		handlePath(w, r)

	} else {
		r.ParseForm()

		// logic part of log in
		log.Println("Form: ", r.Form)

		file := r.Form.Get("file")
		log.Println(r.Form.Get("disposition"))
		dis := r.Form.Get("disposition") + "; filename=" + file
		log.Println(dis)
		mime := r.Form.Get("mimetype")
		description := r.Form.Get("contentdescription")
		encoding := r.Form.Get("transferencoding")
		lastmodified := r.Form.Get("lastmodified")

		log.Println("Disposition:", dis)
		log.Println("Mime:", mime)
		log.Println("Description:", description)
		log.Println("Encoding:", encoding)

		w.Header().Set("Content-Disposition", dis)
		w.Header().Set("Content-Description", description)
		w.Header().Set("Content-Transfer-Encoding", encoding)

		if mime != "" {
			w.Header().Set("Content-Type", mime)
		}

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

		b, err := json.MarshalIndent(w.Header(), "", "  ")
		if err != nil {
			log.Println("error:", err)
		}
		log.Print(string(b))
		http.ServeFile(w, r, file)
	}
}

func main() {
	log.Println("Server listening on port 2040.")
	http.HandleFunc("/", filedownload)       // setting router rule
	err := http.ListenAndServe(":2040", nil) // setting listening port
	if err != nil {
		log.Fatal("ListenAndServe: ", err)
	}
}
