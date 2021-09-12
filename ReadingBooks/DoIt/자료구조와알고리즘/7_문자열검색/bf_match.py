# 브루트 포스법으로 문자열 검색하기 
# 텍스트 txt 안에 패턴 pat가 여러 번 포함된 경우에는 가장 앞쪽에 위치한 인덱스를 반환함
def bf_match(txt:str , pat:str) -> int:
    pt = 0  # txt를 따라가는 커서
    pp = 0  # pat를 따라가는 커서 

    
    # pp != len(pat) 검색 첫 성공시 바로 조건 걸림 
    # pt != len(txt) 는 끝까지 검색한 경우 (끝에 있는 경우나 없는 경우)
    while pt != len(txt) and pp != len(pat):  # len(txt) = 11 , len(pat) = 3
        if txt[pt] == pat[pp]:
            pt += 1 
            pp += 1 
        else : 
            pt = pt - pp + 1 
            pp = 0 
    
    # pt 5 , pp 3일때 while문 종료 break 

    return pt - pp if pp == len(pat) else -1 

if __name__ == '__main__':
    s1 = input('텍스트를 입력하세요. : ') # 텍스트용 문자열      예) ABABCDEFGHA
    s2 = input('패턴을 입력하세요. : ') # 패턴용 문자열 

    idx = bf_match(s1,s2)

    if idx == -1 : 
        print('텍스트 안에 패턴이 존재하지 않습니다. ')
    else : 
        print(f'{(idx+1)}번째 문자가 일치합니다.')