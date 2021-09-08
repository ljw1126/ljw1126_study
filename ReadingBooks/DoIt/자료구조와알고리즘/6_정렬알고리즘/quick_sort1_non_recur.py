# 비재귀적인 퀵 정렬 구현 (스택활용)

from stack import Stack  # 4장 예제 파일사용 (동일 경로 있어야함)
from typing import MutableSequence  

def qsort(a:MutableSequence , left:int, right:int) -> None:
    range = Stack(len(a)) # 스택생성 , 굳이 right - left +1 ? 

    range.push((left,right))
    print(f' 초기 range = {range.dump()}')
    while not range.is_empty():
        print(f' 시작 range = {range.dump()}')
        pl, pr = left,right = range.pop()     # 왼쪽, 오른쪽 커서를 꺼냄 unpack
        x = a[(left+right) // 2]

        # 앞 예제 동일 
        while pl <= pr :
            while a[pl] < x: pl += 1 
            while a[pr] > x : pr -= 1 
            if pl <= pr : 
                a[pl],a[pr] = a[pr],a[pl]
                pl += 1 
                pr -= 1
        print(f' pl = {pr}, pr = {pr} , left = {left}, right={right} , x = {x}')
        print(f' 정렬 후 range = {range.dump()}')
        if left < pr : range.push((left, pr))
        if pl < right : range.push((pl,right))


if __name__ == '__main__':
    print('퀵 정렬 수행함 ============')
    num = int(input('원소 수를 입력하세요 . : '))
    x = [None] * num 
    # 5 8 4 2 6 1 3 9 7
    for i in range(num):
        x[i] = int(input(f' x[{i}] : '))

    qsort(x, 0 , len(x) - 1)

    print('오름차순 정렬결과=======')    
    for i in range(num):
        print(f' x[{i}] = {x[i]}')
