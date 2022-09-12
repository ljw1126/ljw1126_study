# The Vim Help System

## 학습 파일 
$ vim help.txt 

(파일 연 상태에서 command 모드로 확인 가능) :help
:help dd    // dd에 대한 help
:help i_    // insert mode에 대한 help, 그외 나머지는 help 참고 
:help count

Jump back:  Type CTRL-O.  Repeat to go further back.
😅ctrl + o 가 뭘 뜻하는지 모르겠다 ;; 이전(?) 파일을 왜 여는거지 ;; 👉 help 여러개 켰을 경우 이전 페이지로 돌아가는 명령어 인듯
   ctrl + i 는 반대로 최근에 열었던 help 페이지 있을 경우 이동하도록 함
   ctrl - ] ??? 
$

:help linewise  // 라인 전체에 영향을 끼친다는 의미
:help :q
:help :help
:h count

ctrl + w (두번) 👉 help 관련 메뉴얼 <-> 이전 :help 호출했던 창 사이 커서 이동가능

:h :q(그리고 q바로 뒤에 커서 두고 ctrl + d를 누름, 아래와 같이 q와 관련된 alias 목록 보여줌)
    :q                :qall             :quita            :quitall
    :qa               :quit             :quote            w:quickfix_title
👉 한자씩 연달아서 입력하면서 계속 키워드 search 가능
👉 그리고 Tab키 누르면 선택가능한 명령어 목록 출력되고 Tab 키 여러번 눌러서 선택가능 
👉 그리고 Enter 

:h 'wildmenu'        //설명서 확인
:set nowildmenu      //👨‍💻 :h wild 입력후 tab 누르면 이제 목록 안뜨고 단어 자동완성 되면서 하나씩 보여줌 (tab 누를 시 목록 뜨던거 사라짐;)
:set wildmenu        //다시 원복.

:h Ctrl-f           
:h ^f# Note, using a caret symbol is the same as "Ctrl".     // ctrl-f 에 대해 검색함
    # So, ^f and Ctrl-f are the same.
:h ^b      // ctrl + b 에 대해 검색  
:h ^g      // ctrl + g 에 대해 검색

--- 

:h i 
:h wq      // 의미 : Write the current file and quit.
:h :q 
:h ^g      // 아래 내용 참고. ctrl-G는 :f, :fi, :file 과 동일하다네 

    👨‍💻CTRL-G          or                 CTRL-G :f :fi :file
    :f[ile]                 Prints the current file name (as typed, unless ":cd"
                            was used), the cursor position (unless the 'ruler'
                            option is set), and the file status (readonly,
                            modified, read errors, new file).  See the 'shortmess'
                            option about how to make this message shorter.

#### 우연히 찾은 커맨드 
화면 크기 조절 ctrl + "+" 또는 "-"    
[ : 맨처음 이동 -- 음.. 이런 용도가 아닌 듯 ? 🤔
] : 맨 마지막 이동
:h 입력 후 ctrl + f 누르면 뭔가 명령어 입력 이력 뜨고 선택가능해짐