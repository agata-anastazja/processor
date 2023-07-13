(ns pitch.processor
  (:require [clojure.string :as s]
            [pitch.digits :as digits]
            [pitch.validation :as validation]))

(defn chars->digit [lines]
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
   chars->digit))

(defn render-account[account]
  (let [{digits-line :digits-line
         err   :err} account
        parsed-digit-line (s/join digits-line)]
    (if (nil? err)
      parsed-digit-line
      (str parsed-digit-line " " err))))

(defn parse-account [seven-segment-line]
  (->>
   seven-segment-line
   seven-segment-line->account
   validation/validate-account
   render-account
   s/join))

(defn parse-account-numbers [text]
  (->>
   text
   (s/split-lines)
   (partition 3 4)
   (map parse-account)
   (s/join (System/getProperty "line.separator"))))
