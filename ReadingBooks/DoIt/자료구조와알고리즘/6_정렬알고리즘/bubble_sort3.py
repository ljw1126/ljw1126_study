# 버블 정렬 알고리즘 구현하기(알고리즘의 개선2)

from typing import MutableMapping

def bubble_sort(a:MutableMapping)->None:
    n = len(a)
    k = 0 

    while k < n -1 :
        last = n-1
        for j in range(n-1,k,-1):
            if a[j-1] > a[j]:
                a[j-1],a[j] = a[j],a[j-1]
                last = j 
        k = last 


if __name__ == '__main__':

    num = int(input('원소의 갯수를 입력하세요. : '))
    array = [None] * num 

    for i in range(num):
        array[i] = int(input('array[{i}]의 값을 입력하세요. : '))

    bubble_sort(array)

    for i in array :
        print(i,end=' ')
