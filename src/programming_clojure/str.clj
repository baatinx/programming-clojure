"This is a \nmultiline string"
;; => "This is a \nmultiline string"

"This is also
 a multiline string"
;; => "This is also\n a multiline string"

(println "This is a \n multiline string")
;; => This is a
;;    multiline string


;; Any nils passed to str are ignored
(str 1 2 nil 3)
;; => "123"

;; Characters

(str \H \e \y \space \Y \o \u)
;; => "Hey You"

