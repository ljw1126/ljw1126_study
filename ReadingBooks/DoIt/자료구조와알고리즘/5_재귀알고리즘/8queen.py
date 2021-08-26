# 8퀸 알고리즘 구현하기 

pos = [0] * 8        # 각 열에 배치한 퀸의 위치
flag_a = [False] * 8  # 각 행에 퀸을 배치했는지 체크 
flag_b = [False] * 15 # 대각선 방향(↗↙)으로 퀸을 배치했는지 체크 
flag_c = [False] * 15 # 대각선 방향(↖↘)으로 퀸을 배치했는지 체크 

"""각 열에 배치한 퀸의 위치를 출력"""
def put() -> None:
    for j in range(8):
        for i in range(8):
            #print(f'{pos[i]:2}',end='')      # 표기법 뭐임??
            print('■' if pos[i] == j else '□', end = '')
        print()
    print()
    
"""i열에 알맞은 위치에 퀸을 배치"""
def set(i:int) ->None:
    for j in range(8):
        if ( not flag_a[j] and not flag_b[i+j] and not flag_c[i-j+7]):  # ???
            pos[i] = j     # 퀸을 i열 j행에 배치 
            if i == 7 : 
                put()
            else:
                flag_a[j] = flag_b[i+j] = flag_c[i-j+7] = True  # 하나도 모르겠음
                set(i+1)
                flag_a[j] = flag_b[i+j] = flag_c[i-j+7] = False # 마찬가지 

set(0) # 0 열에 퀸을 배치    