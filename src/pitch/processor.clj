(ns pitch.processor
  (:gen-class)
  (:require [clojure.string :as s]
            [pitch.digits :as digits]))



(defn get-digits [seven-segment-line]
  (->> seven-segment-line
       (s/split-lines)
       (apply map str)
       (partition 3)
       (map (fn [x] (->>
                     x
                     (apply map str)
                     (s/join (System/getProperty "line.separator")))))))

(defn seven-segment->digit
  "Callable entry point to the application."
  [seven-segment-digit]
  (get digits/seven-segment-digits seven-segment-digit))

(defn seven-segment-line->digits
  [line]
  (->>
  line
  get-digits
  (map seven-segment->digit) ))

(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  )

(comment



  (def all-digits

" _     _  _     _  _  _  _  _ 
| |  | _| _||_||_ |_   ||_||_|
|_|  ||_  _|  | _||_|  ||_| _|")
  (seven-segment-line->digits all-digits)
  (defn unzip [input]
    (for [iter (iterate (partial map rest) input)
          :while (every? seq iter)]
      (map first iter)))
  
  (def digits
    " _     _  _     _  _  _  _  _
  | |  | _| _||_||_ |_   ||_||_|
  |_|  ||_  _|  | _||_|  ||_| _|") 
  (->> digits
       clojure.string/split-lines
       (apply map (fn [x y z] (map str [x y z])))
       (take 3)
       unzip
       (map (fn [x] (apply str x)))
       (clojure.string/join (System/getProperty "line.separator"))) 

  (->> all-digits
       (s/split-lines)
       (apply map str)
       (partition 3)
       (map (fn [x] (->>
                     x
                     (apply map str)
                     (s/join (System/getProperty "line.separator"))))))
       
  
  (->> all-digits
       (s/split-lines)
       (apply map str)
       (partition 3)
       (map (fn [x] (apply map str x)))
       (map (fn [x] (s/join (System/getProperty "line.separator") x))))
  (->> all-digits
       (s/split-lines)
       (apply map str)
       ) 
(def zero1
" _ 
| |
|_|")
  (def zero2  
" _ \n| |\n|_|")
  (= zero1 zero2)
  (format zero2)
  (System/getProperty "line.separator")
  )