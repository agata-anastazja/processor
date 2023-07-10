(ns pitch.validation)


(defn invalid-checksum? [input]
  (let [input-len (count input)
        sum (reduce-kv (fn [acc idx digit]
                         (+ acc (* (- input-len idx) digit)))
                       0  input)]
    (not= 0 (mod sum 11))))

(defn elem-not-in-the-list [expected-elems elem]
  (empty? (filter expected-elems elem)))

(defn unexpected-char? [input]
 (reduce (fn[acc char]
           (if (elem-not-in-the-list #{0 1 2 3 4 5 6 7 8 9} #{char})
             (reduced true)
             false)) false input))