(println "Hello")

(= 2 2 )

(+ 1 2)

*1
;; => true

*3
;; => true

(/ 1 0)
;; => Execution error (ArithmeticException) at user/eval21702 (form-init8581877204710123806.clj:13).
;;    Divide by zero

*e
;; => #error {
;;     :cause "Divide by zero"
;;     :via
;;     [{:type java.lang.ArithmeticException
;;       :message "Divide by zero"
;;       :at [clojure.lang.Numbers divide "Numbers.java" 188]}]
;;     :trace
;;     [[clojure.lang.Numbers divide "Numbers.java" 188]
;;      [clojure.lang.Numbers divide "Numbers.java" 3901]
;;      [user$eval21702 invokeStatic "form-init8581877204710123806.clj" 13]
;;      [user$eval21702 invoke "form-init8581877204710123806.clj" 13]
;;      [clojure.lang.Compiler eval "Compiler.java" 7176]
;;      [clojure.lang.Compiler eval "Compiler.java" 7131]
;;      [clojure.core$eval invokeStatic "core.clj" 3214]
;;      [clojure.core$eval invoke "core.clj" 3210]
;;      [clojure.main$repl$read_eval_print__9068$fn__9071 invoke "main.clj" 414]
;;      [clojure.main$repl$read_eval_print__9068 invoke "main.clj" 414]
;;      [clojure.main$repl$fn__9077 invoke "main.clj" 435]
;;      [clojure.main$repl invokeStatic "main.clj" 435]
;;      [clojure.main$repl doInvoke "main.clj" 345]
;;      [clojure.lang.RestFn invoke "RestFn.java" 1523]
;;      [nrepl.middleware.interruptible_eval$evaluate invokeStatic "interruptible_eval.clj" 79]
;;      [nrepl.middleware.interruptible_eval$evaluate invoke "interruptible_eval.clj" 55]
;;      [nrepl.middleware.interruptible_eval$interruptible_eval$fn__939$fn__943 invoke "interruptible_eval.clj" 142]
;;      [clojure.lang.AFn run "AFn.java" 22]
;;      [nrepl.middleware.session$session_exec$main_loop__1040$fn__1044 invoke "session.clj" 171]
;;      [nrepl.middleware.session$session_exec$main_loop__1040 invoke "session.clj" 170]
;;      [clojure.lang.AFn run "AFn.java" 22]
;;      [java.lang.Thread run "Thread.java" 834]]}
