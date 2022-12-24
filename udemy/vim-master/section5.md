#### 참고 
[https://www.baeldung.com/linux/vim-registers](https://www.baeldung.com/linux/vim-registers 'Vim Register : baeldung')

---
# Deleting, Yanking, and Putting 
- Move text around a file.
- Duplicate text.
- Make an in-file backup.
- Reuse* the same text.

## You're already been cutting. ✨
- 'd' and 'x' cut text, not just delete it.
- cut = delete and save into a register 
- Register is a clipboard-like storage 

## Standard vs Vim ✨
cut = 'd'elete 
copy = 'y'ank 
paste = 'p'ut  // p 는 blow(아래), P는 above(위) 라인에 붙여넣기 됨

## 연습 
> $ vim cutcopypaste.txt

dd
shift + p  // 붙여 넣기, p 눌러도 됨 
yw   // copy word 
shift + p 
2yw  // copy 2word 
y$   // 한줄 전체 복사 
yy   // 이것도 한줄 전체 복사 
4yy  // 커서 기준 4줄 전체 복사 
p 

2dd 
u    // undo, 취소하기 🤔😅(여태까지 잘못 쓰고 있었네)
ctrl + r    // redo, 다시하기

## Register Types 
- Unnamed  // 이해 못함 😅🤔 >> default register 인듯 !
  - = ""
- Numbered
  - = "0 ,"1, .., "9
- Named 
  - There are 26 named registers from A to Z
  - "a, .., "z

- "" holds text from d,c,s,x and y operations. // operation 사용시 default로 Unnamed Register("")에 등록됨
- "0 holds last text yanked (y).
- "1 holds last text deleted (d) or changed (c). 
- Numbered registerrs shift with each d or c.

> 질의 응답 참고 하거나 다른 기술 블로그 참고하는 걸로
yy       // normal mode 
:reg     // command mode에서 입력 
    Type Name Content
    l  ""   TODO^J         // ""p normal mode에서 입력하면 이게 붙여넣기됨
    l  "0   TODO^J
    l  "1   ^JMany discourses concerning realism exist. Therefore, Sartre's model of^J

""p       // 붙여넣기 됨 , 아 Unnamed Register에 있는 내용을 붙여 넣기 하라는 뜻!!

dd        
:reg      // 삭제한 줄이 있는데??

👨‍💻named register에 저장
"ayy       // "a register에 저장 
"byy       // "b register에 저장
:reg 
    l  "a   Put this line in the a register.^J
    l  "b   Put this one in the b register.^J

😳register에 등록된 텍스트에 append 하는 것도 가능함 !  
" shift+a yy    // a register 기존 문장있으면 그 뒤에 추가 
:reg 
    l  "a   Append this line to the a register.^JAppend this line to the a register.^J    

 " shift+b yy     // b register 기존 문장있으면 그 뒤에 추가
 :reg 
     l  "b   Put this one in the b register.^JAppend this line to the b register.^J

👨‍💻 z regiter에 한 단어 삭제 넣을 경우 
"zdw     // dw한건 "z register에 넣어라 
:reg 1z  // "1 과 "z register 내용만 표출함 

## Repeating with registers 
> [count][register]operator 또는 [register][count]operator 

ex.
"hyy    // "h regiter에 yy 한 걸 저장 
"h2p    // "h regiter에 있는 내용을 2번 paste 하라 

## Summary 
> cut - copy - paste = delete - yank - put.

- Registers are storage locations.
- "" contains last operated on text. 
- Numbered registers : "0 .. "9.
- Named registers : "a .. "z (26개)
- :reg  [register(s)]    // 레지스터 확인 [옵션]에는 조회 레지스터명 지정
- undo = u, redo = Ctrl - R  // 유용한 명령어 

--- 

## 연습 
$ vim dyp.txt      // 예제 파일, Exercise05 꼭 해보기 !! 


"jy
"Jy       // j 레지스터 내용에 append 
"jP       // 그리고 j 레지스터 내용 커서 위치에 붙여 넣기 (shift + p) 

"fy
"Fy
"fP

```bash


```
# 1. 삭제 후 붙여 넣기 
## 첫번째 줄 dd 후 p    // p를 할 경우 다음 줄에 입력됨 (P는 현재 커서 위치에 붙여넣기 )

# 2. 단어에 알파벳 틀린거 고치기 
## x로 삭제 후 붙여넣기 
## p 의 경우 한칸 띄우고 들어가고, P의 경우 커서 위치에 붙여넣기 됨
psell = spell
vmi = vim
wrod = word
taht = that

# 3. 단어 삭제 후 옮기기 
## dW , W(공백 포함 커서 이동, motion), P (붙여넣기)
second, First, third.
First, second, third. //✨

# 4. 생략 

# 5. 단어 중복 복사 
## r 앞에 커서 이동 후 y2W -> P (붙여넣기)
I really, really, love vim!
I really, really, really, really, love vim! //✨

# 6. Use named register  // 레지스터 활용해서 텍스트 편집하고 붙여넣고 하는듯
- "jyy     // "j 레지스터에 한 줄 복사 
- "fyy     // "f 레지스터에 한 줄 복사 
- "jp or "jP     // "j 레지스터 내용 붙여넣기 
- "fp or "fP    // 소문자 p는 바로 다음 줄에 붙여 넣고, P는 현 위치
- "Jyy     // "j 레지스터에 append
- "Pyy     // "p 레지스터에 append

# 마지막. Undo And Redo 
- Undo(취소) : u 
- Redo(다시 실행) : ctrl + r 

---


