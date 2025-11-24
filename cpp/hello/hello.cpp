#include <iostream>
using namespace std;

// in C++, a function with the signature `int main()` or `int main(int argc, char* argv)`
//   is defined in order to specifiy the the entry point to the program. 
int main() { 
    cout << "Hello World! With cout!" << endl;      // printing with cout 
    printf("Hello World! With printf!\n");          // printing with printf
    return 0;                                       // returning 0 to satisfy the method signature
}