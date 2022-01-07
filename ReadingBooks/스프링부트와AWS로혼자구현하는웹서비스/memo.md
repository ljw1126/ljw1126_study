## build.gradle 
```
plugins {

    id 'org.springframework.boot' version '2.6.2' //
    id 'io.spring.dependency-management' version '1.0.11.RELEASE' // 스프링 부트의 의존성들을 관리해주는 플러그인 
    id 'java'
    //id 'eclipse'  //Eclpise IDE 설정 파일을 생성하여, Eclipse로 프로젝트를 import할 수 있도록 한다

}


repositories {
    mavenCentral()
    //jcenter() // 'jcenter' is deprecated  , 더이상 사용되지 않음.. 라이브러리 업로드를 간단하게 하는 용도라는데..
}

// 프로젝트 개발에 필요한 의존성 선언 
dependencies {
    implementation 'org.springframework.boot:spring-boot-starter-web' // implementation : 해당 의존성을 직접 의존하고 있는 의존성만 재빌드함
    testImplementation 'org.springframework.boot:spring-boot-starter-test' // testImplementation

    // 롬북 추가 (p67)
    compileOnly 'org.projectlombok:lombok' // compileOnly : 해당 의존성을 컴파일시에만 포함함
    annotationProcessor 'org.projectlombok:lombok' // 컴파일러가 lombok 애노테이션을 인식하도록 함 


    implementation 'org.springframework.boot:spring-boot-starter-data-jpa'  // jpa 
    implementation 'com.h2database:h2'                                      // h2 db 
    //implementation 'org.mariadb.jdbc:mariadb-java-client'                   // maria db 

}

```

1. testImplementation?
org.gradle.api.artifacts.dsl.DependencyHandler testImplementation(Object... dependencyNotation)
Implementation only dependencies for source set 'test'. 테스트 범위 한정 의존성 재 빌드

2. 라이브러리 
▪ spring-boot-starter-web
    - spring-boot-starter-tomcat: 톰캣 (웹서버)
    - spring-webmvc: 스프링 웹 MVC

▪ spring-boot-starter-test 
    - junit: 테스트 프레임워크 
    - mockito: 목 라이브러리 
    - assertj: 테스트 코드를 좀 더 편하게 작성하게 도와주는 라이브러리 
    - spring-test: 스프링 통합 테스트 지원

▪ spring-boot-starter(공통): 스프링 부트 + 스프링 코어 + 로깅
    - spring-boot : spring-core
    - spring-boot-starter-logging : logback, slf4j

▪ spring-boot-starter-data-jpa
    - 스프링 부트용 spring data jpa 추상화 라이브러리 
    - 스프링 부트 버전에 맞춰 자동으로 JPA 관련 라이브러리들의 버전 관리해 줌 

▪ h2 
    - 인메모리 관계형 데이터베이스 
    - 별도 설치 필요 없이 프로젝트 의존성만으로 관리할 수 있음 
    - 메모리에서 실행되기 때문에 애플리케이션 재시작할 때마다 초기화 된다는 점을 이용해 테스트 용도로 많이 사용됨. 


3. .gitignore 파일 생성 관련.. 


4. spring boot annotation 
@SpringBootApplication 
 > 스프링 부트의 자동 설정, 스프링 Bean 읽기와 생성을 모두 자동으로 설정함 
 > 특히 @SpringBootApplication이 있는 위치 부터 설정을 읽기때문에 '이 클래스는 항상 프로젝트 최상단에 위치'해야 함  

@RestController 
 > 컨트롤러를 JSON을 반환하는 컨트롤러로 만듦
 > 이전에는 Method마다 @ResponseBody를 선언했던것을 한번에 사용할 수 있게 해줌 
 > @Controller , @ResponseBody 등으로 구현되어 있음

@RunWith
 > Junit4에서 사용 
 > Junit5의 경우  @ExtendWith(SpringExtension.class)나 @SpringBootTest 안 붙여도 된다네..
 > If you are using JUnit 4, don’t forget to also add @RunWith(SpringRunner.class) to your test, otherwise the annotations will be ignored. If you are using JUnit 5, there’s no need to add the equivalent @ExtendWith(SpringExtension.class) as @SpringBootTest and the other @…Test annotations are already annotated with it.

// 작성일, 수정일 추상 클래스(BaseTimeEntity) 관련
@MappedSuperclass 
 > JPA Entity 클래스들이 BaseTimeEntity(신규생성) 클래스 상속할 경우 작성일/수정일 필드도 컬럼으로 인식하도록 함 

