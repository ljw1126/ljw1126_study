# 병합 정렬 알고리즘 구현하기 ( 재귀적 )


from typing import MutableSequence 

def merge_sort(a:MutableSequence) -> None:


    def _merge_sort(a:MutableSequence, left:int, right:int) -> None : 
        """ a[left] ~ a[right]를 재귀적으로 병합 정렬 """
        if left < right : 
            center = ( left + right ) // 2

            
            _merge_sort(a, left, center)
            print(f'left : {left} , center : {center}')
            _merge_sort(a, center+1 , right)
            print(f'center+1 : {center+1} , right : {right}')

            p = j = 0 
            i = k = left 
            print(f'left = {left} , right = {right}, center = {center}')
            while i <= center : 
                buff[p] = a[i]
                p += 1 
                i += 1
            print(f'buff1: {buff}')
            while i <= right and j < p : 
                if buff[j] <= a[i]:
                    a[k] = buff[j]
                    j += 1 
                else:
                    a[k] = a[i]
                    i += 1 
                k += 1
            print(f'a1: {a}')
            while j < p :
                a[k] = buff[j]
                k += 1 
                j += 1 
            print(f'a2: {a}')


    # 0. 여기부터 실행됨    
    n = len(a)
    buff = [None] * n    # 작업용 임시 배열
    _merge_sort(a,0,n-1) # 메소드가 위에 있어야 호춤됨
    del buff #작업용 임시 배열 소멸 


if __name__ == '__main__':
    print('병합 정렬을 수행합니다.')
    num = int(input('원소 수를 입력하세요 . : '))
    x = [None] * num 

    for i in range(num):
        x[i] = int(input(f'x[{i}] : '))

    merge_sort(x)

    print('오름차순으로 정렬했습니다.')
    for i in range(num):
        print(f'x[{i}] = {x[i]}')