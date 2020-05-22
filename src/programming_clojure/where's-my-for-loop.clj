;; implement indexOfAny(String str, char[] search-chars)

;; Clojure’s for is not a loop but a sequence comprehension

(map #(inc %) 
     [1 2 3 4 5])
;; => (2 3 4 5 6)

(for[n [1 2 3 4 5]]
 (inc n))
;; => (2 3 4 5 6)


(filter even?
        [1 2 3 4 5])
;; => (2 4)

(for [n [1 2 3 4 5] :when (even? n)]
  n)
;; => (2 4)

;; Clojure sets are functions that test membership in the set.
(#{1 2 4} 2)
;; => 2

(#{\a \b \c} \c)
;; => \c

(#{\a \b \c} \e)
;; => nil

({:a 1 :b 2 3 \c} :a)
;; => 1

([1 2 3] 4)
;; => Execution error (IndexOutOfBoundsException) at user/eval41420 (form-init8789473202077049885.clj:26).
;;    null




(defn indexed 
  [coll]
  (map-indexed (fn[idx elt]
                 [idx elt])
               coll))
(defn index-filter
  [pred coll]
  (for[[idx elt] (indexed coll) :when (pred elt)]
   idx
   ))

(index-filter #{\z \a} "zzabyabc")
;; => (0 1 2 5)

(defn index-of-any
  [pred coll]
  (first (index-filter pred coll)))

(index-of-any #{\z \a} "zzabyabc")
;; => 0

(index-of-any #{\z \a} "bybcd")
;; => nil


(index-of-any #{\z \a} "")
;; => nil

(index-of-any #{\z \a} nil)
;; => nil


;; heads index
(index-filter #{:h} [:h :t :t :t :h :t :t :h])
;; => (0 4 7)

;; 3rd head index
(nth 
 (index-filter #{:h} [:h :t :t :t :h :t :t :h])
 2)
;; => 7

;; how many heads
(count 
 (index-filter #{:h} [:h :t :t :t :h :t :t :h]))
;; => 3


(index-filter even? [1 1 2 2 5 5 6 2])
;; => (2 3 6 7)
