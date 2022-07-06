> vim 과 emacs (이맥스)는 편집기 양대산맥 ( 커맨드 라인 기반 시스템용 에디터 )

## vim 설정파일 
- $HOME/.vimrc 생성하면 되는 듯 

[https://stackoverflow.com/questions/42772115/bash-on-windows-10-where-is-vimrc](https://stackoverflow.com/questions/42772115/bash-on-windows-10-where-is-vimrc)
[https://www.cyberciti.biz/faq/vim-show-line-numbers-by-default-on-linux/](https://www.cyberciti.biz/faq/vim-show-line-numbers-by-default-on-linux/)

```
> vim --version | grep vimrc
     system vimrc file: "$VIM/vimrc
     user vimrc file: "$HOME/.vimrc"       // 느낌이 요 경로에 파일 생성해서 설정하면 된다는 거 같은 느낌
     2nd user vimrc file: "~/.vim/vimrc"

> vim ~/.vimrc  또는 vim $HOME/.vimrc      // 결국 동일한 경로인거 확인
    set number 
    :wq!
> source $HOME/.vimrc           // 설정 반영
> vim ~/.ssh/config             // 확인시 set number 되어 있는거 확인가능
```

👨‍💻 그외 .vimrc 설정 
```
set ignorecase     "검색 시 대소문자 무시
set background=dark       "set bg=dark 와 동일
```



## ubuntu(zsh플러그인 사용) 에 vim 설치 
```
> apt-get install vim 
> vi ~/.zshrc           
    ------------------------
    export EDITOR='vim'
    ------------------------
    // esc 후 :wq!

> source ~/.zshrc
> git config --global -e 
```


## 얄팍한 vim 단축키
단축키 입력모드 
    - **esc** 누르면 단축키 입력모드 

텍스트 입력 모드 
    - i 또는 a 입력시 텍스트 입력 가능해짐
        - 줄 맨 앞쪽 커서 이동 후 입력모드 : esc > shfit + i 
        - 줄 맨 뒤쪽 커서 이동 후 입력 모드 : esc > shift + a
    - 그외
        - I : (줄 맨앞) 입력모드 
        - A : (줄 맨뒤) 입력모드

방향키  
    - h(←, 왼쪽), j(↓, 아래), k(↑, 위), l(L, →, 오른쪽)      // 한 칸씩 커서 이동,한 칸씩 이동하는게 화살표 방향키랑 같네😅
    - w(이전), b(다음)                     // 단어 단위로 커서 이동 
    - shift + { (이전) , shift + } (다음)  // 문단 단위로 커서 이동   
    - 그외 
        - H : 화면 맨 위로 커서 이동
        - L : 화면 맨 아래로 커서 이동
        - M : 화면 가운데로 커서 이동
        - crtl + u : 반페이지 씩 위로 이동(up)
        - crtl + d : 반페이지 씩 밑으로 이동(down)
        - shfit + H :: 맨위, shift + L :: 맨 아래  // 근데 실질적으로 그렇게 맨 위/아래 가진 않음

편집 관련 
    - 단축키 입력모드(esc 누른 후) 사용
        - y : 복사 
        - p : 붙여넣기 
        - d : 선택 부분 지우기 
        - x : 선택 부분 오려두기 
        - v : 선택모드(한칸) , V : 선택모드 (줄 단위)
        - shfit + > : 들여쓰기     // 영역 선택(shift + v)한 다음 사용가능  
        - sfhit + < : 내어쓰기
        - x : 글자 한글자씩 지우기(단축키 모드 :: esc) 
        - r : 한글자 수정 
        - R : 수정 모드


#### vim adventures라는 게임 사이트로 명령어 익힐 수 있음
[https://vim-adventures.com/](https://vim-adventures.com/)


## vim 플러그인 
[https://www.youtube.com/watch?v=oLvFt-UJ7UI](https://www.youtube.com/watch?v=oLvFt-UJ7UI)

> .vimrc 첨부파일 있음

    1. 젤리빈 : 색상 테마 
       [https://github.com/nanotech/jellybeans.vim](https://github.com/nanotech/jellybeans.vim)
    
    2. tagbar : 활성화시 오른쪽 탭에 class structure가 tree처럼 나옴 
       :tagbar 입력하면 활성화 됨(다른 사람은 f12로 하는 방법이 있던거 같음)
       [https://github.com/preservim/tagbar](https://github.com/preservim/tagbar)

    
    3. nerdtree : 여러 파일 열기/닫기/찾기 시 활용 
       :nerdtree 입력시 활성화
       [https://github.com/scrooloose/nerdtree-git-plugin](https://github.com/scrooloose/nerdtree-git-plugin)
    
    4. indent-guide : 소스 코드에서 같인 indent level의 경우 라인으로 표시 (* 꼭 필요) 
       [https://github.com/nathanaelkane/vim-indent-guides](https://github.com/nathanaelkane/vim-indent-guides)

    5. vimg-gitgutter : Git으로 관리하는 프로젝트에서 수정/추가되는 라인있으면 라인 번호 옆에 아이콘 표시 해줌
       [https://github.com/airblade/vim-gitgutter](https://github.com/airblade/vim-gitgutter) 

    6. fugitive : git blame, git diff 를 vim에서도 사용하도록 해줌 
       git blame >> Gblame 으로 입력하면 동작함
       git diff  >> Gdiff 
       [https://github.com/tpope/vim-fugitive](https://github.com/tpope/vim-fugitive)


    7. vim-airline : 위/아래 상태바에 정보 표기해줌 (vim 화면이 풍성해지는 효과)
        :bp (앞 파일), :bn(뒤 파일) 명령어 입력하면 상단 airline 이력에 있는 파일 이동함
        ㅁ 단축키 설정 가능함.
        [https://github.com/vim-airline/vim-airline](https://github.com/vim-airline/vim-airline)

    8. diminactive(빔인엑티브)
        화면 분할 사용시 커서가 있는 화면에 커서 highlight 줄 표시해 줌 
        [https://github.com/blueyed/vim-diminactive](https://github.com/blueyed/vim-diminactive)

> 가장 기본적이고 평가가 좋은 플러그인을 사용해보기 -> 셋팅 그만 두기 -> 개발자체에 집중 -> 개발 환경에 아쉬운 생각 들면 관련 플러그인 찾아보기



