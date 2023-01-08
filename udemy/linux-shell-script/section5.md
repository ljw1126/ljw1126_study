## section5. linux programming conventions

> input, output (>, <, &>)

### part1. Advanced Standard Input, Standard Output and Standard Error
- Standard Input 
  - FD0 - STDIN       // FD : file descript
- Standard Output 
  - FD1 - STDOUT
- Standard Error 
  - FD2 - STDERR

```bash
$ vim luser-demo08.sh

#!/bin/bash

# This script demonstrates I/O redirection.

# 1.Redirect STDOUT to a file.
FILE="/tmp/data"
head -n1 /etc/passwd > ${FILE}          # head data save to ${FILE}

# 2.Redirect STDIN to a progream
read LINE < ${FILE}         # LINE : variable
echo "LINE contains : ${LINE}"

# 3. Redirect STDOUT to a file, overwriting the file.
head -n3 /etc/passwd > ${FILE}
echo 
echo "Contents of ${FILE}:"          # ${FILE} == /tmp/data  ??
cat ${FILE}             # result

# 4. Redirect STDOUT to a file, appending to the file.
echo "${RANDOM} ${RANDOM}" >> ${FILE}
echo "${RANDOM} ${RANDOM}" >> ${FILE}
echo
echo "Contents of ${FILE}:"   # ${FILE} == /tmp/data  ??
cat ${FILE}         # result

# 5. Redirect STDIN to a progream. using FD 0
read LINE 0< ${FILE
echo 
echo "LINE contains : ${LINE}"

# 6. Redirect STDOUT to a file using FD 1, overwriting the file.
head -n3 /etc/passwd 1> ${FILE} 
echo 
echo "Contents of ${FILE} : "
cat ${FILE}

# 7. Redirect STDERR to a file using FD 2.
ERR_FILE="/tmp/data.err"
head -n3 /etc/passwd /fakefile 2> ${ERR_FILE}          # /fakefile error occur

# 8. Redirect STDOUT and STERR to a file.
head -n3 /etc/passwd /fakefile &> ${FILE}
echo 
echo "Content of ${FILE}:"
cat ${FILE}

# 9. Redirect STDOUT and STDERR through a pipe.
echo 
head -n3 /etc/passwd /fakefile |& cat -n

# 10. Send output to STDERR
echo "This is STDERR!" >&2

# 11. Discard STDOUT 
echo 
echo "Discarding STDOUT : "
head -n3 /etc/passwd /fakefile > /dev/null

# 12. Disacrd STDERR
echo
echo "Disacrd STDERR:"
head -n3 /etc/passwd /fakefile 2> /dev/null

# 13. Discard STDOUT and STDERR
echo
echo "Discard STDOUT and STDERR : "
head -n3 /etc/passwd /fakefile &> /dev/null

# 14. Clean up 
rm ${FILE} ${ERR_FILE} &> /dev/null
```

```bash
# 1.Redirect STDOUT to a file.
$ ./luser-demo08.sh

$ id -un > id
$ cat id 

$ echo "${UID}" > uid 
$ cat uid

$ echo "${UID}" > /uid  # permission denied
$ ls -ld /
$ !v

# 2.Redirect STDIN to a progream
$ ./luser-demo08.sh
$ echo "secret" > password
$ cat password
$ sudo passwd --stdin einstein < password          # --stdin userName
Changing password for user einstein 
$ su - einstein
Password : secret 


# 3. Redirect STDOUT to a file, overwriting the file.
$ echo "new" > password
$ !v
$ ./luser-demo08.sh

$ echo "another-line" >> password 
$ cat password 
new 
another-line

$ date | sha256sum | head -c10 >> password
$ cat password

# 4. Redirect STDOUT to a file, appending to the file.
$ !v
$ ./luser-demo08.sh
```

> 이해가 안되는게 .. 쉘 파일 내에 ${FILE} 값이 왜 다르게 처리되는거지??

### part2. Advanced Standard Input, Standard Output and Standard Error

- Standard Input 
  - FD0 - STDIN       // FD : file descript
