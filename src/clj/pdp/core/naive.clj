;; Copyright (c) Konrad Grzanek. All rights reserved.
;; Created 2015-11-19

(in-ns 'pdp.core)

;; BASED ON LINKED LIST

(deftype LinkedEvaluator [rules])

(defn make-linked-evaluator
  []
  (LinkedEvaluator. (java.util.LinkedList.)))


(extend-type LinkedEvaluator
  Evaluator
  (add-rule [this rule]
    (synchronized
     this
     (.addLast ^java.util.LinkedList (.rules this) rule)))

  (eval-conf [this conf]
    ;; Intentionally we go with no synchronization, relying on the
    ;; assumption that the process of creating (and thus adding) rules
    ;; is completed before any evaluation.
    (jpdp.core.Naive/evalLinked (.rules this) conf)))


;; BASED ON ARRAY LIST

(deftype ArrayEvaluator [rules])

(defn make-array-evaluator
  []
  (ArrayEvaluator. (java.util.ArrayList.)))


(extend-type ArrayEvaluator
  Evaluator
  (add-rule [this rule]
    (synchronized
     this
     (.add ^java.util.ArrayList (.rules this) rule)))

  (eval-conf [this conf]
    ;; See comment for LinkedEvaluator's implementation.
    (jpdp.core.Naive/evalArray (.rules this) conf)))
