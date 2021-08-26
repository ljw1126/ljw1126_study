#  재귀호출 거꾸로 출력하기
"""
# 입력예시 
정수값을 입력하세요. : 4
# 출력예시 
2
1
4
1
3
2
1
"""
def recur(n: int) -> int:
    if n > 0:
        recur(n-2)
        print(n)
        recur(n-1)

x = int(input('정수값을 입력하세요. : '))

recur(x)