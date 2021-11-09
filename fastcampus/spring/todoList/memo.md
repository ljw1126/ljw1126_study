## spring boot 로 todo List API 서버 만들기 

- 필요기능 
    - 1. todo 리스트 목록에 아이템 추가 
    - 2. todo 리스트 목록 중 특정 아이템을 조회
    - 3. todo 리스트 전체 목록을 조회 
    - 4. todo 리스트 목록 중 특정 아이템을 수정 
    - 5. todo 리스트 목록 중 특정 아이템을 삭제 
    - 6. todo 리스트 전체 목록을 삭제 


###### 서버 요구사항 문서가 있는데 
- method, endpoint, 기능, request, response 항목으로 구성된 문서로 작업시 front, backend 간 소통 위해 사용됨 

###### http method / status code 정리하기 , dns 정리하기 
[https://medium.com/@lyhlg0201/http-method-d561b77df7](https://medium.com/@lyhlg0201/http-method-d561b77df7 'HTTP method, status code ')

###### 기록
```
- h2 : in memory 관계형 데이터 베이스 
- lombok 추가시 dependency 추가 말고도 인텔리제이 설정이 따로 있음 
  - 1. build.gradle 추가  // maven repository에서 검색해서 의존성 추가함 
    compileOnly ('org.projectlombok:lombok')
    annotationProcessor ('org.projectlombok:lombok')
  - 2. shift 두번 누르고 plugin 검색 (메뉴이동 가능)
    - marketplace에서 lombok 설치 ( 설치시 installed 탭 보기 ) 후 인텔리제이 재시작
    - [Build, Execution, Deployment] > [Compiler] > [Annotation Processors] 에 Enable annotation processing 체크 박스 선택, 저장 

- @Repository : DB를 통해 데이터를 주고 받는 인터페이스 
- 인터페이스 TodoRepository 생성하고 JpaRepository 상속받아서 간략하게 구현함 
- Service 또한 인터페이스로 생성하고 메소드 기반으로 간략하게 구현하는 방법이 존재한다함 (생략하고 그냥함) 
  - @Service에는 구현해야 할 메서드 만드는 곳 
- Controller 작성시 문서 보고 구현하면 됨

※ postman 설치하기 !! 
   https://www.postman.com/downloads/?utm_source=postman-home

※ https://www.todobackend.com/ 접속해서 로컬 실행한 주소 붙여 넣으면 todo 리스트 프론트 UI를 제공함 
   - 백엔드 구현에 대해서는 암묵적인 룰이 있는 것으로 생각됨
   - https://www.todobackend.com/specs/index.html
     - 상단 텍스트 중 "Contributors 'implement(클릭)' that spec using various "      
     - https://www.todobackend.com/contribute.html 에서 "The API is defined using 'a series of automated tests.(요거 클릭)' "
     - https://www.todobackend.com/specs/index.html 여기에 로컬호스트 주소 기재
       - 실패시 빠뜨린 부분 있으니 다시 확인하기 
     - 테스트 성공시 https://www.todobackend.com/ 에서 아래 테스트 프론트 클릭(Example Implementation) // 주소 부분은 유도리 있게 바꿈
       https://www.todobackend.com/client/index.html?http://localhost:8080     
     - 테스트 하면 로그 확인가능       

- Debug 사전적 의미 
  > 컴퓨터 프로그래밍 개발 단계 중에 발생하는 시스템에 논리적인 오류나 비정상적인 버그를 찾아내 원인을 수정하는 작업         

```