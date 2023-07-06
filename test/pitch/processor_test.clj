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
