# Server Setting Guide2 ( 로그 , DB , Tomcat )

## ![Alt text](/images/sample_img12.jpeg)

## [로그관리] ( logrotate 와 crontab 이용 )

```
* logrotate란?

로그 파일(시스템 로그)을 rotates, compresses, and mails 을 할 수 있다.
설정 파일을 변경해도 관련 프로세스를 새로 시작할 필요 없이 cron 데몬이 주기적으로 실행 시켜준다.

$ vi /etc/cron.daily

위 cron을 확인해보면 logrotate 설정 부분을 확인할 수 있다.

logrotate 관련 파일

 - /usr/sbin/logrotate : 데몬의 위치 및 데몬프로그램
 - /etc/logrotate.conf : 설정 파일.
 - /etc/logrotate.d : logrotate를 적용할 로그 파일 보관 디렉토리.
 - /var/lib/logrotate.status : logrotate가 작업 내역 보관 파일.
 - /etc/cron.daily/logrotate : logrotate : cron 에 의해 일 단위로 실행한다.


동작 순서를 살펴보면
1. cron.daily 에서 /usr/sbin/logrotate 호출
2. /usr/sbin/logrotate 에서 /etc/logrotate.conf 설정파일 참조
3. /etc/logrotate.conf 설정 파일에서 /etc/logrotate.d 참조
   ( logrotate.conf 파일 안에 "include /etc/logrotate.d")

참조 : https://blueskai.tistory.com/101
```

##### 1. 설치 확인

![Alt text](/images/server_setting/log1.png)

> ※ ps -ef | grep cron 명령으로도 확인 가능하다 함  
> $ yum -y install logrotate //설치 필요할 경우

##### 2. logrotate 실행 파일 생성 , 크론탭 등록

```
// 파일생성
> cd /etc/logrotate.d
> vi tomcat8_u2cms  //편집후 저장
----------------------------------------------
# 해운대구청 공공데이터 예시 
/usr/local/tomcat8_u2cms/logs/catalina.out{
  copytruncate
  maxsize 80M
  daily
  rotate 7
  compress
  missingok
  notifempty
  dateext
}
----------------------------------------------


//크론탭에 logrotate 등록
> crontab -e     // i 눌러서 편집가능 ( vi툴 처럼 )
------------------------------------------------------------------
 ## haeundae u2cms logrotate setting
 0 23 * * * /usr/sbin/logrotate -f /etc/logrotate.d/tomcat8_u2cms
------------------------------------------------------------------

// esc 누르고 :wq!으로 저장
> service crond restart        // 크론탭 재시작 ( start:시작 , stop:중지 )


============================================================================================
※ 첫번째 경우 logrotate.conf랑 logrotate.d 가 연결되어 있어서 전체 파일을 실행한다 함
> /usr/sbin/logrotate -f /etc/logrotate.conf    //logrotate.d 전체 강제실행
> /usr/sbin/logrotate -f /etc/logrotate.d/tomcat8_u2cms  //logrotate.d의 해당 파일만 실행

※ -d 옵션의 경우 debug모드로 실제 로테이션 되지 않음 ( 설정파일 오류확인, 디버그 메시지만 출력함 )
> /usr/sbin/logrotate -d /etc/logrotate.d/tomcat8_u2cms

※ logrotate가 실행되면
- catalina.out 의 용량이 0일때는 생성되는 파일없음 ( 옵션 notifempty 해당 )
- catalina.out 의 용량이 0이 아닌 경우 *.gz 압축파일 생성하고 catalina.out 초기화 시킴

※ logrotate 일부옵션  ( $ man logrotate 명령어를 통해 옵션 정보 확인가능 )
- maxsize : 파일 최대크기로 최대크기가 넘으면 로테이션하고 최대크기가 넘지 않으면 daily로 로테이션
- daily : 로그파일을 날짜별로 변환 진행 ( 추가로 monthly:월 단위, weekly:주 단위 있음 )
- compress : 지나간 로그파일들을 gzip으로 압축 ( <-> nocompress : gzip 압축 안함  )
- dateext : 로그백업파일 이름에 날짜가 들어가도록 함
- missingok : 로그파일이 없더라도 오류를 발생시키지 않음
- rotate 30 : 로그 파일은 30개만큼 저장된 다음 제거되거나 메일로 보내짐
- notifempty : 로그 내용이 없으면 새로운 로그 파일을 생성 안함 (용량 0인 경우, 반대인 ifempty 는 로그내용 없어도 진행)
- maxage 30(숫자) : 30일 이상된 로그 파일 삭제

※ copytruncate 옵션에 대해
- 요약 > 대상 log파일 용량이 너무 크면 시스템 성능저하 유발가능하다 함 ( 아래 주소참고 )
```

