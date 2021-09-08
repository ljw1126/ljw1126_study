# 배열을 두 그룹으로 나누기 

from typing import MutableSequence

def partition(a:MutableSequence) -> None: 
    n = len(a)
    pl = 0       # 왼쪽 커서 
    pr = n -1    # 오른쪽 커서
    x = a[n//2]  # 피벗(가운데 원소)

    while pl <= pr : 
        while a[pl] < x : pl += 1 
        while a[pr] > x : pr -= 1 
        if pl <= pr : 
            a[pl],a[pr] = a[pr],a[pl]
            pl += 1 
            pr -= 1 

    print(f'피벗은 {x}입니다.')
    print(f'pl = {pl} , pr = {pr}')
    print('피벗 이하인 그룹입니다.')
    print(*a[0:pl])  # a[0] ~ a[pl-1] 출력    , 이게 pl , pr 피벗에 겹치면 의미 없는 스왑발생하면서 pl= 5, pr = 3이 됨 

    if pl > pr + 1 : 
        print('피벗과 일치하는 그룹입니다.')
        print(*a[pr+1:pl]) # a[pr] ~ a[pl-1]

    print('피벗 이상인 그룹입니다.')
    print(*a[pr+1 : n]) # a[pr+1] ~ a[n-1]
    

if __name__ == '__main__':
    print('배열을 나눕니다.')
    num = int(input('원소 수를 입력하세요. : '))
    x = [None] * num 
    # 배열 크기 9 , [1,8,7,4,5,2,6,3] 
    for i in range(num):
        x[i] = int(input(f'x[{i}] : '))

    partition(x)