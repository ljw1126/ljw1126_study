
## window 에 우분투 앱 설치 
https://medium.com/hgmin/windows-10%EC%97%90-ubuntu-%EC%95%B1-%EC%84%A4%EC%B9%98-cbd8d0f5f441

```
# 최초 설치시 사용자 계정/비밀번호 입력하는 건 생략 ( anderson/통계는구라다!!)
# linux 기본 명령어 동작 확인 

## 패키지 레파지토리 인덱스 업데이트 
$ sudo apt update  

## OS 버전 확인 
$ cat /etc/*release            // 220511 기준 Ubuntu 20.04.4 LTS

## netstat 설치 
$ sudo apt install net-tools

## zsh 설치 
- zsh 는 bash 와 같은 shell 프로그램 
- bash 보다 강력한 기능과 여러 플러그인 제공 
- oh my zhs 는 zsh 설정을 관리하기 위한 프레임 워크
- 참고 사이트 : https://the-illusionist.me/47
  ※ 명령어 내용중 apt와 apt-get의 차이 ? 👉동작은 동일. apt-get이 옵션이 더 많다. 

$ sudo apt install zsh  
$ zsh --version           // 버전 확인 
$ chsh -s /usr/bin/zsh    // 사용중인 로그인 쉘을 변경, 비밀번호 입력 필요
$ echo $SHELL 
  /bin/bash            // 터미널 재실행 전 
  /usr/bin/zsh         // 터미널 재실행 후  


## oh my zsh 설치

$ sh -c "$(curl -fsSL https://raw.githubusercontent.com/robbyrussell/oh-my-zsh/master/tools/install.sh)"         // curl로 설치 
$ sh -c "$(wget https://raw.githubusercontent.com/robbyrussell/oh-my-zsh/master/tools/install.sh -O -)"          // wget로 설치

※ oh my zsh 삭제 > uninstall_oh_my_zsh

### oh my zsh 테마 변경 
$ vi ~/.zshrc            // zsh 설정 파일 내용 중에 ZSH_THEME="robbyrussell" 이라고 되어 있는 곳이 테마 변경하는 부분 
  ZSH_THEME="robbyrussell" (수정 전) 👉 ZSH_THEME="agnoster" (수정 후)
$ source ~/.zshrc        // 설정 즉시 반영  
 

### Zsh 터미널 호스트네임 사용자명 제거 ( https://wordpubl.com/zsh-%ED%84%B0%EB%AF%B8%EB%84%90-%ED%98%B8%EC%8A%A4%ED%8A%B8%EB%84%A4%EC%9E%84-%EC%82%AC%EC%9A%A9%EC%9E%90-%EC%A0%9C%EA%B1%B0/ )
$ sudo vim ~/.zshrc   // 맨 아래 빈 곳에 아래의 내용 중 하나 선택해서 입력 후 저장

  /*********************************************************************/
    # 1. 호스트 네임 제거
    prompt_context() {
        if [[ "$USER" != "$DEFAULT_USER" || -n "$SSH_CLIENT" ]]; then
            prompt_segment black default "%(!.%{%F{yellow}%}.)$USER"
        fi
    }
  /*********************************************************************/
    # 2.호스트 네임 & 사용자명 제거
    prompt_context(){}
  /*********************************************************************/

$ source ~/.zshrc        // 설정 내용 반영


### 플러그인 :: zsh plugins 설치 
- 플러그인 설치시 해당 경로에 폴더가 생성되는 듯 /home/anderson/.oh-my-zsh/plugins

  1. zsh-autosuggesionts 
    - 명령어 타이핑시 자동완성 지원
    $ cd /home/anderson/.oh-my-zsh/custom/plugins/
    $ git clone https://github.com/zsh-users/zsh-autosuggestions ${ZSH_CUSTOM:-~/.oh-my-zsh/custom}/plugins/zsh-autosuggestions
    $ vim ~/.zshrc         // 플러그인 내용 수정
      
      plugins=(git zsh-autosuggestions)        
    
    $ source ~/.zshrc        // 설정 반영 , 방향키로 자동 완성 기능 사용할 수 있도록 지원👨‍💻

  2. syntax highlighting 
    - 명령어에 구문 강조 적용, 특정 명령을 사용할 수 있으면 녹색으로, 설치 되지 않음 등의 이유로 알 수 없는 명령어는 빨간색으로 나타냄
    $ cd /home/anderson/.oh-my-zsh/custom/plugins/        // 아래 명령어를 보면 해당 경로에 받아 달라고 함
    $ git clone https://github.com/zsh-users/zsh-syntax-highlighting.git ${ZSH_CUSTOM:-~/.oh-my-zsh/custom}/plugins/zsh-syntax-highlighting
    $ vim ~/.zshrc        // 플러그인 내용 수정
   
      plugins=(git zsh-autosuggestions zsh-syntax-highlighting)
   
    $ source ~/.zshrc        // 설치 안 되어 있는 명령어는 빨간색, 설치된 거는 초록색으로 나타내 줌 

  3. zsh-z 
    - 터미널에서 디렉토리를 이동할때 편리한 플러그인
    - 여러번 입력했던 디렉토리를 기억해서 어느 위치에서건 최종 디렉토리 명을 'z some-directory' 와 같이 입력하여 바로 이동 가능

    $ echo $ZSH_CUSTOM          // 환경 변수 설정으로 경로가 지정되어 있다. /home/anderson/.oh-my-zsh/custom
    $ git clone https://github.com/agkozak/zsh-z $ZSH_CUSTOM/plugins/zsh-z
    $ vi ~/.zshrc               // 내용 수정 후 저장
      plugins=(git zsh-autosuggestions zsh-syntax-highlighting z)      // plugin에 z가 추가됨 
    $ source ~/.zshrc  
    $ z 폴더명 

  4. alias-tips 
     - https://github.com/djui/alias-tips

     $ cd ${ZSH_CUSTOM1:-$ZSH/custom}/plugins
     $ git clone https://github.com/djui/alias-tips.git
     $ vi ~/.zshrc

       # -plugins=(...)
       # +plugins=(... alias-tips)

     $ source ~/.zshrc  
     // 테스트로 git remote -v  작성하면 grv 추천해줌     


### Named directory 구성 
- 터미널에서 드라이브 이동시 불편하니 예약어 걸어주는 방법 
- /mnt/c/Users/{user name}/   매번 적자니 귀찮....          // /mnt/ 폴더 통해서 드라이브 찾을 수 있었구나 !!!

  $ vim ~/.zshrc 

    hash -d gitRepository=/mnt/d/gitRepository
    hash -d personal=/mnt/d/personal_gitRepo
  
  $ cd ~gitRepository         // 간결하게 이동 가능 

### git version 관련 
- git bash 와 ubuntu 터미널의 git 경로가 일치 하지 않는 문제 발생 
  $ which git 
    /mingw64/bin/git         // git bash 
    /usr/bin/git             // ubuntu 터미널

### powerlevel10k 테마 설치 (하지 않기로.. 더 폰트 깨져서 oh my zsh 삭제하고 다시 함)
  $ git clone --depth=1 https://github.com/romkatv/powerlevel10k.git ${ZSH_CUSTOM:-$HOME/.oh-my-zsh/custom}/themes/powerlevel10k
  $ vim ~/.zshrc   // 내용 수정 후 저장
    ZSH_THEME="powerlevel10k/powerlevel10k 
  $ source ~/.zshrc  

### 글꼴 수정 
  - 네이버에서 만든 D2로 변경 
  - 해당 페이지 설명대로 따라 하면 적용됨 
    https://velog.io/@njh7799/Windows-10-%EC%97%90%EC%84%9C-%ED%84%B0%EB%AF%B8%EB%84%90-%EA%BE%B8%EB%AF%B8%EA%B8%B0  


### ssh config 설정으로 쉽게 접속하기 
  - 참고 기술 블로그 
    - https://jojoldu.tistory.com/427
    - https://edykim.com/ko/post/simplifying-ssh-with-ssh-config/

    $ mkdir ~/.ssh 
    $ vim ~/.ssh/config 
      # 양식   
      Host 호스트명 
        HostName 호스트IP 
        User 호스트사용자명(접속 아이디)
        IdentityFile  ~/.ssh/data-analysis.key
      # 실제 예시
      Host web-log-collect01
        HostName 54.180.105.170
        User ubuntu
        IdentityFile ~/.ssh/data-analysis.key 

    $ cd .ssh                
    $ cp /mnt/c/Users/Administrator/Desktop/파일정리/업무/data-analysis.key .   // 현재 위치로 키 복사

  - 에러) Load key "/home/anderson/.ssh/data-analysis.key": bad permissions       키 권한 문제
    해결방법) https://www.deok.me/entry/SSH-%ED%82%A4-%EC%9D%B4%EC%9A%A9-%EC%8B%9C-bad-permissions-ignore-key-%EC%97%90%EB%9F%AC%EA%B0%80-%EB%B0%9C%EC%83%9D%ED%95%A0-%EA%B2%BD%EC%9A%B0

    $ chmod 400 ./{keyname}          // data-analysis.key에 대한 권한 변경해 줌 
    $ ssh web-log-collect01          // 비밀번호 입력하면 접속 됨 


```

