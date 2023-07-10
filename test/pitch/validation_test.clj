(ns pitch.validation-test
  (:require [clojure.test :refer :all]
            [pitch.validation :refer :all]))

(deftest valid?-test
  (testing "returns false for a sequence of only 0s"
    (is (= false (invalid-checksum? [0 0 0 0 0 0 0 0 0]))))
  (testing "returns true for a sequence of only 1s"
    (is (= true (invalid-checksum? [1 1 1 1 1 1 1 1 1]))))
  (testing "returns false for a sequence of 3  4  5  8  8  2  8  6  5"
    (is (= false (invalid-checksum? [3  4  5  8  8  2  8  6  5])))))

(deftest not-only-digits-test
  (testing "returns true if there is a question mark in the vector"
    (is (not-only-digits? [1 2 "?"])))
  (testing "returns false if there is a vector only with digits"
    (is (= false (not-only-digits? [1 2])))))

(deftest parse-validation-test
  (testing "add ERR to invalid checksum line"
    (let [line [0 0 0 0 0 0 0 0 1]
          result (parse-validation line)
          expected-result [0 0 0 0 0 0 0 0 1 " ERR"]]
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