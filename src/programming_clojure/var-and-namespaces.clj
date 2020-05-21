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

