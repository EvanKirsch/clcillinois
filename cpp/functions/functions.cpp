#include <iostream>
using namespace std;

int total_function_calls = 0;  // global variable

/** 
 * @brief increments total_function_calls
 * @details (overloaded) example of a void function modifying program state
 * @return void 
 */
void add_to_total_function_calls() {
  total_function_calls = total_function_calls + 1;                          // total_function_calls is a global variable, this is why we don't need to return it
  printf("  called function, increment to %d\n", total_function_calls);
}

/** 
 * @brief increments and prints details of value specified 
 * @details (overloaded) example of using a function using a parameter and returning a value
 * @param[in] value_total_function_calls - value to be incremented
 * @return the incremented value of value_total_function_calls
 */
int add_to_total_function_calls(int value_total_function_calls) {
  value_total_function_calls = value_total_function_calls + 1;                 // value_total_function_calls is a local variable
  printf("  called function, increment to %d\n", value_total_function_calls);
  return value_total_function_calls;
}

/** 
 * @brief does void function calls
 * @details performs function calls that provide an example of void return functions
 * @return void
 */
void do_void_calls() {
  printf("=== Start Void Functions  ===\n");
  printf("total_function_calls=%d\n", total_function_calls);

  add_to_total_function_calls();
  add_to_total_function_calls();
  add_to_total_function_calls();
  add_to_total_function_calls();
  add_to_total_function_calls();

  printf("total_function_calls=%d\n", total_function_calls);
  printf("=== End Void Functions ===\n\n\n");
}

/** 
 * @brief does value function calls
 * @details performs function calls that provide an example of value return functions
 * @return void
 */
void do_value_calls() {
  printf("\n\n=== Start Value Functions ===\n");
  printf("total_function_calls=%d\n", total_function_calls);

  total_function_calls = add_to_total_function_calls(total_function_calls);
  total_function_calls = add_to_total_function_calls(total_function_calls);
  total_function_calls = add_to_total_function_calls(total_function_calls);

  printf("total_function_calls=%d\n", total_function_calls);
  printf("=== End Value Functions  ===\n");
}

/** 
 * @brief setter function for total_function_calls
 * @details sets total_function_calls, takes one argument and returns void
 * @param[in] value - the value to set total_function_calls
 * @return void
 */
void set_total_function_calls(int value) {
  total_function_calls = value;
  printf("set total_function_calls=%d\n", total_function_calls);
}

/** 
 * @brief main is the entry point of the program
 * @details This is a good example of diffrent types of function calls in cpp
 * @return the function always returns 0
 */
int main() {

  do_void_calls();
  set_total_function_calls(0);
  do_value_calls();

  return 0;
}