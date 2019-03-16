(ns heuhaufen.core
  (:gen-class)
  (:require [heuhaufen.mail :refer :all]
            [clojure-mail.message :refer (read-message)])
  ;(:require [heuhaufen.search :refer :all]
  )

(defn -main
  "I don't do a whole lot ... yet."
  ;; connect imap, get latest message
  ;; put into search index
  ;; get it out of the search index
  [& args]
  (println "Hello, World!")
  (println (:subject (get-latest)))
  )

