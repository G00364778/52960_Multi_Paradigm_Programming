// Write a C program to check two given integers, and return
// true if one of them is 30 or if their sum is 30

#include <stdio.h>
#include <string.h>
#include <stdlib.h>
#include <stdbool.h>

int main()
{
  char* choice = (char*) malloc(10 * sizeof(char));
  int n1,n2,val;

 
	printf("Please enter first Number: ");
	scanf("%d", &n1);
  printf("Please enter second Number: ");
	scanf("%d", &n2);
  
  printf("Numbers entered is : %d and %d \n", n1,n2);
  if (n1==30 || n2==30 ||n1+n2==30){
    val=true;
  }
  else {
    val=false;
  }
  printf("val is: %d\n", val);
  if(val==1){
    printf("Either values or sum of both equils 30, therefore....");
    printf("result: %s\n","TRUE");
  }
  else {
    printf("Neither values or the sum of both equils 30, therefore....");
    printf("result: %s\n","FALSE");
  }
  return val;
}