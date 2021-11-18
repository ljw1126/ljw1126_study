## API 설계 

#### 01. 애노테이션 기반 설계 

##### 컨트롤러 클래스
- MVC 패턴 중 핸들러 메소드를 포함하는 컨트롤러 빈을 만드는 과정 
  - @Controller
  - @RestController = @ResponseBody + @Controller 

#### 02. 핸들러 메소드 (Handler Method)
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


#### 03. Handler Methods 활용한 요청,응답 설계

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


#### 04. Controller Advice 
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
