# 오른쪽 아래가 직각인 이등변 삼각형을 * 으로 출력하기 
## 실습 1-23

print('오른쪽 아래가 직각인 이등변 삼각형을 출력합니다. ')
n = int(input('짧은 변의 길이를 입력하세요. : '))

for i in range(n): # 시작지정안했으니  0 ~ n 까지 
    for _ in range ( n - i - 1) :
        print( ' ' , end = '')
    for j in range ( i + 1 ):
        print('*' , end = '')
    print() #행 변경 ( 줄바꿈 )