// Write a C program to get the absolute difference between n and 51. 
// If n is greater than 51 return triple the absolute difference.

#include <stdio.h>
#include <string.h>
#include <stdlib.h>

int main()
{
  char* choice = (char*) malloc(10 * sizeof(char));
  int n,val;
 
	printf("Please enter a Number: ");
	scanf("%d", &n);
  printf("Number entered is : %d\n", n);
  if (n>51){
    val=(abs(n-51))*3;
  }
  else {
    val=(abs(n-51));
  }
  printf("result: %d\n",val);
  return val;
}