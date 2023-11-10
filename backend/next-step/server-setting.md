root .bashrc

export JAVA_HOME=/usr/lib/jvm/java-1.11.0-openjdk-amd64
export MAVEN_HOME=/usr/share/maven
export PATH=$PATH:$JAVA_HOME/bin:$MAVEN_HOME/bin

export LANG=ko_KR.UTF-8
export LC_ALL=ko_KR.UTF-8
export LANGUAGE=ko_KR:ko:en_US:en

service ssh start

// 환경 변수 설정 확인
$ echo $JAVA_HOME
$ echo $MAVEN_HOME

mvn -version
java -version





13  apt install -y git maven
153  apt-get install -y openjdk-11-jdk        
218  apt-get install -y maven
255  apt-get install -y iproute2
264  apt list --installaed | grep openssh



sudo apt-get install -y ufw 


https://webdir.tistory.com/206

강의 영상1. https://www.youtube.com/watch?v=dWGzApCuF9M

1. 계정 추가 및 sudo 권한 할당 
- adduser명령어 
- sudo 권한 추가 , root 권한과 같이 다른 권한으로 명령을 실행할때 필요하다

// 내 로컬에서 좀더 쉽게 접속하도록 
$ sudo vi /etc/hosts 

아이피 jwp // 로 지정후 저장 

$ ssh -i "jwp.pem" ubuntu@jwp  // 접속

// 계정 추가 및 sudo 권한 할당하기 ubuntu sudoer 추가로 검색해서 문제 해결 

=====================================================
2. 한글 이슈 해결 UTF-8  // 시스템 전체 적용
$ sudo locale-gen ko_KR.EUC-KR ko_KR.UTF-8
$ sudo dpkg-reconfigure locales

// 내 계정에 대한 로컬 설정 
$ vi .bash_profile
LANG="ko_KR.UTF-8"
LANGUAGE="ko_KR:ko:en_US:en"

$ source .bash_profile   // 설정 반영 , 로그아웃 하거나 
$ env        // 설정 확인 

=====================================================
3. JDK, 메이븐 설치  -- 공식 사이트에서 링크 주소를 복사 
- "ubuntu wget jdk 1.8 install" 로 검색 후  설정 해야 함 // 라이센스 승인 부분 필요
https://www.digitalocean.com/community/tutorials/how-to-manually-install-oracle-java-on-a-debian-or-ubuntu-vps

// 수동 설치하는 방법
$ mkdir download

$ wget --header "Cookie: oraclelicense=accept-securebackup-cookie" http://download.oracle.com/otn-pub/java/jdk/8u5-b13/jdk-8u5-linux-x64.tar.gz(다운 로드 링크 주소만 변경)

$ gunzip 파일명    // tar.gz에서 gz를 품

$ tar -xvf 파일명

$ mv 폴더명/ ..    // 홈 디렉 토리로 이동

// 심볼릭 링크 설정
$ ln -s jdk폴더/ java      // 심볼릭 링크 설정하여 java로 사용, 버전업시에도 시스템 환경 변수 설정 수정안하도록

$ vim .bash_profile

export JAVA_HOME=~/java
export MAVEN_HOME=~/maven
export PATH=$PATH:$JAVA_HOME/bin:$MAVEN_HOME/bin

$ source .bash_profile 
$ env      // 설정 확인 

-- maven 설치 https://maven.apache.org/download.cgi

$ wget 링크 
$ gunzip 파일
$ tar -xvf 파일 
$ mv 폴더명/ ..      // home 디렉토리로 이동 

$ ln -s apache-maven-3.3.9/ maven     // 이것도 심볼릭 링크로 설정 쉽게 변경 가능하도록 

$ mvn -version 
============================================================

강의 영상2. https://www.youtube.com/watch?v=N8iLAuAo-Qw

-깃 설치 
$ sudo apt-get install -y git
$ git --version 
$ mkdir repositories
$ cd repositories 
$ git clone https://github.com/slipp/web-application-server.git   // web-application-server를 배포해본다
// 디렉토리 이동후 

$ mvn clean package 
$ ll

