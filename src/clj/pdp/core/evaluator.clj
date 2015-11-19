;; Copyright (c) Konrad Grzanek. All rights reserved.
;; Created 2015-11-19

(in-ns 'pdp.core)

(defprotocol Evaluator
  (add-rule       [this rule])
  (eval-conf-impl [this conf]))


(def ^:private ev (atom nil))

(defmacro commit-evaluator
  []
  `(reset! ev ~'current-ev))


(defn eval-conf
  [conf]
  (eval-conf-impl @ev conf))
