(clojure.repl/source even?)
;; => (defn even?
;;        "Returns true if n is even, throws an exception if n is not an integer"
;;        {:added "1.0"
;;         :static true}
;;        [n] (if (integer? n)
;;              (zero? (bit-and (clojure.lang.RT/uncheckedLongCast n) 1))
;;              (throw (IllegalArgumentException. (str "Argument must be an integer: " n)))))

(defn foo
  [x]
  (inc x))
;; => #'user/foo

(clojure.repl/source foo)
;; => nil


(clojure.repl/source identity)
;; => (defn identity
;;      "Returns its argument."
;;      {:added "1.0"
;;       :static true}
;;      [x] x)
