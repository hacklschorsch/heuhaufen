(ns heuhaufen.search
  (:require [clucy.core :as clucy]))


(defn setup-lucene
  []
  (clucy/memory-index)
  )

