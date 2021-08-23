# 2자리 양수 ( 10 ~ 99 ) 입력받기 
## 실습 1C-3

print('2자리 양수를 입력하세요 ')

# while True : 
#     no = int(input('값을 입력하세요. : '))
#     if no >= 10 and no <= 99 :
#         break 


# while True:
#     no = int(input('값을 입력하세요.: '))
#     if 10 <= no <= 99: # and 결합 , 위와 같음 
#         break

while True:
    no = int(input('값을 입력하세요.: '))
    if not(no < 10 or no > 99):  # 드모르간의 법칙 사용 , no >= 10 and no <= 99 와 같음  // p51
        break

print(f' 입력 받은 양수는 {no}입니다. ')