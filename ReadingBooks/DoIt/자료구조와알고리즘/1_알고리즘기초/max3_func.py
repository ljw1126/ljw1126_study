# 세 정수의 최대값 구하기 

def max3(a, b, c) :
    """a,b,c 최대값 구하여 반환함 """
    maximum = a 
    if b > maximum : maximum = b 
    if c > maximum : maximum = c 
    return maximum 


print(f'max3(1,2,3) = {max3(1,2,3)}' )
print(f'max3(3,2,1) = {max3(3,2,1)}' )
print(f'max3(13,2,0) = {max3(13,2,0)}' )
print(f'max3(1,10,9) = {max3(1,10,9)}' )
print(f'max3(1,32,33) = {max3(1,32,33)}' )
print(f'max3(321,1,2) = {max3(321,1,2)}' )








