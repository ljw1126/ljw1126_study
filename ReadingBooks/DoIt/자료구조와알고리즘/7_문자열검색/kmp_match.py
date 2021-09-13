# KMP 법으로 문자열 검색하기 ------------ 210813 이해 안되서 넘어감 
# skip 표 만들때 변수명 헷갈려서 i,j로 변경 
# ababacabacaabacaaba
# abacaaba
def kmp_match(txt:str, pat: str) -> int:
    i = 1  # txt(텍스트)를 따라가는 커서 
    j = 0  # pat(패턴)를 따라가는 커서
    skip = [0] * ( len(pat) + 1 )
   
    # 건너뛰기 표 만들기 
    skip[i] = 0 
    print(f'1. skip = {skip}')
    while i != len(pat):
        if pat[i] == pat[j]:
            i += 1 
            j += 1 
            skip[i] = j 
        elif j == 0 :
            i += 1 
            skip[i] = j
        else:
            j = skip[j]
    print(f'2. skip = {skip}')
    # 문자열 검색하기 
    pt = pp = 0 
    while pt != len(txt) and pp != len(pat):
        if txt[pt] == pat[pp]:
            pt += 1 
            pp += 1 
        elif pp == 0 : 
            pt += 1 
        else :
            pp = skip[pp]
    print(f' pt= {pt} , pp = {pp}')
    # 검색 실패시 -1 반환 , 검색 성공시 인덱스값(pt-pp) 반환
    return pt - pp if pp == len(pat) else -1

if __name__ == '__main__':
    s1 = input('텍스트를 입력하세요. : ')
    s2 = input('패턴을 입력하세요 . : ')

    idx = kmp_match(s1,s2)

    if idx == -1 : 
        print('텍스트 안에 패턴이 존재하지 않음')
    else : 
        print(f'{(idx+1)}번째 문자가 일치함')

