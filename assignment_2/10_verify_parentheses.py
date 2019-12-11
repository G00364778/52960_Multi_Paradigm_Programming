# 10. Verify the parentheses Given a string, return 
# true if it is a nesting of zero or more pairs of 
# parenthesis, like “(())” or “((()))”.

def checkballance(strchk):
  '''
  Check the balance of parentheses by adding to the counter v every 
  time an opening bracket is encountered and subtracting every time 
  a closing bracket is encountered. The final return will return the 
  value of v.

  If v = 0 the parenteses are balanced
  If V < 0 there are more closing parentehses
  If v > 0 there are more opening parentheses
  '''
  if len(strchk) == 1: # for last values dont iterate again, just return the value
    if strchk[0]=='(': # for opening bracket add one
      v=1
    if strchk[0]==')': # for closing bracket subtract one
      v=-1  
    return v
  if strchk[0]=='(': # for opening bracket add one
    v=1
  if strchk[0]==')': # for closing bracket subtract one
    v=-1
  return v + checkballance(strchk[1:]) # return v and iterate to next value in string

if __name__ == '__main__':
  # string to test 
  chk="((())))"
  # call string iteration test
  status=checkballance(chk)
  #if return value is zero it's balanced
  if status == 0:
    balanced = True
  #otherwise it's unbalanced
  else:
    balanced = False
  print("Parenthesis balanced: {} ({})".format(balanced,status))
  