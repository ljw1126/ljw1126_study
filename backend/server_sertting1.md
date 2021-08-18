#Server Setting Guide1
![Alt text](/images/sample_img12.jpeg)

---

## [Apache 와 Apache Tomcat 비교]

|      |             Apache              |         Apache Tomcat          |
| ---- | :-----------------------------: | :----------------------------: |
| 구분 |               WEB               |    WAS ( WEB, WAS이기도 함)    |
| 차이 | 정적 데이터 처리(html, js, css) | 동적데이터 처리(jsp, 논리로직) |

> <https://giyatto.tistory.com/97>  
> <https://madplay.github.io/post/apache-tomcat-modjk>  
> <https://inma.tistory.com/109>

---

## [Apache Tomcat]

#### 1. 셋팅용 파일확인/가져오기

![Alt text](/images/server_setting/server_setting1.png)

```
# 보통 회사 192.168.174.202 서버에 /home/보면 필요한 파일확인
# ftp툴로 다운받아 넣거나 리눅스 scp 명령어로 가져가 사용하면 됨

// 1. ssh 툴 ( putty , MobaXterm )로 서버접속 후
> ssh root@192.168.174.202            //비밀번호는 문의
> 또는 ssh -l root 211.220.63.108
> cd /home/
> ll                       //파일 확인
> exit;                    //이전 원래 서버로 복귀

// 2. scp 명령어로 서버에서 필요한 파일 가져옴 (※해석 : 202번 서버에 있는 파일을 현재 위치[./]로 가져와라 )
> scp root@192.168.174.202:/home/apache-tomcat-7.0.50.tar.gz ./
> scp root@192.168.174.202:/home/jdk-7u75-linux-x64ssh.tar.gz ./
> scp root@192.168.174.202:/home/tomcat-connectors-1.2.48-src.tar.gz ./

```

#### 2. Apache Tomcat(통칭 톰캣) 설치

```
# 프로젝트 기본 디렉토리 모양에 따라 server 폴더를 만들고 그 안에 넣든, 특정 설치 경로에 추가하든 유도리 있게 하기

// 3. tomcat 압축풀기
> mkdir server                              //폴더생성
> cd server                                 //폴더이동
> tar -zxvf apache-tomcat-7.0.50.tar.gz     // 압축해제, apache-tomcat-7.0.50 폴더 생성됨
> rm -rf apache-tomcat-7.0.50.tar.gz       //압축파일 삭제
> mv apache-tomcat-7.0.50 tomcat7         //tomcat7으로 폴더명 변경
> cd tomcat7

# webapps 폴더 내용은 '삭제'하는게 좋음!!
  - 웹취약점 점검에 걸리는 대상
  ※ 가야대 프로젝트때, 미국에서 해당 폴더 내 파일로 타고 들어와 해킹툴 설치함

// 4. 기타 조치
> rm -rf webapps                          //삭제
> cd conf                                 //폴더이동
> vi server.xml                           //JNDI(선택), 포트, SSL(선택) 설정 잘 해주기




# 43번에 u2cms 테스트용 스키마 생성해둠 (2020-10-08)
<Context docBase="프로젝트 루트 경로" path="" reloadable="true" >
  <Resource
            auth="Container"
            cacheMaxSize="100000"
            cachingAllowed="true"
            driverClassName="com.mysql.jdbc.Driver"
            factory="org.apache.commons.dbcp.BasicDataSourceFactory" maxActive="50"
            maxIdle="50"
            name="jdbc/u2cms"
            password="Ubitec1495!"
            type="javax.sql.DataSource"
            url="jdbc:mysql://211.57.203.43:3306/exam_u2cms?zeroDateTimeBehavior=convertToNull" username="exam_u2cms"
            validationQuery="select 1"/>
</Context>


# 톰캣 JVM 메모리 설정, JDK 설정 관련
 - server_setting_guid2.md 의 [Tomcat 메모리 관리] 부분 참조

```

#### 3. Apache Tomcat 자동실행 서비스 등록하기 ( 선택 )

