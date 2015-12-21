;; Copyright (c) Konrad Grzanek. All rights reserved.
;; Created 2015-12-18

(ns pdp.igraph.i2just
  (:refer-clojure :exclude [get empty? range])
  (:import [jpdp.igraph.util I2Just])
  (:gen-class))


(defn ^I2Just make
  [range]
  (I2Just. range))


(defn ^long range
  {:inline (fn [m] `(.range ~m))}
  [^I2Just m]
  (.range m))


(defn ^long size
  {:inline (fn [m] `(.size ~m))}
  [^I2Just m]
  (.size m))


(defn empty?
  {:inline (fn [m] `(.isEmpty ~m))}
  [^I2Just m]
  (.isEmpty m))


(defn contains-key?
  {:inline (fn [m n] `(.containsKey ~m ~n))}
  [^I2Just m ^long n]
  (.containsKey m n))


(defn contains-val?
  {:inline (fn [m v] `(.containsValue ~m ~v))}
  [^I2Just m ^Object v]
  (.containsValue m v))


(defn get
  {:inline (fn [m n] `(.get ~m ~n))}
  [^I2Just m ^long n]
  (.get m n))


(defn put!
  {:inline (fn [m n v] `(.put ~m ~n ~v))}
  [^I2Just m ^long n ^Object v]
  (.put m n v))


(defn remove!
  {:inline (fn [m n] `(.remove ~m ~n))}
  [^I2Just m ^long n]
  (.remove m n))


(defn clear!
  {:inline (fn [m] `(.clear ~m))}
  [^I2Just m]
  (.clear m))
