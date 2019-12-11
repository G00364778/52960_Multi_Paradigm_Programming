# 9. Find the minimum element in an array of integers. 
# You can carry some extra information through method 
# arguments such as minimum value.

def findmin(A, n): 
      
    # if size = 0 means whole array 
    # has been traversed 
    if (n == 1): 
      #if it's the last value just return it for comparison with last stacked valu
      return A[0]
    #otherwise find the min between returned and current
    minval=min(A[n - 1], findmin(A, n - 1))
    return minval
  
# Driver Code 
if __name__ == '__main__': 
    A = [10, 14, 45, 6, 50, 10, 22] 
    n = len(A) 
    print("Smallest value in array is: {}".format(findmin(A, n)))