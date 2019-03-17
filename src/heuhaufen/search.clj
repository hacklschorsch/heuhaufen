(ns heuhaufen.search
  (:require [clucy.core :as clucy]))


(defn setup-lucene
  []
  (clucy/memory-index)
  ;(clucy/disk-index "/home/fs-priv/git/heuhaufen/resources/fs-lucene-4.idx")
  )