###### 참고 - logrotate , copytruncate의 함정

> <https://brunch.co.kr/@alden/27>

---

## [DB 관리] //centos기준

##### 0. Mysql 과 MariaDB에 대해

###### 참고

> <https://ko.wikipedia.org/wiki/MariaDB>  
> <https://sir.kr/cm_free/1489073>

```
※ 요약
- (오라클 인수후) Mysql 거의 유료 <-> mariaDB 무료
- 마리아DB는 MySQL과 소스코드를 같이 하므로 사용방법과 구조가 MySQL과 동일하다.
- 이름만 다르지 명령어나 사용방법 (5.5까지) 모두 MySQL과 동일하다.
- 편의를 위해 마리아DB는 동일한 MySQL 버전과 바이너리 드롭인 교체를 지원한다.
- 예를 들어, MySQL 5.1은 마리아DB 5.1과 5.2, 5.3과 호환된다. MySQL 5.5는 마리아DB 5.5와 호환되는 식이다.
- 마리아DB 커뮤니티는 MySQL과 비교해 애플리케이션 부분 속도가 약 4~5천배 정도 빠르며,
  MySQL이 가지고 있는 모든 제품의 기능을 완벽히 구현하면서도 성능 면에서는 최고 70%의 향상을 보이고 있다고 주장한다.
```

##### 1-1. Mysql 설치/설정 ( 개인 VMware Centos7 에서 테스트 진행 )

###### Yum

```
Yum은 Yellow Dog update Modified의 약자로 duke 대학에서 RPM 설치를 위해 개발한 패키지 매니저이다.
- yum을 사용했을 떄 장점은 RPM 설치 시 발생하는 의존성 문제를 해결해준다.
- 의존성이 걸린 rpm 들도 자동으로 yum에서 설치한다.

※ yum 설정파일
  - /etc/yum.conf (파일) : 특별히 변경할 일 x
  - /etc/yum.reps.d/ (디렉토리) : yum 명령어 입력시 검색되는 네트워크 주소 있음
```

|                               | 명령어                                                  |
| ----------------------------- | ------------------------------------------------------- |
| \*.rpm 설치                   | > yum install [패키지명] 또는 yum -y install [패키지명] |
| 업데이트 가능목록 확인        | > yum check-update                                      |
| 업데이트                      | > yum update [패키지명] //패키지명 없으면 전체처리      |
| 삭제                          | > yum remove [패키지명]                                 |
| 패키지 그룹설치               | > yum groupinstall "[패키지 그룹명]"                    |
| 패키지 목록 확인              | > yum list [패키지명]                                   |
| 특정파일이 속한 패키지명 확인 | > yum provides [파일명]                                 |

```
# 1. Mysql 설치시작
> yum -y install http://dev.mysql.com/get/mysql57-community-release-el7-11.noarch.rpm
```

###### yum 에러 발생

![Alt text](/images/server_setting/mysql1.png)

###### Wget

```
'Web Get'의 약어로 웹 상의 파일을 다운로드 받을 때 사용하는 명령어로 wget은 비 상호작용 네트워크 다운로더 이다.
- HTTP, HTTPS, FTP 프로토콜을 지원하며, HTTP proxy에서 데이터를 가져올 수도 있다.
- wget이 상호작용을 필요로 하지 않는다(non-interactive)는 것은 사용자가 로그인하지 않은 상태 동안에도 백그라운드 상태에서 동작할 수 있음을 의미한다.
- wget은 HTML과 XHTML, CSS 페이지를 다운로드 받아 웹 사이트의 로컬 버전을 만들 수 있고, 충분히 본래의 사이트의 디렉토리 구조를 만들 수 있다.
- 또한 recursive downloading을 지원해서, 사이트 전체를 쉽게 내려받을 수 있다.
- wget은 느리거나 불안정한 네트워크 환경에서도 매우 잘 작동하는 견고한 프로그램이다.
- 네트워크 환경이 불안해서 도중에 연결이 끊겼다면, 연결이 끊긴 시점부터 다운로드 받는 기능도 가지고 있다.
```

