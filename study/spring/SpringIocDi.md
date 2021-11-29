## Spring Ioc 용어 
- 빈(Bean)이란?
  - Spring이 IoC방식으로 관리하는 객체로, 관리되는 객체(Managed Object)라고도 함 
- IoC(Inversion of Control)?
  - 빈의 생성과 제어의 관점에서 빈 팩토리(Bean Factory)라고도 함 
- Application context?
  - Spring이 제공하는 애플리케이션 지원 기능을 모두 포함하는 의미 
- Spring 프레임워크는 IoC컨터에니와 Application context를 포함한 Spring의 모든 기능을 포괄함 

|    IoC용어                  |                           설 명                                    |     
|:--------------------------:|:--------------------------------------------------------------------|
|빈<br/>(bean)               |- 스프링이 IoC 방식으로 관리하는 Object(객체)<br/> - 스프링이 직접 생성과 제어를 담당하는 오프젝트만을 빈(Bean)이라고 함.|
|빈 팩토리<br/>(bean factory) |- IoC를 담당하는 핵심 컨테이너 <br/> - 빈 등록, 생성, 조회, 관리하는 기능 담당 <br/> - 보통은 이 빈 팩토리를 바로 사용하지 않고 이를 확장한 Application Context를 이용함|
|애플리케이션 컨텍스트<br/>(application context)|- 빈 팩토리를 확장한 IoC 컨테이너 <br/> - 빈 등록, 관리하는 기본적인 기능은 bean factory와 동일함 <br/> - 스프링이 제공하는 각종 부가 서비스를 추가로 제공함|
|설정정보/설정 메타정보<br/>(configuration metadata)|- 애플리케이션 컨텍스트 또는 빈 팩토리가 IoC를 적용하기 위해 사용하는 메타정보|
|스프링 프레임워크|- IoC 컨테이너, 애플리케이션 컨텍스트를 포함해서 스프링이 제공하는 모든 기능 칭함|

<center>
    <img src="/images/spring/ioc.png" alt=""/>
</center>

- 프로젝트 실행시 BeanFactory의 경우 호출시 빈 객체를 등록 ↔ Application Context는 우선 컨테이너 생성시 모든 빈 정보를 메모리에 로딩하고 필요시 메모리에서 빈을 반환해줌(싱글톤) 
- 하나의 애플리케이션은 IoC 컨테이너에 의해 POJO 클래스와 설정 메타정보가 결합되어 만들어 짐 
- 설정 메타정보는 애플리케이션을 구성하는 객체와 객체 사이의 상호 의존성을 포함함
- Spring은 xml 설정 파일과 어노테이션 설정, 자바소스로 메타정보의 설정이 가능함 

<center>
    <img src="/images/spring/ioc.png" alt=""/>
</center>


## 참고 
[https://youtu.be/bnkt-34km5Q](https://youtu.be/bnkt-34km5Q)