$ java -cp target/classes:target/dependency/* webserver.WebServer 8090 &
-- & : 백그라운드(데몬) 실행
-- ":" : 여러 경로 지정해서 war, jar 파일 찾아 실행 


$ docker exec -it ubuntu1 ifconfig 
-- 172.19.0.11:8090 으로 접속하면 됨

-- 접속 되지 않음 .. 방화벽 문제
$ sudo apt-get install -y ufw
- 참고 https://webdir.tistory.com/206

sudo ufw enable 
sudo ufw allow ssh
sudo ufw allow 80/tcp
sudo ufw allow 8090/tcp       // 우선 포트 접속, 정상 접속확인
sudo ufw status  

================================================================
톰캣 설치 강의 - https://www.youtube.com/watch?v=ZsiO27LeW34

톰캣 8버전 다운로드 
https://tomcat.apache.org/download-80.cgi
https://archive.apache.org/dist/tomcat/tomcat-8/

$ wget https://dlcdn.apache.org/tomcat/tomcat-8/v8.5.95/bin/apache-tomcat-8.5.95.tar.gz

$ tar -xvf 파일명

$ mv 폴더명 ..      // home 디렉토리로 이동 

$ ln -s 폴더명 tomcat      // 심볼릭 링크

$ vim .bashrc
export JAVA_HOME=/usr/lib/jvm/java-1.11.0-openjdk-amd64
export MAVEN_HOME=/usr/share/maven
export TOMCAT_HOME=~/tomcat
export PATH=$PATH:$JAVA_HOME/bin:$MAVEN_HOME/bin:$TOMCAT_HOME/bin

// conf/server.xml에서 8080을 8090으로 변경후 bin/startup.sh 실행 
// logs/catalina.out 확인 
// 종료는 shutdown.sh나 ps -ef | grep java로 kill -9 $PID 해버리는 방법있음

=======================================================================

톰캣 디렉토리 구조 설명 https://www.youtube.com/watch?v=9Rr4gMRyUtQ

bin : 서버 시작/종료, catalina.sh 옵션 설정도 가능
logs : 로그파일
conf : 환경 설정 server.xml, web.xml
webapps : 웹 애플리케이션을 독립적으로 관리 가능하도록 서비스 지원 (기본 5개 폴더있음, 기본 ROOT 폴더 접속됨)
          >> server.xml에서 따로 설정도 가능한걸로 아는데 webapps에서도 가능했구나 
work: /Catalina/localhost/examples  샘플 서비스에 대한 컴파일 결과가 생성되는 폴더 (class, java)

*배포의 경우 아래 두가지 방식으로 설정 가능
1) *.war파일 
2) 압축 푼 디렉토리 

=================================================================
Tomcat 서버에 웹 애플리케이션 빌드 및 배포 
https://www.youtube.com/watch?v=bzM1WL4qdoA

jwp-basic 프로젝트 내려받음
// repositorie 디렉토리 이동
$ git clone https://github.com/slipp/jwp-basic.git

$ cd jwp-basic

$ git checkout step15-di-framework-completed

$ git branch -vv        // 현재 브랜치별로 최종 로그 확인가능

*maven skip test 검색
https://maven.apache.org/plugins-archives/maven-surefire-plugin-2.12.4/examples/skipping-test.html

$ mvn clean package -DskipTests
또는 $ mvn clean pacakge -Dmaven.test.skip=true


**기본적으로 톰캣 실행시 webapps에 ROOT 폴더가 되는데 그걸 지우고 war파일을 ROOT로 이름 변경해서 옮겨서 실행시키네 
>> 디렉토리를 옮긴단다

$ ps -ef | grep tomcat 
$ kill -9 $PID
$ cd ~/tomcat/webapps
$ rm -rf ROOT
//$ mv target/jwp-basic ~/tomcat/webapps/ROOT
$ mv ~/repositories/jwp-basic/target/jwp-basic ~/tomcat/webapps/ROOT
실행하고 로그 확인 // war파일 말고 빌드했을때 jwp-baisc 결과물 폴더을 옮겨서 실행하는거네 ****

확인 http://172.19.0.11:8090/
==============================================================

쉘 스크립트를 활용한 배포 자동화 
https://www.youtube.com/watch?v=U7tZnEiYJyE

$ mkdir scripts
$ cd scripts
$ vim deploy.sh

#!/bin/bash      -- 사용 쉘 지정
REPOSITORY_DIR=~/repositories/jwp-basic
TOMCAT_DIR=~/tomcat

cd $REPOSITORY_DIR
pwd

// 이력 최신화,  maven build
git pull
mvn clean package -DskipTests

// 톰캣 종료
$TOMCAT_DIR/bin/shutdown.sh

// 톰캣 서비스 디렉토리에 ROOT 삭제
rm -rf $TOMCAT_DIR/webapps/ROOT
pwd

// maven 빌드 결과인 jwp-basic 디렉토리를 이동 시킴
cd target
pwd

mv $REPOSITORY_DIR/target/jwp-basic $TOMCAT_DIR/webapps/ROOT

// 톰캣 재시작
$TOMCAT_DIR/bin/startup.sh

// 로그 확인 
tail -500f $TOMCAT_DIR/logs/catalina.out


======================================================

심볼릭 링크를 활용한 배포 자동화 
https://www.youtube.com/watch?v=UqocnEIX-mA


$ mkdir -p ~/releases/jwp-basic           // 프로젝트때문에 하나더 분리 
$ cd ~/releases/jwp-basic 	// 아래 내용만 추가


C_TIME=$(date +%s)
echo "time : $C_TIME"
mv $REPOSITORY_DIR/target/jwp-basic $RELEASE_DIR/$C_TIME

// 중략..

ln -s $RELEASE_DIR/$C_TIME $TOMCAT_DIR/webapps/ROOT
             대상                                       심볼릭 링크


================================================================
배포한 소스 코드를 rollback 하는 쉘 스크립트 구현 
https://www.youtube.com/watch?v=7OSzN16FqCw

1) 톰캣 서버 종료 
2) 배포 디렉토리에서 원복할 디렉토리(현재 서비스 중인 디렉토리 직전 디렉토리)를 찾는다. 
  - 구글에서 "shell script find all directories sort date"와 같은 키워드를 입력해 배포 디렉토리를 시간 역순으로 구한다. > ls -1t (time)
       > ls -1tr (reverse)   
  - 시간 역순으로 구한 디렉토리 목록에서 \n(new line)을 기준으로 분리해 배열로 만든다. 
    구글에서 "shell script split string to array"와 같은 키워드를 입력한다. 
  - 배포 디렉토리 2개 미만이면 "원복할 디렉토리가 없다"는 메시지 출력하고 배포 스크립트 종료 
    - if[ "${#REVISIONS[@]}" -lt 2 ]; then exit   // 2개 less than 이면 종료 
  - 배포 디렉토리 2개 이상이면 2번째 디렉토리 목록을 반환 ${REVISIONS[1]}
  
3) TOMCAT_HOME/webapps/ROOT 심볼릭 링크 삭제 
4) 앞에서 찾은 원복할 디렉토리를 TOMCAT_HOME/webapps/ROOT 심볼릭 링크로 생성 
5) 톰캣 서버 재시작

*참고. chatgpt 
디렉토리를 유지하고 최신 5개만 남기려면 스크립트를 작성하여 주기적으로 실행되도록 할 수 있습니다. 이를 위해 Bash 스크립트와 `find`, `sort`, `tail`, `xargs` 등의 명령어를 사용할 수 있습니다.

예를 들어, 다음과 같은 Bash 스크립트를 사용하여 최신 5개의 디렉토리를 제외한 나머지를 삭제할 수 있습니다:

```bash
#!/bin/bash

# 디렉토리 경로
directory="/path/to/your/directory"

# 최신 5개 디렉토리 가져오기
latest_directories=$(ls -t "$directory" | head -n 5)

# 모든 디렉토리 가져오기
all_directories=$(ls -d "$directory"/*/)

