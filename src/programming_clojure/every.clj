(defn blank?
  [str]
  (every? #(Character/isWhitespace %) str))
;; => #'user/blank?

(blank? "fooo")
;; => false

(blank? nil)
(blank? "    ")
;; => true