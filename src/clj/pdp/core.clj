;; Copyright (c) Konrad Grzanek. All rights reserved.
;; Created 2015-11-09

(ns pdp.core
  (:use [clongra.core])

  (:gen-class))

(load "core/igraph")

;; (def ^java.util.Random rnd
;;   (let [seed (byte-array 16)]
;;     (aset ^bytes seed 0 (byte 1))
;;     (org.uncommons.maths.random.MersenneTwisterRNG. seed)))
