# 가로, 세로 길이가 정수이고 넓이가 area 인 직사각형에서 변의 길이 나열하기 ( 실습 1- 17, 약수 나열  )

area = int (input ('직사각형의 넓이를 입력하세요 . : '))

for i in range ( 1, area + 1) :
    if i * i  > area : break 
    if area % i : continue  # 홀수인 경우 
    print(f'{i}  x { area // i }')