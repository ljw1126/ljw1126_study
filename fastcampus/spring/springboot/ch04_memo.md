
github.com/djkeh/fastcampus-spring-boot-practice/

## 실무를 고급지게 만들어주는 기능들
```
# Lombok(롬북)
- "Never write another getter or equals method again"
- Boilerplate code를 줄여주는 도구 
- 생산성 향상에 기여 
- https://projectlombok.org/
- Spring initializer 지원 
- 의존성 추가, 버전 관리 등 빌드 도구 설정에 큰 신경을 쓰지 않아도 됨 (https://start.spring.io/ 에서 dependencies에 lombok검색후 추가후 build.gradle 설정 내용 확인)
  - build.gradle에 추가 
    compileOnly 'org.projectlombok:lombok'
    annotationProcessor 'org.projectlombok:lombok'

# Lombok에서 제일 인기 있는 기능들 
    @Data
        • @Getter + @Setter + @RequiredArgsConstructor + @ToString +
        @EqualsAndHashCode
        • 편함
        • 근데 그래서 조심해야 함
        • @RequiredArgsConstructor >> 스프링 생성자 주입에 잘 어울려서 애용됨
    @Value
        • 불변 객체를 만들때 씀
        • @Getter @FieldDefaults(makeFinal=true, level=AccessLevel.PRIVATE)
        @AllArgsConstructor @ToString @EqualsAndHashCode

# 기타 팁 
    • Java 16 과 호환성 문제가 있음 -> 버전 1.18.20 해결
    • Spring Boot 2.4.4 이하는 버전 수동으로 입력해줄 것
    • https://github.com/projectlombok/lombok/issues/2681
    • Intellij Lombok plugin -> "Enable annotation processor" 자동 적용이라 안 해도 돼요~
    • 자동으로 다 해준다 vs. 디테일을 나도 모르게 바꾼다
    • 프로그래밍 세계는 끊임없이 경쟁과 발전 중
```

## Spring Configuration Processor
```
• build.gradle에 라이브러리 추가해줘야함 
• application.properties 또는 .yml 파일에 넣는 커스텀 설정의 자동 완성 , 도움말 등을 지원 
• https://docs.spring.io/spring-boot/docs/current/reference/html/configuration-metadata.html

※ 자동완성 추천에 설명을 추가하고 보여 줄 수 있다는 내용 
※ 지정된 폴더, 설정 내용 변경 등등 통해서 
※ custom properties 에 대해서 개발하는 사람끼리 편의를 위해 추가된 내용인듯 
```

