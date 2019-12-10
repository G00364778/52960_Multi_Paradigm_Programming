# 5. Replace a given character with ’*’ Given a string, and a character to replace, return a string where each occurance of the character is replaced with ’*’.

newString = ''

def replaceChar(myString, oldChar, newChar):
  global newString
  # if myString has no characters left, return newString
  if myString == '':
      return newString

  elif myString[0] == oldChar:
      # then add newChar to newString
      newString += newChar

  else:
      # then add myString[0] to newString
      newString += myString[0]

  # Chop the first character off of myString
  myString = myString[1:]

  # recurse
  return replaceChar(myString, oldChar, newChar)

if __name__ == '__main__':
  
  # array to test
  string = 'The rain in Spain falls mainley in the plane'
  # call the recursive function passing  in the array
  ans = replaceChar(string, ' ', '_')
  # print the final answer
  print("NewStr: {}".format(ans))