(ns forever-clojure.core
  (:require [clojure.java.javadoc :refer [javadoc]]
            [clojure.set]
            [clojure.string]
            [clojure.math :as m]
            [clojure.pprint]))

;; ;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; 001 - Nothing but the Truth (elementary)
;; ;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

(comment
  (= true true)) ; true

;; ;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; 002 - Simple Math (elementary)
;; ;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

(comment
  (= (- 10 (* 2 3)) 4)) ; true

;; ;;;;;;;;;;;;;;;;;;;;;;;;;;
;; 003 - Strings (elementary)
;; ;;;;;;;;;;;;;;;;;;;;;;;;;;

(comment
  (javadoc ""))

(comment
  (= "HELLO WORLD" (.toUpperCase "hello world"))) ; true

;; ;;;;;;;;;;;;;;;;;;;;;;;;
;; 004 - Lists (elementary)
;; ;;;;;;;;;;;;;;;;;;;;;;;;

(comment
  (= (list :a :b :c) '(:a :b :c))) ; true

;; ;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; 005 - conj on lists (elementary)
;; ;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

(comment
  (= '(1 2 3 4) (conj '(2 3 4) 1))  ; true
  (= '(1 2 3 4) (conj '(3 4) 2 1))) ; true

;; ;;;;;;;;;;;;;;;;;;;;;;;;;;
;; 006 - Vectors (elementary)
;; ;;;;;;;;;;;;;;;;;;;;;;;;;;

