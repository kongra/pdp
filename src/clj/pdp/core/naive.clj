;; Copyright (c) Konrad Grzanek. All rights reserved.
;; Created 2015-11-19

(in-ns 'pdp.core)

;; BASED ON LINKED LIST

(deftype LinkedEvaluator [rules])

(defmacro def-linked-evaluator
  []
  `(def ~'current-ev (pdp.core.LinkedEvaluator. (java.util.LinkedList.))))


(extend-type LinkedEvaluator
  Evaluator
  (add-rule [this rule]
    (synchronized
     this
     (.addLast ^java.util.LinkedList (.rules this) rule)))

  (eval-conf-impl [this conf]
    ;; Intentionally we go with no synchronization, relying on the
    ;; assumption that the process of creating (and thus adding) rules
    ;; is completed before any evaluation.
    (jpdp.core.Naive/evalLinked (.rules this) conf)))


;; BASED ON ARRAY LIST

(deftype ArrayEvaluator [rules])

(defmacro def-array-evaluator
  []
  `(def ~'current-ev (pdp.core.ArrayEvaluator. (java.util.ArrayList.))))


(extend-type ArrayEvaluator
  Evaluator
  (add-rule [this rule]
    (synchronized
     this
     (.add ^java.util.ArrayList (.rules this) rule)))

  (eval-conf-impl [this conf]
    ;; See comment for LinkedEvaluator's implementation.
    (jpdp.core.Naive/evalArray (.rules this) conf)))
