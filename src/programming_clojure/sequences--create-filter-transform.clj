;; seq. three core capabilities
;; first, rest, cons

(first ())
(first nil)
;; => nil

(rest ())
(rest nil)
;; => ()

(cons 0 [1 2 3])
(cons 0 '(1 2 3))
;; => (0 1 2 3)

;; For convenience, you might say that cons adds an element to a sequence, but
;; it’s more accurate to say that cons constructs a new sequence, which is like the original
;; sequence but with one element added.

(seq '())
(seq nil)
;; => nil

;; The next function will return the seq of items after the first:
(next '(1 2 3 4))

(seq (rest '(1 2 3 4)))

(rest [1 2 3 4])
;; => (2 3 4)

(vector? (rest [1 2 3 4 5]))
(list? (rest [1 2 3 4 5]))
;; => false

(seq? (rest [1 2 3 4 5]))
;; => true

(cons 0 [1 2 3])
;; => (0 1 2 3)

(seq? (cons 0 [1 2 3]))
;; => true

(class [1 2 3 4])
(type [1 2 3 4])
;; => clojure.lang.PersistentVector

(class (rest [1 2 3 4]))
(type (rest [1 2 3 4]))
;; => clojure.lang.PersistentVector$ChunkedSeq


;; cons returns a sequence
(cons [:fname "Mustafa"] {:lname "Basit"})
;; => ([:fname "Mustafa"] [:lname "Basit"])



#{:the :quick :brown :for}
;; => #{:the :for :quick :brown}

(sorted-set :the :quick :brown :fox)
;; => #{:brown :fox :quick :the}


(sorted-map :c 3 :a 1 :b 2)
;; => {:a 1, :b 2, :c 3}



;; ***
;; Both conj and into add items at an efficient insertion spot for the underlying data structure
;; (conj coll element & elements)
(conj '(1 2 3) :a)
;; => (:a 1 2 3)

(conj '(1 2 3) :a :b)
;; => (:b :a 1 2 3)

;; (into to-coll from-coll)
(into '(1 2 3) [:a])
;; => (:a 1 2 3)

(into '(1 2 3) '(:a :b))
;; => (:b :a 1 2 3)


(conj [1 2 3] :a)
;; => [1 2 3 :a]

(into [1 2 3] [:a])
;; => [1 2 3 :a]


;;***
;; Why Do Functions on Vectors Return Lists?
;; When you try examples at the REPL, the results of rest and cons appear to be lists, even
;; when the inputs are vectors, maps, or sets. Does this mean that Clojure is converting
;; everything to a list internally? No! The sequence functions always return a seq regardless
;; of their inputs. You can verify this by using the seq? predicate:

;; Sequences are logical lists (but not concrete lists)

;; sequence library
;; seq. lib. functions are grouped into four broad categories:
;; 1. Functions that create sequences
;; 2. Functions that filter sequences
;; 3. Sequence predicates
;; 4. Functions that transform sequences

;; Some of the sequence functions both filter and transform

;; 1. Functions that create sequences

(range 5)
;; => (0 1 2 3 4)

(range 5 15 2)
;; => (5 7 9 11 13)

(range 0 -1 -0.25)
;; => (0 -0.25 -0.5 -0.75)

;; ???
(range 1/2 4 1)
;; => (1/2 3/2 5/2 7/2)

(repeat 5 "foo")
;; => ("foo" "foo" "foo" "foo" "foo")

(take 10 (iterate inc 1))
;; => (1 2 3 4 5 6 7 8 9 10)

(take 10 (iterate #(+ 25 %) 0))
;; => (0 25 50 75 100 125 150 175 200 225)

(take 10 (cycle '(1 2 3)))
;; => (1 2 3 1 2 3 1 2 3 1)

(take 10 (cycle (range 1 4)))
;; => (1 2 3 1 2 3 1 2 3 1)

(def whole-numbers (iterate inc 1))
;; => #'user/whole-numbers

(interleave whole-numbers ["A" "B" "C" "D"])
;; => (1 "A" 2 "B" 3 "C" 4 "D")

(interleave [:a :b :c :d] [1 2 3 4 5 6 7 8 9] ["A" "B" "C" "D" "E" "F"])
;; => (:a 1 "A" :b 2 "B" :c 3 "C" :d 4 "D")

;; Closely related to interleave is interpose, which returns a sequence with each of the
;; elements of the input collection segregated by a separator

(interpose "," ["Apples" "Grapes" "Oranges"])
;; => ("Apples" "," "Grapes" "," "Oranges")

(apply str (interpose "," ["Apples" "Grapes" "Oranges"]))
;; => "Apples,Grapes,Oranges"
;; Clojure provides a performance-optimized version as clojure.string/join:

(clojure.string/join \, ["Apples" "Grapes" "Oranges"])
;; => "Apples,Grapes,Oranges"

(list 1 2 3)
(vector 1 2 3)
(hash-set 1 2 4)
(hash-map :a 1 :b 2)

;; set expects a collection as its first argument
(set [1 2 3 4])
;; => #{1 4 3 2}

(vec [1 2 3])
;; => [1 2 3]

(def vowel? #{\a \e \i \o \u})
(def consonant? (complement vowel?))

(vowel? \a)
;; => \a

(consonant? \a)
;; => false

(vowel? \t)
;; => nil

(consonant? \t)
;; => true


;; Filtering sequences
(take-while consonant? "The quick brown fox")
;; => (\T \h)

(drop-while consonant? "The quick brown fox")
;; => (\e \space \q \u \i \c \k \space \b \r \o \w \n \space \f \o \x)


;; (split-at index coll)
;; (split-with pred coll)

(split-at 3 "The quick brown fox")
;; => [(\T \h \e) (\space \q \u \i \c \k \space \b \r \o \w \n \space \f \o \x)]

(split-at 5 (range 8))
;; => [(0 1 2 3 4) (5 6 7)]

(split-with even? (range 1 20))
;; => [() (1 2 3 4 5 6 7 8 9 10 11 12 13 14 15 16 17 18 19)]

(split-with #(<= % 10) (range 1 20))
;; => [(1 2 3 4 5 6 7 8 9 10) (11 12 13 14 15 16 17 18 19)]



;; Sequence Predicates
;; (every? pred coll)

(every? even? [1 2 3 4])
;; => false

(every? even? [])
(every? even? nil)
;; => true


(some even? [1 2 3 4])
;; => true

(some even? [3 3 3 ])
;; => nil

(some even? [])
(some even? nil)
;; => nil

;; ***
;;some is not a predicate, although it’s often used like one. 
;;some returns the actual value of the first match instead of true.

(some identity [nil false 1 2 3])
;; => 1

(some #{3} (range 1 20))
;; => 3

(every? even? [1 2 3 4 5])
;; => false

(not-every? even? [1 2 3 4 5])
;; => true

(not-any? even? [1 2 3 4 5])
;; => false

(not-any? even? [1 3 5 7 9])
;; => true


;; Transforming Sequences

(sort [1 24 5 16 76 ])
;; => (1 5 16 24 76)

(sort-by #(.toString %) [1 240 5 3 7])
;; => (1 240 3 5 7)

(sort >  [42 1 17 11])
;; => (42 17 11 1)

(sort-by :grade > [{:grade 79}
                   {:grade 80}
                   {:grade 92}])
;; => ({:grade 92} {:grade 80} {:grade 79})


(map inc [1 2 3 4 5])
;; => (2 3 4 5 6)

(for [itm [1 2 3 4 5]]
  (inc itm))
;; => (2 3 4 5 6)

(filter even? [1 2 3 4 5])
;; => (2 4)

(for [itm [1 2 3 4 5] :when (even? itm)]
  itm)
;; => (2 4)

(for [itm [1 2 3 4 5] :when (#(= 0 (rem itm 2)))]
  itm)
;; => (2 4)

(for [itm [1 2 3 4 5] :when true]
  itm)
;; => (1 2 3 4 5)

(for [itm [1 2 3 4 5] :when (= itm 4)]
  itm)
;; => (4)


(for [itm [1 2 3 4 5] :while (even? itm)]
  itm)
;; => ()


(for [itm [2 4 2 8 3 4 5] :while (even? itm)]
  itm)
;; => (2 4 2 8)


;; The real power of for comes when you work with more than one binding expression

;; ***
;; rank iterates faster
(for [file "ABCDEFGH"
      rank (range 1 19)]
  (format "%c%d" file rank))
;; => ("A1"
;;     "A2"
;;     "A3"
;;      ........
;;     "H16"
;;     "H17"
;;     "H18")

;; file iterates faster
(for [rank (range 1 19)
      file "ABCDEFGH"]
  (format "%c%d" file rank))
;; => ("A1"
;;     "A2"
;;     "A3"
;;      ........
;;     "H16"
;;     "H17"
;;     "H18")