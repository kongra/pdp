;; Copyright (c) Konrad Grzanek. All rights reserved.
;; Created 2015-12-11

(in-ns 'pdp.core)

;; FAST MUTABLE UNWEIGHTED GRAPHS WITH INTEGRAL VERTICES IN RANGE
;; 0 .. (range-1)

;; COMMON READS

(defn unigraph
  [range]
  (jpdp.igraph.Unigraph. range))


(defn igraph-range
  [^jpdp.igraph.IGraph g]
  (.range g))


(defn igraph-vertices
  [^jpdp.igraph.IGraph g]
  (.vertices g))


(defn igraph-has-vertex?
  [^jpdp.igraph.IGraph g v]
  (.hasVertex g v))


(defn igraph-has-edge?
  [^jpdp.igraph.IGraph g v1 v2]
  (.hasEdge g v1 v2))


(defn igraph-successors
  [^jpdp.igraph.IGraph g v]
  (.successors g v))


(defn igraph-clone
  [^jpdp.igraph.IGraph g]
  (.cloneme g))


;; DIGRAPH

(defn digraph-predecessors
  [^jpdp.igraph.IDigraph g v]
  (.predecessors g v))


;; MUTATIONS

(defn igraph-add-vertex!
  [^jpdp.igraph.IMutableGraph g v]
  (.addVertex g v))


(defn igraph-remove-vertex!
  [^jpdp.igraph.IMutableGraph g v]
  (.removeVertex g v))


(defn igraph-add-edge!
  [^jpdp.igraph.IMutableGraph g v1 v2]
  (.addEdge g v1 v2))


(defn igraph-remove-edge!
  [^jpdp.igraph.IMutableGraph g v1 v2]
  (.removeEdge g v1 v2))


;; DUMP/PRESENTATION

(defn igraph-adjacent
  [g]
  (fn [v] (seq (igraph-successors g v))))


(defn igraph-print
  ([g v show depth]
   (print-graph v (igraph-adjacent g) show depth))

  ([g v show]
   (print-graph v (igraph-adjacent g) show))

  ([g v]
   (print-graph v (igraph-adjacent g))))


;; (def ug (unigraph 100))
