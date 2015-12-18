;; Copyright (c) Konrad Grzanek. All rights reserved.
;; Created 2015-12-18

(ns pdp.igraph.i2just
  (:refer-clojure :exclude [get empty? range])
  (:gen-class))


(defn ^jpdp.igraph.util.I2Just make
  [range]
  (jpdp.igraph.util.I2Just. range))


(defn range
  [^jpdp.igraph.util.I2Just m]
  (.range m))


(defn size
  [^jpdp.igraph.util.I2Just m]
  (.size m))


(defn empty?
  [^jpdp.igraph.util.I2Just m]
  (.isEmpty m))


(defn contains-key?
  {:inline (fn [m n] `(.containsKey ~m ~n))}
  [^jpdp.igraph.util.I2Just m n]
  (.containsKey m n))


(defn contains-val?
  {:inline (fn [m v] `(.containsValue ~m ~v))}
  [^jpdp.igraph.util.I2Just m v]
  (.containsValue m v))


(defn get
  {:inline (fn [m n] `(.get ~m ~n))}
  [^jpdp.igraph.util.I2Just m n]
  (.get m n))


(defn put!
  {:inline (fn [m n v] `(.put ~m ~n ~v))}
  [^jpdp.igraph.util.I2Just m n v]
  (.put m n v))


(defn remove!
  {:inline (fn [m n] `(.remove ~m ~n))}
  [^jpdp.igraph.util.I2Just m n]
  (.remove m n))


(defn clear!
  {:inline (fn [m] `(.clear ~m))}
  [^jpdp.igraph.util.I2Just m]
  (.clear m))
