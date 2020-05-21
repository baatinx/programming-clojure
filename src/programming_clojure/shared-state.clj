(def visitors (atom #{}))

(defn add-visitor
  [username]
  (swap! visitors conj username)
  (str "Hello, " username))

@visitors
;; => #{}

(add-visitor "Mustafa Basit")
;; => "Hello, Mustafa Basit"

(add-visitor "Seerat")
;; => "Hello, Seerat"

;; current state
@visitors
;; => #{"Mustafa Basit" "Seerat"}

(add-visitor "Zakir Peer")
;; => "Hello, Zakir Peer"

;; current state
@visitors
;; => #{"Zakir Peer" "Mustafa Basit" "Seerat"}

;; ***
;; problems with state
;; results will vary, depending on when things happened

