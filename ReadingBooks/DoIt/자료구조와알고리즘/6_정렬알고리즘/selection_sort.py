# 단순 선택 정렬 알고리즘 구현 

from typing import MutableSequence

def selection_sort(a:MutableSequence)->None:
    n = len(a)
    for i in range(n-1):
        min_idx = i # 정렬할 부분에서 가장 작은 원소의 인덱스 
        for j in range(i+1,n):
            if a[min_idx] > a[j]:
                min_idx = j 
        
        # 정렬할 부분에서 맨 앞의 원소와 가장 작은 원소를 교환
        a[min_idx], a[i] = a[i], a[min_idx] 


if __name__ == '__main__':
    print("단순 선택 정렬입니다.")
    num = int(input('원소의 개수를 입력하세요 . : '))
    array = [None] * num 

    for i in range(num):
        array[i] = int(input(f'array[{i}]:'))

    selection_sort(array)

    print(f'결과값 : {array}')