```
# 2. yum으로 설치안되서 wget으로 받음
> wget http://dev.mysql.com/get/mysql57-community-release-el7-11.noarch.rpm
```

![Alt text](/images/server_setting/mysql2.png)

###### rpm

```
- 리눅스의 초창기 시절에 프로그램을 설치하는것이 어려웠던 점을 개선하기위해 Red Hat(레드헷)에서 windwos의 *.exe와 비슷하게 프로그램을 설치한 후에 바로 실행할 수 있는 설치파일을 제작함
- CentOS의 설치 파일의 확장명은 *.rpm 또는 패키지라고 부릅니다.

※ rpm 의 큰 단점은 '의존성 문제' -> 그래서 yum 나옴
```

|                             | 명령어                               |
| --------------------------- | ------------------------------------ |
| 신 패키지 설치              | > rpm -ivh [패키지파일 *.rpm ]       |
| 업그레이드 설치             | > rpm -Uvh [패키지파일]              |
| 패키지 있으면 설치/업데이트 | > rpm -Fvh [패키지파일]              |
| 삭제                        | > rpm -e [패키지명]                  |
| 패키지 설치확인             | > rpm -qa 파이프라인 grep [패키지명] |
| 설치된 패키지 상세확인      | > rpm -qi [패키지명]                 |

```
# 3. rpm 패키지 설치
> rpm -Uvh mysql57-community-release-el7-11.noarch.rpm
> yum repolist enabled | grep mysql
```

![Alt text](/images/server_setting/mysql3.png)

```
# 4. mysql-community-server 설치하고 서비스 시작
> yum -y install mysql-community-server

> service mysqld start     //systemctl start mysqld
> systemctl enable mysqld  //service 명령어에는 enable 없음

> mysql
//비밀번호 뭐라뭐라 에러뜸
```

![Alt text](/images/server_setting/mysql4.png)

```
# 5. root 임시 비밀번호 찾기
> vi /var/log/mysqld.log
oIBeean;O9sq //임시 비번 복붙하길 .. 타이핑하니 죽어도 안 뚫림
```

###### Mysql 임시 비밀번호 찾기 // vi 검색 명령어 활용

![Alt text](/images/server_setting/mysql6.png)

```

> show databases;
> use mysql ;
//비밀번호 변경하려는데 반갑지 않은 에러 .. 비밀번호 유효성 걸린다함
--------------------------------------------------------------------------------
mysql> UPDATE user set authentication_string=password('1111') where user='root';
ERROR 1819 (HY000): Your password does not satisfy the current policy requirements
---------------------------------------------------------------------------------
```

###### ALTER USER 문으로 비밀번호 재설정하라는 에러 메시지

![Alt text](/images/server_setting/mysql7.png)

###### ALTER USER 문으로 임시비밀번호로 재설정한 뒤 반영 // 이후 원하는 비번으로 변경

![Alt text](/images/server_setting/mysql8.png)

```
# 6. root 비번 변경 후 반영
> show variables like 'validate_password%';  //비밀번호 유효성 조건 확인
> SET GLOBAL validate_password_length=4;    //비밀번호 길이제한 4로 변경
> select password('1111');
> UPDATE user set authentication_string=password('1111') where user='root';
> flush privlieges;         //설정반영
> exit;     //다시 재접속 하면 비밀번호 변경된 것을 확인가능함 !
```

![Alt text](/images/server_setting/mysql9.png)

![Alt text](/images/server_setting/mysql10.png)

```
> mysql uroot -p //비밀번호 1111
> status;        //설정확인
```

![Alt text](/images/server_setting/mysql11.png)