## Spring Cache 
```
• 애플리케이션에 "투명하게(transparently)" 캐시를 넣어주는 기능
• 메소드, 클래스에 적용 가능
• 캐시 인프라는 스프링 부트 자동 설정으로 세팅되고, 프로퍼티로 관리 가능

※ 캐시가 시스템, 애플리케이션에 투명하게 자리잡는다는 말은..
• 데이터를 통신하는 시스템 쌍방이 캐시의 존재를 모른다는 의미
• "캐시가 있건 없건, 시스템의 기대 동작은 동일해야 한다."
• 캐시의 목표: 오로지 "성능"
• 캐시의 개념과 목적에 부합하는 성질이자, 조건

※ 캐시를 왜 쓸까?
반복 작업이라면 고려해 보세요
• 잘 바뀌지 않는 정보를 외부 저장소에서 반복적으로 읽어온다면
• 기대값이 어차피 같다면
• 캐싱해서 성능 향상, I/O 감소

※ 캐싱에서 생각해야 하는 것들 
• 무엇을 캐시할까?
• 얼마나 오랫동안 캐시할까?
• 언제 캐시를 갱신할까?

※ Spring boot에서 사용하는 annotation
• @EnableCaching
• @Cacheable
• @CacheEvict
• @CachePut

# Redis - 실무에서 많이 사용되는 캐시 서버(?)      //ch4_03 다시 들어보기 , lombok anotation설정 이나 redis 관해서
- 기본 포트 : 6379 (설치시 설정가능)
- client, server 구성 
다운로드 - https://github.com/microsoftarchive/redis
redis 명령어 - https://redis.io/commands
참고 - https://aws.amazon.com/ko/elasticache/what-is-redis/

※ redis 실행 명령어 
- https://github.com/microsoftarchive/redis/releases 에서 zip파일 받아서 적당히 c 드라이브에 redis 폴더 만들어서 풀어줌 
- redis.windows-server.conf 파일을 열어서 "# requirpass foobared" 를 "requirepass redis6379" 변경 
- 해당 파일명을 redis.conf 로 변경
- cmd 창으로 redis 폴더로 이동 
  
  # 서버 설치 후 실행  ----- 윈도우 '서비스' 프로그램 열어서 실행중인지 확인가능
  cd c:\reids 
  dir 
  redis-server --service-install redis.conf --service-name redis6379       // 서버 설치 
  redis-server --service-start --service-name redis6379                    // redis 서버 실행
  netstat -an | findstr 6379
  redis-cli -p 6379         // redis 클라이언트 접속 
  auth redis6379            // redis server가 다른 콘솔에 켜져 있으면 에러출력 , 정상인경우 OK 
  ping                      // pong 출력  
     
  # 서버 삭제 
  redis-server --service-stop --service-name redis6379         // 서버 실행 종료 
  redis-server --service-uninstall --service-name redis6379     // 서버 설치 삭제 

  # 압축폴더에 있는 
  redis-server.exe 와 redis-cli.exe 실행해도 되는데 
  redis-cli.exe 실행후 
  AUTH redis6379 하면 "(error) ERR Client sent AUTH, but no password is set" 에러 출력됨 
  - config get requirepass 
    1) "requirepass"
    2) ""
  - config set requirepass redis6379
  - AUTH redis6379

  # 저장 내용 확인 
  redis-server.exe 실행 
  > redis-cli -p 6379       // redis client 접속 
  > auth redis6379
  > keys *
    1) "student:fred"
    2) "student:jack"
    3) "student:cassie"
  > get student:fred 
    "{\"@class\":\"com.fastcampus.springbootpractice.domain.Student\",\"name\":\"fred\",\"age\":16,\"grade\":\"A\"}"
  > flushall        // 캐쉬 다 지우기 
  > keys * 

  # 캐쉬 TTL 설정 
  - 서버 안 내리는 경우 캐쉬가 계속 쌓이므로 용량 찰 수 있음 
  - TTL 통해 캐쉬 초기화 하는 방법 중 하나 
  - CacheConfig.java에서 bean을 생성해서 GenericJackson2JsonRedisSerializer 적용했기때문에 application.propertise 에 설정해도 무용지물 => CacheConfig.java에 하거나 GenericJackson2JsonRedisSerializer 사용안하면 application.propertise에 설정 가능 
  - 만약 해당 ttl 설정이 없으면 redis 서버가 계속 켜져서 캐쉬가 남아 있으므로 "[repo] 나의 통행료는 무척 비싸다!" 출력되지 않고 바로 출력문 실행됨 
  - CacheConfig.java 에 .entryTtl(Duration.ofSeconds(10)) 메소드 체인 추가( 10초 단위로 클리어)
    "[repo] 나의 통행료는 무척 비싸다!" 콘솔에 뜨는게 캐쉬가 초기화 되고 다시 등록하는 과정을 나타냄 

```


