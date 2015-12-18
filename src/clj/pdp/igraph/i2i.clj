;; Copyright (c) Konrad Grzanek. All rights reserved.
;; Created 2015-12-18

(ns pdp.igraph.i2i
  (:refer-clojure :exclude [get empty? range])
  (:gen-class))


(defn ^jpdp.igraph.util.I2I make
  [range]
  (jpdp.igraph.util.I2I. range))


(defn range
  [^jpdp.igraph.util.I2I m]
  (.range m))


(defn size
  [^jpdp.igraph.util.I2I m]
  (.size m))


(defn empty?
  [^jpdp.igraph.util.I2I m]
  (.isEmpty m))


(defn contains-key?
  {:inline (fn [m n] `(.containsKey ~m ~n))}
  [^jpdp.igraph.util.I2I m n]
  (.containsKey m n))


(defn contains-val?
  {:inline (fn [m v] `(.containsValue ~m ~v))}
  [^jpdp.igraph.util.I2I m v]
  (.containsValue m v))


(defn get
  {:inline (fn [m n] `(.get ~m ~n))}
  [^jpdp.igraph.util.I2I m n]
  (.get m n))


(defn safe-get
  {:inline (fn [m n deflt] `(.safeGet ~m ~n ~deflt))}
  [^jpdp.igraph.util.I2I m n deflt]
  (.safeGet m n deflt))


(defn put!
  {:inline (fn [m n v] `(.put ~m ~n ~v))}
  [^jpdp.igraph.util.I2I m n v]
  (.put m n v))


(defn remove!
  {:inline (fn [m n] `(.remove ~m ~n))}
  [^jpdp.igraph.util.I2I m n]
  (.remove m n))


(defn clear!
  {:inline (fn [m] `(.clear ~m))}
  [^jpdp.igraph.util.I2I m]
  (.clear m))
