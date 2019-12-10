# 10. Verify the parentheses Given a string, return 
# true if it is a nesting of zero or more pairs of 
# parenthesis, like “(())” or “((()))”.

def checkballance(strchk,bal):
  if len(strchk) == 1:
    if strchk[0]=='(':
      bal+=1
    if strchk[0]==')':
      bal-=1  
    return bal
  if strchk[0]=='(':
    bal+=1
  if strchk[0]==')':
    bal-=1
  ret = checkballance(strchk[1:],bal)
  print("ret: {}".format(ret))
  return bal

if __name__ == '__main__':
  chk="((()))"
  status=checkballance(chk,0)
  print("Parenthesis balanced: {}".format(status))
  