(ns heuhaufen.core
  (:gen-class)
  (:require [heuhaufen.mail :refer :all]
            [heuhaufen.search :refer :all]
            [clucy.core :as clucy]
            [clojure.core.async :as async :refer :all])
  )

(defn -main
  [& args]
  "I don't do a whole lot ... yet."
  (def c (chan 3))

  ;; connect imap, get latest message
  (>!! c (get-latest))

  ;; put into search index
  (def index (setup-lucene))
  (clucy/add index (<!! c))

  ;; get it out of the search index
  (println ((first (clucy/search index "test" 10)) :subject))
  )

