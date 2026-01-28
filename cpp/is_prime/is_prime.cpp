#include <stdlib.h>
#include <stdio.h>
#include <string.h>
#include <iostream>
using namespace std;

/** 
 * @brief prints if a number is prime or its composites
 * @details This is a good example of control structures in c++
 * @param[in] x - the number to identify primeness of
 * @return the function always returns 0
 */
int isPrime(int x) {
  printf("-----------------\n");
  if (x == 1) {
    printf("1 is not prime. By definition!");

  } else if(x < 1) {
    printf("Error: %d is less than 1!\n", x);

  } else {
    int n = 2;
    int divs = 0;
    printf("Checking: %d\n", x);
    while (n < x) {
      if (x % n == 0) {
	      printf("Divisible by %d\n", n);
	      divs++;

      }
      n++;

    }
    if (divs == 0) {
      printf("%d is Prime!\n", x);

    } else {
      if (divs == 1) {
	      printf("%d is Composite! (1 divisor)\n", x);

      } else {
	      printf("%d is Composite! (%i divisors)\n", x, divs);

      }

    }

  }
  return 0;
}

/** 
 * @brief application entry point
 * @details takes in argc numbers, for each of the numbers provided executes `isPrime()`
 * @param[in] argc - the number of arguments
 * @param[in] argv - arguments, a list of numbers
 * @return the function always returns 0
 */
int main(int argc, char **argv) {
  int i;
  for (i = 1; i < argc; i++) {
    isPrime(strtol(argv[i], NULL, 10));
  }
  return 0;
}