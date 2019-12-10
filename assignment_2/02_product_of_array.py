# 2. Product of an array Given an array of numbers return it’s product (all the numbers multiplied together).

# Similarly using the current sample array the values are popped on the stack untill five is returned, 
# five is the multiplied with four and twenty is returned to the next iteration where twenty is multiplied with 
# three and sixty returned to be multiplied with two and 120 returned to the last iteration on 
# the stack where one is multiplied with it and the final prouct return to the calling function in main.

def prodArr(arr): 
     if len(arr)== 1: 
        # if the array length is one just return the last value to the 
        # previous call on the stack
        return arr[0] 
     else:
        # recursively keep on passing all values but the first into prodArr
        # untill the length is one and then multiply the previous previous result 
        # with the current untill all values are popped off the stack
        prod = arr[0]*prodArr(arr[1:]) 
        return prod

if __name__ == '__main__':
  # array to multiply
  arr = [1,2,3,4,5]
  # call the recursive function passing  in the array
  ans = prodArr(arr)
  # print the final answer
  print("Product of array = {}".format(ans))