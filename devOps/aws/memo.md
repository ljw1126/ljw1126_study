## AWS Secret Manager 

(공식) AWS Secrets Manager란 무엇인가? https://docs.aws.amazon.com/ko_kr/secretsmanager/latest/userguide/intro.html


## AWS CLI 설치 

https://docs.aws.amazon.com/ko_kr/cli/latest/userguide/getting-started-install.html

```

$ sudo apt install unzip -y
$ curl "https://awscli.amazonaws.com/awscli-exe-linux-x86_64.zip" -o "awscliv2.zip"
$ sudo unzip awscliv2.zip
$ sudo ./aws/install
$ aws --version

```

#### 참고

AWS CLI에 사용되는 명명된 프로파일 https://docs.aws.amazon.com/ko_kr/cli/latest/userguide/cli-configure-profiles.html


#### aws access key, aws secret access key 발급

```
✔ aws access key, aws secret access key 발급
console 로그인 > 우측 상단 계정 클릭 > 보안 자격 증명 클릭 > 하단 액세스 키 만들기 > 그냥 선택하고 만들면 됨.. ?

✔ 권한 등록 
IAM > 사용자 > '아이디' > 권한 탭 
- 권한 정책 > SecretsManagerReadWrite✨ 권한 추가

* 공식 doc 
https://docs.aws.amazon.com/ko_kr/IAM/latest/UserGuide/id_credentials_access-keys.html#Using_CreateAccessKey_CLIAPI

====================================================================
$ aws configure 
AWS ACCESS KEY ID 입력 : (공통 파일 참고)
AWS SECRET ACCESS KEY 입력 : 
DEFAULT REGION NAME 입력 : ap-northeast-2
DEFAULT OUTPUT FORMAT 입력 : json

$ aws configure list         // --profile 지정 안했을 경우 default로 생성됨🔺
$ aws configure list --profile default 

$ vim ~/.aws/credentail     // 키
$ vim ~/.aws/config         // 지역이랑 포맷 저장 

vim으로 수정해도 반영이 안됨 !! 😅
====================================================================

👇 여기부터 다시
$ aws configure --profile anderson        // 재생성이 답이다!! ✨
$ aws configure list --profile anderson

$ vim ~/.zshrc 
export AWS_DEFAULT_PROFILE=anderson

$ source ~/.zshrc

$ aws s3 ls             // s3 리스트 나열 되면 정상적으로 호출 된 거다 !! ✨


```

참고 aws cli command
https://novemberde.github.io/post/2018/06/20/AWS-config-switching/
https://www.daleseo.com/aws-cli-configure/
https://err-bzz.oopy.io/e1a36aff-9213-4957-ba18-143b61d45703



✨🤔여기까지는 잘 되었음
--- 



*pom.xml 의존성 추가 

```xml
    <!-- AWS Secret Manager -->
    <dependency>
        <groupId>org.springframework.cloud</groupId>
        <artifactId>spring-cloud-starter-bootstrap</artifactId>
        <version>3.1.5</version>
    </dependency>

    <dependency>
        <groupId>org.springframework.cloud</groupId>
        <artifactId>spring-cloud-starter-aws-secrets-manager-config</artifactId>
        <version>2.2.6.RELEASE</version>
    </dependency>

    // spring-boot-starter-validation도 추가되어 있어야함. property를 가져올 때 validation 의 annotaion을 사용하기 때문에 

```

*build.gradle의 경우 
```
    implementation 'org.springframework.cloud:spring-cloud-starter-bootstrap:3.1.3'
    implementation 'org.springframework.cloud:spring-cloud-starter-aws-secrets-manager-config:2.2.6.RELEASE'
    implementation 'org.springframework.boot:spring-boot-starter-validation'
```

* bootstrap.properties 추가 
```
aws.secretsmanager.name=gaia-webapp-test
aws.secretsmanager.region=ap-northeast-2
aws.secretsmanager.enabled=false
```


Error creating bean with name 'bootstrapImportSelectorConfiguration': Initialization of bean failed


* spring boot 에서는 aws sdk를 심어서 사용하도록 하는 방식이 있나봄 
    - 관련 의존성 추가 
    - 설정 정보 등록 (bean, pom.xml)
    - 클래스 등록해서 instance의 metadata를 활용가능 하다는 것으로 생각됨


    https://cloud.spring.io/spring-cloud-static/spring-cloud-aws/2.1.0.RELEASE/single/spring-cloud-aws.html#_automatic_region_configuration


