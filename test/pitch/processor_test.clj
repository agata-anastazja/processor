(ns pitch.processor-test
  (:require [clojure.test :refer :all]
            [pitch.processor :refer :all]))

(deftest seven-segment->digit-test
  (testing "translates a 0 seven-segment digit to a digit"
    (let [seven-segment-zero
" _  
| |
|_|"
          result (seven-segment->digit seven-segment-zero)]
    (is (= 0 result))))
  (testing "translates a 1 seven-segment digit to a digit"
    (let [seven-segment-one
"   
  |
  |"
          result (seven-segment->digit seven-segment-one)]
      (is (= 1 result)))))

(deftest seven-segment-line->digits-test
  (testing "parses one line of input"
    (let [seven-segment-line 
" _     _  _     _  _  _  _  _ 
| |  | _| _||_||_ |_   ||_||_|
|_|  ||_  _|  | _||_|  ||_| _|" 
          result (seven-segment-line->digits seven-segment-line)]
      (is (= result [0 1 2 3 4 5 6 7 8 9])))))

(deftest parse-test
  (testing "parse file"
    (let [input (slurp "test/pitch/resources/sample_2.txt")
          result (parse input)
          expected-result
          "111111111
222222222
222222222"]
      (is (=  expected-result result)))))