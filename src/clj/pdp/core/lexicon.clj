;; Copyright (c) Konrad Grzanek. All rights reserved.
;; Created 2015-11-16

(in-ns 'pdp.core)

(deftype Prop [name code]
  Object
  (toString [this] name))


(deftype Value [origin]
  Object
  (toString [this] origin))


(defmacro defprops
  [& names]
  (when-not (= names (distinct names))
    (terror "Prop names must be distinct."))

  (let [inames  (map pair names (iterate inc 0))
        clause  (fn [[name code]] `(defl ~name (Prop. ~(str name) ~code)))
        clauses (map clause inames)]
    `(do ~@clauses)))


(defmacro defvals
  [& origins]
  (when-not (= origins (distinct origins))
    (terror "Origins of Values must be distinct."))

  `(do ~@(map (fn [origin] `(defl ~origin (Value. ~(str origin)))) origins)))


;; (defprops Engine Gearbox)
;; (defvals  Diesel Benz Automat Manual)
