## 섹션7. Transforming  Data/Data Processing/Reporting 

### Cut and Awk

```bash
$ type -a cut 

$ man cut       # manual about cut command

$ cat /etc/passwd   # test sample file
...
leejinwoo:x:1000:1000:leejinwoo,,,:/home/leejinwoo:/usr/bin/zsh
fwupd-refresh:x:128:136:fwupd-refresh user,,,:/run/systemd:/usr/sbin/nologin

$ cut -c 1 /etc/passwd
...
g
l
f

$ cut -c 7 /etc/passwd     # -c : characters 7 자리 
$ cut -c 4-7 /etc/passwd
...
jinw
pd-r

$ cut -c 4- /etc/passwd     # 4번째 이후 부터
$ cut -c 4 /etc/passwd
$ cut -c 1-4 /etc/passwd
$ cut -c 1,3,5 /etc/passwd
...
lei
fud

$ cut -c 5,3,1 /etc/passwd     # 순서도 마음대로 가능한듯
$ cut -c 999 /etc/passwd    # nothing display

$ cut -b 1 /etc/passwd      # -b : select only these bytes
$ echo "nu" | cut -c 1
n

$ echo "nu" | cut -b 1      # nu 가 바이트 코드가 아니라서 똑같이 출력됨;; (다른 예제 찾아보기!)

$ echo -e 'one\ttwo\tthree'
$ echo -e 'one\ttwo\tthree' | cut -f 1    # delimiter default : \t (?)
$ echo -e 'one\ttwo\tthree' | cut -f 2
$ echo -e 'one\ttwo\tthree' | cut -f 3

$ echo 'one,two,three'
$ echo 'one,two,three' | cut -d ',' -f 1   # delimiter custom
one
$ echo 'one,two,three' | cut -d ',' -f 2  
two
$ echo 'one,two,three' | cut -d ',' -f 3    
three

$ echo 'one\two\three'
$ echo 'one\two\three' | cut -d \ -f 3  # error
$ echo 'one\two\three' | cut -d '\' -f 3
three 

$ cut -d ':' -f 1,3 /etc/passwd
...
leejinwoo:1000
fwupd-refresh:128

$ cut -d ':' -f 1,3 --output-delimiter=',' /etc/passwd   # delimiter replace 
...
leejinwoo,1000
fwupd-refresh,128

# sample file create 
$ echo 'first,last' > people.csv
$ echo 'John,Smitt' >> people.csv
$ echo 'firstly,mclasty' >> people.csv
$ echo 'Mr. firstly,mclasty' >> people.csv

$ cat people.csv
$ cut -d ',' -f 1 people.csv    # , 로 나눠서 첫번째 단어 출력

# grep [pattern]
$ grep first people.csv 

$ grep 'first,last' people.csv

## carrot symbol ? 
$ grep '^first' people.csv  # first로 시작하는 단어
$ grep 'first' people.csv

$ grep 't$' people.csv      # $가 맨 뒤를 뜻하는 정규 표현
$ grep '^first,last$' people.csv

$ grep -v '^first,last$' people.csv  # -v ?? not??

$ grep -v '^first,last$' people.csv | cut -d ',' -f 1 

$ cut -d ',' -f 1 people.csv
$ cut -d ',' -f 1 people.csv | grep -v '^first$'      # ??

# example awk command
$ cp people.csv people.dat
$ vi !$
$ cat people.dat
DATA:firstDATA:second
DATA:JohnDATA:Smitt
DATA:firstlyDATA:mclasty
DATA:Mr. firstlyDATA:mclasty

$ cut -d ':' -f 2 people.dat
$ cut -d 'DATA:' -f 2 people.dat    # doesn't work
$ awk -F 'DATA:' '{print $2}' people.dat  # work ! 
                 take action  file name

## return previous cut example 
$ cut -d ':' -f 1,3 /etc/passwd
$ awk -F ':' '{print $1, $3}' /etc/passwd
$ awk -F ':' '{print $1$3}' /etc/passwd        # 뭔가 해당 명령어 특징 설명 
$ awk -F ':' -v OFS=',' '{print $1$3}' /etc/passwd
$ awk -F ':' -v OFS=',' '{print $1,       $3}' /etc/passwd
$ awk -F ':' '{print $1",  "   $3}' /etc/passwd
$ awk -F ':' '{print "COL1: " $1 " COL3: " $3}' /etc/passwd

## example
$ cut -d ':' -f 3,1 /etc/passwd
$ awk -F ':' '{print $3, $1}' /etc/passwd  # order change
$ awk -F ':' '{print "UID: " $3 ";LOGIN: " $1}' /etc/passwd
$ awk -F ':' '{print $NF}' /etc/passwd       # $NF ??

$ awk -F ':' '{print $(NF - 1)}' /etc/passwd  

## irregular data 
$ echo 'L1C1     L1C2' > lines
$ echo '  L2C1  L2C2  ' >> lines
$ echo 'L3C1     L3C2' >> lines
$ echo -e 'L1C1\tL1C2' >> lines
$ awk '{print $1, $2}' lines              # awk가 white space 다루기 쉽다는 듯??

```

