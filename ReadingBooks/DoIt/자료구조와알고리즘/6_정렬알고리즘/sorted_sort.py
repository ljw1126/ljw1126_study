# sorted()함수를 사용하여 정렬하기 
# sorted() 함수는 '정렬을 수행한 뒤 늘어선 원소를 새로운 리스트로 생성하여 반환'하는 함수 


print('sorted() 함수를 사용하여 정렬합니다.')

num = int(input('원소 수를 입력하세요. : '))
x = [None] * num 

for i in range(num):
    x[i] = int(input(f'x[{i}] : '))

# 배열 x를 오름차순 정렬 
x = sorted(x)
print('오름차순 정렬함')
for i in range(num):
    print(f'x[{i}] = {x[i]}')

# 배열 x를 내림차순으로 정렬
x = sorted(x, reverse=True)
print('내림차순 정렬함')
for i in range(num):
    print(f'x[{i}] = {x[i]}')    