(peek '(1 2 3))
;; => 1

(peek [1 2 3])
;; => 3

(pop '(1 2 3))

;; pop is not the same as rest
(pop '())
;; => Execution error (IllegalStateException) at user/eval15653 (form-init18263986822352242683.clj:5).
;;    Can't pop empty list

(rest '())
;; => ()


;; functions on vectors
(get [1 2 3] 0)
;; => 1

(get [1 2 3] 5)
;; => nil

;; Vectors are themselves functions

([1 2 3] 0)
;; => 1

([1 2 3] 5)
;; => Execution error (IndexOutOfBoundsException) at user/eval16088 (form-init18263986822352242683.clj:29).
;;    null
(get [1 2 3] 5)
;; => nil


(assoc [1 2 3] 0 0)
;; => [0 2 3]

(assoc [1 2 3] 5 0)
;; => Execution error (IndexOutOfBoundsException) at user/eval16316 (form-init18263986822352242683.clj:40).
;;    null

(subvec [1 2 3 4 5] 1 3)
;; => [2 3]

(take 2 (drop 1 [1 2 3 4 5]))
;; => (2 3)

(subvec [1 2 3 4 5] 2 )
;; => [3 4 5]

(subvec [1 2 3 4] 6 9)
;; => Execution error (IndexOutOfBoundsException) at user/eval16512 (form-init18263986822352242683.clj:51).
;;    null


;; functions on maps
(keys {:fname "Mustafa" :lname "Basit"})
;; => (:fname :lname)

(vals {:fname "Mustafa" :lname "Basit"})
;; => ("Mustafa" "Basit")

;; Note that while maps are unordered, both keys and vals are guaranteed to return the
;; keys and values in the same order as a seq on the map


(get {:fname "Mustafa" :lname "Basit"} :fname "no-name")

;; Maps are functions of their keys
({:fname "Mustafa" :lname "Basit"} :fname)
;; => "Mustafa"

;; Keywords are also functions
(:fname {:fname "Mustafa" :lname "Basit"})
;; => "Mustafa"

;; *** 
;; If you look up a key in a map and get nil back, you can’t tell whether the key was
;; missing from the map or present with a value of nil. The contains? function solves this
;; problem by testing for the mere presence of a key

(:fname {:fname nil :lname nil})
;; => nil

(:id {:fname nil :lname nil})
;; => nil

(contains? {:fname nil :lname nil} :fname)
;; => true

(contains? {:fname nil :lname nil} :id)
;; => false

(get {:fname nil :lname nil} :fname :no-data-found)
;; => nil

(get {:fname nil :lname nil} :id :no-data-found)
;; => :no-data-found

;; The default return value of :no-data-found makes it possible to distinguish that
;; :id is not in the map, while :fname is present with a value of nil.



;; building new maps:
(def song {:name "Saki Saki"
            :artist "Neha Kakar"
            :album "Mix-Mashup"
            :genre "Classical"})

(assoc song :kind "MPEG Audio File")
;; => {:name "Saki Saki", :artist "Neha Kakar", :album "Mix-Mashup", :genre "Classical", :kind "MPEG Audio File"}

(dissoc song :genre)
;; => {:name "Saki Saki", :artist "Neha Kakar", :album "Mix-Mashup"}


(select-keys song [:name :artist])
;; => {:name "Saki Saki", :artist "Neha Kakar"}


(merge song {:size  811839 :time 502939 :genre "Remix"})
;; => {:name "Saki Saki", :artist "Neha Kakar", :album "Mix-Mashup", :genre "Remix", :size 811839, :time 502939}

(concat [1 3] [1 3])
;; => (1 3 1 3)

(merge-with concat
            {:rubble ["Barney"] :flintstone ["Fred"]}
            {:rubble ["Betty"] :flintstone ["Wilma"]}
            {:rubble ["Bam-Bam"] :flintstone ["Pebbles"]})
;; => {:rubble ("Barney" "Betty" "Bam-Bam"), :flintstone ("Fred" "Wilma" "Pebbles")}
;; see clojure-for-the-brave-and-true repo. for merge-with




;; Functions on Sets 
;; In addition to the set functions in the clojure.core namespace, Clojure provides a
;; group of functions in the clojure.set namespace.

;; operations from set theory:
(def languages #{"java" "c" "d" "clojure"})
(def beverages #{"java" "chai" "pop"})


(clojure.set/union languages beverages)
;; => #{"d" "clojure" "pop" "java" "chai" "c"}

(clojure.set/difference languages beverages)
;; => #{"d" "clojure" "c"}
;; which is in A and not in B set

(clojure.set/difference beverages languages)
;; => #{"pop" "chai"}

(clojure.set/select #(= (count %) 1) languages)
;; => #{"d" "c"}




;; in-memory database of musical compositions
(def compositions
  #{{:name "The Art of the Fugue" :composer "J. S. Bach"}
    {:name "Musical Offering" :composer "J. S. Bach"}
    {:name "Requiem" :composer "Giuseppe Verdi"}
    {:name "Requiem" :composer "W. A. Mozart"}})

(def composers
  #{{:composer "J. S. Bach" :country "Germany"}
    {:composer "W. A. Mozart" :country "Austria"}
    {:composer "Giuseppe Verdi" :country "Italy"}})

(def nations
  #{{:nation "Germany" :language "German"}
    {:nation "Austria" :language "German"}
    {:nation "Italy" :language "Italian"}})

(require '[clojure.set :refer :all])
;; => nil

(rename compositions {:name :title})
;; => #{{:composer "Giuseppe Verdi", :title "Requiem"}
;;      {:composer "W. A. Mozart", :title "Requiem"}
;;      {:composer "J. S. Bach", :title "The Art of the Fugue"}
;;      {:composer "J. S. Bach", :title "Musical Offering"}}

;; The select function returns maps, for which a predicate is true, and is analogous to the
;; WHERE portion of a SQL SELECT
(select #(= (:name %) "Requiem") compositions)
;; => #{{:name "Requiem", :composer "Giuseppe Verdi"} 
;;      {:name "Requiem", :composer "W. A. Mozart"}}

;; select name from compositions
(project compositions [:name])
;; => #{{:name "The Art of the Fugue"} 
;;      {:name "Musical Offering"} 
;;      {:name "Requiem"}}

(join compositions composers)
;; => #{{:composer "W. A. Mozart", :country "Austria", :name "Requiem"}
;;      {:composer "J. S. Bach", :country "Germany", :name "Musical Offering"}
;;      {:composer "Giuseppe Verdi", :country "Italy", :name "Requiem"}
;;      {:composer "J. S. Bach", :country "Germany", :name "The Art of the Fugue"}}

(join composers nations {:country :nation})
;; => #{{:composer "W. A. Mozart", :country "Austria", :nation "Austria", :language "German"}
;;      {:composer "J. S. Bach", :country "Germany", :nation "Germany", :language "German"}
;;      {:composer "Giuseppe Verdi", :country "Italy", :nation "Italy", :language "Italian"}}


(project 
 (join 
  (select #(= (:name %) "Requiem") compositions)
  composers)
 [:country])
;; => #{{:country "Italy"} {:country "Austria"}}
