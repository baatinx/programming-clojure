(def a 10)

(var a)
;; => #'user/a

;; reader macro #'
#'a
;; => #'user/a

@(var a)
;; => 10

@#'a
;; => 10

;; vars have many abilities other than just storing a value
;; 1. The same var can be aliased into more than one namespace. This allows you to use convenient short names.
;; 2. Vars can have metadata
;; 3. Vars can be dynamically rebound on a per-thread basis


(resolve 'a)
;; => #'user/a
;; resolve returns the var or class that a symbol will resolve to in the current namespace

;; var v/s resolve - pending 

;; When you create a new namespace with in-ns, the java.lang package is automatically available to you:

(in-ns 'new-ns)
String
;; => java.lang.String

(println "foo")
;; => Syntax error compiling at (form-init14161052752390237346.clj:1:1).
;;    Unable to resolve symbol: println in this context
;;    class clojure.lang.Compiler$CompilerException

File/separator
;; => Syntax error compiling at (var-and-namespaces.clj:1:8133).
;;    No such namespace: File

java.io.File/separator
;; => "/"

(import (java.io File))
;; => java.io.File

File/separator
;; => "/"

(import '(java.io File InputStream FileReader FileWriter))

(import [java.io File]
        [java.util Date])

(meta #'str)
;; => {:added "1.0",
;;     :ns #namespace[clojure.core],
;;     :name str,
;;     :file "clojure/core.clj",
;;     :static true,
;;     :column 1,
;;     :line 544,
;;     :tag java.lang.String,
;;     :arglists ([] [x] [x & ys]),
;;     :doc
;;     "With no args, returns the empty string. With one arg x, returns\n  x.toString().  (str nil) returns the empty string. With more than\n  one arg, returns the concatenation of the str values of the args."}


(meta #'->)
;; => {:added "1.0",
;;     :ns #namespace[clojure.core],
;;     :name ->,
;;     :file "clojure/core.clj",
;;     :column 1,
;;     :line 1677,
;;     :macro true,
;;     :arglists ([x & forms]),
;;     :doc
;;     "Threads the expr through the forms. Inserts x as the\n  second item in the first form, making a list of it if it is not a\n  list already. If there are more forms, inserts the first form as the\n  second item in second form, etc."}


(defn foo
  [s]
  (println s))

(meta #'foo)
;; => {:arglists ([s]), :line 1, :column 1, :file "/tmp/form-init14161052752390237346.clj", :name foo, :ns #namespace[user]}

(defn ^{:tag String} foo
  [^{:tag String} s]
  (println s))

(meta #'foo)
;; => {:tag java.lang.String,
;;     :arglists ([s]),
;;     :line 91,
;;     :column 1,
;;     :file
;;     "/home/mustafa-basit/Documents/Git@MustafaBasit/Clojure/programming-clojure/src/programming_clojure/var-and-namespaces.clj",
;;     :name foo,
;;     :ns #namespace[user]}


(defn ^String foo
  [^String s]
  (println s))

(meta #'foo)
;; => {:tag java.lang.String,
;;     :arglists ([s]),
;;     :line 1,
;;     :column 1,
;;     :file "/tmp/form-init14161052752390237346.clj",
;;     :name foo,
;;     :ns #namespace[user]}
