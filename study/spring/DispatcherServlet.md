## DispatcherServlet 
- **Front Controller** 라고 함
- Spring Web MVC에서 사용하는 Servlet 
  - Servlet만 있을때는 URL마다 servlet을 생성, web.xml로 servelt mapping 관리해야 했음 
  - 요청마다 Servlet을 생성하고 처리해야 하기 때문에 멀티스레드 동작이 되야하고, 이는 서버 부담증가 및 Thread 변환에 따른 Context switching으로 인한 오버헤드 유발 
  - 그래서 과거에 Requset 마다 서블릿과 스레드를 생성했다면 , Spring 에서는 DispatcherServlet을 통해 하나의 서블릿으로 웹요청을 처리가능한 전략취함 
  **(= Client Request를 받고 그에 해당하는 Controller 분기, 그리고 개발자가 설계한 로직처리후 Response를 DispatcherServlet이 처리 해줌)**
- 결국 Spring 에서 제공하는 IoC 덕에 비즈니스 로직에 집중가능해짐 !

#### DispatcherServlet 동작 흐름 
<center>
    <img src="/images/spring/dispatcherServlet.png" alt="출처 : Overview of Spring MVC Architecture "/>
</center>

- [@Controller 기준]
  - ① DispatcherServlet으로 클라이언트의 웹 요청(HttpServletRequest)가 들어온다.
  이때 웹 요청을 LocaleResolver, ThemeResolver, MultipartResolver 인터페이스 구현체에서 분석한다.
  - ② 웹 요청을 HandlerMapping에 위임하여 해당 요청을 처리할 Handler(Controller)를 탐색한다.
  - ③ 찾은 Handler를 실행할 수 있는 HandlerAdapter를 탐색한다.
  - ④, ⑤ 찾은 HandlerAdapter를 사용해서 Handler의 메소드를 실행한다. 이때, Handler의 반환값은 Model과 View이다.
  - ⑥ View 이름을 ViewResolver에게 전달하고, ViewResolver는 해당하는 View 객체를 반환한다.
  - ⑦ DispatcherServlet은 View에게 Model을 전달하고 화면 표시를 요청한다. 이때, Model이 null이면 View를 그대로 사용한다. 반면, 값이 있으면 View에 Model 데이터를 렌더링한다.
  - ⑧ 최종적으로 DispatcherServlet은 View 결과(HttpServletResponse)를 클라이언트에게 반환한다.
- [@RestController의 경우]
  -  View와 관련된 ⑥, ⑦ 과정을 거치지 않고 알맞은 MessageConverter를 찾아 Response 본문을 작성한다.

###### 추가 찾아보기 
1. 웹 서버와 웹 어플리케이션 서버
2. was가 멀티 스레드를 관리하는 방식
3. 스프링 설정 파일과 서블릿 설정 파일
4. 스프링 컨테이너에 빈을 등록하는 방법/컨테이너가 주입 위치를 찾는 방법
5. default 서블릿
6. 스프링과 스프링 부트 


#### 참조 
[https://www.youtube.com/watch?v=calGCwG_B4Y](https://www.youtube.com/watch?v=calGCwG_B4Y '10분 테코톡] 🐶 코기의 Servlet vs Spring')
[https://tecoble.techcourse.co.kr/post/2021-06-25-dispatcherservlet-part-1/](https://tecoble.techcourse.co.kr/post/2021-06-25-dispatcherservlet-part-1/ 'DispatcherServlet - Part 1')
[https://tecoble.techcourse.co.kr/post/2021-07-15-dispatcherservlet-part-2/](https://tecoble.techcourse.co.kr/post/2021-07-15-dispatcherservlet-part-2/ 'DispatcherServlet - Part 2')