```
> exit;

# 7. Mysql 환경 설정
> vi /etc/my.cnf

================================================================================
#없는거만 추가 , 필요시 찾아서 설정 더 추가
[client]
default-character-set = utf8

[mysql]
default-character-set=utf8

[mysqld]

datadir=/var/lib/mysql
socket=/var/lib/mysql/mysql.sock

character-set-server=utf8
collation-server=utf8_general_ci
init_connect=SET collation_connection = utf8_general_ci
init_connect=SET NAMES utf8

character-set-client-handshake = FALSE
skip-character-set-client-handshake

lower_case_table_names=1

[mysqldump]
default-character-set=utf8

================================================================================
# 충남클라우드개발서버(스코인포)

# For advice on how to change settings please see
# http://dev.mysql.com/doc/refman/5.7/en/server-configuration-defaults.html

[mysql]
no-auto-rehash
default-character-set=utf8

[mysqld]
#
# Remove leading # and set to the amount of RAM for the most important data
# cache in MySQL. Start at 70% of total RAM for dedicated server, else 10%.
# innodb_buffer_pool_size = 128M
#
# Remove leading # to turn on a very important data integrity option: logging
# changes to the binary log between backups.
# log_bin
#
# Remove leading # to set options mainly useful for reporting servers.
# The server defaults are faster for transactions and fast SELECTs.
# Adjust sizes as needed, experiment to find the optimal values.
# join_buffer_size = 128M
# sort_buffer_size = 2M
# read_rnd_buffer_size = 2M
datadir=/var/lib/mysql
socket=/var/lib/mysql/mysql.sock

character-set-client-handshake=FALSE
init_connect="SET collation_connection = utf8_general_ci"
init_connect="SET NAMES utf8"
character-set-server = utf8
collation-server = utf8_general_ci



# Disabling symbolic-links is recommended to prevent assorted security risks
symbolic-links=0

log-error=/var/log/mysqld.log
pid-file=/var/run/mysqld/mysqld.pid
# TC MARKER {marker}
######################################################################
### NHN Toast Cloud Standard configuration for MYSQL 5.7.32        ###
######################################################################
default_storage_engine=InnoDB
innodb_log_file_size = 512M
innodb_file_per_table
innodb_log_files_in_group = 2
expire_logs_days = 3
slow_query_log
sysdate-is-now

[client]
default-character-set=utf8
~


================================================================================



```

###### my.cnf 수정전

![Alt text](/images/server_setting/mysql13.png)

###### my.cnf 수정후

![Alt text](/images/server_setting/mysql14.png)

```
//mysql 재시작 후 확인
> systemctl restart mysqld
```

###### 설정 결과

![Alt text](/images/server_setting/mysql15.png)

###### 그외

```
# Mysql secure 설정
> mysql_secure_installation   //아래 mariaDB 내용 참고

# 연결안될때 방화벽 포트 관련
- telent, ping, tcping 으로 포트 ( 기본 3306 ) 열려 있는지 파악하기
- iptables 인지 firewall 인지 파악후 포트 처리해주기
  >> 회사서버인 경우는 관리자 문의 후 방화벽 포트 처리하기
```

##### 1-2. MariaDB 설치/설정

```
> rpm -qa | grep MariaDB  //설치 확인
> curl -sS https://downloads.mariadb.com/MariaDB/mariadb_repo_setup | sudo bash
//해당 경로에 파일이 생성됨  >  /etc/yum.repos.d/
```

![Alt text](/images/server_setting/mariadb1.png)

```
> yum install MariaDB-server    //curl로 내려받은 파일 설치
> systemctl enable mariadb      //MariaDB 서비스를 부팅시 자동으로 실행되게 설정을 변경
> systemctl start mariadb       //MariaDB를 시작
> mysql_secure_installation     //MariaDB의 root암호 및 기본 보안설정 하기위해 아래의 명령어 실행함

===================================================================
NOTE: RUNNING ALL PARTS OF THIS SCRIPT IS RECOMMENDED FOR ALL MariaDB
      SERVERS IN PRODUCTION USE!  PLEASE READ EACH STEP CAREFULLY!

In order to log into MariaDB to secure it, we'll need the current
password for the root user. If you've just installed MariaDB, and
haven't set the root password yet, you should just press enter here.

Enter current password for root (enter for none):
OK, successfully used password, moving on...

Setting the root password or using the unix_socket ensures that nobody
can log into the MariaDB root user without the proper authorisation.

You already have your root account protected, so you can safely answer 'n'.

Switch to unix_socket authentication [Y/n] //Y
Enabled successfully!
Reloading privilege tables..
 ... Success!


You already have your root account protected, so you can safely answer 'n'.

Change the root password? [Y/n] //Y
New password:                      //비밀번호 ubitec123 설정함
Re-enter new password:
Password updated successfully!
Reloading privilege tables..
 ... Success!


By default, a MariaDB installation has an anonymous user, allowing anyone
to log into MariaDB without having to have a user account created for
them.  This is intended only for testing, and to make the installation
go a bit smoother.  You should remove them before moving into a
production environment.

Remove anonymous users? [Y/n] //N
 ... skipping.

Normally, root should only be allowed to connect from 'localhost'.  This
ensures that someone cannot guess at the root password from the network.

Disallow root login remotely? [Y/n] //N
 ... skipping.

By default, MariaDB comes with a database named 'test' that anyone can
access.  This is also intended only for testing, and should be removed
before moving into a production environment.

Remove test database and access to it? [Y/n] //n
 ... skipping.

Reloading the privilege tables will ensure that all changes made so far
will take effect immediately.

Reload privilege tables now? [Y/n] //y
 ... Success!

Cleaning up...

All done!  If you've completed all of the above steps, your MariaDB
installation should now be secure.


Thanks for using MariaDB!
====================================================================

> mysql -uroot -p     //접속
> 비밀번호 입력 : 위에 설정한 ubitec123 //접속 후 수정가능
> status;             //db 설정 확인
```

