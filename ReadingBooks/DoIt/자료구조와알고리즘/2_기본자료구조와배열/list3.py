# 리스트의 모든 우너소를 enumerate()함수로 스캔하기(1부터 카운트)

x = ['John','Georage','Paul','Ringo']

for i , name in enumerate(x,1):
    print(f'{i}번째 = {name}')
    