(ns heuhaufen.core
  (:gen-class)
  (:require [heuhaufen.mail :refer [get-all]]
            [heuhaufen.search :refer [setup-lucene]]
            [clucy.core :as clucy]
            [clojure.core.async :as async :refer [chan onto-chan thread <!!]])
  )


(defn -main
  [& args]

  ;; What's a good buffer value here?
  (def c (chan 300))

  ;; connect imap to channel
  ;; TODO: Can we leave out the attachments for now?
  (thread (onto-chan c (get-all)))

  ;; put into search index
  (def index (setup-lucene))
  (clucy/add index {:subject "lucene buffer smurf"})
  (thread (while true (try (clucy/add index (<!! c)))))

  ;; get it out of the search index
  (Thread/sleep 14400000)
  ;(println ((first (clucy/search index "test" 10)) :subject))
  ; broken (pr-str (clucy/search index "test" 10))
  )