![Alt text](/images/server_setting/mariadb2_status.png)

```
> show variables like 'c%';   // db 설정 확인
```

![Alt text](/images/server_setting/mariadb3.png)

```
> exit            //db에서 나옴
> vi /etc/my.cnf  //설정 수정( MariaDB 공식 설정파일 )

====================================================================
//해당 내용은 소장님이 작성한 mysql 설정인데.. mariaDB는 수정한 기억이 안남
[client]
default-character-set = utf8
 
[mysql]
default-character-set=utf8
 
[mysqld]
 
datadir=/var/lib/mysql
socket=/var/lib/mysql/mysql.sock
 
character-set-server=utf8
collation-server=utf8_general_ci
init_connect=SET collation_connection = utf8_general_ci
init_connect=SET NAMES utf8
 
character-set-client-handshake = FALSE
skip-character-set-client-handshake
 
lower_case_table_names=1
[mysqldump]
default-character-set=utf8

====================================================================
//44번 서버 mariaDB 설정 ( 없으면 넣고 , 있으면 말고 )
//설정 찾아보면 로그도 관리할 수 있음
[client]
default-character-set = utf8

[mysqld]
skip-external-locking
skip-host-cache
skip-locking
skip-name-resolve
max_allowed_packet = 60M

datadir=/var/lib/mysql
socket=/var/lib/mysql/mysql.sock
user=mysql
# Disabling symbolic-links is recommended to prevent assorted security risks
symbolic-links=0

init_connect="SET collation_connection=utf8_general_ci"
init_connect="SET NAMES utf8"
default-character-set = utf8
character-set-server = utf8
collation-server = utf8_general_ci
lower_case_table_names = 1

innodb_buffer_pool_size = 64M

[mysqld_safe]
log-error=/var/log/mysqld.log
pid-file=/var/run/mysqld/mysqld.pid

[mysqldump]
default-character-set=utf8

[mysql]
default-character-set=utf8

====================================================================

> systemctl restart mariadb     // 설정 수정했을 경우 db 재시작해야지 반영됨

```

###### 설정결과

![Alt text](/images/server_setting/mariadb4_status.png)

![Alt text](/images/server_setting/mariadb5.png)

##### 2. 스키마 생성, 테이블 생성 ( = \*.sql import )

```
/*
  u2cms 스키마 설치시 192.168.174.202 서버에 /home/폴더 안에 dump *.sql 파일있음
  scp 명령어로 가져오거나, ftp로 내려받아서 옮기거나 하기
*/
> ssh root@192.168.174.202           //외부망이라 다른망에서 접근 불가
> 또는 ssh -l root 211.220.63.108    //다른망에서도 접근 가능
> exit   //확인후 202에서 나감
> scp root@192.168.174.202:/home/backup_20200609.sql ./      //*.sql 파일을 현재위치로 가져오기

//설치할 db 서버에 적절한 위치에 *.sql 파일 옮겨둠

> mysql -uroot -p      //db접속
> create database u2cms default character set utf8;     //u2cms 스키마 생성
> show databases;      //스키마 확인
```

![Alt text](/images/server_setting/mariadb6.png)

