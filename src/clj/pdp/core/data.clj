;; Copyright (c) Konrad Grzanek. All rights reserved.
;; Created 2015-11-16

(in-ns 'pdp.core)

(def ^:java.util.BitSet bs (doto (java.util.BitSet. 2000)
                             (.set 0)
                             (.set 1)
                             (.set 1999)))
