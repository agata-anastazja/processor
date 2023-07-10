(ns pitch.digits)


(def chars-digits {[[\space \_ \space]
              [\| \space \|]
              [\| \_ \|]] 0

             [[\space \space \space]
              [\space \space \|]
              [\space \space \|]] 1

             [[\space \_ \space]
              [\space \_ \|]
              [\| \_ \space]] 2

             [[\space \_ \space]
              [\space \_ \|]
              [\space \_ \|]] 3

             [[\space \space \space]
              [\| \_ \|]
              [\space \space \|]] 4

             [[\space \_ \space]
              [\| \_ \space]
              [\space \_ \|]] 5

             [[\space \_ \space]
              [\| \_ \space]
              [\| \_ \|]] 6

             [[\space \_ \space]
              [\space \space \|]
              [\space \space \|]] 7

             [[\space \_ \space]
              [\| \_ \|]
              [\| \_ \|]] 8

             [[\space \_ \space]
              [\| \_ \|]
              [\space \_ \|]] 9})

(def seven-segment-digits
  {" _ 
| |
|_|" 0
   "   
  |
  |" 1
   " _ 
 _|
|_ " 2 
   " _ 
 _|
 _|" 3 
  "   
|_|
  |"  4
   " _ 
|_ 
 _|" 5
  " _ 
|_ 
|_|" 6
  " _ 
  |
  |" 7
  " _ 
|_|
|_|"    8
    " _ 
|_|
 _|" 9 })