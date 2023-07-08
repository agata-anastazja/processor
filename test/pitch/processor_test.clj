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
[" _     _  _     _  _  _  _  _ "
"| |  | _| _||_||_ |_   ||_||_|"
"|_|  ||_  _|  | _||_|  ||_| _|"] 
          result (seven-segment-line->digits seven-segment-line)]
      (is (= [0 1 2 3 4 5 6 7 8 9] result)))))

(deftest parse-validation-test 
  (testing "add ERR to invalid checksum line"
    (let [line [0 0 0 0 0 0 0 0 1 ]
          result (parse-validation line)
          expected-result [0 0 0 0 0 0 0 0 1 " ERR" ]]
      (is (=  expected-result result))))
  (testing "don't change a valid checksum"
    (let [line [0 0 0 0 0 0 0 0 0]
          result (parse-validation line)
          expected-result [0 0 0 0 0 0 0 0 0]]
      (is (=  expected-result result)))))

(deftest parse-test
  (testing "parse file"
    (let [input (slurp "test/pitch/resources/sample_2.txt")
          result (parse input)
          expected-result
          "111111111 ERR
222222222 ERR
222222222 ERR"]
      (is (=  expected-result result)))))