* spring boot 에서 aws screts manager 호출 테스트 관련 블로그 
https://shinsunyoung.tistory.com/122


* docker image ECR로 push 관련 공식 문서
https://docs.aws.amazon.com/ko_kr/AmazonECR/latest/userguide/docker-push-ecr-image.html


## AWS CLI COMMAND

#### aws secretsmanager 호출해보기

> aws cli 버전에 따라 secretsmanager 지원안 될 수 있음 
> ec2의 경우 v1.14.44 , local의 경우 v2.11.0 
> aws --help 출력하면 명령어 확인 가능 
> 물론 명령어 호출할 때 aws configure 설정 되어 있어야 하고, 그룹이든 profile(개인계정) 이든 aws secretsmanager 접속 권한 부여가 되어 있어야 할 것으로 판단됨 (SecretsManagerReadWrite)
> aws command를 통해 CRUD 가 가능한 것으로 파악됨 (아래 공식 문서 주소 참고)

```
# secretsmanager에 등록된 전체 목록 확인 
$ aws secretsmanager list-secrets

# 테스트로 생성한 instance 조회 (key : value 도 확인 가능)
/*
    "get-secret-value" subcommand to retrieve the value of the secret. The arguments needed for this command are:
    ㄴ secret-id: ARN or Name of the secret to retrieve.
    ㄴ version-id: ID of the version to retrieve

    global options에 
    --output 형식 지정 가능 // The formatting style for command output (json, text , table)

*/
$ aws secretsmanager get-secret-value --secret-id /secret/something --output (json|text|table)

🤔 SecretString 속성에 설정한 { key : value , ... } object가 담겨있는데 내가 원하는 key만 호출 못하는가??
ARN : 주소
CreatedDate : 생성일 정보
Name  : /secret/something 
SecretString : {"key" : "value"}
VersionId : blahblah
```

참고. 
https://www.learnaws.org/2022/08/28/aws-cli-secrets-manager/#how-to-list-all-secrets
https://docs.aws.amazon.com/cli/latest/reference/secretsmanager/index.html



## ECR 로 local docker image push 테스트 
// aws cli 설치 되고 aws configure 설정되어 있어야 함
```
// maven build 후 
$ docker build -t data_analytic_test .
$ aws ecr get-login-password --region (지역코드) | docker login --username AWS --password-stdin (ECR주소/NAME)
  => aws ecr get-login-password --region ap-northeast-2 | docker login --username AWS --password-stdin 263154317287.dkr.ecr.ap-northeast-2.amazonaws.com/data_analytic_test

    Login Succeeded

// docker tag (이미지명) (RepositoryURI/이미지명:latest 형식)
$ docker tag data_analytic_test:latest 263154317287.dkr.ecr.ap-northeast-2.amazonaws.com/data_analytic_test:latest   // 기존 data_analytic_test 나두고 해당 좌측 이미지명:태그 형태로 생성됨

// docker push (RepositoryURI/이미지명:latest 형식) 
$ docker push 263154317287.dkr.ecr.ap-northeast-2.amazonaws.com/data_analytic_test:latest               // ✨ ECR 확인시 올라감 !!

```

참고. 공식 문서 
https://docs.aws.amazon.com/ko_kr/AmazonECR/latest/userguide/docker-push-ecr-image.html
https://docs.aws.amazon.com/ko_kr/AmazonECS/latest/userguide/create-container-image.html


## aws secrets manager 적용 후 배포 테스트 했는데 에러 발생 
runc create failed: unable to start container process:

## target group about aws load balancer and ecs healthy check failed (504 error)
정확한 해결법은 없는 공식 문서
    https://aws.amazon.com/ko/premiumsupport/knowledge-center/ecs-fargate-health-check-failures/

LB의 보안그룹을 ECS 보안그룹에 추가하면 되었음 !! (해결✨, 26:00)
    https://www.youtube.com/watch?v=o7s-eigrMAI



#### AWS FARGATE 
AWS Fargete란 무엇인가요?
    https://docs.aws.amazon.com/ko_kr/AmazonECS/latest/userguide/what-is-fargate.html
오토피디아 블로그✨ (기본 용량 공급자에 대한 내용도 있음)
    https://blog.doctor-cha.com/ecs-in-depth

