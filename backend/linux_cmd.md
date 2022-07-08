## 리눅스 명령어

[https://ss64.com/bash/](https://ss64.com/bash/)

#### 환경 확인

```
>  java -version         //java 설치버전 확인1
>  which javac           //java 설치버전 확인2
>  rpm -qa | grep jdk    //java 설치버전 확인3
>  echo $JAVA_HOME       //jdk 설치 위치 확인

>  cat /etc/*release     //centos버전 확인
>  grep . /etc/*-release // os 버전 확인

>  netstat -tnlp         //포트 열렸는지 확인
>  getconf LONG_BIT      //리눅스 운영체제 비트수 확인
>  arch                  //리눅스 운영체제 비트수 확인


> httpd -v               //apache 버전확인1
> rpm -qa | grep tomcat  //apache 버전확인2

> netstat -tnlp     //포트 사용확인
> free -h           //메모리 용량확인 , RES 부분이 실제 물리메모리 , PID 는 ps -ef | grep 으로 확인
> free -m
> free -h -w
> top               //실시간 메모리 사용량 모니터링
> cat /proc/meminfo //메모리 정보 확인

> curl ifconfig.me  //공인 ip 확인

> iptables -nL      //리눅스 방화벽 확인 (centos 7이하 또는 7이상)

> file -bi *.확장자 //해당 파일의 charset 확인
> file -i *        //해당 경로에 전체 파일에 대해

> hostname //호스트네임 확인

> netstat -tnlp | grep 포트번호   // 해당 포트 번호를 사용하는 프로세스가 정상 동작하는 지 확인 ( LISTEN 상태 )

```

+ 확인했는데 없는경우, jdk-7u75-linux-x64.tar.gz 와 같은 설치파일을 받아 풀기 ( JAVA_HOME 설정은 server_setting_guide1.md 참고 )


##### ※ tomcat에 jdk 수동으로 연결 시키는 방법

```
> bin폴더에 catalina.sh 를 vi 편집기로 연후 아래와 같은 형태로 명령어 추가!
> JAVA_HOME=/설치경로/jdk1.7    //설치경로 폴더명은 유도리 있게..
```

#### Apache 관련

```
> /usr/sbin/httpd -v     //아파치 버전확인
> rpm -qa httpd          //아파치 버전확인

> systemctl status httpd //아파치 상태확인 1
> service httpd status   //아파치 상태확인 2
> apachectl configtest   //아파치 설정확인**

> systemctl start httpd // 아파치 시작1
> service httpd start   // 아파치 시작2
> apachectl start       // 아파치 시작3

> systemctl restart httpd // 아파치 재시작1
> service httpd restart   // 아파치 재시작2
> apachectl restart       // 아파치 재시작3

> systemctl stop httpd // 아파치 중지1
> service httpd stop   // 아파치 중지2
> apachectl stop      // 아파치 중지3

> apachectl graceful  // 서버 재시작 하지 않고 설정만 갱신

※ apache 로그 위치 
/var/log/httpd/error_log 이 위치는 RHEL / Red Hat / CentOS / Fedora Linux Apache
/var/log/apache2/error.log 이 위치는 Debian / Ubuntu Linux Apache
/var/log/httpd-error.log 이 위치는 FreeBSD Apache

```

#### apache tomcat 버전 정보 확인

```
> (lib폴더 이동후) java -cp catalina.jar org.apache.catalina.util.ServerInfo
```

#### CPU정보확인

```
> cat /proc/cpuinfo
> grep -c processor /proc/cpuinfo    //cpu 코어 전체 개수 확인
> grep "physical id" /proc/cpuinfo | sort -u | wc -l   //물리 CPU 수 확인
> grep "cpu cores" /proc/cpuinfo | tail -1    // CPU당 물리코어 수 확인
```

#### 디스크 남은 용량 확인

```
> df -k // 킬로바이트 단위로 현재 남은 용량을 확인
> df -m // 메가바이트 단위로 남은 용량을 왁인
> df -h // 보기 좋게 보여줌
> df .  // 현재 디렉토리가 포함된 파티션의 남은 용량을 확인
```

#### 현재 디렉토리에서 서브디렉토리까지의 사용량을 확인

```
> du -a // 현재 디렉토리의 사용량을 파일단위 출력
> du -s // 총 사용량을 확인
> du -h // 보기 좋게 바꿔줌
> du -sh * // 한단계 서브디렉토리 기준으로 보여준다.
```

---

#### 파일압축 하기/풀기

```
*.tar 압축풀기
> tar -zxvf 압축파일명.tar

*.war 압축풀기(**jdk 깔려 있어야 함)
> jar -xvf 압축파일명.war

*.zip 압축풀기
> unzip 파일명.zip
> unzip -O cp949 filename.zip   // 한글깨질 경우
```

---

#### 권한 변경

```
> chown -R root:root 폴더명(파일명)
> chmod -R 775 폴더명(파일명)
```

---

#### 파일 이동 >> 참고 : https://skylit.tistory.com/90

```
- 디렉토리 전체를 원격지에 전송*
   $ scp -r [디렉토리_이름] [원격지_아이디]@[원격지_주소]:[경로]

예제:
   $ scp -r /home/usera/test/ cdsn@192.168.2.8:/home/remoteuser/exp
이렇게 하면 원격지 컴퓨터의 exp 디렉토리 안에 test 디렉토리가 복사된다

- 원격지로부터 디렉토리 전체 받기*
   $ scp -r [원격지_아이디]@[원격지_주소]:[보내는_디렉토리_경로] [경로]

예제:
   $ scp -r remoteuser@192.168.2.8:/home/remoteuser/exp/ /home/usera/
이렇게 하면 로컬 컴퓨터의 usera 디렉토리 안에 exp 디렉토리가 복사된다.

- 원격지에 있는 파일을 로컬에 받기
   $ scp [원격지_아이디]@[원격지_주소]:"[파일명1] [파일명2] [파일명3]" [받는 위치]

예제:
   $ scp remoteuser@192.168.2.8:"/home/remoteuser/111.txt /home/remoteuser/222.txt" /home/usera/
이렇게 하면 원격지에 있는 111.txt, 222.txt 파일들이 usera 디렉토리 안에 복사된다.

   $ scp remoteuser@192.168.2.8:"'/home/remoteuser/tcp 1111.txt' '/home/remoteuser/tcp 9999.txt'" /home/usera/
이렇게 일반 따옴표를 써서 공백이 있는 파일 두 개를 받을 수 있다.

※ 콜론(:) 뒤에 쌍따옴표(")로 둘러싼 영역 안에 여러 개의 파일명을 써주면 된다. 만약 보내는 파일명이 공백을 포함하는 경우에는 그냥 따옴표(')로 해당 파일명을 둘러싸도록 한다.

※ 43서버에 있는 파일 현재 위치로 가져오기
> scp -P 10022 root@서버IP주소:/home/u2-cms.tar ./

※ pulickey 접속(wsl로 동작 테스트함)
> scp -i  ~/.ssh/파일명.key -P 포트번호 ubuntu@서버IP주소:/경로/파일명.war ./
https://stackoverflow.com/questions/20939562/scp-permission-denied-publickey-on-ec2-only-when-using-r-flag-on-directories

```

---

#### vi 관련

```
> :set number   // 라인 행 번호 보기
> gg            // 맨 윗줄로 이동
> shift + g 또는 :$  //맨 아랫줄로 이동
> :숫자         // 지정한 줄 번호로 이동
> dd            // 현재 커서가 있는 줄 잘라내기
> yy            // 현재 커서가 있는 줄 복사하기
> p             // 붙여넣기
> u             // 실행취소 ( undo )
> y             // 복사
> c             // 잘라내기
> :w            // 저장
> :q            // 닫기
> :q!           // 저장하지 않고 닫기
> :wq           // 저장하고 닫기
```

---

#### 특정 jdk로 \*.war 파일 압축풀기
```
> 예시) jar 폴더까지의 경로는 유도리 있게 바꾸기
>
> > /DATA/gaya/jdk1.7.0_75/bin/jar -xvf 파일명.war
```
---

####톰캣 여러개 한번에 다 죽이기

```
> ps aux |awk '/tomcat/ {print $2}'         //pid 확인
> kill $(ps aux |awk '/tomcat/ {print $2}') //tomcat 전부 kill
* 한꺼번에 실행할때는 *.sh 스크립트 파일을 만들어서 명령문 작성 후 그걸로 실행함


* 톰캣이 24개인 가야대에서 한번에 재시작하기 위해 *.sh 파일 만들어 실행함 (tomcat_restart 파일 명령어 중 일부)
-------------------------------------
#교육성과관리센터
cd /DATA/gaya/server/tomcat7_cepm/bin
./startup.sh
-------------------------------------

```

---

#### 톰캣 로그 원하는 검색 내용만 조회

- 전체 catalina 로그에서 검색어가 들어간 부분만 조회함

```
> cat catalina.out | grep "검색어"

* catalina.out 내용 옮기기 ( 명령어 이후 catalina.out 용량 확 줄어듦.. )
> cat /dev/null > catalina.out
```

---

#### 파일 복사하기

```
> cp a.txt b.txt      // a를 b로 이름을 바꾸어 복사
> cp a.txt xyz
   // xyz 폴더가 있으면 xyz 폴더 안에 파일 복사
   // xyz 폴더가 없으면 xyz파일로 복사됨
> cp -r abc xyz
   // abc가 디렉토리이고 xyz라는 디렉토리가 없다면, abc 디렉토리를 xyz로 이름을 바꾸어 복사합니다.
   // abc가 디렉토리이고 xyz라는 디렉토리가 있다면, abc 디렉토리를 xyz 디렉토리 안에 복사합니다. 즉 xyz/abc가 됩니다.
> cp -r abc xyz/zzz
   // bc가 디렉토리이고 xyz/zzz라는 디렉토리가 없다면, abc 디렉토리를 xyz 디렉토리 안에 zzz로 이름을 바꾸어서 복사합니다.
   // abc가 디렉토리이고 xyz/zzz라는 디렉토리가 있다면, abc 디렉토리를 xyz/zzz 디렉토리 안에 복사합니다. 즉 xyz/zzz/abc가 됩니다.
```

#### 파일/폴더옮기기( = 파일/폴더 이름변경)

```
> mv 명령어 잘 사용하기
```

---

#### iptables 와 firewall (리눅스 방화벽)

|          | iptables                   | firewall        |
| -------- | -------------------------- | --------------- |
| 사용버전 | centos7이하                | **centos7이상** |
| 설명     |                            |                 |
| 위치     | vi /etc/sysconfig/iptables |                 |
| 장점     |                            |                 |
| 단점     |                            |                 |

##### iptables 내용 일부

```
//43번 서버 iptables 전북 개발서버로 설정
-A INPUT -m state --state NEW -m tcp -p tcp --dport 4545 -j ACCEPT
-A INPUT -m state --state NEW -m tcp -p tcp --dport 4546 -j ACCEPT
//나머지 기본설정 제외하고 이런게 있음
-A INPUT -s 211.57.172.45 -j DROP
//tcp 부분이 udp 이거나 다른 설정 추가 된거 말고는 공공기관과 다른게 없음
```

##### firewall 명령어 일부

```
 firewall-cmd --list-all
 systemctl status firewalld                   //방화벽(firewalld) 서비스 상태 확인 
 firewall-cmd --zone=public --list-all        //방화벽 확인
 firewall-cmd --zone=public --permanent --add-port=4060/tcp      //방화벽 4060포트오픈
 firewall-cmd --reload        //방화벽 설정 갱신,재적용

 firewall-cmd --add-source=27.0.238.0/24        // 카카오 스토리 링크공유관련 ip대역 허용1
 firewall-cmd --add-source=211.231.103.0/24     // 카카오 스토리 링크공유관련 ip대역 허용2    , 80,443 포트 아니면 안되는 보안 이슈가 있음 
 firewall-cmd --list-all 
 firewall-cmd --reload 
```

---

#### OpenJdk 설치방법

```

yum install java-1.8.0-openjdk-devel.x86_64

// 실 jdk 위치 찾기
which javac
/usr/bin/javac
$ readlink -f /usr/bin/javac
/usr/lib/jvm/java-1.8.0-openjdk-1.8.0.232.b09-0.el7_7.x86_64/bin/javac

// 프로파일에 적용
vi /etc/profile

export JAVA_HOME=/usr/lib/jvm/java-1.8.0-openjdk-1.8.0.232.b09-0.el7_7.x86_64
PATH=$JAVA_HOME/bin:$PATH
source /etc/profile

※ JAVA_HOME , readlink 경로는 시스템마다 차이 있으니 유도리 있게 처리
```

---

#### stdin, stdout, stderr 그리고 pipe('|'), redirect('>')에 대해 //std = standard

> <https://velog.io/@jakeseo_me/유닉스의-stdin-stdout-stderr-그리고-pipes에-대해-알아보자> ><https://twpower.github.io/133-difference-between-redirect-and-pipe>

---

#### 리눅스 user 생성시 sudo su(관리자 권한 변경) 되도록 설정

```

1. 신규 유저 생성
useradd [유저명]

passwd 유저명
     비밀번호 입력 후 확인 입력

※ centos에서는 유저 생성 시 home 아래에 해당 유저의 폴더를 만들어준다

2. 신규 유저에 대한 sudo 권한 설정
root 로 접속

cd /etc
=== suduers에 대한 권한 변경
chmod 777 sudoers
=== sudoers 설정 편집
vi sudoers

=== 해당 내용에 유저에 대한 권한 추가
## Allow root to run any commands anywhere
root	ALL=(ALL)	ALL
유저명	ALL=(ALL)	ALL

=== 저장
:wq!

=== sudoers 권한 원상복귀
chmod 440 sudoers


=== 신규생성한 유저로 ssh 접속 테스트 / sudo su 명령어 확인

```


#### 서버 ip 접속 제한, 포트제한 

```
  # 1-1. host 접속허용 
    $ vi /etc/hosts.allow    
    //접속 후 제일 하단에 [ALL: 허용할 IP 주소 : allow] 설정을 추가하여 해당 IP 주소만 접속 허용하게 설정함 

  # 1-2. host 접속차단 
    $ vi /etc/hosts.deny  
    // 접속 후 제일 하단에 [ALL:ALL] 을 추가하여 접속자 전체 차단가능 

  # 2. 수정 후 재시작
    $ service sshd restart 
```

#### yum 관련 
```
# 명령어
* yum check-update : 현재 인스톨된 프로그램 중에 업데이트 된 것을 체크해줍니다.
* yum clean all : 캐시 되어 있는 것을 모두 지웁니다.
* yum deplist : yum 패키지에 대한 의존성 테스트합니다.
* yum downgrade 패키지 : yum을 통한 패키지 다운그레이드합니다.
* yum erase 패키지 : yum을 통한 시스템에서 삭제합니다.
* yum groupinfo 그룹 : 그룹패키지의 정보를 보여줍니다.
* yum groupinstall 그룹 : 그룹패키지를 설치합니다.
* yum grouplist 그룹 : 그룹리스트에 관한 정보를 확인합니다.
* yum groupremove 그룹 : 그룹리스트에 관해 삭제합니다.
* yum help : yum의 도움말을 확인합니다.
* yum info 그룹 또는 패키지 : 패키지 또는 그룹의 패키지를 자세하게 확인합니다.
* yum install 패키지 : 시스템으로 패키지의 Install 을 실시합니다.
* yum list : 서버에 있는 그룹 및 패키지의 리스트를 보여줍니다.
* yum localinstall 패키지 : 로컬에 설치합니다.
* yum makecache : 캐쉬를 다시 올립니다.
* yum provides FilePath명 : 파일이 제공하는 패키지 정보 출력합니다.
* yum reinstall 패키지 : 패키지를 재인스톨 합니다.
* yum update 패키지 : 패키지를 업데이트합니다.
* yum upgrade 패키지 : 패키지를 업그레이드 합니다.
* yum search 키워드 : 키워드로 시작하는 패키지를 검색할수 있습니다.

# 예시 
yum list installed 패키지명: 패키지가 설치여부를 확인합니다.
yum install 패키지명1 패키지명2 : 여러개 패키지를 모두 설치합니다.
yum list all: 설치가 가능한 모든 패키지 목록 출력합니다.
yum list updates: 업데이트 목록을 보여줍니다.

# 옵션 
-h, --help : 해당 명령어의 도움말을 보여주고 실행이 종료됩니다.
-t, --tolerant : 에러를 자동으로 잡아서 설치합니다.
-C, --cacheonly : 캐시를 업데이트 하지 않고 전체 시스템 캐시 실행합니다.
-c [config file], --config=[config file] : 파일 위치를 알려줍니다.
-R [minutes], --randomwait=[minutes] : 최대치의 명령어 실행시 기다립니다.
-d [debug level], --debuglevel=[debug level] : 최종 결과를 디버깅합니다.
--showduplicates : 중복요소를 보여줍니다.
-e [error level], --errorlevel=[error level] : 결과 중 에러를 보여줍니다.
--rpmverbosity=[debug level name] : rpm에서 결과물을 디버깅합니다.
--version : Yum 버전을 보여주고 실행이 종료됩니다.
-y, --assumeyes : 모든 물음에 예를 진행합니다.
-q, --quiet : 모든 작업이 종료됩니다.
-v, --verbose : 작업을 장황하게 합니다.
--installroot=[path] : root권한으로 path위치에 인스톨을 진행합니다.
--enablerepo=[repo] : 1개 이상의 저장소 위치에 저장시킵니다.
--disablerepo=[repo] : 1개 이상의 저장소 위치에 저장시키지 않습니다.
-x [package], --exclude=[package] : 패키지 이름을 제외시킵니다.
--disableexcludes=[repo] : 이름으로 플러그인을 설치를 중단합니다.
--obsoletes : 오래된 패키지는 업데이트를 하는 동안 적절히 삭제 및 교체됩니다.
--noplugins : yum plugin이 없도록 합니다.
--nogpgcheck : gpg signature를 불가능하게 합니다.
--skip-broken : 문제 있는 패키지는 자동으로 스킵해서 넘어갑니다.
--color=COLOR : 컬러가 사용되었을 때 조정합니다.
--releasever=RELEASEVER : $releasever의 값을 yum config와 repo파일에서 조정합니다.
--setopt=SETOPTS : 임의로 config와 repo 옵션값을 지정합니다.
--disablepresto : Presto 플러그인을 중단하고 deltarpm을 다운로드 받지 않습니다.

```

#### 리눅스 service 와 systemctl 명령어 차이 
```
# 결론 
centos 6이전 버전은 service 구문으로 , 
centos 7이후 버전은 systemctl 구문으로 제어함

7버전에서 service 구문수행시 systemctl로 redirecting되어 실행됨 

```

#### docker 설치 
```
-참고사이트1 : http://pyrasis.com/Docker/Docker-HOWTO
-참고사이트2 : https://firework-ham.tistory.com/64?category=887728

# 192.168.174.47 클라우드 서버에서 테스트 진행 

$ cat /etc/*release              // OS 확인 

-----------------------------------------------------
CentOS Linux release 7.8.2003 (Core)
NAME="CentOS Linux"
VERSION="7 (Core)"
ID="centos"
ID_LIKE="rhel fedora"
VERSION_ID="7"
PRETTY_NAME="CentOS Linux 7 (Core)"
ANSI_COLOR="0;31"
CPE_NAME="cpe:/o:centos:centos:7"
HOME_URL="https://www.centos.org/"
BUG_REPORT_URL="https://bugs.centos.org/"

CENTOS_MANTISBT_PROJECT="CentOS-7"
CENTOS_MANTISBT_PROJECT_VERSION="7"
REDHAT_SUPPORT_PRODUCT="centos"
REDHAT_SUPPORT_PRODUCT_VERSION="7"

CentOS Linux release 7.8.2003 (Core)
CentOS Linux release 7.8.2003 (Core)
-----------------------------------------------------


$ sudo yum install -y wget       // wget 없으면 설치
# yum install -y wget            // root 인 경우 

$ rpm -qa | grep wget           // wget 설치 확인 
$ sudo wget -qO- https://get.docker.com/ | sh 


$ sudo docker rm `sudo docker ps -aq`
$ sudo docker rmi hello-world

# yum list installed | grep docker
---------------------------------------------------------------------------------
containerd.io.x86_64                 1.4.6-3.1.el7             @docker-ce-stable
docker-ce.x86_64                     3:20.10.7-3.el7           @docker-ce-stable
docker-ce-cli.x86_64                 1:20.10.7-3.el7           @docker-ce-stable
docker-ce-rootless-extras.x86_64     20.10.7-3.el7             @docker-ce-stable
docker-scan-plugin.x86_64            0.8.0-3.el7               @docker-ce-stabl
---------------------------------------------------------------------------------

//삭제해버리기
#  yum erase containerd.io.x86_64 docker-ce.x86_64 docker-ce-cli.x86_64 docker-ce-rootless-extras.x86_64 docker-scan-plugin.x86_64


// centos7 
# yum install -y docker 
# service docker start
   Redirecting to /bin/systemctl start docker.service            // 맞네 systemctl 로 redirect되네
# rpm -qa | grep docker 
# systemctl status docker       // docker 실행상태 확인
# chkconfig docker on           // 부팅시 자동 재시작 등록 


# docker -v          // docker 버전 확인 
   Docker version 1.13.1, build 7d71120/1.13.1

// ubuntu 컨테이너 생성
# docker search ubuntu           // docker hub에서 ubuntu 이미지 검색 
# docker pull ubuntu:latest      // 최신 우분투 이미지 내려받기 
# docker images                  // 내려받은 docker 이미지 목록 출력
# docker run -i -t --name hello ubuntu /bin/bash      // centos 와 독립된 우분투 os 컨테이너 생성됨 
                         docker명

# cat /etc/issue            // 우분투 버전확인 
# exit                      // 우분투 컨테이너 빠져나옴 

# docker ps -a  또는 docker ps           // 모든 docker 컨테이너 목록 출력
# docker start hello           //hello docker 컨테이너 시작
# docker restart hello         //hello docker 컨테이너 재시작
# docker stop hello            //hello docker 컨테이너 중지 
# docker rm hello              //생성된 hello docker 컨테이너 삭제 
# docker rmi ubuntu:latest     //이미지 삭제 ( docker rmi 이미지명:태그 형식 )

※ 우분투 Bash Sheel에서 exit 또는 ctrl + D 를 입력하게 되면 컨테이너가 정지됨 
※ Ctrl + P , Ctrl + Q 를 차례대로 입력하면 컨테이너를 정지하지 않고 빠져나옴 

# docker exec hello echo "Hello World"     // /bin/bash로 실행된 상태라서 외부에서 해당 방식으로 명령 실행가능 
  docker exec 컨테이너명 명령어 매개변수     // docker exec 명령은 이미 실행된 컨테이너에 명령어를 전달하여 실행가능하게 함
  docker exec -it {container_id or name} /bin/bash    //컨테이너 접속하기 명령어  




```

#### docker로 mariadb 설치해보기
```
※ 참고사이트 - https://logical-code.tistory.com/122

# docker pull mariadb         // mariadb 이미지 내려받기
# docker images               // 내려받은 이미지 확인 
-----------------------------------------------------------------------------------------
REPOSITORY          TAG                 IMAGE ID            CREATED             SIZE
docker.io/mariadb   latest              eff629089685        3 weeks ago         408 MB
-----------------------------------------------------------------------------------------

# docker run --name mariadb -d -p 3306:3306 -e MYSQL_ROOT_PASSWORD=mariadb mariadb

--name mariadb : 컨테이너 이름을 mariadb로 지정 
-d : 컨테이너를 백그라운드로 실행 
-p 3306:3306 : 호스트와 컨테이너 간의 포트연결 , 호스트에서 3306 포트 접속시 컨테이너 3306 포트로 포워딩 됨 
-e : 환경변수 설정 
MYSQL_ROOT_PASSWORD=mariadb : 컨테이너 내 환경변수 설정, root 계정의 패스워드를 mariadb로 지정. 
mariadb : 다운받은 이미지명

// MariaDB 접속 
# docker exec -it mariadb /bin/bash
# mysql -uroot -p         //비밀번호는 mariadb 

// MariaDB Characterset 설정 
> status 
-----------------------------------------------------------------------------------------
...
Server:                 MariaDB
Server version:         10.5.10-MariaDB-1:10.5.10+maria~focal mariadb.org binary distribution
Protocol version:       10
Connection:             Localhost via UNIX socket
Server characterset:    utf8mb4
Db     characterset:    utf8mb4
Client characterset:    latin1
Conn.  characterset:    latin1
...
-----------------------------------------------------------------------------------------
> exit 
> vi /etc/mysql/my.cnf 또는 vim /etc/mysql/my.cnf   // 근데 vi 나 vim이 안깔려 있음 

// OS 확인 
# cat /etc/issue             // 우분투 os 에 mariadb가 깔려있음 ..

// vi, vim 설치 
# apt-get update
# apt-get upgrade 
# apt-get install vim 
# apt install -y net-tools //netstat 설치 
  >> netstat -tnlp     // 3306 포트 올라 온 거 확인됨 
# apt-get install -y wget // 우분투 wget 설치 
  >> which wget        // 설치 확인   
# apt-get install -y curl  // 우분투 curl 설치 

// character-set 환경설정 
# vi /etc/mysql/my.cnf 또는 vim /etc/mysql/my.cnf 
-----------------------------------------------------------------------------------------
[client]
default-character-set = utf8mb4

[mysql]
default-character-set = utf8mb4

[mysqld]
collation-server = utf8_unicode_ci
init-connect = 'SET NAMES utf8'
character-set-server = utf8
-----------------------------------------------------------------------------------------
// 저장 후 나가기 ( :wq! )

// docker 재시작 후 환경 확인 
# exit  또는 ctrl + pq  로 centos 로 복귀  // exit 하면 docker 종료하고 뒤에꺼는 bash shell 실행중 에서 그냥 나감 
# docker ps -a 
# docker restart mariadb 
# docker ps 
# docker exec -it mariadb /bin/bash
# mysql -uroot -p //mariadb
> status 
-----------------------------------------------------------------------------------------
Server:                 MariaDB
Server version:         10.5.10-MariaDB-1:10.5.10+maria~focal mariadb.org binary distribution
Protocol version:       10
Connection:             Localhost via UNIX socket
Server characterset:    utf8mb4
Db     characterset:    utf8mb4
Client characterset:    utf8mb4
Conn.  characterset:    utf8mb4
-----------------------------------------------------------------------------------------

```

#### docker 와 host 간의 파일 전송  ( docker cp 명령어 )
```
1. host 에서 docker container로 파일 전송 
   > host os에서 docker container로 파일 보내기 
   > docker cp /path/test.txt 도커명:/path/test.txt
   > docker cp /home/centos/test.txt mariadb:/home/text.txt

2. docker container에서 host로 파일 전송
   > ctl + pq 한 뒤 host os 에서 명령어 실행하여 docker container 파일 내려받기 
   > docker cp 도커명:/path/test.txt /path/test.txt    
   > docker cp mariadb:/home/text.txt /home/centos/test2.txt

```

#### mariadb 에 db 생성 , 계정 생성 , dump import , db 툴 접속 테스트 
```
//mariadb 접속
# docker exec -it mariadb /bin/bash
# mysql -uroot -p //비밀번호 : mariadb

// 1. db 생성
> create database chungnam_docker default character set utf8;

// 2. 계정 생성 , 권한 부여
> create user 'chungnam'@'%' identified by 'Ubitec1495#';
> GRANT ALL privileges ON chungnam_docker.* TO chungnam@'%';
> create user 'chungnam'@'localhost' identified by 'Ubitec1495#';
> GRANT ALL privileges ON chungnam_docker.* TO chungnam@'localhost';

// 3. 권한 확인
> SHOW GRANTS FOR 'chungnam'@'%';
> SHOW GRANTS FOR 'chungnam'@'localhost';


// db 백업파일 가져오기 
> docker cp /home/centos/chungnam0524.sql mariadb:/home/chungnam0524.sql
또는 윈도우인 경우 
> docker cp ./chungnam_u2_210706.sql mariadb:/home/chungnam_u2_210706.sql


// docker 접속 
> docker exec -it mariadb /bin/bash
> mysql -uroot -p chungnam_docker < chungnam0524.sql
> mysql -uroot -p         // 비밀번호 mariadb 
> use chungnam_docker     // 확인 

// DB 툴 켜서 접속 확인해보기 ( 방화벽 열려있는지 tcping, ping, telnet 으로 확인! )
// 연결 확인됨 !! 

```


#### window os에서 docker로 centos 설치해서 사용하기 
```
참고사이트 - https://hello-bryan.tistory.com/160
```


#### ubuntu 시스템 로그 확인 
``` 
$ sudo cat /var/log/messages | grep -i "ERROR"
```

#### ec2 instance 재부팅 이력 확인 
[https://aws.amazon.com/ko/premiumsupport/knowledge-center/ec2-instance-automatic-reboot-cause/](https://aws.amazon.com/ko/premiumsupport/knowledge-center/ec2-instance-automatic-reboot-cause/)

``` 
// 재부팅 기록 보기 
# grep reboot /home/*/.bash_history
# grep reboot /root/.bash_history
# history | grep -i reboot
# history | grep -i init
# last reboot
```

#### 참고 
[https://harryp.tistory.com/880](https://harryp.tistory.com/880 '리눅스 시스템 로그 파일 정리')
[https://etloveguitar.tistory.com/m/20](https://etloveguitar.tistory.com/m/20 'Redirection 프로그램 실행 로그저장')
