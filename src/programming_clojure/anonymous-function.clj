;; At least three reasons exist to create an anonymous function:
;; 1. The function is so brief and self-explanatory that giving it a name makes the code
;;    harder to read, not easier.

(map #(inc %) [1 2 3])
;; => (2 3 4)

(filter #(> (count %) 2) 
        (clojure.string/split "A fine day it is" #"\W+"))
;; => ("fine" "day")

(clojure.string/split "A fine day it is" #" ")
;; => ["A" "fine" "day" "it" "is"]

(clojure.string/split "A fine day it is" #"\W+")
;; => ["A" "fine" "day" "it" "is"]


;; 2. The function is being used only from inside another function and needs a local
;;    name, not a top-level binding.
(defn indexable-words
  [text] 
  (let [indexable-words? (fn[w] (> (count w) 2))]
    (filter indexable-words? (clojure.string/split text #"\W+"))))
;; => #'user/indexable-words

(indexable-words "A fine day it is")
;; => ("fine" "day")

;; 3. The function is created inside another function for the purpose of capturing the
;;    values of parameters or local bindings.
;;    A third reason to use anonymous functions is when you create a function dynamically
;;    at runtime

(defn greeting
  [username]
  (str "Hello, " username ))
(greeting "Seerat")
;; => "Hello, Seerat"

(defn greeting2
  [username]
  (str "Hi, " username))
(greeting2 "Seerat")
;; => "Hi, Seerat"

;; Anonymous function in Action

(defn make-greeter
  [greeting-prefix]
  (fn[username]
    (str greeting-prefix ", " username)))

((make-greeter "Hello") "Seerat")
;; => "Hello, Seerat"

((make-greeter "Hi") "Seerat")
;; => "Hi, Seerat"

(def foo #(println %1 "\t" %2 "\t" %&))

(foo 1 2 3 4 5 6)
;; => 1 	 2 	 (3 4 5 6)
