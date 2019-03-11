(ns heuhaufen.core-test
  (:require [clojure.test :refer :all]
            [heuhaufen.core :refer :all]
            [clucy.core :as clucy]))

(defn setup-clojure
  [f]
  (def index (clucy/memory-index))
  (clucy/add index
       {:name "Bob", :job "Builder"}
       {:name "Donald", :job "Computer Scientist"})
  (f)
  )

(use-fixtures :each setup-clojure)

(deftest a-test
  (testing "Storing and retrieving things with Lucene"
    (is (= (clucy/search index "bob" 10) '({:name "Bob", :job "Builder"})))))