---

### 38. Cut and Awk Demonstration Script : Open Network Ports

```bash
$ netstat -nutl       # find option !! t:tcp , n : number ??, l : listening port

$ netstat -nutl | grep -v '^Active'
$ netstat -nutl | grep -v '^Active' | grep -v '^Proto'
$ netstat -nutl | grep -Ev '^Active|^Proto'       # same result

$ netstat -nutl | grep ':'
$ netstat -nutl | grep ':' | cut -d ':' -f 2

$ netstat -nutl | grep ':' | awk '{print $4}'
$ netstat -nutl | grep ':' | awk '{print $4}' | awk -F ':' '{print $NF}'   # $NF??

## tcp v4
$ netstat -4nutl
$ netstat -4nutl | grep ':'
$ netstat -4nutl | grep ':' | awk '{print $4}'
$ netstat -4nutl | grep ':' | awk '{print $4}' | cut -d ':' -f 2
$ netstat -4nutl | grep ':' | awk '{print $4}' | awk -F ':' '{print $2}'
$ netstat -4nutl | grep ':' | awk '{print $4}' | awk -F ':' '{print $NF}'
$ netstat -nutl | grep ':' | awk '{print $4}' | awk -F ':' '{print $NF}'

$ vi luser-demo13.sh

#!/bin/bash
# this script shows the open network prots on a system.
# use -4 as an argument to limit to tcpv4 ports

#if [[ "${1}" = '-1']] 
#then ... 

netstat -nutl ${1} | grep ':' | awk '{print $4}' | awk -F ':' '{print $NF}'

$ chome 775 luser-demo13.sh
$ ./luser-demo13.sh -4
$ ./luser-demo13.sh -p

$ sudo netstat -nutlp 
$ sudo netstat -nutlp | grep 22      # 이렇게도 많이 쓴다는 팁인듯.. 포트를 굳이 위에 스크립트 짤 필요 없이! 


```

---

### 39. sort and uniq

