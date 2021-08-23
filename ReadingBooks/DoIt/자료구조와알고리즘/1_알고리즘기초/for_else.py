# 10 ~ 99 사이의 난수 n 개 생성하기 ( 13이 나오면 중단 )

import random 

n = int(input('난수의 개수를 입력하세요. : '))
# 13이 생성되지 않으면 반복이 끝난 다음 else문이 실햄됨 ! 
for _ in range(n):
    r = random.randint(10, 99)
    print(r, end = '|' )
    if r == 13 : 
        print('\n 프로그램을 중단합니다. ')
        break 

else : 
    print('\n 난수 생성을 종료합니다. ')