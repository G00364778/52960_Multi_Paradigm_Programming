# 1. Sum of an array Given an array of numbers return it’s sum (all the numbers added together).
# so using the current sample array the values are popped on the stak untill five is returned, 
# five is the added to four and nine is returned to the next iteration where nine is added to 
# three and twelve returned to be added to two and fourteen returned to the last iteration on 
# the stack where one is adde and the final summed value return to the calling function in main.

def sumArr(arr): 
     if len(arr)== 1: 
        # if the array length is one just return the last value to the 
        # previous call on the stack
        return arr[0] 
     else:
        # recursively keep on passing all values but the first into sumArr
        # untill the length is one and then add the sums of the previous to 
        # the current untill all values are popped off the stack
        summed = arr[0]+sumArr(arr[1:]) 
        return summed

if __name__ == '__main__':
  # array to sum
  arr = [1,2,3,4,5]
  # call the recursive function passinbg  in the array
  ans = sumArr(arr)
  # print the final answer
  print("Sum of array = {}".format(ans))