@EntityListeners(AuditingEntityListener.class)
 > BaseTimeEntity 클래스에 Auditing 기능을 포함시킴 
 
※ @EnableJpaAuditing // JPA Auditing 활성화 
    @CreatedDate
    > @Entity가 생성되어 저장될 때 시간이 자동 저장됨
    @LastModifiedDate 
    > 조회한 Entity의 값을 변경할 때 시간이 자동 저장됨 
    


5. 롬북(lombok)
  > 자바 개발할 때 자주 사용하는 코드 Getter, Setter, 기본 생성자, toString 등을 어노테이션으로 자동 생성해줌  
  > https://m.blog.naver.com/PostView.naver?isHttpsRedirect=true&blogId=vefe&logNo=222072718782
  >> ctrl + shift + a 눌러서 plugin 검색 > 실행 > lombok 설치 후 재시작 
  >> setting > build,Execution,Deployment > Compiler > Annotation, Processors 에서 Enable annotation processing 체크 활성화 
  ```
  # 애노테이션 
    @Getter 
        -선언된 모든 필드의 get 메소드 생성해줌 
    @RequiredArgsConstructor 
        -선언된 모든 final 필드가 포함된 '생성자를 생성'해 줌
        -final이 없는 필드는 생성자에 포함되지 않음 

    @NoArgsConstructor 
        - 기본 생성자 자동 추가 
        - public 클래스명(){} 과 같은 효과 

    @Getter 
        - 클래스 내 모든 필드의 Getter 메소드를 자동 생성 

    @Builder 
        - 해당 클래스의 빌더 패털 클래스를 생성 
        - 생성자 상단에 선언시 생성자에 포함된 필드만 빌더에 포함 

    ※ Setter가 없는데 어떻게 DB값 삽입 가능할까?(p93)
        - 기본적인 구조는 '생성자를 통해' 최종값을 채운 후 DB에 삽입 하는 것이며, 값 변경이 필요한 경우 '해당 이벤트에 맞는 public 메소드를 호출'하여 
          변경하는 것을 전제로 함 
        - 책에서는 생성자 대신 '@Builder' 를 통해 제공되는 빌더 클래스를 사용함

    @RequiredArgsConstructor
        - final필드나 @NonNull이 붙은 필드에 대해 생성자를 생성해줌 
        - 주로 의존성 주입(DI) 편의성을 위해 사용되고 함  

  ```

6. Juint5 테스트에서 
  ```
    // Junit 이 아닌 assertj 사용시 장점(p73)
    assertThat()
    isEqualTo()

    @WebMvcTest 
    > JPA 기능이 작동하지 않음 (p110)
    > Controller와 ControllerAdvice 등 '외부 연동과 관련된 부분만' 활성화되므로 JPA 기능까지 한번에 테스트 시에는 @SpringBootTest로 TestRestTemplate 사용하면 됨 

  ```


7. Spring Data JPA (p82~)
  - JPA는 인터페이스로서 자바 표준명세서 
    - JPA 인터페이스 구현체 : Hibernate, EclipseLink 등이 있음  
    - Spring Data JPA 를 통해 JPA 기술을 다루 ( JPA <- hibernate <- Spring Data JPA)
    - hibernate와 spring data jpa를 쓰는 것 사이에는 큰 차이가 없음 // 한 단계 더 감싸 놓은 Spring Data JPA가 등장한 이유는 다음과 같음 
        - > 구현체 교체의 용이성 : Hibernate 외에 다른 구현체로 쉽게 교체하기 위함  
        - > 저장소 교체의 용이성 : 관계형 데이터베이스 외에 다른 저장소로 쉽게 교체하기 위함 

