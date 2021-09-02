# 셸 정렬 알고리즘 구현하기 (h*3 + 1 수열 사용) // 무슨 내용인지 전혀 이해 안감 


from typing import MutableSequence

def shell_sort(a:MutableSequence) ->None:
    n = len(a)
    h = 1 

    while h < n // 9 :
        h = h * 3 + 1 

    while h > 0 :
        for i in range(h,n):
            j = i -h 
            tmp = a[i]
            while j >= 0 and a[j] > tmp:
                a[j+h] = a[j]
                j -= h 
            a[j+h] = tmp 
        h //= 3 

if __name__ == '__main__':
    print('셸 정렬을 수행합니다.(h*3 +1 의 수열 사용) ')
    num = int(input('원소 수를 입력하세요. : '))
    x = [None] * num 

    for i in range(num):
        x[i] = int(input(f'x[{i}] : '))

    shell_sort(x)

    print('오름차순 정렬합니다. ==== ')
    for i in range(num):
        print(f'x[{i}] = {x[i]}')

