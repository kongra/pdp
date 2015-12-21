;; Copyright (c) Konrad Grzanek. All rights reserved.
;; Created 2015-12-18

(ns pdp.igraph.iset
  (:refer-clojure :exclude [contains? empty? range])
  (:import [jpdp.igraph.util ISet])
  (:gen-class))


(defn ^ISet make
  [range]
  (ISet. range))


(defn range
  {:inline (fn [s] `(.range ~s))}
  [^ISet s]
  (.range s))


(defn size
  {:inline (fn [s] `(.size ~s))}
  [^ISet s]
  (.size s))


(defn empty?
  {:inline (fn [s] `(.isEmpty ~s))}
  [^ISet s]
  (.isEmpty s))


(defn contains?
  {:inline (fn [s n] `(.contains ~s ~n))}
  [^ISet s n]
  (.contains s n))


(defn add!
  {:inline (fn [s n] `(.add ~s ~n))}
  [^ISet s n]
  (.add s n))


(defn remove!
  {:inline (fn [s n] `(.remove ~s ~n))}
  [^ISet s n]
  (.remove s n))


(defn clear!
  {:inline (fn [s] `(.clear s))}
  [^ISet s]
  (.clear s))
