github.com/djkeh/fastcampus-spring-boot-practice/

###### 사이트 
[https://gitignore.io](https://gitignore.io)
[https://spring.io](https://spring.io '스프링 공식 사이트')
[https://start.spring.io/](https://start.spring.io/ '스프링부트 관련 공식 사이트')

#### 기록
```
1. test 폴더 없을시 생성
https://ildann.tistory.com/5

2. 예약어 
pvsm >> public void static main() 생성 
sour >> System.out.println(); 생성 

3. 인텔리제이 UTF-8 설정
https://goddaehee.tistory.com/248

4. junit4 와 junit5 차이 
https://pinokio0702.tistory.com/156
- junit4에는 public 접근제어자 기본 사용되었는데 , junit5는 default 로 사용 
- 어노테이션 명칭 변경

5. junit 작성한 test 파일이 gradle로 실행될때 느려질 경우 
https://lemontia.tistory.com/916

6. .gitignore 설정파일 내용 생성해주는 사이트 
https://lemontia.tistory.com/916

ㅁ 흐름 정리
- git hub에서 issue 생성 
- 로컬에서 gitflow 통해 feature 생성 후 작업함 
  - checkout 해서 해당 feature 통해서 작업 후 push 함 
  - pull request 신청을 하게 됨 
  - 담당자가 확인 후 리뷰하거나 merge함 (버튼 존재)
- 그리고 merge 시 feature 로 작업한 branch가 이력에 남게 되는데 
  merge시 삭제하도록 repository별로 설정하는게 있음 
  - settings 에 아래 항목 체크 하면 됨 
    [v] Automatically delete head branches 
- 만약 pull request 후 merge시 특정 branch name은 삭제 안되도록 하고 싶은 경우 
  - 마찬가지로 settings에서 Branch protection rule 에 branch naming 을 추가하면 됨
```

## 객체 지향 설계 5대 원리 (Solid)
- SRP (Single Responsibility Principle, 단일 책임의 원칙) 
  - 한 클래스는 하나의 책임만 가져야 한다. 
- OCP (Open Close Principle, 개방폐쇄의 원칙)
  - s/w 요소는 확장에는 열려 있으나 변경에는 닫혀 있어야 한다. 
- LSP (The Liskov Substitution Principle, 리스코브 치환의 원칙) 
  - "프로그램의 객체는 프로그램의 정확성을 깨뜨리지 않으면서 하위 타입의 인스턴스로 바꿀 수 있어야 한다."
- ISP (Interface Segregation Principle, 인터페이스 분리의 원칙) 
  - "특정 클라이언트를 위한 인터페이스 여러 개가 범용 인터페이스 하나보다 낫다."
- DIP (Dependency Inversion Principle, 의존성역전의 원칙) 
  - 프로그래머는 "추상화에 의존해야지, 구체화에 의존하면 안된다."

###### 참고 사이트
[https://www.nextree.co.kr/p6960/](https://www.nextree.co.kr/p6960/)

###### 스프링은 엔터프라이즈 애플리케이션을 만드는데 필요한 거의 모든 요소를 지원해주는 프레임워크

## Spring의 핵심기능 
1. The IoC Container (IoC, Inversion of Control, 제어의 역전)
   1. "전통적인 제어 흐름에 비추어볼때, 제어 흐름을 반대로 뒤집은 것" - Wikipedia 
   2. https://ko.wikipedia.org/wiki/%EC%A0%9C%EC%96%B4_%EB%B0%98%EC%A0%84
   3. (좋은예시) 라이브러리와 프레임워크 비교 
      1. 라이브러리를 사용할 때는 내 코드가 라이브러리 코드를 호출하지만, 프레임워크를 사용할 떄는 프레임워크가 내 코드를 호출합니다.
2. Resources 
   1. low-level resource 에 접근할 수 있는 보다 폭넓은 기능을 제공 
      1. UrlResource 
      2. ClassPathResource
      3. FileSystemResource
      4. PathResource
      5. ServletContextResource
      6. InputStreamResource
      7. ByteArrayResource
3. Validation, Data Binding, and Type Conversion 
   1. 데이터의 검증
   2. 데이터를 인식하고 자료형에 할당 
   3. 데이터 자료형의 변환 
4. Spring Expression Language(SpEL)
   1. 스프링 애플리케이션의 런타임에 다양한 데이터에 접근하기 위한 언어 
   2. JSP Unified EL과 유사하지만 스프링에 특화되어 더 다양한 기능 제공 > @Value("${value.from.file}")
5. Aspect Oriented Programming with Spring 
   1. AOP(관점지향 프로그래밍) - 공통 기능을 개발자의 코드 밖에서 필요한 시점에 적용가능 
   2. AOP를 적극적으로 사용하고 지원하는 프레임워크 
   3. Proxy, Aspect, Join Point, Advice, Pointcut, Weaving
      1. 사용하는 라이브러리 찾아보기
   4. CGLib, AspectJ
   5. AOP를 사용하지 않아도, 심지어 몰라도 여전히 프레임워크를 사용가능
      1. @Transafctional 어노테이션 
6. Null-safety 
   1. null-safe한 코드를 작성할 수 있는 다양한 방법을 지원
      1. @Nullable
      2. @NonNull
      3. @NonNullApi
      4. @NonNullFields
7. Logging
   1. 별도의 외부 설정없이 로깅 구현체 사용가능
      1. SLF4J + Logback (기본)
      2. Log4j 2
      3. JUL(java.util.logging)
8. 그 밖에 웹 애플리케이션을 만드는데 유용한 여러 지원들 
   1. Testing 
   2. Data Access 
   3. Web Servlet
   4. Web Reactive
   5. Integration : REST endpoints, email, scheduling, cache, ..



###### 기록 ( 6강 부터 ) 
```
# 1. build.gradle 에 추가 
    implementation 'org.springframework:spring-context:5.3.8'
    implementation 'org.springframework:spring-test:5.3.8'
    // web이랑 webmvc있는데 webmvc 받아야 함 => webmav의 경우 spring-context 기능 포함
    implementation 'org.springframework:spring-webmvc:5.3.8'
    // 톰캣 사용하기 위해 servlet api 추가해야 함 - javax servlet 을 메이븐 저장소 검색
    /*
        maven repository에서 찾은 내용 그대로 복붙 해도 되는데 구성을 맞춰 주려함
        compileOnly group: 'javax.servlet', name: 'javax.servlet-api', version: '4.0.1'
    */
    compileOnly 'javax.servlet:javax.servlet-api:4.0.1'


# 2. 라이브러리 추가시 인식이 제대로 되지 않는 경우 
    [file] - [invalidate cache] 실행해서 캐시 제거 후 재시작 하면 됨 

※공부할 어노테이션 
@Qualifier
@Component       // bean 으로 등록하는 역할
@Service         // bean 으로 등록하는 역할

# 3. 톰캣 버전 확인 
- javax.servlet-api 라이브러리 버전에 따라 톰캣 사이트 통해 확인해보기
- 지금 javax.servlet-api가 4.x 버전이므로 톰캣 9.0 버전 받으면 됨
http://tomcat.apache.org/whichversion.html
- [Run] - [edit configurations...]

# 4. 자동 import 관련 설정 
- https://hjjungdev.tistory.com/102
- 설정후 ctrl + alt + o 

# 5. 테마 변경 , 플러그인 검색 설치
https://codedragon.tistory.com/8854

# 6. 스프링 공식 사이트 
https://spring.io
https://docs.spring.io/spring-framework/docs/current/reference/html/web.html#spring-web
```


###### 기록 (9강. spring boot )
```

# spring boot 주요기능 
- Stand-alone 스프링 애플리케이션 기능 가짐
- 임베디드 톰캣 내장(WAR 파일 배포 필요 없음)
- 빌드 설정을 단순화해줄 기초 세팅과 의존성 
- 스프링 및 서드 파티 라이브러리의 자동 설정 
- 제품 레벨로 사용할 수 있는 각종 기능들 
- XML 설정 필요없음

# 요점
- 스프링이 할 일과 작성할 코드를 줄여주고(강력한 프레임워크)
- 스프링 부트가 그것을 더 줄여주었다.(굿 프랙티스)

# spring boot setup 
1. new project 
    - https://start.spring.io/ 사이트 접속 해서 해도 된다는데 인텔리제이 지원하니 바로 함 
2. 왼쪽 탭에서 spring initialize 선택 
    - 생성할 폴더 생성, gradle 선택 , 이름설정, jdk / java 버전 선택 후 다음 
    - 일단 Spring web만 선택해서 생성 (이때 옆에 보면 가이드 메뉴얼 링크가 있음)

# spring boot 이 어울리지 않는 경우 
- 프로그램의 세세한 설정한 설계를 내 손으로 일일이 만져야 한다면 
- 실행 중인 애플리케이션 기저에서 무슨 일이 일어나는지 모조리 알아야 한다면 
- 내가 사용하지 않는 기능은 단 하나도 프로젝트에 남겨두지 않는 최적화를 원한다면 
    - 외부 라이브러리 사용하지 않는거에 대해 최적화를 하고 싶은 경우 spring boot는 사용 안 하는게 좋음 
```


###### 기록 (Spring boot 1~2버전별 변천사 , 9강 부터)
```
※ 주요 변경사항을 체크하는 경로 
- spring official blog : https://spring.io/blog/category/releases
- spring boot github : https://github.com/spring-projects/spring-boot/wiki/
- SpringOne Platform Presentation 2017 : http://youtu.be/TasMZsZxLCA
- Baeldung(교육사이트) : https://www.baeldung.com/new-spring-boot-2
- Quora
- StackOverflow 
- 각종 기술 블로그들 

# Spring Boot 1 vs 2 주요 변경사항 
- java8(+java9) + spring framework 5
- 써드파티 라이브러리 업그레이드 
- Reactive Spring 
- Functional APIs
- Kotlin 지원 
- Configuration properties 
- Gradle 플러그인 
- Actuator 변경점
- Spring Security 
- 기타 변경 사항 
   - Spring Boot Properties 변경사항 
   - Jackson 시간 표시 기본값 
   - MySQL auto_increment 
   - HikariCP
   - JOOQ
   - GIF banner 

1. java8(+java9) + spring framework 5
   - java8이 최소사양, java 9 공식 최초 지원 - 부트 1.x는 미지원 
   - spring framework 5 

2. 써드파티 라이브러리 업그레이드 
   - Tomcat 8.5     //임베디드 톰캣
   - Flyway 5
   - Hibernate 5.2 
   - Thymeleaf 3       // 성능 , 표현 
   - Elasticsearch 5.6 
   - Gradle 4           // 빌드 도구 
   - Jetty 9.4
   - Mockito 2.x 

3. Reactive Spring 
   - 무엇을 위해 존재하는가?
      - 한정된 자원(thread pool)으로
      - 비동기(asynchronous) 넌 블럭킹(non-blocking) 알고리즘을 이용해
      - 다수의 요청에도 빠르고 예측 가능한 응답 성능(반응)을 실현 
   - 리액티브 지원 모듈 
      - Spring WebFlux 
      - Reactive Spring Data
      - Reactive Spring Security 
      - Embedded Netty Server

4. Functional APIs (함수형)
   - WebFlux.fn
   - WebMvc.fn (Spring Framework 5.2에서 도입되고 spring boot 2.2 부터 지원)
   - 기존의 스프링 웹애플리케이션을 함수형 스타일로 작성가능 
   - 스프링 기술과 에노테이션에서 분리된 코드 
      - 자바 코드 레벨에서 분석 가능
      - 독립적인 유닛 테스트 가능
      - 스프링 컨터에니어에서 독립 

5. Kotlin 지원 
   - spring boot 2 부터 지원 

6. Configuration properties
   - 프로퍼티를 쓸때 Relaxed binding 은 여전히 지원 
   - 프로퍼티를 읽을때 양식이 통일됨 
      - 엘리먼트 구분 : "."
      - 영어 소문자 + 숫자 
      - 단어 구분자로 "-" 사용가능 
    - 환경변수(environment variables)에서 컬렉션 데이터의 인덱스 표현 가능 // 배열 같은거
      - my_var_1 = a   =>  my.var[1] ="a"
      - my_var_1_2 = b =>  my.var[1][2] = "b"
    - 더 편리한 자료형 인식 ( ex. java.time.Duration(시간간격)=> "1s" , "2m", "5d")
    - Origin 지원 : 스프링 부트가 읽은 프로퍼티의 위치를 기억하고, 에러가 나면 알려줌 
      - ex. "origin":"class path resource [application.properties]:1:27"

7. Gradle 플러그인 
   - 최소 버전 : 4.x로 변경 
   - bootRepackage => bootJar & bootWar 
   - dependency management 기능을 사용하려면 플러그인을 명시해야 함 

8. Actuator 변경점
   - 보안성 강화 : 1.5에서 기본으로 보여주던 endpoint를 더이상 보여주지 않음 
   - @Endpoints : 커스텀 endpoint 를 환경 (MVC, JMX, Jersey..) 에 상관없이 편하게 구현 
   - 이름 변화 
      - /autocofing -> /conditions 
      - /trace -> /httptrace

9. Spring Security
   - Oauth 2.0 통합
   - 커스텀 설정이 더 쉬워짐 
   - WebSecurityConfigurerAdapter 순서 문제 해결
      - 기본 설정이 하나로 통합됨 
      - WebSecurityConfigurereAdapter를 추가하면 기본 설정이 꺼짐 
   - 보안이 중요한 기능들을 명시적으로 작성하게끔 변경(ex. actuator)

10. 기타 
   10-1. Spring Boot Properties 변경사항 
      - 이름과 구성에 변화 
      - spring-boot-properties-migrator
      - JdbcTemplate 제어 옵션 추가 : spring.jdbc.template.*
      - Redis 제어 옵션 추가 : spring.cache.redis.*
   10-2. MySQL auto_increment 
      - Spring Data JPA, @GeneratedValue strategy 기본 동작이 바뀜 
      - 기본값 : GenerationType.AUTO 
         - Spring Boot 1.5 : MYSQL AUTO == IDENTITY 
         - Spring Boot 2.0 : MYSQL AUTO == TABLE 
   10-3. HikariCP
      - Database 커넥션 풀 관리 프레임워크
      - Tomcat Poll => HikariCP 로 변경됨 
   10-4. JOOQ(주크)
      - Java Object Oriented Querying  // 현업에서 많이 쓰이진 않음 
      - Datasource에 맞게 JOOQ dialect 자동설정 
      - @JooqTest 지원 
      - 국내에서는 QueryDSL 에 밀리는 분위기?..
   10-5.  GIF banner 
      - spring boot 실행시 맨처음 콘솔에 찍히는 배너를 gif 파일 지원한다는 의미(개인 취향)

```

###### 기록 (10강 , 2.1~2.3 버전 변쳔사)
```
[# Spring boot 2.1 변경점]
- 성능향상 
   • GC 튜닝 통한 속도 향상 => 더 빠른 시작 가능 
   • 더 적은 heap 사용  
- Spring Data JPA : bootstrap-mode
   - Spring Data Lovelace(개발자명) 
   - lazy initailization 
   - spring.data.jpa.repositories.bootstrap-mode=deferred(or lazy) // 해당 설정 추가시 jpa bean을 '실행전'까지 초기화 안한다함 
- JDK11 + spring framework 5.1 
- 주요 버전 업그레이드 
   - tomcat 9 
   - undertow 2 
   - hibernate 5.3
   - junit 5.2
   - micrometer 1.1 
- Deprecatiosn from 2.0
   • setConnectTimeout(int), setReadTimeout(int)
   • data size를 int 입력 인자로 받던 메소드들은 Datasize로 변경 및 deprecated(더이상 사용하지 않는)
   • management.endpoints.jmx.unique-names => spring.jmx.unique-names 로  변경
   • @AutoConfigureMockMvc의 secure 속성 => @WithMockUser 으로 분리됨 
   • RestTemplateBuilder.basicAuthorization => basicAuthentication
   • AbstractFilterRegistrationBean, FilterREgistrationBean 에도 변경점 있었음
      - as-is : REQUEST_WRAPPER_FILTER_MAX_ORDER 
      - to-be : OrderedFilter.REQUEST_WRAPPER_FILTER_MAX_ORDER
- New Spring Actuator Endpoints
   • /actuator/caches 추가 
   • /actuator/integrationgraph 추가 
   • /actuator/health 기능 업데이트 
      - HealthIndicator 를 런타임에 추가, 삭제 가능 
      - 특정 인디케이터만 관찰 가능 (ex. /actuator/health/db)
- Logging Refinements
   • logging.level.web=debug   // 해당 설정할때만 동작 
   • spring.http.log-request-details=true  // 기본값false, 실제 요청의 세부사항을 로그로 확인 가능 
- Junit
   • Junit 5 사용가능 
      - spring-boot-test => 5.3.1
   • 그러나 기본 세팅은 Junit4 이기 때문에 junit5 사용하려면 gradle.build에 설정해야 함 
      testImplementation ('org.springframework.boot:spring-boot-starter-test'){
         exclude group : 'junit'      // 요거는 4이기 때문에 제외
      }
      testImplementation 'org.junit.jupiter:junit-jupiter-api'      // 요게 junit 5 
   • @ExtendWith(SpringExtension.class)을 모든 부트 @-Test 애노테이션에 내재화 => @ExtendWith() 없어도 되니 편리해짐 
- Bean Overriding 
   • 사용자가 모르게 빈이 오버라이드 되지 않도록 , 기본 동작 false 
      - 오버라이드 감지되면 : BeanDefinitionOverrideException
   • spring.main.allow-bean-definition-overriding=true 


[# Spring Boot 2.2 변경점]
- 성능향상 
   • 시작 시간과 메모리 사용이 더욱 감소 
   • 새 속성 추가 됨 => proxyBeanMethods=false 
      - @Configuration
      - @SpringBootApplication
      - @SpringBootConfiguration 
   // bean 생성 원리 - proxy bean 만들지 않고 생으로 factory 로 만드는걸 라이트 모드라함(proxy bean의 장점을 포기하고 조금 가볍게 동작시킨다는 건데 , 각각 장단점 있는 듯 )
   • spring-boot:run, bootRun => -Xverify:none -XX:TieredStopAtLevel=1
      - JDK 13쓸 경우 : -Xverify:none가 deprecated 되었기 때문에 빠짐
   • spring.main.lazy-initiallization 
      - 부작용1 : http request 핸들링 속도가 조금 더 길어짐 
      - 부작용2 : 시작할 때 문제가 있다면 보여주던 실패를 더이상 보여주지 않음 
- JAVA 13 + Spring Framework 5.2 
- 주요 버전 업그레이드 
   - spring HATEOAS 1.0 
   - spring kafka 2.3
   - spring security 5.2
   - hamcrest 2.1
   - mockito 3.1
   - assertJ 3.12
   - elasticsearch 6.7
   - git commit ID plugin 3.0
   - hazelcast 3.12 
   - jackson 2.10
   - jedis 3.1
   - lettuce 5.2
- Deprecations from 2.1
   • 스프링부트 2.1에서 deprecated된 클래스, 메소드, 프로퍼티들이 삭제됨 
- Jakarta EE 
   • javax.group ID => jakarta. 로 이동 
   • com.sun.mail:javax.mail => com.sun.mail:jakarta.mail
   • org.glassfish:javax.el => org.glassfish:jakarta.el
- Junit 5 기본 제공 
- Gradle 버전의 최소 조건이 4.10으로 변경됨 


[# Spring Boot 2.3 변경점] 
- 새 버전의 배포 주기 계획 발표 
   • 새 버전은 6개월 주기로 배포하기로 함 => 배포를 예상 가능, 업그레이드 주기를 계획할 수 있음 
   • Spring framework는 feature-based 배포 전략을 유지 "준비되면 나가겠다."
- Java 14, Gradle 5.6.x ~ 6.3, developmentOnly Gradle 설정 자동 생성 
   • java 8, 11, 14 지원 
   • gradle 6.3필요 (5.6.x은 지원하지만 deprecated형태)
   • developmentOnly 가 스프링 부트 플러그인에 의해 자동 생성됨 
      - developmentOnly에 추가 설정을 수동으로 구성한 코드는 모두 삭제해야 함 
      - 그러지 않을 경우 : "cannot add a configuration with name 'developmentOnly' as a configuration with that name already exists."
- 주요 버전 업그레이드(R2DBC지원) // 내용 생략
   • ※ R2DBC 관계형 데이터 베이스에 들어가는 커넥터를 반응형 방식으로 접근가능해짐 (기존에는 블록킹 방식)
- 도커 지원
   • Cloud Native Buildpacks 
      - Maven : spring-boot:build-image 
      - Gradle : bootBuildImage
      - Default Java buildpack : Paketo 
   • layered jars 
      - jar 내용물을 나눠서 업데이트가 있는 이미지만 빌드하는 방식 
      - 도커 이미지를 더 효율적으로 작성하는데 도움     
- Validation Starter를 web starter에서 제외 
   • javax.validation.*이 기본으로 포함되지 않게 됨 
      - ex. @Valid, @NotNull, @Min, @Max, @Email ...
   • 이유 : 검증 기능이 모든 서비스에서 필요한 것은 아닌데, dependency가 부피를 꽤 차지함 
      - implementation 'org.springframework.boot:spring-boot-starter-validation' // 따로 build.gradle 추가해야 함 
- Graceful shutdown 
   • 4가지 임베디드 웹 서버에 이 기능을 제공 
      - jetty, reactor netty, tomcat, undertow 
   • server.shutdown=graceful 
   • shutdown 실행되면, 바로 죽지 않고 모든 새로운 요청을 거부, 남은 요청들이 처리되기를 기다림 
   • spring.lifecycle.timeout-per-shutdown-phash:grace period 설정
      - 남은 요청이 처리되기를 기다리는 시간(default : 30s)
- Jackson 2.11
   • java.util.Data, java.util.Calendar 기본 포매팅에 변화 
      - 의도 : 정확한 ISO-8601 확장 규격을 준수 
         - as-is : "2021-01-02T03:04:56.789+0000"
         - to-be : "2021-01-02T03:04:56.789+00:00"
- Actuator 향상점 
   • /actuator/configprops 가 end-to-end 정보를 추적하여 제공 
   • /actuator/metrics : 이름들이 알파벳 순 보기 좋게 정렬됨 
- 기본 에러 페이지 내용 변화
   • 에러 메시지, 바인딩 에러가 기본 에러 페이지에 노출되지 않음
   • 클라이언트에 정보 유출을 방지
   • server.error.include-message
   • server.error.include-binding-errors
- @ActiveProfiles에 여러 개의 프로파일 설정하기 


```

###### 기록 
```
# 2.4 변경 내용        -- properties 관련된 변경 내용 많음 
• 버전 네이밍 변화
• Java 15, Startup Logging
• Jar Optimizations
• JUnit Vintage Engine
• 설정 파일 처리 기능 변화 (.properties, .yaml)
• Logback 설정 변경, Property Migrator
• @ConstructorBinding (in 2.2) 과 발전사항
• Origin chains
• Docker, Buildpack 지원 내용 업데이트

# 2.5 변경 내용   -- datasource 관련된 변경 내용 많음 
• Java 16, Gradle 7, Jetty 10, Kotlin 1.5, Groovy 3
• 디펜던시 업그레이드
• Deprecations from Spring Boot 2.3, 2.4, 2.5
• SQL Script DataSource Initialization
• 기본 에러 뷰의 메시지
• Logging Shutdown Hooks
• HTTP/2 over TCP (h2c)
• R2DBC DB 초기화
• Layered WARs, Docker Image Building Support
• GET requests to actuator/startup
   - 프로파일링 기능 추가 (강의 참조, *.jar 실행시 옵션 줌으로써 파일생성되고 특정 프로그램 통해 GC 등 확인간으 )
```