## Valut Configuration (암호관리)
```
# 암호를 관리하는 법 
    DB 접근 암호를 어디에 관리할까?
    • DB 접속 코드에 직접 입력한다    -- 보안 x
    • properties 에 입력한다 -- 보안 x
    • properties 에 암호화하여 입력한다 -- 보안 x
    • 별도 DB에 저장한다
    • 배포 서버에 저장한다
  
※ vault 란?
토큰, 비밀번호, 인증서 ,암호화 키와 같은 비밀 정보나 민감한 데이터를
UI, CLI, HTTP API 를 활용할때 안전하게 저장하고 제어할수 있게 해주는 오픈 소스 입니다.

# Hashicorp Vault 회사에서 만든 민감 정보 관리에 사용하는 오픈소스 도구 
    • https://www.vaultproject.io/
    • 민감 정보의 저장, 관리 (서버 따로 띄워두고)
    • 민감 정보에 접근하는 인증/권한 관리
    • 데이터 암호화
    • 오픈소스: https://github.com/hashicorp/vault
    장점
        • 프로젝트와 민감 정보가 완전히 분리됨
        • 보안성 강화
        • 민감 정보에 접근하고 고객과 공유할 수 있는 다양한 방법을 사용할 수 있음
        • 민감 정보에 접근할 수 있는 권한 관리 가능
    단점
        • 설계에 따라, Vault 서버가 죽으면 인증이 안되어 서비스가 중단되는 문제 발생 (SPoF)
        • 초기 러닝 커브
        • Vault 서버를 별도 운영해야 함

# 스프링 부트의 Vault 사용 지원
    • Spring Vault: Vault 연동을 위한 기본 기능 지원
        • https://spring.io/projects/spring-vault
        • spring-vault-core
    • Spring Cloud Vault: Vault가 외부 환경(클라우드)에 있는 경우를 위한 추가적인 지원
        • Vault 각종 설정을 properties 기반으로 조작 가능
        • https://spring.io/projects/spring-cloud-vault
        • spring-cloud-starter-vault-config
        • 이거 쓰면 됨 ( https://start.spring.io )
          ext {
            set('springCloudVersion', "2020.0.4")
          }

          dependencies {
            implementation 'org.springframework.cloud:spring-cloud-starter-vault-config'
            testImplementation 'org.springframework.boot:spring-boot-starter-test'
          }

          dependencyManagement {
            imports {
                mavenBom "org.springframework.cloud:spring-cloud-dependencies:${springCloudVersion}"
            }
          }

# vault 서버 설치 후 접속 
- vault server -dev      // 로그 내려보면 임시 토큰 존재 
- api access address에 '/ui' 붙이면 UI 웹페이지 접속 가능 


# vault 동작과정 
- vault서버 실행되어 있고 spring boot 실행시 연동되서 properties 파라미터 값을 읽어 올 수 있다는데..
- https://www.vaultproject.io/downloads 에서 *.exe 파일 받아서 c 드라이브에 vault 폴더 만들어서 압축품
- cmd로 해당 폴더로 이동 후 명령어 입력 
  > cd c:\vault 
  > dir 
  > vault server -dev         // 테스트용 dev 모드로 실행 , 실행로그중 api server와 임시 토큰 확인하기 
- 브라우저에 http://localhost:8200/ui 접속 , 로그에 있는 root 토큰으로 접속함 (포트 8200 기본)
- spring properties 명으로 vault에서 관리하고 등록시 가져와 사용가능 
  - 데이터 등록 후 우측 상단에 콘솔창 열어서 아래 command 입력시 등록 확인 가능 
    read secret/data/fastcampus
  - cmd로 vault 실행 후 client로 확인가능 
    이슈 ) Error checking seal status: Get "https://127.0.0.1:8200/v1/sys/seal-status": http: server gave HTTP response to HTTPS client
          - vault server -dev 실행한 후 로그 맨 밑에 "set VAULT_ADDR=http://127.0.0.1:8200" 내용 있음 
          - set VAULT_ADDR=http://127.0.0.1:8200 입력 후 vault status 확인하면 정상 동작
          - 참고 사이트 http://ko.codebug.zone/articles/b042f3754107ca8e874fd4d32e6cd205 
      
- spring boot 실행시 인증절차 통해 vault 서버와 연결되고 한번에 key 값을 가져옴 
  - vault server 죽어버리면 서비스 영향 끼치니 최초 한번에 다 가져와서 위험성을 낮춤 (매번 가져오는 io는 손실발생)
    이슈) spring boot 실행시 데이터 못 가져옴 
    application.properties 에서 설정 내용 중 
    ---
    spring.profiles.active=test
    spring.config.import=optional:vault://

    # Spring Cloud Vault 관련 설정
    spring.application.name=fastcampus        << 요기가 fast-campus 되어 있었음 
    spring.cloud.vault.scheme=http
    spring.cloud.vault.authentication=token
    spring.cloud.vault.token=s.hdx9txQgSza2wc1DtZVPyXu0

    spring.datasource.username=
    spring.datasource.password=
    ---

    ---
    # spring boot 실행 로그 중 spring.application.name 의 값에 따라 vault의 폴더를 뒤짐 (secret/fastcampus 경로에서 정상적으로 읽음)
    2021-11-08 22:06:53.468  INFO 4632 --- [           main] o.s.v.c.e.LeaseAwareVaultPropertySource  : Vault location [secret/fastcampus/test] not resolvable: Not found
    2021-11-08 22:06:53.468  INFO 4632 --- [           main] o.s.v.c.e.LeaseAwareVaultPropertySource  : Vault location [secret/application/test] not resolvable: Not found
    2021-11-08 22:06:53.468  INFO 4632 --- [           main] o.s.v.c.e.LeaseAwareVaultPropertySource  : Vault location [secret/application] not resolvable: Not found

    ---


도입을 검토해 보세요
• 프로젝트를 오픈소스로 공개하고자 하는 경우
• 금융, 상거래 관련 서비스를 하면서 민감 정보를 다룰 때 (고객 개인정보 등)
• 기타 서비스 도메인이 법에 민감한 분야라고 판단될 때
• 제품 코드와 민감 정보를 분리하고자 할 때

```