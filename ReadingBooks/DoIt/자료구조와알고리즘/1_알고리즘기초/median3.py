import time
start = time.time()  # 시작 시간 저장

# 세 정수를 입력받아 중앙값 구하기 1

def med3( a, b, c ):
    """ 중앙값을 구하여 반환 """
    if a >= b : 
       if b >= c :
            return b
       elif a <= c :
            return a 
       else :
            return c 
    elif a > c :
          return a 
    elif b > c :
          return c 
    else :
          return b 

# 세 정수를 입력받아 중앙값 구하기 2 
def med3_b(a,b,c):
    """ 중앙값 구하는 다른 방법 """
    if ( b >= a and c <=a ) or (b <= a and c >= a):
        return a 
    elif ( a > b and c < b) or (a < b and c > b):
        return b 
    return c


print('세 정수의 중앙값을 구합니다')
# a = int(input('정수  a의 값을 입력하세요 : '))
# b = int(input('정수  b의 값을 입력하세요 : '))
# c = int(input('정수  c의 값을 입력하세요 : '))
a = 1 
b = 2 
c = 3

print(f'중앙값은 {med3_b(a,b,c)} 입니다. ')




print("time :", time.time() - start)  # 현재시각 - 시작시간 = 실행 시간


# 측정시 med3은 0.0009586811065673828 , med3_b : 0.0009980201721191406
"""
    med3_b에서 a와 b의 비교를 이미 마친 상태에서 다시 비교하는 것이 효율적이지 않다는 뜻 ( p 25 )
"""