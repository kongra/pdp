;; Copyright (c) Konrad Grzanek. All rights reserved.
;; Created 2015-12-21

(in-ns 'pdp.unigraph)

;; CLIQUES, COMPLETE GRAPHS AND SUBGRAPHS

(defn clique?
  "Aswers a question whether or not a subgraph of g induced by a set
  of vertices vs is a clique (is it complete)."
  [^Unigraph g ^longs vs]
  (let [is (lr/boolean true)
        n  (alength vs)]

    (oloo/for [i 0 (p/< i n) (p/inc i)]
      (oloo/for [j (p/inc i) (p/< j n) (p/inc j)]
        (let [v1 (aget vs i)
              v2 (aget vs j)]
          (when-not (g/has-edge? g v1 v2)
            (lr/reset! is false)
            (oreturn)))))

    (lr/value is)))


(defn ^long missing-clique-edges-count
  "Returns the count of edges that must be added to make the subgraph
  of g induced by vertices vs a clique (complete subgraph)."
  [^Unigraph g ^longs vs]
  (let [count (lr/long 0)
        n     (alength vs)]

    (oloo/for [i 0 (p/< i n) (p/inc i)]
      (oloo/for [j (p/inc i) (p/< j n) (p/inc j)]
        (let [v1 (aget vs i)
              v2 (aget vs j)]
          (when-not (g/has-edge? g v1 v2) (lr/over! count p/inc)))))

    (lr/value count)))


(defn ^Unigraph make-clique!
  "Makes the subgraph of g induced by vertices vs a clique (complete
  subgraph) by adding all necessary edges in g. Returns g."
  [^Unigraph g ^longs vs]
  (let [n (alength vs)]
    (oloo/for [i 0 (p/< i n) (p/inc i)]
      (oloo/for [j (p/inc i) (p/< j n) (p/inc j)]
        (let [v1 (aget vs i)
              v2 (aget vs j)]
          (g/add-edge! g v1 v2))))
    g))


;; TESTS
;; (def ^Unigraph g (-> (make-unigraph 10)
;;                      (make-clique!  (g/vs 0 1 2 3 4))))
