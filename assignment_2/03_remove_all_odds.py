# 3. Remove all odd numbers Given an array of numbers return an array with all the odd numbers removed.

def returnodds(numbers):
  if not numbers:
    # if no numbers are passed in return an empty list
    return []
  if numbers[0] % 2 == 0:
    # if the list in the list is even, i.e. a remainder of zero is present
    # pop on the stack and run the next iteration
    return [numbers[0]] + returnodds(numbers[1:])

  return returnodds(numbers[1:])


if __name__ == '__main__':
  # array to test
  arr = [1,2,3,4,5]
  # call the recursive function passing  in the array
  ans = returnodds(arr)
  # print the final answer
  print("Even numbers in the array = {}".format(ans))