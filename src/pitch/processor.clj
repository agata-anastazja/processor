(ns pitch.processor
  (:require [clojure.string :as s]
            [pitch.digits :as digits]
            [pitch.validation :as validation]))

(defn group-line-by-segments [lines]
  (->>
   lines
   (cons #(digits/chars-digits [%1 %2 %3] \?))
   (apply mapv)))

(defn partition-by-3 [lines] 
  (map #(partition 3 %) lines))

(defn seven-segment-line->account
  [seven-segment-digit]
  (->>
   seven-segment-digit
   partition-by-3
   group-line-by-segments))

(defn parse-account [seven-segment-line]
  (->>
   seven-segment-line
   seven-segment-line->account
   validation/parse-validation
   s/join))

(defn parse [text]
  (->>
   text
   (s/split-lines)
   (partition 3 4)
   (map parse-account)
   (s/join (System/getProperty "line.separator"))))