#### 윈도우 터미널에서 바탕화면 > 파일정리(개인) 폴더 위치 
/mnt/c/Users/Administrator/Desktop/파일정리/업무

#### wsl2 + zsh 로 갈아타기 (정리 잘 되어 있음👍)
https://mulder21c.github.io/2021/01/28/setting-up-wsl-2-dev-env-and-zsh-on-windws-10/
https://llighter.github.io/install_zsh_in_wsl2/

#### wsl 환경에서 복사 붙여넣기 설정 ( 붙여 넣기 시 ctrl + shift + v)
https://hbase.tistory.com/290

#### oh my zsh 공식 CheatSheet (git에 대한 단축키도 존재)
https://github.com/ohmyzsh/ohmyzsh/wiki/Cheatsheet
https://github.com/ohmyzsh/ohmyzsh/tree/master/plugins/git/


#### zsh 태마 변경 (공식 git hub)
https://github.com/ohmyzsh/ohmyzsh/wiki/External-themes

#### zsh command line 컴퓨터명 삭제, 자동완성 플러그인 사용하기 

#### 우분투 메뉴얼 
[http://manpages.ubuntu.com/manpages/bionic/ko/man1/chsh.1.html](http://manpages.ubuntu.com/manpages/bionic/ko/man1/chsh.1.html '18.04 LTS')

#### zsh documentation 링크 포함
https://askubuntu.com/questions/1042002/how-do-i-make-named-directories-permanent-in-zsh-and-how-do-i-edit-them-also-wh


#### oh my zsh 참고 사이트 
https://nolboo.kim/blog/2015/08/21/oh-my-zsh/           //일부 작성하고 tab 누르면 명령어 헬프를 해주네..


#### ssh 명령어 
[https://webisfree.com/2020-06-25/linux-%EB%AA%85%EB%A0%B9%EC%96%B4-ssh-%EC%A0%91%EC%86%8D%ED%95%98%EB%8A%94-%EB%B0%A9%EB%B2%95](https://webisfree.com/2020-06-25/linux-%EB%AA%85%EB%A0%B9%EC%96%B4-ssh-%EC%A0%91%EC%86%8D%ED%95%98%EB%8A%94-%EB%B0%A9%EB%B2%95)

```
# ssh config 설정 했을 경우 
$ ssh {host}

# 기본 형태
$ ssh <host주소> <옵션>

$ ssh ubuntu@website.com 
$ ssh User@0.0.0.0 -p 22        // -p 옵션은 포트 설정 
$ ssh -i keyfile user@ip        // -i 옵션은 키 파일 사용 (경로)

👉 그외 옵션은 필요시 찾아보기👨‍💻
```

#### docker 실행시 에러 
``` 
## 에러 1
> docker -v 
  The command 'docker' could not be found in this WSL 1 distro. 
  We recommend to convert this distro to WSL 2 and activate 
  the WSL ....(이하 생략)

//window powershell 실행 
> wsl -l -v 
  NAME   STATE    VERSION 
  Ubuntu Running    1
> wsl --set-version Ubuntu 2 
  NAME   STATE    VERSION 
  Ubuntu Running    2

## 에러 2 
> docker -v 
  The command 'docker' could not be found in this WSL 2 distro.
  We recommend to activate the WSL integration in Docker Desktop settings.

  For details about using Docker Desktop with WSL 2, visit:


```

#### git 관련 
- oh my zsh git cheat sheet 
  - https://gist.github.com/AdamMarsden/1c9336b65fa185cdfb14
  - https://kapeli.com/cheat_sheets/Oh-My-Zsh_Git.docset/Contents/Resources/Documents/index
```
# repository 속도 느려지는 현상 -> https://kimgaeun.tistory.com/10
## 느려지는 특정 repo에만 적용
  git config --add oh-my-zsh.hide-status 1
  git config --add oh-my-zsh.hide-dirty 1

## 모든 repo에 적용
  git config --global --add oh-my-zsh.hide-status 1         // .. 이것저것 설정 추가하다보니 속도가 느려져 결국 추가.
  git config --global --add oh-my-zsh.hide-dirty 1          // dirty 체크만 설정함 

# git status shows all files as modified
  - https://github.com/microsoft/WSL/issues/184  👍
  - stack overflow > https://stackoverflow.com/questions/59061816/git-forces-refresh-index-after-switching-between-windows-and-linux  

  ## For the current repository
    git config core.filemode false 
    git config core.autocrlf true 

    git config --global core.autocrlf true
    git config --global core.filemode false

 

```