

(+)
;; => 0

(str)
;; => ""

(*)
;; => 1

(-)
;; => Execution error (ArityException) at user/eval26717 (form-init8581877204710123806.clj:32).
;;    Wrong number of args (0) passed to: clojure.core/-

(/)
;; => Execution error (ArityException) at user/eval26732 (form-init8581877204710123806.clj:36).
;;    Wrong number of args (0) passed to: clojure.core//

(if ()
  "() is true"
  "() is false")
;; => "() is true"

(if 0
  "Zero is true"
  "Zero is false")
;; => "Zero is true"


(true? true)
;; => true

(true? "foo")
;; => false

(true? (= 1 1))
;; => true

(false? false)
;; => true

(nil? nil)
;; => true

(false? nil)
;; => false

(nil? false)
;; => false

(nil? (println "foo"))
;; => true

(zero? 0.0)
;; => true

(zero? 0.00000001)
;; => false



