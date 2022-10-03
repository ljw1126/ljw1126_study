# 7. Text Objects and Macros 

## 연습 파일
> vim textobjects.txt

## 7-1. Text Objects 

```
👨‍💻✨ 핵심 문법 2가지
{operator}{a}{object}
{operator}{i}{object}

Ex. 'word, 단어
    daw = Delete A Word  // 단어 한개 삭제
    ciw - Change Inner Word      // 공백 또는 마침표 전까지 지우고 insert mode
    ciW                          // 공백만 인식해서 공백가지만 지우고 insert mode                

Remember that the lowercase 'w' uses spaces and punctuation as word boundaries,
                                     여백과     구두점을        단어 경계로    
whereas the capital 'W' only uses spaces 
대문자 W는 여백만을 경계로 사용          

Ex. 'sentence, 문장
    das -- 커서가 있는 문장을 지우는데 whitespaces 까지 포함해서 전체 문장 삭제
    dis -- 커서가 있는 문장을 지우는데, spaces still remains (공백은 여전히 남는다)

Remember 'A' includes the delimiter or boundaries -- A는 구분자랑 경계 포함
and 'I' does not. -- i는 공백 포함 하지 않음  

Ex. 'paragraph , 절       // ap, ip 
    dap -- 커서 위치한 절 삭제,  마찬가지로 공백까지 삭제 
    dip -- 커서 위치한 절 삭제, blank line은 삭제 x 

Ex. bracket
    ci] = ci[    -- bracket 안에 있는 내용만 삭제 됨 👇 ..

    colors = ['red', 'green', 'blue']
    colors = []     --- 👍

    if you want to delete everything within the brackets, including the brackets themselves

    da[ = da]       // bracket 포함하여 삭제 하고 싶은 경우 

Ex.
    a( = a)   🤔 밑에 꺼 설명하기 위한 용도일까 싶음
    i( = i)   🤔 밑에 꺼 설명하기 위한 용도일까 싶음 
    
    ci( = ci) -- 마찬가지로 괄호 안에 내용만 지우고 insert mode
    ca( = ca) -- 마찬가지로 괄호 포함하여 지우고 insert mode

Ex. tag 관련
    a< = a>
    i< = i>

    (xml 태그, html 태그 유용하다함🤔) 
    at  -- tag 포함한 전체 지우고 insert mode 👇
    it  -- tag 쌍 안에 내용만 지우고 insert mode👇

    cit -- 태그쌍 안에 내용만 지움 
    cat -- 태그쌍 포함하여 내용 지움  

    dit -- 태그쌍 안에 내용 삭제인데 삭제 후 insert mode는 안되네
    dat -- 내용뿐만 아니라 태그쌍 까지 삭제 (insert mode 전화❌)

    사용전 -- <p>This is <strong>so, so, very</strong> cool!</p>
    dit(또는 cit)사용 후 -- <p></p>

Ex. curl braces, 중괄호
    a{ = a}
    i{ = i}

    ci{ = ci} -- {}안 내용만 지우고 insert mode 전환
    di{ = di} -- {}안 내용만 지우고 normal mode 유지 

    ca{ = ca} -- {}까지 포함하여 지우고 insert mode 전환 
    da{ = da} -- {}까지 포함하여 지우고 normal mode 유지 

     scores = {
        'boars': 6,
        'saints': 7,
        'bandits': 0
    }

    scores = {
      
    }

    scores =

Ex. qutoe ( " , ' )
    👨‍💻 double qutoe
    a" 
    i"

    ci"      -- "" 쌍따옴표 안에 내용만 삭제 후 insert mode 
    ca"

    di"      -- "" 쌍따옴표 안에 내용만 삭제 후 normal mode 유지
    da"

    BACKUP_SERVER="deepfreeze01"
    BACKUP_SERVER=""
    BACKUP_SERVER=

    👨‍💻 single qutoe
    a'
    i'

    ci'     -- '' 작은 따옴표 안에 내용만 삭제 후 insert mode 
    ca'

    di'     -- '' 작은 따옴표 안에 내용만 삭제 후 normal mode 유지
    da'

    MAIL_SERVER='mail13'
    MAIL_SERVER=''
    MAIL_SERVER=

Ex. back tick (`, 1번 옆에 있는거)
    a`
    i`
    
    ci`
    ca`

    di`
    da`

    HOST=`uname -n`
    HOST=``
    HOST=

