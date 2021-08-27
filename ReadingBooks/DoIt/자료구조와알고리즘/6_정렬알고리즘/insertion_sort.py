# 단순 삽입 정렬 알고리즘 구현 
"""

종료조건 
1. 정렬된 배열의 왼쪽 끝에 도달한 경우 
2. tmp보다 작거나 키값이 같은 원소 a[j-1]을 발견한 경우 

계속조건(not 종료조건)
1. j가 0보다 큰 경우 
2. a[j-1] 의 값이 tmp 보다 큰 경우 

"""
from typing import MutableSequence

def insertion_sort(a:MutableSequence) -> None:
    n = len(a)
    for i in range(1,n):
        j = i 
        tmp = a[i]
        while j > 0 and a[j-1] > tmp:
            a[j] = a[j-1]
            j -= 1
        a[j] = tmp 


if __name__ == '__main__':
    print("단순 삽입 정렬")
    num = int(input('배열의 원소 개수를 입력하세요.:'))
    array = [None] * num 

    for i in range(num):
        array[i] = int(input(f'array[{i}]값을 입력하세요'))

    insertion_sort(array)
    print("오름차순 정렬 결과 ================ ")
    print(array)