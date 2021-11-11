
- CRUD = 'Create', 'Read', 'Update', 'Delete'
  - Create = POST/PUT
  - Read = GET
  - Update = PUT 
  - Delete = DELETE 

###### GET 
- 서버에게 특정 Resource 요청하는데 사용
- 게시글 목록 조회와 같은 URI에 검색 파라미터와 키워드를 넣어 요청 하는데 사용
- [https://developer.mozilla.org/ko/docs/Web/HTTP/Methods/GET](https://developer.mozilla.org/ko/docs/Web/HTTP/Methods/GET) 

<br/>

###### PUT 
- 요청된 URL에 정의된 새로운 Resource를 생성하거나 Resource 내용(데이터) 전체 갱신하기 위함 
- POST 메소드로 가능한 리소스 생성을 PUT 메소드로 대체 가능 
- [https://developer.mozilla.org/ko/docs/Web/HTTP/Methods/PUT](https://developer.mozilla.org/ko/docs/Web/HTTP/Methods/PUT)

<br/>

###### PATCH 
- PATCH 메소드는 리소스의 '부분적인 수정'을 할 때에 사용 ( ↔ PUT 메소드는 resource 전체 내용 갱신만을 허용)
- [https://developer.mozilla.org/ko/docs/Web/HTTP/Methods/PATCH](https://developer.mozilla.org/ko/docs/Web/HTTP/Methods/PATCH)

<br/>

###### POST 
- 서버로 데이터를 전송하고, 요청 유형은 Content-Type 헤더로 나타낸다.
- 게시글 작성과 같은 새로운 리소스 데이터를 만들 때 주로 사용 
- URI 검색시 GET으로 가능하지만, 추가 파라미터(키워드)가 길 경우 POST 메소드 사용
- [https://developer.mozilla.org/ko/docs/Web/HTTP/Methods/POST](https://developer.mozilla.org/ko/docs/Web/HTTP/Methods/POST)

<br/>

###### DELETE 
- 지정한 resource 삭제 요청시 사용 
- [https://developer.mozilla.org/ko/docs/Web/HTTP/Methods/DELETE](https://developer.mozilla.org/ko/docs/Web/HTTP/Methods/DELETE)

<br/>

###### OPTIONS 
- 서버에서 지원하고 있는 method 정보 취득 
- curl 이용해여 OPTIONS 요청을 서버에 보냄으로써 서버에서 지원하는 method 확인 

```
  curl -X OPTIONS http://example.org -i
```

- 응답에 포함된 Allow 헤더를 통해 허용 method 확인 

```
  200 OK
  Allow: OPTIONS, GET, HEAD, POST
  Cache-Control: max-age=604800
  Content-Type: text/html; charset=UTF-8
  Date: Thu, 11 Nov 2021 13:41:03 GMT
  Expires: Thu, 18 Nov 2021 13:41:03 GMT 
  Server: EOS (vny/0452)
  Content-Length: 0
```

[https://developer.mozilla.org/ko/docs/Web/HTTP/Methods/OPTIONS](https://developer.mozilla.org/ko/docs/Web/HTTP/Methods/OPTIONS)

<br/>

###### TRACE 
- 클라이언트가 서버로 TRACE 요청을 보내면 서버는 요청받은 메시지를 그대로 반환하여 응답한다.
- XST : TRACE요청에 의해 반환되는 응답에는 사용자의 쿠키정보 등과 같은 중요정보도 포함되게 되는데 이걸 가로채는 공격
- 보통 웹취약점 검사시 TRACE 되지 않도록 처리해야 함  
  - 방법1. apache 설정파일 중 하나인 httpd.conf에 설정 통한 method 제한 
  - 방법2. tomcat 설정파일 web.xml에 설정 통한 method 제한 
  - 방법3. spring framework에 web.xml에 설정통한 method 제한 

[https://itinformation.tistory.com/49](https://itinformation.tistory.com/49)

--- 

###### Http status code (http 상태 코드)

- 1xx (정보): 요청을 받았으며 프로세스를 계속함
- 2xx (성공): 요청을 성공적으로 받았으며 인식했고 수용함
- 3xx (리다이렉션): 요청 완료를 위해 추가 작업 조치가 필요함
- 4xx (클라이언트 오류): 요청의 문법이 잘못되었거나 요청을 처리 불가 상태
  - 400(잘못된 요청) : 서버가 요청의 구문을 인식 못한 상태 
  - 401(권한 없음) : 서버에서 사용자 인증 요구,인증실패, Unauthorized 
  - 403(Forbidden, 금지됨) : 서버가 클라이언트 요청 거부. 예로 사용자가 리소스에 대한 권한을 갖지 않음
  - 404(Not Found) : 서버가 요청한 페이지(Resource)를 찾지 못함 
- 5xx (서버 오류): 서버가 명백히 유효한 요청에 대해 충족을 실패
  - 500(내부 서버 오류): 서버에 오류가 발생하여 요청을 수행 x 
  - 501(구현되지 않음): 서버에 요청을 수행할 수 있는 기능이 없다. 예를 들어 서버가 요청 메소드를 인식하지 못할 때 이 코드를 표시한다.
  - 502 (Bad Gateway, 불량 게이트웨이): 서버가 게이트웨이나 프록시 역할을 하고 있거나 또는 업스트림 서버에서 잘못된 응답을 받았다.
  - 503(서비스를 사용할 수 없음): 서버가 오버로드되었거나 유지관리를 위해 다운되었기 때문에 현재 서버를 사용할 수 없다. 이는 대개 일시적인 상태이다.
  - 504(게이트웨이 시간초과): 서버가 게이트웨이나 프록시 역할을 하고 있거나 또는 업스트림 서버에서 제때 요청을 받지 못했다.

[https://ko.wikipedia.org/wiki/HTTP_%EC%83%81%ED%83%9C_%EC%BD%94%EB%93%9C](https://ko.wikipedia.org/wiki/HTTP_%EC%83%81%ED%83%9C_%EC%BD%94%EB%93%9C)
