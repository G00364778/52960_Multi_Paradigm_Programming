# 4. Remove all even numbers Given an array of numbers return an array with all the even numbers removed.

def returneven(numbers):
  if not numbers:
    # if no numbers are passed in return an empty list
    return []
  if numbers[0] % 2 == 1:
    #if the number in the list is odd, i.e. a remainder of one is present
    # pop on the stack and run the next iteration
    return [numbers[0]] + returneven(numbers[1:])

  return returneven(numbers[1:])


if __name__ == '__main__':
  # array to test
  arr = [1,2,3,4,5]
  # call the recursive function passing  in the array
  ans = returneven(arr)
  # print the final answer
  print("Odd numbers in the array = {}".format(ans))