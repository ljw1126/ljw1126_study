# 1~12 까지 8을 건너뛰고 출력하기 1 

for i in range ( 1, 13 ): # range 는 1 ~ n - 1 번  // 시작 지정안하면 0번 부터  
    if i == 8 : 
        continue 
    print(i, end = ' ')

print()