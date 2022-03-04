## ch3-1 개발 환경과 기술 스택 
```
# 개발 환경 
- java 8 , docker 20.10.5 , Mysql 5.7

# 기술 스택 
- Spring Boot 2.5 , Spring Boot Batch 2.5 , Spring Data JPA 2.5 
  Junit 5.7.2, lombok 1.18.20, gradle 6.8

# start.spring.io에서 프로젝트 생성 
  - Dependencies만 기록 
    > Lombok 
    > Spring Configuration Processor
    > Spring Batch 
    > Spring Data JPA 
    > Spring Data JDBC

# docker desktop 알아서 설치
    https://www.docker.com/products/docker-desktop

   - web root에 docker-compose.yml 추가 
   - 터미널에 입력 
    > docker-compose up -d  // 도커 실행 후 입력, docker-compose.yml 읽어서 알아서 컨테이너 생성해줌
    > Mysql workbench 설치해서 연결하면 진짜 만들어져 있음 
          localhost / 3306포트
          MYSQL_USER: "house"
          MYSQL_PASSWORD: "house"
          MYSQL_DATABASE: "house_batch"

[기록]
- application.yml 에 job을 설정안하면 전체 실행하므로 default로 아래와 같이 설정 
    batch: 
        job: 
            names: ${job.name:NONE}

- HelloJobConfig 생성 후 내용 작성 
  > Edit Configuration에 argument 에 --spring.batch.job.names=helloJob 추가하고 실행(만든 걸 실행함) 

[에러]
- 최초 실행시 Cannot load driver class: com.mysql.cj.jdbc.Driver 출력됨 
  (해결) build.gradle에 dependency 추가 >> runtimeOnly 'mysql:mysql-connector-java'
- Parameter 0 of constructor in com.fastcampus.hellospringbatch.job.HelloJobConfig required a bean of type 'org.springframework.batch.core.configuration.annotation.JobBuilderFactory' that could not be found.
  (해결)@EnableBatchProcessing // 추가하니 동작함 
        @RequiredArgsConstructor
        @Configuration 
  (참고) https://wckhg89.github.io/archivers/springbatch2
```

## ch3-2 spring batch 실행환경
```
# build 명령어 
  - 빌드 
  > 터미널/명령프롬프트로 프로젝트 루트 경로 이동 
  > $ ./gradlew bootJar       // 그대로 입력 하면 build됨
  
  - 빌드(*.jar) 파일 실행
  > (예시) $ java -jar ${빌드한파일}.jar --spring.batch.job.names=${잡이름} 
  > (실제) $ java -jar build/libs/hello-spring-batch-0.0.1-SNAPSHOT.jar --spring.batch.job.names=helloJob

## Batch 실행방식 
  1. OS 스케줄러 이용 
     > 한 머신 내부에서 스케줄러를 통해 Batch 프로그램을 실행한다 ex.리눅스 crontab 
     > 실행 결과를 log 파일로 남겨 확인함

  2. Quartz Scheduler이용 
     > Quartz + Spring Batch framework를 함께 사용해 어플리케이션을 개발함 
     > 어플리케이션 내부에서 스케줄링하므로 Batch 실행이 빠름 
     > (단점)스케줄링 데이터가 DB에 저장되어 Admin을 따로 만들어야 함  

  3. CI* 프로그램 이용
     > Jenkins 사용  (현업*에서 가장 많이 사용하고 있다함)
     > 마스터에서 슬레이브로 명령 전달해 배치 프로그램을 실행시킴 
     > 젠킨스에서 지원하는 스케줄링* 기능을 통해 배치 프로그램을 실행시킬 수 있다.
       >> *.sh 파일 만들어서 시나리오대로 실행하도록 함
       >> 파이프라인 기능도 있다함
     > 젠킨스 Admin에서 실행결과를 볼 수 있다.(별도 Admin생성 x)

  4. Spring Cloud Data Flow 
     > 아직 많이 사용하진 않는다함 
     > Spring Cloud Data Flow를 활용해 잡을 실행시킴 
     > Kubernetes 클러스터를 구축해야 함 
     > Admin이 제공되고, 잡의 실행을 연결할 수 있는 등 다양한 기능을 제공함 

```

## ch3-3. 데이터를 읽고,처리하고, 쓰기 
```
😃Let's code 
    1. 데이터 읽기 - ItemReader
    2. 데이터 처리 - ItemProcessor 
    3. 데이터 쓰기 - ItemWriter 

## 파일 작성 후 edit configuration에 vm argument 수정 
  --spring.batch.job.names=plainTextJob

```

## ch3-4. Spring batch Test
```
😃Let's code 
  테스트 환경에서 Job을 실행하기 위한 설정 
  - h2 
  - @SpringBatchTest - JobLauncherTestUtils  // 해당 어노테이션 사용해야 함 

```
