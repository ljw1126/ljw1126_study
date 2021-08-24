
from enum import Enum, EnumMeta 
from fixed_stack import FixedStack

Menu = Enum('Menu',['푸시','팝','피크','검색','덤프','종료'])
# print(list(Menu))   [<Menu.푸시: 1>, <Menu.팝: 2>, <Menu.피크: 3>, <Menu.검색: 4>, <Menu.덤프: 5>, <Menu.종료: 6>]

def select_menu() ->Menu:
    s = [f'({m.value}){m.name}' for m in Menu]
    while True:
        print(*s, sep = '    ', end = '') # sep : 문자 사이 연결하는 방식 ('-'같은), end : 문장 마칠때 
        n = int(input(': '))
        if 1 <= n <= len(Menu):
            return Menu(n) 

s = FixedStack(64) # 최대 64개를 푸시할 수 있는 스택 

while True : 
    print(f'현재 데이터 개수:{len(s)} / {s.capacity}')
    menu = select_menu()    # 메뉴선택 

    if menu == Menu.푸시:
        x = int(input('데이터를 입력하세요. : '))
        try : 
            s.push(x)
        except FixedStack.Full: # rasie로 예외 발생하면 pass니 아래 문장 실행됨
            print('스택이 가득 차 있습니다.')

    elif menu == Menu.팝:
        try:
            x = s.pop()
            print(f'팝한 데이터는 {x}입니다.')
        except FixedStack.Empty: # rasie로 예외 발생하면 pass니 아래 문장 실행됨  
            print('스택이 비어 있습니다.')

    elif menu == Menu.피크:
        try : 
            p = s.peek()
            print(f'피크한 데이터는 {x}입니다.')
        except FixedStack.Empty:
            print('스택이 비어 있습니다.')

    elif menu == Menu.검색: 
        x = int(input('검색할 값을 입력하세요.:'))
        if x in s : # 여기 이해 안됨 
            print(f'{s.count(x)}개 포함되고, 맨 앞의 위치는 {s.find(x)}입니다.') 
        else:
            print('검색값을 찾을 수 없습니다.') 
    elif menu == Menu.덤프:
        s.dump()
    else : # 종료 
        break 
