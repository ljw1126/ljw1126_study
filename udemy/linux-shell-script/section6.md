## Section6. Parsing Command Line Options 

// visual mode .. # 어떻게 맨 앞에 넣었는지 찾아보기.
[https://louky0714.tistory.com/29](https://louky0714.tistory.com/29)

### 27. Case Statemets
```
$ type -a case 
$ help case 

$ vim luser-demo09.sh
// wrting

$ vim luser-demo09.sh start
starting 

$ help if 

$ echo $?
1

# if statement 
    if [[ "${1}" = 'start' ]]
        then 
        echo 'starting'
    elif [[ "${1}" = 'stop' ]]
        then
        echo 'stopping'
    elif [[ "${1} = 'status' ]]
        then 
        echo 'Status:'
    else 
        echo 'Supply a valid option'
        exit 
    fi 

$ ./luser-demo09.sh start 
starting 
$ ./luser-demo09.sh something
Supply a valid option 


# case statement
- 파이프 사용해서 or 처럼 케이스 사용가능 
- 정규식 넣어서 케이스 처리도 가능 

    // 한 줄로도 컴팩트하게 표기 가능
    case "${1}" in  
        start) echo 'Starting.' ;;
        stop) echo 'Stopping.' ;;
        status|state|--state|--status) echo 'Status.' ;;
        *)
        echo 'supply a valid option' >&2 ; exit 1 ;;
    esac


$ man bash 
/case                   // case 문 찾음
/pattern matching       // 정규 패턴 찾음
$ help case 
```


### 28. Functions 
```
$ vim luser-demo10.sh

$ help function

log() {
    // work
}

function log() {
    // same work
}

// 파라미터도 함수로 전달받아 사용가능하네
// 주의 사항에 대해 알아보자 
// ${@} 는 뭐지 >> https://slobell.com/blogs/31 
// >> all positional parameters except $0 
// >> man bash 에서 파라미터 검색하면 설명 있음 !!    


$ type -a logger 
$ man logger 
  // -t, -tag option 
$ logger 'hello from the command line!'
$ sudo tail /var/log/messages     // ubuntu >> sudo tail /var/log/syslog
// 마지막에 내가 작성한 로그가 있음 

$ logger -t my-script 'Tagging on'
$ sudo tail /var/log/messages     // ubuntu >> sudo tail /var/log/syslog
// 마지막에 내가 작성한 로그가 있음 

// cp -p  :: i think -p option important!

$ ls -l /var/tmp     
$ ls -l /etc/passwd        
$ sudo tail /var/log/messages      // ubuntu >> sudo tail /var/log/syslog


// bash command 라인 입력에서 함수 선언시 동작 안함 -> 배쉬 스크립트 안에서만 동작하는 듯 
$ type -a backup_file 
```