*강의 자료 압축 풀기 ! 
## 샘플 파일 열어서 학습
$ vim nav.txt

* 방향키 
       k(위)
(왼쪽)h     l(오른쪽)
       j(아래)

## Motion  
#### page up/down ✨
* page down is the same as the "page down" operation.
  "ctrl" + "f" (forward)

* page up is the same as the "page up" operation
  "crtl" + "b" (backward) 

#### 단어 단위 ✨
* To move **forward** by word 
  "w" 또는 "W" 
  - word 단위 : w
  - white space as word boundaries : W     // 공백 단위로 단어 이동

> Remember that the w motion will stop at punctuation while the W motion ignores punctuation and stops the cursor after white space

* To move **backward** by word 
  "b" 또는 "B" 
  - word 단위 : b
  - white space as word boundaries : B    // 공백 단위로 단어 이동

#### 라인 번호 이동 ✨
* To go to the begining of the file (맨 처음 시작부분으로 이동)
  "1gg" 또는 "gg" 

* To move to the last line of the file 
  "$G" 또는 "G"(대문자)  

* To go to a specific line number 
  <LINE_NUMBER>gg 또는 <LINE_NUMBER>G
  ex) to go to line27, 👉 "27gg" 또는 "27G"       
  
  또는 line mode 통해서도 이동 가능 
  ":32<ENTER>"  

---

* 번호 표시 
  :set number <-> :set nonumber 
* ruler 표시 
  :set ruler <-> :set noruler
  :set ruler! (on이든 off든)  

  // ruler는 우측 하단에 상태바 뜻 함 
  참고 링크. [https://codeyarns.com/tech/2010-11-28-vim-ruler-and-default-ruler-format.html#gsc.tab=0](https://codeyarns.com/tech/2010-11-28-vim-ruler-and-default-ruler-format.html#gsc.tab=0)

  To go to the last line 마지막 라인 이동 
  ":$"     // shift + g 로도 이동 가능 ! 

* 현재 커서 위치한 행의 정보 표시 
  ctrl + g 
  ex) "nav.txt" 74 lines --37%--   👉 nav.txt 74lines로 되어 있고 현재 37% 위치에 있다. (그냥 ruler 활성화 하는게 나은 듯)  

#### vim 에서 라인 점프(줄 단위 이동)

참고 블로그 https://coldmater.tistory.com/211?category=758530

 * 문장(line)의 시작과 끝으로 이동  

$ : 문장의 끝으로 이동 
^ : 문장의 시작으로 이동 
0 : 라인의 첫번째 컬럼으로 이동할 수 있다.

* (비개발용) 마침표 기준으로 한 문장의 시작과 끝으로 이동 

( : (마침표 기준) 이전 문장의 시작 위치로 커서 이동함 
) : (마침표 기준) 다음 문장의 시작 위치로 커서 이동함

--- 
 
#### Deleting Text and "Thinking in Vim" ( D operator ✨ )
$ vim deletinglession.txt


#### 한 자리 삭제
  - 일반 모드에서 "x"를 사용해서 하나씩 삭제 가능 (오른쪽 커서 이동) 
  - upper case "X" 는 뒤로 삭제 (왼쪽으로 커서)
  ㄴ x 누를시 한자리씩 지워지는데 :reg 에 "" 영역에 한자리씩 들어가는게 확인됨

  dw (operation{motion} 형태) 
  ㄴ d = the delete "operation" 
  ㄴ w = the word "motion"

  dj : 커서 위치부터 아래 두줄 삭제

  dk : 커서 위치부터 위로 두줄 삭제

  d0 : 커서 위치부터 맨 앞까지 삭제(0이 한줄 문장 맨앞으로 이동하니)
  d^ : 커서 위치부터 맨 앞까지 삭제 
  d$ : 커서 위치부터 맨 뒤까지 삭제 

  D : 커서 위치 부터 뒤로 전체 삭제(중간 위치부터 끝까지 삭제 가능, 밑에 줄 그대로)
  dd : 한 줄 전체 삭제하면서 밑에 줄 한칸씩 올림
  
  3dd : 커서 부터 3줄 삭제하고 위로 올림 

  5dw ([count]operation{motion})
  ㄴ 5 = the count / how many times to repeat
  ㄴ dw = the command (delete word)

  ([repeat count]operation[count]{motion})
  3w = Repeat word motion 3 times.
  d3w = delete the 3w motion.
  2d3w = delete the 3w motion 2 times.

  . (dot command) -- 이것도 유용하네
  = the DOT command repeats the prviously issued command to execute the command just type of period.
    이전에 실행되었던 명령어를 반복 실행함

  ** 방향키 조합해서 할 수도 있음  
  dl 오른쪽 텍스트 삭제 
  dh 왼쪽 
  dj 아래 삭제 (커서 있는 줄 + 아래 한 줄)
  dk 윗줄 삭제

 #### 연습 문제 
 $ vim practicedeleting.txt

```bash
# 1
## 3번 라인으로 이동 : 3gg, 3G, :3
## x key를 눌러서 텍스트 수정하기 
First, fix this spelling mistakke.  
First, fix this spelling mistake.   //✨   

# 2
# 4번 라인 이동하여 마찬가지로 중복 알파벳 삭제 
Fixx theese allso.
Fix these also. //✨   

# 3. 문장 삭제 
## $ key, l key 활용하여 맨 끝으로 이동 후 X 로 삭제, 그리고 dot(.) 반복
Delete this text with the X command.


# 4. 물음표 뒤 cats 삭제
## ? 뒤로 커서 이동 후 D 또는 d$ (동일)
Who let the dogs out? cats
Who let the dogs out?   //✨   

# 5. Far, far 삭제 
## 43gg (:43 이나 43G)로 라인 이동, dw, dW 사용 
## d operator
Far far away, behind the wild mountains, far from the countries Vokalia and
far away, behind the wild mountains, far from the countries Vokalia and //✨ 

# 6. away, 삭제 
## 참고. Remember that the w motion will stop at punctuation while the W motion ignores punctuation and stops the cursor after white space. 
## dw 하면 punctuation 구두점 앞에서 먼춤, 그래서 dW 로 다음 공밲까지 삭제

away, behind the wild mountains, far from the countries Vokalia and
behind the wild mountains, far from the countries Vokalia and //✨ 

# 7. the 에서 앞에 behind 삭제
## 0 > W > db 또는 dB     // reverse motion : b 또는 B 
the wild mountains, far from the countries Vokalia and //✨ 

# 8. the whild 삭제
## 2dw      // forward motion : w
mountains, far from the countries Vokalia and //✨

# 9. mountains, far 삭제
## d2W      // forward motion : 2W
from the countries Vokalia and //✨
``` 

- 한 라인 삭제 : dd
- multiple line 삭제 : [count]dd 또는 [count]D
  - ex) 3dd  // 3줄 삭제 , dot (.) command 와 같이 활용 가능

The End
---