- Standard Output 
  - FD1 - STDOUT
- Standard Error 
  - FD2 - STDERR

```bash
#
$ cat /etc/centos-release
$ read X < /etc/centos-release
$ echo ${X}

$ read X 0< /etc/centos-release  # 0 : input , Notice that there is no space between the file descriptor zero in this case and the redirection operator. 

$ read X 0 < /etc/centos-release  # doesnt work
$ read X 0    
-bash : read: '0': not a valid identifier

# 
$ echo "${UID}" > uid
$ cat uid 
1000

$ echo "${UID}" 1> uid          # >> == 1> ?
$ cat uid 
1000

$ echo "${UID}" 1 > uid
$ cat uid 
1000 1

# 
$ man head 
$ head -n1 /etc/passwd /etc/hosts        # 2 file read
$ head -n1 /etc/passwd /etc/hosts /fakefile    
$ head -n1 /etc/passwd /etc/hosts /fakefile > head.out
$ cat head.out 
$ head -n1 /etc/passwd /etc/hosts /fakefile 2> head.err   # error processing
$ cat head.err        # print error message
$ rm head.out head.err

$ head -n1 /etc/passwd /etc/hosts /fakefile  > head.out 2> head.err   # same result
$ cat head.out head.err

$ head -n1 /etc/passwd /etc/hosts /fakefile  > head.out 2>> head.err
$ head -n1 /etc/passwd /etc/hosts /fakefile  > head.out 2>> head.err
$ head -n1 /etc/passwd /etc/hosts /fakefile  > head.out 2>> head.err
$ cat head.err      

# 
$ head -n1 /etc/passwd /etc/hosts /fakefile > head.both 2>&1  # 2> : redirect standard error / redirect standard input file (&:ampersand가 반사의 개념? ㅋㅋ)
# since standard error is redirected to standard output and standard output is redirected to head both
$ cat head.both 
$ rm head.both 

$ head -n1 /etc/passwd /etc/hosts /fakefile &> head.both
$ cat head.both

$ head -n1 /etc/passwd /etc/hosts /fakefile &>> head.both
$ cat head.both        # 똑같은 내용이 중복으로 쌓임
$ rm head.both

# pipe line = from standard output to standard input 
$ man cat 
$ head -n1 /etc/passwd /etc/hosts /fakefile | cat -n        # default : 5 
$ head -n1 /etc/passwd /etc/hosts /fakefile 2>&1 | cat -n 

$ head -n1 /etc/passwd /etc/hosts /fakefile |& cat -n    


# 5. Redirect STDIN to a progream. using FD 0
# 6. Redirect STDOUT to a file using FD 1, overwriting the file.
$ vim luser-demo08.sh

# 7. Redirect STDERR to a file using FD 2.
$ cat /tmp/data.err
$ !vi

# 9. Redirect STDOUT and STDERR through a pipe.
$ vim luser-demo08.sh

# 
$ echo 'error' | cat -n
1 error
$ echo 'error' >&2 | cat -n
error
$ echo 'error' 1>&2 | cat -n
error
$ !vi

# 10. Send output to STDERR          // ??
$ ./luser-demo08.sh
$ cat err
$ rm err

# 
$ head -n1 /etc/passwd /etc/hosts /fakefile
$ head -n1 /etc/passwd /etc/hosts /fakefile > /dev/null
$ head -n1 /etc/passwd /etc/hosts /fakefile 2> /dev/null
$ head -n1 /etc/passwd /etc/hosts /fakefile &> /dev/null      # no output generate
$ echo ${?}   # 1 : error code
$ head -n1 /etc/passwd /etc/hosts &> /dev/null
$ echo ${?}   # 0 :success


# 12. Disacrd STDERR
# 13. Discard STDOUT and STDERR
# 14. Clean up 작성
$ ./luser-demo08.sh

```

FD 0 - Standard Input
FD 1 - Standard Output 
FD 2 - Standard Error

Command > FILE          # truncate and overwrite
Command >> FILE         # append

Command &> FILE
Command |& Command 

Command < FILE
Command > /dev/null

