- docker 설치 과정 생략 


```
참고 : https://velog.io/@markyang92/userconfigure
$ sudo usermod ag docker jenkins
```


## jenkins images 설치
```
$ docker search jenkins
$ docker pull jenkins:jenkins:lts           // jenkins deprecated , using jenkins:jenkins:lts
$ docker images

$ docker run -d -p 8080:8080 -v /jenkins:/var/jenkins_home --name jenkins_test -u root jenkins/jenkins:lts
    -d	daemon, 흔히 말하는 백그라운드 모드로 동작하여 터미널 사용가능해짐
    -p	호스트와 컨테이너의 포트를 연결 (포워딩)
    -v	volumn, 호스트와 컨테이너의 디렉토리를 연결 (마운트), host path:container path 인데 container path에 생성되는게 host path 에 생성되게 됨 (host folder 내용이 container folder에 공유됨)✨
    –name	컨테이너 이름 설정
    -u 실행할 사용자 지정

    맨 마지막 jenkins/jenkins:lts 는 실행할 이미지의 레포지토리 이름이며 만약 이미지가 없을 경우 이미지를 docker hub 에서 땡겨오므로 주의한다.

$ docker ps -a         // 옵션이 없으면 실행중인 container만 표시, -a : all 은 container 전부 표시
$ docker --help        // logs 라는게 있어서 굳이 컨테이너 접속 안해도 초기 비밀번호 확인 가능 

$ docker logs jenkins_test 

    Jenkins initial setup is required. An admin user has been created and a password generated.
    Please use the following password to proceed to installation:

    9a386cc585cd4e728e0ced53cbd2bcd4

    This may also be found at: /var/jenkins_home/secrets/initialAdminPassword

==============================================================================================================================================

localhost:8080/ 접속 > 초기 비밀번호 입력 > 플러그인 설치 > 초기 계정 생성 


==============================================================================================================================================

[기타]
$ docker info | grep -i root 
$ sudo du -sh /var/lib/docker 

// 버전 확인 
우측 하단에 보면 Jenkins 버전 정보 표시됨 
- docker image 로 받은 jenkins는 2.375.3 <-> 2.332.1 , UI가 다름..

// 실행 상태인 container에 접속 
$ docker exec -it 이미지명 /bin/bash

// root 경로에 jenkins 폴더가 생성되었는데, docker-compose.yml 만드는 이유가..?


```

참고 
http://jmlim.github.io/docker/2019/02/25/docker-jenkins-setup/
https://hub.docker.com/_/jenkins


## DockerFile

참고 
https://www.youtube.com/watch?v=hWPv9LMlme8


## docker-compose.yml 
- Dockerfile이 N 개 일 때, 해당 요소를 연결해서 서비스를 일괄적으로 구축하기 위한 전체 설계도 (Dockerfile은 부분 설계도✨)

```
// docker-compose.yml 위치한 directory에서
$ docker-compose up -d
```


#### dockerfile 사용시 환경변수 

```
$ docker build -t dev-treenod2 --build-arg "PROFILE=set2" .
                  [name:tag]      [매개변수]         [dockerfile위치]
  docker build -t insight --build-arg "JAVA_OPTS=-Dspring.profiles.active=dev" .

* dockerfile 환경 변수 관련 👉 https://stackoverflow.com/questions/34324277/how-to-pass-arg-value-to-entrypoint

# Dockerfile 내용
    # base image setup
    FROM openjdk:11-jre

    # 로컬 -> 컨테이너로 파일 복사
    COPY build/libs/dev-treenod.war app.war

    # execute command     // ARG와 ENV가 FROM 위에 가면 에러 출력됨 failed to solve with frontend dockerfile.v0: failed to create LLB definition: no build stage in current context
    ARG PROFILE
    ENV profiles=$PROFILE
    RUN echo "$profiles"
    
    #ENTRYPOINT ["java", "-Dspring.profiles.active=$PROFILE", "-jar", "app.war"] 요거는 안 먹힘
    
    CMD java -jar -Dspring.profiles.active=$profiles app.war


$ docker run -d -p 8082:8082 --name dev-treenod2 dev-treenod2
                                    컨테이너명    실행 이미지명

// docker-compose 로 바로 구동시 .. ports랑 environment가 먹혔는데 .. 
$ docker-compose up -d 

    --- 
    version: '2'
    services:
    app:
        image: analytic/qa-jira-dashboard
        ports:
        - "8090:8080"
        environment:
        - "JAVA_OPTS=-Dspring.profiles.active=dev"
        volumes:
        - "/mnt/d/data/etc/qa-jira-dashboard:/data/etc/qa-jira-dashboard"
        build:
        context: .
        dockerfile: Dockerfile
    ---
    ---
    FROM maven:3.6.1-jdk-8 as BUILD
    COPY . /usr/src/project
    WORKDIR /usr/src/project
    RUN mvn --batch-mode clean package -DskipTests

    FROM openjdk:8-jre
    WORKDIR /app
    COPY --from=BUILD /usr/src/project/target/*.war /app/package.war
    
    //ARG나 ENV 지정해서 사용할 경우 docker-compose의 enviroment 인식 안됨 !!🤔
    CMD java $JAVA_OPTS -jar /app/package.war           //Dockerfile에서 바로 build 할 경우, ARG, ENV 사용하여 환경변수 전달
    ```


