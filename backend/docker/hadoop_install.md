## 참고 
**wikidocs - 무슨 책 사이트 같음**
[https://wikidocs.net/22654](https://wikidocs.net/22654)

**DATA ON-AIR**
[https://dataonair.or.kr/db-tech-reference/d-guide/data-practical/?mod=document&uid=402](https://dataonair.or.kr/db-tech-reference/d-guide/data-practical/?mod=document&uid=402)

**👨‍💻Apache hadoop 명령어 공식 문서**
[https://hadoop.apache.org/docs/](https://hadoop.apache.org/docs/)
[https://hadoop.apache.org/docs/r2.10.1/hadoop-project-dist/hadoop-common/FileSystemShell.html](https://hadoop.apache.org/docs/r2.10.1/hadoop-project-dist/hadoop-common/FileSystemShell.html)

**ssh config 설정/접속 관련**
[https://blog.jiniworld.me/106](https://blog.jiniworld.me/106)

**ec2 로 하는 예제인데 제일 잘되어 있음 .. slave1/2 왜 통신 안되었는지 이유를 여기서 찾음**
[https://1mini2.tistory.com/84](https://1mini2.tistory.com/84)

**(사용안함) docker hadoop 셋팅 다 되어있는게 있는듯**
[https://github.com/big-data-europe/docker-hadoop](https://github.com/big-data-europe/docker-hadoop)

**하둡 트러블 슈팅 관련**
[https://www.cs.brandeis.edu/~cs147a/lab/hadoop-troubleshooting/](https://www.cs.brandeis.edu/~cs147a/lab/hadoop-troubleshooting/)

**하둡 v1.x 설치 영상** 
[https://www.youtube.com/watch?v=KDC2Nto4NfE](https://www.youtube.com/watch?v=KDC2Nto4NfE)

**하둡 NameNode 기동과정과 메커니즘의 이해**
[https://likebnb.tistory.com/162](https://likebnb.tistory.com/162)

---
// START

## 하둡(Hadoop)이란?
```
하둡은 2006년 야후의 더그 커팅이 '넛치'라는 검색엔진을 개발하는 과정에서 대용량의 비정형 데이터를 기존의 RDB 기술로는 처리가 힘들다는 것을 깨닫고, 새로운 기술을 찾는 중 구글에서 발표한 GFS와 MapReduce 관련 논문을 참고하여 개발하였습니다. 이후 아파치 재단의 오픈 소스로 공개 되었습니다.

하둡은 하나의 성능 좋은 컴퓨터를 이용하여 데이터를 처리하는 대신, 적당한 성능의 범용 컴퓨터 여러 대를 클러스터화(군집화*)하고, 큰 크기의 데이터를 클러스터에서 병렬로 동시에 처리하여 처리 속도를 높이는 것을 목적으로 하는 분산처리를 위한 오픈소스 프레임워크라고 할 수 있습니다.
```

> edit log랑 Standby NameNode라는걸 만들어둬서 기존 NameNode 장애 발생시 백업 대처 가능하도록 버전업이 되었다나 뭐라나

## Hadoop 와 HDFS 구분 
[https://1mini2.tistory.com/85](https://1mini2.tistory.com/85)

```
하둡이란?
대량의 자료를 처리할 수 있는 컴퓨터 클러스터에서 동작하는 "프리웨어 자바 소프트웨어 프레임워크"입니다.
Apache Hadoop Framework에서는 아래의 모듈을 포함하고 있습니다.
  - 하둡 커먼(Hadoop Common)
  - 하둡 분산 파일 시스템(HDFS) - 스토리지 계층
  - 하둡 YARN - 자원 관리 계층
  - 하둡 맵리듀스 - 처리 계층 

참고링크 (https://ko.wikipedia.org/wiki/%EC%95%84%ED%8C%8C%EC%B9%98_%ED%95%98%EB%91%A1)

HDFS란? 
  하둡 클러스터의 데이터 스토리지 계층입니다.
  하둡 프레임워크를 위해 자바언어로 작성된 분산 확장 파일시스템 입니다.
  매우 큰 파일을 저장하도록 설계(대용량파일 분산 저장)되었으며, 데이터를 여러 노드에 중복해서 저장합니다.

HDFS는 하둡의 스토리지 계층으로, Hadoop 분산파일시스템(hdfs)를 말합니다. :)
다시 정리해 말하자면, hdfs는 하둡 프레임워크의 한 부분인거죠! 

```



## NameNode(master)/DataNode(slave) 
> NameNode와 DataNode의 개수를 어떻게 알지 ?

## Hadoop을 설치하면 hdfs/yarn/mapreduce가 기본적으로 깔려있는건가? 어떻게 앙ㄹ지?

## Hadoop fs 와 dfs 차이 
- hadoop fs 의 경우 
  - local fs 나 hdfs 등과 같이여러 파일 시스템과 상호 작용할 수 있는 명렁어 이다. 
- hadoop dfs 
  - hdfs에만 해당/사용가능 -> 이는 더이상 사용 되지 않고 **hdfs dfs** 를 사용해야 함  
- hdfs dfs 
  - hdfs에만 해당/사용가능

> 요약하자면 hadoop fs 는 hadoop 뿐만아니라 여러 파일시스템과 호환가능 명령어고, hdfs dfs는 HDFS에만 해당하는 명령어이다.

[https://reference-m1.tistory.com/197]

## 웹기반 서비스 체크 

  **YARN 리소스 매니저 웹페이지**
    YARN에서 실행되는 모든 애플리케이션 상태 웹서비스(Web UI)를 확인가능
    > http://localhost:8088              
  
  **HDFS 파일 시스템의 웹페이지**
    YARN 기반의 HDFS 상태에 관한 웹 서비스(Web UI)를 확인가능
    > http://localhost:50070

## HDFS / YARN 서비스 구분 
> jps  
  
  HDFS 서비스 : NameNode, SecondaryNameNode, DataNode
  YARN 서비스 : ResourceManager, NodeManager 
     

## hadoop 재시작시 put으로 올린 데이터 다 사라지는가 ?

## HDFS / YARN 개별 재시작시 NameNode 실행이 안되는데 어떻게 처리 가능 ? 

## 하둡 핵심 설정파일 
core-site.xml
  $HADOOP_HOME/etc/hadoop/core-site.xml     

hdfs-site.xml
  $HADOOP_HOME/etc/hadoop/hdfs-site.xml

mapred-site.xml
  $HADOOP_HOME/hadoop-2.10.2/etc/hadoop

yarn-site.xml 
  $HADOOP_HOME/etc/hadoop


## bash_profile과 bashrc의 차이점 
  .bash_profile : 로그인 쉘 환경 (사용자 로그인, ssh, su - 할때만 로드)
  .bashrc : 로그인 이외의 쉘 환경 (터미널 창을 열때, bash 쉘에 접근할때 로드)

// END
---


## 디렉토리 확인
```
$ hadoop fs -ls 
```

## 파일 삭제 
```
$ hadoop fs -rm [파일]
```

## 디렉토리 삭제 
```
$ hadoop fs -rmr output 
```

## 하둡시스템에 파일 저장
```
$ hadoop fs -put 대상파일 .       // 대상파일을 하둡의 루트 경로에 저장 
```

- hadoop 은 작은 파일에 대한 처리가 최적화 되어있는게 아니라, 큰 파일을 병렬로 분산처리 하는데 최적화 되어 있는 시스템.
- hadoop 은 자바 기반으로 만들어져 있음 ( php, python, shell script 등으로도 제어가능 )
- hadoop 디렉토리에 hadoop-examples-1.0.4.jar 
- fs* : file system 제어

## 하둡시스템에 저장된 파일을 mapreduce로 
- 해석 : hadoop-examples-1.0.4.jar 파일에 있는 wordcount 클래스를 실행시켜서 [분석파일]에 대한 결과를 [디렉토리(없으면 현재 경로 기준으로 생성)]에 저장하겠다.
```
$ hadoop jar hadoop-examples-1.0.4.jar wordcount [분석파일] [디렉토리]
$ hadoop jar hadoop-examples-1.0.4.jar wordcount [분석파일] wordcount_deadpoetssociety
$ hadoop fs -ls       // 확인 
$ hadoop fs -ls wordcount_deadpoetssociety    // 폴더 내용 확인 , _SUCCESS, _logs는 의미없고 part-r-00000 파일에 결과 저장됨
```

## 분석 결과 확인 
```
$ hadoop fs -cat [디렉토리]/[파일명]
```

## 하둡 설치 사이트
[https://mungiyo.tistory.com/17](https://mungiyo.tistory.com/17)

```
// ubuntu/centos 이미지 가져오기 
$ docker search ubuntu 
- search : docker hub에서 이미지 검색 

$ docker pull ubuntu       // docker hub 에서 확인한 이미지 NAME , STARS가 많고 OFFICIAL인 걸 받음 

// 실행 
$ docker run -it --name ubuntu_server ubuntu
- i(interactive), -i(Pseudo-tty)
또는 
$ docker run -i -t --name ubuntu_server ubuntu /bin/bash
  docker run -i -t --name hello ubuntu /bin/bash
- /bin/bash 즉시 실행

// git bash 로 실행시 안되서 create 사용했는데 안됨..($ docker create -i -t --name ubuntu_server ubuntu /bin/bash)
에러. the input device is not a TTY.  If you are using mintty, try prefixing the command with 'winpty'
원인. git bash 사용해서 그럼.. cmd 사용해서 명령어 입력시 정상 실행됨. git으로 하고 싶다면 아래 주소 참고
> winpty docker exec -it [컨테이너명] /bin/bash 
참고. https://wotres.tistory.com/entry/docker-error-%ED%95%B4%EA%B2%B0%EB%B2%95-the-input-device-is-not-a-TTY-If-you-are-using-mintty-try-prefixing-the-command-with-winpty


    $ docker images            // 내려 받은 이미지 확인 
    $ docker ps -a             // ps : 현재 실행 중인 프로세스 , -a : 전부  
    $ docker start [컨테이너명]      // 컨테이너 실행
    $ docker stop [컨테이너명]
    $ docker attach [컨테이너명]     // 실행 중인 컨테이너 접속 
    $ docker rm [컨테이너명]    // 컨테이너 삭제 
    $ docker rmi [이미지명]     // 이미지 삭제 

// 우분투 업데이터 
# apt-get update 

// 우분투 업그레이드 
# apt-get upgrade

// 우분투 접속 
docker exec -it {container_id or name} /bin/bash    //컨테이너 접속하기 명령어  

// JDK 설치 
> apt-get install openjdk-11-jdk    <-> 삭제의 경우 apt-get purge openjdk*

> java -version
> javac -version

// JAVA_HOME 환경변수 설정 
> vi ~/.bashrc 

----------------------------
export JAVA_HOME=$(dirname $(dirname $(readlink -f $(which java))))
export PATH=$PATH:$JAVA_HOME/bin
----------------------------

> source ~/.bashrc 
> echo $JAVA_HOME      // 환경변수 설정 확인


```

## ping 유틸 설치 
```
$ apt-get install iputils-ping -y
```

## 우분투에 vim 설치 
```
$ apt-get install vim  -y    // root 아닌 경우 앞에 sudo 붙이기
```

## netstat 설치 (netstat, ifconfig 등 사용 가능)
```
> apt-get install net-tools
```

## zip, unzip 설치 
```
> apt-get install -y zip unzip
```

##  우분투 ssh 서버 구축 
https://davelogs.tistory.com/17?category=933085
```
> apt-get install openssh-server 
> dpkg -l | grep openssh    //관련 패키지 설치 확인
> apt-get install openssh-clients openssh-askpass -y    // 추가 설치

> service --status-all | grep + // 실행 중인 서비스 목록 확인 
> netstat -tnlp                 // 포트 확인 (22) 

//원격 접속 
ssh [id]@[address]
```

## vim 자동정렬 딘측키
https://wookiist.dev/1

## 하둡설치  // 우분투 
```
> apt-get install wget 

// https://www.apache.org/dyn/closer.cgi/hadoop/common/hadoop-2.10.2/hadoop-2.10.2.tar.gz

> wget https://dlcdn.apache.org/hadoop/common/hadoop-2.10.2/hadoop-2.10.2.tar.gz
> tar xvzf [압축파일명]
> vi ~/.bashrc 

------------------------------------------------------------------------------------------
    export JAVA_HOME=$(dirname $(dirname $(readlink -f $(which java))))
    export PATH=$PATH:$JAVA_HOME/bin
    export JAVA_OPTS="-Dfile.encoding=UTF-8"
    export CLASSPATH="." 
    export HADOOP_HOME=/home/hadoop_home/hadoop-2.10.2
    export HADOOP_CONFIG_HOME=$HADOOP_HOME/etc/hadoop
    export PATH=$PATH:$HADOOP_HOME/bin:$HADOOP_HOME/sbin
    //export PATH=$PATH:$HADOOP_HOME/sbin
------------------------------------------------------------------------------------------

> source ~/.bashrc

// 파일 내용 수정 
1. hadoop-env.sh

$ vim /home/hadoop_home/hadoop-2.10.2/etc/hadoop/hadoop-env.sh

```
  //export JAVA_HOME=${JAVA_HOME}     // 환경변수 설정되 잇으면 안해도 될듯 -> 위에꺼 주석 처리 해버림 

  // 아래 configuration 을 추가 후 저장하자
  export JAVA_HOME=/usr/lib/jvm/java-11-openjdk-amd64
  export HDFS_NAMENODE_USER="root"
  export HDFS_DATANODE_USER="root"
  export HDFS_SECONDARYNAMENODE_USER="root"
  export YARN_RESOURCEMANAGER_USER="root"
  export YARN_NODEMANAGER_USER="root"

```

// 각 데몬들이 사용할 홈 디펙토리 생성
$ cd /home/hadoop_home
$ mkdir temp 
$ mkdir datanode 
$ mkdir namenode

// 환경정보 설정 파일 3개 수정 (core-site.xml, core-site.xml, mapred-site.xml)
$ cd $HADOOP_CONFIG_HOME 

1. core-site.xml 
- HDFS와 MapReduce 에서 공통적으로 사용할 환경정보
- 파일 위치 : /home/hadoop_home/hadoop-2.10.2/etc/hadoop/core-site.xml     
- 매개변수 정보 : [https://hadoop.apache.org/docs/current/hadoop-project-dist/hadoop-common/core-default.xml](https://hadoop.apache.org/docs/current/hadoop-project-dist/hadoop-common/core-default.xml)

------------------------------------------------------------------------------------------
<configuration>
    <property>
            <name>hadoop.tmp.dir</name>
            <value>/root/soft/apache/hadoop/hadoop-2.7.7/tmp</value>     >> /home/hadoop_home/temp 경로 수정 후 저장
            <description>A base for other temporary directories.</description>
    </property>

    <property>
            <name>fs.default.name</name>           >> 해당 property는 deprecated 되었네.. --> fs.defaultFS 사용하길
            <value>hdfs://master:9000</value>     >> hdfs://master:9000 으로 수정      , localhost 문제있음..
            <final>true</final>
            <description>The name of the default file system.  A URI whose
            scheme and authority determine the FileSystem implementation.  The
            uri's scheme determines the config property (fs.SCHEME.impl) naming
            the FileSystem implementation class.  The uri's authority is used to
            determine the host, port, etc. for a filesystem.</description>
    </property>
</configuration>
------------------------------------------------------------------------------------------

2. hdfs-site.xml
- HDFS에서 사용할 환경 정보 
- 파일 위치 : /home/hadoop_home/hadoop-2.10.2/etc/hadoop/hdfs-site.xml
- 매개변수 정보 : [https://hadoop.apache.org/docs/r2.4.1/hadoop-project-dist/hadoop-hdfs/hdfs-default.xml](https://hadoop.apache.org/docs/r2.4.1/hadoop-project-dist/hadoop-hdfs/hdfs-default.xml)

------------------------------------------------------------------------------------------
<configuration>
    <property>
        <name>dfs.replication</name>
        <value>3</value>      >> 1로 수정 -> 3 
        <final>true</final>
        <description>Default block replication.
        The actual number of replications can be specified when the file is created.
        The default is used if replication is not specified in create time.
        </description>
    </property>

    <property>
        <name>dfs.namenode.name.dir</name>
        <value>/root/soft/apache/hadoop/hadoop-2.7.7/namenode</value>     >> /home/hadoop_home/namenode 수정 후 저장
        <final>true</final>
    </property>

    <property>
        <name>dfs.datanode.data.dir</name>
        <value>/root/soft/apache/hadoop/hadoop-2.7.7/datanode</value>    >> /home/hadoop_home/datanode 수정 후 저장
        <final>true</final>
    </property>
</configuration>
------------------------------------------------------------------------------------------

3. mapred-site.xml  -- 파일이 없고 템플릿만 있어서 복사 후 사용
- MapReduce에서 사용할 환경 정보
- 파일 위치 : /home/hadoop_home/hadoop-2.10.2/etc/hadoop
- 매개변수 정보 : [https://hadoop.apache.org/docs/r2.7.1/hadoop-mapreduce-client/hadoop-mapreduce-client-core/mapred-default.xml](https://hadoop.apache.org/docs/r2.7.1/hadoop-mapreduce-client/hadoop-mapreduce-client-core/mapred-default.xml)


$ cd /home/hadoop_home/hadoop-2.10.2/etc/hadoop
$ cp mapred-site.xml.template mapred-site.xml
$ vi mapred-site.xml 

------------------------------------------------------------------------------------------
<configuration>
    <property>

        <name>mapreduce.framework.name</name>
        <value>yarn</value>
    </property>

    <property>

        <name>mapred.job.tracker</name>
        <value>master:9001</value>
        <description>The host and port that the MapReduce job tracker runs
        at.  If "local", then jobs are run in-process as a single map
        and reduce task.
        </description>
    </property>

        <!-- job 내역 확인 가능한 web ui 접속간으 : localhost:19888-->
        <property>
                <name>mapreduce.jobhistory.webapp.address</name>
                <value>master:19888</value>
        </property>
    
</configuration>
------------------------------------------------------------------------------------------

4. yarn-site.xml 
- 매개변수 정보 : [https://hadoop.apache.org/docs/r2.7.6/hadoop-yarn/hadoop-yarn-common/yarn-default.xml](https://hadoop.apache.org/docs/r2.7.6/hadoop-yarn/hadoop-yarn-common/yarn-default.xml)

------------------------------------------------------------------------------------------
<configuration>

<!-- Site specific YARN configuration properties -->
  <property>
    <name>yarn.nodemanager.aux-services</name>
    <value>mapreduce_shuffle</value>
  </property>
  <property>
    <name>yarn.resourcemanager.address</name>
    <value>master:8032</value>
  </property>
  <property>
    <name>yarn.resourcemanager.scheduler.address</name>
    <value>master:8030</value>
  </property>
  <property>
    <name>yarn.resourcemanager.resource-tracker.address</name>
    <value>master:8031</value>
  </property>
  <property>
    <name>yarn.resourcemanager.admin.address</name>
    <value>master:8033</value>
  </property>
  <property>
    <name>yarn.resourcemanager.webapp.adress</name>
    <value>master:8088</value>
  </property>
</configuration>

------------------------------------------------------------------------------------------

// 네임노드 포맷 
> hadoop namenode -format 

// ssh 설치 
> apt-get install ssh -y 

// ~/.bashrc 수정 
#autorun  
/usr/sbin/sshd

> source ~/.bashrc

> exit  // exit 하면 docker container 꺼짐 -> ctlr + pq 하면 그냥 나가짐
> docker restart ubuntu_server 
> docker commit -m "hadoop install in ubuntu" ubuntu_server ubuntu_hadoop:hadoop      // 이미지가 생성되는 구나 
> docker images 

// master, slave 생성 
docker run -i -t -h master --name master -p 50070:50070 -p 8088:8088 ubuntu_hadoop:hadoop 
docker run -i -t -h slave1 --name slave1 --link master:master ubuntu_hadoop:hadoop 
docker run -i -t -h slave2 --name slave2 --link master:master ubuntu_hadoop:hadoop 

// --link 에 대해 간략 설명하자면 salve1은 master와 link를 맺게되게 컨테이너 이름으로 통신가능 (https://it-sunny-333.tistory.com/85)
// slave1 에 접속 후 ping master 하면 호출됨 <-> 반대로 master에서 slave1,2 ping으로 호출 가능함
> cat /etc/hosts 

  172.17.0.2      master master

> docker ps -a     // 실행확인

// master, slave 컨테이너 ip확인 
docker inspect master | grep IPAddress   
docker inspect slave1 | grep IPAddress   // 뭔가 ip 검사는 안되네.. 172.17.0.4
docker inspect slave2 | grep IPAddress   // 172.17.0.5

// 하둡 설정 및 구동
docker attach master          // 컨테이너 구동중이여야 함 


**cd /home/hadoop_home/hadoop-2.10.2/sbin/**

./start-all.sh // 이거 해도 에러..
./stop-all.sh  // 이거 해도 에러

 //자꾸 에러 발생  -> 이거는 hosts 에 선언 안한건데.. 비밀번호 틀리는건 stack overflow 봤을때 ssh 접속 하도록 해라네 
 slave2: ssh: Could not resolve hostname slave2: Name or service not known                                                     
 slave1: ssh: Could not resolve hostname slave1: Name or service not known                                                     

vi /etc/hosts 

  // 내용 추가
  172.17.0.3  master
  172.17.0.4  slave1
  172.17.0.5  slave2

// stop-all.sh나 start-all.sh 실행시 root password 오류가 계속 발생할 경우 
// https://stackoverflow.com/questions/15195048/hadoop-require-roots-password-after-enter-start-all-sh
// 동일한 내용 https://mungiyo.tistory.com/17

$ ssh-keygen -t rsa -P '' -f ~/.ssh/id_dsa
$ cat ~/.ssh/id_dsa.pub >> ~/.ssh/authorized_keys
$ ssh localhost       // 연결 확인 , 근데 뭐 ubuntu 가 minimized되었다고 설명만 뜨고 실행이 안됨 .. 

$ unminimize          // 설명에 적혀있는 키워드 입력하면 뭐 계속 뜸 
$ ssh localhost       // 뭔가 다름. 설명서 없고 버전이랑 Last login : 날짜 출력됨 

$ cd $HADOOP_HOME/sbin 
$ ./stop-all.sh        // 또 에러 뜨네 

  Error : JAVA_HOME is not set and could not be found        ---> hadoop-env.sh 파일에 JAVA_HOME 상단꺼로는 안되니.. 밑에 하드코딩 박아버림 --> 에러 제거됨
  localhost : Error : JAVA_HOME is not set and could not be found.

  slave2 : Host key verification failed.
  slave1 : Host key verification failed.

// 이것저것 설정 변경했으니 .. 
$ hadoop namenode -format      // ctrl + pq로 나감 
$ docker commit -m "hadoop updated in master" master ubuntu_hadoop:hadoop      // 도커 이미지 갱신을 해주자..

// 의문인게 master images를 commit 해서 재시작하는데.. slave1, slave2 설정이 갱신될까??
$ docker restart master 
$ docker restart slave1
$ docker restart slave2
$ dcoker exec -it slave1 /bin/bash // 확인차 slave1에 접속해보자 --> /home/hadoop 폴더에 아무것도 없네요..

$ docker stop slave1
$ docker stop slave2
$ docker rm slave1     // slave1 컨테이너 삭제
$ docker rm slave2 
$ docker ps -a       // 컨테이너 목록 확인

// slave1, slave2 재 생성 --> vim /home/hadoop_home/hadoop-2.10.2/etc/hadoop/hadoop-env.sh 확인해보니 master랑 동일
$ docker run -i -t -h slave1 --name slave1 --link master:master ubuntu_hadoop:hadoop 
$ docker run -i -t -h slave2 --name slave2 --link master:master ubuntu_hadoop:hadoop 

$ dokcer exec -it master /bin/bash 
$ ./start-all.sh         // all daemon start 

  // vi /etc/hosts 에 slave1,2 없어짐..  
  slave2 : ssh : could not resolve hostname slave2 : name or service not known
  slave1 : ssh : could not resolve hostname slave1 : name or service not known

  // hosts 수정후 위에껀 없어졌는데 이렇네.. 
  The authenticity of host 'slave1 (172.17.0.4)' can't be established
  The authenticity of host 'slave2 (172.17.0.5)' can't be established

  ED25519 key fingerprint is SHA256:eWNfxpdvzBCBlgVcO58vynK0eL1Y/HwhUJ/WK1Rf1lY.
  This host key is known by the following other names/addresses:
       ~/.ssh/known_hosts:1: [hashed name]
       ~/.ssh/known_hosts:2: [hashed name]
       ~/.ssh/known_hosts:3: [hashed name]
  slave2: Host key verification failed.
  slave1: Host key verification failed.



$ cd $HADOOP_HOME/sbin
$ ./stop-all.sh

  // 징글징글하다.. 
  The authenticity of host 'slave1 (172.17.0.4)' can't be established
  The authenticity of host 'slave2 (172.17.0.5)' can't be established

$ hadoop jar /home/hadoop_home/hadoop-2.10.2/share/hadoop/mapreduce/hadoop-mapreduce-examples-2.10.2.jar wordcount /home/hadoop_home/hadoop-2.10.2/LICENSE.txt wordcount_output  > log.txt 2>&1

  // 에러1) 징글징글하다.. 
  22/07/05 03:47:09 INFO client.RMProxy: Connecting to ResourceManager at master/172.17.0.3:8032
  java.net.ConnectException: Call From master/172.17.0.3 to localhost:9000 failed on connection exception: java.net.ConnectException: Connection refused; For more details see:  http://wiki.apache.org/hadoop/ConnectionRefused 

  // 에러2) core-site.xml 포트를 9000 -> 8088 수정하니 새로운 에러 
  java.io.IOException: Failed on local exception: org.apache.hadoop.ipc.RpcException: RPC response exceeds maximum data length;
  Host Details : local host is: "master/172.17.0.3"; destination host is: "localhost":8088;

  > 힌트 https://stackoverflow.com/questions/49060244/exception-org-apache-hadoop-ipc-rpcexception-rpc-response-exceeds-maximum-da
  > 포트는 9000, 8020만 가능하다해서 .. core-site.xml에 localhost:9000 하고 start-all.sh 재시작해서 돌리니 새로운 에러 확인

  // 에러3) 이게 dfs에 파일을 안올려서 그렇다는데? 그래서 밑에 예제 처럼 폴더/파일 올리고 하면 동작함✨
  22/07/05 04:32:32 INFO mapreduce.JobSubmitter: Cleaning up the staging area /tmp/hadoop-yarn/staging/root/.staging/job_1656989/hadoop-2.10.2/LICENSE.txt 
  org.apache.hadoop.mapreduce.lib.input.InvalidInputException: Input path does not exist: hdfs://localhost:9000/home/hadoop_home/hadoop-2.10.2/LICENSE.txt

// slave1, slave2 접속해서 /etc/hosts에 불필요한 부분 삭제


$ jps     //  최종결과 이런식으로 데몬 실행 목록 확인
1185 Jps
486 SecondaryNameNode
663 DataNode
807 NodeManager
299 ResourceManager
140 NameNode

// HDFS에 LICENSE.txt 파일 올림 
$ hadoop fs -mkdir /anderson
$ hadoop fs -put LICENSE.txt /anderson               // 로컬 파일시스템의 파일을 HDFS의 분산파일 시스템에 업로드
$ hadoop fs -ls /anderson 
-rw-r--r--   1 root supergroup     106210 2022-07-05 04:54 /anderson/LICENSE.txt

$ hadoop /home/hadoop_home/hadoop-2.10.2/share/hadoop/mapreduce/hadoop-mapreduce-examples-2.10.2.jar wordcount /anderson /anderson_result > log.txt 2>&1
$ hadoop fs -ls /anderson_result       // 목록 확인
$ hadoop fs -cat /anderson_result     // 결과 보기 .. 뭔가 나오긴 했네
or 
$ hadoop fs -text /anderson_result/part-r-0000          // 이게 결과파일

// 그외 
$ hadoop fs -get /adnerson_result   //폴더채로 분산파일 시스템 상의 파일을 로컬로 가져옴 

/*
  //2.10.1 버전은 포트가 19888인듯
  //MapReduce JobHistory Server Web UI host:port	Default port is 19888.
  > docker run -it --name hadoop-base -p 19888:19888 ubuntu_hadoop:hadoop // start-all.sh 재시작 했는데 안됨 .. 
*/
😅localhost:8088 접속하니 뭔가 hadoop cluster 페이지 접속됨 ! 
```


## ubuntu root 비밀번호 초기화 
> passwd root  // 통계는구라다!!

--- 
## Linux Shell script - Redirection(>, >>, 2>&1)
[https://etloveguitar.tistory.com/m/20](https://etloveguitar.tistory.com/m/20)

## linux history 명령어
[https://withcoding.com/107](https://withcoding.com/107)


-------


## 누락된 설정들 (https://1mini2.tistory.com/84)
```

1. Master 에서 slaves 설정 누락 

  # vi $HADOOP_HOME/etc/hadoop/slaves             
    전체 경로 :: /home/hadoop_home/hadoop-2.10.2/etc/hadoop/slaves

    v1.x 하둡의 경우 slaves 파일에 
    [마스터 ip 주소]
    [첫번째 슬레이브 IP주소]
    [두번째 슬레이브 IP주소]
    

    -- localhost  지워버리던가
    master             // 국룰인가봄(?), 경유한다면..
    slave1
    slave2

2. master, slave1/2 각각 적용
  2-1. slave1, 2 서버에 hosts 수정 

    172.17.0.3  master master       // 이거는 docker run 할때 --link 옵션 줘서 이럼
    172.17.0.4  slave1
    172.17.0.5  slave2

  2-2. slave 서버에 ssh 키 생성 및 ssh 설정 변경 
    $ ssh-keygen -t rsa -P '' -f ~/.ssh/id_dsa
    $ cat ~/.ssh/id_dsa.pub >> ~/.ssh/authorized_keys
    $ ssh localhost       

    $ vi /etc/ssh/sshd_config
      // 주석 해제
      PermitRootLogin yes       
      PasswordAuthentication yes

    //$ systemctl restart sshd  // 대몬 재시작 .. 안되네..  sudo service sshd  restart 안됨
    $ sudo passwd root      // 통계는구라다!!

    // sshd 재시작 관련.. 
    apt-get install openssh-server 
    netstat -tnlp | grep sshd       // 실행 확인 
    /etc/init.d/ssh restart         // 동작확인됨

    # docker restart slave1  // slave2도 설정 후 재시작

3. Master 서버 접속후 slave1, slave2 와 키 교환
   // master 에서만 수행 
   $ ssh-copy-id -f root@slave1 
   $ ssh-copy-id -f root@slave2

4. Master 서버는 NameNode 포맷, Slave는 DataNode 포맷 실행 
   # Master 서버에서 
    $ hadoop namenode -format       // hadoop 명령어가 설정되어 있는데 없으면 /usr/local/hadoop-2.10.1/bin/hdfs namenode -format 으로 실행 

   # NameNode 서버에서 
    $ hadoop datanode -format 

  //각각 실행 후 /data 폴더가 생성된다함   

5. HDFS , YARN 재시작 

  # 한꺼번에 시작 
    $ cd $HADOOP_HOME/sbin 
    $ ./start-all.sh

  # 각각 따로 시작 
  ## HDFS 
    $ cd $HADOOP_HOME/sbin 
    $ ./start-dfs.sh

  ## YARN   
    $ cd $HADOOP_HOME/sbin 
    $ ./start-yarn.sh 

ㅁ HDFS Web UI : http://<Master IP>:50070
ㅁ YARN Web UI : http://<Master IP>:8088


6. HDFS , YARN 종료  
  # 한꺼번에 종료
    $ cd $HADOOP_HOME/sbin 
    $ ./stop-all.sh

  # 각각 따로 종료 ( yarn 먼저 종료 ?🤔)
  ## YARN 
    $ cd $HADOOP_HOME/sbin 
    $ ./stop-yarn.sh

  ## HDFS 
    $ cd $HADOOP_HOME/sbin 
    $ ./stop-dfs.sh


# 이슈 
  > namenode 가 켜지지 않는 이슈 존재 (50070포트 웹페이지 접속 안됨)
  // sbin 폴더에서 
  ./hadoop-daemon.sh start datanode 
```

## hadoop 명령어 입력시 WARING 로그 관련 

```bash
root@master:/# hadoop fs -ls / 
WARNING: An illegal reflective access operation has occurred 
WARNING: Illegal reflective access by org.apache.hadoop.security.authentication.util.KerberosUtil (file:/home/hadoop_home/hado
op-2.10.2/share/hadoop/common/lib/hadoop-auth-2.10.2.jar) to method sun.security.krb5.Config.getInstance()
WARNING: Please consider reporting this to the maintainers of org.apache.hadoop.security.authentication.util.KerberosUtil
WARNING: Use --illegal-access=warn to enable warnings of further illegal reflective access operations 
WARNING: All illegal access operations will be denied in a future release 
```

#### 관련 stackoverflow
[https://stackoverflow.com/questions/52155078/how-to-fix-hadoop-warning-an-illegal-reflective-access-operation-has-occurred-e](https://stackoverflow.com/questions/52155078/how-to-fix-hadoop-warning-an-illegal-reflective-access-operation-has-occurred-e)

> 요약하자면, java version과 라이브러리(hadoop-auth-2.10.2.jar) 관련되어 출력하는 로그로 직접 설정 고칠 수 있는게 없음. 단, hadoop 3.x 버전부터 fixed 되었다함.

#### hadoop log level 변경에 대해 찾아보자. -> 근데 라이브러리에서 발생하는 로그인데 제어가 가능한가 싶음.


#### 하둡 에러 
hadoop fs -mkdir -p /hadoop-dir/mydir01
hadoop fs -mkdir -p /hadoop-dir/mydir02

echo "this is test file 01" > testfile01.txt
echo "this is test file 02" > testfile02.txt

hadoop fs -copyFromLocal testfile01.txt /hadoop-dir/mydir01          // 아래 에러 출력 
hadoop fs -copyFromLocal testfile01.txt /hadoop-dir/mydir02
```
- 데이터 노드가 없구나 .. jps 확인해보니 확인 
22/07/06 05:09:52 WARN hdfs.DataStreamer: DataStreamer Exception
eplicated to 0 nodes instead of minReplication (=1).  There are 0 datanode(s) running and no node(s) are excluded in this operation

# 참고
https://stackoverflow.com/questions/26545524/there-are-0-datanodes-running-and-no-nodes-are-excluded-in-this-operation?answertab=trending#tab-top
https://stackoverflow.com/questions/11889261/datanode-process-not-running-in-hadoop
```

hadoop fs -ls -R /                           // 나니모 나캇다 

```
일단 hadoop namenode -format 을 재실행하면 
/tmp 에 생성되는 h**_유저아이디 폴더삭제 해주고.. (중요한듯..)
다 삭제 후 재 생성 
/home/hadoop_home/datanode
/home/hadoop_home/namenode 
/home/hadoop_home/temp 

chmod -R 755 datanode 
chmod -R 755 namenode 
chmod -R 755 temp 

hadoop namenode -format        // tmp 폴더 안에 또 생성해줌

> ./hadoop-daemon.sh start datanode        // ***************** datanode 올라옴

하둡 로그 관련 참고 >> https://myeonguni.tistory.com/1472
https://www.edureka.co/community/28196/datanode-process-not-running-in-hadoop


// 당연히 하둡에 올라와 있어야 하는데.. 
hadoop jar /home/hadoop_home/hadoop-2.10.2/share/hadoop/mapreduce/hadoop-mapreduce-examples-2.10.2.jar wordcount /hadoop-dir/mydir01/testfile01.txt wordcount_output

에러..) 느낌이 slave1에서는 master의 ssh 키가 없어서.. slave끼리 교환했는데 master는 안되노 ;; ssh-copy-id root@master
java.net.ConnectException: Call From slave1/172.17.0.4 to localhost:9000 failed on connection exception: java.net.ConnectExce
ption: Connection refused; For more details see:  http://wiki.apache.org/hadoop/ConnectionRefused

> vim /etc/ssh/sshd_config  ✨✨🤔
  // 수정했는데 반영이 .. 
  PermitRootLogin yes  
  PasswordAuthentication yes 

> /etc/init.d/ssh restart 
> netstat -tnlp | grep sshd

// slave1, slave2에서 각각 실행하면 동작함 
> ssh-copy-id -f root@master

// 우욱 .. wordcount 실행해도 에러 
  > vim /home/hadoop_home/hadoop-2.10.2/etc/hadoop/core-site.xml   // localhost:9000 을 master:9000으로 수정

  > ./stop-all.sh
  > ./start-all.sh 

에러)org.apache.hadoop.hdfs.server.namenode.SafeModeException: Cannot delete /tmp/hadoop-yarn/staging/root/.staging/job_1657090927  Name node is in safe mode.
  // safe mode 종료 
  > hdfs dfsadmin -safemode leave 
  또는 
  > hdfs dfsadmin -safemode forceExit
  // 상태 확인
  > hdfs dfsadmin -safemode get


에러)  DataStreamer Exception
eplicated to 0 nodes instead of minReplication (=1).  There are 0 datanode(s) running and no node(s) are excluded in this operation
// 데이터 노드 재시작 ($HADOOP_HOME/sbin) , start-all.sh || stop-all.sh 와 별개로 동작하는 듯함
  > ./hadoop-daemon.sh stop datanode 
  > ./hadoop-daemon.sh start datanode 

// 결과 추출 됨 .. ✨🤔🤔😁

  # echo "this is test file 01" > testfile01.txt

  # hadoop fs -mkdir -p /hadoop-dir/mydir01
  # hadoop fs -copyFromLocal testfile01.txt /hadoop-dir/mydir01

  # hadoop fs -ls -R / 

  # hadoop jar /home/hadoop_home/hadoop-2.10.2/share/hadoop/mapreduce/hadoop-mapreduce-examples-2.10.2.jar wordcount /hadoop-dir/mydir01/testfile01.txt wordcount_output
  # hadoop fs -text /wordcount/part-r-00000
  01      1
  file    1
  is      1
  test    1
  this    1


에러..) 파일 있어서 이럼.. 
org.apache.hadoop.mapred.FileAlreadyExistsException: Output directory hdfs://localhost:9000/user/root/wordcount_output already exists
> hadoop fs -ls /     
> hadoop fs -rmr /wordcount*

참고 ) How to track which data block is in which data node in hadoop?
# hadoop fsck /hadoop-dir/mydir01/testfile01.txt -files -blocks -locations 

```
