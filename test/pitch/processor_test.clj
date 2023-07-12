(ns pitch.processor-test
  (:require [clojure.test :refer :all]
            [pitch.processor :refer :all]))

(deftest seven-segment-line->account-test
  (testing "parses one line of valid input"
    (let [seven-segment-line
          [" _     _  _     _  _  _  _  _ "
           "| |  | _| _||_||_ |_   ||_||_|"
           "|_|  ||_  _|  | _||_|  ||_| _|"]
          result (seven-segment-line->account seven-segment-line)]
      (is (= `(0 1 2 3 4 5 6 7 8 9) result))))
  (testing "parses one line of input with a corrupted representation of 9"
    (let [seven-segment-line
          [" _     _  _     _  _  _  _  _ "
           "| |  | _| _||_||_ |_   ||_||_|"
           "|_|  ||_  _|  | _||_|  ||_| _ "]
          result (seven-segment-line->account seven-segment-line)]
      (is (= `(0 1 2 3 4 5 6 7 8 \?) result)))))



(deftest parse-account-numbers-test
  (testing "parse a valid input file"
    (let [input (slurp "test/pitch/resources/valid_input.txt")
          result (parse-account-numbers input)
          expected-result "000000000"]
      (is (= expected-result result))))
  (testing "parse file with checksum validation failing"
    (let [input (slurp "test/pitch/resources/checksum_validation_failing_input.txt")
          result (parse-account-numbers input)
          expected-result
          "111111111 ERR
222222222 ERR
222222222 ERR"]
      (is (= expected-result result))))
  (testing "parse file wit checksum and digit validation failing"
    (let [input (slurp "test/pitch/resources/corrupted_line_input.txt")
          result (parse-account-numbers input)
          expected-result "00000?00? ILL\n111111111 ERR"]
      (is (= expected-result result)))))