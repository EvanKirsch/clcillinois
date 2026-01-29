## C++ 
C++, read as C plus plus and also written as cpp (because plus signs arn't the best characters to use in file names) is a great language to learn the fundementals of memory management or for high performance programming. Unlike C, C++ provides the ablity to write in the object oriented paradigm.

## Compiling and running C++ from a bash shell
C++ is a compiled language. This means that it takes two steps to run the program:
  1. Compile the program into an output file
  1. Run the output file

C++ is best compiled and run from a bash shell. To compile a C++ program I recommend using the g++ compiler. This compiler can be used by running the command:

```bash
g++ <input_file>.cpp
```

Additionally an output file can be specified, if no output file is speicified the default output file is `a.out` This file can be run using the command:

 ```bash 
 ./a.out
 ```