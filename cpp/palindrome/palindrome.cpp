#include <stdio.h>
#include <string.h>
#include <stdlib.h>
#include <ctype.h>

#define LINELEN 1024
char* read_str(char s[]);
int is_palindrome(char* word);

/** 
 * @brief application entry point
 * @details takes in argc buffers seperated by new lines, for each of the buffers provided, 
 *   executes `is_palendrome()` and prints a summary of the buffers anylized
 * @param[in] argc - the number of arguments
 * @param[in] argv - arguments, buffers seperated by new lines
 * @return the function always returns 0
 */
int main(int argc, char **argv) {
  char *fgets_rtn = NULL;
  char buffer[LINELEN];
  int line = 1;
  int pal_count = 0;
  int tot_count = 0;
  
  do {
    fgets_rtn = fgets(buffer, LINELEN, stdin);
    if (fgets_rtn != NULL) {
      if('\n' == buffer[strlen(buffer)-1]) {
	      buffer[ strlen(buffer)-1 ] = '\0';

      }
      char * temp_buffer = strdup(buffer);
      int temp = is_palindrome( read_str(buffer) );
      if (temp == -1) {
	      printf("[Regular String]: %s\n", temp_buffer);
	      tot_count++;

      } else if(temp == 1) {
	      printf("[Palindrome!]: %s\n", temp_buffer);
	      pal_count++;
	      tot_count++;

      }
      line++;

    }

  } while( fgets_rtn != NULL );

  printf("--------------------------------\n");
  printf("Total Palindromes: %d (out of %d strings)\n",pal_count, tot_count );
  printf("--------------------------------\n");
  return 0;
}

/** 
 * @brief determines if a char* is a palindrome
 * @details takes in a char* and determines if the first half of the string mirrors the second half
 * @param[in] word - char*, string to anylize
 * @return  -1 - if the string provided is not a palindrome
 * @return  0 - if the length of the string provided is 0.
 * @return  1 - if the string provided is a palindrome
 */
int is_palindrome(char*  word) {
  int len = strlen(word);
  int op = len/2;
  int i;
  if (len == 0) {
    return 0;

  }
  if (len%2 != 0) {
    int c;
    for(c = len/2; c<len; c++){
      word[c] = word[c+1];

    }

  }
  for(i = 0; i < len/2; i++){
    if(word[op-i-1] != word[op+i]){
	    return -1;

    }

  }
  return 1;
}

/** 
 * @brief cleans chars* read from buffer
 * @details removes special chars and newlines from string and returns string 
 * @param[in, out] s - char* s
 * @return s - if the string provided is not a palindrome
 */
char* read_str(char s[]) {
  int len = strlen(s);
  int i;
  int c = 0;
  for (i = 0; i < len; i++){
    while((s[i] != '\0') 
      && (isspace(s[i]) 
            || isblank(s[i]) 
            || !isalpha(s[i]))) {
       i++;
       c++;

    }
    s[i-c] = tolower(s[i]);

  }
  s[i-c] = '\0';
  return s;
}