(ns pitch.validation)


(defn invalid-checksum? [input]
  (let [input-len (count input)
        sum (reduce-kv (fn [acc idx digit]
                         (+ acc (* (- input-len idx) digit)))
                       0 input)]
    (not= 0 (mod sum 11))))

(defn not-only-digits? [input]
  (some? (some (fn [x] (not (number? x))) input)))

(defn parse-validation [digits-line]
  (cond
    (not-only-digits? digits-line) (conj digits-line " ILL")
    (invalid-checksum?  digits-line) (conj digits-line " ERR")
    :else digits-line))
