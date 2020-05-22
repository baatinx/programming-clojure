;; ***
;; In many programming languages, you bind a variable to an entire collection when you
;; need to access only part of the collection.

(def author-db {:fname "Alex"
                :lname "Miller"
                :age 32
                :mail "alexmiller@gmail.com"
                :contact 9023498123})

(defn process-author-details
  [details]
  (str "processing...." details))

(process-author-details author-db)
;; => "processing....{:fname \"Alex\", :lname \"Miller\", :age 32, :mail \"alexmiller@gmail.com\", :contact 9023498123}"

(defn greet-author
  [details]
  (str "Hello, " (:fname details)))
(greet-author author-db)
;; => "Hello, Alex"

;; Destructuring in Action

(defn greet-author
  [{fname :fname}]
  (str "Hello, " fname))
;; => #'user/greet-author

(let [[x y] [1 2 3 4 5]]
  (str "x: " x  ", y: " y))
;; => "x: 1, y: 2"

(let [[_ _ x y] [1 2 3 4 5]]
  (str "x: " x  ", y: " y))
;; => "x: 3, y: 4"

(let [[_ _ x y] [1 2 3 4 5]]
  _)
;; => 2

(let [[_ x _ _ y] [1 2 3 4 5]]
  _)
;; => 4

(let [[x y :as coords] [1 2 3 4 5 6]]
  (println x y)
  (println coords))
;; => 1 2
;;    [1 2 3 4 5 6]

(defn foo
  [[m n & o]]
  (println m)
  (println n)
  (println o))

(foo "one two three")
;; => o
;;    n
;;   (e   t w o   t h r e e)

(defn ellipsize
  [text]
  (let [[w1 w2 w3] (clojure.string/split text #"\s+")]
    (clojure.string/join " " [w1 w2 w3 "..."])))


(ellipsize "The Quick Brown fox jumps over the lazy dog")
;; => "The Quick Brown ..."
