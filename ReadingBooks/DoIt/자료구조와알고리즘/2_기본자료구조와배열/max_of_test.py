# 각 배열의 원소의 최댓값을 구해서 출력하기(튜플, 문자열, 문자열 리스트)

from max import max_of

t = (4,7,5.6,2,3.14,1)
s = 'string'       # 문자코드 중 가장 큰 
a = ['BTS', 'ABC', 'TEST'] # 사전 순으로 가장 큰 문자열

print(f'{t}의 최댓값은 {max_of(t)}입니다.')
print(f'{s}의 최댓값은 {max_of(s)}입니다.')
print(f'{a}의 최댓값은 {max_of(a)}입니다.')