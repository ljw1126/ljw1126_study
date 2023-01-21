## section4. Password Generation and Shell Script Arguments

Tip. 최근 update한 파일 (quickly load)
$ !v            
또는
$ !. 

최근 실행한 파일
$ !./         // execute script file

### 18. Random Data, Crytographic Hash Functions, Text and String Manipulation.

```
$ vim luser-demo05.sh
:q
$ chmod 755 luser-demo05.sh

$ man bash    
/Random

$ echo "${RANDOM}"
869

$ man date 
/FORMAT

# every single second
$ date +%s            

# execute recently search manual
$ !m
$ man date 
/FORMAT
$ date +%s%N

# checksum file by centos       
$ cat sha1sum.txt     // where ? ubuntu has not file. does not here !

// it works in ubuntu.
$ md5sum [file]
$ sha1sum [file] 

$ type sha1sum
sha1sum is /usr/bin/sha1sum

// 입력해서 무결성을 검증하는 듯함, *.iso 파일 사용 
// 파일 내용 수정시 값이 틀려짐 !! 
$ sha1sum luser-demo01.sh
cf5f2b4ced40d9273cfded1dab48ed8672d472db  luser-demo01.sh

$ ls -l /usr/bin/*sum        // print end of *sum files 
$ date +%s | sha256sum 
$ man sha256sum 

# head command
$ type -a head 
$ man head 

$ head -n1 /etc/passwd
$ head -n 1 /etc/passwd 
$ head -1 /etc/passwd

$ head -2 /etc/passwd
$ head -n2 /etc/passwd
$ head -n 2 /etc/passwd

$ head -c1 /etc/passwd   // c : character 
$ echo "testing" | head -c2
te

$ date +%s | sha256sum | head -c8 
aaae5c31

$ S='!@#$%%^&*()_'
$ echo "${S}"

# shuf command
$ man shuf
$ cat /etc/passwd
$ shuf /etc/passwd

# fold command 
$ man fold 
$ S='!@#$%^&*()_+'
$ echo "${S}"
$ echo "${S}" | fold -b1     // width = 1 
!
@
#
$
%
^
&
*
(
)
_
+

$ echo "${S}" | fold -w1
$ echo "${S}" | fold -w1 | shuf 
$ echo "${S}" | fold -w1 | shuf 

$ echo "${S}" | fold -w1 | shuf  | head -c1 
$ echo "${S}" | fold -w1 | shuf  | head -c1 

```


### 19. Positional Parameters, Arguments, for Loops, Special Parameters

