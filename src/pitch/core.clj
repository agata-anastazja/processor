(ns pitch.core
  (:gen-class)
  (:require [pitch.processor :as processor]))




(defn -main
  
  [input-file output-file]
  (let [in-text (slurp input-file)
        out-text (processor/parse in-text)]
    (spit output-file out-text)))
