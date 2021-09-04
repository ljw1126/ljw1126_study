# 정렬을 마친 두 배열을 병합하기 

from typing import Sequence, MutableSequence

def merge_sorted(a:Sequence, b:Sequence, c:MutableSequence) -> None : 
    """ 정렬을 마친 배열 a와 b를 병합하여 c에 저장"""
    pa,pb,pc = 0,0,0  # 각 배열의 커서 
    na,nb,nc = len(a),len(b),len(c)

    # 오름차순 정렬 
    while pa < na and pb < nb : 
        if a[pa] <= b[pb]:
            c[pc] = a[pa]
            pa += 1 
        else : # a[pa] > b[pb]
            c[pc] = b[pb]
            pb += 1
        pc += 1

    while pa < na :   # a에 남은 원소를 c에 복사 
        c[pc] = a[pa]
        pa += 1
        pc += 1
    
    while pb < nb : 
        c[pc] = b[pb]
        pb += 1
        pc += 1

if __name__ == '__main__':
    a = [2,4,6,8,11,13]
    b = [1,2,3,4,9,16,21]
    c = [None] * (len(a)+len(b))
    print('정렬을 마친 두 배열의 병합을 수행합니다.')

    merge_sorted(a,b,c) 

    print('배열 a와 b를 병합하여 배열 c에 저장완료')   
    print(f'배열 a : {a}')
    print(f'배열 b : {b}')
    print(f'배열 c : {c}')
