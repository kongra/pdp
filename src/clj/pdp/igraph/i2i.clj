;; Copyright (c) Konrad Grzanek. All rights reserved.
;; Created 2015-12-18

(ns pdp.igraph.i2i
  (:refer-clojure :exclude [get empty? range])
  (:import [jpdp.igraph.util I2I])
  (:gen-class))


(defn ^I2I make
  [range noval]
  (I2I. range noval))


(defn range
  [^I2I m]
  (.range m))


(defn size
  [^I2I m]
  (.size m))


(defn empty?
  [^I2I m]
  (.isEmpty m))


(defn contains-key?
  {:inline (fn [m n] `(.containsKey ~m ~n))}
  [^I2I m n]
  (.containsKey m n))


(defn contains-val?
  {:inline (fn [m v] `(.containsValue ~m ~v))}
  [^I2I m v]
  (.containsValue m v))


(defn get
  {:inline (fn [m n] `(.get ~m ~n))}
  [^I2I m n]
  (.get m n))


(defn put!
  {:inline (fn [m n v] `(.put ~m ~n ~v))}
  [^I2I m n v]
  (.put m n v))


(defn remove!
  {:inline (fn [m n] `(.remove ~m ~n))}
  [^I2I m n]
  (.remove m n))


(defn clear!
  {:inline (fn [m] `(.clear ~m))}
  [^I2I m]
  (.clear m))
