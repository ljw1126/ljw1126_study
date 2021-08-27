# 버블 정렬 알고리즘 구현하기(정렬 과정을 출력)
"""
i : 0 ~ 6 번 반복 
j : 6번부터 i번까지, -1 감소하면서 반복 
m : 의 경우 값 비교해서 ' '(공백)/+/- 넣거나 하는거 
    for m in range(0, n-1):
        print(f'{a[m]:2}|' + (' ' if m != j-1 else '+' if a[j-1] > a[j] else '-'), end='')   
        # '-' 는 앞 < 뒤 인 경우 , '+'는 앞 > 뒤 , 공백은 j-1 와 m 값이 같지 않을때만 ' ' 출력  
        # 즉 j -1 과 m 이 같은 때만 '+' , '-' 출력 
    print(f'{a[n-1]:2}') # 배열 마지막 수 출력              
"""
from typing import MutableSequence

def buffle_sort_verbose(a:MutableSequence) ->None:
    ccnt = 0 #비교횟수
    scnt = 0 #교환횟수 
    n = len(a)

    for i in range(n-1):
        print(f'패스 {i+1}')
        for j in range(n-1,i,-1):
            
            for m in range(0, n-1):
                print(f'{a[m]:2}|' + (' ' if m != j-1 else '+' if a[j-1] > a[j] else '-'), end='')
            
            print(f'{a[n-1]:2}') # 배열 마지막 수 출력 
            
            ccnt += 1 
            # 패스(Pass) : 일련의 비교/교환하는 과정 
            if a[j-1] > a[j]:
                a[j-1], a[j] = a[j],a[j-1]
                scnt += 1

        # i 번 정렬 후 결과 출력 
        for m in range(0,n-1):
            print(f'{a[m]:2}',end=' ') # 기본 오른쪽 정렬에 왼쪽 공간 2 만큼 확보 
        print(f'{a[n-1]:2}*')
    
    print(f'비교를 {ccnt}번 했습니다')
    print(f'교환을 {scnt}번 했습니다')

if __name__ == '__main__':
    print('버블 정렬을 수행합니다.')
    num = int(input('원소 수를 입력하세요.: '))
    x = [None] * num      # 원소 수가 num인 배열 생성 

    for i in range(num):
        x[i] = int(input(f'x[{i}]:'))

    buffle_sort_verbose(x) # 배열 x를 버블 정렬 