```
// ${0} is executed command !
echo "you executed this command ${0}" something   // luser-demo06.sh

$ man bash 
/PATH
PATH   The search path for commands.  It is a colon-separated list of directo‐
              ries in which the shell looks for commands (see COMMAND  EXECUTION  be‐
              low).   A  zero-length (null) directory name in the value of PATH indi‐
              cates the current directory.  A null directory name may appear  as  two
              adjacent  colons, or as an initial or trailing colon.  The default path
              is system-dependent, and is set by the administrator who installs bash.
              A common value is ``/usr/local/bin:/usr/local/sbin:/usr/bin:/usr/sbin:
              /bin:/sbin''.

# PATH variable ?
$ echo ${PATH} 
home/leejinwoo/.nvm/versions/node/v18.12.1/bin:/usr/local/sbin:/usr/local/bin:/usr/sbin:/usr/bin:/sbin:/bin:/usr/games:/usr/local/games:/snap/bin:/snap/bin

# make custom command
$ which head 
/usr/bin/head

$ sudo vim /usr/local/bin/head

#!/bin/bash  
echo "Hello from my head."

$ sudo chmod 755 /usr/local/bin/haed 
$ which head
$ which -a head 
/usr/local/bin/head
/usr/bin/head
/bin/head

$ /usr/local/bin/head
Hello from my head.
$ /usr/bin/head -n1 /etc/passwd      // just execute head command
$ sudo rm /usr/local/bin/head

$ type head
-bash: /usr/local/bin/head: No such file or directory
$ hash -r 
$ type head 
head is /usr/bin/head

$ help hash         // 

# 결국에는 쉘 스크립트 작성해서 시스템 경로에 올리면 커맨드/이름만으로 명령어 실행가능하다는거인듯 , 근데 우분투에서는 동작 안하는듯 .. 명령어들..
$ which luser-demo06.sh
$ sudo cp luser-demo06.sh /usr/loca/bin/
$ which luser-demo06.sh
/usr/loca/bin/luser-demo06.sh
$ luser-demo06.sh       // execute command 

# 파일 실행시 이처럼 ./파일명 또는 명령어 등록, 절대경로 등의 방식으로 실행 가능 !!

$ sudo rm /usr/local/bin/luser-demo06.sh 

# basename command (print last child path name)
$ man basename

# dirname command (print parent path name)
$ man dirname 

# 두 명령어의 특징은 실제 없어도 동작은 한다는거 ! ls 로 없는 경로 탐색하면 에러 출력함

$ ./luser-demo06.sh
You executed this command : ./luser-demo06.sh
You used . as the path to the luser-demo06.sh script
    ( dirname )                 (basename)

$ man bash
/Special Parameters   

# edit luser-demo06.sh

    # Tell them how many arguments they passed in. 
    # (Inside the script they are parameters, outside they are arguments.)
    NUMBER_OF_PARAMETERS="${#}"
    echo "You supplied ${NUMBER_OF_PARAMETERS} argument(s) on the command line."

$ ./luser-demo06.sh hello      
You executed this command : ./luser-demo06.sh
You used . as the path to the luser-demo06.sh script
You supplied 1 argument(s) on the command line.

$ ./luser-demo06.sh hello there
You executed this command : ./luser-demo06.sh
You used . as the path to the luser-demo06.sh script
You supplied 2 argument(s) on the command line.

# edit luser-demo06.sh
    # Make sure they at least supply one argument.
    if [[ "${NUMBER_OF_PARAMETERS}" -lt 1 ]]
        then
            echo "Usage : ${0} USER_NAME [USER_NAME]..."
        exit 1
    fi

$ ./luser-demo06.sh one "thie whole thing is two" three 


# for command
$ type -a for 
for is a shell keyword
$ help for | head
$ for X in Frank Clarie Doug
> do
> echo "Hi ${X}"
> done
Hi Frank
Hi Clarie
Hi Doug

$ man bash
/Special Parameters          // read @

# edit *-demo06.sh 
# Generate and display a passwor dfor each parameter.
for USER_NAME in "${@}"
do
  PASSWORD=$(date +%s%N | sha256sum | head -c48)
  echo "${USER_NAME} : ${PASSWORD}"
done

$ ./luser-demo06.sh jason steve jan fred

# Generate and display a passwor dfor each parameter.
for USER_NAME in "${*}"        <--- asteris = just one parameter(=all) read 여러개 넣어도 한개로 받아들임
do
  PASSWORD=$(date +%s%N | sha256sum | head -c48)
  echo "${USER_NAME} : ${PASSWORD}"
done

```

### 20. The While Loop, Infinite Loops, Shifting, Sleeping 
```
$ vim luser-demo07.sh

    # Display the first three parameters.
    echo "Parameter 1: ${1}"
    echo "Parameter 2: ${2}"
    echo "Parameter 3: ${3}"

$ ./luser-demo07.sh apple bread candy 

# while keyword !!
$ type -a while 
while is a shell keyword 

$ help while 
$ X=1
$ while [[ "${X}" -eq 1 ]]
> do
> echo "This is the value of X : ${X}"
> X=7
> done
Thie is the value of X : 1

$ echo $X
7

$ type -a true 
true is a shell builtin
true is /usr/bin/true 
$ help true
$ man true        // status code : 0 
$ echo ${?}
0
$ true blash 
$ echo ${?}
0

# sleep command 
$ man sleep 
$ sleep 1 
$ sleep 1s
$ sleep .5         // half second

$ while [[ true ]]
> do 
>   echo "${RANDOM}"
>   sleep 1
> done 
...
// exit => ctrl +c 

$ sleep 10m 
// exit => ctrl +c 

# shift command 
$ type -a shift 
$ help shift 

# edit luser-demo07.sh
$ !v

    # Loop through all the positional paramters. 
    while [[ "${#}" -gt 0 ]]
     do 
        echo "Number of parameters : ${#}"
        echo "Parameter 1 : ${1}"
        echo "Parameter 2 : ${2}"
        echo "Parameter 3 : ${3}"
        echo
        shift
     done

$ !.
$ ./luser-demo07.sh 
$ ./luser-demo07.sh apple candy
Number of parameters : 2
Parameter 1 : apple
Parameter 2 : candy
Parameter 3 : 

Number of parameters : 1
Parameter 1 : candy
Parameter 2 : 
Parameter 3 :
```



### 21-22. Exercise 3