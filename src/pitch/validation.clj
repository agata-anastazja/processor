(ns pitch.validation)


(defn valid? [input]
  (let [input-len (count input)
        sum (reduce-kv (fn [acc idx digit]
                         (+ acc (* (- input-len idx) digit)))
                       0  input)]
    (= 0 (mod sum 11))))