8. 패키지 
    - Web, Service, Repository, Dto, Domain 영역이 존재(p101~)
    - domain 
        - 게시글, 댓글, 회원, 정산, 결제 등 소프트웨어에 대한 요구사항 혹은 문제 영역이라 생각하면 됨 
        - '비지니스 처리를 담당' (p102)
      ```
      # Posts.class 에서 
      @Entity (p90)
        - 테이블과 링크될 클래스임을 나타냄 
        - 기본값으로 클래스의 카멜케이스 이름을 언더스코어 네이밍(_)으로 테이블 이름을 매칭함 
          ex) SalesManager.java -> sales_manager table 
      @Id
        - 해당 테이블의 PK 뜻함

      @GeneratedValue 
        - PK의 생성 규칙을 나타냄  
        - 스프링 부트2.0에서는 GenerationType.IDENTITY 옵션을 추가해야만 auto_increment가 됨 (p90참고)

      @Column 
        - 테이블의 칼럼을 굳이 선언하지 않더라도 해당 클래스의 필드는 모두 칼럼이 됨 
        - 기본 값 외에 추가로 변경이 필요한 옵션이 있으면 사용함 
        - 문자열의 경우 varchar(255)가 기본 값인데, 사이즈를 500으로 늘리고 싶거나(ex:title), 타입을 text로 변경하고 싶거나(ex. content)등의 경우에 사용됨

      ```
    - repository 
      - 보통 ibatis나 mybatis에서는 Dao라고 불리는 DB Layer 접근자이다.
      - JPA 에서 Repository라고 부르며 '인터페이스'로 생성함
        ex) extends JpaRepository<Entity클래스명, PK타입> 상속처리하면 기본적인 CRUD 메소드가 자동 생성됨 
      - @Repository 추가 x 
      - 주의) Entity클래스와 기본 Entity Repository는 함께 위치해야 함 (Entity 클래스는 기본 Repository 없이는 제대로 역할을 할 수 없음)

    - service (p101)
      - 트랜잭션, 도메인간 순서 보장(비지니스 로직을 처리 x)
      
      ※ PostService에 update 기능에서 DB 쿼리 날리는 부분이 없는 이유?
         > JPA 영속성 컨텍스트 때문임(p113~)
         > JPA 엔티티 매니저(EntityManager)가 활성화된 상태로(Spring Data JPA를 쓴다면 기본 옵션) '트랜잭션 안에서 DB에서 데이터를 가져오면'
           이 데이터는 영속성 컨텍스트가 유지된 상태임 
           >> 이 상태에서 해당 데이터의 값을 변경하면 '트랜잭션이 끝나는 시점에 해당 테이블에 변경분을 반영'함 
           >> 즉, Entity 객체의 값만 변경하면 별도로 Update 쿼리 날릴 필요 x 
           >> 이 개념을 더티 체킹(dirty checking)이라고 함 

    - Entity (DB Layer)
      - 절대로 Request/Response 클래스로 사용해서는 안됨 
      - Entity 클래스는 데이터베이스와 맞닿은 핵심 클래스 ! Entity 클래스를 기준으로 테이블 생성되고, 스키마가 변경됨 

    - Dto (View Layer)
      - request/reponse용으로 Dto는 view를 위한 클래스라 자주 변경이 일어남 

9. applcation.properties
```
# jpa 
spring.jpa.show_sql=true        // 실행되는 쿼리문 콘솔에 출력
spring.jpa.properties.hibernate.format_sql=true   // sql 문 포맷팅 해서 이쁘게 출력

# 로컬 환경 
spring.h2.console.enabled=true       //
spring.jpa.hibernate.ddl-auto=none   // 프로그램 실행시 ddl문 자동 실행 여부에 대해 설정(none은 실행 안함)

spring.datasource.schema=schema.sql // 사용안하는지 줄 그어짐 
spring.sql.init.schema-locations=classpath:schema.sql // 스프링 부트 실행시 ddl 실행
spring.sql.init.data-locations=classpath:data.sql // 스프링 부트 실행시 sql 실행 
```

###### 참고 
- Date와 Calendar 클래스 그리고 JPA Auditing(p120)
- optional 클래스 [http://www.tcpschool.com/java/java_stream_optional](http://www.tcpschool.com/java/java_stream_optional)

---

220105 3일차 
- 머스테치 화면 구현 (p130)

```
# 작업기록

1. 머스테치 플러그인 설치 , 의존성 추가
    implementation 'org.springframework.boot:spring-boot-starter-mustache'

※ 머스테치 
- 스프링 부트에서 공식 지원하는 템플릿 엔진 
- 의존성 하나만 추가하면 끝 
- 머스테치 기본 파일 위치 
  > src/main/resources/templates 
  > src/main/resources/static/ 이후에 js | images | css 폴더 생성해서 절대경로로 호출하면 됨 
- 문법참고
  http://mustache.github.io/mustache.5.html
  https://bibi6666667.tistory.com/269


2. mustache.js 사용할 경우 파일 확장자 *.html만 가능함!! 
   > *.mustache로 할 경우 {{}} 바로 바인딩 되버려서 태그가 없어져버리니 render 불가함 

```


## 220107 
