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

## 
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

## 우분투에 vim 설치 
```
$ apt-get install vim  -y    // root 아닌 경우 앞에 sudo 붙이기
```

##  우분투 ssh 서버 구축 
https://davelogs.tistory.com/17?category=933085
```
> apt-get install openssh-server 
> dpkg -l | grep openssh    //관련 패키지 설치 확인

> service --status-all | grep + // 실행 중인 서비스 목록 확인 
> netstat -tnlp                 // 포트 확인 (22) 

//원격 접속 
ssh [id]@[address]
```

## netstat 설치 
```
> apt-get install net-tools
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
    export HADOOP_HOME=/home/hadoop_home/hadoop-2.10.2
    export HADOOP_CONFIG_HOME=$HADOOP_HOME/etc/hadoop
    export PATH=$PATH:$HADOOP_HOME/bin
    export PATH=$PATH:$HADOOP_HOME/sbin
------------------------------------------------------------------------------------------

> source ~/.bashrc

// 파일 내용 수정 
1. hadoop-evn.sh
/home/hadoop_home/hadoop-2.10.2/etc/hadoop/hadoop-env.sh
> export JAVA_HOME=${JAVA_HOME}     // 환경변수 설정되 잇으면 안해도 될듯 

2. core-site.xml 
/home/hadoop_home/hadoop-2.10.2/etc/hadoop/core-site.xml

------------------------------------------------------------------------------------------
<configuration>
    <property>
            <name>hadoop.tmp.dir</name>
            <value>/root/soft/apache/hadoop/hadoop-2.7.7/tmp</value>     >> /home/hadoop_home/hadoop-2.10.2/tmp 수정 후 저장
            <description>A base for other temporary directories.</description>
    </property>

    <property>
            <name>fs.default.name</name>
            <value>hdfs://master:9000</value>
            <final>true</final>
            <description>The name of the default file system.  A URI whose
            scheme and authority determine the FileSystem implementation.  The
            uri's scheme determines the config property (fs.SCHEME.impl) naming
            the FileSystem implementation class.  The uri's authority is used to
            determine the host, port, etc. for a filesystem.</description>
    </property>
</configuration>
------------------------------------------------------------------------------------------

3. hdfs-site.xml 
/home/hadoop_home/hadoop-2.10.2/etc/hadoop/hdfs-site.xml

------------------------------------------------------------------------------------------
<configuration>
    <property>
        <name>dfs.replication</name>
        <value>3</value>
        <final>true</final>
        <description>Default block replication.
        The actual number of replications can be specified when the file is created.
        The default is used if replication is not specified in create time.
        </description>
    </property>

    <property>
        <name>dfs.namenode.name.dir</name>
        <value>/root/soft/apache/hadoop/hadoop-2.7.7/namenode</value>     >> /home/hadoop_home/hadoop-2.10.2/namenode 수정 후 저장
        <final>true</final>
    </property>

    <property>
        <name>dfs.datanode.data.dir</name>
        <value>/root/soft/apache/hadoop/hadoop-2.7.7/datanode</value>    >> /home/hadoop_home/hadoop-2.10.2/datanode 수정 후 저장
        <final>true</final>
    </property>
</configuration>
------------------------------------------------------------------------------------------

4. mapred-site.xml  -- 파일이 없고 템플릿만 있어서 복사 후 사용
> cd /home/hadoop_home/hadoop-2.10.2/etc/hadoop
> cp mapred-site.xml.template mapred-site.xml
> vi mapred-site.xml 

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
</configuration>
------------------------------------------------------------------------------------------

5. yarn-site.xml 

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

> docker ps -a     // 실행확인

// slave 컨테이너 ip확인 
docker inspect slave1 (172.17.0.4)          // 뭔가 ip 검사는 안되네..
docker inspect slave2 (172.17.0.5) 

// 하둡 설정 및 구동
docker attach master          // 컨테이너 구동중이여야 함 


/home/hadoop_home/hadoop-2.10.2/sbin/start-all.sh
./start-all.sh

```


