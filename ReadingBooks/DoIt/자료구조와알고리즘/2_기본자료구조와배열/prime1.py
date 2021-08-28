# 1,000 이하의 소수를 나열하기 

counter = 0 # 나눗셈 횟수를 카운트

# 2부터 1000까지 반복문을 돔 
for n in range(2,1001):
    for i in range(2,n): # n 이하 까지 돔 
        counter += 1 
        if n % i == 0 :  # 나누어 떨어지면 소수가 아니므로 break
            break        
    else :            # 끝까지 나누어 떨어지지 않으면 다음을 수행 , print 되는게 소수 
        print(n)

print(f'나눗셈을 실행한 횟수 : {counter}')
