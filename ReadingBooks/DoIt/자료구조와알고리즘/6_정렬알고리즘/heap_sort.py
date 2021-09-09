# 힙 정렬 알고리즘 구현하기 

from typing import MutableSequence

def heap_sort(a:MutableSequence) -> None : 

    def down_heap(a:MutableSequence, left:int, right:int) -> None:
        print(f'시작 a = {a}')
        print(f'left = {left} , right = {right} ')
        temp = a[left]

        parent = left 
        while parent < (right + 1) // 2 :
            print(f'반복문: parent = {parent} , right = {right}')
            cl = parent * 2 + 1  # 왼쪽 자식 
            cr = cl + 1 
            child = cr if cr <= right and a[cr] > a[cl] else cl  # 큰 값을 선택 
            print(f'cl = {cl} , cr = {cr} , child = {child}')
            if temp >= a[child]:
                break 
            a[parent] = a[child]
            parent = child
            print(f'반복문 a = {a}') 
        a[parent] = temp 
        print(f'종료 a = {a}')

    n = len(a)

    for i in range((n-1)//2, -1, -1): # a[i] ~ a[n-1]을 힙으로 만들기 
        down_heap(a, i , n - 1)
            
    for i in range(n-1,0,-1):
        a[0],a[i] = a[i],a[0] # 최댓값인 a[0]와 마지막 원소를 교환 
        down_heap(a,0,i-1)  # a[0] ~ a[i-1]을 힙으로 만들기

if __name__ == '__main__':
    print('힙 정렬을 수행합니다.')
    num = int(input('원소 수를 입력하세요. : '))
    x = [None] * num 

    for i in range(num):
        x[i] = int(input(f'x[{i}] : '))

    heap_sort(x)

    print('오름차순으로 정렬했습니다.')
    for i in range(num):
        print(f'x[{i}] = {x[i]}')