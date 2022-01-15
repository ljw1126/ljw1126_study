# Server Setting Guide
- 사용기술 : ubuntu, nginx, spring boot, mysql, aws ec2 
- ubuntu 서버에 build한 *.jar 결과물을 실행시키고 접속시키는 과정에 대한 내용 정리
  - spring boot 자체에 임베디드 톰캣 내장되어 있고, nginx를 web 서버 역할을 함

## ![Alt text](/images/sample_img12.jpeg)
---

## ubuntu 서버 기본 셋팅 
```
1. UTC -> KST로 시간대 변경 
    $ timedatectl         // 확인시 UTC로 기본 잡혀 있음 
    $ sudo rm /etc/localtime 
    $ sudo ln -s /usr/share/zoneinfo/Asia/Seoul /etc/localtime 
    $ date          // KST로 변경된 걸 확인 
    $ timedatectl   // 마찬가지로 Local time이 KST로 변경된 걸 확인 가능 

2. JDK 설치 
- 해당 과정에서는 open jdk 11 설치해서 사용함
    $ sudo apt-get install openjdk-11-jdk     //설치 진행 
    $ java -version                  // 설치 버전 확인

3. netstat 설치 
- 프로세스가 몇 번 포트로 올라왔는지 확인하기 위해 사용(필수)
    $ sudo apt install net-tools  // 설치 
    $ netstat -tnlp               // 항상 기억해 둘 것

4. nginx 설치 
- apache 처럼 web server 동작을 하는 것으로 보임
- 설치 후 실행시 80포트가 올라와야 함 

    $ sudo apt-get install nginx 
    $ sudo service nginx start  // 설치 후 바로 기동 
    $ netstat -tnlp             // :::80 으로 올라온게 nginx 
    $ nginx -v                  //nginx 버전 확인 , nginx version: nginx/1.18.0 (Ubuntu)

- 설치 ubuntu 서버 ip로 브라우저 통해 접속시 nginx 기본 페이지가 출력됨 ( default )
- nginx 기본 명령어는 다음과 같다.

    $ sudo service nginx status    // 상태 확인
    $ sudo service nginx restart   // 재시작
    $ sudo service nginx stop     // 중지
    $ sudo sservice nginx reload  // 설정 다시 반영 

5. nginx , 프로젝트 연결 설정 
- 요구사항 1. 서버 ip로 접속시 nginx 기본 페이지로 이동하라.
- 요구사항 2. 주어진 도메인 주소 접속시 파일럿 프로젝트 페이지로 이동하라. 

    $ cd /etc/nginx/sites-available/
    $ sudo vi pilot_board_project.conf          //아래 내용 작성 후 저장 (esc 누르고 저장하고 나가기 :wq!)

/---------------------------------------------------------------/
# 1. 요구사항에 주어진 도메인 주소로 접속시 8080포트 프로세스로 이동함
server {
    listen 80;
    listen [::]:80;

    server_name 요구사항에주어진도메인주소

    location / {
         proxy_pass http://localhost:8080;
         proxy_set_header X-Real-IP $remote_addr;
         proxy_set_header X-Forwarded-For $proxy_add_x_forwarded_for;
         proxy_set_header Host $http_host;
    }
}

# 2. default(ip주소)로 접속시 nginx 기본 페이지로 이동하게 됨 
server {
        listen 80 default_server;
        listen [::]:80 default_server;

        root /var/www/html;

        index index.html index.htm index.nginx-debian.html;

        server_name _;

        location / {
                try_files $uri $uri/ =404;
        }
    }
}
/---------------------------------------------------------------/
    $ sudo ln -s /etc/nginx/sites-available/pilot_board_project.conf  /etc/nginx/sites-enabled      // 심볼릭 링크 생성 
    $ cd /etc/nginx/sites-enabled 
    $ ls -l
    $ sudo rm default   // sites-available폴더에 default 파일이 존재하기 때문에 필요하면 똑같이 심볼릭 링크 생성하면 됨 

- 이제 spring boot 프로젝트 기동 후 nginx start하면 접속 됨 
- 본인 컴퓨터에 hosts 파일을 찾아 가상 호스트 등록하고 접속할 것 ! 
    $ sudo service nginx restart 또는 sudo service nginx reload // nginx 재시작 

6. gradle build 결과물 업로드 후 실행 
- spring boot 로 작업시 gradle build하게 되면 *.jar 파일 생성됨 

    # 터미널로 build할 경우 
        $ cd 프로젝트루트경로        // git bash나 인텔리제이 terminal 사용 
        $ ./gradlew clean build -x test     // -x test 옵션은 test 폴더 제외 뜻함

    # 인텔리제이(IDE)에서 gradle build 할 경우 
    - build configuration에 run 입력창에 buil -x test로 수정해서 돌리면 됨!

    # build 결과물 생성 경로 
    - /프로젝트경로/build/libs 에 *.jar 파일이 생성되어 있음
    - 파일명 또한 환경설정 통해 수정 가능 

    # ubuntu 서버에 프로젝트 설치할 폴더 생성 
        $ cd /home
        $ sudo mkdir pilot_board_project          // 폴더 생성
        $ sudo chown -R ubuntu:ubuntu pilot_board_project   // root 아니면 접근이 안되니 폴더를 ubuntu소유로 변경 
        $ ls -al  // 확인 

    # sftp 툴로 프로젝트.jar 파일 업로드 후 실행 (에러 해결은 다른 곳에 기재)
        ## 1. 실행 
        $ java -Dspring.profiles.active=real -jar pilot_board_project-1.0-SNAPSHOT.jar 2>&1 > catalina.log&   //catalina.log파일 생성해서 로그 남기면서 실행
        $ tail -f catalina.log        // 실행 로그 확인  

        ## 2. 종료 
        $ ps -ef | grep pilot_board_project-1.0-SNAPSHOT.jar
        $ sudo kill -9 PID 

nginx 가동하고 접속확인! (안될시 catalina.log 에러로그 확인 및 처리 후 재기동 ! )
끝.
```

