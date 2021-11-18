## 맥 vs 윈도우
|     맥     |    윈도우   |
|:----------:|:----------:|
|     ⌘     |   command  |
|     ⌥     |    option  |
|     ⇧      |    shift   |
|     ⌃      |    control |

## IntellJ 단축키 정리 

|                       |       맥                  |        윈도우          |
|:---------------------:|:-------------------------:|:---------------------:|
| 프로젝트 창 포커스 이동 |      ⌘1                  |     Alt +1            |
| 에디터 창으로 이동      |     ESC                   |     ESC               |
| 프로젝트 창 미리보기    |    space                  |      space            |
| *에디터 창만 남기고 숨김|      ⇧⌘F12                |  ctrl + shift + F12   |
| 에디터 창 이동         |     ⌃tab                  |    ctrl + tab         |
| 새파일 생성- 에디터에서 |      ⌃⌥N                 |  ctrl + alt + Insert   |
| 새파일 생성- 프로젝트창에서|     ⌘N                |    alt + Insert        |
| 단어별 커서 이동          |     ⌥ + ← →           |    ctrl + ← →          |
| 라인 시작/끝 커서 이동     |    fn + ← →           |   home, end            |
| 페이지 위/아래 커서 이동   |    fn + ↑ ↓           |  page up, page down    |
| 영역 선택 확장/축소        |    ⌥↑,⌥↓            |   ctrl + W, ctrl + shift + W  |
| 한 줄 주석                |       ⌘/             |   ctrl + /             |
| 블록 주석                 |     ⌥⌘/             |    shift + ctrl + /     |
| 들여쓰기                  |     tab               |    tab                  |
| 들여쓰기 취소             |     ⇧tab              |    shift + tab          |
| 자동 들여쓰기             |    ⌃⌥I               |   ctrl + alt + I         |
| 사용차 찾기               |     ⌥F7              |   alt + F7               |
| 빠른 찾기                 |      ⌘B              |   ctrl + B               |
| 파일내 찾기               |      ⌘F              |  ctrl + F                |
| 찾은 결과 이동            |   ⌘G, ⌘⇧G           |   F3, shift + F3         |
| 경로내 검색               |    ⌘⇧F               |   ctrl + shift + F       |
| *전체 검색                |      ⇧2번             |   shift 2번              |
| 최근 파일 열기            |     ⌘E               |    ctrl + E              |
| 터미널 창                |   ⌥F12                 |  alt + f12              |
| 구문 완성                |   ⇧⌘Enter             |   shift + ctrl + enter   |
| 파일 내 대체(키워드)      |    ⌘R                 |  ctrl + R                |
| 경로 내 대체(키워드)      |    ⌘⇧R                |  ctrl + shift + R        |
| Run anything            |     ⌃ 2번                |  ctrl 2번               |
| 에디터 실행              |     ⌃⇧R                 |    ctrl + shift + F10   |
| 실행                     |     ⌃R                 |   shift + F10            |
| 실행 종료                |     ⌘F2                |    ctrl + F2            |
| 라인 복사                |     ⌘D                 |   ctrl + D              |
| 라인 삭제                |     ⌘delete           |  ctrl + y                |
| 메소드 파라미터 정보 확인 |  ⌘P                   | ctrl + P                 |
| Quick Definition        |     ⌥space            |  ctrl + shift + I        |
| Quick Document          |    F1                  |   ctrl + Q               |
| *기능(action) 찾기       |     ⇧⌘A               |   shift + ctrl + A       |
| Java Doc                |  양식 맞춰 작성          |   양식 맞춰 작성          |
| 주요 단축키              | help-keymap ref.. 메뉴  |  help-keymap ref.. 메뉴   |


## 단축키 응용 
|                       |       맥                  |        윈도우          |
|:---------------------:|:-------------------------:|:---------------------:|
| 테스트 코드 생성       |      ⌥⏎                  |       alt + enter     |
| 테스크 코드 이동       |      ⇧⌘T                 |      ctrl + shift + T |
| 선택된 테스트 코드 실행 |     ⌃⇧R                   |       Ctrl+Shift+F10  |
| 정지                  |     ⌘F2                   |       Ctrl+F2         |
| 리팩토링 - 클래스 이동 |         F6                 |          F6           |
| 리팩토링 - 타입 변경   |          ⇧⌘F6             |  ctrl +shift + F6     |
| 리팩토링 - 시그니처변경 |        ⌘F6               |       ctrl + F6       |
| 리팩토링 - 이름변경    |        ⇧F6                 |        shift + F6     |
| 리팩토링 기능 리스트*  |          ⌃T                | ctrl + alt + shift + T|
| 디버깅 - brk pt 설정  |         ⌘F8                |        ctrl + F8     |
| 디버깅 - brk pt 보기  |       ⌘⇧F                  |  ctrl + shift + F8   |
| 디버깅 - 모드 실행    |          ⌃D                  |     shift + F9       |
| 디버깅 - 데이터 실행  |        ⌃⇧D                   |   마우스로 직접 실행   |
| 디버깅 - step into(실행라인)|    F7                   |   F8                |
| 디버깅 - step over(다음라인)|    F7                   |   F8                |
| 디버깅 - resume program |       ⌘⌥R                 |     F9              |
| Git                   |        ⌃V                    |        alt + `      |

- 시그니처 == 오버로딩 메소드 생성 
- shift 2번 -> keymap 검색  

## live template 설정 (psvm , sout 과 같은) 
- preference == setting 메뉴 
- 내가원하는 템플리 추가 하기 : ctrl + alt + s > Editor > Live Template  
- java 부분에 항목 추가 후 Abbreviation 입력창에 명칭넣고 
- Template text에 코드 구문 넣고 
- 하단에 언어로 JAVA 선택 해주면 됨
- 단축키 확인은 setting(ctrl + alt + s) > 좌측 keymap 메뉴 > 검색 
  - auto formatting > **alt + ctrl + L**
  - import 정리 > **alt + ctrl + O**
  - macro 설정기능도 있음 
- 최근 열었떤 파일 확인 : ctrl + e
- DMakerService.java에 validateCreateDeveloperRequest() 보면 인텔리제이 리팩토링 기능 활용해서 지역변수 선언해서 좀더 깔끔하게 할 수 있음 
  - 공통 지역변수로 사용할 부분을 선택 > 마우스 오른쪽 클릭 > Refactor > Introduce Variable .. 선택 > 전체 메소드에 대해 선택 
- 테스트 파일 만들기 : **ctrl + shift + t**
- javaDoc : /** + enter