```


---


# 7-2. Macros (매크로)

## 연습 파일 
> vim macros.txt 

## Macro Best Practices 
- Normalize the cursor position 
  - 0 
- Perform edits and operations.
- Position your cursor to enable easy replays
  - j

> 연습하기 쉽게 맨 앞(0)에서 시작하는 매크로 등록 후 한줄 내려(j)서 반복하는게 좋다는 뜻인듯

> 🤔특정 키워드로 명령어 기록(레지스터에) 시작하고, 기록 끝나면 단축키로 동작 실행 가능한 듯

```
## flow

    qa                     // normal 모드에서 입력          
    -- recording @a        -- a register에 매크로 기록 시작 
    (작업 후)
    q        -- 기록 종료 

※ .vimrc 파일에 매크로 기록하여 사용하는 방식도 있음    

## ex1. 첫 줄 맨앞에 'NOTE:' 삽입하는 매크로 
    qa 
    0 // Note : 입력 
    q // 종료 
    :reg a      // 확인 
        👉 c  "a   0iNoto : ^[<80><fd>a
    
    j // 밑에 라인 이동후 
    @a // a register에 등록된 매크로 실행 ✨
        
        Note : To record a macro, use the q command followed by a regsiter. To stop, type q.
        Note : There are no special macro registers. There is only one a register, for example.

    j    // 한 단계 더 밑으로 나간 후 
    @@   // (shift + 2) 두 번 누르면 반복 실행됨✨

## ex2.
    qb       // b register에 매크로 등록 시작 

    0    // TIP : 입력 후 j 눌러서 한 줄 내림 
    q    // 종료 
    :reg b 
        👉 c  "b   0iTIP: <80>kd^[<80><fd>a
    
    @b   // b register 등록된 매크로 실행 
    @@   // b매크로 재실행 

        TIP: Think big.
        TIP: Feed and strengthen your mind.
    
    ✨ 5줄에 동일하게 매크로 적용하고 싶으면 
    5@b //👍 

        TIP: Think big.
        TIP: Feed and strengthen your mind.
        TIP: Better to have written a lousy ballet than to have composed no ballet at all.
        TIP: If you don’t prioritize your life, someone else will.
        TIP: Without great solitude no serious work is possible.

## ex3.
    qc    // c register 등록 시작
    
    0     // 맨 앞 으로 이동 후 
    2dW   // 공백포함하여 word 2개 지움
    fL    // LAST NAME 으로 커서 이동 
    .     // 2dW 반복 실행 
    
    q     // 매크로 등록 종료

        전)FIRST NAME: Joseph LAST NAME: Andrews
        후)Joseph Andrews

    🤔 설명이 빠른데, register에 이전 처럼 문자 append 할 수도 있고 레스트러 등록 할 수 있다는 듯 (독립적이라는 뜻일까?)

    (밑에줄 이동 후j)
    @c  // c register에 등록된 매크로 실행
    @@  // c 매크로 반복
    5@c // 5줄 연달아 수행

