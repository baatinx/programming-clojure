(defn stack-consuming-fibo 
  [n]
  (cond
    (= n 0) 0
    (= n 1) 1
    :else (+ 
           (stack-consuming-fibo (- n 1))
           (stack-consuming-fibo (- n 2)))))

(stack-consuming-fibo 2)
;; => 1

(stack-consuming-fibo 9)
;; => 34

(stack-consuming-fibo 10000)
;; => Execution error (StackOverflowError) at user/stack-consuming-fibo (form-init10410706667350446345.clj:4).
;;    null


(defn recur-fibo
  [n]
  (letfn[(fib 
          [current next n]
          (if (zero? n)
            current
            (recur next (+ current next) (dec n))))]
   (fib 0N 1N n)))

(recur-fibo 3)
;; => 2N

(recur-fibo 9)
;; => 34N

(recur-fibo 10000)
;; => 33644764876466073......................................310059947366875N

(defn positive-numbers
  ([] (positive-numbers 0))
  ([n] (cons n (lazy-seq (positive-numbers (inc n))))))

(take 4 (positive-numbers))
;; => (0 1 2 3)

(take 20 (positive-numbers))
;; => (0 1 2 3 4 5 6 7 8 9 10 11 12 13 14 15 16 17 18 19)



(concat [0 1] (list 1 2 3 5 8))
;; => (0 1 1 2 3 5 8)

(defn lazy-seq-fibo
  ([] (concat [0 1] (lazy-seq-fibo 0N 1N )))
  
  ([a b]
   (let [n (+ a b)]
     (lazy-seq
      (cons n (lazy-seq-fibo b n))))))

(take 2 (lazy-seq-fibo))
;; => (0 1)

;; lazy-seq replace recursion with laziness.
(take 5 (iterate (fn[[a b]]
                   [b (+ a b)])
                 [0N 1N]))
;; => ([0N 1N] [1N 1N] [1N 2N] [2N 3N] [3N 5N])
;; induction - (fn [[a b]]
;;             [b (+ a b)])

;; base -  [0N 1N]))


(defn  fibo
  []
  (map first 
       (iterate (fn[[a b]]
                        [b (+ a b)])
                      [0N 1N])))
(take 10 (fibo))
;; => (0N 1N 1N 2N 3N 5N 8N 13N 21N 34N)

;; ***
;; Lazy definitions consume some stack and heap. But they don’t consume resources
;; proportional to the size of an entire (possibly infinite!) sequence. Instead, you choose
;; how many resources to consume when you traverse the sequence. If you want the one
;; millionth Fibonacci number, you can get it from fibo, without having to consume stack
;; or heap space for all the previous values.

(defn lazy-even
  ([] (lazy-even 0))
  ([n] (cons n (lazy-seq (lazy-even (+ n 2))))))

(take 10(lazy-even))
;; => (0 2 4 6 8 10 12 14 16 18)

(take 10 (iterate (fn[n]
                    (+ n 2))
                  0))
;; => (0 2 4 6 8 10 12 14 16 18)


(set! *print-length* 100000)
;; => 10

(take 10000 (fibo))
;; => (0N 1N 1N 2N 3N 5N 8N 13N 21N 34N ...)

(lazy-cat [1 2 3] '(4 5 6))
;; => (1 2 3 4 5 6)

(defn n-repeat [n] (lazy-cat (repeat n n) (n-repeat (inc n))))
(take 6 (n-repeat 1))
;; => (1 2 2 3 3 3)


(def head-fibo (lazy-cat [0 1]  (map +
                                     head-fibo
                                     (rest head-fibo))))
https://groups.google.com/forum/#!topic/clojure/leQICeA9w_o
http://clojurescriptmadeeasy.com/blog/how-to-follow-recursion-with-lazy-sequences.html
https://stackoverflow.com/questions/2214258/holding-onto-the-head-of-a-sequence
