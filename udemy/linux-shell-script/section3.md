## section3. User and Account Creation - Shell Scripting Project1

## 12. Getting Started with Shell Script : Naming, Permissions, Variables, Builtins.
echo "$VARIABLE"
echo "${VARIABLE}prefix"

```
- 최상단
    #!/bin/bash 
    #!/bin/{something}
        # = sharp
        ! = bang
        #! = Shebang

- 권한 관련 (r : read, w : write, x : execute)
    $ ls -al
    drwxrwxr-x 2 leejinwoo leejinwoo 4096  1월  8 18:01 .
    drwxrwxr-x 3 leejinwoo leejinwoo 4096  1월  8 17:51 ..
    -rwxrwxr-x 1 leejinwoo leejinwoo  103  1월  8 18:01 luser-demo01.sh
    -> onner, group, anyone 순서로 권한 할당 `

- dot(.) mean
    . // thie dir 
    cd ..  //parent dir

- touch command 
    touch blah.sh       // 510 권한으로 그냥 파일 만듦

- echo 
    $ type echo
    echo is a shell builtin
    $ type -a echo 
    echo is a shell builtin
    echo is /usr/bin/echo   --- 해당 경로에 있는 명령어를 실행하는구나 (시스템 명령어 같음 0.0)
    $ /usr/bin/echo 'Hello'
    Hello

    $ help echo |less

- uptime 
    $ type -a uptime
    uptime is /usr/bin/uptime 
    $ help uptime       -- its not shell built in 
    $ man uptime        -- it works!

- variable naming 
    WORD1   (good)
    _WORD   (good)
    3WORD   (bad)
    A-WORD  (bad)
    E@MAIL='asdf'   (bad)

- point
    WORD='script'        -- no space
    echo "$WORD"     (work, double qutoes important !! 0.0/)
    echo '$WORD'     (do not work)

    echo "this is a shell $WORD"
    echo "$WORDing is fun"!       -- is fun! (appending not work)

    echo "this is a shell ${WORD}"
    echo "${WORD} is fun"!     -- script is fun! (appending not work)

    ENDING='ed'
    echo "This is ${WORD}${ENDING}" -- This is scripted

    ENDING='ing'            -- reassignment
    echo "${WORD}${ENDING} is fun!"  -- scripting is fun!

```


## 13. Special Variables, Pseudocode, Command Substitution, if Statement, Conditionals.

```
- UID
    echo "${UID}"   -- 1000 (root = 0)
                  
    man bash
    ?Shell    -- search word in man page
    q         -- quit 

- id
    type -a id
    id is /usr/bin/id
    id is /bin/id
    man id
    
    $ id 
    $ id -u         -- uid 
    $ id -u -n      -- username
    $ id -n -u      -- same result 
    $ id -nu
    $ id -un 

- whoami 
    $ type -a whoami     
    $ man whoami

- if condition ( double bracket syntax)
    $ type -a if
    $ help if 
    $ if [[ "${UID}" -eq 0 ]];then echo 'ur root';fi
    // 조건문 안에 좌우 공백 한칸 씩 중요함 !! ([[ condition ]])

    $ type -a [[         -- shell keyword.. (에러 나서 확인 못함, 아래도 마찬가지)
    $ help [[   

    $ type -a test 
    $ help test
    $ help test | less      -- condition expression .. 

- root user execute
    $ sudo luser-demo02.sh                 -- super user do
    $ su            -- change root

- single bracket 
    $ type -a [
        [ is a shell builtin 
        [ is /usr/bin/[
    $ ls -l /usr/bin/[

```

## 14. Exit Statuses, Return Codes, String Test Conditionals, More Special Variables.

```
$ vim luser-demo03.sh

- exit status
    0 : success
    1 : fail (실패는 0이 아닌 어떤 숫자든 상관없음)
    
    $ id -un
    $ echo "${?}"   -- 0, print status code

    $ id -unx       -- occur error
    $ echo "${?}"   -- 1

- useradd 
    $ man useradd 

- exit 
    $ type -a exit
    $ help exit

- text
    $ help test 
    $ man test

- special variable
    "${?}"     -- saved status code (?)

// ?? 
- = : one equal sign  (using if condition)
  == : double equal sign  (??)
   
```


## 15. Reading Standard Input, Creating Accounts, Username Conventions, More Quoting.

```
$ vim luser-demo04.sh 

- read
    $ type read
    read is a shell builtin
    
    $ type -a read
    read is a shell builtin
    read is /usr/bin/read 
    
    $ help read | less      // what is '| less' ??
    -p(prompt) option 확인함 

    $ read -p 'Type something:' THING        // -p option error in oh my zsh
    // typing 
    $ echo ${THING}

- useradd 
    $ man useradd 
    $ sudo useradd dougstamper  
    $ sudo su - dougstamper
    $ ps -ef
    // 계정이 변경되어서 실행된 것을 확인 가능
    UID         PID    PPID   C STIME   TTY        TIME CMD 
    ...
    root       15358   15357  0 23:46 pts/2    00:00:00 sudo su - dougstamper
    root       15359   15358  0 23:46 pts/2    00:00:00 su - dougstamper
    dougsta+   15360   15359  0 23:46 pts/2    00:00:00 -sh
    dougsta+   15366   15360  0 23:47 pts/2    00:00:00 ps -ef

    * 유저 이름은 소문자로 !! 영어로 시작하고 숫자 허용

    $ man useradd         
        -c option
        -m option 
        /CONFIGURATION 

    $ cat /etc/login.defs      // 이제 사용안 하는건지 .. 쉘이 달라서 그런지 내용 없음

- passwd command
    $ passwd           // password change for current user      
    $ man passwd
      --stdin
      -e option  (--expire)
      -f option  (--force)

- execute file 
    $ sudo ./luser-demo04.sh 
    $ su - {new user name}

    *Error : passwd --stdin not work in ubuntu ! 
    --stdin not work in ubuntu


```

## 16-17. Exercise