## ex4. 아래 예시와 같이 d 매크로 등록/사용
    BEFORE: "Montgomery", "(Alabama)" => "usa"    -- 변경 전
    AFTER:  'Montgomery', 'Alabama', 'USA'  -- 변경 후

    qd    // d register 등록 시작

    0     // 줄 맨 앞 이동, 🤔 굳이 할 필요는 없었기에 
    :s/"/'/g    // double quote -> single quote replace , g를 넣어야 한 줄에 있는 내용 다 수정 
    enter
    :s/(//g     // ( 제거
    enter
    :s/)//g     // ) 제거
    enter
    :s/ =>/,/g    // => 를 , 로 replace
    enter
    :s/usa/USA/g  // usa -> USA replace 
    enter
    j             // 아래 줄 이동 후
    q            // 매크로 등록 종료 

    :reg d        // 매크로 확인
       👉 c  "d   0:s/"/'/g^M:s/(//g^M:s/)//g^M:s/ =>/ <80>kb,/g^M:s/usa/USA/g^Mj

    ctrl + g     // 맨 밑에 라인 정보 나타남 (총 50줄 중 몇 % 위치에 있는지 나타냄)   
    :set nu      // 라인 넘버 왼쪽 출력 

    :27,35normal @d    // command mode로 범위 지정해서 사용할 수 있구나✨✨

    (결과)
    'Montgomery', 'Alabama', 'USA'
    'Juneau', 'Alaska', 'USA'
    'Phoenix', 'Arizona', 'USA'
    'Little', 'Arkansas', 'USA'
    'Sacramento', 'California', 'USA'
    'Denver', 'Colorado', 'USA'
    'Hartford', 'Connecticut', 'USA'
    'Dover', 'Delaware', 'USA'
    'Tallahassee', 'Florida', 'USA'
    'Atlanta', 'Georgia', 'USA'

## ex5. 
    (전)amazon.com has address:
        54.239.17.7
    (후)54.239.17.7 amazon.com      // 아래 줄 blank line 제거까지 

    qe      // e register 매크로 등록 시작 
    
    0       // 라인 맨 앞으로 이동
    j       // 54.239.17.7있는 라인 이동 후
    D       // 삭제 
    k       // 위로 이동
    P       // 붙여 넣기 
    a       // insert mode 진입 후 space ㅜ가
    /h      // has 로 이동 
    enter 
    D       // has부터 뒤에 다 삭제 
    j       
    dd      // blank 라인삭제
    q       // 종료

    ✨(number)@e       // number 라인 만큼 e 매크로 수행
    ✨:(from),(to)normal @e      // 적용

    (결과)
    54.239.17.7 amazon.com
    216.58.192.78 google.com
    208.80.154.224 wikipedia.org

## vim 편집기에 매크로 명령어 수정/사용 할 수 도 있다
    "ap      // a레지스터 내용 p(paste) 붙여넣기 
    (편집 후)
    "ay$     // a 레지스터에 해당 line yank 하라 $ (끝)까지    
    
    j       // 다음 라인 이동 후
    @a      // 수정된 a 레지스터 매크로 실행
```

> .,$    // range 표현시 사용

## Saving Macros 
- viminfo file 
  - .viminfo 
  - _viminfo 

- Stores history and non-empty registers.
- Read when vim starts.
- Can easily overwrite registers.

- vimrc file 
  - .vimrc
  - _vimrc

```
# .vimrc 같은 파일에 매크로 등록시 

    let @d = '(👨‍💻"dp, d레지스터에 등록된 매크로 명령어를 작은 따옴표 사이에 붙여넣기함)'
    let @t = 'ITODO: ^[j'        // I : Insert mode, j는 아래로 이동(?)
```

## Summary 
- Macros are a recorded series of keystrokes.
- Macros use registers.

// 🤔주요 명령어 요약 
- Start recording : q{REGISTER}     // qa, qb
- Append to a macro : q{CAPITAL_REGISTER}
- Playback : @{REGISTER}
- Repeat last played macro : @@ 

## Practice

> vim macros-practice.txt 

```
# 1. Convert Old Python Code to New Python Code
from. 
    print "Macros are very fun!"
