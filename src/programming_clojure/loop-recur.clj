(loop[result []
      x 5]
 (if (zero? x)
   result
   (recur (conj result x) (dec x))))
;; => [5 4 3 2 1]

(defn countdown
  [result x]
  (if (zero? x)
    result
    (recur (conj result x) (dec x))))

(countdown [] 5)
;; => [5 4 3 2 1]

;;***
;; recur is a powerful building block. But you may not use it very often, because many
;; common recursions are provided by Clojure’s sequence library.

(into [] (take 5 (iterate dec 5)))
;; => [5 4 3 2 1]

(into [] (drop-last (reverse (range 6))))
;; => [5 4 3 2 1]

(vec (reverse (rest (range 6))))
;; => [5 4 3 2 1]
