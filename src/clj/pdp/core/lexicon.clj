;; Copyright (c) Konrad Grzanek. All rights reserved.
;; Created 2015-11-18

(in-ns 'pdp.core)

(defmacro defprops
  [& names]
  (when-not (= names (distinct names))
    (terror "Prop names must be distinct."))

  (let [inames  (map pair names (iterate inc 0))
        clause  (fn [[name code]]
                  `(defl ~name (jpdp.core.Prop. ~(str name) ~(int code))))
        clauses (map clause inames)]
    `(do ~@clauses)))


(defmacro defvals
  [& origins]
  (when-not (= origins (distinct origins))
    (terror "Origins of Values must be distinct."))

  `(do ~@(map (fn [origin]
                `(defl ~origin (jpdp.core.Value. ~(str origin))))
              origins)))
