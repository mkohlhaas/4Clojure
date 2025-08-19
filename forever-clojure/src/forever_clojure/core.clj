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
;; 022 - Count a Sequence (easy)  
;; 023 - Reverse a Sequence (easy)  
;; 024 - Sum It All Up (easy)  
;; 025 - Find the odd numbers (easy)  
;; 026 - Fibonacci Sequence (easy)  
;; 027 - Palindrome Detector (easy)  
;; 028 - Flatten a Sequence (easy)  
;; 029 - Get the Caps (easy)  
;; 030 - Compress a Sequence (easy)  
;; 031 - Pack a Sequence (easy)  
;; 032 - Duplicate a Sequence (easy)  
;; 033 - Replicate a Sequence (easy)  
;; 034 - Implement range (easy)  
;; 035 - Local bindings (elementary)  
;; 036 - Let it Be (elementary)  
;; 037 - Regular Expressions (elementary)  
;; 038 - Maximum value (easy)  
;; 039 - Interleave Two Seqs (easy)  
;; 040 - Interpose a Seq (easy)  
;; 041 - Drop Every Nth Item (easy)  
;; 042 - Factorial Fun (easy)  
;; 043 - Reverse Interleave (medium)  
;; 044 - Rotate Sequence (medium)  
;; 045 - Intro to Iterate (easy)  
;; 046 - Flipping out (medium)  
;; 047 - Contain Yourself (easy)  
;; 048 - Intro to some (easy)  
;; 049 - Split a sequence (easy)  
;; 050 - Split by Type (medium)  
;; 051 - Advanced Destructuring (easy)  
;; 052 - Intro to Destructuring (elementary)  
;; 053 - Longest Increasing SubSeq (hard)  
;; 054 - Partition a Sequence (medium)  
;; 055 - Count Occurences (medium)  
;; 056 - Find Distinct Items (medium)  
;; 057 - Simple Recursion (elementary)  
;; 058 - Function Composition (medium)  
;; 059 - Juxtaposition (medium)  
;; 060 - Sequence Reductions (medium)  
;; 061 - Map Construction (easy)  
;; 062 - Reimplement Iteration (easy)  
;; 063 - Group a Sequence (easy)  
;; 064 - Intro to Reduce (elementary)  
;; 065 - Black Box Testing (medium)  
;; 066 - Greatest Common Divisor (easy)  
;; 067 - Prime Numbers (medium)  
;; 068 - Recurring Theme (elementary)  
;; 069 - Merge with a Function (medium)  
;; 070 - Word Sorting (medium)  
;; 071 - Rearranging Code: > (elementary)  
;; 072 - Rearranging Code: >> (elementary)  
;; 073 - Analyze a TicTacToe Board (hard)  
;; 074 - Filter Perfect Squares (medium)  
;; 075 - Euler's Totient Function (medium)  
;; 076 - Intro to Trampoline (medium)  
;; 077 - Anagram Finder (medium)  
;; 078 - Reimplement Trampoline (medium)  
;; 079 - Triangle Minimal Path (hard)  
;; 080 - Perfect Numbers (medium)  
;; 081 - Set Intersection (easy)  
;; 082 - Word Chains (hard)  
;; 083 - A HalfTruth (easy)  
;; 084 - Transitive Closure (hard)  
;; 085 - Power Set (medium)  
;; 086 - Happy numbers (medium)  
;; 087 - Create an Equation    
;; 088 - Symmetric Difference (easy)  
;; 089 - Graph Tour (hard)  
;; 090 - Cartesian Product (easy)  
;; 091 - Graph Connectivity (hard)  
;; 092 - Read Roman numerals (hard)  
;; 093 - Partially Flatten a Sequence (medium)  
;; 094 - Game of Life (hard)  
;; 095 - To Tree, or not to Tree (easy)  
;; 096 - Beauty is Symmetry (easy)  
;; 097 - Pascal's Triangle (easy)  
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
;; 135 - Infix Calculator (easy)  
;; 137 - Digits and bases (medium)  
;; 140 - Veitch, Please! (hard)  
;; 141 - Tricky card games (medium)  
;; 143 - dot product (easy)  
;; 144 - Oscilrate (medium)  
;; 145 - For the win (elementary)  
;; 146 - Trees into tables (easy)  
;; 147 - Pascal's Trapezoid (easy)  
;; 148 - The Big Divide (medium)  
;; 150 - Palindromic Numbers (medium)  
;; 153 - Pairwise Disjoint Sets (easy)  
;; 156 - Map Defaults (elementary)  
;; 157 - Indexing Sequences (easy)  
;; 158 - Decurry (medium)  
;; 161 - Subset and Superset (elementary)  
;; 162 - Logical falsity and truth (elementary)  
;; 166 - Comparisons (easy)  
;; 168 - Infinite Matrix (medium)  
;; 171 - Intervals (medium)  
;; 173 - Intro to Destructuring 2 (easy)  
;; 177 - Balancing Brackets (medium)  
;; 195 - Parentheses... Again (medium)  
