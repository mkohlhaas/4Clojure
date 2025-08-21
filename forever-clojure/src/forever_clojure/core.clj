(ns forever-clojure.core
  (:require [clojure.java.javadoc :refer [javadoc]]
            [clojure.set]
            [clojure.string]))

;; levels of difficulty: elementary -> easy -> medium -> hard

;; 001 - Nothing but the Truth (elementary)

(= true true) ; true

;; 002 - Simple Math (elementary)

(= (- 10 (* 2 3)) 4) ; true

;; 003 - Strings (elementary)

(comment
  (javadoc ""))

(= "HELLO WORLD" (.toUpperCase "hello world")) ; true

;; 004 - Lists (elementary)

(= (list :a :b :c) '(:a :b :c)) ; true

;; 005 - conj on lists (elementary)

(= '(1 2 3 4) (conj '(2 3 4) 1)) ; true

(= '(1 2 3 4) (conj '(3 4) 2 1)) ; true

;; 006 - Vectors (elementary)

(= [:a :b :c] (list :a :b :c) (vec '(:a :b :c)) (vector :a :b :c)) ; true

;; 007 - conj on vectors (elementary)

(= [1 2 3 4] (conj [1 2 3] 4)) ; true

(= [1 2 3 4] (conj [1 2] 3 4)) ; true

;; 008 - Sets (elementary)

(= #{:a :b :c :d} (set '(:a :a :b :c :c :c :c :d :d))) ; true

(= #{:a :b :c :d} (clojure.set/union #{:a :b :c} #{:b :c :d})) ; true

;; 009 - conj on sets (elementary)

(= #{1 2 3 4} (conj #{1 4 3} 2)) ; true

;; 010 - Maps (elementary)

(= 20 ((hash-map :a 10, :b 20, :c 30) :b)) ; true

(= 20 (:b {:a 10, :b 20, :c 30})) ; true

;; 011 - conj on maps (elementary)

(= {:a 1, :b 2, :c 3} (conj {:a 1} {:b 2} [:c 3])) ; true

;; 012 - Sequences (elementary)

(= 3 (first '(3 2 1))) ; true

(= 3 (second [2 3 4])) ; true

(= 3 (last (list 1 2 3))) ; true

;; 013 - rest (elementary)

(=  [20 30 40] (rest [10 20 30 40])) ; true
(= '(20 30 40) (rest [10 20 30 40])) ; true

;; 014 - Functions (elementary)

(= 8 ((fn add-five [x] (+ x 5)) 3)) ; true
(= 8 ((fn [x] (+ x 5)) 3))          ; true
(= 8 (#(+ % 5) 3))                  ; true
(= 8 ((partial + 5) 3))             ; true

;; 015 - Double Down (elementary)

(= ((fn double-down [n] (* 2 n)) 2) 4) ; true
(= ((fn [n] (* 2 n)) 3) 6)             ; true
(= (#(* 2 %) 11) 22)                   ; true
(= ((partial * 2) 7) 14)               ; true

;; 016 - Hello World (elementary)

(= ((fn [name] (str "Hello, " name, "!")) "Dave") "Hello, Dave!")                   ; true
(= (#(str "Hello, " % "!") "Jenn") "Hello, Jenn!")                                  ; true
(= ((fn [name] (clojure.string/join name ["Hello, ", "!"])) "Rhea") "Hello, Rhea!") ; true

;; 017 - map (elementary)

(= '(6 7 8) (map #(+ % 5) '(1 2 3))) ; true

;; 018 - filter (elementary)

(= '(6 7) (filter #(> % 5) '(3 4 5 6 7))) ; true

;; 019 - Last Element (easy)

(= (reduce (fn [_ n] n) nil [1 2 3 4 5]) 5)     ; true
(= (reduce (fn [_ n] n) nil []) nil)            ; true
(= (reduce (fn [_ n] n) nil [1]) 1)             ; true
(= (reduce (fn [_ n] n) nil [1 2]) 2)           ; true
(= (reduce (fn [_ n] n) nil '(5 4 3)) 3)        ; true
(= (reduce (fn [_ n] n) nil ["b" "c" "d"]) "d") ; true

;; 020 - Penultimate Element (easy)

(= (#(first (last (partition 2 1 %))) (list 1 2 3 4 5)) 4)  ; true
(= (#(first (last (partition 2 1 %))) ["a" "b" "c"]) "b")   ; true
(= (#(first (last (partition 2 1 %))) [[1 2] [3 4]]) [1 2]) ; true

(= ((comp first (partial take-last 2)) (list 1 2 3 4 5)) 4)  ; true
(= ((comp first (partial take-last 2)) ["a" "b" "c"]) "b")   ; true
(= ((comp first (partial take-last 2)) [[1 2] [3 4]]) [1 2]) ; true

;; 021 - Nth Element (easy)

(= (#(first (drop %2 %1)) '(4 5 6 7) 2) 6)               ; true
(= (#(first (drop %2 %1)) [:a :b :c] 0) :a)              ; true
(= (#(first (drop %2 %1)) [1 2 3 4] 1) 2)                ; true
(= (#(first (drop %2 %1)) '([1 2] [3 4] [5 6]) 2) [5 6]) ; true

;; 022 - Count a Sequence (easy)

(= (#(reduce (fn [n _m] (inc n)) 0 %) '(1 2 3 3 1)) 5)        ; true
(= (#(reduce (fn [n _m] (inc n)) 0 %) "Hello World") 11)      ; true
(= (#(reduce (fn [n _m] (inc n)) 0 %) [[1 2] [3 4] [5 6]]) 3) ; true
(= (#(reduce (fn [n _m] (inc n)) 0 %) '(13)) 1)               ; true
(= (#(reduce (fn [n _m] (inc n)) 0 %) '(:a :b :c)) 3)         ; true

;; 023 - Reverse a Sequence (easy)

(= (#(reduce conj nil %) [1 2 3 4 5]) [5 4 3 2 1])                 ; true
(= (#(reduce conj nil %) (sorted-set 5 7 2 7)) '(7 5 2))           ; true
(= (#(reduce conj nil %) [[1 2] [3 4] [5 6]]) [[5 6] [3 4] [1 2]]) ; true

;; 024 - Sum It All Up (easy)

(= (#(apply + %) [1 2 3]) 6)          ; true
(= (#(apply + %) (list 0 -2 5 5)) 8)  ; true
(= (#(apply + %) #{4 2 1}) 7)         ; true
(= (#(apply + %) '(0 0 -1)) -1)       ; true
(= (#(apply + %) '(1 10 3)) 14)       ; true

(= (#(reduce + %) [1 2 3]) 6)         ; true
(= (#(reduce + %) (list 0 -2 5 5)) 8) ; true
(= (#(reduce + %) #{4 2 1}) 7)        ; true
(= (#(reduce + %) '(0 0 -1)) -1)      ; true
(= (#(reduce + %) '(1 10 3)) 14)      ; true

;; 025 - Find the odd numbers (easy)

(= (#(filter odd? %) #{1 2 3 4 5}) '(1 3 5))     ; true
(= (#(filter odd? %)  [4 2 1 6])    '(1))        ; true
(= (#(filter odd? %)  [2 2 4 6])    '())         ; true
(= (#(filter odd? %)  [1 1 1 3])    '(1 1 1 3))  ; true

; if `filter` is not allowed
(= (#(reduce (fn [n1 n2] (if (odd? n2) (conj n1 n2) n1)) [] %) #{1 2 3 4 5}) '(1 3 5))    ; true
(= (#(reduce (fn [n1 n2] (if (odd? n2) (conj n1 n2) n1)) [] %)  [4 2 1 6])    '(1))       ; true
(= (#(reduce (fn [n1 n2] (if (odd? n2) (conj n1 n2) n1)) [] %)  [2 2 4 6])    '())        ; true
(= (#(reduce (fn [n1 n2] (if (odd? n2) (conj n1 n2) n1)) [] %)  [1 1 1 3])    '(1 1 1 3)) ; true

;; 026 - Fibonacci Sequence (easy)

(defn fib [n] (->> [1 1]
                   (iterate (fn [[a b]] [b (+' a b)]))
                   (map first)
                   (take n)))

(= (fib 3) '(1 1 2))             ; true
(= (fib 6) '(1 1 2 3 5 8))       ; true
(= (fib 8) '(1 1 2 3 5 8 13 21)) ; true

;; 027 - Palindrome Detector (easy)

(defn palindrome? [coll] (= (reverse coll) (seq coll)))

(false? (palindrome? '(1 2 3 4 5)))      ; true
(true?  (palindrome?  "racecar"))        ; true
(true?  (palindrome?  [:foo :bar :foo])) ; true
(true?  (palindrome?  '(1 1 3 3 1 1)))   ; true
(false? (palindrome?  '(:a :b :c)))      ; true

;; 028 - Flatten a Sequence (easy)

;; (defn flatten-me [coll]
;;   (let [[fst & rest :as all] coll]
;;     (cond
;;       (empty? all)  (seq '())
;;       (coll? fst)   (concat (flatten-me fst) (flatten-me rest))
;;       :else         (conj (flatten-me rest) fst))))

(defn flatten-me [c]
  (if (sequential? c) ; `seq?` doesn't work
    (mapcat flatten-me c)
    [c]))

(= (flatten-me '((1 2) 3 [4 [5 6]])) '(1 2 3 4 5 6)) ; true
(= (flatten-me ["a" ["b"] "c"])      '("a" "b" "c")) ; true
(= (flatten-me '((((:a)))))          '(:a))          ; true

;; 029 - Get the Caps (easy)

;; instance method
(.toUpperCase "asldfj")    ; "ASLDFJ"

;; class/static method
(Character/isUpperCase \b) ; false
(Character/isUpperCase \B) ; true

;; solution with regex
;; (defn get-the-caps [str]
;;   (clojure.string/join (re-seq #"[A-Z]+" str)))

;; solution with a simple filter using Java Interop
(defn get-the-caps [str]
  (clojure.string/join (filter #(Character/isUpperCase %) str)))

(=      (get-the-caps "HeLlO, WoRlD!") "HLOWRD") ; true
(empty? (get-the-caps "nothing"))                ; true
(=      (get-the-caps "$#A(*&987Zf") "AZ")       ; true

;; 030 - Compress a Sequence (easy)

(= (apply str (dedupe "Leeeeeerrroyyy")) "Leroy")           ; true
(= (dedupe [1 1 2 3 3 2 2 3]) '(1 2 3 2 3))                 ; true
(= (dedupe [[1 2] [1 2] [3 4] [1 2]]) '([1 2] [3 4] [1 2])) ; true

;; if `dedupe` is not allowed
(->> "Leeeeeerrroyyy"
     (partition-by identity) ; ((\L) (\e \e \e \e \e \e) (\r \r \r) (\o) (\y \y \y))
     (map first))            ; (\L \e \r \o \y)

(->> [1 1 2 3 3 2 2 3]
     (partition-by identity) ; ((1 1) (2) (3 3) (2 2) (3))
     (map first))            ; (1 2 3 2 3)

(defn dedupe-me [col]
  (->> col
       (partition-by identity)
       (map first)))

(= (apply str (dedupe-me "Leeeeeerrroyyy")) "Leroy")           ; true
(= (dedupe-me [1 1 2 3 3 2 2 3]) '(1 2 3 2 3))                 ; true
(= (dedupe-me [[1 2] [1 2] [3 4] [1 2]]) '([1 2] [3 4] [1 2])) ; true

;; 031 - Pack a Sequence (easy)

(defn pack-a-sequence [coll]
  (partition-by identity coll))

(= (pack-a-sequence [1 1 2 1 1 1 3 3]) '((1 1) (2) (1 1 1) (3 3))) ; true
(= (pack-a-sequence [:a :a :b :b :c]) '((:a :a) (:b :b) (:c)))     ; true
(= (pack-a-sequence [[1 2] [1 2] [3 4]]) '(([1 2] [1 2]) ([3 4]))) ; true

;; 032 - Duplicate a Sequence (easy)

(defn duplicate-a-sequence [coll]
  (mapcat vector coll coll))

(= (duplicate-a-sequence [1 2 3]) '(1 1 2 2 3 3))                    ; true
(= (duplicate-a-sequence [:a :a :b :b]) '(:a :a :a :a :b :b :b :b)) ; true
(= (duplicate-a-sequence [[1 2] [3 4]]) '([1 2] [1 2] [3 4] [3 4])) ; true
(= (duplicate-a-sequence [44 33]) [44 44 33 33])                    ; true

;; 033 - Replicate a Sequence (easy)

(apply mapcat vector (repeat 2 [1 2 3])) ; (1 1 2 2 3 3)
(mapcat #(repeat 2 %) [1 2 3])           ; (1 1 2 2 3 3)

;; (defn replicate-a-sequence [coll n]
;;   (apply mapcat vector (repeat n coll)))

(defn replicate-a-sequence [coll n]
  (mapcat #(repeat n %1) coll))

(= (replicate-a-sequence [1 2 3] 2)       '(1 1 2 2 3 3))             ; true
(= (replicate-a-sequence [:a :b] 4)       '(:a :a :a :a :b :b :b :b)) ; true
(= (replicate-a-sequence [4 5 6] 1)       '(4 5 6))                   ; true
(= (replicate-a-sequence [[1 2] [3 4]] 2) '([1 2] [1 2] [3 4] [3 4])) ; true
(= (replicate-a-sequence [44 33] 2)       [44 44 33 33])              ; true

;; 034 - Implement range (easy)

(take (- 4 1) (iterate inc 1))   ; (1 2 3)
(take (- 2 -2) (iterate inc -2)) ; (-2 -1 0 1)
(take (- 8 5) (iterate inc 5))   ; (5 6 7)

(defn range-me [start stop]
  (take (- stop start) (iterate inc start)))

(= (range-me  1 4) '(1 2 3))     ; true
(= (range-me -2 2) '(-2 -1 0 1)) ; true
(= (range-me  5 8) '(5 6 7))     ; true

;; 035 - Local bindings (elementary)

(= 7 (let [x 5] (+ 2 x)))       ; true
(= 7 (let [x 3, y 10] (- y x))) ; true
(= 7 (let [x 21 y 3] (/ x y)))  ; true

;; 036 - Let it Be (elementary)

(= 10 (let [x 6, y 4] (+ x y))) ; true
(= 4 (let [y 1, z 3] (+ y z)))  ; true
(= 1 (let [z 1] z))             ; true

;; 037 - Regular Expressions (elementary)

(= "ABC" (apply str (re-seq #"[A-Z]+" "bA1B3Ce "))) ; true

;; 038 - Maximum value (easy)

(reduce #(if (> %1 %2) %1 %2) '(1 8 3 4)) ; 8

(defn maximum-value [& rest]
  (reduce #(if (> %1 %2) %1 %2) rest))

(= (maximum-value 1 8 3 4)   8) ; true
(= (maximum-value 30 20)    30) ; true
(= (maximum-value 45 67 11) 67) ; true

;; 039 - Interleave Two Seqs (easy)

(mapcat vector [1 2 3] [:a :b :c])

(defn interleave-two-seqs [coll1 coll2]
  (mapcat vector coll1 coll2))

(= (interleave-two-seqs [1 2 3] [:a :b :c]) '(1 :a 2 :b 3 :c)) ; true
(= (interleave-two-seqs [1 2] [3 4 5 6])    '(1 3 2 4))        ; true
(= (interleave-two-seqs [1 2 3 4] [5])       [1 5])            ; true
(= (interleave-two-seqs [30 20] [25 15])     [30 25 20 15])    ; true

;; 040 - Interpose a Seq (easy)

(let [val 0
      coll [1 2 3]]
  (butlast (interleave-two-seqs coll (repeat val))))
; (1 0 2 0 3)

(defn interpose-a-seq [val coll]
  (butlast (interleave coll (repeat val))))

(= (interpose-a-seq 0 [1 2 3])                              [1 0 2 0 3])            ; true
(= (apply str (interpose-a-seq ", " ["one" "two" "three"])) "one, two, three")      ; true
(= (interpose-a-seq :z [:a :b :c :d])                       [:a :z :b :z :c :z :d]) ; true

;; 041 - Drop Every Nth Item (easy)

(let [coll [1 2 3 4 5 6 7 8]
      n    3]
  (keep-indexed #(when (not= 0 (mod (inc %1) n)) %2) coll))

(mod 0 3) ; 0
(mod 1 3) ; 1
(mod 2 3) ; 2
(mod 3 3) ; 0
(mod 4 3) ; 1

(defn drop-every-nth-item [coll n]
  (keep-indexed #(when (not= 0 (mod (inc %1) n)) %2) coll))

(= (drop-every-nth-item [1 2 3 4 5 6 7 8] 3)   [1 2 4 5 7 8]) ; true
(= (drop-every-nth-item [:a :b :c :d :e :f] 2) [:a :c :e])    ; true
(= (drop-every-nth-item [1 2 3 4 5 6] 4)       [1 2 3 5 6])   ; true

;; 042 - Factorial Fun (easy)

(let [n 5]
  (apply * (range 1 (inc n))))

;; you can use `apply` or `reduce`
(defn factorial-fun [n]
  (apply * (range 1 (inc n))))

(= (factorial-fun 1) 1)     ; true
(= (factorial-fun 3) 6)     ; true
(= (factorial-fun 5) 120)   ; true
(= (factorial-fun 8) 40320) ; true

;; 043 - Reverse Interleave (medium)
;; 044 - Rotate Sequence (medium)
;; 045 - Intro to Iterate (easy)

(= '(1 4 7 10 13) (take 5 (iterate #(+ 3 %) 1))) ; true

;; 046 - Flipping out (medium)
;; 047 - Contain Yourself (easy)

(contains? #{4 5 6}     4)  ; true
(contains? [1 1 1 1 1]  0)  ; true (in a vector it's an index!!!)
(contains? {4 :a 2 :b}  4)  ; true
(not (contains? [1 2 4] 3)) ; true (in a vector it's an index!!!)

;; 048 - Intro to some (easy)

(= 6 (some #{2 7 6} [5 6 7 8]))            ; true
(= 6 (some #(when (even? %) %) [5 6 7 8])) ; true

;; 049 - Split a sequence (easy)

(defn split-a-sequence [n coll]
  [(take n coll) (drop n coll)])

(= (split-a-sequence 3 [1 2 3 4 5 6])       [[1 2 3] [4 5 6]])       ; true
(= (split-a-sequence 1 [:a :b :c :d])       [[:a] [:b :c :d]])       ; true
(= (split-a-sequence 2 [[1 2] [3 4] [5 6]]) [[[1 2] [3 4]] [[5 6]]]) ; true

;; 050 - Split by Type (medium)
;; 051 - Advanced Destructuring (easy)

(= [1 2 [3 4 5] [1 2 3 4 5]] (let [[a b & c :as d] [1 2 3 4 5]] [a b c d])) ; true

;; 052 - Intro to Destructuring (elementary)

(= [2 4] (let [[_a _b c _d e _f _g] (range)] [c e])) ; true

;; 053 - Longest Increasing SubSeq (hard)
;; 054 - Partition a Sequence (medium)
;; 055 - Count Occurences (medium)
;; 056 - Find Distinct Items (medium)
;; 057 - Simple Recursion (elementary)

(= '(5 4 3 2 1) ((fn foo [x] (when (> x 0) (conj (foo (dec x)) x))) 5)) ; true

;; 058 - Function Composition (medium)
;; 059 - Juxtaposition (medium)
;; 060 - Sequence Reductions (medium)
;; 061 - Map Construction (easy)

(into {} (map vector [:a :b :c] [1 2 3])) ; {:a 1, :b 2, :c 3}

(defn map-construction [keys values]
  (into {} (map vector keys values)))

(= (map-construction [:a :b :c]  [1 2 3])               {:a 1, :b 2, :c 3})            ; true
(= (map-construction [1 2 3 4]   ["one" "two" "three"]) {1 "one", 2 "two", 3 "three"}) ; true
(= (map-construction [:foo :bar] ["foo" "bar" "baz"])   {:foo "foo", :bar "bar"})      ; true

;; 062 - Reimplement Iteration (easy)

(take 5 (iterate #(* 2 %) 1)) ; (1 2 4 8 16)

(defn iterate-me [f n]
  (cons n (lazy-seq (iterate-me f (f n)))))

(= (take 5   (iterate-me #(* 2 %) 1))         [1 2 4 8 16])             ; true
(= (take 10  (iterate-me inc 0))              (take 10 (range)))        ; true
(= (take 9   (iterate-me #(inc (mod % 3)) 1)) (take 9 (cycle [1 2 3]))) ; true

;; 063 - Group a Sequence (easy)

(defn group-a-sequence [f coll]
  (reduce #(update-in %1 [(f %2)] concat [%2]) {} coll))

(= (group-a-sequence #(> % 5) #{1 3 6 8})                    {false [1 3], true [6 8]})                   ; true
(= (group-a-sequence #(apply / %) [[1 2] [2 4] [4 6] [3 6]]) {1/2 [[1 2] [2 4] [3 6]], 2/3 [[4 6]]})      ; true
(= (group-a-sequence count [[1] [1 2] [3] [1 2 3] [2 3]])    {1 [[1] [3]], 2 [[1 2] [2 3]], 3 [[1 2 3]]}) ; true

;; 064 - Intro to Reduce (elementary)

(= 15 (reduce + [1 2 3 4 5])) ; true
(=  0 (reduce + []))          ; true
(=  6 (reduce + 1 [2 3]))     ; true

;; 065 - Black Box Testing (medium)
;; 066 - Greatest Common Divisor (easy)

(defn gcd [a b]
  (if (zero? b)
    a
    (recur b (mod a b))))

(= (gcd 2 4)       2) ; true
(= (gcd 10 5)      5) ; true
(= (gcd 5 7)       1) ; true
(= (gcd 1023 858) 33) ; true

;; 067 - Prime Numbers (medium)
;; 068 - Recurring Theme (elementary)

(= [7 6 5 4 3]
   (loop [x      5
          result []]
     (if (> x 0)
       (recur (dec x) (conj result (+ 2 x)))
       result)))

;; 069 - Merge with a Function (medium)
;; 070 - Word Sorting (medium)
;; 071 - Rearranging Code: > (elementary)

(= (count (sort (rest (reverse [2 5 4 1 3 6]))))
   (-> [2 5 4 1 3 6] reverse rest sort count)
   5)
; true

;; 072 - Rearranging Code: >> (elementary)

(= (reduce + (map inc (take 3 (drop 2 [2 5 4 1 3 6]))))
   (->> [2 5 4 1 3 6] (drop 2) (take 3) (map inc) (reduce +))
   11)
; true

;; 073 - Analyze a TicTacToe Board (hard)
;; 074 - Filter Perfect Squares (medium)
;; 075 - Euler's Totient Function (medium)
;; 076 - Intro to Trampoline (medium)
;; 077 - Anagram Finder (medium)
;; 078 - Reimplement Trampoline (medium)
;; 079 - Triangle Minimal Path (hard)
;; 080 - Perfect Numbers (medium)
;; 081 - Set Intersection (easy)

(#{0 1 2 3} 0) ; 0
(#{0 1 2 3} 1) ; 1
(#{0 1 2 3} 2) ; 2
(#{0 1 2 3} 3) ; 3
(#{0 1 2 3} 4) ; nil
(#{0 1 2 3} 5) ; nil

(filter #{0 1 2 3} #{2 3 4 5})       ;  (3 2)
(set (filter #{0 1 2 3} #{2 3 4 5})) ; #{3 2}

(defn intersect-me [s1 s2]
  (set (filter s1 s2)))

;; (defn intersect-me [s1 s2]
;;   (reduce #(if (contains? s2 %2) (conj %1 %2) %1) #{} s1))

(= (intersect-me #{0 1 2 3} #{2 3 4 5})            #{2 3})      ; true
(= (intersect-me #{0 1 2} #{3 4 5})                #{})         ; true
(= (intersect-me #{:a :b :c :d} #{:c :e :a :f :d}) #{:a :c :d}) ; true

;; 082 - Word Chains (hard)
;; 083 - A HalfTruth (easy)

(defn half-truth [& rest]
  (and (not-every? true? rest) (not-every? false? rest)))

(= false (half-truth false false))          ; true
(= true  (half-truth true false))           ; true
(= false (half-truth true))                 ; true
(= true  (half-truth false true false))     ; true
(= false (half-truth true true true))       ; true
(= true  (half-truth true true true false)) ; true

;; 084 - Transitive Closure (hard)
;; 085 - Power Set (medium)
;; 086 - Happy numbers (medium)
;; 087 - Create an Equation
;; 088 - Symmetric Difference (easy)

(defn symmetric-difference [s1 s2]
  (clojure.set/union (clojure.set/difference s1 s2)
                     (clojure.set/difference s2 s1)))

;; (defn symmetric-difference [s1 s2]
;;   (into (set (remove s1 s2)) (remove s2 s1)))

(= (symmetric-difference #{1 2 3 4 5 6} #{1 3 5 7})     #{2 4 6 7})     ; true
(= (symmetric-difference #{:a :b :c} #{})               #{:a :b :c})    ; true
(= (symmetric-difference #{} #{4 5 6})                  #{4 5 6})       ; true
(= (symmetric-difference #{[1 2] [2 3]} #{[2 3] [3 4]}) #{[1 2] [3 4]}) ; true

;; 089 - Graph Tour (hard)
;; 090 - Cartesian Product (easy)

(defn cartesian-product [c1 c2]
  (set (for [c1 c1 c2 c2]
         [c1 c2])))

(= (cartesian-product #{"ace" "king" "queen"} #{"♠" "♥" "♦" "♣"})
   #{["ace"   "♠"] ["ace"   "♥"] ["ace"   "♦"] ["ace"   "♣"]
     ["king"  "♠"] ["king"  "♥"] ["king"  "♦"] ["king"  "♣"]
     ["queen" "♠"] ["queen" "♥"] ["queen" "♦"] ["queen" "♣"]})                  ; true
(= (cartesian-product #{1 2 3} #{4 5}) #{[1 4] [2 4] [3 4] [1 5] [2 5] [3 5]})  ; true
(= 300 (count (cartesian-product (into #{} (range 10)) (into #{} (range 30))))) ; true

;; 091 - Graph Connectivity (hard)
;; 092 - Read Roman numerals (hard)
;; 093 - Partially Flatten a Sequence (medium)
;; 094 - Game of Life (hard)
;; 095 - To Tree, or not to Tree (easy)

(defn tree? [tree]
  (or (nil? tree)
      (and (sequential? tree)
           (= 3 (count tree))
           (tree? (nth tree 1))
           (tree? (nth tree 2)))))

(= (tree? '(:a nil nil))                           true)  ; true
(= (tree? '(:a (:b nil nil) nil))                  true)  ; true
(= (tree? '(:a (:b nil nil)))                      false) ; true
(= (tree? [1 nil [2 [3 nil nil] [4 nil nil]]])     true)  ; true
(= (tree? [1 [2 nil nil] [3 nil nil] [4 nil nil]]) false) ; true
(= (tree? [1 [2 [3 [4 nil nil] nil] nil] nil])     true)  ; true
(= (tree? [1 [2 [3 [4 false nil] nil] nil] nil])   false) ; true
(= (tree? '(:a nil ()))                            false) ; true

;; 096 - Beauty is Symmetry (easy)

(defn symmetric-trees? [[val1 left1 right1] [val2 left2 right2]]
  (and (= val1 val2)
       (or (and (nil? left1) (nil? right2))
           (symmetric-trees? left1 right2))
       (or (and (nil? right1) (nil? left2))
           (symmetric-trees? right1 left2))))

(symmetric-trees?
 '(:b nil nil)
 '(:c nil nil))
; false

(symmetric-trees?
 '(:b nil nil)
 '(nil))
; false

(symmetric-trees?
 '(:b nil nil)
 '(:b nil nil))
; true

(symmetric-trees?
 [2 nil [3 [4 [5 nil nil] [6 nil nil]] nil]]
 [2 [3 nil [4 [6 nil nil] [5 nil nil]]] nil])
; true

(symmetric-trees?
 [2 nil [3 [4 [5 nil nil] [6 nil nil]] nil]]
 [2 [3 nil [4 [5 nil nil] [6 nil nil]]] nil])
; false

(symmetric-trees?
 [2 nil [3 [4 [5 nil nil] [6 nil nil]] nil]]
 [2 [3 nil [4 [6 nil nil] nil]] nil])
; false

(defn beauty-is-symmetry [[_val left right]]
  (letfn [(symmetric-trees? [[val1 left1 right1] [val2 left2 right2]]
            (and (= val1 val2)
                 (or (and (nil? left1) (nil? right2))
                     (symmetric-trees? left1 right2))
                 (or (and (nil? right1) (nil? left2))
                     (symmetric-trees? right1 left2))))])
  (symmetric-trees? left right))

(= (beauty-is-symmetry '(:a (:b nil nil) (:b nil nil)))                                                              true)  ; true
(= (beauty-is-symmetry '(:a (:b nil nil) nil))                                                                       false) ; true
(= (beauty-is-symmetry '(:a (:b nil nil) (:c nil nil)))                                                              false) ; true
(= (beauty-is-symmetry [1 [2 nil [3 [4 [5 nil nil] [6 nil nil]] nil]] [2 [3 nil [4 [6 nil nil] [5 nil nil]]] nil]])  true)  ; true
(= (beauty-is-symmetry [1 [2 nil [3 [4 [5 nil nil] [6 nil nil]] nil]] [2 [3 nil [4 [5 nil nil] [6 nil nil]]] nil]])  false) ; true
(= (beauty-is-symmetry [1 [2 nil [3 [4 [5 nil nil] [6 nil nil]] nil]] [2 [3 nil [4 [6 nil nil] nil]] nil]])          false) ; true

;; 097 - Pascal's Triangle (easy)

(defn pascal-triangle
  ([n] (pascal-triangle n [1]))
  ([n acc]
   (if (= n 1)
     acc
     (pascal-triangle (dec n)
                      (conj (vec (conj (map (partial apply +) (partition 2 1 acc)) 1)) 1)))))

(= (pascal-triangle 1) [1]) ; true
(= (map pascal-triangle (range 1 6))
   [[1]
    [1 1]
    [1 2 1]
    [1 3 3 1]
    [1 4 6 4 1]]) ; true
(= (pascal-triangle 11) [1 10 45 120 210 252 210 120 45 10 1]) ; true
(= (pascal-triangle 11) [1 10 45 120 210 252 210 120 45 10 1]) ; true

;; 098 - Equivalence Classes (medium)
;; 099 - Product Digits (easy)
;; 100 - Least Common Multiple (easy)
;; 101 - Levenshtein Distance (hard)
;; 102 - intoCamelCase (medium)
;; 103 - Generating kcombinations (medium)
;; 104 - Write Roman Numerals (medium)
;; 105 - Identify keys and values (medium)
;; 106 - Number Maze (hard)
;; 107 - Simple closures (easy)
;; 108 - Lazy Searching (medium)
;; 110 - Sequence of pronunciations (medium)
;; 111 - Crossword puzzle (hard)
;; 112 - Sequs Horribilis (medium)
;; 114 - Global takewhile (medium)
;; 115 - The Balance of N (medium)
;; 116 - Prime Sandwich (medium)
;; 117 - For Science! (hard)
;; 118 - Reimplement Map (easy)
;; 119 - Win at TicTacToe (hard)
;; 120 - Sum of square of digits (easy)
;; 121 - Universal Computation Engine (medium)
;; 122 - Read a binary number (easy)
;; 124 - Analyze Reversi (hard)
;; 125 - Gus' Quinundrum (hard)
;; 126 - Through the Looking Class (easy)
;; 127 - Love Triangle (hard)
;; 128 - Recognize Playing Cards (easy)
;; 131 - Sum Some Set Subsets (medium)
;; 132 - Intervals (medium)
;; 134 - A nil key (elementary)

(:a {:a nil :b 2})       ; nil
(:b {:a nil :b 2})       ; 2
(:c {:a nil :b 2})       ; nil
(:c {:a nil :b 2} false) ; false

(true?  (#(nil? (%1 %2 false)) :a {:a nil :b 2})) ; true
(false? (#(nil? (%1 %2 false)) :b {:a nil :b 2})) ; true
(false? (#(nil? (%1 %2 false)) :c {:a nil :b 2})) ; true

;; 135 - Infix Calculator (easy)
;; 137 - Digits and bases (medium)
;; 140 - Veitch, Please! (hard)
;; 141 - Tricky card games (medium)
;; 143 - dot product (easy)
;; 144 - Oscilrate (medium)
;; 145 - For the win (elementary)

(= '(1 5 9 13 17 21 25 29 33 37) (for [x (range 40)
                                       :when (= 1 (rem x 4))]
                                   x))
; true

(= '(1 5 9 13 17 21 25 29 33 37) (for [x (iterate #(+ 4 %) 0)
                                       :let [z (inc x)]
                                       :while (< z 40)]
                                   z))
; true

(= '(1 5 9 13 17 21 25 29 33 37) (for [[x y] (partition 2 (range 20))]
                                   (+ x y)))
; true

;; 146 - Trees into tables (easy)
;; 147 - Pascal's Trapezoid (easy)
;; 148 - The Big Divide (medium)
;; 150 - Palindromic Numbers (medium)
;; 153 - Pairwise Disjoint Sets (easy)
;; 156 - Map Defaults (elementary)

(zipmap [:a :b :c] (repeat 0)) ; {:a 0, :b 0, :c 0}

(= (#(zipmap %2 (repeat %1)) 0 [:a :b :c]) {:a 0 :b 0 :c 0})                   ; true
(= (#(zipmap %2 (repeat %1)) "x" [1 2 3]) {1 "x" 2 "x" 3 "x"})                 ; true
(= (#(zipmap %2 (repeat %1)) [:a :b] [:foo :bar]) {:foo [:a :b] :bar [:a :b]}) ; true

;; 157 - Indexing Sequences (easy)
;; 158 - Decurry (medium)
;; 161 - Subset and Superset (elementary)

(clojure.set/superset? #{2}   #{2})   ; true
(clojure.set/subset?   #{1}   #{1})   ; true
(clojure.set/superset? #{1 2} #{1 2}) ; true
(clojure.set/subset?   #{1 2} #{1 2}) ; true

;; 162 - Logical falsity and truth (elementary)

;; In Clojure, only `nil` and `false` represent the values of logical falsity in conditional tests - anything else is logical truth!!!

(= 1 (if-not false 1 0)) ; true
(= 1 (if-not nil 1 0))   ; true
(= 1 (if true 1 0))      ; true
(= 1 (if [] 1 0))        ; true
(= 1 (if [0] 1 0))       ; true
(= 1 (if 0 1 0))         ; true
(= 1 (if 1 1 0))         ; true

;; 166 - Comparisons (easy)
;; 168 - Infinite Matrix (medium)
;; 171 - Intervals (medium)
;; 173 - Intro to Destructuring 2 (easy)
;; 177 - Balancing Brackets (medium)
;; 195 - Parentheses... Again (medium)
