

VS Code 관련
 - 설치 및 좋은 익스텐션 소개: https://youtu.be/bS9yTI2fC0w
 - 단축키: https://youtu.be/EVxCdenPbFs
 - 마지막 익스텐션: https://youtu.be/m7wsrVQsVjI 

1) window : ctrl + , 
2) strict null 검색 
3) TypeScript 탭에서 해당 항목 체크 후 저장 
   JS/TS Implicit Project Config : Strict Null Check 

터미널 
- mac : iTerm2 추천 
- window : cmder 추천 

npm : node 기반으로 한 패키지 매니저로써 다양한 라이브러리를 추가/삭제 가능하도록 해줌 

노드 설치 확인 ( 최신보다 안정적인 버전 사용 )
> node -v      // node 버전 확인 
14.15.1
> npm -v       // npm 버전확인 ( node 설치시 같이 설치됨 )

타입스크립트 설치 
> npm install -g typescript      //글로벌 설치 ( 이외 다른 방법도 있다함 )
> tsc -v                         //typescript 컴파일러 버전확인 

---

Typescript 공식 사이트 > https://www.typescriptlang.org/
* Typescript 는 오픈소스이다, git hub에서 이슈 확인가능 , 공식사이트 통해서 버전별 변경내용 확인가능 

Typescript 컴파일러 툴 설치 
> npm install -g ts-node           

비쥬얼 스튜디오 코드의 Live Server 익스텐션 설치 

* html 파일 만든 뒤 ! + tab 누르면 템플릿 자동완성 됨 

```
* tsc 실행시 에러 발생하는 경우 

위치 줄:1 문자:1
+ tsc main.ts
+ ~~~
    + CategoryInfo          : 보안 오류: (:) [], PSSecurityException
    + FullyQualifiedErrorId : UnauthorizedAccess

* 해결방안
- window powrshell 프로그램을 관리자 권한으로 실행 
> ExecutionPolicy 
Restricted     
> Set-ExecutionPolicy Unrestricted 
> y 입력 
> ExecutionPolicy
Unrestricted 

```

node main.js
ts-node main.ts 
tsc -h       //typescript 옵션 확인 
tsct main-ts -w      //watch 모드로 하면 *.ts파일 갱신되면 자동으로 *.js 반영해줌 


---
* Programming ? 
  input - operation - output
  // 지정된 type에 맞춰 정확한 type을 출력가능 함 


---
* 윈도우 아이콘 이모지  >  window키 + . 또는 window키 + ;