```
//※ grant all privileges on [DB이름].* to [계정이름]@'외부접속권한' identified by '[패스워드]';
// %는 any , 그외 localhost 있음
> grant all privileges on u2cms.* to root@'%' identified by 'ubitec123';
// 권한 부여하면서 패스워드 변경가능! ( identified by 부분 )

> flush privileges;    //설정수정 반영
> exit
> mysql -uroot -p u2cms < backup_20200609.sql       // u2cms DB에 sql 내용을 넣어라 root 권한으로
> mysql -uroot -p      //ubitec123
> show databases;
> use u2cms;
> show tables;          // 확인 끝

※ u2cms 스키마 넣을때 가끔 function과 view에 사용되는 스키마가 틀려서 에러 뜰 때도 있음 ..
sql파일 수정해서 다시 넣어버리면 됨

※ maridDB 접속해서 *.sql 파일 실행시키기
> mysql -uroot -p
> use u2cms
> source backup_20200609.sql    //sql파일 실행해서 밀어넣기 , 절대경로 지정해도 됨

※ db생성 , 계정 생성, 권한 전체/일부 부여, 제거 ( 해운대 u2cms 관련 )
  # 1. db 생성
  > create database haeundae_u2 default character set utf8;

  # 2. 계정 생성 및 권한 부여
  > create user 'u2cms'@'%' identified by 'Ubitec1495#';
  > GRANT ALL privileges ON haeundae_u2.* TO u2cms@'%';

  > create user 'u2cms'@'localhost' identified by 'Ubitec1495#';
  > GRANT ALL privileges ON haeundae_u2.* TO u2cms@'localhost';

  # 3. u2cms 계정에 haeundae( 타 DB ) select 권한 부여
  > grant select on haeundae.* to u2cms@'%';      //haeundae 데이터 베이스에 대해
  > grant select on haeundae.* to u2cms@'localhost';

  # 4. 권한부여 확인
  > use mysql
  > select user, host from user;     //db 사용자 목록
  > show grants for current_user;   //현재 사용자 권한 확인
  > SHOW GRANTS FOR 'u2cms'@'%';
  > SHOW GRANTS FOR 'u2cms'@'localhost';

  # 5. 그외 권한 취소
  > revoke select on haeundae.* from u2cms@'%';   //select 권한 제거

```

![Alt text](/images/server_setting/mariadb7_grant.png)

![Alt text](/images/server_setting/mariadb8_grant.png)

##### 3. DB 백업설정 ( 필요시 )

```
//db 백업할 폴더를 적절한 위치에 생성함 , 예제는 home폴더 안에
> cd /home
> mkdir database_backup
> vi db_backup.sh

//내용 입력 후 저장 ( dump 뜨는 명령어 눈치있게 수정하기 )
--------------------------------------------------------------
#!/bin/bash
DATE=$(date +%Y%m%d)
BACKUP_DIR=/home/database_backup/
mysqldump -uroot -pubitec123 --lock-all-tables u2cms > $BACKUP_DIR"backup_"$DATE.sql

find $BACKUP_DIR -ctime +3 -exec rm -f {} \;
--------------------------------------------------------------
//--lock-all-tables 옵션의 경우 해당 mysqldump: Got error: 1449: "The user specified as a definer ('u2cms'@'%') does not exist" when using LOCK TABLES 에러가 떠서 추가함


> crontab -e    // 추가 후 저장 ( i 누르고 편집 , :wq! 저장 )
--------------------------------------------------------------
41 18 * * * sh /home/db_backup.sh
--------------------------------------------------------------
> service cond restart  //크론탭 재시작
> sh db_backup.sh  //강제 실행
```

###### 결과

![Alt text](/images/server_setting/mariadb9_backup.png)

##### 4. 그외

###### innodb_buffer_pool_size 주기적 점검 및 확인 방법

