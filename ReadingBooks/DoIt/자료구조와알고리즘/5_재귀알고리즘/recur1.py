# 순수한 재귀함수 구현 
"""
# 입력예시 
정수값을 입력하세요. : 4
# 출력예시 
1
2
3
1
4
1
2
"""
def recur(n: int) -> int:
    if n > 0:
        recur(n-1)
        print(n)
        recur(n-2)

x = int(input('정수값을 입력하세요. : '))

recur(x)