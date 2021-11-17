## Servlet 과 Servlet Container 

#### 서블릿(Servlet)이란?
  - 클라이언트 요청(request)을 처리하고, 그 결과를 응답(response)해주는 프로그래밍 기술
   - **웹 애플리케이션을 만들때 필요한 인터페이스**

      - 과거 정적데이터만 전달하던 Web Server
      - 동적 데이터 처리하는 CGI(Common Gateway Interface) 구현체가 정의되면서 Web Server와 연동하여 동적 콘텐츠 전달가능해짐 
      - [문제점]
      - Client Request가 들어올때마다 process와 CGI 객체 생성
          - 개선1. process에서 thread로 변경 (개별 Local variables 영역 제외한 나머지 자원을 thread 공유)
          - 개선2. 각각 생성되던 CGI Instance를 Singleton 구현체로 관리되도록 변경 
      - [Servlet 인터페이스 단계] 
          - init : Servlet Instance 생성(initalize)
          - service : 실제 기능이 수행되는 곳 , Request Method별 개발자가 구현한 로직 수행
          - Destory : Servlet Instance 소멸 

<center>
    <img src="/images/spring/servlet.png" alt=""/>
</center>


###### CGI는 과거 동적 컨텐츠를 웹서버를 통해 제공하기 위해 정의된 규격이고, 자원 많이 사용하는 CGI를 개선해서 나온게 Servlet 

#### Servlet 동작방식
  - 사용자가 URL을 입력하면 Http request가 Servlet Container로 전송 
  - 요청을 전송받은 Servlet Container는 HttpRequest, HttpResponse 객체를 생성 
  - 사용자가 요청한 URL이 어느 서블릿에 대한 요청인지 찾음
  - 클라이언트의 GET/POST(Request Method)에 따라 doGet(), doPost()를 호출 
  - 동적 페이지를 생성한 후 HttpServletResponse 객체 통해 응답 보냄 
  - 응답 후 HttpServletrequest, HttpServletResonse 객체 소멸 

#### Servelt Container 
  - 서블릿의 생명주기를 관리하는 객체 
  - 클라이언트 request 요청이 들어왔을때 Servlet 생성/소멸 관리하는 역할 

#### Servlet Container 특징 
  - Tomcat(톰캣)과 같은 서블릿을 지원하는 WAS를 서블릿 컨테이너라 함
  - 서블릿 객체의 생명주기 관리 ( 생성, 초기화, 호출, 종료 )
  - 서블릿 객체는 싱글톤으로 관리
  - 동시 요청을 위한 멀티 쓰레드 처리 지원 


#### Servlet Container 역할 
- 1.웹서버와 통신지원
- 2.서블릿 생명주기 관리 
- 3.멀티쓰레드 지원 및 관리 

###### 참조 - 우아한 기술 블로그, 우아한 Tech 
[https://tecoble.techcourse.co.kr/post/2021-05-23-servlet-servletcontainer/](https://tecoble.techcourse.co.kr/post/2021-05-23-servlet-servletcontainer/)
