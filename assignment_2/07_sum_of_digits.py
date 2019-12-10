# 7. Sum of Digits Given a whole, number such as 23, return 
# the sum of the digits in the number i.e. 2 + 3 = 5. For 
# this would be useful to convert the number to a string 
# then break it apart into digits.


def sum_of_digit(n): 
    if n == 0: 
        return 0
    return (n % 10 + sum_of_digit(int(n / 10))) 
  
if __name__ == '__main__':
  # Find the sume of 12345 
  num = 12345
  result = sum_of_digit(num) 
  print("Sum of digits in",num,"is", result)