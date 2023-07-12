# pitch/processor

# Data transformation challenge

Hello! Welcome to the data transformation challenge.

This challenge is divided into three parts, with each building upon the previous. You shouldn't spend more than two hours on the challenge and you should try to complete as many parts as possible. However, don't feel pressured to complete every single part; we aren't judging your submission solely for completeness.

We've provided a template in both JavaScript and Clojure, and you can choose the one you're most comfortable with. Refer to the readme of the respective language for more details. One note: at Pitch we develop in Clojure, which is a functional programming language, and we highly value functional programming. Regardless of your chosen template, please try to use a functional approach where possible.

To ensure that we can carry a blind review process, please don't include anything that refers to you or anything that you can be identified with (such as a Github profile).

Lastly, when you're done, please submit your solution in a ZIP file to your recruiter.

Thanks, and enjoy!

# Challenge

## Part 1

You work for a bank named Simply Ingenious, which has recently invented a clever machine to assist in reading incoming letters and faxes sent in by branch offices. The machine scans paper documents that contain account numbers, which are represented as [seven segment](https://en.wikipedia.org/wiki/Seven-segment_display) digits from 0 to 9:

```
 _     _  _     _  _  _  _  _
| |  | _| _||_||_ |_   ||_||_|
|_|  ||_  _|  | _||_|  ||_| _|

```




Each entry is 3 lines long and each line has at most 27 characters. Each entry is followed by an empty blank line (a newline).

Each line of the entry contains parts of an account number written in pipes and underscores, and the entire entry comprises an account number that contains 9 digits, all of which are in the range 0-9. A normal file contains around 500 entries.

Your task is to write a program that takes an input file, parses the entries inside, and outputs them. A sample input file is included for you.

So, your sample input files starts with these entries:

```
 _  _  _  _  _  _  _  _  _
| || || || || || || || || |
|_||_||_||_||_||_||_||_||_|


  |  |  |  |  |  |  |  |  |
  |  |  |  |  |  |  |  |  |

```

Your program should take these entries and generate an output file that looks like this:

```
000000000
111111111
```

## Part 2

After delivering this newly processed output file to your manager, she returns to you and tells you that some account numbers are in fact invalid based on their checksum and asks you to add the account number validity to the output.

Your next step therefore is to validate that the entries you parse are in fact valid account numbers. For clarity: a valid account number has a valid checksum. The checksum can be calculated as follows:

```
account number:  3  4  5  8  8  2  8  6  5
position names:  d9 d8 d7 d6 d5 d4 d3 d2 d1
checksum calculation: (d1 + 2*d2 + 3*d3 + ⋯ + 9*d9) mod 11 = 0
```
Your next task is to modify your program to print out both the parsed entries and whether the entry is invalid. If an entry is invalid, you should append `ERR` to its output. Pay close attention to the way the checksum is calculated in the example above as well -- it's not a simple dot product.

Given the same input as above, your output should now be:

```
000000000
111111111 ERR
```

## Part 3

Now that you have the entry validity calculation working, you feel confident and hand the program over to your manager.

A little while later, she returns to you and tells you that she forgot to mention that the machine is in beta testing and has some issues. In particular, sometimes the machine incorrectly reads entries and skips reading a pipe or underscore. You need to change your program to account for this.

Your last task is to modify your program to handle these incorrectly read entries. If your program encounters a digit that doesn't match any of the known digits, you should treat the digit as unreadable and the entire account number as illegal. For clarity: a legal account number is one in which all 9 of its digits can be read by the program.

When outputting the illegal account number, you should treat any unreadable digit as `?` and you should append `ILL` to it. As before, if you encounter a legal but invalid account number, you should append `ERR` to it.

Note that you can assume the input will be "well formed" and that a missing pipe or underscore will not affect other digits; the character will simply be missing.

So, taking the initial example above, let's remove a pipe and underscore from the first entry:

Given an input like this:

```
 _  _  _  _  _  _  _  _  _
| || || || || |  || || || |
|_||_||_||_||_||_||_||_||_


  |  |  |  |  |  |  |  |  |
  |  |  |  |  |  |  |  |  |

```

You should have an output like this:

```
00000?00? ILL
111111111 ERR
```

Your sample input might not contain any illegal entries, so you might need to change it to have one.

## Installation

To run this project you need to have 
- brew
- java


Install clojure by running
```
brew install clojure/tools/clojure
```

## Usage


Run the project  by providing a path for a file to be read and a path for a file to be :

    $ clojure -X:run-x :name '"Someone"'
    Hello, Someone!

Run the project directly, via `:main-opts` (`-m pitch.processor`):

    $ clojure -M:run-m
    Hello, World!

Run the project's tests (they'll fail until you edit them):

    $ clojure -T:build test

## Design decisions


