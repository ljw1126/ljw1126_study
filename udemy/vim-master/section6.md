# Transforming and Substituing Text 

## 연습파일
> $ vim inserting.txt 

### insert mode 연습 
```
# 1. insert mode 
- i  는 생략 

# 2. 라인 맨앞으로 이동 후 insert mode 
shift + i 

# 3. 커서가 위치한 텍스트 다음에 커서 포인트가 나타나며 insert mode 됨
a

# 4. 커서를 라인 끝으로 이동 시킨 후 insert mode 
A (shift + a)

# 5. 밑(below) 라인으로 커서  이동 후 insert mode 
o 

# 6. 윗(above) 라인으로 커서 이동 후
O

# 7. 한 줄을 asterisk (별표)로 해서 추가 
80i(insert mode) 👉 * 👉 esc(escape) 누르면 한 줄 생성됨

    *************************************************************************

# 8. 다섯 줄 시작을 #으로 해서 생성
5o (insert mode) 👉 # 👉 esc 누름 

    #
    #
    #
    #
    #

# 9. Create 4 lines that begin with "10.11.12."
4o (insert mode) 👉 10.11.12 (입력) 👉 esc 누름 

    10.11.12
    10.11.12
    10.11.12
    10.11.12

```

### replace mode 연습 

```
Replace Mode : shift + r 

10. dog -> cat 
shift + r 누르고 cat 입력하고 esc  // 🤔 전체 교체라기 보다는 insert mode 같음 

11. canine -> cat 
cw (change word) 누르고 cat 입력 후 esc // 커서가 있는 canine 지워지고 insert mode 됨

12. a register에 저장 
"acw -> cat 입력 -> esc (수정 종료)
:reg  또는 :reg a 입력 후 확인 // 명령 모드, 레지스터에 자른 워드가 존재함 

    c  "a   canine

13. 커서 뒤에 라인 전체 삭제 후 insert mode 
shift + c 또는 c$ 또는 cW

14. 커서 있는 라인 전체 Replace 
cc      // 라인 전체 지워지고 insert mode 
3cc     // 3 라인 지우고 insert mode 

15. lower case -> upper case (소문자 -> 대문자 변경)
|the     // shift + ` 입력  (` : Tilde)   
The

16. 한단어 전체 lower -> upper 
g~w     // the -> THE, 🤔 g가 무슨 의미지?

17. 한 줄 전체 lower -> upper 
g~~
g~$

18. 한단어 대문자 변환 
gUw

    upper -> UPPER 

19. 한 줄 전체 대문자 변환 
gUU // guu는 소문자 변환 😅
👨‍💻비쥬얼 모드로 한줄 선택후 shift + u 눌러도 대문자 변환됨

20. 소문자 변환 
guw  // 한단어 소문자 변환
guu  // 한줄 전체 소문자 변환

```

#### 참고 
https://gracefulprograming.tistory.com/121

### join 
```
J 가 시작 키워드

# 1. 
밑에 줄을 위로 끝어 당김 
|The cat chased       // J 누르면 
the mouse.

|The cat chased the mouse.

# 2 그런데 J했을때 space가 추가된 상태로 붙게 되는데 
3.
146

3.  146    // J만 했을 경우 
3.146      // gJ했을 경우 공백이 사라짐 

// 3. 3개 라인 붙일 때 
3J 
    Thing 1 Thing 2 Thing 3 // 공백 있는 상태로 붙임
3gJ 
    Thing 1Thing 2Thing 3  // 공백 붙임
```

--- 

## Search, Find, and Replace 1 

> $ vim searching.txt

```
# f{char}
fA      // 해당 라인 내에서 A 문자 찾음 , forward 
Fa      // 해당 라인, 커서 위치에서 뒤로 a 문자 찾음, backward
;              // 다음 문자 찾음 (해당 라인 내에서), forward 
,(comma)       // 이전 문자 찾음 (해당 라인 내에서), backward

ti             // i를 찾는데 커서가 i 전에 위치 함
                  한번 찾고 ti 다시 입력시 안 찾아짐 
                  그래서 lti 눌러서 한칸 오른쪽 이동 후 검색해야 연달아 검색됨

Ta             // 왼쪽(반대방향)으로 a를 검색  
                  마찬가지로 연속 검색(TaTa)은 안 되고
                  방향키로 움직인 후에 다음꺼 검색함

2f (space)     //현 라인에서 2번째 공백 위치 찾음                       

# 라인 내에서 단어 찾고 삭제(✨)
fT             //T로 시작하는 단어 앞으로 커서 이동 
dtw            //t로 시작하는 단어 삭제 
df (space)     //라인 내에서 다음 space 까지 삭제 

---
# 단어 찾기(command mode)
/and         // and 단어 찾음
n            // 다음 and 단어 찾음, 마지막에 한번더 n 누르면 top으로 올라가 처음 and 찿음 
N            // 이전 and 단어 찾음

* is / incsearch  -- 무슨 설정인지 전혀 모르겠음(command mode에서 사용 )
:set is?          // 무슨 search 설정하는거 같은데 .. 뭐지 ?? incsearch
:set is 
:set nois

뭔가 위에꺼 셋팅하니 incremental search option이라는데..

* hls / hlsearch  HighLight search option 
:set hls?
:set hls        // /and 검색시 highlight 처리 되서 표시됨 
:nohls          // highlight search 해제(현 상태에서만)
:set nohls      // highlight search 전체 해제 

---
# 단어 교체 
/and        // 검색 
cw          // change word 입력 후 &로 변경, esc 
n           // 다음 and 검색/이동
.           // 전에 실행한 내용 반복

# 다른 검색
?is         // 현재 커서 뒤로 is 단어 검색 (/와는 반대방향으로 검색하네) 
*           // 단어 앞에 커서 위치 후 asterisk (별표) 누르면 다음 단어 검색 (다음 n,이전 N 동일) -- 좀 더 simple함
               근데 별표가 entiry macthing 하는거만 찾아서 is 를 검색시 miss 는 검색 안됨 -- 다른 글 찾아보기  

ggfb        // command mode에서 , 맨 위로 이동 후 해당 라인 내에 b를 찾아라 (by찾음)
#           // 거꾸로 by 찾아서 커서 이동(루프)

    👨‍💻 특정 단어 앞에 커서 두고 *(forward방향) 또는 #(backward방향) 누르면 해당 단어 검색 진행

# 응용 
- 삭제 명령어와 검색 명령어를 응용 가능
d 
/THIS         // 현재 커서 위치에서 THIS 단어 앞까지 삭제 

- register 저장 명령어와 검색 명령어 응용 가능 
"ay           // a 레지스터에 복사한 내용을 넣는데    
/z            // z 앞까지 검색한 내용을  
:reg a        // a 레지스터 확인
 c  "a   Searching forward is accomplished by typing f{char}.  Also,

(원문) Searching forward is accomplished by typing f{char}.  Also, zebras eat grass.
 
```

## Search, Find, and Replace 2 

> 일괄 변경, 수정에 대한 커맨드 설명인듯 

```
형식1 >> :s/old/new/

    :s/net/org/             // only 현재 라인에 only first net에 대한 수정 발생 (하나만 수정되었네..)

형식2 >> :s/old/new/[flags]      --- g 플래그

    u       // undo 
    :s/net/org/g       // only 현재 라인의 모든 net 을 org로 변경

형식3 >> :[range]s/old/new/[flags]  --- default range : current line

    #### [range] 에 사용 가능한 예약어 
    . = current line
    $ = last line
    % = All lines (entire file, % == 1,$)

    :1s/is/isn't/g         // :set number할때 보이는 그 line number 구나
                           1번 줄에 is를 isn't으로 수정

    :1,5s/for/FOR/g        // 1~5 같지만 1번줄만 수정됨 
    
    (전체 단어 수정 예시✨👨‍💻👍)
    :%s/net/org/g          // entire file 행에 있는 모든 net 을 org로 수정 -- g를 안주면 행 당 1개씩 수정됨 

형식 4 >>  /PATTERN-1/,/PATTERN-2/ -- 패턴 사용 (패턴에 해당하는 범위에 대한 수정 진행이네)
    
    :/Global/,/Local/s/net/org/g          -- g flag를 붙여야 한 행에 하나만 아닌 한 행에 대상 전체가 수정됨👨‍💻

    (수정전)
    # Global configuration

    DOMAIN=example.net                      # The example.net domain.
    MAIL_SERVER=mail.example.net
    MAIL_PATH=/var/spool/mail

    # Local configuration

    LOCAL_DOMAIN=internal.example.net       # The internal.example.net domain.
    LOCAL_MAIL_SERVER=mail.internal.example.net
    MAIL_PATH=/var/spool/mail    

    (수정후) -- 패턴이면 LOCAL에도 변경되야 하는거 아닌가?? 🤔 Global ~ Local 문맥 안에 대해서만 수정 수행하네
    # Global configuration      ----- 여기부터 

    DOMAIN=example.org                      # The example.org domain.
    MAIL_SERVER=mail.example.org
    MAIL_PATH=/var/spool/mail

    # Local configuration   ----- 여기까지 범위

    LOCAL_DOMAIN=internal.example.net       # The internal.example.net domain.
    LOCAL_MAIL_SERVER=mail.internal.example.net
    MAIL_PATH=/var/spool/mail

    ✨응용 
    :/Local/,$s/net/org/g          --- Local matching 되는 부분 부터 $(끝)까지

    (실행결과)
    # Global configuration

    DOMAIN=example.net                      # The example.net domain.
    MAIL_SERVER=mail.example.net
    MAIL_PATH=/var/spool/mail

    # Local configuration

    LOCAL_DOMAIN=internal.example.org       # The internal.example.org domain.
    LOCAL_MAIL_SERVER=mail.internal.example.org
    MAIL_PATH=/var/spool/mail

형식 5 >>          -- 백 스페이스를 활용한 hard 한 방식 

    :s/\/var\/spool/\/usr\/local/       -- hard 하네 백스페이스 써써 

    :s#old#new#                    -- 좀더 simply 한 방식 
    :s#/var/spool#/usr/local#      -- 커서가 위치하고 있는 행만 대상으로 /var/spool -> /usr/local로 수정 (g flag 없으니 1개만) 
    :1,$s#/var/spool#/usr/local#g   -- 1~끝까지 변경 (g는 안붙이고 실행했지만 분명 될거임)

```

> :set nu  == :set number , :set nonu == :set nonumber, :set nu! (! 붙이는게 토글 동작 뜻함✨👨‍💻)

#### 참고 
https://www.cs.swarthmore.edu/oldhelp/vim/searching.html
https://vim.fandom.com/wiki/Search_patterns

#### Summary - Same Line Searching 
- f{char} : forward search 
- F{char} : reverse search 
- t{char} : forward till(까지) search 
- T{char} : Reverse till search 
- ; - Repeat in the same direction (같은 방향으로 다음 검색)
- , - Repeat in the opposite direction (반대 방향으로 이전 검색)

#### Summary - Search 
- /{pattern} : forward search 
- ?{pattern} : reverse search 
- n : Repeat search in the same direcction 
- N : Repeat search in the opposite direction 
- * : Forward search for word (단어 앞에 커서가 있어야 함) 
- \#\ : Reverse search for word (마찬가지)

#### Summary - Substitue Command 
Format 
    :[range]s/{pattern}/{string}/[flags]  -- pattern, string 이라기 보단 old 와 new
Global Substitution : 
    :%s/{pattern}/{string}/g       -- 마찬가지


## Ex6. Inserting, Chaning, Replacing, and Joining 

> $ vim insert-practice.txt

```
* hjkl 이동키 앞에 숫자를 붙여서도 이동 가능하구나 
ex. 2j 

ex. Enter your name here =><=

    f>       // 검색으로 커서 이동 후 i 또는
    f<       // 이동 후 a 로 insert mode 들어가면 한칸 뒤에 커서 생김👍

ex. Enter your name here:     // 끝라인에 넣는 문제 
    A        // 명령 모드에서 입력하면 끝라인으로 커서 이동 후 insert mode됨👍

ex. One the line below, type the name of your favorite movie.
    o        // 아래 줄에 커서 이동 후 입력모드 됨     

ex. ^^^ One the line above, type the name of the editor you are using.
    O        // 한 줄 위에 커서 이동 후 입력모드 됨

ex. This line belongs
    with the one below it.       // 한 줄로 합치기 

    J        // join 으로 한 줄로 붙일 수 있음

ex. Vim is her favorite editor.    // Replace "her" -> "our"
    Fh 또는 fh      // her로 이동하기 위해 F는 역방향, f 는 정방향
    R               // her 앞에 커서 위치하면 replace mode 진입 후 수정

ex. I have a white car.      // car -> cat 수정 
    fr
    r 누른 후 수정 

ex. I am having a great time in this vim class! // "great" => "brilliant"        
    /gr         // 커서 이동 
    cw          // change word, 수정하면 됨 

ex. I love myself.      // myself. => everyone! 
    /my         // a forward search 
    cW 또는 c$ 또는 C         // punctuation(구두점) 포함해서 수정

ex. Type something wonderful here.     // "The sky is beautiful!"
    cc             //전체 라인 (지워지고) 수정

ex. monday <= The "m" is supposed to be in uppercase. // m을 대문자로 
    커서 이동후  
    ~               // m 앞에 커서 위치하고 누르면 대문자 변환

ex. Don't shout.  It's not nice.    // shout 단어 대문자로 
    g~w 👉 Don't SHOUT.  It's not nice.     

ex. mONDAY'S START BETTER WITH coffee. // 한줄 전체 
    g~~ 👉 Monday's start better with COFFEE

ex. Don't Shout.  It's just too loud. // Shout -> SHOUT 
    gUw 또는 gUW

ex. Please Whisper. // Whisper -> whipser 
    guw 

ex. Create a line of asterisks below:  // 한줄을 별로 채워라 
    100i > * > esc[Escape]        

ex. Create 3 lines that begin with "-" below:    // 3줄을 - 로 시작하는 줄 넣기 
    3o > - > esc[Escape]
    (결과)
    -
    -
    -

//종료
```


## Ex7. Search, Find, and Replace 

> $ vim search-practice.txt

```

# linewise search (줄별 검색)
ex. The Time Traveller (for so it will be convenient to speak of him) was expounding      // f로 시작하는 단어 찾아가기 
    ff          //f{char}, 해당 find의 경우 한 라인 내 검색만 됨 
    ;           //(현 라인 내) 이전 검색 결과  
    ,           //(현 라인 내) 다음 검색 결과
    FT          //(현 라인 내) 역방향으로 T를 찾음 
    
    tb          //(현 라인 내) b 찾음, 근데 커서가 b 앞에 있으니 다음 찾으려면 한칸 움직이여야 함 (🤔맞네, 한칸 전에 있는데 tb 누르면 제자리)
    ts          //(현 라인 내) s 찾음
    TT          //역방향 으로 T를 찾음 (연달아 하려면 커서 한번 움직이고 해야함)

ex. /and<SPACE><ENTER>      -- 공백없으면 incandescent 와 같은 단어에 매치될 거다.
    n           //(repeat) 정방향으로 다음 검색 결과 찾음
    N           //(repeat) 역방향으로 이전 검색 결과 찾음

ex. Search for a word 
    2fi   //🤔 number가 무슨 의미 ? 2번째가 i 인 word를 찾는다? 
    *     //next
    n     //repeat, *를 누른 후에 n이 되는듯? (#도 마찬가지 일까?)

    "us" 찾기 
    Fu  
    #         // 음.. 안되는데 .. 
    n

ex. Perform a global substitution // "sat" 대신 "laid"
    :%s/sat/laid/g        // %가 파일 전체 영역, g가 각 줄 마다 수정(없으면 각 줄 마다 1개만 수정됨)
```