```
# 해당 AP서버 재시작시 톰캣도 자동 재실행 되도록 설정
>  vi /etc/systemd/system/tomcat.service    //아래 내용중 경로 유도리 있게 설정하기

-----------------------------------------------------------------------------------------------------------------
[Unit]
Description=Apache Tomcat 7
After=syslog.target network.target

[Service]
Type=forking

+ WorkingDirectory=/home/centos/tomcat7       //여기
/* 생략가능한듯
Environment=CATALINA_PID=/home/centos/tomcat7/temp/tomcat.pid
Environment="JAVA_HOME=/home/centos/jdk1.7.0_75"
Environment="CATALINA_HOME=/home/centos/tomcat7"
Environment='JAVA_OPTS=-Djava.awt.headless=true -Djava.security.egd=file:/dev/./urandom'
*/
User=root
Group=root

ExecStart=/bin/sh /home/centos/tomcat7/bin/startup.sh    //여기
ExecStop=/bin/sh /home/centos/tomcat7/bin/shutdown.sh    //여기

[Install]
WantedBy=multi-user.target

------------------------------------------------------------------------------------------------------------------------

# daemon 등록후 시작해보기
> systemctl daemon-reload        // 편집한 설정 파일(tomcat.service) 반영
> systemctl enable /etc/systemd/system/tomcat.service       //해당 서비스를 허용하겠다
> systemctl start tomcat          //서비스 구동
  또는 sudo systemctl start tomcat.service
> netstat -tnlp               //포트 확인하기1

※ 그외 포트 열렸는지 확인방법
> curl -i -X GET ip주소 또는 도메인주소
> telnet 명령어
> ping 명렁어
> 그리고 윈도우 tcping

```

###### 톰캣 server.xml 설정한 포트가 없음

![Alt text](/images/server_setting/server_setting4_port_dead.png)

###### 톰캣 server.xml 설정한 포트가 표출

![Alt text](/images/server_setting/server_setting4_port_alive.png)

---

## [JDK 설치]

###### ※ /etc/profile 이란?

```
user가 쉘에 로그인 하게 되면 가장 먼저 /etc/profile파일을 읽는다.
/etc/profile 파일에는 USER, LOGNAME, HOSTNAME 등의 쉘 변수들이 선언되어 있다.
/etc/profile 파일을 통해 이러한 시스템 전영 쉘 변수들을 초기화한다.
그 다음으로 전역 리드라인 초기화 파일인 /etc/inputrc 파일을 읽고, 기타 특수 프로그램들의 전역 환경 설정 파일을 포함하고 있는 /etc/profile.d 디렉터리를 읽는다.
```

###### ※ 리눅스 source 명령어

```
-리눅스 환경 설정 파일들을 수정하기만 한다고 바로 내용이 적용되는 것은 아니다.
-리부팅이나 쉘에 재로그인 하지 않고 수정된 새로운 환경 설정 내용을 즉시 적용하기 위해서 source 명령어가 사용된다.
```

#### jdk 설치 및 설정

```
# yum 으로 openjdk 받아서 설치해도 됨 ( 알아서 검색해서 하기 )
# 아래 내용은 압축파일로 설치하는 방법임

// 1. java(jdk) 설치되어 있는지 확인
>  java -version         //java 설치,버전 확인1
>  which javac           //java 설치,버전 확인2
>  rpm -qa | grep jdk    //java 설치,버전 확인3

// 2. 적당한 위치에 jdk 압축 풀어줌
>  tar -zxvf jdk-7u75-linux-x64.tar.gz         // *.tar는 -xvf 옵션이면 됨
>  pwd                                         //경로 확인

// 3. java(jdk) 경로 설정
> vi /etc/profile                             //아래 내용중 경로 수정해서 저장(유도리 있게)
------------------------------------------------------------

JAVA_HOME=/home/centos/jdk1.7.0_75         //pwd로 확인한 경로 설정
CLASSPATH=.:$JAVA_HOME/lib/tools.jar
PATH=$PATH:$JAVA_HOME/bin
export JAVA_HOME CLASSPATH PATH

------------------------------------------------------------

 :wq!     //저장후 나가기
> source /etc/profile         //수정된 설정내용 반영하라는 명령어
> java -version               //확인(아래 이미지 참조)

```

###### jdk 설치결과

