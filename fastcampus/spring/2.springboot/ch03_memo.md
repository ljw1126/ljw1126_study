## Spring Boot 기본기 
```
- 스프링 부트의 기본 기능 전체를 튜닝하는 부트 전용 설정 프로퍼티 
- classpath:aplication.properties , application.uml로 제어가능 
- https://docs.spring.io/spring-boot/docs/current/reference/html/application-properties.html

- 부트의 기능 거의 대부분을 제어 가능
- 기본값이 세팅되어 있어서 심지어 아무 것도 쓰지 않아도 작동함

* Spring boot의 경우, 특정한 라이브러리나 프레임워크의 파일성정, 코드설정 부분을 다 자동으로 설정해서 프로퍼티로 제공하고 있음 
[그 밖에]
- 웹 설정
- 템플릿 엔진 설정
- 서버 설정
- 시큐리티 설정
- Actuator 설정
- Devtools 설정
- Testing 설정 

# @SpringBootApplication 애노테이션 
  - 스프링 부트 애플리케이션의 시작점 
    - @SpringBootConfiguration , @EnableAutoConfiguration, @ComponentScan 등을 포함  // 내용 알아보기 !
    - @SpringBootConfiguration : 스프링 부트용 @Configuration 
    - @EnableAutoConfiguration : 사전에 정의한 라이브러리 빈을 등록 
    - @ComponentScan : 각종 스프링 빈 애노테이션을 베이스 패키지에서부터 스캔하여 스프링 빈으로 스프링 IoC컨테이너에 등록 
   - 해당 애노테이션에 속성과 속성값을 지정해서 설정하는 것도 있다.
        @SpringBootApplication(
            exclude = WebMvcAutoConfiguration.class
        )
    - @SpringBootApplication의 속성(attribute)들 
      - exclude  => 예시) exclude = {WebMavAutoConfiguration.class}
      - excludeName => 예시) excludeName = {"org.springframework.boot.autoconfigure.web.servlet.WebMvcAutoConfiguration"}
      - scanBasePackages => 예시) scanBasePackages = {"com.fastcampus.springbootpractice"}
      - scanBasePackageClasses => 예시) scanBasePackageClasses = {MainController.class}
      - nameGenerator => 예시) nameGenerator = BeanNameGenerator.class
      - proxyBeanMethods => 예시) proxyBeanMethods = true
   - SpringApplication 인스턴스를 만들어 run()전에 각종 설정 가능함 
      - 기존 방법 
        SpringApplication.run(SpringbootPracticeApplication.class, args);
      - 인스턴스화 사용시 
        SpringApplication application = new SpringApplication(SpringbootPracticeApplication.class);
        application.setBannerMode(Banner.Mode.OFF); // 배너를 끄는 기능
        application.run(args);
    - 메인 애플리케이션 클래스는 루트 패키지에 놓는 것을 권장 (아래 공식문서 링크 )
      - https://docs.spring.io/spring-boot/docs/current/reference/html/using.html#using.structuring-your-code


※ 톰캣이 사라지거나 한글 설정 초기화 됨 ?
java: error: release version 16 not supported
참고 => https://minaminaworld.tistory.com/154

# @Component vs @Configuration 

## @Component 
- "이 클래스는 커스텀 빈이다"를 
    - 1. @ComponentScan => base package 에서부터 모든 @Component 검색 
    - 2. 인스턴스화 : 필요한 의존성을 모두 주입 
    - 3. 스프링 컨테이너에 등록 : 필요한 곳에 주입 

- 참고 : 빈을 만드는 방법 
    - 1. @Component     // 클래스 명 위에 
    - 2. @Bean(in @Configuration)   // 클래스명 위에는 @Configuration 애노테이션을 달고 함수에 @Bean을 담 
    - 3. @Bean(in @Component) : Lite Mode, factory method  방식이라함 (proxy bean보다 가볍지만 proxy bean의 기능은 이용 x )

    https://docs.spring.io/spring-framework/docs/current/reference/html/core.html#beans-java-basic-concepts

- 공부해보기 ! : proxy bean , factory method 
    - factory method 방식 사용시 proxy  bean 사용시 spring AOP와 같은 기능 이용못함 
    - 그러므로 상황에 따라 spring AOP가 필요한 경우 proxy bean 방식을 사용해야 하고 , 그렇지 않은 경우 factory method 방식을 사용하는 것을 선택하기 !

- 참고 : @Component vs @Bean 
    - @Component 
        - "컴포넌트는 빈이다"
        - Class-level annotation
        - 등록하려는 빈의 클래스 소스가 편집 가능한 경우 사용 
        - auto-detection에 걸려서 bean으로 등록된다.
        - 컴포넌트에 해당하는 스테레오 타입 애노테이션들 => @Controller , @Service, @Repository
    - @Bean 
        - method-level annotation // 클래스에 붙이는게 아니라 method에 붙임 
        - 좀 더 읽기 쉬움 
        - 인스턴스화 하는 코드가 수동으로 작성됨 
        - 빈의 인스턴스 코드와 클래스 정의가 분리된  구조 
        - 따라서 외부 라이브러리, 써드 파티 클래스도 빈으로 등록 가능 

## @Configuration 
- "이 클래스는 각종 빈 설정을 담고있다."
    - 1. @SpringBootApplication이 컴포넌트 스캔을 통해 @Configuration 을 찾아냄
    - 2. 안의 빈 설정(메소드)을 읽어서 스프링 컨테이너에 등록 
    - 3. 필요한 곳에 주입 
    - 4. 또는 각종 스프링 인터페이스의 구현에 함께 활용됨 
- @Configuration 또한 @Component 의 스테레오 타입 애노테이션이다.

## 결론 
- "애노테이션이 의도에 맞게 사용되었는지 잘 봐주자"
    - 빈 설정은 @Configuration, 클래스 빈 등록은 @Component 
    - 정확한 목적을 모르고 쓰면 "잘 모르겠는데 어쨌든 돌아가요" 운영의 시한폭탄 
    - IDE가 노란줄 그러주면 
        - 응 돌아 간다는 거지(X)
        - 내 사전에 노란줄이란 있을 수 없다 ! 재확인(O)
```