to.  
    print("Macros are very fun!")

    qa        // "a register 등록 시자가 
    0         // 맨 앞으로 이동  
    f<SPACE>  // 맨 처음 space 위치로 이동 
    r(        // parenthesis (괄호)
    <ESCAPE>  // esc
    A)  
    <ESCAPE>
    j         // 아래 행으로 이동
    q         // 매크로 기록 종료

    :6,8normal @a      // normal mode로 a 매크로 실행, 적용 : 6~8 라인
    
    👨‍💻 결과
    print("Macros are very fun!")
    print("Macros are powerful.")
    print("Macros use registers to store keystrokes.")
    print("I'm so glad Vim has Macros!")

    :reg a 
    예시)"a   0f r(A)^[j
    나)"a   0f r(A)^[<80><fd>aj  //🤔 왜 다르지 .. 

# 2. Create a Shell Script From a List     
from. 
    jason
    sophia
    jack
    emma
    ava
to. 
    passwd -l jason && echo jason >> locked_users.txt
    passwd -l sophia && echo sophia >> locked_users.txt
    passwd -l jack && echo jack >> locked_users.txt
    passwd -l emma && echo emma >> locked_users.txt
    passwd -l ava && echo ava >> locked_users.txt

    ✨ I : 라인 맨 앞 insert mode, A : 라인 맨 뒤 insert mode 
    qb        // b register에 등록 시작 
    yaw       // default register에 username Yank
    I         // insert mode 들어간 후 "passwd -l "내용 입력 <ESCAPE>
    A         // A and type " && echo ".  Press <ESCAPE> to return to normal mode.
    p         //  paste the contents of the unnamed register after the cursor position with p.
    A         // " >> locked_users.txt" followed by <ESCAPE>.
    j 
    q         

    ✨ 여러줄 적용 
    @@
    4@b

    :reg b 
    (예시)"b   yawIpasswd -l ^[A && echo ^[pa >> locked_users.txt^[j
    (나)"b   yawIpasswd -l ^[<80><fd>aA && echo ^[<80><fd>apA >> locked_users.txt^[<80><fd>aj

# 3. Normalize Phone Numbers
from.
    2798265253
    6189034410
    1484945696
    4317906896
    2799430841
    1047478277
to.
    (279) 826-5253
    (618) 903-4410
    (148) 494-5696
    (431) 790-6896
    (279) 943-0841
    (104) 747-8277

    qp      // p register에 등록 
    I       // Start insert mode at the beginning of the line with I.
    ( 입력  // Type an opening parenthesis ( and press <ESCAPE> to return to normal mode.       
    3l      // 오른쪽으로 커서 3번 이동
    a       // insert mode, 공백이 들어가게 되니 a 넣어야 커서 위치 원하는 곳에 할당됨 
    )<SPACE> 입력 후 <ESCAPE> (== esc)
    3l       
    a                
    - 입력 후 <ESCAPE>
    j
    q       // 매크로 종료

    👨‍💻 나는 커서 이동할때 3l과 insert mode 돌입시 i 사용함(a 사용시 커서 한칸식 밀려서)
    ✨ p 매크로 적용 , 25~73 라인 까지
    :25,73normal @p 

    :reg q 
    (예시) "p   I(^[llla) ^[llla-^[j
    (나) "p   I(^[<80><fd>a3la) ^[<80><fd>a3la-^[<80><fd>aj

# 4. Extract Important Data from a Log File
from. 
    Jan 13 09:57:01 www1 kernel: [3947771.808744] [BLOCK] IN=eth0 OUT= MAC=e6:e9:2d:04:b6:95:3c:8a:b0:0d:6f:f0:08:00 SRC=190.18.193.152 DST=2.5.9.1 LEN=40 TOS=0x02 PREC=0x00 TTL=51 ID=25120 PROTO=TCP SPT=12502 DPT=23 WINDOW=4078 RES=0x00 SYN URGP=0