```bash
$ cat /etc/passwd

$ sort /etc/passwd
$ sort /etc/passwd | less
$ sort -r /etc/passwd      // -r : ??

# uid cut and sort
$ cut -d ':' -f 3 /etc/passwd
$ cut -d ':' -f 3 /etc/passwd | sort       # string sort 
$ cut -d ':' -f 3 /etc/passwd | sort -n    # number sort 

#
$ sudo du /var      # du : disk usage 
$ sudo du /var | sort -n      # KB
$ sudo du -h /var
$ sudo du -h /var | sort -h   # h : human numeric sort

# sort and uniq
$ nestat -nutl  # l : listening 
$ nestat -nutl | grep ':'
$ nestat -nutl | grep ':' | awk '{print $4}'
$ nestat -nutl | grep ':' | awk '{print $4}' | awk -F ':' '{print $NF}'

$ nestat -nutl | grep ':' | awk '{print $4}' | awk -F ':' '{print $NF}' | sort -n
$ nestat -nutl | grep ':' | awk '{print $4}' | awk -F ':' '{print $NF}' | sort -nu            # sort -u : uniq option 
$ nestat -nutl | grep ':' | awk '{print $4}' | awk -F ':' '{print $NF}' | sort -u
$ nestat -nutl | grep ':' | awk '{print $4}' | awk -F ':' '{print $NF}' | sort -n | uniq # uniq work 
$ nestat -nutl | grep ':' | awk '{print $4}' | awk -F ':' '{print $NF}' | uniq      # duplicated !! uniq doesnt work !

$ nestat -nutl | grep ':' | awk '{print $4}' | awk -F ':' '{print $NF}' | sort -n | uniq -c    # uniq -c : count ?!

# 
$ sudo cat /var/log/messages
$ sudo cat /var/log/messages | awk '{print $5}'
$ sudo cat /var/log/messages | awk '{print $5}' | sort
$ sudo cat /var/log/messages | awk '{print $5}' | sort | uniq -c 
$ sudo cat /var/log/messages | awk '{print $5}' | sort | uniq -c  | sort -n

# wc : word count ??
$ wc /etc/passwd  
48 87 2902 /etc/passwd
                ## column1. : the number of lines in the file
                ## *2. : the number of word 
                ## *3. number of character 
$ wc -l /etc/passwd
$ wc -w /etc/passwd
$ wc -c /etc/passwd

$ grep bash /etc/passwd
$ grep bash /etc/passwd | wc -l
$ grep -c bash /etc/passwd    # sample above command

# ??
$ cat /etc/passwd 
$ cat /etc/passwd | sort -t ':' -k 3 -n 
$ cat /etc/passwd | sort -t ':' -k 3 -n -r

# visit url count
$ cd /vagrant/              # 나는 VM 사용안해서 해당 폴더 없음;;
$ cat access_log 
$ cat access_log  | cut -d '"' -f 2
$ cut -d '"' -f 2 access_log        # same result

$ cut -d '"' -f 2 access_log | cut -d ' ' -f 2
$ awk '{print $7}' access_log

$ cut -d '"' -f 2 access_log | cut -d ' ' -f 2 | sort 
$ cut -d '"' -f 2 access_log | cut -d ' ' -f 2 | sort | uniq -c
$ cut -d '"' -f 2 access_log | cut -d ' ' -f 2 | sort | uniq -c | sort -n 
$ cut -d '"' -f 2 access_log | cut -d ' ' -f 2 | sort | uniq -c | sort -n | tail -3

$ vi luser-demo14.sh 
#!/bin/bash 

# display the top three most visted urls for a given web server log file.
LOG_FILE="${1}"
if [[ ! -e "${LOG_FILE}"]]
then 
    echo "Cannot open ${LOG_FILE}" >&2
    exit 1
fi

cut -d '"' -f 2 ${LOG_FILE} | cut -d ' ' -f 2 | sort | uniq -c | sort -n | tail -3

$ chome 755 luser-demo14.sh 
$ ./luser-demo14.sh  dsjaklsda     # print condition text
$ ./luser-demo14.sh  access_log 

```

---

### 42. Sed 
- Sed = Stream editor.
- A stream is data that travels from : 
    - One process to another through a pipe.
    - One file to another as a redirect.
    - One device to another.
- Standard Input = Standard Input Stream, etc 
- Streams are typically textual data.

Sed Usage 
- Sed performs text transformations on streams.
- ex.
    - Substitute some text for other text.
    - Remove lines.
    - Append text after given lines.
    - Insert text before certain lines.
