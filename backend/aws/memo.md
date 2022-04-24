#### 용어
- EC2 (Elastic Compute Cloud)
  - 서버 서비스 
  - 개요 : https://aws.amazon.com/ko/ec2/?nc2=h_ql_prod_cp_ec2
  - 요금 : https://aws.amazon.com/ko/ec2/pricing/
- RDS (Relational Database Service)
  - 데이터 베이스 서비스
  - 개요 : https://aws.amazon.com/ko/products/databases/?nc2=h_ql_prod_db
- S3 (Simple Storage Service)
  - 간단 저장소 서비스 (→ 현재는 Complicate Storage Service가 됨😅)
  - console, sdk, java 등 통해 파일 업로드 가능
  - 개요 : https://aws.amazon.com/ko/s3/?nc2=h_ql_prod_st_s3
    - aws s3 cp 파일명 s3://주소   // 콘솔 통해 해당 파일을 지정 s3로 복사가능 
- IAM (Identity and Access Management)
  - 접근 권한/자격 관리 (ex. MFA 멀티팩터인증 == OTP)
  - https://us-east-1.console.aws.amazon.com/iamv2/home#/home

#### 프리티어 
- 무료로 사용해 볼 수 있는 기간 
  
> https://aws.amazon.com/ko/free/?all-free-tier.sort-by=item.additionalFields.SortRank&all-free-tier.sort-order=asc&awsf.Free%20Tier%20Types=*all&awsf.Free%20Tier%20Categories=*all

#### 비용관리 
> https://us-east-1.console.aws.amazon.com/cost-management/home?region=ap-northeast-2#/startupError?code=_CE_Not_Ready_&title=_CE_Not_Ready_Title_


## S3
- 구성 
  - ① Bucket
    - ② Folder 
      - ③ File(Object)
- 공유와 권한 (직접 찾아보기)
- 스토리지 클래스 
  - 사용 용도/목적에 따라 스토리지 클래스가 분류되어 있음
- 요금체계 
  - 저장공간 요금, 사용 요금, 인터넷 요금 
    - 업로드는 공짜인데, 다운로드 요청처리는 일정 비용 부과 (USD/건)
#### 그외 s3 기능 
- Web Site Hosting 기능 제공 
- CloudFront 서비스 제공 
  - Content Delivery Network(CDN)
- Version(이력) 관리 기능 제공
- Lifecycle 기능 제공 

## CloudFront
- 기본적으로 Cache Server 이고 CDN으로 동작함.
- 전세계 사용자 대상으로 고속으로 content 를 제공함 

> Cloud Front (Distribution) ↔ Web Server (Origin)

- 도메인만 작성 (ip, http 프로토콜 기재 x)
  - Cloud Front Distribution 생성 후 
  - [General] > Domain Name 에 기재되어 있는 주소가 cloud front 주소이다 

> Cloud Front (cache)로 인해 접근 속도는 빨라졌으나, Web Server(Origin)에 데이터 변경되어도 확인 x → 캐싱 제어 필요


- 캐쉬 설정 
  - **CloudFront는 default로 캐싱을 24 Hours* 저장**
  - CloudFront Distributions 에 [Behaviors] 선택 
  - "Object Caching" 항목 주목 → Minimum/Maximum/Default TTL 값 수정하면 캐쉬 조정 가능(초 단위) // 브라우저 쪽에서 request header에 캐싱 요청 하지 않으면 이 값을 통해 캐싱 제어 된다함 ! 
  - "Query String Forwarding and Caching" 항목 선택에 따라 Query String(?GET파라미터) 캐싱 여부 활성화 가능 // all하면 파라미터마다 새로운 요청으로 인식해서 캐싱 x
    

> Cache-Control : max-age=60(s)      // 60초동안 캐쉬 유지 된다는 Header 설정값 

```
$ tail -f /var/log/apache2/access.log         // 아파치 접속 로그 확인 
```
> cloud Front 를 사용하면 웹서버 로그는 아무것도 오르지 x (= Cloud front가 캐싱 처리해주니 웹서버가 쉼)

- 캐쉬 설정 무효화 
  - [Invalidations] 탭에 "Create Invalidation" 선택
  - 가이드 규칙에 맞게 path 추가 후 저장 → 그런데 무효화 작업은 돈이 든다.


#### CDN
- cloudFront 는 기본적으로 CDN 기능이 켜져 있음 
- CloudFront Distributions > [General] 탭에 "Edit" 선택
  - Price Class 항목 주목 // Edge Location(=캐시서버) 표시 부분이 CloudFront라 생각하면 됨
  - 적용 방식에 따라 과금 차이 남  // 안내서 참고 

> dotcom-tools 사이트에 도메인 주소 넣으면 전세계 컴퓨터가 접속 ! 서버 테스트 가능 

#### 참고 
- AWS Calculator 사이트로 요금 계산 사전에 가능 
- dotcom-tools 사이트 통해서 서버 응답속도 테스트 가능 