# 최신 5개를 제외한 나머지 디렉토리 삭제
echo "$all_directories" | grep -v "$latest_directories" | xargs rm -r
```

grep -v 옵션이 invert match : 매치에 맞지 않는 행만 선택

주의할 점:
- `/path/to/your/directory` 부분을 실제 디렉토리 경로로 바꿔주세요.
- 이 스크립트는 최신 5개를 제외한 모든 디렉토리를 삭제합니다. 필요에 따라 스크립트를 수정하여 적절히 사용하세요.
- 스크립트를 주기적으로 실행하려면 cron 또는 systemd 타이머 등을 사용할 수 있습니다.

이 스크립트를 사용하면 지정된 디렉토리에서 최신 5개를 제외한 모든 디렉토리를 삭제할 수 있습니다.


`xargs`는 주로 명령어 라인 인자(argument)를 빌드하거나 표준 입력에서 읽은 데이터를 기반으로 명령을 실행하는 데 사용되는 명령어입니다. 주로 파이프(|)를 통해 앞 명령어의 출력을 `xargs`로 전달하고, `xargs`는 해당 출력을 인자로 받아 다음 명령어에 전달하는 역할을 합니다.

간단한 예를 들어보겠습니다. 아래의 명령어는 현재 디렉토리의 모든 `.txt` 파일을 `cat` 명령으로 읽어들여 출력합니다.

```bash
ls *.txt | xargs cat
```

여기서 `ls *.txt`는 현재 디렉토리에서 확장자가 `.txt`인 파일을 나열하고, `xargs cat`은 이 파일 목록을 `cat` 명령어의 인자로 전달하여 파일 내용을 출력합니다.

`xargs`의 주요 사용 사례 중 하나는 명령어의 인자가 너무 많아서 한 번에 처리하기 어려울 때입니다. `xargs`는 입력을 적절한 크기로 나누어 여러 명령어 호출을 수행하도록 도와줍니다.

또한, `xargs`는 명령어에 인자를 전달하는 데 유용한 옵션들을 가지고 있습니다. 일반적으로 `xargs`를 사용할 때는 `-n`, `-I`, `-d` 등의 옵션을 함께 활용합니다. -=---- 반복문 같은 느낌이다*
=====================================================================================
4-1.
- nginx 설치 및 설정 디렉토리, 파일 설명 
- https://www.youtube.com/watch?v=7GlCo6RHBns

// nginx 설치 옛날이라 직접 해야 겠다 
$ curl http://localhost           // 호출 테스트 ,nginx html 파일 확인

*주요 디렉토리
/etc/nginx/sites-available
/etc/nginx/sites-enabled

==========
4-2. nginx와 tomcat 서버 연동 
https://www.youtube.com/watch?v=QRqm5Xlw1HQ

// 최신 브랜치로 내려받고 배포 
$ jps -v        // 현재 운영 체제에 실행중인 자바 프로세스 모두 확인 가능 

* 연동 시작 
- "ubuntu nginx tomcat 연동" 검색

$ cd /etc/nginx/sites-available 
$ sudo vim jwp-basic      // 설정 복사해서 proxy_pass에 바로 넣어도 되고 upstream tomcat 변수명 지정가능 
$ cd ../sited-enabled
$ sudo rm -rf default 
$ sudo ln -s ../sites-available/jwp-basic jwp-basic
// nginx 요청을 톰캣으로 위임하게 된다.

$ sudo nginx -s reload // 서버 재시작 

// 브라우저 새로 고침(refresh)

// 톰캣의 8090 방화벽 설정 제거하기!

==========
4-3. nginx에 점검 페이지 설정 
https://www.youtube.com/watch?v=XEaqMl7eZT4

배포시 점검 페이지로 변경하고, 배포 완료하면 다시 스왑

1) 점검 페이지 임의 탬플릿 로컬 다운받아서 원격 서버로 올림 
$ scp 파일 ubuntu@jwp        // jwp 호스트 설정했으면 
2) mv 파일 repositores
3) 압축풀기
$ unzip 파일명 
4) 점검 페이지 경로 복사 
5) nginx 설정 이동 
$ cd /etc/nginx/sites-available 
$ sudo cp default jwp-basic-pm      // pm이라고 점검페이지라 하는듯
5) vim으로 열어서 root를 아까 탬플릿 경로로 변경해줌 
6) 테스트
$ sudo rm -rf jwp-basic
$ sudo ln -s ../sites-avaialable/jwp-basic-pm jwp-basic 
$ sudo nginx -s reload 
7) 확인

// 이제 배포시 점검페이지랑 배포시 심볼릭 링크만 바꿔주면 된다! shell script로 
// 좋은 사용자 경험 제공하므로 방식으로 습득하기

==========
4-4. 쉘 스크립트를 활용한 배포 및 점검 자동화 
https://www.youtube.com/watch?v=ZeY0xpnXF7w

**** shell script를 그냥 실행시킴..

1) deploy.sh 수정 

NGINX_HOME=/etc/nginx

cd $NGINX_HOME/sites-enabled
sudo rm -rf jwp-basic 
sudo ln -s ../sites-available/jwp-basic-pm jwp-basic
sudo nginx -s reload

2) 점검 -> 서비스 페이지로 swap 
$ cp deploy.sh deploy-finish.sh
NGINX_HOME=/etc/nginx

cd $NGINX_HOME/sites-enabled
sudo rm -rf jwp-basic 
sudo ln -s ../sites-available/jwp-basic jwp-basic
sudo nginx -s reload

echo "server start finished"

// 특정 url 요청을 해서 원하는 결과가 오는지 확인 
// 원복을 수동으로 하는데 자동화 가능 (직접해보기)




