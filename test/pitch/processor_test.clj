(ns pitch.processor-test
  (:require [clojure.test :refer :all]
            [pitch.processor :refer :all]))

(deftest seven-segment-line->digits-test
  (testing "parses one line of input"
    (let [seven-segment-line
          [" _     _  _     _  _  _  _  _ "
           "| |  | _| _||_||_ |_   ||_||_|"
           "|_|  ||_  _|  | _||_|  ||_| _|"]
          result (seven-segment-line->digits seven-segment-line)]
      (is (= `(0 1 2 3 4 5 6 7 8 9) result))))
  (testing "parses one line of input"
    (let [seven-segment-line
          [" _     _  _     _  _  _  _  _ "
           "| |  | _| _||_||_ |_   ||_||_|"
           "|_|  ||_  _|  | _||_|  ||_| _ "]
          result (seven-segment-line->digits seven-segment-line)]
      (is (= `(0 1 2 3 4 5 6 7 8 \?) result)))))

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
      (is (=  expected-result result))))
  (testing "add ILL to a line with badly parsed numbers"
    (let [line [0 "?" 0 0 0 0 0 0 0]
          result (parse-validation line)
          expected-result [0 "?" 0 0 0 0 0 0 0 " ILL"]]
      (is (= expected-result result)))))

(deftest parse-test
  (testing "parse a valid input file"
    (let [input (slurp "test/pitch/resources/sample_1.txt")
          result (parse input)
          expected-result "000000000"]
      (is (= expected-result result))))
  (testing "parse file with checksum validation failing"
    (let [input (slurp "test/pitch/resources/sample_2.txt")
          result (parse input)
          expected-result
          "111111111 ERR
222222222 ERR
222222222 ERR"]
      (is (= expected-result result))))
  (testing "parse file wit checksum and digit validation failing"
    (let [input (slurp "test/pitch/resources/sample_3.txt")
          result (parse input)
          expected-result "00000?00? ILL\n111111111 ERR"]
      (is (= expected-result result)))))