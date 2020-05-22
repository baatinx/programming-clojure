(def a (smile 1 2 3))
;; => Syntax error compiling at (comments.clj:1:8).
;;    Unable to resolve symbol: smile in this context

(comment
  (def a (smile 1 2 3)))
  ;; => Syntax error compiling at (comments.clj:6:10).
  ;;    Unable to resolve symbol: smile in this context


(defn foo
  [name]
  #_(println "Hello world")
  name)
;; => #'user/foo


(foo "Basit")
;; => "Basit"
