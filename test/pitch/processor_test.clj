(ns pitch.processor-test
  (:require [clojure.test :refer :all]
            [pitch.processor :refer :all]))

(deftest partition-by-3-test
  (testing "partitions a line of input with one digit by length of one digit"
    (let [seven-segment-line 
          [" _ "
           "| |"
           "|_|"]
          result (partition-by-3 seven-segment-line)
          expected-result (list (list (seq " _ ")) (list (seq "| |")) (list (seq "|_|")))]
      (is (= expected-result result))))
  (testing "partitions a line of input with two digits by length of one digit"
    (let [seven-segment-line
          [" _    "
           "| |  |"
           "|_|  |"]
          result (partition-by-3 seven-segment-line)
          expected-result (list (list (seq " _ ") (seq "   ")) (list (seq "| |") (seq "  |")) (list (seq "|_|") (seq "  |")))]
      (is (= expected-result result)))))


(deftest chars->digit-test
  (testing "rearrange chars so that we can compose them into respective digits and retrieve them from lookup"
    (let [list-of-list-of-numbers (list (list (seq " _ ") (seq "   ")) (list (seq "| |") (seq "  |")) (list (seq "|_|") (seq "  |")))
          expected-result [0 1]
          result (chars->digit list-of-list-of-numbers)]
      (is (= expected-result result))))
   (testing "if the digit is corrupted return char ?"
    (let [list-of-list-of-numbers (list (list (seq "   ") (seq "   ")) (list (seq "| |") (seq "  |")) (list (seq "|_|") (seq "  |")))
          expected-result [\? 1]
          result (chars->digit list-of-list-of-numbers)]
      (is (= expected-result result)))))

(deftest render-account-test
  (testing "takes an account number and it's error message and parses into expected string"
    (let [account {:digits-line [1 1 1 1 1 1 1 1 \?] :err "ILL"}
          expected-result "11111111? ILL"
          result (render-account account)]
      (is (= expected-result result))))
  (testing "takes an account number without an error message and parses into expected string"
    (let [account {:digits-line [0 0 0 0 0 0 0 0 0]}
          expected-result "000000000"
          result (render-account account)]
      (is (= expected-result result)))))

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
          "111111111 ERR\n222222222 ERR\n222222222 ERR"]
      (is (= expected-result result))))
  (testing "parse file wit checksum and digit validation failing"
    (let [input (slurp "test/pitch/resources/corrupted_line_input.txt")
          result (parse-account-numbers input)
          expected-result "00000?00? ILL\n111111111 ERR"]
      (is (= expected-result result)))))