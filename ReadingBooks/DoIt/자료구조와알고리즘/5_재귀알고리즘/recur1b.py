# 스택으로 재귀 함수 구현하기 (재귀를 제거)
"""
# 입력예시
정수값을 입력하세요 . : 4
# 출력예시 
1
2
3
1
4
1
2

※ 직접 결과 적어보기 
"""
# 같은 경로에 stack.py 있어야 함
from stack import Stack

def recur(n:int) -> int:

    s = Stack(n)

    while True : 
        if n > 0 :
            s.push(n)
            n = n-1 
            continue 
        if not s.is_empty():  # 스택이 비어 있지 않으면
            n = s.pop()       # 저장한 값을 n에 팝 
            print(n)
            n = n-2
            continue
        break

x = int(input('정수값을 입력하세요 . : '))
recur(x)