# 1,000 이하의 소수를 나열하기(알고리즘 개선 1)

counter = 0 # 나눗셈 횟수를 카운트 
ptr = 0      # 이미 찾은 소수의 갯수 
prime = [None] * 500         # 소수를 저장하는 배열 , n이 소수인지 판단할 때 배열 prime에 저장한 소수로 나눗셈을 하면 됨 

prime[ptr] = 2          # 2는 소수이므로 초깃 값으로 지정 
ptr += 1 

# 홀수만을 대상으로 설정 ( 짝수는 어차피 2의 배수로 무조건 나눠지니 )
for n in range(3, 1001, 2):  
    for i in range(1, ptr) : # 이미 찾은 소수로 나눔 
        counter += 1
        if n % prime[i] == 0 :
            break 
    else : # 2번째 for문에서 끝까지 나누어 떨어지지 않을 때 
        prime[ptr] = n 
        ptr += 1 

for i in range(ptr):
    print(prime[i])

print(f'나눗셈을 실행한 횟수 : {counter}')
