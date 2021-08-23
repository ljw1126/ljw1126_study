# +를 n개 출력하되 w개마다 줄바꿈하기 1 

print('* 를 출력합니다. ')
n = int(input(' 몇 개를 출력할까요 ? : '))
w = int(input(' 몇 개마다 줄바꿈할까요 ? : '))

import time
start = time.time()

for i in range(n) : 
        #print(f'*{i}' , end = '|')
        print('*',end='')
        if i % w == w - 1 : #n번 판단 
            print() # 줄바꿈 

if n % w :   # 1번 판단
    print()  # 줄 바꿈 

print("time :", time.time() - start)  # 현재시각 - 시작시간 = 실행 시간