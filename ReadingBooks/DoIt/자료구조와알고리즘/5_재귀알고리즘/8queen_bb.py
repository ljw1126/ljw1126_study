"""
규칙 2 . 각 행에 퀸을 1개만 배치합니다.
"""
# 행과 열에 퀸을 1개 배치하는 조합을 재귀적으로 나열하기 

pos = [0] * 8     # 각 열에서 퀸의 위치 
flag = [False] * 8 # 각 행에 퀸을 배치했는지 체크 

"""각 열에 배치한 퀸의 위치 출력"""
def put() ->None:
    for i in range(8):
        print(f'{pos[i]:2}',end='')
    print()

"""i 열의 알맞은 위치에 퀸을 배치"""
def set(i:int)-> None:
    for j in range(8):
        if not flag[j]:     # j행에 퀸을 배치하지 않았으면 
            pos[i] = j      # 퀸을 i열 j행에 배치 
            if i ==7:
                put()
            else:
                flag[j] = True 
                set(i+1)   # 다음 열에 퀸을 배치 
                flag[j] = False

set(0)