## configuration properties 설정 방법 
```
"각종 설정값을 외부로 분리해낸 것"
- 서로 다른 환경에서도 사용할 수 있음 
- 애플리케이션을 새로 컴파일하지 않고 설정값을 바꿀 수 있음 
- 종류 
  - java properties file 
  - YAML 
  - environment variable 
  - command-line argument 

# 외부 설정의 우선 순위  ( 외부 설정을 읽어들이는 순서: 아래 설정이 위에서 읽은 것을 덮어씀 )
    1.디폴트 프로퍼티
    2.@Configuration 클래스에 @PropertySource 로 정의된 것
    3.설정 파일: application.properties
    4.RandomValuePropertySource
    5.OS 환경변수
    6.자바 시스템 프로퍼티: System.getProperties()
    7.JNDI 속성: java:comp/env
    8.ServletContext - 초기 파라미터
    9.ServletConfig - 초기 파라미터
    10.SPRING_APPLICATION_JSON 안의 프로퍼티들
    11.Command-line arguments
    12.테스트에 사용된 프로퍼티들
    13.@TestPropertySource
    14.Devtools 글로벌 세팅: $HOME/.config/spring-boot

# 설정 파일(Config data) 의 우선 순위 
    1.JAR 패키지 안의 application.properties, application.yaml
    2.JAR 패키지 안의, 프로파일이 지정된 파일: application-{profile}.properties
    3.JAR 패키지 밖의 파일
    4.JAR 패키지 밖의, 프로파일이 지정된 파일

# 설정 파일(Config data)의 위치 
    1.classpath
        1.1.classpath:/
        1.2.classpath:/config
    2.현재 디렉토리
        2.1../
        2.2../config
        2.3../config/child

# 설정 파일(Config data)을 읽는 방법 
    • @value  //데이터를 읽는 순서에 따라 null 이 입력되기도 하네 . @PostConstruct 사용시 제대로 불러옴 
    • Environment
    • @ConfigurationProperties

# Configuration Properties 
    • 자바 클래스로 매핑하므로 type-safe
    • 각 프로퍼티에 대응하는 meta-data 작성 가능
    • Relaxed binding 지원
    • 작성 방법
        • 기본
        • @Configuration 생략 후 root class에 @ConfigurationPropertiesScan 붙여줘도 빈 등록함
        • @Bean 메소드
        • @ConstructorBinding
# 결론 
    • @ConfigurationProperties 를 이용해서
    • 상수처럼, immutable 하고 type-safe 하고 명확한 프로퍼티를 만들어 사용하

```