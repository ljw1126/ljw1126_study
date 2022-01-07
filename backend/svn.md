
# SVN 서버 셋팅

```
# svn 도움말
svn help

# svn 버전 
svn --version 

# 1. svn 설치 확인 
> svn 
> svn --version
> rpm -qa | grep subversion 

# 2. svn 설치 
> yum -y install subversion

# 3. svn 저장소 생성 / 설정 
> mkdir -p /svn/repos
> cd /svn/repos 
> svnadmin create --fs-type fsfs /svn/repos
                                         저장소명 
# 4. snvserve.conf 파일 수정 
> cd /svn/repos/conf/
> mv svnserve.conf svnserve.conf.old
> vi svnserve.conf  // 아래 내용 추가 후 저장 (:wq!)

/***********************************************/
[general]

#익명 접근의 권한은 none 없음
anon-access = none 

#인증 접근의 권한은 write 읽기/쓰기
auth-access = write

#사용자 패스워드 저장 파일 위치
password-db = passwd

#프로젝트 명칭
realm = gaya_repository

#인증 접근의 권한 설정 파일 위치
authz-db = authz
/***********************************************/

# 5. svnserve 서비스 데몬 확인,생성  // 생성시 service svnserve start/stop/restart 가능하다함 
> vi /etc/sysconfig/svnserve        // 아래 내용 처럼 설정 
/***********************************************/
#OPTIONS="-r /var/svn"
OPTIONS="--threads --root /svn/repos"
/***********************************************/

> 또는 echo 'OPTIONS="--threads --root 저장소최상위폴더"' > /etc/sysconfig/svnserve

# 6. svn 계정 설정 
> vi /svn/repos/conf/passwd        // 아래 내용처럼 ' 계정 = 비밀번호 ' 설정
/***********************************************/
[users]
# harry = harryssecret
# sally = sallyssecret
gaya = gaya123
ubitec = ubitec123
/***********************************************/

# 7. 권한 설정 
> vi /svn/repos/conf/authz       
/***********************************************/
[/]            
gaya=rw
ubitec=rw
/***********************************************/

# 8. svn 서비스 시작 ( ※  svn 기본 포트는 3690 임 ) 
> service svnserve start 
> ps -ef | grep svnserve | grep -v grep 또는 
   netstat -anp | grep svnserve  로 프로세스(LISTEN) 올라왔는지 확인하기 


# 9. 재부팅(reboot)시 자동시작 설정 
> chkconfig --list svnserve
> chkconfig svnserve on 
> chkconfig --list svnserve

※ 참고 주소 : https://goddaehee.tistory.com/81

# 10. 방화벽 설정 ( 3690 포트에 대해 접근 열어줘야함, svnserve 통해서 포트 변경이 되나봄 ) 
10-1. firewall 인 경우 
> systemctl status firewalld
> firewall-cmd --permanent --zone=public --add-port=22/tcp
> firewall-cmd --permanent --zone=public --add-port=3690/tcp
> firewall-cmd --permanent --zone=public --add-port=80/tcp
> firewall-cmd --permanent --zone=public --add-port=8080/tcp
> firewall-cmd --permanent --zone=public --add-port=8009/tcp
> firewall-cmd --permanent --zone=public --add-port=3306/tcp
> firewall-cmd --permanent --zone=public --add-port=2525/tcp
> firewall-cmd --permanent --zone=public --add-port=2526/udp
> firewall-cmd --reload
> firewall-cmd --list-port
> vi /etc/hosts.allow 
/*****************************************************************************************/
# 이런 정보가 추가되어 있었음 
sshd:61.103.191.0/255.255.255.0 211.220.63.61  
/*****************************************************************************************/
> reboot             // 굳이 reboot ? source 로 못하는가 ? 


10-2 iptables 인 경우 
> vi /etc/sysconfig/iptables         // 아래 내용 추가 
/*****************************************************************************************/
-A RH-Firewall-1-INPUT -p tcp -m state --state NEW -m tcp --dport 3306 -j ACCEPT
# svn 3690 포트 방화벽 허용 
-A RH-Firewall-1-INPUT -m state --state NEW -m tcp -p tcp --dport 3690 -j ACCEPT
-A RH-Firewall-1-INPUT -j REJECT --reject-with icmp-host-prohibited
COMMIT
/*****************************************************************************************/
> service iptables restart 


 


svnserve -d -r /data/svn_repo  ..??



※ svn default 포트 3690 올라옴 
> netstat -tnlp 

```