![Alt text](/images/server_setting/server_setting2.png)

```
# 그리고 톰캣에 환경변수 설정해줌

// 4.  tomcat7 > bin 폴더에 catalina.sh 에 JAVA_HOME 설정해줌
> vi catalina.sh              //적당한 위치에 넣고 저장 (경로 유도리 있게)
-----------------------------------------------------------
JAVA_HOME=/home/centos/jdk1.7.0_75
-----------------------------------------------------------



# 톰캣 JVM 메모리 설정, JDK 설정 관련
 - server_setting_guid2.md 의 [Tomcat 메모리 관리] 부분 참조
```

![Alt text](/images/server_setting/server_setting3_java_home.png)

###### ※ 임의 설치한 jdk 로 war 파일 압축 풀기

```
> /home/centos/jdk1.7.0_75/bin/jar -xvf 파일명.war
```

---

## [Apache]

#### 1. 연동 준비

```
# 1. 아파치, 톰캣 연동용을 위한 connector 설치
> tar -zxvf tomcat-connectors-1.2.48-src.tar.gz

-----------------------------------------------------
* Tomcat Connector Download Web Link
링크 >> http://tomcat.apache.org/download-connectors.cgi

* 리눅스 wget 명령어로 바로 다운받기
> rpm -qa | grep wget      //설치 확인
> wget -c 다운로드링크
-----------------------------------------------------

# 2. 톰캣 연동위한 mod_jk 설치를 위해 gcc, httpd-devel 패키치 설치 (소장님은 gcc-c++ 생략하심)
> yum -y install httpd-devel
> yum -y install gcc
또는
> yum -y install gcc gcc-c++ httpd-devel     //한꺼번에 설치하기

# 3. 패키지 설치 확인
> rpm -qa | grep httpd-devel //설치 패키지 목록 출력
> rpm -qa | grep gcc
> which httpd         //설치 위치 확인
> httpd -v
> /usr/sbin/httpd -v

```

###### httpd-devel 패키지 설치 완료

![Alt text](/images/server_setting/httpd_devel.png)

###### ll /etc/httpd 없던게 생성됨

![Alt text](/images/server_setting/httpd_folder.png)

###### gcc 패키지 설치 완료

![Alt text](/images/server_setting/gcc_install.png)

###### gcc 패키지 설치 확인

![Alt text](/images/server_setting/gcc_install_complete.png)

```
# 4. 전체경로에 대해 이름으로 configure 찾음
> find / -name configure
> cd /home/centos/tomcat-connectors-1.2.48-src/native/          //경로이동
> ./configure --with-apxs=/usr/bin/apxs                         //
> make                                                          //컴파일 실행 ( 해당 native 폴더 안에서 동작함 )
> file apache-2.0/mod_jk.so                                     //지정된 파일의 종류(타입)을 확인하는 명령어, *해당 모듈로 apache와 tomcat이 ajp 프로토콜로 통신을 하게 됨
> cp apache-2.0/mod_jk.so /usr/lib64/httpd/modules/             //*Apache 모듈 디렉토리로 해당 연동모듈 복사함
```

#### 2. 아파치 설정 ( 톰캣 연동 )

###### ※ Apache 주요 디렉토리

```
- /etc/httpd
  > Apache 디렉토리
- /etc/httpd/conf
  > httpd.conf : Apache 설정파일(*중요*)
  > magic : MIME 형식을 지정하기 위한 파일
- /etc/httpd/conf.d
  > Apache 설정을 분리해서 저장하는 디렉토리
  > httpd.conf 설정내용을 이 디렉토리에 저장하면 httpd.conf 파일이 현 디렉토리 내용을 불러와 사용함
    >> httpd.conf 파일 마지막에 " IncludeOptional conf.d/*.conf "구문이 존재함(*포인트*)
- /etc/httpd/log
  > 로그 파일이 저장되는 디렉토리
- /etc/httpd/modules
  > Apache 모듈 설치 디렉토리
```

##### 아파치 설정 //각 사이트별로 설정 상이하니 비교해보고 유도리 있게 받아들이세요

