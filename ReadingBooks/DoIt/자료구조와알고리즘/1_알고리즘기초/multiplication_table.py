# 구구단 곱셈표 출력 p52
## 실습 1-21 

print('-' * 27)
for i in range (1, 10) : # 1~9까지 ( 1, n-1)
    for j in range ( 1, 10 ):
        print(f'{i * j:3}' , end = '')
    print()
print('-' * 27)
