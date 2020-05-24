;; The seq abstraction of first/rest applies to anything that there can be more than one of.
;; In the Java world, that includes the following:
;; 1. The Collections API
;; 2. Regular expressions
;; 3. File system traversal
;; 4. XML processing
;; 5. Relational database results

(first "Quick")
(first (java.lang.String. "Quick"))
(first (new java.lang.String "Quick"))
;; => \Q

(rest "Quick")
;; => (\u \i \c \k)

(.getBytes "Quick")
;; => [81, 117, 105, 99, 107]

(first (.getBytes "Quick"))
;; => 81

(cons \Q "uick")
;; => (\Q \u \i \c \k)

;; Hashtables and Maps are also seq-able:
(first (System/getProperties))
;; => #object[java.util.concurrent.ConcurrentHashMap$MapEntry 0x59121ce0 "sun.desktop=gnome"]

(rest (System/getProperties))
;; => (#object[java.util.concurrent.ConcurrentHashMap$MapEntry 0x519f1545 "awt.toolkit=sun.awt.X11.XToolkit"]
;;     #object[java.util.concurrent.ConcurrentHashMap$MapEntry 0x2013a935 "java.specification.version=11"]
;;     .
;;     .
;;     .
;;     #object[java.util.concurrent.ConcurrentHashMap$MapEntry 0x3d1e3e4b "java.class.version=55.0"])

;; Clojure will automatically obtain a sequence from a collection, but it won’t
;; automatically convert a sequence back to the original collection type

(reverse "Quick")
;; => (\k \c \i \u \Q)

(apply str (reverse "Quick"))
;; => "kciuQ"

;; Prefer the Java collections only in interop scenarios where you’re working with legacy Java APIs


;; Seq-ing Regular Expressions - pending

;; Seq-ing the file system

(import 'java.io.File)
(.listFiles (File. "."))
;; => [#object[java.io.File 0x1a3c416f "./project.clj"], #object[java.io.File 0x6ec3224d "./.nrepl-port"],
;;     #object[java.io.File 0x3c04033c "./doc"], #object[java.io.File 0x4ab7f3b1 "./LICENSE"],
;;     #object[java.io.File 0x3c49f08e "./test"], #object[java.io.File 0x9026188 "./CHANGELOG.md"],
;;     #object[java.io.File 0x15838e77 "./.hgignore"], #object[java.io.File 0x605cdd0d "./README.md"],
;;     #object[java.io.File 0x13e28c08 "./resources"], #object[java.io.File 0x976b1dd "./target"],
;;     #object[java.io.File 0x8f76b2 "./words-of-wisdom.txt"], #object[java.io.File 0x63d771b "./.gitignore"],
;;     #object[java.io.File 0x1383918 "./src"], #object[java.io.File 0x3d7c88bb "./.git"]]

(map #(.getName %)
     (.listFiles (File. ".")))
;; => ("project.clj"
;;     ".nrepl-port"
;;     "doc"
;;     "LICENSE"
;;     "test"
;;     "CHANGELOG.md"
;;     ".hgignore"
;;     "README.md"
;;     "resources"
;;     "target"
;;     "words-of-wisdom.txt"
;;     ".gitignore"
;;     "src"
;;     ".git")


;; Often, you want to recursively traverse the entire directory tree. Clojure provides a
;; depth-first walk via file-seq.
(file-seq (File. "."))

(count (file-seq (File. ".")))
;; => 174

;; recently modified file only

(defn minutes-to-millis
  [mins]
  (* mins 1000 60))

(defn recently-modified?
  [file]
  (> (.lastModified file) (- (System/currentTimeMillis) (minutes-to-millis 30))))

(filter recently-modified? 
        (file-seq (File. ".")))
;; => (#object[java.io.File 0x1919e226 "./src/programming_clojure/sequences--clojure-makes-java-seqable.clj"])





(line-seq (clojure.java.io/reader "src/programming_clojure/core.clj"))
;; => ("(ns programming-clojure.core"
;;     "  (:gen-class))"
;;     ""
;;     "(defn -main"
;;     "  \"I don't do a whole lot ... yet.\""
;;     "  [& args]"
;;     "  (println \"Hello, World!\"))")

(take 2 (line-seq (clojure.java.io/reader "src/programming_clojure/core.clj")))
;; => ("(ns programming-clojure.core" "  (:gen-class))" "" "(defn -main" "  \"I don't do a whole lot ... yet.\"" "  [& args]")

;; ???
(with-open [rdr (clojure.java.io/reader "src/programming_clojure/core.clj")]
  (take 2 (line-seq rdr)))
;; => Error printing return value (IOException) at java.io.BufferedReader/ensureOpen (BufferedReader.java:122).
;;    Stream closed

(with-open [rdr (clojure.java.io/reader "src/programming_clojure/core.clj")]
  (count (line-seq rdr)))
;; => 7

;; count non-blank lines only
(with-open [rdr (clojure.java.io/reader "src/programming_clojure/core.clj")]
  (count (filter #(re-find #"\S" %) (line-seq rdr))))
;; => 6

;; Using seqs both on the file system(file-seq) and on the contents of individual files(line-seq), you can
;; quickly create interesting utilities

;; (use '[clojure.java.io :only (reader)])
;; (use '[clojure.string :only (blank?)])

;; ???
;; non-svn? detects files that are not Subversion metadata.
(.toString (File. "src/programming_clojure/core.clj"))
;; => "src/programming_clojure/core.clj"


(defn non-blank? [line] (not (clojure.string/blank? line)))

(defn non-svn? [file] (not (.contains (.toString file) ".svn")))

(defn clojure-source? [file]  (.endsWith (.toString file) ".clj"))

;; calculate clojure lines of code
(defn clojure-loc 
  [base-file]
  (reduce +
          (for [file (file-seq base-file) 
                :when (and (clojure-source? file) (non-svn? file))]
            (with-open [rdr (clojure.java.io/reader file)]
              (count (filter non-blank? 
                             (line-seq rdr)))))))

(clojure-loc (File. "src/programming_clojure"))
;; => 1126

(clojure-loc (File. "src/programming_clojure"))
;; => 1128

(clojure-loc (File. "src/programming_clojure"))
;; => 1130
