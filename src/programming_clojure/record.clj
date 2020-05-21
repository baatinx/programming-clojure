(defrecord Employee [id name salary])
;; => user.Employee

(def foo (new Employee 1 "Danish" 200000))
;; => #'user/foo
foo

(def foo (Employee. 1 "Danish" 200000))
;; => #'user/foo
foo


(def foo (->Employee 1 "Danish" 200000))
;; => #'user/foo
foo

(:name foo)
;; => "Danish"


(macroexpand '(defrecord Employee [id name salary]))
;; => (let*
;;     []
;;     (clojure.core/declare ->Employee)
;;     (clojure.core/declare map->Employee)
;;     (deftype*
;;      user/Employee
;;      user.Employee
;;      [id name salary __meta __extmap __hash __hasheq]
;;      :implements
;;      [clojure.lang.IRecord
;;       clojure.lang.IHashEq
;;       clojure.lang.IObj
;;       clojure.lang.ILookup
;;       clojure.lang.IKeywordLookup
;;       clojure.lang.IPersistentMap
;;       java.util.Map
;;       java.io.Serializable]
;;      (clojure.core/entrySet [this__7800__auto__] (clojure.core/set this__7800__auto__))
;;      (clojure.core/values [this__7799__auto__] (clojure.core/vals this__7799__auto__))
;;      (clojure.core/keySet [this__7798__auto__] (clojure.core/set (clojure.core/keys this__7798__auto__)))
;;      (clojure.core/clear [this__7797__auto__] (throw (java.lang.UnsupportedOperationException.)))
;;      (clojure.core/putAll [this__7795__auto__ m__7796__auto__] (throw (java.lang.UnsupportedOperationException.)))
;;      (clojure.core/remove [this__7793__auto__ k__7794__auto__] (throw (java.lang.UnsupportedOperationException.)))
;;      (clojure.core/put
;;       [this__7790__auto__ k__7791__auto__ v__7792__auto__]
;;       (throw (java.lang.UnsupportedOperationException.)))
;;      (clojure.core/get [this__7788__auto__ k__7789__auto__] (.valAt this__7788__auto__ k__7789__auto__))
;;      (clojure.core/containsValue
;;       [this__7786__auto__ v__7787__auto__]
;;       (clojure.core/boolean (clojure.core/some #{v__7787__auto__} (clojure.core/vals this__7786__auto__))))
;;      (clojure.core/isEmpty [this__7785__auto__] (clojure.core/= 0 (.count this__7785__auto__)))
;;      (clojure.core/size [this__7784__auto__] (.count this__7784__auto__))
;;      (clojure.core/without
;;       [this__7782__auto__ k__7783__auto__]
;;       (if
;;        (clojure.core/contains? #{:name :id :salary} k__7783__auto__)
;;        (clojure.core/dissoc (clojure.core/with-meta (clojure.core/into {} this__7782__auto__) __meta) k__7783__auto__)
;;        (new Employee id name salary __meta (clojure.core/not-empty (clojure.core/dissoc __extmap k__7783__auto__)))))
;;      (clojure.core/assoc
;;       [this__7780__auto__ k__7781__auto__ G__17524]
;;       (clojure.core/condp
;;        clojure.core/identical?
;;        k__7781__auto__
;;        :id
;;        (new Employee G__17524 name salary __meta __extmap)
;;        :name
;;        (new Employee id G__17524 salary __meta __extmap)
;;        :salary
;;        (new Employee id name G__17524 __meta __extmap)
;;        (new Employee id name salary __meta (clojure.core/assoc __extmap k__7781__auto__ G__17524))))
;;      (clojure.core/iterator
;;       [G__17524]
;;       (clojure.lang.RecordIterator. G__17524 [:id :name :salary] (clojure.lang.RT/iter __extmap)))
;;      (clojure.core/seq
;;       [this__7779__auto__]
;;       (clojure.core/seq
;;        (clojure.core/concat
;;         [(clojure.lang.MapEntry/create :id id)
;;          (clojure.lang.MapEntry/create :name name)
;;          (clojure.lang.MapEntry/create :salary salary)]
;;         __extmap)))
;;      (clojure.core/entryAt
;;       [this__7775__auto__ k__7776__auto__]
;;       (clojure.core/let
;;        [v__7777__auto__ (.valAt this__7775__auto__ k__7776__auto__ this__7775__auto__)]
;;        (clojure.core/when-not
;;         (clojure.core/identical? this__7775__auto__ v__7777__auto__)
;;         (clojure.lang.MapEntry/create k__7776__auto__ v__7777__auto__))))
;;      (clojure.core/containsKey
;;       [this__7773__auto__ k__7774__auto__]
;;       (clojure.core/not
;;        (clojure.core/identical? this__7773__auto__ (.valAt this__7773__auto__ k__7774__auto__ this__7773__auto__))))
;;      (clojure.core/equiv
;;       [this__7772__auto__ G__17524]
;;       (clojure.core/boolean
;;        (clojure.core/or
;;         (clojure.core/identical? this__7772__auto__ G__17524)
;;         (clojure.core/when
;;          (clojure.core/identical? (clojure.core/class this__7772__auto__) (clojure.core/class G__17524))
;;          (clojure.core/let
;;           [G__17524 G__17524]
;;           (clojure.core/and
;;            (clojure.core/= id (. G__17524 -id))
;;            (clojure.core/= name (. G__17524 -name))
;;            (clojure.core/= salary (. G__17524 -salary))
;;            (clojure.core/= __extmap (. G__17524 __extmap))))))))
;;      (clojure.core/cons
;;       [this__7770__auto__ e__7771__auto__]
;;       (#'clojure.core/imap-cons this__7770__auto__ e__7771__auto__))
;;      (clojure.core/empty
;;       [this__7769__auto__]
;;       (throw (java.lang.UnsupportedOperationException. (clojure.core/str "Can't create empty: " "user.Employee"))))
;;      (clojure.core/count [this__7768__auto__] (clojure.core/+ 3 (clojure.core/count __extmap)))
;;      (clojure.core/getLookupThunk
;;       [this__7766__auto__ k__7767__auto__]
;;       (clojure.core/let
;;        [gclass (clojure.core/class this__7766__auto__)]
;;        (clojure.core/case
;;         k__7767__auto__
;;         :id
;;         (clojure.core/reify
;;          clojure.lang.ILookupThunk
;;          (clojure.core/get
;;           [thunk gtarget]
;;           (if (clojure.core/identical? (clojure.core/class gtarget) gclass) (. gtarget -id) thunk)))
;;         :name
;;         (clojure.core/reify
;;          clojure.lang.ILookupThunk
;;          (clojure.core/get
;;           [thunk gtarget]
;;           (if (clojure.core/identical? (clojure.core/class gtarget) gclass) (. gtarget -name) thunk)))
;;         :salary
;;         (clojure.core/reify
;;          clojure.lang.ILookupThunk
;;          (clojure.core/get
;;           [thunk gtarget]
;;           (if (clojure.core/identical? (clojure.core/class gtarget) gclass) (. gtarget -salary) thunk)))
;;         nil)))
;;      (clojure.core/valAt
;;       [this__7763__auto__ k__7764__auto__ else__7765__auto__]
;;       (clojure.core/case
;;        k__7764__auto__
;;        :id
;;        id
;;        :name
;;        name
;;        :salary
;;        salary
;;        (clojure.core/get __extmap k__7764__auto__ else__7765__auto__)))
;;      (clojure.core/valAt [this__7761__auto__ k__7762__auto__] (.valAt this__7761__auto__ k__7762__auto__ nil))
;;      (clojure.core/withMeta [this__7760__auto__ G__17524] (new Employee id name salary G__17524 __extmap __hash __hasheq))
;;      (clojure.core/meta [this__7759__auto__] __meta)
;;      (clojure.core/equals
;;       [this__7758__auto__ G__17524]
;;       (clojure.lang.APersistentMap/mapEquals this__7758__auto__ G__17524))
;;      (clojure.core/hashCode
;;       [this__7755__auto__]
;;       (clojure.core/let
;;        [hash__7756__auto__ __hash]
;;        (if
;;         (clojure.core/zero? hash__7756__auto__)
;;         (clojure.core/let
;;          [h__7757__auto__ (clojure.lang.APersistentMap/mapHash this__7755__auto__)]
;;          (set! __hash h__7757__auto__)
;;          h__7757__auto__)
;;         hash__7756__auto__)))
;;      (clojure.core/hasheq
;;       [this__7752__auto__]
;;       (clojure.core/let
;;        [hq__7753__auto__ __hasheq]
;;        (if
;;         (clojure.core/zero? hq__7753__auto__)
;;         (clojure.core/let
;;          [h__7754__auto__
;;           (clojure.core/int (clojure.core/bit-xor -760821649 (clojure.lang.APersistentMap/mapHasheq this__7752__auto__)))]
;;          (set! __hasheq h__7754__auto__)
;;          h__7754__auto__)
;;         hq__7753__auto__))))
;;     (clojure.core/import user.Employee)
;;     (clojure.core/defn
;;      ->Employee
;;      "Positional factory function for class user.Employee."
;;      [id name salary]
;;      (new user.Employee id name salary))
;;     (clojure.core/defn
;;      map->Employee
;;      "Factory function for class user.Employee, taking a map of keywords to field values."
;;      ([m__7873__auto__]
;;       (user.Employee/create
;;        (if
;;         (clojure.core/instance? clojure.lang.MapEquivalence m__7873__auto__)
;;         m__7873__auto__
;;         (clojure.core/into {} m__7873__auto__)))))
;;     user.Employee)



