# 퀵 정렬 알고리즘 구현하기 

from typing import MutableSequence

def qsort(a:MutableSequence, left:int, right:int) -> None : 

     pl = left 
     pr = right 
     pivot = a[ (left+right) //2] 

     # 앞 partition 예제 동일
     while pl <= pr : 
         while a[pl] < pivot : pl += 1
         while a[pr] > pivot : pr -= 1 
         if pl <= pr : 
           a[pl],a[pr] = a[pr],a[pl]
           pl += 1 
           pr -= 1 

     # 여기 주목 
     if left < pr : qsort(a,left,pr)
     if pl < right : qsort(a,pl,right)

def quick_sort(a:MutableSequence) -> None : 
    qsort(a, 0, len(a) - 1)

if __name__ == '__main__':
    print('퀵 정렬 수행함 ============')
    num = int(input('원소 수를 입력하세요 . : '))
    x = [None] * num 

    for i in range(num):
        x[i] = int(input(f' x[{i}] : '))

    quick_sort(x)

    print('오름차순 정렬결과=======')    
    for i in range(num):
        print(f' x[{i}] = {x[i]}')

