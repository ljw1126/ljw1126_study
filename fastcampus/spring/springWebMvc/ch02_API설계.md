## API 설계 

## 01. 애노테이션 기반 설계 

##### 컨트롤러 클래스
- MVC 패턴 중 핸들러 메소드를 포함하는 컨트롤러 빈을 만드는 과정 
  - @Controller
  - @RestController = @ResponseBody + @Controller 

## 02. 핸들러 메소드 (Handler Method)
- 스프링 웹서비스가 받는 URI 요청을 컨트롤러 클래스의 특정 메소드에 매핑하는 과정
  - @RequestMapping
  - @GetMapping
  - @PostMapping
  - @PutMapping
  - @DeleteMapping
  - @PatchMapping


---

#### 기록 
```
1. 사용자 view 페이지 연결 설정 
   - src/main/webapp/index.html 생성 
   - [project structure] > [Project Settings] > [Modules] > Web선택 > Web Resource Directories 추가 
      F:\gitRepository\practice-mvc-get-in-line\src\main\webapp
   - src/mina/resources/application.properties 에 내용 추가 ( 톰캣의 경우 jsp를 인식하는데 테스트는 html로 해서 설정 변경 후 확인만) 
     spring.mvc.view.suffix=.html  
   - 테스트 컨트롤러에서 연결되었는지 확인
      
        @GetMapping("/")
        public String root(){
            return "index";           //ctrl 누르면 index.html을 찾아감  
        }
   - 그런데 추가적으로 설정해야 될게 있고 번거롭다함 ( 2 로 )

2. build.gradle에 타임리프 추가 , 타임리프의 @AutoConfiguration 기능을 사용하려고(사용하지 않는데 넣는 딜레마)
   - implementation 'org.springframework.boot:spring-boot-starter-thymeleaf'
   - 기본경로가 src/main/resource/templates/index.html 로 변경됨 
     - 이때 1-2 project structure 설정한 내용 지우기 

3. 만약 / 경로 설정하지 않는 경우 defaul welcome 페이지로 넘어감
   WebMvcAutoConfiguration.class 내용중 

      @Bean
      public WelcomePageHandlerMapping welcomePageHandlerMapping(ApplicationContext applicationContext,
          FormattingConversionService mvcConversionService, ResourceUrlProvider mvcResourceUrlProvider) {
        WelcomePageHandlerMapping welcomePageHandlerMapping = new WelcomePageHandlerMapping(
            new TemplateAvailabilityProviders(applicationContext), applicationContext, getWelcomePage(),
            this.mvcProperties.getStaticPathPattern());
        welcomePageHandlerMapping.setInterceptors(getInterceptors(mvcConversionService, mvcResourceUrlProvider));
        welcomePageHandlerMapping.setCorsConfigurations(getCorsConfigurations());
        return welcomePageHandlerMapping;
      }

  resource 폴더에 존재하는 index를 찾아서 default 페이지 해줌(resource에 root는 못 찾네, 특정 폴더안에 index찾음)

4. 에러페이지 제어권 가져오기 
   - applcation.properties 
      server.error.whitelabel.enabled=false     // spring boot 기본 에러 페이지(whitelabel) 꺼두기
   - 해당 class에 implements ErrorController 해줘야 제어권 가짐 


5. api 요구사항, 설계에 따라 컨트로럴 생성 및 뷰 폴더/파일 생성 

기타 확인 >> http://localhost:8080/actuator/mappings

```

---

###### API 테스트는 postman 또는 Talend API Tester로 함 


## 03. Handler Methods 활용한 요청,응답 설계

- @RequestMapping
• name: 뷰 템플릿에서 식별할 때 쓰는 이름
• value, path: URI (value == path)
• method: HTTP method (ex: GET, POST, ...)
• params: 파라미터 검사
• headers: 헤더 검사
• consumes: 헤더의 Content-Type 검사 (ex. MediaType.APPLICATION_JSON_VALUE 인 경우 json으로 데이터 주는 요청만 받음)
• produces: 헤더의 Accept 검사(ex. MediaType.APPLICATION_JSON_VALUE의 경우 json 데이터 받을 수 있냐?, 클라이언트가 처리 가능한 미디어 타입 종류)

- @RequestMapping shortcuts
• @GetMapping
• @PostMapping
• @PutMapping
• @DeleteMapping
• @PatchMapping

