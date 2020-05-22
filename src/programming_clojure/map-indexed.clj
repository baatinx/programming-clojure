(map-indexed (fn[idx itm] 
               [idx itm]) 
             "Hello World")
;; => ([0 \H] [1 \e] [2 \l] [3 \l] [4 \o] [5 \space] [6 \W] [7 \o] [8 \r] [9 \l] [10 \d])

(map-indexed hash-map
             "Hello World")
;; => ({0 \H} {1 \e} {2 \l} {3 \l} {4 \o} {5 \space} {6 \W} {7 \o} {8 \r} {9 \l} {10 \d})


(map-indexed vector
             "Hello World")
;; => ([0 \H] [1 \e] [2 \l] [3 \l] [4 \o] [5 \space] [6 \W] [7 \o] [8 \r] [9 \l] [10 \d])


(map-indexed list
             "Hello World")
;; => ((0 \H) (1 \e) (2 \l) (3 \l) (4 \o) (5 \space) (6 \W) (7 \o) (8 \r) (9 \l) (10 \d))

