# 하노이의 탑 구현 -- 해당 방식의 경우 코드 길이는 짧으나 258byte 이나 메모리를 두배 차지함 120648
# 메모리 46932 으로 줄이려면 메모리제이션 기법 적용하는 듯함 
# 참고 https://www.acmicpc.net/source/23977483
"""
210826 메모리제이션 방식 인데 .. 이해 덜됨  

import sys

def hanoi(level,start,goal):
    myCache = {}
    def innerHanoi(level,start,goal):
        if (level,start,goal) in myCache:
            return myCache[(level,start,goal)]
        else:
            if level==1:
                returnStr = str(start) + " " + str(goal)
            else:
                goalTemp = 6-start-goal
                returnStr = "\n".join(char for char in [innerHanoi(level-1,start,goalTemp),
                    str(start) + " " + str(goal),
                    innerHanoi(level-1,goalTemp,goal)])
            myCache[(level,start,goal)] = returnStr
            return returnStr
    print(innerHanoi(level,start,goal))
        
N = int(sys.stdin.readline())
print(2**N-1)
hanoi(N,1,3)

"""


"""
#하노이탑 구현예제
원반의 개수를 입력하세요.3
위/no:3/x:1/y:3
위/no:2/x:1/y:2
원반 [1]을(를) 1기둥에서 3기둥으로 옮깁니다.
원반 [2]을(를) 1기둥에서 2기둥으로 옮깁니다.
아래/no:2/x:1/y:2
원반 [1]을(를) 3기둥에서 2기둥으로 옮깁니다.
원반 [3]을(를) 1기둥에서 3기둥으로 옮깁니다.
아래/no:3/x:1/y:3
위/no:2/x:2/y:3
원반 [1]을(를) 2기둥에서 1기둥으로 옮깁니다.
원반 [2]을(를) 2기둥에서 3기둥으로 옮깁니다.
아래/no:2/x:2/y:3
원반 [1]을(를) 1기둥에서 3기둥으로 옮깁니다.

"""
"""
참고 : https://sikaleo.tistory.com/29
- n : 옮기려는 원반의 개수 
- from_pos : 옮길 원반이 현재 있는 출발점 기둥 
- to_pos : 원반을 옮길 도착점 기둥 
- aux_pos : 옮기는 과정에서 사용할 보조 기둥 
- 그외 원반을 옮기는 순서를 print()로 출력함 

def hanoi(n, from_pos, to_pos, aux_pos):

    if n == 1 :
        print(from_pos , '->', to_pos)
        return 
    
    hanoi(n-1, from_pos, aux_pos, to_pos)
    print(from_pos,'->',to_pos)
    hanoi(n-1, aux_pos, to_pos, from_pos)

print('n = 1')
hanoi(1,1,3,2)

print('n = 2')
hanoi(2,1,3,2)

print('n = 3')
hanoi(3,1,3,2)

print('n = 4')
hanoi(4,1,3,2)

"""


""" 원반 no개를 x 기둥에서 y 기둥으로 옮김"""
def move(no:int, x:int,y:int)->None:
    if no > 1 : 
        move(no -1 , x , 6 -x -y) # 기둥 번호의 합이 6이므로 시작 기둥, 목표기둥이 어느 위치에 있든 중간 기둥은(6-x-y)로 구할 수 있음 

    print(f'원반 [{no}]을(를) {x}기둥에서 {y}기둥으로 옮깁니다.')

    if no > 1 : 
        move(no -1, 6 -x -y , y)

print('#하노이탑 구현예제')
n = int(input('원반의 개수를 입력하세요.'))

move(n,1,3) # 1기둥에 쌓인 원반 n개를 3기둥으로 옮김 





