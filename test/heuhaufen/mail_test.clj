(ns heuhaufen.mail-test
  (:require [clojure.test :refer :all]
            [heuhaufen.core :refer :all]
            [clojure-mail.core :refer :all]
            [clojure-mail.message :refer (read-message)]))


(defn connect-imap
  [f]
  ;; flo the processor ^^ test account with autogenerated PW.
  (def mail-store (store "mail.your-server.de" "flo-der-pro@sesser.at" "IWqD0SdV85Owo8t9"))
  (def inbox-messages (inbox mail-store))
  (f)
  )

(use-fixtures :once connect-imap)

(deftest latest-message-test
  (def latest (read-message (first inbox-messages)))

  ;;(:subject latest)
  ;;(print (keys latest))
  ;;(print (:headers latest))
  ;;(print (:body latest))

  (testing "Most recent message has headers"
    (is (not (empty? (:headers latest)))))
  (testing "Most recent message has body"
    (is (not (empty? (:body latest)))))
  ;;(testing "Has most every tin"
  ;;  (is (every? seq [(:headers latest) (:body latest) ""])))
  )

