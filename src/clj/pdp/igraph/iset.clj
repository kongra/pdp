;; Copyright (c) Konrad Grzanek. All rights reserved.
;; Created 2015-12-18

(ns pdp.igraph.iset
  (:refer-clojure :exclude [contains? empty? range])
  (:gen-class))


(defn ^jpdp.igraph.util.ISet make
  [range]
  (jpdp.igraph.util.ISet. range))


(defn range
  [^jpdp.igraph.util.ISet s]
  (.range s))


(defn size
  [^jpdp.igraph.util.ISet s]
  (.size s))


(defn empty?
  [^jpdp.igraph.util.ISet s]
  (.isEmpty s))


(defn contains?
  {:inline (fn [s n] `(.contains ~s ~n))}
  [^jpdp.igraph.util.ISet s n]
  (.contains s n))


(defn add!
  {:inline (fn [s n] `(.add ~s ~n))}
  [^jpdp.igraph.util.ISet s n]
  (.add s n))


(defn remove!
  {:inline (fn [s n] `(.remove ~s ~n))}
  [^jpdp.igraph.util.ISet s n]
  (.remove s n))


(defn clear!
  {:inline (fn [s] `(.clear s))}
  [^jpdp.igraph.util.ISet s]
  (.clear s))