## 발생 에러 기록 
```
[에러1] Caused by: java.sql.SQLException: No database selected
- h2(인 메모리 테스트 db)에서 mysql (실제 db) 전환시 에러 로그 출력 
- (해결)driverClassName 수정 후 정상 동작
    spring.datasource.url=jdbc:mysql://디비서버주소:3306/디비명?characterEncoding=UTF-8
    spring.datasource.driverClassName=com.mysql.cj.jdbc.Driver      //com.mysql.jdbc.Driver 는 이제 사용안함(디클리어)
    spring.datasource.username=유저명
    spring.datasource.password=비밀번호
    spring.jpa.database-platform=org.hibernate.dialect.MySQL5InnoDBDialect

[에러2] The last packet sent successfully to the server was 0 milliseconds ago. The driver has not received any packets from the server. No appropriate protocol (protocol is disabled or cipher suites are inappropriate).
- 인텔리제이 database 탭에서 aws mysql db 서버 연결시 에러 발생 
- (해결)aws mysql 드라이브가 따로 있어서 설치하면 됨 
- 이슈 대한 내용 
   https://youtrack.jetbrains.com/issue/DBE-13313?_ga=2.267697870.1378670194.1642049654-763682932.1641861757

[에러3] gradle build시 test폴더 컴파일 에러로 build 안되는 문제 
- test 폴더 제외하고 실행할 수 있도록 edit configuration에 옵션 추가하거나 터미널 명령어로 실행하면됨 
    # 터미널로 build할 경우 
        $ cd 프로젝트루트경로        // git bash나 인텔리제이 terminal 사용 
        $ ./gradlew clean build -x test     // -x test 옵션은 test 폴더 제외 뜻함

    # 인텔리제이(IDE)에서 gradle build 할 경우 
    - build configuration에 run 입력창에 buil -x test로 수정해서 돌리면 됨!  

[에러4] The last packet sent successfully to the server was 0 milliseconds ago. The driver has not received any packets from the server. 
- 실서버에서 프로젝트 실행시 로그 확인 및 유추한 결과 aws ec2랑 mysql 서버가 연결이 안된 것으로 판단되어 담당자 요청함 
    # 연결 확인 방법 
    > telnet 실디비url 3306     // mysql의 default포트는 3306

[에러5] javax.net.ssl.SSLHandshakeException: No appropriate protocol (protocol is disabled or cipher suites are inappropriate..
- (해결)datasource url에 option parameter가 추가 후 재시작시 정상 동작 확인
    # application-real.propertise 내용 중 'useSSL=true&enabledTLSProtocols=TLSv1.2' 옵션 추가
    spring.datasource.url=jdbc:디비서버:3306/디비명?characterEncoding=UTF-8&useSSL=true&enabledTLSProtocols=TLSv1.2

```

## 참고 
[https://velog.io/@damiano1027/Nginx-Nginx%EC%99%80-SpringBoot-%EB%82%B4%EC%9E%A5-Tomcat-%EC%97%B0%EB%8F%99 ](https://velog.io/@damiano1027/Nginx-Nginx%EC%99%80-SpringBoot-%EB%82%B4%EC%9E%A5-Tomcat-%EC%97%B0%EB%8F%99 '[Nginx] Nginx와 SpringBoot 내장 Tomcat 연동')