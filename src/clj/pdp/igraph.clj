;; Copyright (c) Konrad Grzanek. All rights reserved.
;; Created 2015-12-11

(ns pdp.igraph
  (:refer-clojure :exclude [range empty?])
  (:use [clongra.core])
  (:gen-class))

;; FAST MUTABLE UNWEIGHTED GRAPHS WITH INTEGRAL VERTICES IN RANGE
;; 0 .. (range-1)

;; CONSTRUCTORS

(defn ^jpdp.igraph.Unigraph unigraph
  [range]
  (jpdp.igraph.Unigraph. range))


(defn ^jpdp.igraph.Digraph digraph
  [range]
  (jpdp.igraph.Digraph. range))


;; COMMON READS

(defn range
  [^jpdp.igraph.IGraph g]
  (.range g))


(defn vertices-count
  [^jpdp.igraph.IGraph g]
  (.verticesCount g))


(defn empty?
  [^jpdp.igraph.IGraph g]
  (.isEmpty g))


(defn vertices
  {:inline (fn [g] `(.vertices ~g))}
  [^jpdp.igraph.IGraph g]
  (.vertices g))


(defn has-vertex?
  {:inline (fn [g v] `(.hasVertex ~g ~v))}
  [^jpdp.igraph.IGraph g v]
  (.hasVertex g v))


(defn has-edge?
  {:inline (fn [g v1 v2] `(.hasEdge ~g ~v1 ~v2))}
  [^jpdp.igraph.IGraph g v1 v2]
  (.hasEdge g v1 v2))


(defn successors
  {:inline (fn [g v] `(.successors ~g ~v))}
  [^jpdp.igraph.IGraph g v]
  (.successors g v))


(defn ^jpdp.igraph.IGraph clone
  [^jpdp.igraph.IGraph g]
  (.cloneme g))


;; DIGRAPH

(defn predecessors
  {:inline (fn [g v] `(.predecessors ~g ~v))}
  [^jpdp.igraph.IDigraph g v]
  (.predecessors g v))


;; MUTATIONS

(defn add-vertex!
  {:inline (fn [g v] `(.addVertex ~g ~v))}
  [^jpdp.igraph.IMutableGraph g v]
  (.addVertex g v))


(defn remove-vertex!
  {:inline (fn [g v] `(.removeVertex ~g ~v))}
  [^jpdp.igraph.IMutableGraph g v]
  (.removeVertex g v))


(defn add-edge!
  {:inline (fn [g v1 v2] `(.addEdge ~g ~v1 ~v2))}
  [^jpdp.igraph.IMutableGraph g v1 v2]
  (.addEdge g v1 v2))


(defn remove-edge!
  {:inline (fn [g v1 v2] `(.removeEdge ~g ~v1 ~v2))}
  [^jpdp.igraph.IMutableGraph g v1 v2]
  (.removeEdge g v1 v2))


;; EDGES

(defn ev1
  {:inline (fn [e] `(.v1 ~e))}
  [^jpdp.igraph.Edge e]
  (.v1 e))


(defn ev2
  {:inline (fn [e] `(.v2 ~e))}
  [^jpdp.igraph.Edge e]
  (.v2 e))


(defn successor-edges
  {:inline (fn [g v] `(.successorEdges ~g ~v))}
  [^jpdp.igraph.IGraph g v]
  (.successorEdges g v))


(defn predecessor-edges
  {:inline (fn [g v] `(.predecessorEdges ~g ~v))}
  [^jpdp.igraph.IDigraph g v]
  (.predecessorEdges g v))


;; DUMP/PRESENTATION

(defn adjacent
  [^jpdp.igraph.IGraph g]
  (fn [v] (seq (successors g v))))


(defn dump
  ([g v show depth]
   (print-graph v (adjacent g) show depth))

  ([g v show]
   (print-graph v (adjacent g) show))

  ([g v]
   (print-graph v (adjacent g))))


;; TESTS

;; (defn fc-unigraph
;;   "Creates and returns a fully connected unigraph of range n."
;;   [n]
;;   (let [ug (unigraph n)]
;;     (dotimes [v1 n]
;;       (dotimes [v2 n]
;;         (add-edge! ug v1 v2)))
;;     ug))

;; (def ^jpdp.igraph.IGraph g (fc-unigraph 100))

;; (dotimes [i 20] (add-vertex! ug i))
;; (def mapka (apply hash-map (take 200 (N))))
;; (def secik (apply hash-set (take 0 (N))))
