# 선형 검색 알고리즘을 보초법으로 수정 

from typing import Any, Sequence
import copy 

"""시퀀스 seq에서 key와 일치하는 원소를 선형 검색(보초법)"""
def seq_search(seq:Sequence , key:Any) -> int:
    
    a = copy.deepcopy(seq) # seq를 복사 
    a.append(key)

    i = 0
    while True :
        if a[i] == key:
            break
        i +=1 
    return -1 if i == len(seq) else i  # 위에서 len() 비교하던 if문이 없어져 cost는 반이 되고 , return 에 판단 횟수 1 추가 될 뿐 

if __name__ == '__main__':
    num = int(input('원소 수를 입력하세요 . : '))
    x = [None] * num 

    for i in range(num):
        x[i] = int(input(f'x[{i}] : '))

    ky = int(input('검색할 값을 입력하세요. : ')) # 검색할 키 ky를 입력받기 

    idx = seq_search(x,ky)

    if idx == -1 :
        print('검색값이 존재하지 않습니다.')
    else:
        print(f'검색값은 x[{idx}]에 있습니다.')