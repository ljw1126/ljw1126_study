# seq_search() 함수를 사용하여 실수 검색하기 

from ssearch_while import seq_search 

print('실수를 검색합니다.')
print('주의 : "End"를 입력하면 종료합니다. ')

number = 0 
x = []

while True : 
    s = input(f'x[{number}] : ')
    if s == 'end':
        break 
    x.append(float(s))
    number += 1


ky = float(input('검색할 값을 입력하세요. : ')) #검색할 키 ky를 입력받기 

idx = seq_search(x,ky)
if idx == -1 :
    print('검색값이 존재하지 않습니다. ')
else:
    print(f'검색값은 x[{idx}]에 있습니다. ')