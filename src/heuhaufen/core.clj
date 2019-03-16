(ns heuhaufen.core
  (:gen-class)
  (:require [heuhaufen.mail :refer :all]
            [heuhaufen.search :refer :all]
            [clucy.core :as clucy])
  )

(defn -main
  [& args]
  "I don't do a whole lot ... yet."

  ;; connect imap, get latest message
  (def latest (get-latest))

  ;; put into search index
  (def index (setup-lucene))
  (clucy/add index latest)

  ;; get it out of the search index
  (println ((first (clucy/search index "test" 10)) :subject))
  )

