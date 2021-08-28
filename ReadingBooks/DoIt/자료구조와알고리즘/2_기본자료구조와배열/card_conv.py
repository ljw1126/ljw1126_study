# 10 진수 정수값을 입력받아 2~36 진수로 변환하여 출력하기 

""" 정숫값 x를 r진수로 변환한 뒤 그 수를 나타내는 문자열을 반환 """
def card_conv(x:int, r:int)->str:
    d = '' # 변환 후의 문자열 
    dchar = '0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ'

    while x > 0 :
        d += dchar[x%r] # 해당하는 문자를 꺼내 결합 
        x //= r 

    return d[::-1] # 59에 16진수 구해라면 d = 'B3' 이되어 있으로 역순 출력


# x,r = map(int,input().split())
# print(card_conv(x,r))

if __name__ == '__main__':
    print('10진수를 n 진수로 변환합니다. ')

    while True : 
        while True :  # 음이 아닌 정수를 입력받음 
            no = int(input('변환할 값으로 음이 아닌 정수를 입력하세요. : '))    
            if no > 0 : 
                break

        while True : # 2~36진수의 정숫값을 입력받음 
            cd = int(input('어떤 진수로 변환할까요 ? : '))
            if 2 <= cd <= 36 :
                break 

        print(f'{no}를 {cd} 진수 변환한 결과는 {card_conv(no, cd)} 입니다. ')


        retry = input('한번더 변환할까요? ( Y 예 , N 아니요 ) ')
        if retry in {'n' , 'N'}:
            break 