- Sed is used programmatically, not interactively(대화식으로).

```bash
$ type -a sed 

$ man sed          # q : quit

$ echo 'Dwight is the assistant regional manager.' > manager.txt
$ cat manager.txt

# s : substitude, using ragular expression  정규식 형태로 바꿔준다는 듯
$ sed 's/assistant/assistant to the/' manager.txt 

$ cat manager.txt      # 그대로, sed가 입력만 받아서 변경만 할 뿐 저장은 안함*

$ echo 'I love my wife.' > love.txt 
$ sed 's/my wife/sed/' love.txt

$ sed 's/MY WIFE/sed' love.txt       # doesnt effect , follow case 
# $ sed 's/search-pattern/replacement-string/flags' 

$ sed 's/MY WIFE/sed/i' love.txt        # i : ignore case flag, work!
$ sed 's/MY WIFE/sed/I' love.txt        # same result (?)

$ echo 'Thie is line 2.' >> love.txt      # append
$ echo 'I love my wife with all of my heart.' >> love.txt
$ cat love.txt

$ sed 's/my wife/sed/' love.txt 

$ echo 'I love my wife and my wife loves me. Also, my wife loves the cat.' >> love.txt
$ sed 's/my wife/sed/g' love.txt         # g : global, 전체 내용 중 수정

$ sed 's/my wife/sed/2' love.txt       # 2 ? 라인 지정하는거 같은데.. 번호가 안 맞는듯?? 왜 마지막 줄이 수정되지??

# replace and output
$ sed 's/my wife/sed/g' love.txt > my-new-love.txt
$ sed -i.bak 's/my wife/sed/g' love.txt   # love.txt.bak 생성, 기존 love.txt 는 수정 결과 담기고, 백업파일에 원본 내용이 있네 !! 
$ sed -i .bak 's/my wife/sed/g' love.txt  # error. !! space with i option 

$ sed 's/love/like/gw like.txt' love.txt  # like.txt 

# same result
$ cat like.txt | sed 's/my wife/sed/g'      # powerful
$ sed 's/my wife/sed/g' like.txt

#
$ echo '/home/jason'
$ echo '/home/jason' | sed 's/\/home\/jason/\/export\/users\/jsonc/'
$ echo '/home/jason' | sed 's#/home/jason#/export/users/jasonc#'    # same result 
$ echo '/home/jason' | sed 's:/home/jason:/export/users/jasonc:'    # same result 

# 
$ cat love.txt 
$ sed '/This/d' love.txt        # d: delete line(?)
$ sed '/love/d' love.txt        # love line all delete , but original file not change

# using regular expressions
$ echo '#User to run service as.' > config 
$ echo 'User apache' >> config
$ echo >> config 
$ echo '# Group to run service as.' >> config
$ echo 'Group apache' >> config 
$ cat config
$ sed '/^#/d' config        # using regular expression, delete containing # lines
$ sed '/^$/d' config        
$ sed '/^#/d ; /^$/d' config  # start with # line and blank line delete
$ sed '/^#/d ; /^$/d ; s/apache/httpd' config     # and substitude apache to httpd
$ sed -e '/^#/d' -e '/^$/d' -e 's/apache/httpd' config     # same result

# 
$ echo '/^#/d' > script.sed
$ echo '/^$/d' >> script.sed
$ echo 's/apache/httpd' >> script.sed         
$ sed -f script.sed config  # script.sed 파일 내용을 뒤에 파일에 적용해서 실행한 결과를 표출

# range
$ cat config 
$ sed '2 s/apache/httpd' config        # effect second line
$ sed '2s/apache/httpd' config              # ? 
$ sed '/Group/ s/apache/httpd' config        # ?
$ sed '1,3 s/run/execute' config            # 1~3 line effect 
$ sed '1,4 s/run/execute' config
$ sed '/#User/,/^$/ s/run/execute/' config      # #User ~ blank line  
```