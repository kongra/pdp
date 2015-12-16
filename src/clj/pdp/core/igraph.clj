;; Copyright (c) Konrad Grzanek. All rights reserved.
;; Created 2015-12-11

(in-ns 'pdp.core)

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

(defn igraph-range
  [^jpdp.igraph.IGraph g]
  (.range g))


(defn igraph-vertices-count
  [^jpdp.igraph.IGraph g]
  (.verticesCount g))


(defn igraph-empty?
  [^jpdp.igraph.IGraph g]
  (.isEmpty g))


(defn igraph-vertices
  {:inline (fn [g] `(.vertices ~g))}
  [^jpdp.igraph.IGraph g]
  (.vertices g))


(defn igraph-has-vertex?
  {:inline (fn [g v] `(.hasVertex ~g ~v))}
  [^jpdp.igraph.IGraph g v]
  (.hasVertex g v))


(defn igraph-has-edge?
  {:inline (fn [g v1 v2] `(.hasEdge ~g ~v1 ~v2))}
  [^jpdp.igraph.IGraph g v1 v2]
  (.hasEdge g v1 v2))


(defn igraph-successors
  {:inline (fn [g v] `(.successors ~g ~v))}
  [^jpdp.igraph.IGraph g v]
  (.successors g v))


(defn ^jpdp.igraph.IGraph igraph-clone
  [^jpdp.igraph.IGraph g]
  (.cloneme g))


;; DIGRAPH

(defn digraph-predecessors
  {:inline (fn [g v] `(.predecessors ~g ~v))}
  [^jpdp.igraph.IDigraph g v]
  (.predecessors g v))


;; MUTATIONS

(defn igraph-add-vertex!
  {:inline (fn [g v] `(.addVertex ~g ~v))}
  [^jpdp.igraph.IMutableGraph g v]
  (.addVertex g v))


(defn igraph-remove-vertex!
  {:inline (fn [g v] `(.removeVertex ~g ~v))}
  [^jpdp.igraph.IMutableGraph g v]
  (.removeVertex g v))


(defn igraph-add-edge!
  {:inline (fn [g v1 v2] `(.addEdge ~g ~v1 ~v2))}
  [^jpdp.igraph.IMutableGraph g v1 v2]
  (.addEdge g v1 v2))


(defn igraph-remove-edge!
  {:inline (fn [g v1 v2] `(.removeEdge ~g ~v1 ~v2))}
  [^jpdp.igraph.IMutableGraph g v1 v2]
  (.removeEdge g v1 v2))


;; DUMP/PRESENTATION

(defn igraph-adjacent
  [^jpdp.igraph.IGraph g]
  (fn [v] (seq (igraph-successors g v))))


(defn igraph-print
  ([g v show depth]
   (print-graph v (igraph-adjacent g) show depth))

  ([g v show]
   (print-graph v (igraph-adjacent g) show))

  ([g v]
   (print-graph v (igraph-adjacent g))))


(def ^jpdp.igraph.Unigraph ug (unigraph 100))
(dotimes [i 20] (igraph-add-vertex! ug i))

;; (def mapka (apply hash-map (take 200 (N))))
;; (def secik (apply hash-set (take 0 (N))))
