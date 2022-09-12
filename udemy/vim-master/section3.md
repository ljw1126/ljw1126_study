*강의 자료 압축 풀기 ! 
## 샘플 파일 열어서 학습
$ vim nav.txt

* 방향키 
       k(위)
(왼쪽)h     l(오른쪽)
       j(아래)

* page down
  "ctrl" + "f" (forward)

* page up
  "crtl" + "b" (backward) 

* To move forward by word 
  "w" 또는 "W" 

* To move backward by word 
  "b" 또는 "B" 
    
* To go to the begining of the file (맨 처음 시작부분으로 이동)
  "1gg" 또는 "gg" 

* To move to the last line of the file 
  "$G" 또는 "G"(대문자)  

* 번호 표시 
  :set number <-> :set nonumber 
* ruler 표시 
  :set ruler <-> :set noruler
  :set ruler! (on이든 off든)  

* To go to a specific line number 
  <LINE_NUMBER>gg 또는 <LINE_NUMBER>G
  ex) to go to line27, 👉 "27gg" 또는 "27G"       
  
  또는 line mode 통해서도 이동 가능 
  ":32<ENTER>"

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


$
--- 
 
#### Deleting Text and "Thinking in Vim" ( D operator )
$ vim deletinglession.txt
  일반 모드에서 "x"를 사용해서 하나씩 삭제 가능 (upper case "X" 는 뒤로 삭제)
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