(comment
  (= [:a :b :c] (list :a :b :c) (vec '(:a :b :c)) (vector :a :b :c))) ; true

;; ;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; 007 - conj on vectors (elementary)
;; ;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

(comment
  (= [1 2 3 4] (conj [1 2 3] 4))  ; true
  (= [1 2 3 4] (conj [1 2] 3 4))) ; true

;; ;;;;;;;;;;;;;;;;;;;;;;;
;; 008 - Sets (elementary)
;; ;;;;;;;;;;;;;;;;;;;;;;;

(comment
  (= #{:a :b :c :d} (set '(:a :a :b :c :c :c :c :d :d)))          ; true
  (= #{:a :b :c :d} (clojure.set/union #{:a :b :c} #{:b :c :d}))) ; true

;; ;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; 009 - conj on sets (elementary)
;; ;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

(comment
  (= #{1 2 3 4} (conj #{1 4 3} 2))) ; true

;; ;;;;;;;;;;;;;;;;;;;;;;;
;; 010 - Maps (elementary)
;; ;;;;;;;;;;;;;;;;;;;;;;;

(comment
  (= 20 ((hash-map :a 10, :b 20, :c 30) :b)) ; true
  (= 20 (:b {:a 10, :b 20, :c 30})))         ; true

;; ;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; 011 - conj on maps (elementary)
;; ;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

(comment
  (= {:a 1, :b 2, :c 3} (conj {:a 1} {:b 2} [:c 3]))) ; true

;; ;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; 012 - Sequences (elementary)
;; ;;;;;;;;;;;;;;;;;;;;;;;;;;;;

(comment
  (= 3 (first '(3 2 1)))     ; true
  (= 3 (second [2 3 4]))     ; true
  (= 3 (last (list 1 2 3)))) ; true

;; ;;;;;;;;;;;;;;;;;;;;;;;
;; 013 - rest (elementary)
;; ;;;;;;;;;;;;;;;;;;;;;;;

(comment
  (=  [20 30 40] (rest [10 20 30 40]))  ; true
  (= '(20 30 40) (rest [10 20 30 40]))) ; true

;; ;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; 014 - Functions (elementary)
;; ;;;;;;;;;;;;;;;;;;;;;;;;;;;;

(comment
  (= 8 ((fn add-five [x] (+ x 5)) 3)) ; true
  (= 8 ((fn [x] (+ x 5)) 3))          ; true
  (= 8 (#(+ % 5) 3))                  ; true
  (= 8 ((partial + 5) 3)))            ; true

;; ;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; 015 - Double Down (elementary)
;; ;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

(comment
  (= ((fn double-down [n] (* 2 n)) 2) 4) ; true
  (= ((fn [n] (* 2 n)) 3) 6)             ; true
  (= (#(* 2 %) 11) 22)                   ; true
  (= ((partial * 2) 7) 14))              ; true

;; ;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; 016 - Hello World (elementary)
;; ;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

(comment
  (= ((fn [name] (str "Hello, " name, "!")) "Dave") "Hello, Dave!")                    ; true
  (= (#(str "Hello, " % "!") "Jenn") "Hello, Jenn!")                                   ; true
  (= ((fn [name] (clojure.string/join name ["Hello, ", "!"])) "Rhea") "Hello, Rhea!")) ; true

;; ;;;;;;;;;;;;;;;;;;;;;;
;; 017 - map (elementary)
;; ;;;;;;;;;;;;;;;;;;;;;;

(comment
  (= '(6 7 8) (map #(+ % 5) '(1 2 3)))) ; true

;; ;;;;;;;;;;;;;;;;;;;;;;;;;
;; 018 - filter (elementary)
;; ;;;;;;;;;;;;;;;;;;;;;;;;;

(comment
  (= '(6 7) (filter #(> % 5) '(3 4 5 6 7)))) ; true

;; ;;;;;;;;;;;;;;;;;;;;;;;;;
;; 019 - Last Element (easy)
;; ;;;;;;;;;;;;;;;;;;;;;;;;;

(comment
  (= (reduce (fn [_ n] n) nil [1 2 3 4 5]) 5)      ; true
  (= (reduce (fn [_ n] n) nil []) nil)             ; true
  (= (reduce (fn [_ n] n) nil [1]) 1)              ; true
  (= (reduce (fn [_ n] n) nil [1 2]) 2)            ; true
  (= (reduce (fn [_ n] n) nil '(5 4 3)) 3)         ; true
  (= (reduce (fn [_ n] n) nil ["b" "c" "d"]) "d")) ; true

;; ;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; 020 - Penultimate Element (easy)
;; ;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

(comment
  (= (#(first (last (partition 2 1 %))) (list 1 2 3 4 5)) 4)   ; true
  (= (#(first (last (partition 2 1 %))) ["a" "b" "c"]) "b")    ; true
  (= (#(first (last (partition 2 1 %))) [[1 2] [3 4]]) [1 2])) ; true

(comment
  (= ((comp first (partial take-last 2)) (list 1 2 3 4 5)) 4)   ; true
  (= ((comp first (partial take-last 2)) ["a" "b" "c"]) "b")    ; true
  (= ((comp first (partial take-last 2)) [[1 2] [3 4]]) [1 2])) ; true

;; ;;;;;;;;;;;;;;;;;;;;;;;;
;; 021 - Nth Element (easy)
;; ;;;;;;;;;;;;;;;;;;;;;;;;

(comment
  (= (#(first (drop %2 %1)) '(4 5 6 7) 2) 6)                ; true
  (= (#(first (drop %2 %1)) [:a :b :c] 0) :a)               ; true
  (= (#(first (drop %2 %1)) [1 2 3 4] 1) 2)                 ; true
  (= (#(first (drop %2 %1)) '([1 2] [3 4] [5 6]) 2) [5 6])) ; true

;; ;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; 022 - Count a Sequence (easy)
;; ;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

(comment
  (= (#(reduce (fn [n _m] (inc n)) 0 %) '(1 2 3 3 1)) 5)         ; true
  (= (#(reduce (fn [n _m] (inc n)) 0 %) "Hello World") 11)       ; true
  (= (#(reduce (fn [n _m] (inc n)) 0 %) [[1 2] [3 4] [5 6]]) 3)  ; true
  (= (#(reduce (fn [n _m] (inc n)) 0 %) '(13)) 1)                ; true
  (= (#(reduce (fn [n _m] (inc n)) 0 %) '(:a :b :c)) 3))         ; true

;; ;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; 023 - Reverse a Sequence (easy)
;; ;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

(comment
  (= (#(reduce conj nil %) [1 2 3 4 5]) [5 4 3 2 1])                  ; true
  (= (#(reduce conj nil %) (sorted-set 5 7 2 7)) '(7 5 2))            ; true
  (= (#(reduce conj nil %) [[1 2] [3 4] [5 6]]) [[5 6] [3 4] [1 2]])) ; true

;; ;;;;;;;;;;;;;;;;;;;;;;;;;;
;; 024 - Sum It All Up (easy)
;; ;;;;;;;;;;;;;;;;;;;;;;;;;;

(comment
  (= (#(apply + %) [1 2 3]) 6)           ; true
  (= (#(apply + %) (list 0 -2 5 5)) 8)   ; true
  (= (#(apply + %) #{4 2 1}) 7)          ; true
  (= (#(apply + %) '(0 0 -1)) -1)        ; true
  (= (#(apply + %) '(1 10 3)) 14))       ; true

(comment
  (= (#(reduce + %) [1 2 3]) 6)          ; true
  (= (#(reduce + %) (list 0 -2 5 5)) 8)  ; true
  (= (#(reduce + %) #{4 2 1}) 7)         ; true
  (= (#(reduce + %) '(0 0 -1)) -1)       ; true
  (= (#(reduce + %) '(1 10 3)) 14))      ; true

;; ;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; 025 - Find the odd numbers (easy)
;; ;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

(comment
  (= (#(filter odd? %) #{1 2 3 4 5}) '(1 3 5))      ; true
  (= (#(filter odd? %)  [4 2 1 6])    '(1))         ; true
  (= (#(filter odd? %)  [2 2 4 6])    '())          ; true
  (= (#(filter odd? %)  [1 1 1 3])    '(1 1 1 3)))  ; true

; if `filter` is not allowed
(comment
  (= (#(reduce (fn [n1 n2] (if (odd? n2) (conj n1 n2) n1)) [] %) #{1 2 3 4 5}) '(1 3 5))     ; true
  (= (#(reduce (fn [n1 n2] (if (odd? n2) (conj n1 n2) n1)) [] %)  [4 2 1 6])    '(1))        ; true
  (= (#(reduce (fn [n1 n2] (if (odd? n2) (conj n1 n2) n1)) [] %)  [2 2 4 6])    '())         ; true
  (= (#(reduce (fn [n1 n2] (if (odd? n2) (conj n1 n2) n1)) [] %)  [1 1 1 3])    '(1 1 1 3))) ; true

;; ;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; 026 - Fibonacci Sequence (easy)
;; ;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

(comment
  (defn fib1
    ([]
     (fib1 0 1))
    ([a b]
     (lazy-seq (cons a (fib1 b (+ a b))))))

  (take 10 (fib1)) ; (0 1 1 2 3 5 8 13 21 34)
  (nth (fib1) 9))  ; -> 34

(comment
  (def fib-seq
    ((fn fib [prev1 prev2]
       (lazy-seq (cons prev1 (fib prev2 (+ prev1 prev2)))))
     0 1))

  (take 10 fib-seq) ; -> (0 1 1 2 3 5 8 13 21 34)
  (nth fib-seq 9))  ; -> 34

(comment
  (def fibs
    (->> [0 1]
         (iterate (fn [[prev1 prev2]] [prev2 (+ prev1 prev2)]))
         (map first)))

  (take 10 fibs) ; -> (0 1 1 2 3 5 8 13 21 34)
  (nth fibs 9))  ; -> 34

(comment
  (defn fib [n] (->> [1 1]
                     (iterate (fn [[a b]] [b (+' a b)]))
                     (map first)
                     (take n)))

  (= (fib 3) '(1 1 2))              ; true
  (= (fib 6) '(1 1 2 3 5 8))        ; true
  (= (fib 8) '(1 1 2 3 5 8 13 21))) ; true

;; ;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; 027 - Palindrome Detector (easy)
;; ;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

(comment
  (defn palindrome? [coll] (= (reverse coll) (seq coll)))

  (false? (palindrome? '(1 2 3 4 5)))       ; true
  (true?  (palindrome?  "racecar"))         ; true
  (true?  (palindrome?  [:foo :bar :foo]))  ; true
  (true?  (palindrome?  '(1 1 3 3 1 1)))    ; true
  (false? (palindrome?  '(:a :b :c))))      ; true

;; ;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; 028 - Flatten a Sequence (easy)
;; ;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

(comment
  (defn flatten-me [coll]
    (let [[fst & rest :as all] coll]
      (cond
        (empty? all)  (seq '())
        (coll? fst)   (concat (flatten-me fst) (flatten-me rest))
        :else         (conj (flatten-me rest) fst))))

  (defn flatten-me [c]
    (if (sequential? c) ; `seq?` doesn't work
      (mapcat flatten-me c)
      [c]))

  (= (flatten-me '((1 2) 3 [4 [5 6]])) '(1 2 3 4 5 6))  ; true
  (= (flatten-me ["a" ["b"] "c"])      '("a" "b" "c"))  ; true
  (= (flatten-me '((((:a)))))          '(:a)))          ; true

;; ;;;;;;;;;;;;;;;;;;;;;;;;;
;; 029 - Get the Caps (easy)
;; ;;;;;;;;;;;;;;;;;;;;;;;;;

;; instance method
(comment
  (.toUpperCase "asldfj")    ; "ASLDFJ"

;; class/static method
  (Character/isUpperCase \b)  ; false
  (Character/isUpperCase \B)) ; true

;; solution with regex
(comment
  (defn get-the-caps [str]
    (clojure.string/join (re-seq #"[A-Z]+" str)))

;; solution with a simple filter using Java Interop
  (defn get-the-caps [str]
    (clojure.string/join (filter #(Character/isUpperCase %) str)))

  (=      (get-the-caps "HeLlO, WoRlD!") "HLOWRD")  ; true
  (empty? (get-the-caps "nothing"))                 ; true
  (=      (get-the-caps "$#A(*&987Zf") "AZ"))       ; true

;; ;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; 030 - Compress a Sequence (easy)
;; ;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

(comment
  (= (apply str (dedupe "Leeeeeerrroyyy")) "Leroy")            ; true
  (= (dedupe [1 1 2 3 3 2 2 3]) '(1 2 3 2 3))                  ; true
  (= (dedupe [[1 2] [1 2] [3 4] [1 2]]) '([1 2] [3 4] [1 2])) ; true

  ;; if `dedupe` is not allowed
  (->> "Leeeeeerrroyyy"
       (partition-by identity) ; ((\L) (\e \e \e \e \e \e) (\r \r \r) (\o) (\y \y \y))
       (map first))            ; (\L \e \r \o \y)

  (->> [1 1 2 3 3 2 2 3]
       (partition-by identity) ; ((1 1) (2) (3 3) (2 2) (3))
       (map first)))            ; (1 2 3 2 3)

(comment
  (defn dedupe-me [coll]
    (->> coll
         (partition-by identity)
         (map first)))

  (= (apply str (dedupe-me "Leeeeeerrroyyy")) "Leroy")            ; true
  (= (dedupe-me [1 1 2 3 3 2 2 3]) '(1 2 3 2 3))                  ; true
  (= (dedupe-me [[1 2] [1 2] [3 4] [1 2]]) '([1 2] [3 4] [1 2]))) ; true

;; ;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; 031 - Pack a Sequence (easy)
;; ;;;;;;;;;;;;;;;;;;;;;;;;;;;;

(comment
  (defn pack-a-sequence [coll]
    (partition-by identity coll))

  (= (pack-a-sequence [1 1 2 1 1 1 3 3]) '((1 1) (2) (1 1 1) (3 3)))  ; true
  (= (pack-a-sequence [:a :a :b :b :c]) '((:a :a) (:b :b) (:c)))      ; true
  (= (pack-a-sequence [[1 2] [1 2] [3 4]]) '(([1 2] [1 2]) ([3 4])))) ; true

;; ;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; 032 - Duplicate a Sequence (easy)
;; ;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

(comment
  (defn duplicate-a-sequence [coll]
    (mapcat vector coll coll))

  (= (duplicate-a-sequence [1 2 3]) '(1 1 2 2 3 3))                    ; true
  (= (duplicate-a-sequence [:a :a :b :b]) '(:a :a :a :a :b :b :b :b))  ; true
  (= (duplicate-a-sequence [[1 2] [3 4]]) '([1 2] [1 2] [3 4] [3 4]))  ; true
  (= (duplicate-a-sequence [44 33]) [44 44 33 33]))                    ; true

;; ;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; 033 - Replicate a Sequence (easy)
;; ;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

(comment
  (apply mapcat vector (repeat 2 [1 2 3])) ; (1 1 2 2 3 3)
  (mapcat #(repeat 2 %) [1 2 3])           ; (1 1 2 2 3 3)

  (defn replicate-a-sequence [coll n]
    (apply mapcat vector (repeat n coll))))

(comment
  (defn replicate-a-sequence [coll n]
    (mapcat #(repeat n %1) coll))

  (= (replicate-a-sequence [1 2 3] 2)       '(1 1 2 2 3 3))              ; true
  (= (replicate-a-sequence [:a :b] 4)       '(:a :a :a :a :b :b :b :b))  ; true
  (= (replicate-a-sequence [4 5 6] 1)       '(4 5 6))                    ; true
  (= (replicate-a-sequence [[1 2] [3 4]] 2) '([1 2] [1 2] [3 4] [3 4]))  ; true
  (= (replicate-a-sequence [44 33] 2)       [44 44 33 33]))              ; true

;; ;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; 034 - Implement range (easy)
;; ;;;;;;;;;;;;;;;;;;;;;;;;;;;;

(comment
  (take (- 4 1) (iterate inc 1))    ; (1 2 3)
  (take (- 2 -2) (iterate inc -2))  ; (-2 -1 0 1)
  (take (- 8 5) (iterate inc 5)))   ; (5 6 7)

(comment
  (defn range-me [start stop]
    (take (- stop start) (iterate inc start)))

  (= (range-me  1 4) '(1 2 3))      ; true
  (= (range-me -2 2) '(-2 -1 0 1))  ; true
  (= (range-me  5 8) '(5 6 7)))     ; true

;; ;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; 035 - Local bindings (elementary)
;; ;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

(comment
  (= 7 (let [x 5] (+ 2 x)))        ; true
  (= 7 (let [x 3, y 10] (- y x)))  ; true
  (= 7 (let [x 21 y 3] (/ x y))))  ; true

;; ;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; 036 - Let it Be (elementary)
;; ;;;;;;;;;;;;;;;;;;;;;;;;;;;;

(comment
  (= 10 (let [x 6, y 4] (+ x y)))  ; true
  (= 4 (let [y 1, z 3] (+ y z)))   ; true
  (= 1 (let [z 1] z)))             ; true

;; ;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; 037 - Regular Expressions (elementary)
;; ;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

(comment
  (= "ABC" (apply str (re-seq #"[A-Z]+" "bA1B3Ce ")))) ; true

;; ;;;;;;;;;;;;;;;;;;;;;;;;;;
;; 038 - Maximum value (easy)
;; ;;;;;;;;;;;;;;;;;;;;;;;;;;

(comment
  (reduce #(if (> %1 %2) %1 %2) '(1 8 3 4))) ; 8

(comment
  (defn maximum-value [& rest]
    (reduce #(if (> %1 %2) %1 %2) rest))

  (= (maximum-value 1 8 3 4)   8)  ; true
  (= (maximum-value 30 20)    30)  ; true
  (= (maximum-value 45 67 11) 67)) ; true

;; ;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; 039 - Interleave Two Seqs (easy)
;; ;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

(comment
  (mapcat vector [1 2 3] [:a :b :c]))

(comment
  (defn interleave-two-seqs [coll1 coll2]
    (mapcat vector coll1 coll2))

  (= (interleave-two-seqs [1 2 3] [:a :b :c]) '(1 :a 2 :b 3 :c))  ; true
  (= (interleave-two-seqs [1 2] [3 4 5 6])    '(1 3 2 4))         ; true
  (= (interleave-two-seqs [1 2 3 4] [5])       [1 5])             ; true
  (= (interleave-two-seqs [30 20] [25 15])     [30 25 20 15]))    ; true

;; ;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; 040 - Interpose a Seq (easy)
;; ;;;;;;;;;;;;;;;;;;;;;;;;;;;;

(comment
  (let [val 0
        coll [1 2 3]]
    (butlast (interleave-two-seqs coll (repeat val)))))
; (1 0 2 0 3)

(comment
  (defn interpose-a-seq [val coll]
    (butlast (interleave coll (repeat val))))

  (= (interpose-a-seq 0 [1 2 3])                              [1 0 2 0 3])             ; true
  (= (apply str (interpose-a-seq ", " ["one" "two" "three"])) "one, two, three")       ; true
  (= (interpose-a-seq :z [:a :b :c :d])                       [:a :z :b :z :c :z :d])) ; true

;; ;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; 041 - Drop Every Nth Item (easy)
;; ;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

(comment
  (let [coll [1 2 3 4 5 6 7 8]
        n    3]
    (keep-indexed #(when (not= 0 (mod (inc %1) n)) %2) coll))

  (mod 0 3)  ; 0
  (mod 1 3)  ; 1
  (mod 2 3)  ; 2
  (mod 3 3)  ; 0
  (mod 4 3)) ; 1

(comment
  (defn drop-every-nth-item [coll n]
    (keep-indexed #(when (not= 0 (mod (inc %1) n)) %2) coll))

  (= (drop-every-nth-item [1 2 3 4 5 6 7 8] 3)   [1 2 4 5 7 8])  ; true
  (= (drop-every-nth-item [:a :b :c :d :e :f] 2) [:a :c :e])     ; true
  (= (drop-every-nth-item [1 2 3 4 5 6] 4)       [1 2 3 5 6]))   ; true

;; ;;;;;;;;;;;;;;;;;;;;;;;;;;
;; 042 - Factorial Fun (easy)
;; ;;;;;;;;;;;;;;;;;;;;;;;;;;

(comment
  (let [n 5]
    (apply * (range 1 (inc n)))))

;; you can use `apply` or `reduce`
(comment
  (defn factorial-fun [n]
    (apply * (range 1 (inc n))))

  (= (factorial-fun 1) 1)      ; true
  (= (factorial-fun 3) 6)      ; true
  (= (factorial-fun 5) 120)    ; true
  (= (factorial-fun 8) 40320)) ; true

;; ;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; 043 - Reverse Interleave (medium)
;; ;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

(comment
  (partition 2 [1 2 3 4 5 6])            ; ((1 2) (3 4) (5 6))
  (apply map list '((1 2) (3 4) (5 6))) ; ((1 3 5) (2 4 6))

  (partition 3 (range 9))                     ; ((0 1 2) (3 4 5) (6 7 8))
  (apply map list '((0 1 2) (3 4 5) (6 7 8))) ; ((0 3 6) (1 4 7) (2 5 8))

  (partition 5 (range 10))                     ; ((0 1 2 3 4) (5 6 7 8 9))
  (apply map list '((0 1 2 3 4) (5 6 7 8 9)))) ; ((0 5) (1 6) (2 7) (3 8) (4 9))

(comment
  (defn reverse-interleave [coll n]
    (apply map list (partition n coll)))

  (= (reverse-interleave [1 2 3 4 5 6] 2) '((1 3 5) (2 4 6)))             ; true
  (= (reverse-interleave (range 9) 3) '((0 3 6) (1 4 7) (2 5 8)))         ; true
  (= (reverse-interleave (range 10) 5) '((0 5) (1 6) (2 7) (3 8) (4 9)))) ; true

;; ;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; 044 - Rotate Sequence (medium)
;; ;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

(comment
  (defn rotate-seq [n coll]
    (let [n (mod n (count coll))]
      (concat
       (drop n coll)
       (take n coll))))

  (= (rotate-seq  2 [1 2 3 4 5]) '(3 4 5 1 2))  ; true
  (= (rotate-seq -2 [1 2 3 4 5]) '(4 5 1 2 3))  ; true
  (= (rotate-seq  6 [1 2 3 4 5]) '(2 3 4 5 1))  ; true
  (= (rotate-seq  1 '(:a :b :c)) '(:b :c :a))   ; true
  (= (rotate-seq -4 '(:a :b :c)) '(:c :a :b)))  ; true

;; ;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; 045 - Intro to Iterate (easy)
;; ;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

(comment
  (= '(1 4 7 10 13) (take 5 (iterate #(+ 3 %) 1)))) ; true

;; ;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; 046 - Flipping out (medium)
;; ;;;;;;;;;;;;;;;;;;;;;;;;;;;

;; compare to PureScript
;; https://github.com/purescript/purescript-prelude/blob/2a51e602c067f1e2f9387600307544b97b02a239/src/Data/Function.purs#L28-L28

(defn flip [op]
  (fn [& args]
    (apply op (reverse args))))

(comment
  (= 3       ((flip nth) 2 [1 2 3 4 5]))   ; true
  (= true    ((flip >) 7 8))               ; true
  (= 4       ((flip quot) 2 8))            ; true
  (= [1 2 3] ((flip take) [1 2 3 4 5] 3))) ; true

;; ;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; 047 - Contain Yourself (easy)
;; ;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

(comment
  (contains? #{4 5 6}     4)   ; true
  (contains? [1 1 1 1 1]  0)   ; true (in a vector it's an index!!!)
  (contains? {4 :a 2 :b}  4)   ; true
  (not (contains? [1 2 4] 3))) ; true (in a vector it's an index!!!)

;; ;;;;;;;;;;;;;;;;;;;;;;;;;;
;; 048 - Intro to some (easy)
;; ;;;;;;;;;;;;;;;;;;;;;;;;;;

(comment
  (= 6 (some #{2 7 6} [5 6 7 8]))             ; true
  (= 6 (some #(when (even? %) %) [5 6 7 8]))) ; true

;; ;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; 049 - Split a sequence (easy)
;; ;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

(comment
  (defn split-a-sequence [n coll]
    [(take n coll) (drop n coll)])

  (= (split-a-sequence 3 [1 2 3 4 5 6])       [[1 2 3] [4 5 6]])        ; true
  (= (split-a-sequence 1 [:a :b :c :d])       [[:a] [:b :c :d]])        ; true
  (= (split-a-sequence 2 [[1 2] [3 4] [5 6]]) [[[1 2] [3 4]] [[5 6]]])) ; true

;; ;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; 050 - Split by Type (medium)
;; ;;;;;;;;;;;;;;;;;;;;;;;;;;;;

(comment
  (defn split-by-type [coll]
    (vals (group-by type coll)))

  (= (set (split-by-type [1 :a 2 :b 3 :c])) #{[1 2 3] [:a :b :c]})                  ; true
  (= (set (split-by-type [:a "foo"  "bar" :b])) #{[:a :b] ["foo" "bar"]})           ; true
  (= (set (split-by-type [[1 2] :a [3 4] 5 6 :b])) #{[[1 2] [3 4]] [:a :b] [5 6]})) ; true

;; ;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; 051 - Advanced Destructuring (easy)
;; ;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

(comment
  (= [1 2 [3 4 5] [1 2 3 4 5]] (let [[a b & c :as d] [1 2 3 4 5]] [a b c d]))) ; true

;; ;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; 052 - Intro to Destructuring (elementary)
;; ;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

(comment
  (= [2 4] (let [[_a _b c _d e _f _g] (range)] [c e]))) ; true

;; ;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; 053 - Longest Increasing SubSeq (hard)
;; ;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

(comment
  ;; see also 171

  (map second                                                                                ; (0 1 2 3)
       (apply max-key count                                                                  ; ((-1 0) (-1 1) (-1 2) (-1 3))
              (filter #(> (count %) 1)                                                       ; (((-1 0) (-1 1) (-1 2) (-1 3)) ((-2 4) (-2 5)))
                      (partition-by first                                                    ; (((1 1)) ((-1 0) (-1 1) (-1 2) (-1 3)) ((-5 0)) ((-2 4) (-2 5)))
                                    (map-indexed #(list (- %2 %) %2) [1 0 1 2 3 0 4 5])))))) ; ((1 1) (-1 0) (-1 1) (-1 2) (-1 3) (-5 0) (-2 4) (-2 5))

(comment
  (defn longest-increasing-subseq [coll]
    (let [res (->> coll
                   (map-indexed #(list (- %2 %) %2))
                   (partition-by first)
                   (filter #(> (count %) 1)))]
      (if (seq res)
        (->> res
             (apply max-key count res)
             (map second))
        res))))

(comment
  (= (longest-increasing-subseq [1 0 1 2 3 0 4 5]) [0 1 2 3]) ; true
  (= (longest-increasing-subseq [5 6 1 3 2 7])     [5 6])     ; true
  (= (longest-increasing-subseq [2 3 3 4 5])       [3 4 5])   ; true
  (= (longest-increasing-subseq [7 6 5 4])         []))       ; true

;; ;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; 054 - Partition a Sequence (medium)
;; ;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

(comment
  (defn partition-me [n coll]
    (let [p (take n coll)]
      (when (= (count p) n)
        (cons p (partition-me n (drop n coll))))))

  (= (partition-me 3 (range 9)) '((0 1 2) (3 4 5) (6 7 8)))  ; true
  (= (partition-me 3 (range 8)) '((0 1 2) (3 4 5)))          ; true
  (= (partition-me 2 (range 8)) '((0 1) (2 3) (4 5) (6 7)))) ; true

;; ;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; 055 - Count Occurences (medium)
;; ;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

(comment
  (defn count-occurences [coll]
    (->> coll
         (group-by identity)
         ((flip update-vals) count)))

  (= (count-occurences [1 1 2 3 2 1 1]) {1 4, 2 2, 3 1})          ; true
  (= (count-occurences [:b :a :b :a :b]) {:a 2, :b 3})            ; true
  (= (count-occurences '([1 2] [1 3] [1 3])) {[1 2] 1, [1 3] 2})) ; true

;; ;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; 056 - Find Distinct Items (medium)
;; ;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

(comment
  (defn find-distinct-items [coll]
    (reduce #(if (contains? (set %1) %2) %1 (conj %1 %2)) [] coll))

  (= (find-distinct-items [1 2 1 3 1 2 4]) [1 2 3 4])                        ; true
  (= (find-distinct-items [:a :a :b :b :c :c]) [:a :b :c])                   ; true
  (= (find-distinct-items '([2 4] [1 2] [1 3] [1 3])) '([2 4] [1 2] [1 3]))  ; true
  (= (find-distinct-items (range 50)) (range 50)))                           ; true

;; ;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; 057 - Simple Recursion (elementary)
;; ;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

(comment
  (= '(5 4 3 2 1) ((fn foo [x] (when (> x 0) (conj (foo (dec x)) x))) 5))) ; true

;; ;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; 058 - Function Composition (medium)
;; ;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

(comment
  (defn comp-me [& funcs]
    (let [[func & funcs] (reverse funcs)]
      (fn [& args]
        (reduce #(%2 %1) (apply func args) funcs))))

  (defn comp-me [& funcs]
    (reduce #(fn [& args] (%1 (apply %2 args))) funcs))

  (= [3 2 1] ((comp-me rest reverse) [1 2 3 4]))                                 ; true
  (= 5       ((comp-me (partial + 3) second) [1 2 3 4]))                         ; true
  (= true    ((comp-me zero? #(mod % 8) +) 3 5 7 9))                             ; true
  (= "HELLO" ((comp-me #(.toUpperCase %) #(apply str %) take) 5 "hello world"))) ; true

;; ;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; 059 - Juxtaposition (medium)
;; ;;;;;;;;;;;;;;;;;;;;;;;;;;;;

(comment
  (defn juxt-me [& funcs]
    (fn [& args]
      (reduce #(conj %1 (apply %2 args)) [] funcs)))

  (defn juxt-me [& funcs]
    (fn [& args]
      (map #(apply % args) funcs)))

  (= [21 6 1]    ((juxt-me + max min) 2 3 5 1 6 4))                    ; true
  (= ["HELLO" 5] ((juxt-me #(.toUpperCase %) count) "hello"))          ; true
  (= [2 6 4]     ((juxt-me :a :c :b) {:a 2, :b 4, :c 6, :d 8 :e 10}))) ; true

;; ;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; 060 - Sequence Reductions (medium)
;; ;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

(comment
  (defn reductions-me
    ([f coll]
     (reductions-me f (first coll) (rest coll)))
    ([f init coll]
     (lazy-seq
      (cons init
            (when-let [s (seq coll)]
              (reductions-me f (f init (first s)) (rest s)))))))

  (= (take 5 (reductions-me + [0 1 2 3 4 5])) [0 1 3 6 10])                  ; true
  (= (take 5 (reductions-me + (range)))       [0 1 3 6 10])                  ; true
  (= (reductions-me conj [1] [2 3 4])         [[1] [1 2] [1 2 3] [1 2 3 4]]) ; true
  (= (last (reductions-me * 2 [3 4 5]))       (reduce * 2 [3 4 5]) 120))     ; true

;; ;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; 061 - Map Construction (easy)
;; ;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

(comment
  (into {} (map vector [:a :b :c] [1 2 3]))) ; {:a 1, :b 2, :c 3}

(comment
  (defn map-construction [keys values]
    (into {} (map vector keys values)))

  (= (map-construction [:a :b :c]  [1 2 3])               {:a 1, :b 2, :c 3})             ; true
  (= (map-construction [1 2 3 4]   ["one" "two" "three"]) {1 "one", 2 "two", 3 "three"})  ; true
  (= (map-construction [:foo :bar] ["foo" "bar" "baz"])   {:foo "foo", :bar "bar"}))      ; true

;; ;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; 062 - Reimplement Iteration (easy)
;; ;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

(comment
  (take 5 (iterate #(* 2 %) 1))) ; (1 2 4 8 16)

(comment
  (defn iterate-me [f n]
    (lazy-seq
     (cons n (iterate-me f (f n)))))

  (= (take 5   (iterate-me #(* 2 %) 1))         [1 2 4 8 16])              ; true
  (= (take 10  (iterate-me inc 0))              (take 10 (range)))         ; true
  (= (take 9   (iterate-me #(inc (mod % 3)) 1)) (take 9 (cycle [1 2 3])))) ; true

;; ;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; 063 - Group a Sequence (easy)
;; ;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

(comment
  (defn group-a-sequence [f coll]
    (reduce #(update-in %1 [(f %2)] concat [%2]) {} coll))

  (= (group-a-sequence #(> % 5) #{1 3 6 8})                    {false [1 3], true [6 8]})                    ; true
  (= (group-a-sequence #(apply / %) [[1 2] [2 4] [4 6] [3 6]]) {1/2 [[1 2] [2 4] [3 6]], 2/3 [[4 6]]})       ; true
  (= (group-a-sequence count [[1] [1 2] [3] [1 2 3] [2 3]])    {1 [[1] [3]], 2 [[1 2] [2 3]], 3 [[1 2 3]]})) ; true

;; ;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; 064 - Intro to Reduce (elementary)
;; ;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

(comment
  (= 15 (reduce + [1 2 3 4 5]))  ; true
  (=  0 (reduce + []))           ; true
  (=  6 (reduce + 1 [2 3])))     ; true

;; ;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; 065 - Black Box Testing (medium)
;; ;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

(comment
  (defn type-me [s]
    (let [empty-s (empty s)]
      (cond
        (empty? (conj empty-s nil))      :map
        (= 1 (count (conj empty-s 1 1))) :set
        (= 1 (first (conj empty-s 1 2))) :vector
        :else :list)))

  (= :map    (type-me {:a 1, :b 2}))                           ; true
  (= :list   (type-me (range (rand-int 20))))                  ; true
  (= :vector (type-me [1 2 3 4 5 6]))                          ; true
  (= :set    (type-me #{10 (rand-int 5)}))                     ; true
  (= [:map :set :vector :list] (map type-me  [{} #{} [] ()]))) ; true

;; ;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; 066 - Greatest Common Divisor (easy)
;; ;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

(comment
  (defn gcd [a b]
    (if (zero? b)
      a
      (recur b (mod a b))))

  (= (gcd 2 4)       2)  ; true
  (= (gcd 10 5)      5)  ; true
  (= (gcd 5 7)       1)  ; true
  (= (gcd 1023 858) 33)) ; true

;; ;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; 067 - Prime Numbers (medium)
;; ;;;;;;;;;;;;;;;;;;;;;;;;;;;;

;; Lazy sequences
;; (comment
;;   (def primes
;;     (remove
;;      (fn [x] (some #(zero? (mod x %)) primes))
;;      (iterate inc 2))))
;;
;; (comment
;;   (take 10 primes)
;;   (take 50 primes)
;;   (time (nth primes 10000))
;;   (time (nth primes 20000))
;;   (time (nth primes 30000)))

(comment
  (defn prime? [n [prime & primes]]
    (cond
      (not prime)           true
      (zero? (mod n prime)) false
      :else                 (recur n primes)))

  (prime? 7 [2 3 5])   ; true
  (prime? 9 [2 3 5 7]) ; false

  (defn next-primes
    ([primes]
     (next-primes (+ 2 (last primes)) primes))
    ([candidate primes]
     (if (prime? candidate primes)
       (conj primes candidate)
       (next-primes (+ 2 candidate) primes))))

  (defn primes [n]
    (last (take (dec n) (iterate next-primes [2 3])))))

(comment
  (next-primes [2 3])         ; [2 3 5]
  (next-primes [2 3 5])       ; [2 3 5 7]
  (next-primes [2 3 5 7])     ; [2 3 5 7 11]
  (next-primes [2 3 5 7 11])) ; [2 3 5 7 11 13]

(comment
  (= (primes 2)          [2 3])         ; true
  (= (primes 5)          [2 3 5 7 11])  ; true
  (= (last (primes 100)) 541))          ; true

;; ;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; 068 - Recurring Theme (elementary)
;; ;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

(comment
  (= [7 6 5 4 3]
     (loop [x      5
            result []]
       (if (> x 0)
         (recur (dec x) (conj result (+ 2 x)))
         result))))

;; ;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; 069 - Merge with a Function (medium)
;; ;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

(comment
  (defn merge-with-me [f to-map & from-maps]
    (into
     {}
     (for [m     from-maps
           [k v] m]
       (cond
         (to-map k) [k (f (to-map k) v)]
         :else      [k v]))))

  (= (merge-with-me * {:a 2, :b 3, :c 4} {:a 2} {:b 2} {:c 5})             ; true
     {:a 4, :b 6, :c 20})
  (= (merge-with-me - {1 10, 2 20} {1 3, 2 10, 3 15})                      ; true
     {1 7, 2 10, 3 15})
  (= (merge-with-me concat {:a [3], :b [6]} {:a [4 5], :c [8 9]} {:b [7]}) ; true
     {:a [3 4 5], :b [6 7], :c [8 9]}))

;; ;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; 070 - Word Sorting (medium)
;; ;;;;;;;;;;;;;;;;;;;;;;;;;;;

(comment
  (defn word-sorting [s]
    (sort #(compare (.toLowerCase %1) (.toLowerCase %2)) (re-seq #"\w+" s)))

  (defn word-sorting [s]
    (sort-by #(.toLowerCase %) (re-seq #"\w+" s)))

  (= (word-sorting "Have a nice day.")                ; true
     ["a" "day" "Have" "nice"])
  (= (word-sorting "Clojure is a fun language!")      ; true
     ["a" "Clojure" "fun" "is" "language"])
  (= (word-sorting "Fools fall for foolish follies.") ; true
     ["fall" "follies" "foolish" "Fools" "for"]))

;; ;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; 071 - Rearranging Code: -> (elementary)
;; ;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

(comment
  (= (count (sort (rest (reverse [2 5 4 1 3 6])))) ; true
     (-> [2 5 4 1 3 6] reverse rest sort count)
     5))

;; ;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; 072 - Rearranging Code: ->> (elementary)
;; ;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

(comment
  (= (reduce + (map inc (take 3 (drop 2 [2 5 4 1 3 6]))))       ; true
     (->> [2 5 4 1 3 6] (drop 2) (take 3) (map inc) (reduce +))
     11))

;; ;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; 073 - Analyze a TicTacToe Board (hard)
;; ;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

(defn analyze-tic-tac-toe [board]
  (let [coords ['((0 0) (0 1) (0 2))   ; rows (3)
                '((1 0) (1 1) (1 2))
                '((2 0) (2 1) (2 2))
                '((0 0) (1 0) (2 0))   ; cols (3)
                '((0 1) (1 1) (2 1))
                '((0 2) (1 2) (2 2))
                '((0 0) (1 1) (2 2))   ; diag (2)
                '((0 2) (1 1) (2 0))]]
    (loop [[[a b c :as cs] & tail] coords]
      (when cs
        (let [fst (get-in board a)
              snd (get-in board b)
              trd (get-in board c)]
          (if (and (= fst snd trd) (not= fst :e))
            fst
            (recur tail)))))))

(comment
  (= nil (analyze-tic-tac-toe  [[:e :e :e]     ; true
                                [:e :e :e]
                                [:e :e :e]]))
  (= :x  (analyze-tic-tac-toe  [[:x :e :o]     ; true
                                [:x :e :e]
                                [:x :e :o]]))
  (= :o  (analyze-tic-tac-toe  [[:e :x :e]     ; true
                                [:o :o :o]
                                [:x :e :x]]))
  (= nil (analyze-tic-tac-toe  [[:x :e :o]     ; true
                                [:x :x :e]
                                [:o :x :o]]))
  (= :x  (analyze-tic-tac-toe  [[:x :e :e]     ; true
                                [:o :x :e]
                                [:o :e :x]]))
  (= :o  (analyze-tic-tac-toe  [[:x :e :o]     ; true
                                [:x :o :e]
                                [:o :e :x]]))
  (= nil (analyze-tic-tac-toe  [[:x :o :x]     ; true
                                [:x :o :x]
                                [:o :x :o]])))

;; ;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; 074 - Filter Perfect Squares (medium)
;; ;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

(comment
  (defn perfect-square? [n]
    (zero? (mod (m/sqrt n) 1)))

  (defn filter-perfect-squares [s]
    (->> s
         (re-seq #"\d+")
         (map read-string)
         (filter perfect-square?)
         (map str)
         (clojure.string/join ",")))

  (= (filter-perfect-squares "4,5,6,7,8,9")    "4,9")       ; true
  (= (filter-perfect-squares "15,16,25,36,37") "16,25,36")) ; true

;; ;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; 075 - Euler's Totient Function (medium)
;; ;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

(comment
  (defn gcd [a b]
    (if (zero? b)
      a
      (recur b (mod a b))))

  (defn totient [n]
    (if (= n 1)
      1
      (count (filter #(= 1 (gcd %1 n)) (range 1 n)))))

  (= (totient 1)  1)                    ; true
  (= (totient 10) (count '(1 3 7 9)) 4) ; true
  (= (totient 40) 16)                   ; true
  (= (totient 99) 60))                  ; true

;; ;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; 076 - Intro to Trampoline (medium)
;; ;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

(= [1 3 5 7 9 11]                       ; true
   (letfn
    [(foo [x y] #(bar (conj x y) y))
     (bar [x y] (if (> (last x) 10)
                  x
                  #(foo x (+ 2 y))))] (trampoline foo [] 1)))

;; ;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; 077 - Anagram Finder (medium)
;; ;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

(comment
  "complicated solution"

  (defn permutations [coll]
    (if (= 1 (count coll))
      (list coll)
      (for [idx  (range (count coll))
            tail (permutations (remove-nth coll idx))]
        (cons (nth coll idx) tail))))

  (defn remove-nth
    [coll n]
    (into (subvec (vec coll) 0 n) (subvec (vec coll) (inc n))))

  (remove-nth "meat" 0)

  (map (partial apply str) (permutations "meat"))
  ; ("maet" "mate" "meat" "meta" "mtae" "mtea" "eamt" "eatm" "emat"
  ;  "emta" "etam" "etma" "aemt" "aetm" "amet" "amte" "atem" "atme"
  ;  "taem" "tame" "team" "tema" "tmae" "tmea")

  ;; wrong
  (map (partial apply str) (permutations "veer"))
  ; ("veer" "vere" "veer" "vere" "vree" "vree" "ever" "evre" "eevr"
  ;  "eerv" "erve" "erev" "ever" "evre" "eevr" "eerv" "erve" "erev"
  ;  "rvee" "rvee" "reve" "reev" "reve" "reev")

  ((set ["meat" "mat" "team" "mate" "eat"]) "meat") ; "meat"
  ((set ["meat" "mat" "team" "mate" "eat"]) "abc")  ; nil

  (set [#{"meat" "team" "mate"}, #{"meat" "team" "mate"}]) ; #{#{"meat" "mate" "team"}}

  (defn anagrams [words]
    (set (filter #(> (count %) 1)
                 (set
                  (for [word words]
                    (apply conj #{word}
                           (for [perm (map (partial apply str) (permutations word))
                                 :when (and (not= word perm) (not (neg? (.indexOf words perm))))]
                             perm))))))))

(comment
  "elegant solution but wrong"
  (defn anagrams [words]
    (->> (group-by set words)
         (vals)
         (filter #(> (count %) 1))
         (map set)
         (set)))

  (group-by set ["meat" "mat" "team" "mate" "eat"])
  ; {#{\a \e \m \t} ["meat" "team" "mate"],
  ;  #{\a \m \t}    ["mat"],
  ;  #{\a \e \t}    ["eat"]}

  (group-by set ["veer" "lake" "item" "kale" "mite" "ever"])
  ; {#{\e \r \v}    ["veer" "ever"],
  ;  #{\a \e \k \l} ["lake" "kale"],
  ;  #{\e \i \m \t} ["item" "mite"]}

  ;; "eerev" will lead to a WRONG result!!!
  (group-by set ["veer" "lake" "item" "kale" "mite" "ever" "eerev"])
  ; {#{\e \r \v}    ["veer" "ever" "eerev"], ; "eerev" is wrong!
  ;  #{\a \e \k \l} ["lake" "kale"],
  ;  #{\e \i \m \t} ["item" "mite"]}

  (= (anagrams ["meat" "mat" "team" "mate" "eat"])           ; true
     #{#{"meat" "team" "mate"}})
  (= (anagrams ["veer" "lake" "item" "kale" "mite" "ever"])  ; true
     #{#{"veer" "ever"} #{"lake" "kale"} #{"mite" "item"}})
  (= (anagrams ["veere" "veer" "lake" "item" "kale" "mite" "ever"]) ; false
     #{#{"veer" "ever"} #{"lake" "kale"} #{"mite" "item"}}))

(comment
  "beautiful solution and correct"

  (defn anagrams [words]
    (->> words
         (group-by frequencies)
         vals
         (filter #(> (count %) 1))
         (map set)
         set))

  (group-by frequencies ["meat" "mat" "team" "mate" "eat"])
  ; {{\m 1, \e 1, \a 1, \t 1} ["meat" "team" "mate"],
  ;  {\m 1, \a 1, \t 1}       ["mat"],
  ;  {\e 1, \a 1, \t 1}       ["eat"]}

  (group-by frequencies ["veere" "veer" "lake" "item" "kale" "mite" "ever"])
  ; {{\v 1, \e 3, \r 1}       ["veere"],
  ;  {\v 1, \e 2, \r 1}       ["veer" "ever"],
  ;  {\l 1, \a 1, \k 1, \e 1} ["lake" "kale"],
  ;  {\i 1, \t 1, \e 1, \m 1} ["item" "mite"]}

  (= (anagrams ["meat" "mat" "team" "mate" "eat"])                  ; true
     #{#{"meat" "team" "mate"}})
  (= (anagrams ["veer" "lake" "item" "kale" "mite" "ever"])         ; true
     #{#{"veer" "ever"} #{"lake" "kale"} #{"mite" "item"}})
  (= (anagrams ["veere" "veer" "lake" "item" "kale" "mite" "ever"]) ; true
     #{#{"veer" "ever"} #{"lake" "kale"} #{"mite" "item"}}))

;; ;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; 078 - Reimplement Trampoline (medium)
;; ;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

(comment
  (defn trampoline-me [f & args]
    (loop [res (apply f args)]
      (if (ifn? res)
        (recur (res))
        res)))

  (= (letfn [(triple  [x] #(sub-two (* 3 x)))                         ; true
             (sub-two [x] #(stop? (- x 2)))
             (stop?   [x] (if (> x 50) x #(triple x)))]
       (trampoline-me triple 2))
     82)

  (= (letfn [(my-even? [x] (if (zero? x) true  #(my-odd?  (dec x))))  ; true
             (my-odd?  [x] (if (zero? x) false #(my-even? (dec x))))]
       (map (partial trampoline-me my-even?) (range 6)))
     [true false true false true false]))

;; ;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; 079 - Triangle Minimal Path (hard)
;; ;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

(comment
  (map-indexed (fn [idx item] (list item [idx (inc idx)])) [1])       ; ((1 [0 1]))
  (map-indexed (fn [idx item] (list item [idx (inc idx)])) [2 4])     ; ((2 [0 1]) (4 [1 2]))
  (map-indexed (fn [idx item] (list item [idx (inc idx)])) [5 1 4])   ; ((5 [0 1]) (1 [1 2]) (4 [2 3]))
  (map-indexed (fn [idx item] (list item [idx (inc idx)])) [2 3 4 5]) ; ((2 [0 1]) (3 [1 2]) (4 [2 3]) (5 [3 4]))

  (def t [[1]
          [2 4]
          [5 1 4]
          [2 3 4 5]])
  ;; 1
  ;; 3 5
  ;; 8 4 6 9
  ;; 10 11 7 9 9 10 13 14

  (map #(map-indexed (fn [idx item] (vector item [idx (inc idx)])) %) t))
  ; (([1 [0 1]])
  ;  ([2 [0 1]] [4 [1 2]])
  ;  ([5 [0 1]] [1 [1 2]] [4 [2 3]])
  ;  ([2 [0 1]] [3 [1 2]] [4 [2 3]] [5 [3 4]]))

(comment
  "way too complicated solution"

  (defn triangle-min-path [triangle]
    (first
     (apply min-key first
            (let [paths (map
                         #(map-indexed (fn [idx item] (vector item [idx (inc idx)])) %)
                         triangle)]
              (loop [sum-paths                 '([0 [0]])
                     [next-paths & rest-paths] paths]
                (if (seq next-paths)
                  (recur (reduce
                          (fn [acc [val1 indexes1]]
                            (concat acc
                                    (reduce
                                     (fn [acc idx] (let [[val2 indexes2] ((vec next-paths) idx)] (conj acc [(+ val1 val2) indexes2])))
                                     []
                                     indexes1)))
                          []
                          sum-paths)
                         rest-paths)
                  sum-paths)))))))

(comment
  (= (triangle-min-path [[1]             ; true
                         [2 4]
                         [5 1 4]
                         [2 3 4 5]])
     (+ 1 2 1 3)
     7)
  (= (triangle-min-path [[3]             ; true
                         [2 4]
                         [1 9 3]
                         [9 9 2 4]
                         [4 6 6 7 8]
                         [5 7 3 5 1 4]])
     (+ 3 4 3 2 7 1)
     20))

(comment
  "much better solution"

  ;; triangle is just a binary tree -> task: find minimum path in a binary tree
  ;; Dynamic Programming Approach: https://heycoach.in/blog/finding-the-minimum-path-in-a-binary-tree/

  ;; left tree
  (rest (map butlast [[1] [2 4] [5 1 4] [2 3 4 5]])) ; ((2) (5 1) (2 3 4))
  ;; right tree
  (rest (map rest    [[1] [2 4] [5 1 4] [2 3 4 5]])) ; ((4) (1 4) (3 4 5))

  (defn triangle-min-path [t]
    (let [n     (ffirst t)
          leaf? #(= (count %) 1)]
      (if (leaf? t)
        n
        (+ n (min (triangle-min-path (rest (map butlast t)))       ; left  tree
                  (triangle-min-path (rest (map rest    t))))))))) ; right tree

;; ;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; 080 - Perfect Numbers (medium)
;; ;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

(comment
  (defn perfect-number? [n]
    (= n (apply + (for [m (range 1 n)
                        :when (zero? (rem n m))]
                    m))))

  (= (perfect-number? 6) true)     ; true
  (= (perfect-number? 7) false)    ; true
  (= (perfect-number? 496) true)   ; true
  (= (perfect-number? 500) false)  ; true
  (= (perfect-number? 8128) true)) ; true

;; ;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; 081 - Set Intersection (easy)
;; ;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

(comment
  (#{0 1 2 3} 0)  ; 0
  (#{0 1 2 3} 1)  ; 1
  (#{0 1 2 3} 2)  ; 2
  (#{0 1 2 3} 3)  ; 3
  (#{0 1 2 3} 4)  ; nil
  (#{0 1 2 3} 5)) ; nil

(comment
  (filter #{0 1 2 3} #{2 3 4 5})        ;  (3 2)
  (set (filter #{0 1 2 3} #{2 3 4 5}))) ; #{3 2}

(comment
  (defn intersect-me [s1 s2]
    (set (filter s1 s2)))

  (defn intersect-me [s1 s2]
    (reduce #(if (contains? s2 %2) (conj %1 %2) %1) #{} s1))

  (= (intersect-me #{0 1 2 3} #{2 3 4 5})            #{2 3})       ; true
  (= (intersect-me #{0 1 2} #{3 4 5})                #{})          ; true
  (= (intersect-me #{:a :b :c :d} #{:c :e :a :f :d}) #{:a :c :d})) ; true

;; ;;;;;;;;;;;;;;;;;;;;;;;;
;; 082 - Word Chains (hard)
;; ;;;;;;;;;;;;;;;;;;;;;;;;

;; Every word must be used exactly once!
;; Starting word can be any word!

(comment
  (defn change-type [from-word to-word]
    (cond
      (=   (count from-word) (count to-word))     :substitution
      (= 1 (- (count from-word) (count to-word))) :deletion
      (= 1 (- (count to-word) (count from-word))) :insertion
      :else                                       :false))

  (defn remove-nth [coll n]
    (let [collv (vec coll)]
      (apply str (into (subvec collv 0 n)
                       (subvec collv (inc n))))))

  (defn substitution? [from-word to-word]
    (some identity
          (for [r (range (count from-word))
                :let [from-word' (remove-nth from-word r)
                      to-word' (remove-nth to-word r)]
                :when (= from-word' to-word')]
            to-word)))

  (defn deletion? [from-word to-word]
    (some identity
          (for [r (range (count from-word))
                :let [from-word' (remove-nth from-word r)]
                :when (= from-word' to-word)]
            to-word)))

  (defn insertion? [from-word to-word]
    (some identity
          (for [r (range (count from-word))
                :let [to-word' (remove-nth to-word r)]
                :when (= from-word to-word')]
            to-word)))

  (defn chains [[[from-word words :as all] & rest]]
    (cond
      (nil? all) false
      :else (if (seq words)
              (let [res (filter #(not (nil? %))
                                (map (fn [to-word]
                                       (let [ct (change-type from-word to-word)]
                                         (condp = ct
                                           :substitution (substitution? from-word to-word)
                                           :deletion     (deletion? from-word to-word)
                                           :insertion    (insertion? from-word to-word)
                                           :false        nil)))
                                     words))]
                (chains (apply conj rest (map (fn [next-word] [next-word (disj words next-word)]) res))))
              true)))

  (defn word-chains [words]
    (true? (some identity
                 (for [word words] ; creates a lazy sequence
                   (chains [[word (disj words word)]]))))))

(comment
  (= true  (word-chains #{"spout" "pot" "pout" "spot"}))                      ; true
  (= true  (word-chains #{"hat" "coat" "dog" "cat" "oat" "cot" "hot" "hog"})) ; true
  (= false (word-chains #{"cot" "hot" "bat" "fat"}))                          ; true
  (= false (word-chains #{"to" "top" "stop" "tops" "toss"}))                  ; true
  (= true  (word-chains #{"spout" "do" "pot" "pout" "spot" "dot"}))           ; true
  (= true  (word-chains #{"share" "hares" "shares" "hare" "are"}))            ; true
  (= false (word-chains #{"share" "hares" "hare" "are"})))                    ; true

;; ;;;;;;;;;;;;;;;;;;;;;;;;
;; 083 - A HalfTruth (easy)
;; ;;;;;;;;;;;;;;;;;;;;;;;;

(comment
  (defn half-truth [& rest]
    (and (not-every? true? rest) (not-every? false? rest)))

  (= false (half-truth false false))           ; true
  (= true  (half-truth true false))            ; true
  (= false (half-truth true))                  ; true
  (= true  (half-truth false true false))      ; true
  (= false (half-truth true true true))        ; true
  (= true  (half-truth true true true false))) ; true

;; ;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; 084 - Transitive Closure (hard)
;; ;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

(comment
  (defn trans [[[key dests] & rest :as m] acc]
    (if (seq m)
      (let [new-map (conj m acc)
            ways    (apply clojure.set/union (map #(new-map %) dests))]
        (trans (into {} rest) (assoc acc key (clojure.set/union (m key) ways))))
      acc))

  (defn trans-rec [s]
    (let [new-map (trans s {})]
      (if (not= s new-map)
        (trans-rec new-map)
        new-map)))

  (defn trans-closure [s]
    (reduce (fn [acc [k dests]] (apply conj acc (map (fn [x] [k x]) dests))) #{}
            (trans-rec
             (into {} (for [[fst snd] s]
                        {fst #{snd}}))))))

(comment
  (= (trans-closure #{[8 4] [9 3] [4 2] [27 9] [2 1]})                         ; true
     #{[27 9] [27 3] [8 4] [4 2] [9 3] [4 1] [8 2] [8 1] [2 1]})
  (= (trans-closure #{[8 4] [9 3] [4 2] [27 9]})                               ; true
     #{[4 2] [8 4] [8 2] [9 3] [27 9] [27 3]})
  (= (trans-closure #{["cat" "man"] ["man" "snake"] ["spider" "cat"]})         ; true
     #{["cat" "man"] ["cat" "snake"] ["man" "snake"]
       ["spider" "cat"] ["spider" "man"] ["spider" "snake"]})
  (= (trans-closure #{["father" "son"] ["uncle" "cousin"] ["son" "grandson"]}) ; true
     #{["father" "son"] ["father" "grandson"]
       ["uncle" "cousin"] ["son" "grandson"]}))

;; ;;;;;;;;;;;;;;;;;;;;;;;;
;; 085 - Power Set (medium)
;; ;;;;;;;;;;;;;;;;;;;;;;;;

(comment
  ;; Haskell
  ;; type Set a = [a]
  ;;
  ;; powerset :: Set a -> Set (Set a)
  ;; powerset [] = [[]]
  ;; powerset (x:xs) = [x:ps | ps <- powerset xs] ++ powerset xs

  ;; Haskell equivalent
  (defn power-set [[x & xs :as s]]
    (if (seq s)
      (let [ps (power-set xs)]
        (concat (map #(conj % x) ps) ps))
      #{#{}}))

  ;; constructive
  (defn power-set [coll]
    (reduce (fn [a x] (into a (map #(conj % x)) a))
            #{#{}}
            coll))

  (= (power-set #{1 :a})  #{#{1 :a} #{:a} #{} #{1}})                           ; true
  (= (power-set #{1 2 3}) #{#{} #{1} #{2} #{3} #{1 2} #{1 3} #{2 3} #{1 2 3}}) ; true
  (= (count (power-set (into #{} (range 10)))) 1024)                           ; true
  (= (count (power-set (into #{} (range 20)))) 1048576))                       ; true

;; ;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; 086 - Happy numbers (medium)
;; ;;;;;;;;;;;;;;;;;;;;;;;;;;;;

;; def is_happy(number: int) -> bool:
;;     """Determine if the specified number is a happy number."""
;;     seen_numbers = set()
;;     while number > 1 and number not in seen_numbers:
;;         seen_numbers.add(number)
;;         number = pdi_function(number)
;;     return number == 1

(comment
  (defn char->num [c]
    (- (int c) 48))

  (defn sum-squared [n]
    (let [n-str (str n)]
      (apply + (map #(* (char->num %) (char->num %)) n-str))))

  (defn happy-number? [n]
    (loop [n n, seen #{}]
      (cond
        (= 1 n)  true
        (seen n) false
        :else (recur (sum-squared n) (conj seen n)))))

  (= (happy-number? 7)         true)   ; true
  (= (happy-number? 986543210) true)   ; true
  (= (happy-number? 2)         false)  ; true
  (= (happy-number? 3)         false)) ; true

;; ;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; 088 - Symmetric Difference (easy)
;; ;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

(comment
  (defn symmetric-difference [s1 s2]
    (clojure.set/union (clojure.set/difference s1 s2)
                       (clojure.set/difference s2 s1))))

(comment
  (defn symmetric-difference [s1 s2]
    (into (set (remove s1 s2)) (remove s2 s1))))

(comment
  (= (symmetric-difference #{1 2 3 4 5 6} #{1 3 5 7})     #{2 4 6 7})      ; true
  (= (symmetric-difference #{:a :b :c} #{})               #{:a :b :c})     ; true
  (= (symmetric-difference #{} #{4 5 6})                  #{4 5 6})        ; true
  (= (symmetric-difference #{[1 2] [2 3]} #{[2 3] [3 4]}) #{[1 2] [3 4]})) ; true

;; ;;;;;;;;;;;;;;;;;;;;;;;
;; 089 - Graph Tour (hard)
;; ;;;;;;;;;;;;;;;;;;;;;;;

(comment
  (defn remove-nth
    [coll n]
    (into (subvec (vec coll) 0 n) (subvec (vec coll) (inc n))))

  (defn find-dests [[starting-point edges]]
    (concat
     (for [[idx [start end]] (keep-indexed #(vector %1 %2) edges)
           :let [paths (remove-nth edges idx)]
           :when (= start starting-point)]
       [end paths])
     (for [[idx [start end]] (keep-indexed #(vector %1 %2) edges)
           :let [paths (remove-nth edges idx)]
           :when (= end starting-point)]
       [start paths])))

  (defn graph [[[_starting-point edges :as paths] & rest]]
    (if (seq paths)
      (if (seq edges)
        (graph (apply conj rest (find-dests paths)))
        true)
      false))

  (defn graph-tour [g]
    (graph
     (let [starting-points (into #{} (flatten g))]
       (for [starting-point starting-points]
         [starting-point g])))))

(comment
  (= true  (graph-tour [[:a :b]]))                                                 ; true
  (= false (graph-tour [[:a :a] [:b :b]]))                                         ; true
  (= true  (graph-tour [[:a :b] [:a :b] [:a :c] [:c :a] [:a :d]]))                 ; true
  (= false (graph-tour [[:a :b] [:a :b] [:a :c] [:c :a] [:a :d] [:b :d] [:c :d]])) ; true
  (= true  (graph-tour [[1 2] [2 3] [3 4] [4 1]]))                                 ; true
  (= true  (graph-tour [[:a :b] [:a :c] [:c :b] [:a :e]                            ; true
                        [:b :e] [:a :d] [:b :d] [:c :e]
                        [:d :e] [:c :f] [:d :f]]))
  (= false (graph-tour [[1 2] [2 3] [2 4] [2 5]])))                                ; true

;; ;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; 090 - Cartesian Product (easy)
;; ;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

(comment
  (defn cartesian-product [c1 c2]
    (set (for [c1 c1 c2 c2]
           [c1 c2])))

  (= (cartesian-product #{"ace" "king" "queen"} #{"♠" "♥" "♦" "♣"})                ; true
     #{["ace"   "♠"] ["ace"   "♥"] ["ace"   "♦"] ["ace"   "♣"]
       ["king"  "♠"] ["king"  "♥"] ["king"  "♦"] ["king"  "♣"]
       ["queen" "♠"] ["queen" "♥"] ["queen" "♦"] ["queen" "♣"]})
  (= (cartesian-product #{1 2 3} #{4 5}) #{[1 4] [2 4] [3 4] [1 5] [2 5] [3 5]})   ; true
  (= 300 (count (cartesian-product (into #{} (range 10)) (into #{} (range 30)))))) ; true

;; ;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; 091 - Graph Connectivity (hard)
;; ;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

(comment
  (defn connect-edges [edges]
    (set
     (for [edge1 edges
           edge2 edges
           :when (not= edge1 edge2)
           :let [e1      (vec edge1)
                 e2      (vec edge2)
                 e_1_2   (concat e1 e2)
                 e_1_2_d (distinct e_1_2)
                 c1      (count e_1_2)
                 c2      (count e_1_2_d)]
           :when (< c2 c1)]
       (set e_1_2_d))))

  (defn connect-edges-rec [g]
    (let [conncected-edges (connect-edges g)
          num-elements-g   (count (distinct (flatten (map vec g))))
          num-elements-c   (count (distinct (flatten (map vec conncected-edges))))
          num-edges        (count g)]
      (cond
        (= 1 num-edges)                      true
        (= 0 num-edges)                      false
        (not= num-elements-g num-elements-c) false
        :else (connect-edges-rec conncected-edges))))

  (defn graph-connected? [g]
    (connect-edges-rec (set (map set g))))

  (= true  (graph-connected? #{[:a :a]}))                                                  ; true
  (= true  (graph-connected? #{[:a :b]}))                                                  ; true
  (= false (graph-connected? #{[1 2] [2 3] [3 1] [4 5] [5 6] [6 4]}))                      ; true
  (= true  (graph-connected? #{[1 2] [2 3] [3 1] [4 5] [5 6] [6 4] [3 4]}))                ; true
  (= false (graph-connected? #{[:a :b] [:b :c] [:c :d] [:x :y] [:d :a] [:b :e]}))          ; true
  (= true  (graph-connected? #{[:a :b] [:b :c] [:c :d] [:x :y] [:d :a] [:b :e] [:x :a]}))) ; true

;; ;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; 092 - Read Roman numerals (hard)
;; ;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

(comment
  (defn read-roman-numerals [r]
    (->> (reverse (.toUpperCase r))
         (map {\M 1000 \D 500 \C 100 \L 50 \X 10 \V 5 \I 1})
         (partition-by identity)
         (map (partial apply +))
         (reduce #(if (< %1 %2) (+ %1 %2) (- %1 %2))))))

(comment
  (reverse (.toUpperCase "MMMCMXCIX"))                                              ; (\X \I \C \X \M \C \M \M \M)
  (map {\M 1000 \D 500 \C 100 \L 50 \X 10 \V 5 \I 1} '(\X \I \C \X \M \C \M \M \M)) ; (10 1 100 10 1000 100 1000 1000 1000)
  (partition-by identity '(10 1 100 10 1000 100 1000 1000 1000))                    ; ((10) (1) (100) (10) (1000) (100) (1000 1000 1000))
  (map (partial apply +) '((10) (1) (100) (10) (1000) (100) (1000 1000 1000)))      ; (10 1 100 10 1000 100 3000)
  (reductions #(if (< %1 %2) (+ %1 %2) (- %1 %2)) '(10 1 100 10 1000 100 3000)))     ; (10 9 109 99 1099 999 3999)

(comment
  "testing"

  ;; built-in pretty printing for roman numerals stolen from Common Lisp
  (def arabic->roman
    (partial clojure.pprint/cl-format nil "~@R"))

  ;; How many errors? 0!
  (->> (range 1 4000)
       (map (fn [n] [n (read-roman-numerals (arabic->roman n))]))
       (filter (fn [[n1 n2]] (not= n1 n2)))
       count))

;; ;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; 093 - Partially Flatten a Sequence (medium)
;; ;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

(comment
  (defn flatten-partially [[x :as xs]]
    (if (coll? x)
      (mapcat flatten-partially xs)
      [xs]))

  (= (flatten-partially [["Do"] ["Nothing"]])               ; true
     [["Do"] ["Nothing"]])
  (= (flatten-partially [[[[:a :b]]] [[:c :d]] [:e :f]])    ; true
     [[:a :b] [:c :d] [:e :f]])
  (= (flatten-partially '((1 2) ((3 4) ((((5 6)))))))       ; true
     '((1 2) (3 4) (5 6))))

;; ;;;;;;;;;;;;;;;;;;;;;;;;;
;; 094 - Game of Life (hard)
;; ;;;;;;;;;;;;;;;;;;;;;;;;;

;; [Inventing Game of Life (John Conway) - Numberphile](https://www.youtube.com/watch?v=R9Plq-D1gEk)

(comment
  (defn moore-neighborhood [[x y]]
    (for [dx [-1 0 1]
          dy [-1 0 1]
          :when (not (= [dx dy] [0 0]))]
      [(+ x dx) (+ y dy)]))

  (defn step [set-of-cells]
    (set (for [[cell count] (frequencies (mapcat moore-neighborhood set-of-cells))
               :when (or (= count 3)
                         (and (= count 2) (contains? set-of-cells cell)))]
           cell)))

  (defn board-to-set [board rows cols]
    (set
     (for [row (range rows)
           col (range cols)
           :when (= \# (nth (board row) col))]
       [row col])))

  (defn set-to-board [s rows cols]
    (apply map str
           (partition rows
                      (for [col (range cols)
                            row (range rows)]
                        (if (contains? s [row col])
                          \#
                          \space)))))

  (defn game-of-life [board]
    (let [rows (count board)
          cols (count (board 0))]
      (-> board
          (board-to-set rows cols)
          step
          (set-to-board rows cols)))))

(comment
  (= (game-of-life ; true
      ["      "
       " ##   "
       " ##   "
       "   ## "
       "   ## "
       "      "])
     ["      "
      " ##   "
      " #    "
      "    # "
      "   ## "
      "      "])

  (= (game-of-life ; true
      ["     "
       "     "
       " ### "
       "     "
       "     "])
     ["     "
      "  #  "
      "  #  "
      "  #  "
      "     "])

  (= (game-of-life ; true
      ["      "
       "      "
       "  ### "
       " ###  "
       "      "
       "      "])
     ["      "
      "   #  "
      " #  # "
      " #  # "
      "  #   "
      "      "]))

;; ;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; 095 - To Tree, or not to Tree (easy)
;; ;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

(comment
  (defn tree? [tree]
    (or (nil? tree)
        (and (sequential? tree)
             (= 3 (count tree))
             (tree? (nth tree 1))
             (tree? (nth tree 2)))))

  (= (tree? '(:a nil nil))                           true)   ; true
  (= (tree? '(:a (:b nil nil) nil))                  true)   ; true
  (= (tree? '(:a (:b nil nil)))                      false)  ; true
  (= (tree? [1 nil [2 [3 nil nil] [4 nil nil]]])     true)   ; true
  (= (tree? [1 [2 nil nil] [3 nil nil] [4 nil nil]]) false)  ; true
  (= (tree? [1 [2 [3 [4 nil nil] nil] nil] nil])     true)   ; true
  (= (tree? [1 [2 [3 [4 false nil] nil] nil] nil])   false)  ; true
  (= (tree? '(:a nil ()))                            false)) ; true

;; ;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; 096 - Beauty is Symmetry (easy)
;; ;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

(comment
  (defn symmetric-trees? [[val1 left1 right1] [val2 left2 right2]]
    (and (= val1 val2)
         (or (and (nil? left1) (nil? right2))
             (symmetric-trees? left1 right2))
         (or (and (nil? right1) (nil? left2))
             (symmetric-trees? right1 left2))))

  (symmetric-trees? ; false
   '(:b nil nil)
   '(:c nil nil))

  (symmetric-trees? ; false
   '(:b nil nil)
   '(nil))

  (symmetric-trees? ; true
   '(:b nil nil)
   '(:b nil nil))

  (symmetric-trees? ; true
   [2 nil [3 [4 [5 nil nil] [6 nil nil]] nil]]
   [2 [3 nil [4 [6 nil nil] [5 nil nil]]] nil])

  (symmetric-trees? ; false
   [2 nil [3 [4 [5 nil nil] [6 nil nil]] nil]]
   [2 [3 nil [4 [5 nil nil] [6 nil nil]]] nil])

  (symmetric-trees? ; false
   [2 nil [3 [4 [5 nil nil] [6 nil nil]] nil]]
   [2 [3 nil [4 [6 nil nil] nil]] nil]))

(comment
  (defn beauty-is-symmetry [[_val left right]]
    (letfn [(symmetric-trees? [[val1 left1 right1] [val2 left2 right2]]
              (and (= val1 val2)
                   (or (and (nil? left1) (nil? right2))
                       (symmetric-trees? left1 right2))
                   (or (and (nil? right1) (nil? left2))
                       (symmetric-trees? right1 left2))))])
    (symmetric-trees? left right))

  (= (beauty-is-symmetry '(:a (:b nil nil) (:b nil nil)))                                                              true)   ; true
  (= (beauty-is-symmetry '(:a (:b nil nil) nil))                                                                       false)  ; true
  (= (beauty-is-symmetry '(:a (:b nil nil) (:c nil nil)))                                                              false)  ; true
  (= (beauty-is-symmetry [1 [2 nil [3 [4 [5 nil nil] [6 nil nil]] nil]] [2 [3 nil [4 [6 nil nil] [5 nil nil]]] nil]])  true)   ; true
  (= (beauty-is-symmetry [1 [2 nil [3 [4 [5 nil nil] [6 nil nil]] nil]] [2 [3 nil [4 [5 nil nil] [6 nil nil]]] nil]])  false)  ; true
  (= (beauty-is-symmetry [1 [2 nil [3 [4 [5 nil nil] [6 nil nil]] nil]] [2 [3 nil [4 [6 nil nil] nil]] nil]])          false)) ; true

;; ;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; 097 - Pascal's Triangle (easy)
;; ;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

(comment
  (defn pascal-triangle
    ([n] (pascal-triangle n [1]))
    ([n acc]
     (if (= n 1)
       acc
       (pascal-triangle (dec n)
                        (conj (vec (conj (map (partial apply +) (partition 2 1 acc)) 1)) 1)))))

  (= (pascal-triangle 1) [1])          ; true
  (= (map pascal-triangle (range 1 6)) ; true
     [[1]
      [1 1]
      [1 2 1]
      [1 3 3 1]
      [1 4 6 4 1]])
  (= (pascal-triangle 11) [1 10 45 120 210 252 210 120 45 10 1])  ; true
  (= (pascal-triangle 11) [1 10 45 120 210 252 210 120 45 10 1])) ; true

;; ;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; 098 - Equivalence Classes (medium)
;; ;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

(comment
  (defn equivalence-rel [f coll]
    (->> coll
         (group-by f)
         vals
         (map set)
         set))

  (= (equivalence-rel #(* % %) #{-2 -1 0 1 2})        ; true
     #{#{0} #{1 -1} #{2 -2}})
  (= (equivalence-rel #(rem % 3) #{0 1 2 3 4 5})      ; true
     #{#{0 3} #{1 4} #{2 5}})
  (= (equivalence-rel identity #{0 1 2 3 4})          ; true
     #{#{0} #{1} #{2} #{3} #{4}})
  (= (equivalence-rel (constantly true) #{0 1 2 3 4}) ; true
     #{#{0 1 2 3 4}}))

;; ;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; 099 - Product Digits (easy)
;; ;;;;;;;;;;;;;;;;;;;;;;;;;;;

(comment
  (defn product-digits [n1 n2]
    (map #(Character/digit % 10) (str (* n1 n2)))))

(comment
  (= (product-digits  1 1) [1])             ; true
  (= (product-digits 99 9) [8 9 1])         ; true
  (= (product-digits  999 99) [9 8 9 0 1])) ; true

;; ;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; 100 - Least Common Multiple (easy)
;; ;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

(defn gcd [a b]
  (if (zero? b)
    a
    (recur b, (mod a b))))

(defn lcm [a b]
  (/ (* a b) (gcd a b)))

(defn least-common-multiple [& n] (reduce lcm n))

(comment
  (== (least-common-multiple 2 3) 6)            ; true
  (== (least-common-multiple 5 3 7) 105)        ; true
  (== (least-common-multiple 1/3 2/5) 2)        ; true
  (== (least-common-multiple 3/4 1/6) 3/2)      ; true
  (== (least-common-multiple 7 5/7 2 3/5) 210)) ; true

;; ;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; 101 - Levenshtein Distance (hard)
;; ;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

;; https://en.wikipedia.org/wiki/Levenshtein_distance
;; https://de.wikipedia.org/wiki/Levenshtein-Distanz

;; https://python-course.eu/applications-python/levenshtein-distance.php

(comment
 ;; slow (recursive)
  (defn levenshtein-distance [w1 w2]
    (let [len1 (count w1)
          len2 (count w2)]
      (cond (zero? len1) len2
            (zero? len2) len1
            :else
            (let [cost (if (= (first w1) (first w2)) 0 1)]
              (min (inc    (levenshtein-distance (rest w1) w2))
                   (inc    (levenshtein-distance w1        (rest w2)))
                   (+ cost (levenshtein-distance (rest w1) (rest w2))))))))

 ;; fast (constructive = dynamic programming)
  (defn levenshtein-distance [w1 w2]
    (let [num-cols (inc (count w1))
          num-rows (inc (count w2))]
      (letfn [(distance [prev-row cur-row row-idx col-idx]
                (let [ch1 (nth w1 (dec col-idx))
                      ch2 (nth w2 (dec row-idx))]
                  (min (inc (nth prev-row col-idx))                              ; insertion
                       (inc (last cur-row))                                      ; deletion
                       (+ (nth prev-row (dec col-idx)) (if (= ch1 ch2) 0 1)))))] ; substitution
        (loop [row-idx  1
               prev-row (range (inc (count w1)))]
          (if (= row-idx num-rows)
            (last prev-row)
            (let [next-prev-row (reduce (fn [cur-row col-idx]
                                          (conj cur-row (distance prev-row cur-row row-idx col-idx)))
                                        [row-idx]
                                        (range 1 num-cols))]
              (recur (inc row-idx) next-prev-row))))))))

(comment
  (= (levenshtein-distance "Clojure" "Clojure")                  ; true
     (levenshtein-distance "" "")
     (levenshtein-distance [] [])
     0)
  (= (levenshtein-distance "closure" "clojure")                  ; true
     (levenshtein-distance "clojure" "closure")
     1)
  (= (levenshtein-distance "xyx" "xyyyx") 2)                     ; true
  (= (levenshtein-distance [1 2 3 4] [0 2 3 4 5]) 2)             ; true
  (= (levenshtein-distance '(:a :b :c :d) '(:a :d)) 2)           ; true
  (= (levenshtein-distance "kitten" "sitting") 3)                ; true
  (= (levenshtein-distance "" "123456") 6)                       ; true
  (= (levenshtein-distance "gaattctaatctc" "caaacaaaaaattt") 9)  ; true
  (= (levenshtein-distance "ttttattttctg" "tcaaccctaccat") 10))  ; true

;; ;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; 102 - intoCamelCase (medium)
;; ;;;;;;;;;;;;;;;;;;;;;;;;;;;;

(comment
  (defn into-camel-case [s]
    (clojure.string/replace s #"-\w" #(.toUpperCase (subs % 1 2))))

  (= (into-camel-case "something")      "something")     ; true
  (= (into-camel-case "multi-word-key") "multiWordKey")  ; true
  (= (into-camel-case "leaveMeAlone")   "leaveMeAlone")) ; true

;; ;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; 103 - Generating k-combinations (medium)
;; ;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

(comment
  (defn remove-nth
    [coll n]
    (into (subvec (vec coll) 0 n) (subvec (vec coll) (inc n))))

  (defn permutations [coll]
    (if (= 1 (count coll))
      (list coll)
      (for [idx  (range (count coll))
            tail (permutations (remove-nth coll idx))]
        (cons (nth coll idx) tail))))

  (defn take-me [n coll]
    (let [res (take n coll)]
      (when (= n (count res))
        res)))

  (defn k-combinations [n s]
    (let [res (set (map #(set (take-me n %)) (permutations (vec s))))]
      (if (= res #{#{}})
        #{}
        res))))

(comment
  (permutations [4 5 6])                  ; ((4 5 6) (4 6 5) (5 4 6) (5 6 4) (6 4 5) (6 5 4))
  (permutations [0 1 2])                  ; ((0 1 2) (0 2 1) (1 0 2) (1 2 0) (2 0 1) (2 1 0))
  (permutations [0 1 2 3 4])
  (permutations [[1 2 3] :a "abc" "efg"])

  (set [(set []) (set [])])

  (vec #{1 2 3})) ; [1 3 2]

(comment
  (defn k-combinations [k s]
    (cond
      (zero?  k) #{#{}}
      (empty? s) #{}
      :else (set (for [i s
                       x (k-combinations (dec k) (disj s i))]
                   (conj x i))))))

(comment
  (= (k-combinations  1 #{4 5 6})                  #{#{4} #{5} #{6}})                                                                            ; true
  (= (k-combinations  2 #{0 1 2})                  #{#{0 1} #{0 2} #{1 2}})                                                                      ; true
  (= (k-combinations 10 #{4 5 6})                  #{})                                                                                          ; true
  (= (k-combinations  3 #{0 1 2 3 4})              #{#{0 1 2} #{0 1 3} #{0 1 4} #{0 2 3} #{0 2 4} #{0 3 4} #{1 2 3} #{1 2 4} #{1 3 4} #{2 3 4}}) ; true
  (= (k-combinations  4 #{[1 2 3] :a "abc" "efg"}) #{#{[1 2 3] :a "abc" "efg"}})                                                                 ; true
  (= (k-combinations  2 #{[1 2 3] :a "abc" "efg"}) #{#{[1 2 3] :a} #{[1 2 3] "abc"} #{[1 2 3] "efg"} #{:a "abc"} #{:a "efg"} #{"abc" "efg"}}))   ; true

;; ;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; 104 - Write Roman Numerals (medium)
;; ;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

(comment
  (def arabic-roman-map
    [[1000 "M"]  [900 "CM"] [500 "D"]  [400 "CD"] [100 "C"]
     [90   "XC"] [50  "L"]  [40  "XL"] [10  "X"]
     [9    "IX"] [5   "V"]  [4   "IV"] [1   "I"]])

  (defn d2r
    ([n]
     (apply str (d2r n arabic-roman-map [])))
    ([n [[arabic roman] & rest-arabic-roman-map :as arabic-roman-map] acc]
     (if (zero? n)
       acc
       (if (>= n arabic)
         (recur (- n arabic) arabic-roman-map      (conj acc roman))
         (recur n            rest-arabic-roman-map acc))))))

(comment
  ;; cheating solution (but very useful for testing)
  (def arabic->roman
    (partial clojure.pprint/cl-format nil "~@R"))

  ;; how many errors?
  (->> (range 1 4000)                                ; 0
       (map (fn [n] [n (arabic->roman n) (d2r n)]))
       (filter (fn [[_n r1 r2]] (not= r1 r2)))
       count))

(comment
  (= "I"         (d2r 1))     ; true
  (= "IV"        (d2r 4))     ; true
  (= "XXX"       (d2r 30))    ; true
  (= "XLVIII"    (d2r 48))    ; true
  (= "CXL"       (d2r 140))   ; true
  (= "DCCCXXVII" (d2r 827))   ; true
  (= "MMMCMXCIX" (d2r 3999))  ; true

  (= "I"         (arabic->roman 1))     ; true
  (= "IV"        (arabic->roman 4))     ; true
  (= "XXX"       (arabic->roman 30))    ; true
  (= "XLVIII"    (arabic->roman 48))    ; true
  (= "CXL"       (arabic->roman 140))   ; true
  (= "DCCCXXVII" (arabic->roman 827))   ; true
  (= "MMMCMXCIX" (arabic->roman 3999))) ; true

;; ;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; 105 - Identify keys and values (medium)
;; ;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

(comment
  (into {} '([:a [1 2 3]] [:c [3]] [:b []]))                  ; {:a [1 2 3], :c [3], :b []}
  (into {} (conj '([:a [1 2 3]] [:c [3]] [:b []]) [:d [4]]))) ; {:d [4], :a [1 2 3], :c [3], :b []}

(comment
  (defn identify-kv [coll]
    (into {}
          (reduce
           (fn [[[k val-list] & rest :as acc] key-or-val]
             (if (keyword? key-or-val)
               (conj acc  [key-or-val []])
               (conj rest [k (conj val-list key-or-val)])))
           []
           coll)))

  (= {}                              (identify-kv []))                     ; true
  (= {:a [1]}                        (identify-kv [:a 1]))                 ; true
  (= {:a [1] :b [2]}                 (identify-kv [:a 1, :b 2]))           ; true
  (= {:a [1 2 3] :b [] :c [4]}       (identify-kv [:a 1 2 3 :b :c 4]))     ; true
  (= {:a [1 2 3] :b [] :c [] :d [4]} (identify-kv [:a 1 2 3 :b :d 4 :c]))) ; true

;; ;;;;;;;;;;;;;;;;;;;;;;;;
;; 106 - Number Maze (hard)
;; ;;;;;;;;;;;;;;;;;;;;;;;;

(comment
  (defn number-maze-rec [seen [[start end num-steps] & rest]]
    (if (= start end)
      num-steps
      (recur
       (conj seen start)
       (let [dble (* start 2)
             half (/ start 2)
             add2 (+ start 2)]
         (concat rest (when-not (contains? seen dble)
                        [[dble end (inc num-steps)]])
                 (when (and (not (contains? seen half)) (even? start))
                   [[half end (inc num-steps)]])
                 (when-not (contains? seen add2)
                   [[add2 end (inc num-steps)]]))))))

  (defn number-maze [start end]
    (number-maze-rec #{} [[start end 0]])))

(comment
  (= 0 (number-maze 1  1))  ; true
  (= 2 (number-maze 3  12)) ; true
  (= 2 (number-maze 12 3))  ; true
  (= 2 (number-maze 5  9))  ; true
  (= 4 (number-maze 9  12)) ; true
  (= 8 (number-maze 9  2))) ; true

;; ;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; 107 - Simple closures (easy)
;; ;;;;;;;;;;;;;;;;;;;;;;;;;;;;

(comment
  (defn pow-int [n]
    (fn [exp]
      (apply * (repeat n exp)))))

(comment
  (= 256 ((pow-int 2) 16), ((pow-int 8) 2))            ; true
  (= [1 8 27 64] (map (pow-int 3) [1 2 3 4]))          ; true
  (= [1 2 4 8 16] (map #((pow-int %) 2) [0 1 2 3 4]))) ; true

;; ;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; 108 - Lazy Searching (medium)
;; ;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

(comment
  (defn lazy-search [& colls]
    (let [firsts (map first colls)]
      (if (apply = firsts)
        (first firsts)
        (apply lazy-search
               (map
                #(drop-while (partial > (apply max firsts)) %)
                colls)))))

  (= 3  (lazy-search [3 4 5]))                                     ; true
  (= 4  (lazy-search [1 2 3 4 5 6 7] [0.5 3/2 4 19]))              ; true
  (= 64 (lazy-search (map #(* % % %) (range))                      ; true
                     (filter #(zero? (bit-and % (dec %))) (range))
                     (iterate inc 20)))
  (= 7  (lazy-search (range)                                       ; true
                     (range 0 100 7/6)
                     [2 3 5 7 11 13])))

;; ;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; 110 - Sequence of pronunciations (medium)
;; ;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

(comment
  (defn pronunciations [coll]
    (rest
     (iterate #(->> %
                    (partition-by identity)
                    (mapcat (juxt count first)))
              coll)))

  (= [3 1 2 4]               (first      (pronunciations [1 1 1 4 4]))) ; true
  (= [[1 1] [2 1] [1 2 1 1]] (take 3     (pronunciations [1])))         ; true
  (= [1 1 1 3 2 1 3 2 1 1]   (nth        (pronunciations [1]) 6))       ; true
  (= 338                     (count (nth (pronunciations [3 2]) 15)))   ; true
  (= 1282                    (count (nth (pronunciations [3 2]) 20))))  ; true

;; ;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; 111 - Crossword puzzle (hard)
;; ;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

#_{:clj-kondo/ignore [:unused-binding]}
(defn crossword-puzzle [word board])

(comment
  (= true  (crossword-puzzle "the" ["_ # _ _ e"]))
  (= false (crossword-puzzle "the" ["c _ _ _"
                                    "d _ # e"
                                    "r y _ _"]))
  (= true  (crossword-puzzle "joy" ["c _ _ _"
                                    "d _ # e"
                                    "r y _ _"]))
  (= false (crossword-puzzle "joy" ["c o n j"
                                    "_ _ y _"
                                    "r _ _ #"]))
  (= true  (crossword-puzzle "clojure" ["_ _ _ # j o y"
                                        "_ _ o _ _ _ _"
                                        "_ _ f _ # _ _"])))

;; ;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; 112 - Sequs Horribilis (medium)
;; ;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

(comment
  (defn sequs-horribilis [n [head & tail]]
    (when head
      (if (number? head)
        (if (<= head n)
          (conj (sequs-horribilis (- n head) tail) head)
          (list))
        (list (sequs-horribilis n head)))))

  (= (sequs-horribilis 10 [1 2 [3 [4 5] 6] 7])                 '(1 2 (3 (4))))             ; true
  (= (sequs-horribilis 1 [1])                                  '(1))                       ; true
  (= (sequs-horribilis 1 [[[[[1]]]]])                          '(((((1))))))               ; true
  (= (sequs-horribilis 9 (range))                              '(0 1 2 3))                 ; true
  (= (sequs-horribilis 30 [1 2 [3 [4 [5 [6 [7 8]] 9]] 10] 11]) '(1 2 (3 (4 (5 (6 (7))))))) ; true
  (= (sequs-horribilis 9 (range))                              '(0 1 2 3))                 ; true
  (= (sequs-horribilis 0 [1 2 [3 [4 5] 6] 7])                  '())                        ; true
  (= (sequs-horribilis 0 [0 0 [0 [0]]])                        '(0 0 (0 (0))))             ; true
  (= (sequs-horribilis 1 [-10 [1 [2 3 [4 5 [6 7 [8]]]]]])      '(-10 (1 (2 3 (4))))))      ; true

;; ;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; 114 - Global takewhile (medium)
;; ;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

(comment
  (defn global-take-while [n p [x & xs]]
    (if (p x)
      (if (= n 1)
        nil
        (cons x (global-take-while (dec n) p xs)))
      (cons x (global-take-while n p xs))))

  (= [2 3]                        (global-take-while 2 #(= 2 (mod % 3)) [2 3 5 7 11 13 17 19 23])) ; 2,5,11,17,23 ; true
  (= [2 3 5 7 11 13]              (global-take-while 4 #(= 2 (mod % 3)) [2 3 5 7 11 13 17 19 23])) ; 2,5,11,17,23 ; true
  (= [2 3 5 7 11 13 17 19]        (global-take-while 5 #(= 2 (mod % 3)) [2 3 5 7 11 13 17 19 23])) ; 2,5,11,17,23 ; true
  (= ["this" "is" "a" "sentence"] (global-take-while 3 #(some #{\i} %) ["this" "is" "a" "sentence" "i" "wrote"])) ; true
  (= ["this" "is"]                (global-take-while 1 #{"a"} ["this" "is" "a" "sentence" "i" "wrote"])))         ; true

;; ;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; 115 - The Balance of N (medium)
;; ;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

(comment
  (defn char-to-int [d]
    (- (int d) 48))

  (defn balanced? [n]
    (let [n-str     (str n)
          n-str-len (count n-str)
          count     (clojure.math/floor-div n-str-len 2)
          fst       (apply + (map char-to-int (take count n-str)))
          snd       (apply + (map char-to-int (take-last count n-str)))]
      (= fst snd)))

  (= true     (balanced? 0))     ; true
  (= true     (balanced? 11))    ; true
  (= true     (balanced? 121))   ; true
  (= true     (balanced? 89098)) ; true
  (= true     (balanced? 89089)) ; true
  (= false    (balanced? 123))   ; true
  (= false    (balanced? 88099)) ; true
  (= (take 20 (filter balanced? (range))) ; true
     [0 1 2 3 4 5 6 7 8 9 11 22 33 44 55 66 77 88 99 101]))

;; ;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; 116 - Prime Sandwich (medium)
;; ;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

(comment
  "taken from 064"

  (defn prime? [n [prime & primes]]
    (cond
      (not prime)           true
      (zero? (mod n prime)) false
      :else                 (recur n primes)))

  (defn next-primes
    ([primes]
     (next-primes (+ 2 (last primes)) primes))
    ([candidate primes]
     (if (prime? candidate primes)
       (conj primes candidate)
       (next-primes (+ 2 candidate) primes))))

  (defn prime-sandwich? [n]
    (loop [primes [2 3 5]]
      (let [[l p r] (take-last 3 primes)]
        (if (= p n)
          (= (+ l r) (* 2 p))
          (if (> p n)
            false
            (recur (next-primes primes))))))))

(comment
  (= false (prime-sandwich? 4))                        ; true
  (= true  (prime-sandwich? 563))                      ; true
  (= 1103  (nth (filter prime-sandwich? (range)) 15))) ; true

;; ;;;;;;;;;;;;;;;;;;;;;;;;;
;; 117 - For Science! (hard)
;; ;;;;;;;;;;;;;;;;;;;;;;;;;

(comment
  (defn von-neumann-neighborhood [[x y] max-x-dim max-y-dim]
    (filter (fn [[x y]] (and (>= x 0)
                             (>= y 0)
                             (< x max-x-dim)
                             (< y max-y-dim)))
            [[x       (- y 1)]
             [x       (+ y 1)]
             [(- x 1) y]
             [(+ x 1) y]]))

  (defn find-items [maze item]
    (let [max-x-dim (count maze)
          max-y-dim (count (maze 0))]
      (for [x (range max-x-dim)
            y (range max-y-dim)
            :when (= item (get-in maze [x y]))]
        [x y])))

  (defn find-mouse [maze]
    (first (find-items maze \M)))

  (defn find-cheese [maze]
    (first (find-items maze \C)))

  (defn candidates [maze]
    (let [max-x-dim (count maze)
          max-y-dim (count (maze 0))
          mouse     (find-mouse maze)
          neighbors (von-neumann-neighborhood mouse max-x-dim max-y-dim)]
      (filter #(= (get-in maze %) \space) neighbors)))

  (defn cheese-found? [maze]
    (first
     (let [max-x-dim (count maze)
           max-y-dim (count (maze 0))
           mouse-pos (find-mouse maze)]
       (for [neighbor (von-neumann-neighborhood mouse-pos max-x-dim max-y-dim)
             :when (= \C (get-in maze neighbor))]
         true))))

  (defn new-mouse-positions [maze seen]
    (let [mouse-pos (find-mouse maze)]
      (filter (fn [x] (not= x nil))
              (map #(when (not (contains? seen %))
                      (assoc-in (assoc-in maze % \M) mouse-pos \space))
                   (candidates maze)))))

  (defn for-science
    ([maze]
     (for-science [(vec (map #(vec (map identity %)) maze))] #{}))
    ([[maze & rest] seen]
     (if (seq maze)
       (if (cheese-found? maze)
         true
         (if (seen maze)
           (for-science rest seen)
           (for-science
            (apply conj rest (new-mouse-positions maze seen))
            (conj seen (find-mouse maze)))))
       false))))

(comment
  (= true  (for-science ["M   C"]))     ; true
  (= true  (for-science ["C   M"]))     ; true
  (= true  (for-science ["C   M"        ; true
                         "     "]))
  (= false (for-science ["M # C"]))     ; true
  (= true  (for-science ["#######"      ; true
                         "#     #"
                         "#  #  #"
                         "#M # C#"
                         "#######"]))
  (= false (for-science ["########"     ; true
                         "#M  #  #"
                         "#   #  #"
                         "# # #  #"
                         "#   #  #"
                         "#  #   #"
                         "#  # # #"
                         "#  #   #"
                         "#  #  C#"
                         "########"]))
  (= false (for-science ["M     "       ; true
                         "      "
                         "    ##"
                         "    #C"]))
  (= false (for-science ["M     "       ; true
                         "      "
                         "      "
                         "      "
                         "    ##"
                         "    #C"]))
  (= false (for-science ["      "       ; true
                         " M    "
                         "      "
                         "      "
                         "    ##"
                         "    #C"]))
  (= true  (for-science ["C######"      ; true
                         " #     "
                         " #   # "
                         " #   #M"
                         "     # "]))
  (= true  (for-science ["C# # # #"     ; true
                         "        "
                         "# # # # "
                         "        "
                         " # # # #"
                         "        "
                         "# # # #M"])))

;; ;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; 118 - Reimplement Map (easy)
;; ;;;;;;;;;;;;;;;;;;;;;;;;;;;;

(comment
  (defn map-me [f [head & rest]]
    (lazy-seq
     (when head
       (cons (f head) (map-me f rest))))))

(comment
  (map-me inc (range))) ; (1 2 3 4 5 6 7 8 9 10 11 …)

(comment
  (= [3 4 5 6 7]     (map-me inc [2 3 4 5 6]))         ; true
  (= (repeat 10 nil) (map-me (fn [_] nil) (range 10))) ; true
  (= [1000000 1000001]                                 ; true
     (->> (map-me inc (range))
          (drop (dec 1000000))
          (take 2)))
  (= [1000000 1000001]                                 ; true
     (->> (map-me inc (range))
          (drop (dec 1000000))
          (take 2))))

;; ;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; 119 - Win at TicTacToe (hard)
;; ;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

(comment
  (defn win? [piece board]
    (let [coords ['((0 0) (0 1) (0 2))   ; rows (3)
                  '((1 0) (1 1) (1 2))
                  '((2 0) (2 1) (2 2))
                  '((0 0) (1 0) (2 0))   ; cols (3)
                  '((0 1) (1 1) (2 1))
                  '((0 2) (1 2) (2 2))
                  '((0 0) (1 1) (2 2))   ; diag (2)
                  '((0 2) (1 1) (2 0))]]
      (loop [[[a b c :as cs] & tail] coords]
        (if cs
          (let [fst (get-in board a)
                snd (get-in board b)
                trd (get-in board c)]
            (if (= piece fst snd trd)
              true
              (recur tail)))
          false))))

  (defn find-holes [board]
    (for [x (range 3)
          y (range 3)
          :let [p (get-in board [x y])]
          :when (= p :e)]
      [x y]))

  (defn win [piece board]
    (let [holes (find-holes board)]
      (set
       (filter
        (fn [hole] (win? piece (assoc-in board hole piece)))
        holes)))))

(comment
  (= (win :x [[:o :e :e]     ; true
              [:o :x :o]
              [:x :x :e]])
     #{[2 2] [0 1] [0 2]})
  (= (win :x [[:x :o :o]     ; true
              [:x :x :e]
              [:e :o :e]])
     #{[2 2] [1 2] [2 0]})
  (= (win :x [[:x :e :x]     ; true
              [:o :x :o]
              [:e :o :e]])
     #{[2 2] [0 1] [2 0]})
  (= (win :x [[:x :x :o]     ; true
              [:e :e :e]
              [:e :e :e]])
     #{})
  (= (win :o [[:x :x :o]     ; true
              [:o :e :o]
              [:x :e :e]])
     #{[2 2] [1 1]}))

;; ;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; 120 - Sum of square of digits (easy)
;; ;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

(comment
  (defn char-to-int [d]
    (- (int d) 48)))

(comment
  (defn square [n]
    (* n n)))

(comment
  (map char-to-int [\0 \1 \2 \3 \4 \5 \6 \7 \8 \9])) ; (0 1 2 3 4 5 6 7 8 9)

(comment
  (map char-to-int (str 12345))                                           ; (1 2 3 4 5)
  (map square (map char-to-int (str 12345)))                              ; (1 4 9 16 25)
  (apply + (map square (map char-to-int (str 12345))))                    ; 55
  (< 12345 (apply + (map square (map char-to-int (str 12345)))))          ; false
  (< 10 (apply + (map square (map char-to-int (str 10)))))                ; false
  (< 15 (apply + (map square (map char-to-int (str 15))))))               ; true

(comment
  (defn sum-of-square-of-digits [coll]
    (letfn [(chars-to-ints [coll]     (map #(- (int %) 48) coll))           ; e.g. (1 2 3 4 5)
            (squares [coll]           (map #(* % %) (chars-to-ints coll)))  ; e.g. (1 4 9 16 25)
            (sum-of-squares [coll]    (apply + (squares coll)))             ; e.g. 55
            (small-sum-of-squares [n] (< n (sum-of-squares (str n))))]
      (count (filter small-sum-of-squares coll)))))

(comment
  (= 8  (sum-of-square-of-digits (range 10)))    ; true
  (= 19 (sum-of-square-of-digits (range 30)))    ; true
  (= 50 (sum-of-square-of-digits (range 100)))   ; true
  (= 50 (sum-of-square-of-digits (range 1000)))) ; true

;; ;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; 121 - Universal Computation Engine (medium)
;; ;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

;; `uce` returns a function that takes bindings
(comment
  (defn uce [formula]
    (fn [bindings]
      (eval
       (map #(cond
               (symbol? %) (% bindings %)
               (list? %)   ((uce %) bindings)
               :else %)
            formula))))

  (= 2        ((uce '(/ a b))   '{b 8, a 16}))                 ; true
  (= 8        ((uce '(+ a b 2)) '{a 2, b 4}))                  ; true
  (= [6 0 -4] (map (uce '(* (+ 2 a) (- 10 b)))                 ; true
                   '[{a 1, b 8}
                     {b 5, a -2}
                     {a 2, b 11}]))
  (= 1        ((uce '(/ (+ x 2) (* 3 (+ y 1)))) '{x 4, y 1}))) ; true

;; ;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; 122 - Read a binary number (easy)
;; ;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

(comment
  (def powers-of-two
    (iterate #(* 2 %) 1)))

(comment
  (defn binary-to-digit [n]
    (if (= n \0)
      0
      1)))

(comment
  (map binary-to-digit "101010")                               ; (1 0 1 0 1 0)
  (map vector (reverse '(1 0 1 0 1 0)) powers-of-two)          ; ([0 1] [1 2] [0 4] [1 8] [0 16] [1 32])
  (map #(apply * %) '([0 1] [1 2] [0 4] [1 8] [0 16] [1 32]))  ; (0 2 0 8 0 32)
  (apply + '(0 2 0 8 0 32))) ; 42

(comment
  (defn read-binary-number [str]
    (let [powers-of-two (iterate #(* 2 %) 1)]
      (letfn [(char-to-bit [c] ({\0 0, \1 1} c))
              (list-of-digits [str] (map char-to-bit str))                                            ; e.g. (1 0 1 0 1 0)
              (list-of-digits-pairs [str] (map vector (reverse (list-of-digits str)) powers-of-two))  ; e.g. ([0 1] [1 2] [0 4] [1 8] [0 16] [1 32])
              (list-of-values [str] (map #(apply * %) (list-of-digits-pairs str)))]                   ; e.g. (0 2 0 8 0 32)
        (apply + (list-of-values str))))))

(comment
  (defn read-binary-number [str]
    (let [char->bit (fn [c] ({\0 0 \1 1} c))
          step (fn [acc c]
                 (+ (* 2 acc)
                    (char->bit c)))]
      (reduce step 0 str))))

(comment
  (defn read-binary-number [s]
    (->> s
         (str "2r")
         (read-string))))

(comment
  (str "2r" "1111111111111111")                ; "2r1111111111111111"
  (read-string (str "2r" "1111111111111111"))) ; 65535

(comment
  (= 0     (read-binary-number "0"))                 ; true
  (= 7     (read-binary-number "111"))               ; true
  (= 8     (read-binary-number "1000"))              ; true
  (= 9     (read-binary-number "1001"))              ; true
  (= 255   (read-binary-number "11111111"))          ; true
  (= 1365  (read-binary-number "10101010101"))       ; true
  (= 65535 (read-binary-number "1111111111111111"))) ; true

;; ;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; 124 - Analyze Reversi (hard)
;; ;;;;;;;;;;;;;;;;;;;;;;;;;;;;

(comment
  (defn add-coords [c1 c2]
    (map + c1 c2))

  (def coords
    [[[{:w :b, :b :w}, [-1 +0]], [{:w :b, :b :w}, [-2 +0]], [{:w :e, :b :e}, [-3 +0]]]
     [[{:w :b, :b :w}, [+1 +0]], [{:w :b, :b :w}, [+2 +0]], [{:w :e, :b :e}, [+3 +0]]]
     [[{:w :b, :b :w}, [+0 -1]], [{:w :b, :b :w}, [+0 -2]], [{:w :e, :b :e}, [+0 -3]]]
     [[{:w :b, :b :w}, [+0 +1]], [{:w :b, :b :w}, [+0 +2]], [{:w :e, :b :e}, [+0 +3]]]
     [[{:w :b, :b :w}, [-1 -1]], [{:w :b, :b :w}, [-2 -2]], [{:w :e, :b :e}, [-3 -3]]]
     [[{:w :b, :b :w}, [-1 +1]], [{:w :b, :b :w}, [-2 +2]], [{:w :e, :b :e}, [-3 +3]]]
     [[{:w :b, :b :w}, [+1 -1]], [{:w :b, :b :w}, [+2 -2]], [{:w :e, :b :e}, [+3 -3]]]
     [[{:w :b, :b :w}, [+1 +1]], [{:w :b, :b :w}, [+2 +2]], [{:w :e, :b :e}, [+3 +3]]]
     [[{:w :b, :b :w}, [-1 +0]], [{:w :e, :b :e}, [-2 +0]]]
     [[{:w :b, :b :w}, [+1 +0]], [{:w :e, :b :e}, [+2 +0]]]
     [[{:w :b, :b :w}, [+0 -1]], [{:w :e, :b :e}, [+0 -2]]]
     [[{:w :b, :b :w}, [+0 +1]], [{:w :e, :b :e}, [+0 +2]]]
     [[{:w :b, :b :w}, [-1 -1]], [{:w :e, :b :e}, [-2 -2]]]
     [[{:w :b, :b :w}, [-1 +1]], [{:w :e, :b :e}, [-2 +2]]]
     [[{:w :b, :b :w}, [+1 +1]], [{:w :e, :b :e}, [+2 +2]]]
     [[{:w :b, :b :w}, [+1 -1]], [{:w :e, :b :e}, [+2 -2]]]])

 ;; find coordinates of pieces of given color
  (defn color-pos [board color]
    (for [x (range 4)
          y (range 4)
          :when (= color (get-in board [x y]))]
      [x y]))

  (defn valid-moves [board color pos]
    (filter (partial every? identity)
            (map
             #(map
               (fn [[color-map direction]] (let [reverse-color-pos (add-coords pos direction)]
                                             (when (= (color-map color) (get-in board reverse-color-pos))
                                               reverse-color-pos)))
               %)
             coords)))

  (defn moves [board color]
    (mapcat #(valid-moves board color %) (color-pos board color)))

  (defn reversi [board color]
    (reduce
     (fn [acc l]
       (let [destination    (last l)
             flipped-colors (reduce conj #{} (butlast l))]
         (conj acc [destination flipped-colors])))
     {}
     (moves board color))))

(comment
  (= {[1 3] #{[1 2]}         ; true
      [0 2] #{[1 2]}
      [3 1] #{[2 1]}
      [2 0] #{[2 1]}}
     (reversi
      [[:e :e :e :e]
       [:e :w :b :e]
       [:e :b :w :e]
       [:e :e :e :e]] :w))
  (= {[3 2] #{[2 2]}         ; true
      [3 0] #{[2 1]}
      [1 0] #{[1 1]}}
     (reversi
      [[:e :e :e :e]
       [:e :w :b :e]
       [:w :w :w :e]
       [:e :e :e :e]] :b))
  (= {[0 3] #{[1 2]}         ; true
      [1 3] #{[1 2]}
      [3 3] #{[2 2]}
      [2 3] #{[2 2]}}
     (reversi
      [[:e :e :e :e]
       [:e :w :b :e]
       [:w :w :b :e]
       [:e :e :b :e]] :w))
  (= {[0 3] #{[2 1] [1 2]}   ; true
      [1 3] #{[1 2]}
      [2 3] #{[2 1] [2 2]}}
     (reversi
      [[:e :e :w :e]
       [:b :b :w :e]
       [:b :w :w :e]
       [:b :w :w :w]] :b)))

;; ;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; 125 - Gus' Quinundrum (hard)
;; ;;;;;;;;;;;;;;;;;;;;;;;;;;;;

(comment
  "example for a quine"

  (fn []
    (let [s (str
             (quote (fn []
                      (let [s (str (quote %s))]
                        (format s s)))))]
      (format s s))))

(= (str '(fn []                                            ; true
           (let [s (str
                    (quote (fn []
                             (let [s (str (quote %s))]
                               (format s s)))))]
             (format s s))))
   ((fn []
      (let [s (str
               (quote (fn []
                        (let [s (str (quote %s))]
                          (format s s)))))]
        (format s s)))))

;; ;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; 126 - Through the Looking Class (easy)
;; ;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

(comment
  Class         ; java.lang.Class
  (class Class)) ; java.lang.Class

(comment
  (let [x Class]
    (and (= (class x) x) x)))

;; ;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; 128 - Recognize Playing Cards (easy)
;; ;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

(comment
  (def card-map
    {\2 0, \3 1, \4 2, \5 3, \6 4, \7 5, \8 6, \9 7, \T 8, \J 9, \Q 10, \K 11, \A 12, \S :spade, \H :heart, \D :diamond, \C :club}))

(comment
  (map card-map "DQ")) ; (:diamond 10)

(comment
  (first "DQ")   ; \D
  (second "DQ")) ; \Q

(comment
  (defn read-card [[suit rank]]
    (let [suit-map {\S :spade, \H :heart, \D :diamond, \C :club}
          rank-map {\2 0, \3 1, \4 2, \5 3, \6 4, \7 5, \8 6, \9 7, \T 8, \J 9, \Q 10, \K 11, \A 12}]
      {:suit (suit-map suit), :rank (rank-map rank)})))

(comment
  (read-card "DQ")) ; {:suit :diamond, :rank 10}

(comment
  (= {:suit :diamond :rank 10} (read-card  "DQ"))  ; true
  (= {:suit :heart :rank 3}    (read-card  "H5"))  ; true
  (= {:suit :club :rank 12}    (read-card  "CA"))  ; true
  (= (range 13) (map (comp :rank read-card str)    ; true
                     '[S2 S3 S4 S5 S6 S7
                       S8 S9 ST SJ SQ SK SA])))

;; ;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; 131 - Sum Some Set Subsets (medium)
;; ;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

(comment
  "using k-combinations from 102"
  "very slow"

  (defn k-combinations [k s]
    (cond
      (zero?  k) #{#{}}
      (empty? s) #{}
      :else (set (for [i s
                       x (k-combinations (dec k) (disj s i))]
                   (conj x i)))))

  (defn sum-some-set [& sets]
    (not=
     #{}
     (apply clojure.set/intersection
            (for [s sets]
              (set
               (for [n    (range 1 (inc (count s)))
                     sums (set (map (partial reduce +) (k-combinations n s)))]
                 sums)))))))

(comment
  (defn sum-some-set [& sets]
    (let [sums (fn [[x & xs]]
                 (reduce
                  #(into %1 (cons %2 (map (partial + %2) %1)))
                  #{x}
                  xs))]
      (not= #{} (reduce
                 #(set (filter %1 %2))
                 (map sums sets))))))

(comment
  (= true  (sum-some-set #{-1 1 99} #{-2 2 888} #{-3 3 7777}))                                        ; true
  (= false (sum-some-set #{1} #{2} #{3} #{4}))                                                        ; true
  (= true  (sum-some-set #{1}))                                                                       ; true
  (= false (sum-some-set #{1 -3 51 9} #{0} #{9 2 81 33}))                                             ; true
  (= true  (sum-some-set #{1 3 5} #{9 11 4} #{-3 12 3} #{-3 4 -2 10}))                                ; true
  (= false (sum-some-set #{-1 -2 -3 -4 -5 -6} #{1 2 3 4 5 6 7 8 9}))                                  ; true
  (= true  (sum-some-set #{1 3 5 7} #{2 4 6 8}))                                                      ; true
  (= true  (sum-some-set #{-1 3 -5 7 -9 11 -13 15} #{1 -3 5 -7 9 -11 13 -15} #{1 -1 2 -2 4 -4 8 -8})) ; true
  (= true  (sum-some-set #{-10 9 -8 7 -6 5 -4 3 -2 1} #{10 -9 8 -7 6 -5 4 -3 2 -1})))                 ; true

;; ;;;;;;;;;;;;;;;;;;;;;;;;
;; 132 - Intervals (medium)
;; ;;;;;;;;;;;;;;;;;;;;;;;;

;; creates a lazy sequence (bc of last test case)
(comment
  (defn interval [p val [x & _xs :as coll]]
    (if x
      (->> (partition 2 1 coll)
           (map (fn [[n1 n2]] (if (p n1 n2) [val n2] [n2])))
           (concat [x])
           (flatten))
      (empty coll)))

  (defn interval [p val coll]
    (if (seq coll)
      (cons (first coll)
            (mapcat #(if (apply p %) [val (last %)] [(last %)])
                    (partition 2 1 coll)))
      '()))

  (= '(1 :less 6 :less 7 4 3) (interval < :less [1 6 7 4 3]))                     ; true
  (= '(2)                     (interval > :more [2]))                             ; true
  (= [0 1 :x 2 :x 3 :x 4]     (interval #(and (pos? %1) (< %1 %2)) :x (range 5))) ; true
  (empty?                     (interval > :more []))                              ; true
  (= [0 1 :same 1 2 3 :same 5 8 13 :same 21]                                      ; true
     (take 12 (->> [0 1]                                 ; fibonacci numbers
                   (iterate (fn [[a b]] [b (+ a b)]))
                   (map first)
                   (interval (fn [a b]
                               (= (mod a 2) (mod b 2))) ; both even or both odd
                             :same)))))

;; ;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; 134 - A nil key (elementary)
;; ;;;;;;;;;;;;;;;;;;;;;;;;;;;;

(comment
  (:a {:a nil :b 2})        ; nil
  (:b {:a nil :b 2})        ; 2
  (:c {:a nil :b 2})        ; nil
  (:c {:a nil :b 2} false)) ; false

(comment
  (true?  (#(nil? (%1 %2 false)) :a {:a nil :b 2}))  ; true
  (false? (#(nil? (%1 %2 false)) :b {:a nil :b 2}))  ; true
  (false? (#(nil? (%1 %2 false)) :c {:a nil :b 2}))) ; true ; true

;; ;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; 135 - Infix Calculator (easy)
;; ;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

(comment
  (defn infix-calc [left op right & rest]
    (let [result (op left right)]
      (if rest
        (apply infix-calc (cons result rest))
        result))))

(comment
  (= 7  (infix-calc 2 + 5))                            ; true
  (= 42 (infix-calc 38 + 48 - 2 / 2))                  ; true
  (= 8  (infix-calc 10 / 2 - 1 * 2))                   ; true
  (= 72 (infix-calc 20 / 2 + 2 + 4 + 8 - 6 - 10 * 9))) ; true

;; ;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; 137 - Digits and bases (medium)
;; ;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

(comment
  ((juxt quot rem) 1234501 10)  ; [123450 1]
  ((juxt quot rem) 123450  10)  ; [12345 0]
  ((juxt quot rem) 12345   10)  ; [1234 5]
  ((juxt quot rem) 1234    10)  ; [123 4]
  ((juxt quot rem) 123     10)  ; [12 3]
  ((juxt quot rem) 12      10)  ; [1 2]
  ((juxt quot rem) 1       10)  ; [0 1]
  ((juxt quot rem) 0       10)  ; [0 0]

  ((juxt quot rem) 0       11)) ; [0 0]

(comment
  (defn digits-and-bases [n base]
    (let [quot-rem (juxt quot rem)]
      (if (zero? n)
        [0]
        (->> (quot-rem n base)
             (iterate (fn [[n _]] (quot-rem n base)))
             (take-while (fn [[n d]] (not= 0 n d)))
             (map second)
             (reverse)))))

  (= [1 2 3 4 5 0 1]                  (digits-and-bases 1234501 10))         ; true
  (= [0]                              (digits-and-bases 0 11))               ; true
  (= [1 0 0 1]                        (digits-and-bases 9 2))                ; true
  (= [1 0] (let [n (rand-int 100000)] (digits-and-bases n n)))               ; true
  (= [12 36 0 6 30 7 4 35 25 14 36 7] (digits-and-bases Long/MAX_VALUE 42))) ; true

;; ;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; 141 - Tricky card games (medium)
;; ;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

(comment
  (apply max-key :rank (filter #(= (:suit %) :club) [{:suit :club, :rank 4} {:suit :club :rank 9}]))
  (group-by :suit [{:suit :spade, :rank 2} {:suit :club :rank 10}]) ; {:spade [{:suit :spade, :rank 2}], :club [{:suit :club, :rank 10}]}

  (letfn [(max-card [suit cards] (apply max-key :rank (filter #(= (:suit %) suit) cards)))]
    (max-card :club [{:suit :club,  :rank 4} {:suit :club, :rank  9}])))

(comment
  (defn tricky-card-games [trump-suit]
    (fn [cards]
      (letfn [(max-card [suit cards] (apply max-key :rank (filter #(= (:suit %) suit) cards)))]
        (let [lead-suit (:suit (first cards))]
          (if-let [winner (max-card (or trump-suit lead-suit) cards)]
            winner
            (max-card lead-suit cards))))))

  (= {:suit :club,  :rank 9} ((tricky-card-games nil) [{:suit :club,  :rank 4} {:suit :club, :rank  9}]))                                  ; true
  (= {:suit :spade, :rank 2} ((tricky-card-games nil) [{:suit :spade, :rank 2} {:suit :club, :rank 10}]))                                  ; true
  (let [notrump (tricky-card-games nil)]                                                                                                   ; true
    (and (= {:suit :club,  :rank 9} (notrump [{:suit :club,  :rank 4} {:suit :club :rank  9}]))
         (= {:suit :spade, :rank 2} (notrump [{:suit :spade, :rank 2} {:suit :club :rank 10}]))))
  (= {:suit :club,  :rank 10} ((tricky-card-games :club) [{:suit :spade, :rank 2} {:suit :club, :rank 10}]))                               ; true
  (= {:suit :heart, :rank 8}                                                                                                               ; true
     ((tricky-card-games :heart) [{:suit :heart, :rank  6} {:suit :heart, :rank 8} {:suit :diamond, :rank 10} {:suit :heart, :rank 4}])))

;; ;;;;;;;;;;;;;;;;;;;;;;;;
;; 143 - dot product (easy)
;; ;;;;;;;;;;;;;;;;;;;;;;;;

(comment
  (defn dot-product [v1 v2]
    (apply + (map * v1 v2))))

(comment
  (= 0   (dot-product [0 1 0] [1 0 0]))     ; true
  (= 3   (dot-product [1 1 1] [1 1 1]))     ; true
  (= 32  (dot-product [1 2 3] [4 5 6]))     ; true
  (= 256 (dot-product [2 5 6] [100 10 1]))) ; true

;; ;;;;;;;;;;;;;;;;;;;;;;;;
;; 144 - Oscilrate (medium)
;; ;;;;;;;;;;;;;;;;;;;;;;;;

(comment
  "Too complicated"

  (defn cycle-me [coll]
    (let [fst (first coll)
          lst (vec (rest coll))]
      (conj lst fst)))

  (cycle-me '(1 2 3)) ; [2 3 1]
  (cycle-me  [1 2 3]) ; [2 3 1]

  (defn oscilrate [init & funs]
    (lazy-seq
     (let [next ((first funs) init)]
       (cons init (apply oscilrate next (cycle-me funs)))))))

(comment
  "https://clojure.org/reference/lazy#_recipe_how_to_write_lazy_sequence_functions_in_new_model"

  (defn oscilrate [init & funs]
    (lazy-seq
     (let [next ((first funs) init)]
       (cons init (apply oscilrate next (concat (rest funs) [(first funs)]))))))

  (let [i [1 2 3]] ; (2 3 1)
    (concat (rest i) [(first i)])))

(comment
  "`reduce` isn't lazy, but `reductions` is!"

  (defn oscilrate [init & funs]
    (reductions (fn [acc f] (f acc))
                init
                (cycle funs))))

(comment
  (= (take 3  (oscilrate 3.14 int double))       [3.14 3 3.0])               ; true
  (= (take 5  (oscilrate 3 #(- % 3) #(+ 5 %)))   [3 0 5 2 7])                ; true
  (= (take 12 (oscilrate 0 inc dec inc dec inc)) [0 1 0 1 0 1 2 1 2 1 2 3])) ; true

;; ;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; 145 - For the win (elementary)
;; ;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

(comment
  (= '(1 5 9 13 17 21 25 29 33 37) (for [x (range 40)           ; true
                                         :when (= 1 (rem x 4))]
                                     x))

  (= '(1 5 9 13 17 21 25 29 33 37) (for [x (iterate #(+ 4 %) 0) ; true
                                         :let [z (inc x)]
                                         :while (< z 40)]
                                     z))

  (= '(1 5 9 13 17 21 25 29 33 37) (for [[x y] (partition 2 (range 20))] ; true
                                     (+ x y))))

;; ;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; 146 - Trees into tables (easy)
;; ;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

(comment
  (defn trees-into-tables [tree]
    (into {}
          (for [[k  v]  tree
                [k' v'] v]
            [[k k'] v'])))

  (= (trees-into-tables '{a {p 1, q 2}, b {m 3, n 4}})                        ; true
     '{[a p] 1, [a q] 2, [b m] 3, [b n] 4})
  (= (trees-into-tables '{[1] {a b c d} [2] {q r s t u v w x}})               ; true
     '{[[1] a] b, [[1] c] d,
       [[2] q] r, [[2] s] t,
       [[2] u] v, [[2] w] x})
  (= (trees-into-tables '{m {1 [a b c] 3 nil}}) '{[m 1] [a b c], [m 3] nil})) ; true

;; ;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; 147 - Pascal's Trapezoid (easy)
;; ;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

(comment
  (concat [0] [2 3 2])                                ; (0 2 3 2)
  (concat [2 3 2] [0])                                ; (2 3 2 0)
  (map +' (concat [0] [2 3 2]) (concat [2 3 2] [0]))) ; (2 5 5 2)

(comment
  (defn pascal-trapezoid [l]
    (iterate
     #(map +'
           (concat [0] %)
           (concat % [0]))
     l))

  (= (second   (pascal-trapezoid [2 3 2])) [2 5 5 2])                                   ; true
  (= (take 5   (pascal-trapezoid [1]))     [[1] [1 1] [1 2 1] [1 3 3 1] [1 4 6 4 1]])   ; true
  (= (take 2   (pascal-trapezoid [3 1 2])) [[3 1 2] [3 4 3 2]])                         ; true
  (= (take 100 (pascal-trapezoid [2 4 2])) (rest (take 101 (pascal-trapezoid [2 2]))))) ; true

;; ;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; 148 - The Big Divide (medium)
;; ;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

;; too slow
(comment
  ;; direct calculation
  (defn big-divide [n a b]
    (let [calc #(let [n (quot (dec n) %)]
                  (/ (*' % n (inc n)) 2))]
      (-' (+' (calc a) (calc b)) (calc (*' a b))))))

(comment
  (= 0                          (big-divide 3 17 11))                               ; true
  (= 23                         (big-divide 10 3 5))                                ; true
  (= 233168                     (big-divide 1000 3 5))                              ; true

  (= "2333333316666668"         (str (big-divide 100000000 3 5)))                   ; true
  (= "110389610389889610389610" (str (big-divide (* 10000 10000 10000) 7 11)))      ; true
  (= "1277732511922987429116"   (str (big-divide (* 10000 10000 10000) 757 809)))   ; true
  (= "4530161696788274281"      (str (big-divide (* 10000 10000 1000) 1597 3571)))) ; true

;; ;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; 150 - Palindromic Numbers (medium)
;; ;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

(comment
  (defn palindrome-number [n]
    (let [s (str n)
          left-half (read-string (subs s 0 (quot (inc (count s)) 2)))
          palindrome (->> (iterate inc left-half)
                          (map str)
                          (map #(str % (subs (clojure.string/reverse %)
                                             (if (even? (count s)) 0 1)
                                             (count %))))
                          (map read-string)
                          (filter (partial <= n))
                          first)]
      (lazy-seq
       (cons palindrome (palindrome-number (inc palindrome)))))))

(comment
  (= (take 26 (palindrome-number 0))                              ; true
     [0 1 2 3 4 5 6 7 8 9
      11 22 33 44 55 66 77 88 99
      101 111 121 131 141 151 161])
  (= (take 16 (palindrome-number 162))                            ; true
     [171 181 191 202
      212 222 232 242
      252 262 272 282
      292 303 313 323])
  (= (take 6 (palindrome-number 1234550000))                      ; true
     [1234554321 1234664321 1234774321
      1234884321 1234994321 1235005321])
  (= (first (palindrome-number (* 111111111 111111111)))          ; true
     (* 111111111 111111111))
  (= (set (take 199 (palindrome-number 0)))                       ; true
     (set (map #(first (palindrome-number %)) (range 0 10000))))
  (= true (apply < (take 6666 (palindrome-number 9999999))))      ; true
  (= (nth (palindrome-number 0) 10101) 9102019))                  ; true

;; ;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; 153 - Pairwise Disjoint Sets (easy)
;; ;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

(comment
  (let [set-of-sets #{#{\U} #{\s} #{\e \R \E} #{\P \L} #{\.}}]
    (for [s1 set-of-sets
          s2 set-of-sets
          :when (not= s1 s2)]
      (clojure.set/intersection s1 s2))))
; (#{} #{} #{} #{} #{} #{} #{} #{} #{} #{} #{} #{} #{} #{} #{} #{} #{} #{} #{} #{})

(comment
  (defn pairwise-disjoint-sets [set-of-sets]
    (let [xs (for [s1 set-of-sets
                   s2 set-of-sets
                   :when (not= s1 s2)]
               (clojure.set/intersection s1 s2))]
      (every? empty? xs))))

(comment
  (= (pairwise-disjoint-sets #{#{\U} #{\s} #{\e \R \E} #{\P \L} #{\.}}) true) ; true
  (= (pairwise-disjoint-sets  #{#{:a :b :c :d :e}                             ; true
                                #{:a :b :c :d}
                                #{:a :b :c}
                                #{:a :b}
                                #{:a}})
     false)
  (= (pairwise-disjoint-sets    #{#{[1 2 3] [4 5]}                             ; true
                                  #{[1 2] [3 4 5]}
                                  #{[1] [2] 3 4 5}
                                  #{1 2 [3 4] [5]}})
     true)
  (= (pairwise-disjoint-sets     #{#{'a 'b}                                    ; true
                                   #{'c 'd 'e}
                                   #{'f 'g 'h 'i}
                                   #{''a ''c ''f}})
     true)
  (= (pairwise-disjoint-sets      #{#{'(:x :y :z) '(:x :y) '(:z) '()}          ; true
                                    #{#{:x :y :z} #{:x :y} #{:z} #{}}
                                    #{'[:x :y :z] [:x :y] [:z] [] {}}})
     false)
  #_{:clj-kondo/ignore [:single-operand-comparison]}
  (= (pairwise-disjoint-sets       #{#{(= "true") false}                       ; true
                                     #{:yes :no}
                                     #{(class 1) 0}
                                     #{(symbol "true") 'false}
                                     #{(keyword "yes") ::no}
                                     #{(class '1) (int \0)}})
     false)
  (= (pairwise-disjoint-sets        (set [(set [distinct?])                    ; true
                                          (set [#(-> %) #(-> %)])
                                          (set [#(-> %) #(-> %) #(-> %)])
                                          (set [#(-> %) #(-> %) #(-> %)])]))
     true)
  #_{:clj-kondo/ignore [:redundant-do]}
  (= (pairwise-disjoint-sets         #{#{(#(-> *)) + (quote mapcat) #_nil}     ; true
                                       #{'+ '* mapcat (comment mapcat)}
                                       #{(do) set contains? nil?}
                                       #{#_empty?}})
     false))

;; ;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; 156 - Map Defaults (elementary)
;; ;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

(comment
  (zipmap [:a :b :c] (repeat 0))) ; {:a 0, :b 0, :c 0}

(comment
  (= (#(zipmap %2 (repeat %1)) 0 [:a :b :c]) {:a 0 :b 0 :c 0})                    ; true
  (= (#(zipmap %2 (repeat %1)) "x" [1 2 3]) {1 "x" 2 "x" 3 "x"})                  ; true
  (= (#(zipmap %2 (repeat %1)) [:a :b] [:foo :bar]) {:foo [:a :b] :bar [:a :b]})) ; true

;; ;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; 157 - Indexing Sequences (easy)
;; ;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

(comment
  (defn indexing-seq [c]
    (keep-indexed (fn [i e] [e i]) c)))

(comment
  (= (indexing-seq [:a :b :c]) [[:a 0] [:b 1] [:c 2]])                  ; true
  (= (indexing-seq [0 1 3]) '((0 0) (1 1) (3 2)))                       ; true
  (= (indexing-seq [[:foo] {:bar :baz}]) [[[:foo] 0] [{:bar :baz} 1]])) ; true

;; ;;;;;;;;;;;;;;;;;;;;;;
;; 158 - Decurry (medium)
;; ;;;;;;;;;;;;;;;;;;;;;;

(comment
  (defn decurry [f]
    (fn [& args] (reduce #(%1 %2) f args))))

(comment
  (= 10 ((decurry (fn [a]                        ; true
                    (fn [b]
                      (fn [c]
                        (fn [d] (+ a b c d))))))
         1 2 3 4))
  (= 24 ((decurry (fn [a]                        ; true
                    (fn [b]
                      (fn [c]
                        (fn [d] (* a b c d))))))
         1 2 3 4))
  (= 25 ((decurry (fn [a]                        ; true
                    (fn [b] (* a b))))
         5 5)))

;; ;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; 161 - Subset and Superset (elementary)
;; ;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

(comment
  (clojure.set/superset? #{2}   #{2})    ; true
  (clojure.set/subset?   #{1}   #{1})    ; true
  (clojure.set/superset? #{1 2} #{1 2})  ; true
  (clojure.set/subset?   #{1 2} #{1 2})) ; true

;; ;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; 162 - Logical falsity and truth (elementary)
;; ;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

;; In Clojure, only `nil` and `false` represent the values of logical falsity in conditional tests - anything else is logical truth!!!

(comment
  (= 1 (if-not false 1 0))  ; true
  (= 1 (if-not nil 1 0))    ; true
  (= 1 (if true 1 0))       ; true
  (= 1 (if [] 1 0))         ; true
  (= 1 (if [0] 1 0))        ; true
  (= 1 (if 0 1 0))          ; true
  (= 1 (if 1 1 0)))         ; true

;; ;;;;;;;;;;;;;;;;;;;;;;;;
;; 166 - Comparisons (easy)
;; ;;;;;;;;;;;;;;;;;;;;;;;;

(comment
  (defn compare-me [less-than-op i1 i2]
    (cond
      (less-than-op i1 i2) :lt
      (less-than-op i2 i1) :gt
      :else :eq)))

(comment
  (= :gt (compare-me < 5 1))                                              ; true
  (= :eq (compare-me (fn [x y] (< (count x) (count y))) "pear" "plum"))   ; true
  (= :lt (compare-me (fn [x y] (< (mod x 5) (mod y 5))) 21 3))            ; true
  (= :gt (compare-me > 0 2)))                                             ; true

;; ;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; 168 - Infinite Matrix (medium)
;; ;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

(comment
 (defn infinite-matrix
   ([f] (infinite-matrix f 0 0))
   ([f m n] (letfn [(row [a b] (lazy-seq (cons (f a b) (row a (inc b)))))]
              (lazy-seq (cons (row m n) (infinite-matrix f (inc m) n)))))
   ([f m n s t] (map (partial take t) (take s (infinite-matrix f m n))))))

(comment
  (= (take 5 (map #(take 6 %) (infinite-matrix str)))                            ; true
     [["00" "01" "02" "03" "04" "05"]
      ["10" "11" "12" "13" "14" "15"]
      ["20" "21" "22" "23" "24" "25"]
      ["30" "31" "32" "33" "34" "35"]
      ["40" "41" "42" "43" "44" "45"]])

  (= (take 6 (map #(take 5 %) (infinite-matrix str 3 2)))                        ; true
     [["32" "33" "34" "35" "36"]
      ["42" "43" "44" "45" "46"]
      ["52" "53" "54" "55" "56"]
      ["62" "63" "64" "65" "66"]
      ["72" "73" "74" "75" "76"]
      ["82" "83" "84" "85" "86"]])

  (= (infinite-matrix * 3 5 5 7)                                                 ; true
     [[15 18 21 24 27 30 33]
      [20 24 28 32 36 40 44]
      [25 30 35 40 45 50 55]
      [30 36 42 48 54 60 66]
      [35 42 49 56 63 70 77]])

  (= (infinite-matrix #(/ % (inc %2)) 1 0 6 4)                                   ; true
     [[1/1 1/2 1/3 1/4]
      [2/1 2/2 2/3 1/2]
      [3/1 3/2 3/3 3/4]
      [4/1 4/2 4/3 4/4]
      [5/1 5/2 5/3 5/4]
      [6/1 6/2 6/3 6/4]])

  (= (class (infinite-matrix (juxt bit-or bit-xor)))                             ; true
     (class (infinite-matrix (juxt quot mod) 13 21))
     (class (lazy-seq)))

  (= (class (nth (infinite-matrix (constantly 10946)) 34))                       ; true
     (class (nth (infinite-matrix (constantly 0) 5 8) 55))
     (class (lazy-seq)))

  (= (let [m        377                                                          ; true
           n        610
           w        987
           check    (fn [f s] (every? true? (map-indexed f s)))
           row      (take w (nth (infinite-matrix vector) m))
           column   (take w (map first (infinite-matrix vector m n)))
           diagonal (map-indexed #(nth %2 %) (infinite-matrix vector m n w w))]
       (and (check #(= %2 [m %]) row)
            (check #(= %2 [(+ m %) n]) column)
            (check #(= %2 [(+ m %) (+ n %)]) diagonal)))
     true))

;; ;;;;;;;;;;;;;;;;;;;;;;;;
;; 171 - Intervals (medium)
;; ;;;;;;;;;;;;;;;;;;;;;;;;

(comment

  "complicated"
  (defn intervals [coll]
    (if (seq coll)
      (let [l (dedupe (sort coll))]
        (map (fn [lst] [(first lst) (last lst)])
             (filter (partial not= (list :sep))
                     (partition-by (partial = :sep)
                                   (reduce
                                    (fn [acc n] (if (= (inc (last acc)) n) (conj acc n) (conj acc :sep n)))
                                    [(first l)]
                                    (rest l))))))
      []))

  "elegant"
  (defn intervals [coll]
    (->> coll
         distinct
         sort
         (map-indexed #(list (- %2 %1) %2))
         (partition-by first)
         (map #(list (last (first %)) (last (last %))))))

  (map-indexed #(list (- %2 %) %2) (sort (distinct [19 4 17 1 3 10 2 13 13 2 16 4 2 15 13 9 6 14 2 11]))))
  ; ((1 1)
  ;  (1 2)
  ;  (1 3)
  ;  (1 4)
  ;  (2 6)
  ;  (4 9)
  ;  (4 10)
  ;  (4 11)
  ;  (5 13)
  ;  (5 14)
  ;  (5 15)
  ;  (5 16)
  ;  (5 17)
  ;  (6 19))

(comment
  (= (intervals [1]) [[1 1]])                                        ; true
  (= (intervals [1 2 3]) [[1 3]])                                    ; true
  (= (intervals [10 9 8 1 2 3]) [[1 3] [8 10]])                      ; true
  (= (intervals [1 1 1 1 1 1 1]) [[1 1]])                            ; true
  (= (intervals []) [])                                              ; true
  (= (intervals [19 4 17 1 3 10 2 13 13 2 16 4 2 15 13 9 6 14 2 11]) ; true
     [[1 4] [6 6] [9 11] [13 17] [19 19]]))

;; ;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; 173 - Intro to Destructuring 2 (easy)
;; ;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

;; __ = op a
(comment
  (= 3                                           ; true
     (let [[op a] [+ (range 3)]] (apply op a))
     (let [[[op a] b] [[+ 1] 2]] (op a b))
     (let [[op a] [inc 2]]       (op a))))

;; ;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; 177 - Balancing Brackets (medium)
;; ;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

(comment
  (let [[fst & tail] "Hallo"] ; [\H "allo"]
    [fst (apply str tail)]))

(comment
  (defn balancing-brackets
    ([s]
     (balancing-brackets s '()))
    ([[fst & tail] stack]
     (let [push conj
           pop  rest
           parens-map {\) \(, \} \{, \] \[}
           s (apply str tail)]
       (cond
         (not fst)                   (empty? stack)
         (contains? #{\( \{ \[} fst) (recur s (push stack fst))
         (contains? #{\) \} \]} fst) (if (and (seq stack) (= (first stack) (parens-map fst)))
                                       (recur s (pop stack))
                                       false)
         :else                       (recur s stack)))))

  (balancing-brackets "This string has no brackets.")                                ; true
  (balancing-brackets "class Test {
                              public static void main(String[] args) {
                                System.out.println(\"Hello world.\");
                              }
                            }")                                                      ; true
  (not (balancing-brackets "(start, end]"))                                          ; true
  (not (balancing-brackets "())"))                                                   ; true
  (not (balancing-brackets "[ { ] } "))                                              ; true
  (balancing-brackets "([]([(()){()}(()(()))(([[]]({}()))())]((((()()))))))")        ; true
  (not (balancing-brackets "([]([(()){()}(()(()))(([[]]({}([)))())]((((()()))))))")) ; true
  (not (balancing-brackets "[")))                                                    ; true

;; ;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; 195 - Parentheses... Again (medium)
;; ;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

(comment
  "generate recursively"

  (declare generate-parens)

  (defn add-open-paren [n num-open-parens res acc]
    (if (= (count acc) (* 2 n))
      (if (zero? num-open-parens)
        (conj res acc)
        res)
      (if (< num-open-parens n)
        (generate-parens n (inc num-open-parens) res (str acc \())
        res)))

  (defn add-close-paren [n num-open-parens res acc]
    (if (= (count acc) (* 2 n))
      (if (zero? num-open-parens)
        (conj res acc)
        res)
      (if (pos? num-open-parens)
        (generate-parens n (dec num-open-parens) res (str acc \)))
        res)))

  (defn generate-parens
    ([n]
     (generate-parens n 0 [] ""))
    ([n num-open-parens res acc]
     (set (concat (add-open-paren  n num-open-parens res acc)
                  (add-close-paren n num-open-parens res acc))))))

;; (comment
;;   "brute force"
;;
;;   (defn generate-parens [n]
;;     (if-not (pos? n) #{""}
;;             (set (for [i (range n)
;;                        l (generate-parens i)
;;                        r (generate-parens (- n 1 i))]
;;                    (str "(" l ")" r))))))

(comment
  (= (generate-parens 0) #{""})                                                                                   ; true
  (= (generate-parens 1) #{"()"})                                                                                 ; true
  (= (generate-parens 2) #{"(())" "()()"})                                                                        ; true
  (= (generate-parens 3) #{"(()())" "((()))" "()()()" "()(())" "(())()"})                                         ; true
  (= [#{""} #{"()"} #{"()()" "(())"}] (map (fn [n] (generate-parens n)) [0 1 2]))                                 ; true
  (= #{"((()))" "()()()" "()(())" "(())()" "(()())"} (generate-parens 3))                                         ; true
  (= 16796  (count (generate-parens 10)))                                                                         ; true
  (= 208012 (count (generate-parens 12)))                                                                         ; true
  (= 742900 (count (generate-parens 13)))                                                                         ; true
  (= (nth (sort (filter #(clojure.string/includes? % "(()()()())") (generate-parens 9))) 6) "(((()()()())(())))") ; true
  (= (nth (sort (generate-parens 12)) 5000) "(((((()()()()()))))(()))"))                                          ; true
