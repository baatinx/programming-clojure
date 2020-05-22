(new java.util.Random)
;; => #object[java.util.Random 0x1f9ca46f "java.util.Random@1f9ca46f"]

(java.util.Random.)
;; => #object[java.util.Random 0xfdfb600 "java.util.Random@fdfb600"]


(def rnd (java.util.Random.))
;; (. class-or-instance member-symbol & args)
(. rnd nextInt 100)
;; => 68
;; => 52

(. java.lang.Math sqrt 4)
;; => 2.0

(.nextInt rnd 100)
;; => 51
;; => 13


(macroexpand '(. rnd nextInt 10))
;; => (. rnd nextInt 10)

(macroexpand '(.nextInt rnd 10))
;; => (. rnd nextInt 10)

(. java.lang.Math PI)
;; => 3.141592653589793

;; ???
(.PI  java.lang.Math)
;; => Execution error (IllegalArgumentException) at user/eval21063 (form-init14161052752390237346.clj:31).
;;    No matching field found: PI for class java.lang.Class

(java.lang.Math/PI)
;; => 3.141592653589793

java.lang.Math/PI
;; => 3.141592653589793


(def p (java.awt.Point. 10 20))
;; => #'user/p
(. p x)
;; => 10

(. p y)
;; => 20

(. p -x)
;; => 10

;; x v\s -x ---------> ???


(. System lineSeparator)
;; => "\n"

(System/lineSeparator)
;; => "\n"

(javadoc java.net.URL)
;; => Syntax error compiling at (calling-java.clj:63:1).
;;    Unable to resolve symbol: javadoc in this context
