# 6. Find index in array for item. Given an array, and an element to 
# search for return the index of the element in the array or -1 if 
# the element is not present in the array.

def recursive_index(L, v):
    if v not in L: 
      return -1
    # if a match was found at zero poistion return zero, otherwise 
    # iterate until a match is found. Once a match is found pop the 
    # stack and and keep on adding one and return the count when all 
    # iterations are popped of the stack
    return 0 if L[0] == v else 1 + recursive_index(L[1:], v)


if __name__ == '__main__':
  
  # array to test
  string = 'The rain in Spain falls mainley in the plane'
  find = 'S'
  # call the recursive function passing  in the array
  ans = recursive_index(string, find)
  # print the final answer
  print("Found '{}' at position {} in string '{}'".format(find, ans+1, string))