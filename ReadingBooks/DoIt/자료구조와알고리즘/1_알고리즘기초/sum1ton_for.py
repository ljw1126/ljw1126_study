#1부터 n까지 정수의 합 구하기2 (for문)

print('1부터 n까지 정수의 합 구함 ')
n = int(input('n값을 입력하세요 : '))

import time
start = time.time()  # 시작 시간 저장

sum = 0 
i = 1 

for i in range ( 1, n + 1) :
    sum += i

print("time :", time.time() - start) 
print(f' 1부터 {n}까지의 합은 {sum} 입니다 ')