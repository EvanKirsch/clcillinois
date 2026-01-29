#include <iostream>
using namespace std;

// in C++, a function with the signature `int main()` or `int main(int argc, char* argv)`
//   is defined in order to specifiy the the entry point to the program. 
int main() { 
    string name;                    // declare a string to read name into

    cout << "What is your Name? ";  // ask name by printing string "What is your Name?", no new line
    cin >> name;                    // ask user for input, read your name from input
    cout << "Hello ";               // print hello 
    cout << name;                   // print the name provided by the input
    cout << endl;                   // print new line

    return 0;                       // returning 0 to satisfy the method signature
}