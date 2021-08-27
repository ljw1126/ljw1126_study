# 버블 정렬 알고리즘 구현하기(알고리즘의 개선1)

from typing import MutableSequence

""" 교환 횟수가 없을 경우 종료 ( 이미 정렬 다 되어 있다는 .. ) """
def bubble_sort(a:MutableSequence) -> None:
    n = len(a)

    for i in range(n-1):
        exchng = 0 # 패스에서 교환횟수 
        for j in range(n-1, i, -1):
            if a[j-1] > a[j]:
                a[j-1],a[j] = a[j],a[j-1]
                exchng += 1
        
        if exchng == 0 :
            break

if __name__ == '__main__':
    print('버블 정렬을 수행합니다.')
    num = int(input('원소 수를 입력하세요. : '))
    x = [None] * num 

    for i in range(num):
        x[i] = int(input(f'x[{i}] : '))

    bubble_sort(x) 

    for i in x:
        print(i,end = ' ')     
