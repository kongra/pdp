;; Copyright (c) Konrad Grzanek. All rights reserved.
;; Created 2015-12-21

(ns pdp.unigraph
  (:require [pdp.igraph :as g])
  (:use     [clongra.core])
  (:import  [jpdp.igraph IUnigraph Unigraph])
  (:gen-class))


;; UNDIRECTED GRAPHS

(defn ^IUnigraph make-unigraph [range] (Unigraph. range))

(defn ^longs neighbors
  {:inline (fn [ug v] `(.neighbors ~ug ~v))}
  [^IUnigraph ug ^long v]
  (.neighbors ug v))


(defn neighbor-edges
  {:inline (fn [ug v] `(.neighborEdges ~ug ~v))}
  [^IUnigraph ug ^long v]
  (.neighborEdges ug v))


;; DUMP/PRESENTATION

(defn adjacent
  [^IUnigraph ug]
  (fn [v] (seq (neighbors ug v))))


(defn dump
  ([ug v show depth]
   (print-graph v (adjacent ug) show depth))

  ([ug v show]
   (print-graph v (adjacent ug) show))

  ([ug v]
   (print-graph v (adjacent ug))))


;; MORE TO COME ...

(load "unigraph/commalg")
