# +를 n개 출력하되 w개마다 줄바꿈하기 2 

print('* 를 출력합니다. ')
n = int(input(' 몇 개를 출력할까요 ? : '))
w = int(input(' 몇 개마다 줄바꿈할까요 ? : '))

import time
start = time.time() 

for i in range(n // w) :
    print( '*' * w) 

rest = n % w 
if rest : # if문 판단 1번 
    print('*' * rest)

print("time :", time.time() - start)  # 현재시각 - 시작시간 = 실행 시간