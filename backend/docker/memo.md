##

#### docker-compose.yml 작성 
```
spring :
  profile :
    active : local
  batch :
    job :
      name : ${job.name:NONE}

---
spring :
  config :
    activate :
      on-profile : local
  datasource :
    url : jdbc:mysql://127.0.0.1:3306/house_batch
    drive-class-name : com.mysql.cj.jdbc.Driver
    username : house
    password : house
  jpa :
    show-sql : true
    generate-ddl : false
    hibernate :
      ddl-auto : none
  batch :
    jdbc :
      initialize-schema : ALWAYS

---
spring :
  config :
    activate :
      on-profile : test
  jpa :
    database : h2

```

#### 실행 
docker desktop 실행 👉 터미널로 해당 파일(docker-compose.yml) 경로에서 명령어 실행 
> docker-compose up -d

#### docker mysql server 접속 
docker desktop 에 container가 실행되어 있는 상태
> docker exec -it <continaer-name> /bin/bash 

> cat /etc/*release  // Oracle Linux Server release 7.9 서버
> yum update -y
> yum install vim-enhanced -y

[https://www.cyberithub.com/how-to-install-vim-editor-on-linux-rhel-centos/]('https://www.cyberithub.com/how-to-install-vim-editor-on-linux-rhel-centos/ 오라클서버vim설치)

// 한글깨진 상태로 들어가서 mysql server 문제 인줄 알았는데, 파일 자체가 문제였음 

## 컨테이너 <-> 로컬 파일 복사 
1. 컨테이너 -> 로컬 

  > $ docker cp <컨테이너명:/경로/파일명> <복사할 위치>

2. 로컬 -> 컨테이너

  > $ docker cp <로컬파일경로(올릴거)> <컨테이너명:/경로/파일명>

  ex. 
    $ docker cp /mnt/c/Users/Administrator/Desktop/파일정리/스터디/udemy/ml-100k.zip master:/home/