```
Fargate 유형은 별도의 물리적인 인스턴스 없이, AWS가 관리하는 자원 풀을 사용하는 유형입니다. 물리적 자원인 인스턴스를 관리할 필요가 없다는 장점이 있으나, 컨테이너가 실행되는 인스턴스에 직접 접근할 수 없으므로 디버깅 시에 복잡하다는 단점과, 비용이 다른 유형에 비해 비싸다는 단점이 있습니다.
```

#### AWS LB
✨https://aws-hyoh.tistory.com/133 




```

* AWS ECR 이란? 


* AWS ECS 클러스터란?
    - task 또는 service의 논리적 그룹 뜻함 
    - task 와 service는 클러스터에 등록된 인프라에서 실행 
    
        https://docs.aws.amazon.com/ko_kr/AmazonECS/latest/developerguide/clusters.html

* AWS Fargate란?





```


## AWS ECS 배포시 task 중복 생성되는 현상 
- ECS service 1 - task 1 로 구동되고 있는 상황
    - 💩 ECS update 시 task 2 가 추가됨 ( 로그 확인시 에러 발생하여 구동 되지 않음, 포트 충돌도 되고.. )
- ECS service - task 삭제 후 재기동시 
    - ECS Task 에 public/private ip 가 동적으로 변경됨
    - 💩 이에 따라 ALB Target group 에 ip 할당을 매번 수정해야 하는 현상 발생 
- ALB와 ECS를 연결해서 구동하도록 하는 방법 테스트 진행

```
🤔 ECS Service 신규 생성시 기존 ALB와 생성한 target group, 그리고 ECS task container를 연동
✨ ECS Service 재배포시 ECS task, ALB target group 자동 swap 되어 고민하던 문제 해결 됨 ! 
    👉 ECS task 중복 생성, private/public dynamic ip 생성됨으로써 그리고 target group 관리 안 되던 문제 해결


```

## AWS ECS Task Cpu, memeory

> ECS Task 에 cpu, memory 설정이 있고, Service 에 "용량 공급자 전략 (기본 1024, 가중치 1)" 설정이 있는데 모호하다.. 

*공식 Amazon ECS on AWS Fargate
    https://docs.aws.amazon.com/AmazonECS/latest/developerguide/AWS_Fargate.html#fargate-tasks-size

*공식 Amazon ECS 의 CPU 할당에 대해 알아야 할 사항은 무엇입니까?
    https://aws.amazon.com/ko/premiumsupport/knowledge-center/ecs-cpu-allocation/

*공식 Amazon ECS 용량 공급자 생성 (용어 모호🤔)
    https://docs.aws.amazon.com/ko_kr/AmazonECS/latest/developerguide/cluster-capacity-providers.html

*공식 롤링 업데이트
    https://docs.aws.amazon.com/ko_kr/AmazonECS/latest/userguide/deployment-type-ecs.html

*공식 Workshop - ECS Overview (각 장비 용어 있음)
    https://catalog.us-east-1.prod.workshops.aws/workshops/4b59b9fb-48b6-461c-9377-907b2e33c9df/en-US/introduction/ecs

*공식 Workshop - Create ECS Cluster 
    https://catalog.us-east-1.prod.workshops.aws/workshops/4b59b9fb-48b6-461c-9377-907b2e33c9df/en-US/setupecscluster/ecscluster

*오토피디아 블로그 (✨)
    https://blog.doctor-cha.com/ecs-in-depth/codepipelineeuro-ecs-baepo-jadonghwahagi#yongryang-gonggeubja-jeonryag

*용량 공급자의 등장 배경 
    https://velog.io/@dlawlrb/AWS-ECS-Capacity-Provider%EB%A5%BC-%EC%9D%B4%EC%9A%A9%ED%95%98%EC%97%AC-%EC%98%A4%ED%86%A0%EC%8A%A4%EC%BC%80%EC%9D%BC%EB%A7%81%EC%9D%84-%EC%89%BD%EA%B2%8C-%EA%B4%80%EB%A6%AC%ED%95%98%EC%9E%90

*공식 메모리를 Amazon ECS의 태스크에 할당하려면 어떻게 해야 하나요?
    https://aws.amazon.com/ko/premiumsupport/knowledge-center/allocate-ecs-memory-tasks/

