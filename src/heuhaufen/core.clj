(ns heuhaufen.core
  (:gen-class)
  (:require [heuhaufen.mail :refer :all]
            [heuhaufen.search :refer :all]
            [clucy.core :as clucy]
            [clojure.core.async :as async :refer :all])
  )

(defn -main
  [& args]

  ;; What's a good buffer value here?
  (def c (chan 3))

  ;; connect imap to channel
  ;; TODO: Can we leave out the attachments for now?
  (go (onto-chan c (get-all)))

  ;; put into search index
  (def index (setup-lucene))
  (clucy/add index {:subject "lucene buffer smurf"})
  (go (while true (clucy/add index (<! c))))

  ;; get it out of the search index
  (Thread/sleep 3000)
  (println ((first (clucy/search index "test" 10)) :subject))
  (println ((second (clucy/search index "test" 10)) :subject))
  ; broken (pr-str (clucy/search index "test" 10))
  )

