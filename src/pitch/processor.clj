(ns pitch.processor
  (:require [clojure.string :as s]
            [pitch.digits :as digits]
            [pitch.validation :as validation]))



(defn get-seven-segment-digits [seven-segment-line]
  (->> seven-segment-line
       (apply map str)
       (partition 3)
       (map (fn [x] (->>
                     x
                     (apply map str)
                     (s/join (System/getProperty "line.separator")))))))

(defn seven-segment->digit
  [seven-segment-digit]
  (get digits/seven-segment-digits seven-segment-digit))

(defn seven-segment-line->digits
  [line]
  (->>
   line
   get-seven-segment-digits 
   (mapv seven-segment->digit)))

(defn parse-validation [digits-line]
  (if (validation/valid?  digits-line)
    digits-line
    (conj digits-line " ERR")))

(defn parse [text]
  (->>
   text
   (s/split-lines)
   (partition 3 4)
   (map seven-segment-line->digits) 
   (map parse-validation)
   (map s/join)
   (s/join  (System/getProperty "line.separator"))))