```
//접속 후 쿼리 실행
> SELECT table_schema "DB",
         sum(data_length + index_length)/1024/1024 "DB size in MB"
  FROM information_schema.TABLES GROUP BY table_schema;

/*
  innodb_buffer_pool_size 를 계산해보자
  이  옵션은  무들 성능에 가장 큰 임팩트를 주기 쉬운데, 간단히 말해서 얼마나 많이 RAM 에
  데이터를 저장되게 할것인가 라는 겁니다. (스토리지가 아니라)
  데이타베이스는 점점 커지기때문에 여분의 공간도 점점더 늘어나야하는데 , 한달에 한번정도는 확인을해서 innodb_buffer_pool_size 를 조절해야합니다.
  너무 많이 설정해서 낭비하면 안되며, 적절하게 10% 정도 추가하면 좋습니다.

  예를들면 :
  252MB + (10% of 252MB) = 277MB
  277MB + (10% of 277MB) = 305MB

  < 참고설명>
  innodb_buffer_pool_size 항목값에서 MySQL의 임시 기억 공간인 버퍼 풀 메모리 크기를 지정한다.
  이 값으로 전체 메모리의 50~80%를 권하기도 하지만, 메모리가 넉넉하지 않은 환경이라면 이 값을 20% 이하로 줄여야 할 때도 있다.
  버퍼 풀 메모리가 너무 크면 하드디스크를 가상 메모리로 쓰는 작업(스와핑)이 일어나 매우 느려지는 원인이 될 수 있기 때문이다.
*/

//설정하기
> vi /etc/my.cnf
> innodb_buffer_pool_size = xxxM   //해당 내용 삽입

```

---

## [Tomcat 메모리 관리]

###### ※ 참고- 프로젝트 실행시 JVM 이 기본 200MB 메모리 잡아 먹는다 함

```
//서버의 메모리 상황을 고려하여 메모리 설정을 해줘야 함
//설치한 tomcat > bin > vi catalina.sh 편집하여 적당한 위치에 삽입

#Java7 ( jdk 1.7인 경우 )
  //해운대 RFC
  CATALINA_OPTS="-Xms10g -Xmx10g -XX:PermSize=512M -XX:MaxPermSize=1024M"
  //가야대 (23개 학과 , 총관리자 1개)
  JAVA_OPTS="$JAVA_OPTS -Xms256m -Xmx256m -XX:MaxPermSize=128m"

#Java8 ( jdk 1.8인 경우 )
  //해운대 u2cms
  JAVA_OPTS="$JAVA_OPTS -Xms512m -Xmx512m -XX:MetaspaceSize=256m -XX:MaxMetaspaceSize=512m"

=====================================================================================
※ 참고
  - jdk 1.8 부터 permSize, MaxPermSize가 없어지고 MetaspaceSize, MaxMetaspaceSize 변경됨
  - MetaspaceSize, MaxMetaspaceSize 설정 안 할 경우,
    OS가 동적으로 메모리 처리한다지만, 장애에 직면할 수 있다함 > 고로 설정 처리요함
    (아래 'JDK8 적용 후, 심각한 성능저하가 발생한다면?' 링크 참고)

※ 서버환경 확인 방법
  >  java -version         //java 설치버전 확인1
  >  which javac           //java 설치버전 확인2
  >  rpm -qa | grep jdk    //java 설치버전 확인3
  >  cat /etc/*release     //centos버전 확인
  >  free -h -w            //메모리 용량확인 , RES 부분이 실제 물리메모리, PID는 알죠?
  >  top                   //실시간 메모리 사용량 모니터링
  >  curl ifconfig.me      //공인 ip 확인
  >  netstat -tnlp         //사용되고 있는 포트확인

  #### 리눅스 방화벽 확인 ( 둘 중 사용하는거 쓰기 )
  >  iptables -nL          //리눅스 방화벽 확인1
  >  firewall-cmd --zone=public --list-all  //리눅스 방화벽 확인2

  #### cpu 정보 확인
  >  cat /proc/cpuinfo
  >  grep -c processor /proc/cpuinfo    //cpu 코어 전체 개수 확인
  >  grep "physical id" /proc/cpuinfo | sort -u | wc -l   //물리 CPU 수 확인
  >  grep "cpu cores" /proc/cpuinfo | tail -1    // CPU당 물리코어 수 확인

  #### 디스크 남은 용량 확인
  > df -k // 킬로바이트 단위로 현재 남은 용량을 확인
  > df -m // 메가바이트 단위로 남은 용량을 왁인
  > df -h // 보기 좋게 보여줌
  > df .  // 현재 디렉토리가 포함된 파티션의 남은 용량을 확인

  ※ 상대방 포트 열려있는지 확인 방법 
  > nc 호스트IPv4주소 포트      // centos7 , yum install nc 로 설치가능* 
  > nc -z 호스트IPv4주소 포트      // centos7 이하 

  > telnet 호스트IPv4주소 포트     
    - Connected to xxx.xxx.xxx.xxx... 출력되면 통신 성공 
    - trying에서 멈춘다면 방화벽 오픈 안된 걸로 판단 
    - telnet: Unable to connect to remote host: Connection refused 인 경우 방화벽은 열려있으나 포트에 해당하는 프로세스가 안 올라와있다는 뜻 
      >> 목적지 서버에서 netstat -tnlp 로 확인 

```

