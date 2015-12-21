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

(defn range
  {:inline (fn [g] `(.range ~g))}
  [^IGraph g]
  (.range g))


(defn vertices
  {:inline (fn [g] `(.vertices ~g))}
  [^IGraph g]
  (.vertices g))


(defn vertices-count
  {:inline (fn [g] `(.verticesCount ~g))}
  [^IGraph g]
  (.verticesCount g))


(defn empty?
  {:inline (fn [g] `(.isEmpty ~g))}
  [^IGraph g]
  (.isEmpty g))


(defn has-vertex?
  {:inline (fn [g v] `(.hasVertex ~g ~v))}
  [^IGraph g v]
  (.hasVertex g v))


(defn has-edge?
  {:inline (fn [g v1 v2] `(.hasEdge ~g ~v1 ~v2))}
  [^IGraph g v1 v2]
  (.hasEdge g v1 v2))


(defn ^IGraph clone
  [^IGraph g]
  (.cloneme g))


;; MUTATIONS

(defn add-vertex!
  {:inline (fn [g v] `(.addVertex ~g ~v))}
  [^IMutableGraph g v]
  (.addVertex g v))


(defn remove-vertex!
  {:inline (fn [g v] `(.removeVertex ~g ~v))}
  [^IMutableGraph g v]
  (.removeVertex g v))


(defn add-edge!
  {:inline (fn [g v1 v2] `(.addEdge ~g ~v1 ~v2))}
  [^IMutableGraph g v1 v2]
  (.addEdge g v1 v2))


(defn remove-edge!
  {:inline (fn [g v1 v2] `(.removeEdge ~g ~v1 ~v2))}
  [^IMutableGraph g v1 v2]
  (.removeEdge g v1 v2))


;; EDGES

(defn ev1
  {:inline (fn [e] `(.v1 ~e))}
  [^Edge e]
  (.v1 e))


(defn ev2
  {:inline (fn [e] `(.v2 ~e))}
  [^Edge e]
  (.v2 e))
