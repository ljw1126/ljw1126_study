## 참고 기술 블로그 

**wikidoc**
[https://wikidocs.net/37809](https://wikidocs.net/37809)

[https://1mini2.tistory.com/83](https://1mini2.tistory.com/83)

> centos 기반인데 .. 나는 ec2 서버에 ubuntu 로 해서 명령어가 다름

**하둡2.10.1 설치**
[https://1mini2.tistory.com/84](https://1mini2.tistory.com/84)
[https://hoing.io/archives/22174](https://hoing.io/archives/22174)

**centos7 기반 설치**
[https://www.tecmint.com/install-hadoop-single-node-on-centos-7/](https://www.tecmint.com/install-hadoop-single-node-on-centos-7/)

## DATA ON-AIR 하둡 설치 1.x, 2.x **
[https://dataonair.or.kr/db-tech-reference/d-guide/data-practical/?pageid=2&mod=document&keyword=hadoop&uid=385](https://dataonair.or.kr/db-tech-reference/d-guide/data-practical/?pageid=2&mod=document&keyword=hadoop&uid=385)


**하둡NOde설명**
[https://blog.geunho.dev/posts/hadoop-namenode/#journalnode](https://blog.geunho.dev/posts/hadoop-namenode/#journalnode)

인스턴스 생성 ( ubuntu에 무료 사양, 생략 )

pem 키 신규 생성해서 접속
[https://docs.aws.amazon.com/ko_kr/AWSEC2/latest/UserGuide/AccessingInstancesLinux.html](https://docs.aws.amazon.com/ko_kr/AWSEC2/latest/UserGuide/AccessingInstancesLinux.html 'ec2 접속 가이드')

```bash
> cd ~/.ssh 
> cp /mnt/c/Users/Administrator/Desktop/파일정리/스터디/sampleKeypair.pem ./ 
> ssh -i sampleKeypair.pem ubuntu@13.125.251.186

@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
@         WARNING: UNPROTECTED PRIVATE KEY FILE!          @
@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
Permissions 0755 for 'sampleKeypair.pem' are too open.
It is required that your private key files are NOT accessible by others.       >> 755 권한은 안전하지 못하니 변경 해줘라는 뜻인듯
This private key will be ignored.
Load key "sampleKeypair.pem": bad permissions
ubuntu@13.125.251.186: Permission denied (publickey).

> chmod -R 600 ~/.ssh/sampleKeypair.pem
> ssh -i sampleKeypair.pem ubuntu@13.125.251.186      // 접속 성공✨

> cat /etc/*release                                  // OS 확인 
> whoami                                             // ubuntu (당연😅)

// 기본 설정
> sudo apt-get update               
> sudo apt-get install net-tools -y        // 이미 깔려있는것도 있음 

> sudo apt-get install openjdk-11-jdk -y 
> java -version

// JAVA_HOME 환경변수 설정 
> vi ~/.bashrc                  // centos는 /etc/profile 에 설정하는듯👨‍💻

    ----------------------------
    export JAVA_HOME=$(dirname $(dirname $(readlink -f $(which java))))
    export PATH=$PATH:$JAVA_HOME/bin
    ----------------------------

> source ~/.bashrc 
> echo $JAVA_HOME      // 환경변수 설정 확인
```

## 하둡 설치 
```bash
> sudo su          // root로 전환
> wget https://dlcdn.apache.org/hadoop/common/hadoop-2.10.2/hadoop-2.10.2.tar.gz
> cd /usr/local                //여기에 설치하려는듯

> tar -zxvf ./hadoop-2.10.2.tar.gz -C /usr/local/

> chown -R root:root /usr/local/hadoop-2.10.2/
> ls -al /usr/local/

> vim ~/.bashrc             // ubuntu로 export 설정한 내용이 없음.. root와 ubuntu 각각 설정파일을 구분 지어 주는 것으로 판단 
> exit                      // ubuntu 전환 
> vim ~/.bashrc             // real로 계정 별로 사용자 설정이 다랐다 ! 

  ----------------------------
    # hadoop setup
    export HADOOP_HOME=/usr/local/hadoop-2.10.2
    export HADOOP_CONFIG=$HADOOP_HOME/etc/hadoop
    export JAVA_HOME=$(dirname $(dirname $(readlink -f $(which java))))
    export PATH=$PATH:$JAVA_HOME/bin:$HADOOP_HOME/bin/:$HADOOP_HOME/sbin
  ----------------------------

> source ~/.bashrc 
> echo $HADOOP_HOME
    /usr/local/hadoop-2.10.2
> echo $HADOOP_CONFIG
    /usr/local/hadoop-2.10.2/etc/hadoop

// hadoop-env.sh 설정 수정 
$ echo $JAVA_HOME
    /usr/lib/jvm/java-11-openjdk-amd64
$ sudo vim $HADOOP_CONFIG/hadoop-env.sh    
    ----------------------------
    export JAVA_HOME=/usr/lib/jvm/java-11-openjdk-amd64
    ----------------------------
```

## 하둡 공식 사이트 (Deprecated Properties)
[https://hadoop.apache.org/docs/r2.10.1/hadoop-project-dist/hadoop-common/DeprecatedProperties.html](https://hadoop.apache.org/docs/r2.10.1/hadoop-project-dist/hadoop-common/DeprecatedProperties.html)
[https://hadoop.apache.org/docs/current/hadoop-project-dist/hadoop-common/DeprecatedProperties.html](https://hadoop.apache.org/docs/current/hadoop-project-dist/hadoop-common/DeprecatedProperties.html)

## 하둡 설정 파일 설정하기
1. core-site.xml

  > 로그파일, 네트워크 튜닝, I/O 튜닝, 파일시스템 튜닝, 압축 등 시스템 설정 파일
  > HDFS와 MapReduce에서 공통적으로 사용할 환경 정보를 입력하게 되며, core-default.xml이 기본값이고, core-site.xml통해 설정 override 하여 사용함
  > 매개변수 정보 : https://hadoop.apache.org/docs/current/hadoop-project-dist/hadoop-common/core-default.xml

  $ sudo vim $HADOOP_CONFIG/core-site.xml     
  
  ```xml
    // NameNode 데몬이 실행중인 위치 나타냄. master:9000 서버에서 NameNode 실행중
    <configuration>
        <property>
                <name>fs.defaultFS</name>
                <value>hdfs://ec2-54-180-80-163.ap-northeast-2.compute.amazonaws.com:9000</value>       <!--aws ec2에서는 public dbs 으로 해야함 -->
        </property>
    </configuration>
  ```



2. hdfs-site.xml
  
  > HDFS에서 사용할 환경정보 설정. hdfs-default.xml이 기본값이며, hdfs-site.xml 통해 설정 override 하여 사용함 
  > 매개변수 정보 : https://hadoop.apache.org/docs/r2.4.1/hadoop-project-dist/hadoop-hdfs/hdfs-default.xml

  $ sudo vim $HADOOP_CONFIG/hdfs-site.xml

  ```xml 
    <configuration>
        <property>
            <name>dfs.namenode.name.dir</name>
            <value>file:///data/namenode</value> 
            <!-- NameNode에서 네임스페이스와 트랜잭션 로그를 지속적으로 저장하는 로컬파일 시스템 경로 (fsImage, editlog)-->
        </property>
        <property>
            <name>dfs.datanode.data.dir</name>
            <value>file:///data/datanode</value> 
            <!-- 실제 데이터를 저장할 데이터 노드의 로컬 파일 시스템 경로 -->
        </property>
        <property>
            <name>dfs.namenode.checkpoint.dir</name>
            <value>file:///data/namesecondary</value> 
            <!-- DFS 보조 네임노드가 병합되어야할 임시 이미지를 저장하는 로컬파일 시스템의 경로-->
        </property>
        <property>
            <name>dfs.replication</name>
            <value>3</value> <!-- 블록 복제 수를 지정-->
        </property>
    </configuration>
  ``` 

3. mapred-site.xml

   > MapReduce 어플리케이션 설정파일 
   > 매개변수 정보 : https://hadoop.apache.org/docs/r2.7.1/hadoop-mapreduce-client/hadoop-mapreduce-client-core/mapred-default.xml


   $ sudo vim $HADOOP_CONFIG/mapred-site.xml

   ```xml
        <configuration>
            <property>
                <name>mapreduce.framework.name</name>
                <value>yarn</value>
                <!-- MapReduce 실행 프레임워크를 Hadoop YARN으로 설정합니다.-->
            </property>
        </configuration>
   ```

4. yarn-site.xml 

  > Resource Manager 및 Node Manager 에 대한 구성을 정의함 
  > 매개변수 정보 : https://hadoop.apache.org/docs/r2.7.6/hadoop-yarn/hadoop-yarn-common/yarn-default.xml

  $ sudo vim $HADOOP_CONFIG/yarn-site.xml

  ```xml
    <configuration>
        <property>
            <name>yarn.nodemanager.local-dirs</name>
            <value>file:///data/yarn/local</value>
            <!-- 중간 데이터가 기록될 로컬 파일 시스템의 목록입니다. 다중 경로는 disk I/O를 분산하는데 도움됨-->
        </property>
        <property>
            <name>yarn.nodemanager.log-dirs</name>
            <value>file:///data/yarn/logs</value>
            <!-- 로그가 기록될 로컬파일 시스템의 목록. 다중 경로는 disk I/O 분산하는데 도움됨(?)-->
        </property>
        <property>
            <name>yarn.resourcemanager.hostname</name>
            <value>ip-172-31-41-173.ap-northeast-2.compute.internal</value>       <!--aws 에서는 private dns 를 적어줘야함 , 요거만 적어주면 나머지 파람은 포트만 바뀌는걸로 변경됨 2.10.2-->
            <!-- 리소스 매니저 데몬이 동작하고 잇는 호스트 위치-->
        </property>

        <property>
            <name>yarn.nodemanager.aux-services</name>
            <value>mapreduce_shuffle</value>
        </property>
        <property>
            <name>yarn.nodemanager.aux-services.mapreduce_shuffle.class</name>
            <value>org.apache.hadoop.mapred.ShuffleHandler</value>
        </property>

    </configuration>
  ```

## EC2 복제하기 
- 현재 설정해둔 Master.hadoop 기준으로 이미지 생성
- 생성한 이미지로 Slave.hadoop 인스턴스 3개 생성 
  - AMI 이미지 생성시 "AMI is xxxx pending, and cannot be run" 에러 출력 
  - AMI 이미지 탬플릿 생성 후 다시 만들면 되기는 함.


## Master 서버 설정 
1. Hostname 변경 
   $ sudo hostnamectl set-hostname master.hadoop 
   $ hostnamectl

     Static hostname: master.hadoop
       Icon name: computer-vm
         Chassis: vm
      Machine ID: a51cc3f7851142cc859828bfae9edd56
         Boot ID: f10ee558768d47ed9b1ea49ba6bd7261
  Virtualization: xen
Operating System: Ubuntu 22.04 LTS
          Kernel: Linux 5.15.0-1011-aws
    Architecture: x86-64
 Hardware Vendor: Xen
  Hardware Model: HVM domU

2. /etc/hosts 파일 수정 
    
   $ sudo vim /etc/hosts 
    // public dns (이걸로 설정해야 함 !! master / slave )
    172.31.41.173 ec2-54-180-80-163.ap-northeast-2.compute.amazonaws.com master.hadoop master
    172.31.34.225 ec2-15-164-163-43.ap-northeast-2.compute.amazonaws.com slave1.hadoop slave1
    172.31.45.115 ec2-54-180-109-71.ap-northeast-2.compute.amazonaws.com slave2.hadoop slave2
    172.31.42.121 ec2-15-164-212-171.ap-northeast-2.compute.amazonaws.com slave3.hadoop slave3

    // private dns 
    ping ip-172-31-41-173.ap-northeast-2.compute.internal  
    ping ip-172-31-34-225.ap-northeast-2.compute.internal  
    ping ip-172-31-45-115.ap-northeast-2.compute.internal  
    ping ip-172-31-42-121.ap-northeast-2.compute.internal  

3. ssh 키 생성 및 ssh 설정 변경 
    
    $ sudo ssh-keygen            // 테스트 용도라 간단하게 생성 
    $ sudo vi /etc/ssh/sshd_config 
    
    ```bash
        PermitRootLogin yes  
        PasswordAuthentication yes 
    ```
    $ sudo /etc/init.d/ssh restart      // ubuntu인 경우 , linux인 경우 sudo systemctl restart sshd
    $ sudo netstat -tnlp | grep sshd

4. root 비밀번호 변경
   $ sudo passwd root          // 통계는구라다!! 

5. Slaves 파일 설정
   
   > Master 서버에서만 실행 

   $ sudo vim $HADDOP_CONFIG/slaves 

   // 추가 후 저장(public aws dns 추가해줘야 함)
   ec2-15-164-163-43.ap-northeast-2.compute.amazonaws.com 
   ec2-54-180-109-71.ap-northeast-2.compute.amazonaws.com
   ec2-15-164-212-171.ap-northeast-2.compute.amazonaws.com

--- 

## Slave 1~3 서버 설정 
   $ cd ~/.ssh 
   slave1 >> ssh -i sampleKeypair.pem ubuntu@15.164.170.18
   slave2 >> ssh -i sampleKeypair.pem ubuntu@13.124.203.216
   slave3 >> ssh -i sampleKeypair.pem ubuntu@3.34.130.163

1. Hostname 변경 

   $ sudo hostnamectl set-hostname slave1.hadoop 
   $ hostnamectl

2. /etc/hosts 수정 (동일)
3. ssh 키 생성 및 ssh 설정 변경 (동일)
4. root 비밀번호 변경 (동일)

5. (공통) master 서버와 key 교환 
    $ sudo su 
    $ ssh-copy-id root@master 

6. master에서는 slave1,2,3 ssh key 가져오기 
    $ sudo su
    $ ssh-copy-id root@slave1 
    $ ssh-copy-id root@slave2 
    $ ssh-copy-id root@slave3 



## Master 서버에서 NameNode 포맷 

    $ /usr/local/hadoop-2.10.2/bin/hdfs namenode -format

    > hdfs-site.xml 에서 설정한 대로 /data/namenode 폴더 생성되고 초기화됨

 ## 각각 slave 1,2,3 서버에서 dataNode포맷 

     $ /usr/local/hadoop-2.10.2/bin/hdfs datanode -format  

 ## Master 서버에서 HDFS시작
    $ /usr/local/hadoop-2.10.2/sbin/start-dfs.sh         // namenode와 scenodaryNameNode 노드 올라오 

    에러)java.net.BindException: Problem binding to [master:9000] java.net.BindException: Cannot assign requested address; For more details see:  http://wiki.apache.org/hadoop/BindException      
    > If you are running on EC2, your service is trying to explicitly bind a public Elastic IP address using the public hostname or IP, or implicitly using "0.0.0.0" as the address.

    > core-site.xml 에서 master:9000 대신 0.0.0.0:9000 하고 재시작하니 됨 

    에러) 마스터IP:50070 접속 안됨 

    > AWS ec2 의 인바운드 규칙 수정 필요 
    > https://inkkim.github.io/hadoop/AWS-EC2%EC%97%90%EC%84%9C-Hadoop-Cluster-%EA%B5%AC%EC%B6%95%ED%95%98%EA%B8%B0/

## yarn 시작 
    $ /usr/local/hadoop-2.10.2/sbin/start-yarn.sh       // resourceManage(MASTER)/nodeManager(slave*) 각각 올라와야함

    에러) resourceManager가 안올라옴 -> slog 에 resource 매니저 관련 파일이 있음 .. 

    2022-07-07 04:59:34,893 FATAL org.apache.hadoop.yarn.server.resourcemanager.ResourceManager: Error starting ResourceManager org.apache.hadoop.yarn.webapp.WebAppException: Error starting http server
    Caused by: java.net.BindException: Port in use: master:8088

    > yarn-site.xml에 복붙할때 configuration 태그가 한겹 더 들어갔었음..
    > https://hadoop.apache.org/docs/r2.10.2/hadoop-yarn/hadoop-yarn-common/yarn-default.xml
    > 보니깐 hostname 이 master.hadoop이든 master든 못찾네..  0.0.0.0 으로 수정하니 정상동작함
    > 참고 https://stackoverflow.com/questions/22846028/hadoop-2-2-0-resourcemanager-fails-to-bind-to-port-8088
        >> 설정에서 포트를 박아버리니.. 

./start-all.sh 시작하려니 
deprecated 되고 start-dfs.sh 와 start-yarn.sh 가 대신 실행된다고 로그 뜸     


http://13.125.251.186:8088
http://13.125.251.186:50070

## 태스트 하는데 안된다.. 
- core-site.xml에 기본 포트가 8020 이었음 
    INFO ipc.Client: Retrying connect to server: 0.0.0.0/0.0.0.0:8020. Already tried
    > core-site.xml 에 포트를 9000으로 수정함

    - 각 서버에 core-site.xml , yarn-site.xml 수정함.. 
        - yarn-site에 태그 잘못 들어간거.. 
        - core-site.xml에 master 인식 못하는거 수정 -> 13.125.251.186

        org.apache.hadoop.ipc.Client: Retrying connect to server: master.hadoop/13.125.251.186:8031.

- 포맷 부터 꼬인거 같음.. 설정이 잘못되엇는데 .. 
  /data 폴더 각 서버 별로 다 지우고 , format 다시하자          

   $ /usr/local/hadoop-2.10.2/bin/hdfs namenode -format
    $ /usr/local/hadoop-2.10.2/bin/hdfs datanode -format  

    에러) 
    ssh: Could not resolve hostname slave3: Temporary failure in name resolution
    ssh: Could not resolve hostname slave2: Temporary failure in name resolution
    ssh: Could not resolve hostname slave1: Temporary failure in name resolution


    wordcount 실행하니.. resourcemanager 꼬라지가 이럼 ..
    에러) 22/07/07 09:17:40 INFO client.RMProxy: Connecting to ResourceManager at ip-172-31-41-173.ap-northeast-2.compute.internal/172.3
    > yarn-site.xml
    > https://hadoop.apache.org/docs/r2.10.2/hadoop-yarn/hadoop-yarn-common/yarn-default.xml 
    > 설명 보면 포트는 고정이라서 hostname만 넣어주면 됨
    > 8088 포트 안올라옴 

    org.apache.hadoop.io.retry.RetryInvocationHandler: org.apache.hadoop.net.ConnectTimeoutException:
    Call From master.hadoop/13.125.251.186 to slave2.hadoop:38147 failed on socket timeout exception: org.apache.hadoop.net.Conne ctTimeoutException: 20000 millis timeout while waiting for channel to be ready for connect. ch : java.nio.channels.SocketChann 
    el[connection-pending remote=slave2.hadoop/13.124.203.216:38147]; For more details see:  http://wiki.apache.org/hadoop/SocketTimeout, while invoking ContainerManagementProtocolPBClientImpl.startContainers over null. Retrying after sleeping for 10000ms.


    에러) 
    hdfs.DataStreamer: Caught exception java.lang.InterruptedException

    에러) 
    22/07/07 10:04:12 INFO ipc.Client: Retrying connect to server: 0.0.0.0/0.0.0.0:10020. Already tried 9 time(s); retry policy is RetryUpToMaximumCountWithFixedSleep(maxRetries=10, sleepTime=1000 MILLISECONDS)


    java.io.IOException: java.net.ConnectException: Your endpoint configuration is wrong; For more details see:  http://wiki.apache.org/hadoop/UnsetHostnameOrPort

    >> https://stackoverflow.com/questions/17930644/connection-error-in-apache-pig
    >> mapreduce.jobhistory.address	|| 0.0.0.0:10020    기본 설정이 되어있네 2.10.2 부터 
    
    $ mr-jobhistory-daemon.sh start historyserver        // 하면 올라오는데..


    >> /etc/hosts 파일과 hadoop 설정파일에서 namenode 주소는 aws public dns 를 사용해야했음..(정답)

    에러) Container launch failed for container_1657242159385_0001_01_000004 : org.apache.hadoop.yarn.exceptions.InvalidAuxServiceException: The auxService:mapreduce_shuffle does not exist

    > yarn-site.xml 에 추가 (https://stackoverflow.com/questions/30921838/auxservicemapreduce-shuffle-does-not-exist-on-hive)

     <property>
        <name>yarn.nodemanager.aux-services</name>
        <value>mapreduce_shuffle</value>
    </property>
    <property>
        <name>yarn.nodemanager.aux-services.mapreduce_shuffle.class</name>
        <value>org.apache.hadoop.mapred.ShuffleHandler</value>
    </property>

    yarn.nodemanager.aux-services.mapreduce_shuffle.class 는 default 같은데..

# 최종 테스트 커맨드 
 ```

$ cd /usr/local/hadoop-2.10.2/bin

$ ./hadoop fs -ls -R /
$ ./hadoop fs -mkdir /anderon 

$ echo "this is test file 01" > testfile01.txt
$ ./haddop fs -copyFromLocal testfile01.txt /anderson

$ ./hadoop fs -ls -R /anderson

$ ./hadoop jar /usr/local/hadoop-2.10.2/share/hadoop/mapreduce/hadoop-mapreduce-examples-2.10.2.jar wordcount /anderson/testfile01.txt /result

$ ./hadoop fs -text /result/파일명          // 결과 확인

 ```   



 /usr/local/hadoop-2.10.2/bin/hadoop namenode -format
/usr/local/hadoop-2.10.2/bin/hadoop datanode -format