```
※ /etc/httpd/conf/httpd.conf  Apache 설정파일

# 5. httpd-jk.conf, workers.properties, uriworkermap.properties 파일 생성

## 5-1 httpd-jk.conf 생성
> vi /etc/httpd/conf.d/httpd-jk.conf        //아래 내용 복*붙
------------------------------------------------------
LoadModule jk_module modules/mod_jk.so

# jk_module로 작동시 설정
<IfModule jk_module>
  # 워커 설정파일 위치
  JkWorkersFile conf.d/workers.properties
  # 로그(log)위치
  JkLogFile logs/mod_jk.log
  # 로그레벨 설정
  JkLogLevel info
  # 공유 메모리 파일 위치 반드시 seliunux 보안때문에 run에 위치 필수
  JkShmFile run/mod_jk.shm
  # 설정파일 연결
  JkMountFile conf.d/uriworkermap.properties
  # 로그 포맷에 사용할 시간 형식을 지정가능
  # JkLogStampFormat "[%y %m %d %H:%M:%S] "

</IfModule>
------------------------------------------------------



## 5-2 workers.properties 생성
> vi /etc/httpd/conf.d/workers.properties        //apache, tomcat 연동하기 위한 ajp 통신 프로토콜 설정
------------------------------------------------------
//하나인 경우
worker.list=worker1

worker.worker1.type=ajp13
worker.worker1.host=도메인주소 또는 아이피 주소
worker.worker1.port=8009            //tomcat > conf > server.xml 에 설정한 ajp 포트번호
======================================================
//두개이상인 경우
worker.list=worker1,worker2

worker.worker1.type=ajp13
worker.worker1.host=도메인주소 또는 아이피 주소
worker.worker1.port=8009            //tomcat > conf > server.xml 에 설정한 ajp 포트번호

worker.worker2.type=ajp13
worker.worker2.host=도메인주소 또는 아이피 주소
worker.worker2.port=8019
------------------------------------------------------


## 5-3 uriworkermap.properties 생성 ( *각 서버에서 담당할 URI 패턴지정 )
> vi /etc/httpd/conf.d/uriworkermap.properties
------------------------------------------------------
//하나인 경우
/*=worker1
또는 각각 맵핑 설정가능
/*.do=worker1
/*.jsp=worker1
======================================================
//두개이상인 경우 파일을 각각 만들어서 연결시킴 ( 가야대 23개의 경우 )
> vi /etc/httpd/conf.d/uriworkermap1.properties
/*=worker1
> vi /etc/httpd/conf.d/uriworkermap2.properties
/*=worker2
------------------------------------------------------



## 5-4 httpd.conf
------------------------------------------------------
//hosts 파일에 192.168.174.47 ww1.ubitec.com 설정해 둠
<VirtualHost *:80>
  ServerName ww1.ubitec.com
  DocumentRoot "/home/centos/server/tomcat8/webapps"
  JkMountFile conf.d/uriworkermap.properties
</VirtualHost>

------------------------------------------------------



```

###### ※ Apache 도메인 설정 관련

```
> https://httpd.apache.org/docs/trunk/ko/vhosts/examples.html  (공식 아파치 가이드)
> https://araikuma.tistory.com/780
> https://suwoni-codelab.com/linux/2017/05/29/Linux-CentOS-Apache-Tomcat/

> https://roqkffhwk.tistory.com/102   //아파치 , 톰캣 연동 관련
> https://suwoni-codelab.com/linux/2017/05/29/Linux-CentOS-Apache-Tomcat/
> https://jisblee.me/board/view/0/1/167
> https://offbyone.tistory.com/332
> https://ncube.net/%EA%B0%80%EC%83%81%ED%98%B8%EC%8A%A4%ED%8A%B8-%ED%99%98%EA%B2%BD%EC%97%90%EC%84%9C-apache%EC%99%80-tomcat%EC%9D%98-%EC%97%B0%EB%8F%99/
```