*메가존 클라우드 블로그 - Amazon ECS 클러스터 자동 스케일링 정식 출시 (서울 리전 포함)
    https://www.megazone.com/techblog_20200109_aws-ecs-cluster-auto-scaling-is-now-generally-available/

## AWS Secrets Manager 적용 에러 (by sms)
Caused by: java.lang.NoClassDefFoundError: com/amazonaws/transform/EnhancedJsonErrorUnmarshaller
```
# 아래 의존성과 출동나서 되지 않음..
<dependency>
            <groupId>com.amazonaws</groupId>
            <artifactId>aws-java-sdk</artifactId>
            <version>1.11.58</version>
        </dependency>
```


## AWS Auto Scaling 
https://inpa.tistory.com/entry/AWS-%F0%9F%93%9A-EC2-%EC%98%A4%ED%86%A0-%EC%8A%A4%EC%BC%80%EC%9D%BC%EB%A7%81-ELB-%EB%A1%9C%EB%93%9C-%EB%B0%B8%EB%9F%B0%EC%84%9C-%EA%B0%9C%EB%85%90-%EA%B5%AC%EC%B6%95-%EC%84%B8%ED%8C%85-%F0%9F%92%AF-%EC%A0%95%EB%A6%AC#thankYou

https://m.post.naver.com/viewer/postView.naver?volumeNo=29960975&memberNo=36733075

블로그 | 오토 스케일링이 항상 정답은 아니다 ✨
    https://www.itworld.co.kr/news/235524

논문 | 컨테이너 기반 오토스케일링 환경의 성능 분석 ✨
    https://koreascience.kr/article/CFKO201835372170782.pdf

## AWS VPC (Virtual Private Cloud)
https://inpa.tistory.com/entry/AWS-%F0%9F%93%9A-VPC-%EC%82%AC%EC%9A%A9-%EC%84%9C%EB%B8%8C%EB%84%B7-%EC%9D%B8%ED%84%B0%EB%84%B7-%EA%B2%8C%EC%9D%B4%ED%8A%B8%EC%9B%A8%EC%9D%B4-NAT-%EB%B3%B4%EC%95%88%EA%B7%B8%EB%A3%B9-NACL-Bastion-Host

## AWS ELB (Elastic Load Balancer)
https://inpa.tistory.com/entry/AWS-%F0%9F%93%9A-ELB-Elastic-Load-Balancer-%EA%B0%9C%EB%85%90-%EC%9B%90%EB%A6%AC-%EA%B5%AC%EC%B6%95-%EC%84%B8%ED%8C%85-CLB-ALB-NLB-GLB



## ECS Service with multi ALB 
- targetGroupArn의 경우 ALB : tagetGroup 연결 했을 떄 대상 그룹에 arn 주소 확인 가능 !!

```
# aws ecs update-service command -- 업데이트 시 롤링 방식으로 재배포 발생 확인
aws ecs update-service --cluster data-analysis-web-cluster \
	--service sms-web-service \
	--cli-input-json '{"loadBalancers" : [
		{
			"targetGroupArn": "arn:aws:elasticloadbalancing:ap-northeast-2:263154317287:targetgroup/ecs-sms-tg/5439ce3aa41c6307",
			"containerName": "sms-container",
		    "containerPort": 8093
		}, 
		{
			"targetGroupArn": "arn:aws:elasticloadbalancing:ap-northeast-2:263154317287:targetgroup/ecs-sms-internal-tg/5762a64c7c136cfc",
			"containerName": "sms-container",
            "containerPort": 8093
		}
	]}'


# ECS Service 설정 확인
$ aws ecs describe-services --cluster data-analysis-web-cluster --services sms-web-service   

```

#### 참고
https://docs.aws.amazon.com/ko_kr/AmazonECS/latest/APIReference/API_UpdateService.html#API_UpdateService_Examples
https://docs.aws.amazon.com/AmazonECS/latest/developerguide/register-multiple-targetgroups.html
https://docs.aws.amazon.com/cli/latest/reference/ecs/update-service.html

// 별로 유용하진 않음
https://docs.aws.amazon.com/ko_kr/AmazonECS/latest/APIReference/API_UpdateService.html#ECS-UpdateService-request-loadBalancers

// stackoverflow 
https://stackoverflow.com/questions/64233863/how-can-i-update-and-ecs-service-adding-an-addition-load-balance-to-which-a-serv