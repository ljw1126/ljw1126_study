# 뮤터블 시퀀스 원소를 역순으로 정렬

from typing import Any, MutableSequence

"""뮤터블 시퀀스 a의 원소를 역순으로 정렬"""
def reverse_array(a:MutableSequence) -> None:
    n = len(a)
    for i in range( n // 2):
        a[i],a[n-i-1] = a[n-i-1],a[i]

if __name__ == '__main__':
    print('배열 원소를 역순으로 정렬합니다.')
    nx = int(input('원소 수를 입력하세요 . : '))
    x = [None] * nx 

    for i in range(nx):
        x[i] = int(input(f'x[{i}] 값을 입력하세요 . : '))

    reverse_array(x)
    #x.reverse()  # 리스트 x의 원소를 역순으로 정렬 , 역순으로 꺼내는 이터레이터를 반환하는 거
    #y = list(reversed(x)) # reversed()가 반환하는 이터러블 객체를 list()함수에 넣어 새로운 함수로 만듬

    print('배열 원소를 역순으로 정렬합니다.')
    for i in range(len(x)):
        print(f'x[{i}] = {x[i]}')