;; Copyright (c) Konrad Grzanek. All rights reserved.
;; Created 2015-12-21

(in-ns 'pdp.unigraph)

;; COMMON ALGORITHMS FOR UNIGRAPHS

(defn clique?
  "Aswers a question whether or not a subgraph of g induced by a set
  of vertices vs is a clique (is it complete)."
  [^Unigraph g ^longs vs]
  (TODO Implement)

  (let [is (lr/boolean true)
        n  (alength vs)]

    (lr/value is)))
