## 기록 
- 저장소 
    - github > git clone https://github.com/kker5/spring-security-practice
- 프로젝트 목적 
    - Spring Security 가 필요한 상황을 경험해보고 직접 구현해 봄 
    - 구현 내용을 토대로 Spring Security 아키텍처를 이해함 
- 프로젝트 요구사항 
    - 1. 유저는 본인의 노트(게시글)만 저장/삭제/확인만 가능 
    - 2. 다른 유저는 노트를 볼 수 없음 
    - 3. 어드민은 관리 차원에서 유저들의 노트 제목 리스트는 볼 수 있지만 내용은 볼 수 없음.
    - 4. 어드민은 공지사항을 작성할 수 있고 일반 유저들은 이 공지사하응ㄹ 볼 수 있음 
- 기술 스택 
    - 1. Spring WebMVC
    - 2. Spring Security 
    - 3. Thymeleaf 
    - 4. Lombok 
    - 5. Spring Jpa 
    - 6. H2
    - 7. Gradle 

```
# 1. build.gradle 의존성 추가 
    //spring security, security test
    implementation 'org.springframework.boot:spring-boot-starter-security'
    testImplementation 'org.springframework.security:spring-security-test'

    //junit test
    testImplementation 'org.junit.jupiter:junit-jupiter-api:5.7.0'
    testRuntimeOnly 'org.junit.jupiter:junit-jupiter-engine:5.7.0'

    > 테스트 실행시 로그에 Using generated security password: 84727af0-d021-4203-8cd4-82dddbae296f 찍히고 
    > /페이지로 안가고 자체 내부 /login 페이지로 바로 들어감 ;;

# 2. User 클래스에서 
    @NoArgsConstructor(access = AccessLevel.PROTECTED) // 무분별한기본 생성자를 막는 좋은 수단 . 한번더 체크 할 수 있도록 해줌 
    참고 - https://cobbybb.tistory.com/14

# 3. 시큐리티 참고 인터페이스 / 클래스 
   - Filter
   - FilterChainProxy 
     > doFilterInternal()
   - 1) FilterOrderRegistration
     > FilterOrderRegistration() //처음 서버 동작시 실행됨
        Step order = new Step(INITIAL_ORDER, ORDER_STEP);
		put(ChannelProcessingFilter.class, order.next()); // 100(순서)
		order.next(); // gh-8105 , 200
		put(WebAsyncManagerIntegrationFilter.class, order.next()); // 300 순으로 증가 
		put(SecurityContextPersistenceFilter.class, order.next());
		put(HeaderWriterFilter.class, order.next());
		put(CorsFilter.class, order.next());
		put(CsrfFilter.class, order.next());
		put(LogoutFilter.class, order.next());
        ..
        //순서 간격을 넣는건 개발자가 커스텀화한 필터를 끼우기 위해서 !
   - SecurityContextPersistenceFilter
     > 두번째로 실행 
     > SecurityContext를 찾아와서 SecurityContextHolder에 넣어주는 역할함 (SecurityContext를 찾았는데 없다면 새로 하나 만들어 줌)
     >> SecurityContext는 세션에 저장됨 
     > HttpSession에 저장된 SecurityContext를 가져오고 사용자 마다 구분(스레드 마다 구분) 할 수 있는 것을 쿠키로 저장된 jsessionid 로 요청/처리함 

   - 2) BasicAuthenticationFilter
     > console 창에서 curl 명령어로 로그인 요청시 막거나 / 허용하는 역할 수행 
        
            public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {
                    ..
                    @Override
                    protected void configure(HttpSecurity http) throws Exception {
                        // basic authentication
                        http.httpBasic().disable(); // basic authentication filter 비활성화 (활성화 할 경우 curl 명령어로 로그인 가능해짐 )

                        ...
                    }
            ...
            }

            //console창에서 
            > curl -u user:user -L http://localhost:8080/note       // 권한이 있어야 접속가능한데 basic authentication 허용 되어 있으면 로그인 된 후에 페이지 출력됨*

     > https 사용시 활성화 해주는게 좋음 (테스트 환경에서는 disable();*)   

    - 3) UsernamePasswordAuthenticationFilter  // 이미지 참고
     > 로그인 시 인증하는 필터 
     > attemptAuthentication()
     > ProviderManager 클래스 
        ...
        result = provider.authenticate(authentication); // AuthenticationManager 인터페이스 정의된 메소드고 구현체인 ProviderManager에 있는 걸 호출
     > AbstracdtuserDetailsAuthenticationProvier 
     > DaoAuthenticationProvider
     > UserDetailService

        // 해당 필터 실행과정 중에 SpringSecurityConfig 에 오버라이딩한 메소드가 사용됨(사용자 정보 획득용)
        @Bean
        @Override
        public UserDetailsService userDetailsService() { .. } 

     - 4) CsrfFiler  // 이미지 참고 
      > csrf 토큰을 통해서 위조된 요청인지 아닌지 확인가능하도록 하는거 
        마찬가지로 WebSecurityConfiguraenAdapter 확장하는 설정 클래스에서 
        http.csrf(); //csrf활성화
        >> 활성화 하게 되면 로그인 form 태그안에 input type="hidden" 으로 _csrf 토큰값이 생성되어 있음 
        >> 서버 요청시(로그인)  _csrf* 토큰 여부 파악에서 인증하게 됨 // 위조된 페이지인 경우 csrf 가 없음!

     - 5) RemembermeAuthenticationFiler 
      > RememberMeAuthenticationFilter 는 일반적인 세션보다 훨씬 오랫동안 로그인 사실을 기억할 수 있도록 해줌 
      > Session 의 만료시간은 기본 30분이지만 RememberMeAuthenticationFiler의 기본 설정은 2주* !!

        >> SpringSecurityConfig 상속받은 @Configuration 클래스에서 
            http.rememberMe(); // 활성화    --> login.html에서 id="remember-me" 체크박스가 체크 되어 있으면 넘어가서 쿠키 생성해 줌 

      > rememberme 쿠키를 삭제하지 않는 이상 크롬 껏다켜도 유지되면서 세션 새로 발급받아 연결이 됨 
        >> 주의)그렇지만 서버 재시작시 크롬쪽에는 rememberme 토큰이 남아있지만 서버에는 없으므로 주의해야함.. // 재시작해보니 클라이언트 측에도 쿠키는 날라갔는데.. 음.. 동기화 문제 발생 가능할 수 도 ? 책에서 세션id를 DB에 저장하는 방법있다는걸 본거 같기도 함 

     - 6) AnonymousAuthenticationFilter
      > 로그인하지 않은 경우 'annoymous', 'ROLE_ANNOYMOUS' 토큰 가진 채 이용하게 됨 (default)
        
        >> SpringSecurityConfig 상속받은 @Configuration 클래스에서 custom 설정가능 (default는 위와 같은 annoymous )
            http.annoymous().princial(객체);
     
     - 7) FilterSecurityInterceptor // 설명 다시 듣기 
      > 인가에 대한 최종 판단을 내림 
        >> 결과에 문제 있으면 *Exception 발생 시킴 

     - 8) ExceptionTranslationFilter // 이미지 참고 
      > FilterSecurityInterceptor 에서 발생할 수 있는 두가지 Exception을 처리해주는 필터 
        >> 1. AuthenticationException : 인증 실패할 경우 발생     -> 로그인하지 않고 http://localhost:8080/note (회원용) 접근시 로그인 페이지로 넘겨버림 
        >> 2. AccessDeniedException : 인가 실패할 경우 발생       -> admin 접속 후 http://localhost:8080/note 접근시 403 forbidden 에러 페이지로 넘어감 
      > 즉, 인증이나 인가에 실패했을 때 어떤 행동을 취해야하는지를 결정해주는 Filter입니다. 

  # 4. 시큐리티 테스트 
     - Authentication과 SecurityContext를 가짜로 만들어서 할 수 있도록 지원 
     - build.gradle에 의존성 추가해야 함 
       testImplementation 'org.springframework.security:spring-security-test'


        /*
            테스트 실행시 에러 
            org.springframework.web.util.NestedServletException: Request processing failed; nested exception is java.lang.ClassCastException: class org.springframework.security.core.userdetails.User cannot be cast to class com.pilot.security.user.User (org.springframework.security.core.userdetails.User and com.pilot.security.user.User are in unnamed module of loader 'app')
        */
        @DisplayName("인증하고 /note 요청할 경우")
        @Test
        @WithMockUser
        void getNote_인증있음withMockUser() throws Exception {
            mockMvc.perform(get("/note"))
                    .andExpect(status().isOk());
        }
        
        

```

## test
- MockMvc 
```
# @Controller 인 경우 
    https://spring.io/guides/gs/testing-web/
    https://twofootdog.github.io/Spring-Spring-MVC%EC%97%90%EC%84%9C-JUnit-%ED%99%9C%EC%9A%A9%ED%95%98%EA%B8%B02(MockMvc%EB%A5%BC-%ED%99%9C%EC%9A%A9%ED%95%9C-Controller-%ED%85%8C%EC%8A%A4%ED%8A%B8)/

# @RestContrller 인 경우 


```


###### 참고 사이트 
[https://imgzon.tistory.com/101](https://imgzon.tistory.com/101)