;; Copyright (c) Konrad Grzanek. All rights reserved.
;; Created 2015-11-19

(in-ns 'pdp.core)

(defprotocol Evaluator
  (add-rule  [this rule])
  (eval-conf [this conf]))
