;; Copyright (c) Konrad Grzanek. All rights reserved.
;; Created 2015-12-11

(ns pdp.igraph
  (:refer-clojure :exclude [range empty?])
  (:use    [clongra.core])
  (:import [jpdp.igraph IGraph   IDigraph IMutableGraph
                        Unigraph Digraph  Edge])
  (:gen-class))

;; FAST MUTABLE UNWEIGHTED GRAPHS WITH INTEGRAL VERTICES IN RANGE
;; 0 .. (range-1)

;; CONSTRUCTORS

(defn ^Unigraph unigraph
  [range]
  (Unigraph. range))


(defn ^Digraph digraph
  [range]
  (Digraph. range))


;; COMMON READS

(defn range
  [^IGraph g]
  (.range g))


(defn vertices-count
  [^IGraph g]
  (.verticesCount g))


(defn empty?
  [^IGraph g]
  (.isEmpty g))


(defn vertices
  {:inline (fn [g] `(.vertices ~g))}
  [^IGraph g]
  (.vertices g))


(defn has-vertex?
  {:inline (fn [g v] `(.hasVertex ~g ~v))}
  [^IGraph g v]
  (.hasVertex g v))


(defn has-edge?
  {:inline (fn [g v1 v2] `(.hasEdge ~g ~v1 ~v2))}
  [^IGraph g v1 v2]
  (.hasEdge g v1 v2))


(defn successors
  {:inline (fn [g v] `(.successors ~g ~v))}
  [^IGraph g v]
  (.successors g v))


(defn ^IGraph clone
  [^IGraph g]
  (.cloneme g))


;; DIGRAPH

(defn predecessors
  {:inline (fn [g v] `(.predecessors ~g ~v))}
  [^IDigraph g v]
  (.predecessors g v))


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


(defn successor-edges
  {:inline (fn [g v] `(.successorEdges ~g ~v))}
  [^IGraph g v]
  (.successorEdges g v))


(defn predecessor-edges
  {:inline (fn [g v] `(.predecessorEdges ~g ~v))}
  [^IDigraph g v]
  (.predecessorEdges g v))


;; DUMP/PRESENTATION

(defn adjacent
  [^IGraph g]
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

;; (def ^IGraph g (fc-unigraph 100))

;; (dotimes [i 20] (add-vertex! ug i))
;; (def mapka (apply hash-map (take 200 (N))))
;; (def secik (apply hash-set (take 0 (N))))