- 핸들러 메소드가 받을 수 있는 요청들(=메소드 파라미터로 적어 넣을 수 있는 타입들)
• ServletRequest, ServletResponse, HttpSession
• WebRequest, NativeWebRequest
• @RequestParam, @PathVariable         // @RequestParam 을 명시하지 않고 컨트롤러에서 파라미터 받을 시 optional로 받음 (required=true/false)  
• @RequestBody, HttpEntity<B\>
• @ModelAttribute, @SessionAttribute, Model, ModelMap
• @RequestHeader, @CookieValue
• Principal, Locale, TimeZone, InputStream, OutputStream, Reader, Writer, ....등등

-핸들러 메소드가 내보낼 수 있는 응답들(=메소드가 리턴할 수 있는 타입들)
• ModelAndView
• String, View
• @ModelAttribute, Map, Model
• @ResponseBody
• HttpEntity<B>, ResponseEntity<B>
• HttpHeaders
• void

---

###### 타임리프(thymeleaf)문법 공식 사이트 
[https://www.thymeleaf.org/doc/tutorials/3.0/usingthymeleaf.html#standard-expression-syntax](https://www.thymeleaf.org/doc/tutorials/3.0/usingthymeleaf.html#standard-expression-syntax)


## 04. Controller Advice 

- request header에 accept 유형에 따라 whitelabel error page(default page) 내용이 출력됨 
  - accpet 의 default는 text/html 이고 json, xml 등등 으로 요청가능 
  - 그리고 application.properties에서 해당 에러 출력에 대한 설정을 통해 상세 정보 확인가능한데, 보안상 이슈가 될 수 있으므로 기본은 false 처리 되어 있음 
  
```
# cms 에러 페이지에서는 다 출력되었는데 시스템 정보 유출되기 때문에 좋지 않은 행위였음  
server.error.include-exception=true
server.error.include-message=always
server.error.include-stacktrace=always
```
- (프레임워크 default 에러 처리)BasicErrorController.class에 errorHtml(): whitelabel view만들어주는곳 , error() : json body 만들어 주는 곳 
- (커스텀 에러 처리하고 싶은 경우) 커스터 페이지 간단 등록 방법
   - 적용한 컨트롤러에 implements ErrorController 하고    
   - 단일 기본 페이지
      -/resources/static/error.html
      -/resources/public/error.html
      -/resources/template/error.[템플릿확장자]
   - http status 별 기본 페이지
      -/resources/[static|public|template]/error/{http status 번호}.[html|템플릿확장자]
      -/resources/[static|public|template]/error/4xx.[html|템플릿확장자]
      -/resources/[static|public|template]/error/5xx.[html|템플릿확장자]
- **@ExceptionHandler** 
  - (특정 컨트롤러 )비즈니스 로직이 던진 예외에 반응하는 핸들러 메소드 
- **@ControllerAdvie**
  - @ExceptionHandler 를 모아서 글로벌하게 적용할때 사용 

---

## 05.컨트롤러 테스트 

```
# @SpringBootTest 기본 구성
일단 스프링 컨테이너와 스프링 부트 환경을 포함하는 테스트는 이것으로 다 작성 가능
• 통합 테스트를 할 때 적절한 선택
• 애플리케이션 컨텍스트를 로드하는데 시간이 걸리므로 테스트가 다소 무거운 편
• @SpringBootTest
  • 포함한 애노테이션: @BootstrapWith + @ExtendWith
  • 흔한 실수: JUnit 5 쓸 때 @ExtendWith(SpringExtension.class) 쓰지 않기
• @Test
  • JUnit 5 애노테이션
  • 단위 테스트 메소드를 지정

• value, properties: 프로퍼티 설정
• args: 애플리케이션 실행 시 커맨드라인으로 입력하는 인자(옵션) 설정
• classes: ApplicationContext 로딩을 위한 설정 클래스를 직접 지정
• webEnvironment: ApplicationContext 의 웹 환경 설정
  • WebEnvironment.MOCK: mock servlet, embedded server 동작 x
  • @AutoConfigureMockMvc, @AutoConfigureWebTestClient 와 함께 써서 mock test 가능
  • WebEnvironment.RANDOM_PORT: 랜덤 포트, embedded server 동작
  • WebEnvironment.DEFINED_PORT: 포트 지정(server.port), embedded server 동작
  • WebEnvironment.NONE: 웹 환경 구성 안 함, embedded server 동작 x


# Auto-configured Test (Slice Test)
스프링 애플리케이션에서 내가 필요한 일부분(slice)의 자동 설정만 불러오는 방법
  • @DataCassandraTest
  • @DataJdbcTest
  • @DataJpaTest
  • @DataLdapTest
  • @DataMongoTest
  • @DataNeo4jTest
  • @DataR2dbcTest
  • @DataRedisTest
  • @JdbcTest
  • @JooqTest
  • @JsonTest
  • @RestClientTest
  • @WebFluxTest
  • @WebMvcTest
  • @WebServiceClientTest

# @WebMvcTest
Spring MVC 컨트롤러 레이어를 슬라이스 테스트할 때 사용
• MockMvc 빈을 자동 설정하고 테스트에 사용
• 로드할 컨트롤러 클래스를 지정 가능 (기본 동작: 전체 컨트롤러 로드

# 참고 
• https://docs.spring.io/spring-boot/docs/current/reference/html/
features.html#features.testing.spring-boot-applications
• https://docs.spring.io/spring-boot/docs/current/reference/html/test-autoconfiguration.html#test-auto-configuration
• https://github.com/json-path/JsonPath
```

#### BaseController 테스트 중 
```
1. 테스트 실패 에러 
Content type expected:<text/html> but was:<text/html;charset=UTF-8>
Expected :text/html
Actual   :text/html;charset=UTF-8          // 웹 표준이 변경되면서 이러한 문제가 발생 했다함

## 수정전 
    @Test
    void root() throws Exception{
        // Given

        // When & Then
        mvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.TEXT_HTML))
                .andExpect(content().string(containsString("Hello world")))
                .andExpect(view().name("index"))
                .andDo(print())
        ;
    }

## 수정후 
    @Test
    void root() throws Exception{
        // Given

        // When & Then
        mvc.perform(get("/"))
                .andExpect(status().isOk())     // 수행시 상태 200 예상 
                .andExpect(content().contentTypeCompatibleWith(MediaType.TEXT_HTML))
                // 아래 두 메소드는 추천하진 않는다 함 
                //.andExpect(content().contentType(MediaType.TEXT_HTML_VALUE+";charset=UTF-8"))
                //.andExpect(content().contentType(MediaType.valueOf("text/html;charset=UTF-8")))
                // content안에 "Hello world" 글자가 있는가?    
                .andExpect(content().string(containsString("Hello world")))          
                .andExpect(view().name("index")) // view name은 index로 예상 
                .andDo(print())      // 내용 출력 
        ;
    }
```


#### 기록
```
1. 구글에 junit5 document 검색 
   https://junit.org/junit5/docs/current/user-guide/
   >> 2.12. Dependency Injection for Constructors and Methods 확인 

   방법 1. 
   @Autowired
   private MockMvc mvc;

   방법 2. 메소드의 매개변수에 주입 
   @DisplayName("[view][GET] 기본페이지 요청")
   @Test
   void givenNothing_whenRequestingRootPage_thenReturnIdnexPage(@Autowired MockMvc mvc) throws Exception{
      ...
   }  

   방법 3. 생성자 주입 방식(이거 사용) 
   private MockMvc mvc;

   public BaseControllerTest(@Autowired MockMvc mvc){
        this.mvc = mvc;
   }

   방법 4.(추천안함) @TestConstructor 선언하면 생성자에 있는 모든 파라미터의 주도권은 spring이 가져감 > Autowired 무조건 시도함 // 원하지 않는 파라미터까지 의존 주입 가능하므로 별로 추천 x 

   @TestConstructor(autowireMode = TestConstructor.AutowireMode.ALL)
   @AutoConfigureMockMvc
   @SpringBootTest
   class BaseControllerTest {

    private MockMvc mvc;

    // 생성자 주입방식
    public BaseControllerTest(MockMvc mvc){
        this.mvc = mvc;
    }

```


###### ※ junit 테스트시 json 데이터 조회 문법은 아래 링크 참조 
[https://github.com/json-path/JsonPath](https://github.com/json-path/JsonPath)


## 06. TDD 방식으로 복습하기 
```
# Test Driven Development (TDD, 테스트 주도 개발 기법)
• 프로그램의 설계와 구현, 사고의 흐름을 테스트 중심으로 생각하는 개발 방법
• 개발 순서의 변화
  • as-is: 구현한다 -> 테스트한다
  • to-be: 테스트를 만든다 -> 구현한다
• 주요 키워드: 익스트림 프로그래밍 (XP), 애자일, 폭포수 모델, Test-First Programming


# TDD 개발 사이클
1. RED: (실패하는) 테스트를 짠다. (요구사항의 명세)
2. GREEN: 테스트를 성공시킨다. (구현)
3. REFACTOR: 구현 코드를 고도화(리팩토링)한다.


# 테스트의 구조를 표현하는 방법 (a.k.a. 3A, Arrange - Act - Assert)
• Given (Arrange): 상태(state)의 정의 - 테스트를 수행할 때 전제 조건
• When (Act): 동작 - 테스트 실행
• Then (Assert): 검증 - 동작의 결과(actual) vs. 예상값(expected)


# WHY TDD? WHY 테스트?
- ("협업(기업) 코딩" 환경에서) 왜 이걸 해야 할까?
• 내가 지금 뭘 하려는지 명확히 안다는 사실을, 스스로 지속적으로 확인한다.
  • 개발이 지연되는 이유 중 하나는, 막막해서 멍때리기 때문
• 내가 지금 뭘 하려는지 명확히 안다는 사실을, 동료와 소스 코드로 공유하고 소통(코드 리뷰)한다. // 테스크 코드 == 프로그래머가 작성하는 요구사항 명세 
  • as-is 1: 개발 계획을 별도의 문서로 공유함
  • as-is 2: 개발 계획을 구현 코드로 공유함
  • to-be: 개발 계획을 테스트 코드로 공유함


# 최고의 효율로 테스트 개발을 하려면
- TDD가 기존 개발 방식보다 효율적이기 위해서는
  • 테스트 설계 흐름에 익숙해야 합니다.
  • 사람의 요구사항을 프로그램이 할 수 있는 기능으로 변환하기
  • 기능을 단위 기능으로 세분화하기
  • 기능의 관계와 상호작용을 설계하기
  • 테스트 작성 기술에 익숙해야 합니다.
- 결론: 계속, 꾸준히, 많이, 동료와 함께 하세요


# 테스트 내용의 발전 과정 예시 (막연한 사람을 위한 가이드)
1. 메인 요구사항의 기본 목표 위주로만 우선 테스트를 작성
  • 날짜를 yyyymmdd 포맷으로 입력하면, 정산일(D+3)을 계산해준다
2. 메인 요구사항 기본 + 세부 목표를 테스트로 작성
  • 날짜를 yyyymmdd 포맷으로 입력하면, 정산일(D+3)을 계산해준다.
  • 입력값이 없으면, 사용자에게 적절한 경고를 표시해준다.
  • 2000.01.01 이전 날짜 입력은, 최초 정산일(2000.01.01)을 출력해준다.
  • 매월 말일은, 빠른 정산일(D+2)을 계산해준다.
3. 메인 요구사항 기본 + 세부 + 더욱 구체적인 기능적 고려 요소
  • 날짜를 yyyymmdd 포맷으로 입력하면, 정산일(D+3)을 계산해준다.
  • yyyy-mm-dd 는? yymmdd는? 20210000 은? 20210231 은?
  • 입력값이 없으면, 사용자에게 적절한 경고를 표시해준다. -> 에러로 응답한다 (표현 방법은 위임)
  • null 은? "" 는? " " 는? " " 는?
  • 2000.01.01 이전 날짜 입력은, 최초 정산일(2000.01.01)을 출력해준다.
  • 19991230 은? 19991231 은? 20000101 은? 20000102 은?
  • 매월 말일은, 빠른 정산일(D+2)을 계산해준다.
  • 20210101? 20210102? 20210130? 20210131? 20210201? 20210228? 20201231?

## Reference 
• https://en.wikipedia.org/wiki/Test-driven_development
• https://ko.wikipedia.org/wiki/테스트_주도_개발
• https://martinfowler.com/bliki/TestDrivenDevelopment.html
• https://martinfowler.com/bliki/GivenWhenThen.html
```