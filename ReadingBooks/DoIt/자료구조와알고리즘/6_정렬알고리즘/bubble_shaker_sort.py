# 셰이커 정렬 (버블)알고리즘 구현하기
"""
셰이커 정렬 
- 홀수 패스에서 가장 작은 원소를 맨앞으로 이동시키고,
  짝수 패스에서는 가장 큰 원소를 맨 뒤로 이동시켜 패스의 스캔 방향을 
  번갈아 바꾸어 봄 
"""
from typing import MutableSequence


def shaker_sort(a:MutableSequence) ->None:
    left = 0 
    n = len(a)
    right = n -1 
    last = right 

    # 그림으로 보니깐 이해감 ( 교차로 줄여 간다는 거네 )
    while left < right : 
        print(f'패스========================={left}/{right}')
        for j in range(right, left, -1):

            for m in range(0, n-1):
                print(f'{a[m]:2}|' + (' ' if m != j-1 else '+' if a[j-1] > a[j] else '-'), end='')
            print(f'{a[n-1]:2}') # 배열 마지막 수 출력     

            if a[j-1] > a[j]:
                a[j-1], a[j] = a[j],a[j-1]
                last = j 
        left = last 

        print(f'================================{left}/{right}')

        for j in range(left,right):
            for m in range(0, n-1):
                print(f'{a[m]:2}|' + (' ' if m != j else '+' if a[j] > a[j+1] else '-'), end='')
            print(f'{a[n-1]:2}') # 배열 마지막 수 출력 

            if a[j] > a[j+1]:
                a[j],a[j+1] = a[j+1],a[j]
                last = j 
        right = last 


if __name__ == '__main__':
    num = int(input('원소 수를 입력하세요 . : '))
    x = [None] * num 

    for i in range(num):
        x[i] = int(input(f'x[{i}] : '))

    shaker_sort(x)

    # 정렬 결과 출력 
    for i in x : 
        print(i, end = ' ')