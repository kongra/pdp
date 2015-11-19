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
    (synchronized
     this
     (let [it (.iterator ^java.util.LinkedList (.rules this))]
       (loop []
         (when (.hasNext it)
           (let [r (.next it)]
             (r conf)
             (recur))))))))


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
    (synchronized
     this
     (let [^java.util.ArrayList rules (.rules this)]
       (dotimes [i (.size rules)]
         (let [r (.get rules (int i))]
           (r conf)))))))
