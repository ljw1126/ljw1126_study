#1부터 n까지 정수의 합 구하기1 (while문)

print('1부터 n까지 정수의 합 구함 ')
n = int(input('n값을 입력하세요 : '))

import time
start = time.time()  # 시작 시간 저장

sum = 0 
i = 1 

while i <= n :
      sum += i 
      i += 1 

print("time :", time.time() - start) 
print(f' 1부터 {n}까지의 합은 {sum} 입니다 ')