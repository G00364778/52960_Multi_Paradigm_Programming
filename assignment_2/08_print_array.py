# 8. Print an array Given an array of integers prints all 
# the elements one per line. This is a little bit different 
# as there is no need for a ’return’ statement just to print 
# and recurse.


def stepArr(arr): 
  if len(arr)== 1: 
    print(arr[0])
  else: 
    print(arr[0])
    stepArr(arr[1:])

if __name__ == '__main__':
  # input values to list 
  arr = [1, 2, 3, 4, 5, 6, 7, 8, 9, 10] 
  stepArr(arr)
  