// QA dash board의 경우 ✨
$ docker build -t qa-jira-dashboard --build-arg "PROFILES=dev" .     // JAVA_OPTS=-Dspring.profiles.active=dev 로 변경
$ docker run -d -p 8090:8080 -v "/mnt/d/data/etc/qa-jira-dashboard:/data/etc/qa-jira-dashboard" --name qa-jira-dashboard qa-jira-dashboard

// docker-compose 사용 ✨
$ docker-compose build --help 
$ docker-compose build --build-arg "PROFIELS=dev"    // 🤔 build-args를 안 주면 Dockerfile에 default로 설정한 값이 할당됨 , JAVA_OPTS=-Dspring.profiles.active=default 로 변경
$ docker images 
$ docker run -d -p 8090:8080 -v "/mnt/v/data/etc/qa-jira-dashboard:/data/etc/qa-jira-dashboard" --name qa-jira-dashboard analytic/qa-jira-dashboard     

    ---
    FROM maven:3.6.1-jdk-8 as BUILD
    COPY . /usr/src/project
    WORKDIR /usr/src/project
    RUN mvn --batch-mode clean package -DskipTests

    FROM openjdk:8-jre
    WORKDIR /app
    COPY --from=BUILD /usr/src/project/target/*.war /app/package.war

    // 실행시 환경변수 전달
    ARG JAVA_OPTS=-Dspring.profiles.active=default
    ENV JAVA_OPTS=$JAVA_OPTS
    RUN echo $JAVA_OPTS

    CMD java $JAVA_OPTS -jar /app/package.war
    ---

```

https://citronbanana.tistory.com/197
https://www.daleseo.com/docker-compose-file/


#### docker image build 시 ECR로 push 
참고.
https://docs.aws.amazon.com/ko_kr/AmazonECR/latest/userguide/docker-push-ecr-image.html


#### docker run 했을 때 profiles 전달
https://www.baeldung.com/spring-boot-docker-start-with-profile



docker build 시 awsSecretsManager 에러 발생 
// container 안에는 aws cli가 없기 때문에 당연히 동작 안하는게 아닌가 싶음.. AWS_ACCESS_KEY랑 .. AWS_SECRET_ACCESS_KEY를 못찾네..

    2023-03-09 05:27:03.499 ERROR 1 --- [           main] s.AwsSecretsManagerPropertySourceLocator : Fail fast is set and there was an error reading configuration from AWS Secrets Manager:
    Unable to load AWS credentials from any provider in the chain: [EnvironmentVariableCredentialsProvider: Unable to load AWS credentials from environment variables (AWS_ACCESS_KEY_ID (or AWS_ACCESS_KEY) and AW
    S_SECRET_KEY (or AWS_SECRET_ACCESS_KEY)), SystemPropertiesCredentialsProvider: Unable to load AWS credentials from Java system properties (aws.accessKeyId and aws.secretKey), WebIdentityTokenCredentialsProvi
    der: To use assume role profiles the aws-java-sdk-sts module must be on the class path., com.amazonaws.auth.profile.ProfileCredentialsProvider@1a760689: profile file cannot be null, com.amazonaws.auth.EC2Con
    tainerCredentialsProviderWrapper@115667d: Failed to connect to service endpoint: ]





Error creating bean with name 'awsSecretsManagerPropertySourceLocator'
https://aws.amazon.com/ko/premiumsupport/knowledge-center/ecs-secrets-manager-issues/
https://github.com/docker/compose/issues/1259

#### docker images name <none>인 경우 삭제 
https://web-front-end.tistory.com/102

```
$ docker rmi $(docker images -f "dangling=true" -q)
```





#### 참고 
도커를 사용하여 간단한 SpringBoot 어플 실행하기 https://ttl-blog.tistory.com/761
(공식)Spring Boot Docker https://spring.io/guides/topicals/spring-boot-docker/