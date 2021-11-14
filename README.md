# ljw1126_study
personal study repository

## VScode 단축키 
```
1. 인터페이스 상속시 오버라이드 함수 표시 하는 방법
    - 인터페이스 명 선택 후 crtl + . 
2. import 한번에 다 하기 
    - Alt + Shift + O
3. getter / setter 생성하기 
    - 해당 속성 드래그 해서 선택 
    - F1 또는 다른 단축키 
    - getter 검색하면 generator 나옴 
```

## 검색시 신뢰 가능한 사이트 
- baeldung, medium, github
- Reference site
  - spring.io, kotlinlang.org 
- 질의응답
  - stackoverflow 
- 조금 더 다양한 토론 
  - reddit 
- 우아한 형제 
  - https://tecoble.techcourse.co.kr/

#### 궁금한거 
디자인 패턴 
> builder 패턴 https://mangkyu.tistory.com/163 
> 팩토리 메서드 패턴 
- bean과 component 차이
- enum 에 대해 
- generic에 대해 
- lamba, stream , build api 
- javascript, vue.js, typescript 로 to do list 만들기 
- ajax

- 트랙잰션, AOP 개념 정리 
  - 이전에 했던거는 low 레벨 , @Transactional 어노테이션 통해 할경우 메소드 시작전 트랜잭션 생성하고 관리함

- TCP/UDP 
  - https://www.youtube.com/watch?v=ikDVGYp5dhg&t=4s

- TCP - 흐름제어, 오류제어 , 혼잡제어 (2021-11-13 확인)
  - 흐름제어 : 송/수신측 사이의 데이터 처리 속도 차이(흐름)을 제어하기 위한 기법으로 데이터 처리 속도를 조절하여 수신자의 버퍼 오버플로우를 방지. - Stop and Wait / Sliding Window 
  - 오류제어 : 오류 검출과 재전송 (ARQ,Automatic Repeat Request) - Stop and Wait ARQ / Go-Back-n ARQ / SR(Selective-Reject) ARQ
  - 혼잡제어 : 네트워크의 혼잡을 피하기 위해 송신측에서 보내는 데이터의 전송 속도를 제어 - AIMD(Additive Increase Multicative Decrease , 1배씩 증가 2배 감소) / Slow Start(2배씩 지수함수적 증가, 문제발생시 window size 1로 감소)
  - https://velog.io/@jsj3282/TCP-%ED%9D%90%EB%A6%84%EC%A0%9C%EC%96%B4%ED%98%BC%EC%9E%A1%EC%A0%9C%EC%96%B4-%EC%98%A4%EB%A5%98%EC%A0%9C%EC%96%B4
  
- ARP 프로토콜 , RARP 프로토콜 
- Servlet , Spring, Spring boot 
- 프로세스와 스레드 
- DNS 원리 
  https://www.youtube.com/watch?v=5rBzHoR4F2A
- String, StringBuilder, StringBuffer, Scanner 
- Strem API, ramda api 
- Primitive Type, Wrapper Class
  
- 생성인자가 많아 가독성이 떨어질때 사용하는 builder pattern 
  https://tecoble.techcourse.co.kr/post/2020-08-17-builder_pattern/

- custom exception의 필요성 
  https://tecoble.techcourse.co.kr/post/2020-08-17-custom-exception/

- stack trace에 대해 
  https://hashcode.co.kr/questions/2162/stack-trace%EA%B0%80-%EB%AC%B4%EC%97%87%EC%9D%B8%EA%B0%80%EC%9A%94-%EC%95%A0%ED%94%8C%EB%A6%AC%EC%BC%80%EC%9D%B4%EC%85%98-%EC%97%90%EB%9F%AC%EB%A5%BC-%EB%94%94%EB%B2%84%EA%B9%85%ED%95%A0-%EB%95%8C-%EA%B7%B8%EA%B2%83%EC%9D%84-%EC%96%B4%EB%96%BB%EA%B2%8C-%ED%99%9C%EC%9A%A9%ED%95%B4%EC%95%BC-%ED%95%98%EB%82%98%EC%9A%94

- JAVA로 동기화 해보자 
  https://tecoble.techcourse.co.kr/post/2021-10-23-java-synchronize/

- 브라우저 랜더링 
  https://d2.naver.com/helloworld/59361
  https://beomy.github.io/tech/browser/browser-rendering/