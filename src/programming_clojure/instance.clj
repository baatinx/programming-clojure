(instance? java.util.Collection [1 2 3])
;; => true

(instance? clojure.lang.PersistentVector [1 2 3])
;; => true

(type [])
;; => clojure.lang.PersistentVector

(class [])
;; => clojure.lang.PersistentVector



