# 비 재귀적으로 재귀 함수 구현하기 (꼬리 재귀 제거)
"""
# 입력예시
값을 입력하세요 . : 4
# 출력예시 
1
2
3
1
4
1
2

"""
def recur(n: int)->int:
    while n > 0 :
        recur(n-1)
        print(n)
        n = n-2

recur(int(input('값을 입력하세요 . : ')))