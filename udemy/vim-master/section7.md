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

## Macro Base Practices 
- Normalize the cursor position 
  - 0 

- Perform edits and operations.

- Position your cursor to enable easy replays
  - j

> 🤔특정 키워드로 명령어 기록(레지스터에) 시작하고, 기록 끝나면 단축키로 동작 실행 가능한 듯

```



```

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