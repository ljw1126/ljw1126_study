# + 와 - 를 번갈아 출력하기 1


print(' +와 -를 번갈아 출력합니다. ')
n = int(input(' 몇 개를 출력할까요 ? : '))

import time
start = time.time()  # 시작 시간 저장 

#  // 몫 연산자 
for _ in range( n // 2 ):
        print('+-', end = '') 

if n % 2 : 
    print('+' , end = '') # n이 홀수 일때만 마지막 + 출력 

print("time :", time.time() - start) 