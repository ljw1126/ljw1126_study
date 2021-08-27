# 이진 삽입 정렬 알고리즘 구현(bisect.insort사용 )

from typing import MutableSequence
import bisect

def binary_insertion_sort(a:MutableSequence)->None:
    for i in range(1, len(a)):
        bisect.insort(a, a.pop(i), 0, i)

if __name__ == '__main__':
    print("bisect모듈의 insort()로 삽입정렬 수행")
    num = int(input('배열의 크기를 입력하세요. : '))
    x = [None] * num 

    for i in range(num):
        x[i] = int(input(f'x[{i}] : '))

    binary_insertion_sort(x)
    
    print('오름차순으로 정렬했습니다.')
    print(x)