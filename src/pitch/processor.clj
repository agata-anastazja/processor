(ns pitch.processor
  (:require [clojure.string :as s]
            [pitch.digits :as digits]
            [pitch.validation :as validation]))
(defn seven-segment-line->account
  [seven-segment-line]
  (->>
   seven-segment-line
   (map #(->>
          %
          seq
          ((fn [x] (partition 3 x)))))
   (cons #(digits/chars-digits [%1 %2 %3] \?))
   (apply mapv)))

(defn parse-validation [digits-line]
  (cond
    (validation/not-only-digits? digits-line) (conj digits-line " ILL")
    (validation/invalid-checksum?  digits-line) (conj digits-line " ERR") 
    :else digits-line))

(defn parse-account [seven-segment-line]
  (->>
   seven-segment-line
   seven-segment-line->account
   parse-validation
   s/join))

(defn parse [text]
  (->>
   text
   (s/split-lines)
   (partition 3 4)
   (map parse-account)
   (s/join (System/getProperty "line.separator"))))
