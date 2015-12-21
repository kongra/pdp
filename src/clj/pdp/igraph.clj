;; Copyright (c) Konrad Grzanek. All rights reserved.
;; Created 2015-12-11

(ns pdp.igraph
  (:refer-clojure :exclude [range empty?])
  (:use    [clongra.core])
  (:import [jpdp.igraph IGraph IMutableGraph Edge])
  (:gen-class))

;; FAST MUTABLE UNWEIGHTED GRAPHS WITH INTEGRAL VERTICES IN RANGE
;; 0 .. (range-1)

;; COMMON NON-DESTRUCTIVE OPERATORS

(defn ^long range
  {:inline (fn [g] `(.range ~g))}
  [^IGraph g]
  (.range g))


(defn ^longs vertices
  {:inline (fn [g] `(.vertices ~g))}
  [^IGraph g]
  (.vertices g))


(defn ^long vertices-count
  {:inline (fn [g] `(.verticesCount ~g))}
  [^IGraph g]
  (.verticesCount g))


(defn empty?
  {:inline (fn [g] `(.isEmpty ~g))}
  [^IGraph g]
  (.isEmpty g))


(defn has-vertex?
  {:inline (fn [g v] `(.hasVertex ~g ~v))}
  [^IGraph g ^long v]
  (.hasVertex g v))


(defn has-edge?
  {:inline (fn [g v1 v2] `(.hasEdge ~g ~v1 ~v2))}
  [^IGraph g ^long v1 ^long v2]
  (.hasEdge g v1 v2))


(defn ^IGraph clone
  [^IGraph g]
  (.cloneme g))


;; MUTATIONS

(defn add-vertex!
  {:inline (fn [g v] `(.addVertex ~g ~v))}
  [^IMutableGraph g ^long v]
  (.addVertex g v))


(defn remove-vertex!
  {:inline (fn [g v] `(.removeVertex ~g ~v))}
  [^IMutableGraph g ^long v]
  (.removeVertex g v))


(defn add-edge!
  {:inline (fn [g v1 v2] `(.addEdge ~g ~v1 ~v2))}
  [^IMutableGraph g ^long v1 ^long v2]
  (.addEdge g v1 v2))


(defn remove-edge!
  {:inline (fn [g v1 v2] `(.removeEdge ~g ~v1 ~v2))}
  [^IMutableGraph g ^long v1 ^long v2]
  (.removeEdge g v1 v2))


;; EDGES

(defn ^long ev1
  {:inline (fn [e] `(.v1 ~e))}
  [^Edge e]
  (.v1 e))


(defn ^long ev2
  {:inline (fn [e] `(.v2 ~e))}
  [^Edge e]
  (.v2 e))


;; VERTICES

(defn ^longs vs
  "A simple constructor of vertices array."
  [& coll]
  (long-array coll))
