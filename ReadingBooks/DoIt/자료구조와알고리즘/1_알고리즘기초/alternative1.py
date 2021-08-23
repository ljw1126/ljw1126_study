# + 와 - 를 번갈아 출력하기 1

print(' +와 -를 번갈아 출력합니다. ')
n = int(input(' 몇 개를 출력할까요 ? : '))

import time
start = time.time()  # 시작 시간 저장

for i in range(1, n + 1 ):
    if i % 2 : 
        print('-', end = '') # 홀수인 경우 ( 나머지가 있는 경우 )
    else :
        print('+', end = '') # 짝수인 경우 ( 나머지가 없는 경우 )

print()  

print("time :", time.time() - start) 