# while무느로 작성한 선형 검색 알로기즘

from typing import Any, Sequence

""" 시퀀스 a(배열)에서 key와 값이 같은 원소를 선형 검색 """
def seq_search(a:Sequence , key : Any) -> int:
    i = 0

    while True:
        if i == len(a):
            return -1   # 검색에 실패할 경우 -1 반환
        if a[i] == key : 
            return i    # 검색 성공시 현재 배열 인덱스 반환 
        i += 1 


if __name__ == '__main__':
    num = int(input('원소 수를 입력하세요. : ')) 
    x = [None] * num 

    for i in range(num):
        x[i] = int(input(f'x[{i}] : '))
    
    ky = int(input('검색할 값을 입력하세요.'))
        
    idx = seq_search(x,ky)

    if idx == -1 :
        print("검색값이 존재하지 않습니다.")
    else:
        print(f"검색값은 x[{idx}]에 있습니다.")     # 첫번째 검색값 위치 반환 
    