```
# 6. 설정완료 후 아파치 재시작 확인
  > apachectl configtest       //설정 검사
  > apachectl restart          //아파치 재시작
  > netstat -tnlp              //항상 포트가 열렸는지 말았는지 확인하기

※ apache 설정 후 에러
 1. You don't have permission to access / on this server. 출력되는 경우
    > 원인 : 프로젝트 폴더에 접근 권한이 없어서
    > 해결 : 권한 변경하기 ! ( $chmod 771 centos )
 2. Service Unavailable The server is temporarily unable to service your request due to maintenance downtime or capacity problems. Please try again later. 와 같은 503 에러 출력되는 경우
    > 원인 : ?
    > 해결 : apachectl restart 하면 됨
 3. 그리고 방화벽에 원하는 포트가 허용되어 있는지 확인해보기 ( 공공기관은 함부로 방화벽 포트 열면 안되고 요청해야 함)
    > iptables, firewall

```

---

## [그외]

###### 상식으로 알아둬야 할 기본 포트

| 구분           | 기본 포트  |
| -------------- | --------- |
| http프로토콜   | 80         |
| https프로토콜  | 443        |
| ssh            | 22        |
| sFTP           | 22        |
| FTP            | 21        |
| Mysql, MariaDB | 3306      |
| Oracle         | 1521      |
| Tibero         | 8629      |
| svn            | 3690      |
| tibero         |           |
※ 나머지는 알아서

###### SSL 설정

```
※ 3Tear 방식 > apache + tomcat + db  ( L4 스위치 추가되면 4Tear 방식 된다하심 )
  2Tear 방식 > tomcat + db

※ 경험상
3Tear 방식에서는 아파치에 설정 ( httpd.conf )
2Tear 방식에서는 tomcat에 server.xml 에 설정함

----------------------------------------------------------------------------
# [아파치에 ssl 설정] ( httpd.conf 파일)

/*
  사례 1 . 가야대
  i) 80포트로 들어오면 무조건 https 로 redirect 되게 설정함
  ii) 443포트(https) 에서 ssl 설정 해줌
*/

    <VirtualHost *:80>
      ServerName nursing.kaya.ac.kr
      DocumentRoot "/DATA/gaya/web/nursing/"
      JkMountFile conf.d/uriworkermap.properties

      RewriteEngine on
      RewriteCond %{HTTPS} off
      RewriteRule ^/(.*) https://%{SERVER_NAME} [R=301,L]
    </VirtualHost>
    <VirtualHost *:80>
        ServerName 61.103.191.8
        <Location />
            Order deny,allow
            Deny from all
        </Location>
    </VirtualHost>
    ....(이하생략)
    <VirtualHost *:443>
      ServerName nursing.kaya.ac.kr
      DocumentRoot "/DATA/gaya/web/nursing/"
      JkMountFile conf.d/uriworkermap.properties

      SSLEngine on
      SSLCertificateFile "/DATA/ssl/Wildcard.kaya.ac.kr_tomcat.crt"
      SSLCertificateKeyFile "/DATA/ssl/Wildcard.kaya.ac.kr.key"
      SSLCertificateChainFile "/DATA/ssl/ChainBundle.crt"
      SSLCACertificateFile "/DATA/ssl/GLOBALSIGN_ROOT_CA.crt"
    </VirtualHost>
    <VirtualHost *:443>
        ServerName 61.103.191.8
        <Location />
            Order deny,allow
            Deny from all
        </Location>
    </VirtualHost>
    ....(이하생략)
----------------------------------------------------------------------------

# [톰캣에 ssl 설정]

/*
  부산시 긴급재난 지원금 관리자 (u2cms)

  ※ 설정확인은
    $ telnet 아이피주소 443      //connected to 나오면 성공
    또는 cmd 창에
    $ tcping 아이피주소 443
*/

  <Connector protocol="org.apache.coyote.http11.Http11NioProtocol" 
             port="443" maxThreads="200" 
             scheme="https" secure="true" SSLEnabled="true" 
             keystoreFile="/data/ssl/busan.go.kr.jks" 
             keystorePass="*.busan.go.kr" 
             clientAuth="false" sslProtocol="TLS"/>

```

###### 가야대 ssl 인증서 파일 (/DATA/ssl)

![Alt text](/images/server_setting/ssl_gaya.jpg)

###### ※ 참고

```
https://tomcat.apache.org/tomcat-7.0-doc/ssl-howto.html
```

---