###### JDK8 적용 후, 심각한 성능저하가 발생한다면?

> <https://brunch.co.kr/@heracul/1>

###### Java 7 / 8 메모리에 대해

> <https://blog.voidmainvoid.net/184>

###### 특정 JDK로 변경

```
> cd /톰캣/bin/
> vi catalina.sh    //적당한 위치에 아래 내용 삽입 후 저장
-------------------------------------------------------------------------
// 압축(*.tar)된 jdk 파일을 특정 위치에 푼 뒤 경로 유도리 있게 수정해서 추가하기!
JAVA_HOME=/usr/local/jdk1.8.0_05
-------------------------------------------------------------------------
```

###### 아파치 톰캣 튜닝 가이드

> <https://bcho.tistory.com/788>

###### ※ 아파치 톰캣 메모리 누수 관련 

> <https://ktko.tistory.com/entry/JAVA-%EB%98%90%EB%8A%94-Tomcat-JVM-Heap-%EC%83%81%ED%83%9C-%ED%99%95%EC%9D%B8%ED%95%98%EA%B8%B0>  
> <https://ktko.tistory.com/entry/JVM-%EB%A9%94%EB%AA%A8%EB%A6%AC-%EA%B5%AC%EC%A1%B0-%EC%9D%B4%ED%95%B4%ED%95%98%EA%B8%B0?category=629345>

---


###### 네트워크 상태 확인

- u2cms 3.\* 을 적용한 경남도청 프로젝트부터는 해당 데이터를 수집하여 그래프로 제공하고 있음
  ![Alt text](/images/server_setting/network_sts.png)
- 배치 프로그램(etl_batch)을 통해, 서버에서 생성된 네트워크 정보 파일을 가져와 DB 적재하도록 함

```
# 네트워크 RX/TX 데이터 생성 ( crontab 활용 )
> crontab -e 후 편집

  1. CentOS 의 경우
  5 * * * * /sbin/ifconfig | grep RX | grep bytes | awk '{print$1$2,$5$6}' | head -1 > /생성할서버경로/net.txt

  2. SuLinux 의 경우
  netstat -i | grep enp3so | awk '{print"bytes:"$3,"bytes:"$7}' | head -1 > /생성할서버경로/net.txt
  // 이때 enp3so는 이더넷 id 로 ifconfig 명령어로 확인 후 유도리 있게 변경하시길 바람.

> crontab 재시작하면 지정한 시간대에 지정된 경로에 net.txt 파일 생성됨
  >> 그럼 배치에서 net.txt 를 호출하여 pasring 한 후 DB에 데이터 적재하는 로직임

------------------------------------------------------------------------------------------------------------
※ 192.168.174.13 충남 테스트서버 설정의 경우
[root@localhost chungnam]# crontab -e
* * * * * netstat -i | grep enp3s0 | awk '{print"bytes:"$3,"bytes:"$7}' | head -1 > /home/chungnam/net.txt
00 04 * * * sh /home/db_backup.sh

[root@localhost chungnam]# netstat -i   // RX-OK가 $3이고 TX-OK가 $7 , 3번째 7번째
Kernel Interface table
Iface             MTU    RX-OK RX-ERR RX-DRP RX-OVR    TX-OK TX-ERR TX-DRP TX-OVR Flg
docker0          1500    30482      0      0 0         33877      0      0      0 BMRU
enp3s0           1500 10293009      0      0 0       4060793      0      0      0 BMRU
lo              65536   985131      0      0 0        985131      0      0      0 LRU
veth216a428      1500    12698      0      0 0         15189      0      0      0 BMRU

[root@localhost chungnam]# vi net.txt   //크론탭이 돌면서 생성된 net.txt 파일내용을 배치로 읽어와 DB 적재함
bytes:10294042 bytes:4062086
------------------------------------------------------------------------------------------------------------



```
