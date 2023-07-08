(ns pitch.validation-test
  (:require [clojure.test :refer :all]
            [pitch.validation :refer :all]))

(deftest valid?-test
  (testing "returns true for a sequence of only 0s"
    (is (= true (valid? [0 0 0 0 0 0 0 0 0]))))
  (testing "returns false for a sequence of only 1s"
    (is (= false (valid? [1 1 1 1 1 1 1 1 1]))))
  (testing "returns true for a sequence of 3  4  5  8  8  2  8  6  5"
    (is (= true (valid? [3  4  5  8  8  2  8  6  5])))))