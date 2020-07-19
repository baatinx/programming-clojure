(. "Basit" toUpperCase)
;; => "BASIT"

(.toUpperCase "Basit")
;; => "BASIT"

(. "abc" equals "def")
;; => false

(.equals "abc" "def")
;; => false


(macroexpand '(.equals "abc" "def"))
;; => (. "abc" equals "def")

(.equals (.toUpperCase "abc") "ABC")
;; => true

(. (. "abc" toUpperCase) equals "ABC") 
;; => true

(.. "abc" (toUpperCase) (equals "ABC"))
;; => true

(macroexpand '(.. "abc" (toUpperCase) (equals "ABC")))
(. (. "abc" (toUpperCase)) (equals "ABC"))

(-> "ABC"
    (.toUpperCase)
    (.equals "ABC"))

(.. System (getProperties) (get "os.name"))
;; => "Linux"