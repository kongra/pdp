;; Copyright (c) Konrad Grzanek. All rights reserved.
;; Created 2015-12-21

(ns pdp.digraph
  (:use    [clongra.core])
  (:import [jpdp.igraph IDigraph Digraph])
  (:gen-class))


;; UNDIRECTED GRAPHS

(defn ^IDigraph make-digraph [range] (Digraph. range))

(defn successors
  {:inline (fn [dg v] `(.successors ~dg ~v))}
  [^IDigraph dg v]
  (.successors dg v))


(defn successor-edges
  {:inline (fn [dg v] `(.successorEdges ~dg ~v))}
  [^IDigraph dg v]
  (.successorEdges dg v))


(defn predecessors
  {:inline (fn [dg v] `(.predecessors ~dg ~v))}
  [^IDigraph dg v]
  (.predecessors dg v))


(defn predecessor-edges
  {:inline (fn [dg v] `(.predecessorEdges ~dg ~v))}
  [^IDigraph dg v]
  (.predecessorEdges dg v))


;; DUMP/PRESENTATION

(defn adjacent
  [^IDigraph dg]
  (fn [v] (seq (successors dg v))))


(defn dump
  ([dg v show depth]
   (print-graph v (adjacent dg) show depth))

  ([dg v show]
   (print-graph v (adjacent dg) show))

  ([dg v]
   (print-graph v (adjacent dg))))
