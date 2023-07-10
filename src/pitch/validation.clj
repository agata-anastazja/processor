(ns pitch.validation)


(defn invalid-checksum? [input]
  (let [input-len (count input)
        sum (reduce-kv (fn [acc idx digit]
                         (+ acc (* (- input-len idx) digit)))
                       0  (vec input))]
    (not= 0 (mod sum 11))))

(defn not-only-digits? [input]
  (some? (some (fn [x] (not (number? x))) input)))