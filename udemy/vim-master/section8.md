# 8. Visual Mode

## 연습 파일 
> vim visual.txt

> 멀티 커서와 같은 형태로 텍스트 일괄 편집 가능 해서 익히는게 좋다

## Visual Mode - Part 1
🤔 visual block mode가 붙여넣기(ctrl+v)와 겹치는 문제 => wsl 설정에 setting.json에 복사 붙여넣기 단축키 수정하면 됨
    https://stackoverflow.com/questions/61824177/visual-block-mode-not-working-in-vim-with-c-v-on-wslwindows-10

🤔 highlight 시 글자가 안 보일 경우

   VIM 테마 변경 https://mong9data.tistory.com/150
   VIM 테마 관련 사이트 https://www.slant.co/topics/480/~best-vim-color-schemes#4 

   VIM에서 실수로 ctrl + z를 눌렀을 때, 복귀하는 법 => https://bitlog.tistory.com/178

   Vim A to Z => https://dev.to/prodopsio/vim-a-to-z-literally-1iah

   Visaul Mode 기술 블로그 => https://coldmater.tistory.com/216 

   window WSL 설치 https://mong9data.tistory.com/113

   vim 관련 명령어 http://skkuassa.tistory.com/126
   https://happycode.tistory.com/303 

```
v -- 단어 하나 단위 characterwise visual mode
V -- 라인 단위 linewise visual mode 
ctrl + v -- visual block mode (멀티 커서 같음✨)

    응용 
    v   -- 현재 cursor 위치에서 
        /C  -- 대문자 C까지 선택(highlight)됨
        2w  -- 단어 2개 선택 
        2l  -- 오른쪽 2번 이동 선택 
        2h  -- 왼쪽 2번 이동 선택
    D   -- 삭제 

    v
    2w -- 2단어까지 highlight
        o -- highlight 상태에서 맨 앞으로 커서 이동
        O -- highlight 상태에서 맨 뒤로 커서 이동
        b -- 커서 위치에서 뒤로 단어 하나씩 highlight 범위 확장
        w -- 커서 위치에서 앞으로 단어 하나씩 highlight 범위 확장
        n -- 문단 앞으로 단위로 highlight 범위 확장
        N -- 문단 뒤로 단위로 highlight 범위 확장

    // highlight 범위 선택 후 y (yank) 누르면 복사되고 커서 맨앞으로 이동해서 normal mode로 변환

    
    vap    -- visual mode, all, paragraph , 한 구절 선택

V - Linewise Visual Mode 라인 단위 비쥬얼 모드 
    방향키와 위k/아래j와 함께 사용가능
    
    /ex       -- ex 단어가 있는 라인까지 선택 
    n         -- ex가 있는 다음 라인까지 선택
    U         -- highlight 내에 모두 대'문자 변환 
    u         -- highlight 내에 모두 소'문자 변환

    gve       -- 🤔 뭔지 모르겠다, highlight 선택이 되기는 하는데 .. 
    z         -- .. ? z + enter 누르니 라인 맨 앞으로 이동 하긴 하네 .. 

🤔 뭔가 멑티 커서 처럼 블록 선택가능하네 

    비쥬얼 모드에서 아래 커맨드가 먹힌다는 뜻 (✨ 블록으로 선택하고 아래 편집하고 esc 누르면 멀티 커서처럼 적용되는 듯)
    ~ - Switch case (대문자 소문자 변환)
    c - Change
    d - Delete
    y - Yank
    r - Replace
    x - Delete
    I - Insert
    A - Append
    J - Join
    u - Make lowercase
    U - Make uppercase
    > - Shift right
    < - Shift left

블록모드가 윈도우 ctrl + v 라 겹친다... 

    블록모드
    3j

ex. 
    before)
    class Person
    def initialize(nick_name, birthdate, telephone, title)
        nick_name=nick_name
        birthdate=birthdate
        telephone=telephone
        title=title
    end
    end

    after)
    class Person
    def initialize(nick_name, birthdate, telephone, title)
        @nick_name=nick_name
        @birthdate=birthdate
        @telephone=telephone
        @title=title
    end
    end

    ctrl + v  -- block visual mode 
    3j
    I     -- lower case i doenst work in visual mode 
    @ 입력 후 esc 하면 선택 영역에 @ 앞에 붙음 (조금 버벅 되면서 적용됨 😅)

ex.
    before)
    john.nick_name = "Happy"
    john.birthdate = "4/4/1984"
    john.telephone = "(555) 612-2000"
    john.TITLe = "Developer"

    after)
    billy.nick_name = "Happy"
    billy.birthdate = "4/4/1984"
    billy.telephone = "(555) 612-2000"
    billy.TITLe = "Developer"

    ctrl + v      -- 블록모드 돌입 
    fn            -- john 선택되도록
    3j      
    c      -- cut and insert mode , (이건 또 소문자 동작하네, 😅)
    billy 입력 후 esc 

// 1. 비주얼 모드로 선택해서 복붙하는거.. 예제로 있으면 좋겠다..
// 2. 다른 파일 열어서 해당 파일 내용 복사 붙여 넣기 하는 것도 좋겠다..
```


