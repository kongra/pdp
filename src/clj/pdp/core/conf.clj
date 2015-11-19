;; Copyright (c) Konrad Grzanek. All rights reserved.
;; Created 2015-11-19

(in-ns 'pdp.core)

(defn make-conf [n] (jpdp.core.Conf. (int n)))

(defmacro conf-set!
  [conf prop value]
  (let [conf (vary-meta conf assoc :tag 'jpdp.core.Conf)]
    `(.set ~conf (lv ~prop) (lv ~value))))


(defmacro conf-get
  [conf prop]
  (let [conf (vary-meta conf assoc :tag 'jpdp.core.Conf)]
    `(.get ~conf (lv ~prop))))


;; (defprops Engine Gearbox)
;; (defvals  Diesel Benz Auto Manual)
;; (def c (make-conf 100))