to.
    Jan 13 09:57:01,190.188.193.152,23    
    💡 The source IP address follows "SRC=".  Example: SRC=190.18.193.152
    💡 The destination port follows "DPT=".  Example: DPT=23

    ql       // l register에 매크로 등록 
    0 
    tw       // 라인에서 w 찾음 (t : same as "f" but cursor moves to just before found character)
    dtS      // S를 찾을 때까지 삭제 ✨search 같은건데 현재 커서 위치에서 첫 S까지 삭제 구나
    dw       // SRC 단어 삭제
    r,       // replace "=" with a comma: 'r,'
    f<SPACE> // IP 처리했으니 SPACE 공백 찾음  Position the cursor after the IP address with f<SPACE>.
    d/DPT<ENTER> //현 커서 부터 DTP 뒤에 특수문자 까지 찾아 삭제, Delete the text up to "DPT=" with d/DPT<ENTER>.
    dw          // Now delete DPT with dw.
    r,          //Next, replace "=" with a comma: r,.
    f<SPACE>    // 다음 공백 찾음 
    D           // Delete the remaining text on the line with D.
    j
    q           // 매크로 등록 종료 

    👨‍💻 나머지 매크로 적용 
    9@l

    :reg l
    (예시) "l   0twdtSdwr,f d/DPT^Mdwr,f Dj
    (나) "l   0twdtSdwr,^[<80><fd>af d/DPT^Mdwr,^[<80><fd>af Dj

    ✨TIP.✨
    As always, there are other ways to accomplish the same task.  Just one simple example is using ✨**2cw,<ESCAPE>** to change "SRC=" to "," instead of using **dwr,**.  Take a moment and think of other ways to alter this macro and get the same result.


# 5. Condense Data From Multiple Lines Into a Single Line
from. 
    Country China
    1,380,950,000 people
to. 
    1,380,950,000;China

    qc     // c register 등록 시작 
    0
    dw     // Delete the word "Country" with dw
    j      // 아래로 한번 내려감 Move to the line below with j.
    dW     // Cut the number into the unnamed register with dW. 
    k      // 위 
    P      // Paste the number before your cursor position with P.
    r;     // Replace the space with a semicolon by typing r;.
    j      // 한칸 아래로 이동
    2dd    // Delete the current line and the next line with 2dd.
    q      // Finally type q to stop recording the macro.

    👨‍💻 나머지 매크로 적용 
    4@c

    :reg c 
    (예시) "c   0dwjdWkPr;j2dd
    (나) "c   0dwjdWkPr;j2dd

    (결과)
    1,380,950,000;China
    1,310,540,000;India
    324,369,000;United States
    260,581,000;Indonesia
    206,960,000;Brazil

# 6. Extract Data from HTML // tag 사이 데이터 추출 
from.
    <a href="#">@armyspy.com</a><a href="#">@cuvox.de</a><a href="#">@dayrep.com</a><a href="#">@einrot.com</a><a href="#">@fleckens.hu</a><a href="#">@gustr.com</a><a href="#">@jourrapide.com</a><a href="#">@rhyta.com</a><a href="#">@superrito.com</a><a href="#">@teleworm.us</a>

    // 정렬 
    <a href="#">@armyspy.com</a>
    <a href="#">@cuvox.de</a>
    <a href="#">@dayrep.com</a>
    <a href="#">@einrot.com</a>
    <a href="#">@fleckens.hu</a>
    <a href="#">@gustr.com</a>
    <a href="#">@jourrapide.com</a>
    <a href="#">@rhyta.com</a>
    <a href="#">@superrito.com</a>
    <a href="#">@teleworm.us</a>

to.
    armyspy.com
    cuvox.de
    dayrep.com
    einrot.com
    fleckens.hu
    gustr.com
    jourrapide.com
    rhyta.com
    superrito.com
    teleworm.us

    qq        // q register에 등록 시작 
    df@       // Delete the text up to and including "@" with df@. ( f : find character after cursor in current line)
    f<        //Position the cursor under the "<" with f<.
    cf><ENTER><ESCAPE> // > 까지 변경하겠다는 의미인듯, Replace "</a>" with <ENTER> by typing cf><ENTER><ESCAPE>.
    q         // 종료 

    :reg q
    (예시) "q   df@f<cf>^M^[
    (나) "q   df@f<cf>^M^[<80><fd>a

    👨‍💻 나머지 매크로 적용
    @q
    @@
    
```