## Visual Mode - Part 2

들여쓰기(인덴트 관련)
https://doubly12f.tistory.com/167
https://stackoverflow.com/questions/235839/indent-multiple-lines-quickly-in-vi

```
    gv -- visual mode로 블록 선택 후
    >  -- right로 이동 (indent)
    <  -- left로 이동 

    👨‍💻설정 확인
    :set shiftwidth?     -- ? :: shiftwidth 설정값 확인(8)   // ? : question mark 
    :set tabstop?        -- ? :: tabstop 설정값 확인 (8), 탭 사용시 character 8칸씩 
    :set expandtab?

    :set list     -- ^I : 인덴트 의미하는 듯 , $ : 라인의 끝을 의미하는 듯
        gv -- 영역 선택 후 
        >  -- 들여쓰기 수행하면 맨앞에 ^I 인덴트 추가된거 확인 가능
    :set list!    -- list mode off , 인덴트가 설정만큼 넓어 지는듯 

    :set shiftwidth=4 
    :set expandtab 

START: ~~~ List 에서 
    before) 
    START:United States of America State Capital List
    United States of America,Alabama,Montgomery
    United States of America,Juneau,Alaska
    United States of America,Phoenix,Arizona
    United States of America,Little,Arkansas
    United States of America,Sacramento,California
    United States of America,Denver,Colorado
    United States of America,Hartford,Connecticut
    United States of America,Dover,Delaware
    United States of America,Tallahassee,Florida
    United States of America,Atlanta,Georgia
    END:United States of America State Capital List

    after)
    START:United States of America State Capital List
    USA,Alabama,Montgomery
    USA,Juneau,Alaska
    USA,Phoenix,Arizona
    USA,Little,Arkansas
    USA,Sacramento,California
    USA,Denver,Colorado
    USA,Hartford,Connecticut
    USA,Dover,Delaware
    USA,Tallahassee,Florida
    USA,Atlanta,Georgia
    end:united States of America State Capital List

    gv -- 영역 선택 후 : 입력하면 replace하거나 정렬 가능한듯?
    ctrl + v
    /G         === G가 있는 단어 찾음
    :'<,'>s/United States of America/USA  -- 문장을 USA로 치환 
                                          -- '<, '> 의 경우 visual mode 로 선택 영역 처음과 끝을 뜻함
                                          -- ✨ visual mode로 영역 선택 후 command mode 들어가면 :'<, '> 입력되어 있음 !! 😄

Episode V:        // 정렬 관련된 예제
    V             -- visual mode 시작
    gv            -- 두줄 highlight선택 후 
    :'<,'>center  -- 가운데 정렬됨
    :'<,'>ce      -- 동일한 가운데 정렬 
    :'<,'>center 40 -- 40만큼 column 잡아서 가운데 정렬?? 
    :'<,'>left -- 왼쪽 정렬 
    :'<,'>le5  
    :'<,'>right -- 오른쪽 정렬
    :'<,'>ri60

    그리고 마지막에 visual block 모드로 두줄 맨앞에 # 삽입
```

```
Summary

- characterwise : v
- Linewise : V
- Blockwise : ctrl + v 

- Expanding the highlighted area:
    - motions 
    - text objects 
    - o O       // visual mode 내에서 커서 앞 뒤로 이동하는 키

- visual mode 로 영역 선택하고 아래 명령어를 사용하여 편집 가능 
    ~ - Switch case (대문자 소문자 변환)
    c - Change
    d - Delete
    y - Yank
    r - Replace
    x - Delete
    I - Insert       // 소문자 I는 동작 안함😅
    A - Append
    J - Join
    u - Make lowercase
    U - Make uppercase
    > - Shift right
    < - Shift 
    
- Visual ranges 
    - substitue 
    - center
    - right
    - left
```

#### Exercisie. visual mode


```
// 연습 파일
$ vim visual-practice.txt



```
