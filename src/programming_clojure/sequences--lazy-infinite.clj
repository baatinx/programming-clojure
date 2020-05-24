;; Lazy and Infinite Sequences
;; “pay” only for what you use.

;; Using lazy sequences has many benefits:
;; 1. You can postpone expensive computations that may not in fact be needed.
;; 2. You can work with huge data sets that don’t fit into memory.
;; 3. You can delay I/O until it’s absolutely needed.


;; ***
;;  prime numbers using wheel factorization - pending


;; force the sequence to evaluate fully
(def x (for [i (range 1 5)]
         (do (println "val. of i:" i)
             (* i 100))))
;; => #'user/x
;; prints nothing

;; ***
;; You’ve created a lazy sequence, and you want to force the sequence to evaluate fully.
;; The problem usually arises when the code generating the sequence has side effects

;; x don't contains actuall elements yet
;; force evaluation
(doall x)
;; => val. of i: 1
;;    val. of i: 2
;;    val. of i: 3
;;    val. of i: 4
;;    (100 200 300 400)

x
;; => (100 200 300 400)

;; dorun walks the elements of a sequence without keeping past elements in memory. As
;; a result, dorun can walk collections too large to fit in memory.
;; (dorun x)
(def y (for [i (range 1 5)]
         (do (println "val. of i:" i)
             (* i 100))))

(dorun y)
;; => val. of i: 1
;;    val. of i: 2
;;    val. of i: 3
;;    val. of i: 4
;;    nil

;; ***
;; The nil return value is a telltale reminder that dorun does not hold a reference to the
;; entire sequence. The dorun and doall functions help you deal with side effects, while
;; most of the rest of Clojure discourages side effects
