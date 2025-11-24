# clcillinois
Scripts and examples for use at clc

## CIT-132 : Intro to Linux
  - [Command Cheat Sheet](unix-reference.pdf)
  - The Unix Philosophy
    - A program should do one thing and do it well
    - Programs should work together

## CIT-134 : Intro to Programming Concepts:
  - Loops
    - [For Loops](python/For_Loops.py)
    - [While Loops](python/While_Loops.py)
  - Sorting Algorithms
    - [Selection Sort](python/Selection_Sort.py) - Simplest sorting impl compares each element to the entire list
    - [Merge Sort](python/Merge_Sort.py) - Recursively  splits up list then merges lists back together sorting them as they are merged.
    - [Quick Sort](python/Quick_Sort.py) - Sorts by selecting a pivot and recursively sorts sub lists.

## CIT-141 : Programming in C++
  - [Hello World](cpp/hello/hello.cpp) - Quick example to get you past the main method syntax and makefile
  - [File I/O](c/ptr/main.c) - See `in_file` and `out_file` for examples of File I/O
  - Control Structures
    - [If/else](cpp/is_prime/is_prime.cpp) - see `is_prime.isPrime(int)`
    - [Boolean expressions](cpp/is_prime/is_prime.cpp) - see `is_prime.isPrime(int)`
    - [Logical operators](cpp/is_prime/is_prime.cpp) - see `is_prime.isPrime(int)`
    - [For Loop](cpp/is_prime/is_prime.cpp) - see `is_prime.main(int, char**)`
    - [Order of precedence](https://en.cppreference.com/w/cpp/language/operator_precedence.html) - when in doubt, use parenthese 
    - [Do While](cpp/palindrome/palindrome.cpp) - see input buffer reading.
    - TODO - Break and continue
  - Functions
    - TODO - Void/value returning functions  
    - TODO - Pass by reference/pass by value
    - TODO - Overloading
    - TODO - Defualt parameters
    - TODO - Local vs. global variables
  - TODO - Enums
  - TODO - Structs
  - [Arrays](cpp/palindrome/palindrome.cpp) - Strings are really just arrays of chars
  - [Composition](c/ptr) - `.c`/`.cpp` files should have `.h` files. `.h` files define the interface other classes are allowed to interact with the class.
  - [Pointers](c/ptr) - Example of pointers and dereferencing
  - TODO - Exceptions
  - [Recursion](python/Recursion.py)

## CIT-142 : Programming in Python
  - [User inputs](python/Cli_io.py)
  - Loops
    - [For Loops](python/For_Loops.py)
    - [While Loops](python/While_Loops.py)
  - [Recursion](python/Recursion.py)

## MCS-141 : Computer Science I
  - [Hello World](java/HelloWorld.java)
  - [Method Calls](java/Prime.java) - See the call and return of `isPrime()`
  - [Conditionals](java/Prime.java) - See the if/else contition of `isPrime()` in the while loop
  - Loops
    - [For Loops](java/MaximumFinder.java) - See the for loop of `findMaximum()`
    - [While Loops](java/Prime.java) - See the while loop of `isPrime()`
  - Recursion
    - Java Examples
      - [Navigating Bianary Trees](java/BinarySearchTree.java) - See the method `find(long, int)`. This method is calling itself to navigate through the tree
      - [Merge Sort](java/sorter/MergeSortImpl.java) 
      - [Quick Sort](java/sorter/QuickSortImpl.java) 
    - [Python Examples](python/Recursion.java) 
  - [Arrays](java/MaximumFinder.java)
  - Working With Strings
    - [File I/O](java/DecodeTape/DecodeTape.java) - This reads in the file `tape.test.in.txt`
    - [CLI](java/Prime.java)- This reads a variable given by the user from `System.in`
  - OOP Principles
    - [Sort Interface](java/sorter/ISorter.java) - Interfaces play an important role in dependancy inversion and injection
      - [Merge Sort Implemention](java/sorter/MergeSortImpl.java)
      - [Quick Sort Implemention](java/sorter/QuickSortImpl.java)
      - [Selection Sort Implemention](java/sorter/SelectionSortImpl.java)
    - [Simple Factory Pattern](java/sorter/SimpleSorterFactory.java) - Tried and tested OOP design patterns can be used to produce clean, predictable, extendable code. See "Design Patterns: Elements of Reusable Object-Oriented Software".
    
## MCS-142 : Computer Science II
  - Sorting Algorithms
    - [Selection Sort](python/Selection_Sort.py) - Simplest sorting impl compares each element to the entire list
    - [Merge Sort](python/Merge_Sort.py) - Recursively  splits up list then merges lists back together sorting them as they are merged.
    - [Quick Sort](python/Quick_Sort.py) - Sorts by selecting a pivot and recursively sorts sub lists.
  - [Recursion](python/Recursion.py)
  - [Binary Trees](java/BinarySearchTree.java)
  - [Directed Graph](java/TopologicalSort.java)
