(ns pitch.processor
  (:require [clojure.string :as s]
            [pitch.digits :as digits]
            [pitch.validation :as validation]))




(defn divide-lines-by-3-chars [lines]
  (map #(partition 3 %) lines))

(defn collect-chars-for-digit [three-levels-nested-lines]
  (cons (fn [first-line second-line third-line] [digits/seven-segment-digits first-line second-line third-line]) three-levels-nested-lines))

(defn get-seven-segment-digits [seven-segment-line]
  (->> seven-segment-line
       divide-lines-by-3-chars
       collect-chars-for-digit))

(defn seven-segment->digit
  [seven-segment-digit]
  (digits/seven-segment-digits seven-segment-digit "?"))

(defn seven-segment-line->digits
  [line]
  (->>
   line
   get-seven-segment-digits 
   (mapv seven-segment->digit)))

(defn parse-validation [digits-line]
  (cond
    (validation/not-only-digits? digits-line) (conj digits-line " ILL")
    (validation/invalid-checksum?  digits-line) (conj digits-line " ERR") 
    :else digits-line))

(defn parse [text]
  (->>
   text
   (s/split-lines)
   (partition 3 4)
   (map seven-segment-line->digits) 
   (map parse-validation)
   (map s/join)
   (s/join  (System/getProperty "line.separator"))))


(comment 
  
  (->> (slurp "test/pitch/resources/sample_1.txt")
       (s/split-lines)
       (partition 3 4)
       (map (fn [line-of-digits]
              (->>
               line-of-digits
               (map #(->>  %
                           seq
                           ( (fn [x] (partition 3 x)))))
               (cons #(digits/chars-digits [%1 %2 %3] \?))
               (apply map)
               )))
       
       
       ))


