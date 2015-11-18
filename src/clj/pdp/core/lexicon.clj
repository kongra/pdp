;; Copyright (c) Konrad Grzanek. All rights reserved.
;; Created 2015-11-16

(in-ns 'pdp.core)

(defn prop
  "Returns an interned property of a given name or nil when not found."
  [^jpdp.core.Ruleset rs name]
  (.getProp (.lexicon rs) name))


(defn assert-prop
  "Works like prop, but raises an error when property was not found."
  [rs name]
  (if-let [p (prop rs name)]
    p
    (terror "Property was not found for name " name)))


(defn value
  "Returns an interned value of a given origin or nil when not found."
  [^jpdp.core.Ruleset rs origin]
  (.getValue (.lexicon rs) origin))


(defn assert-value
  "Works like value, but raises an error when value was not found."
  [rs origin]
  (if-let [v (value rs origin)]
    v
    (terror "Value was not found for origin" origin)))
