## 맥 vs 윈도우
|     맥     |    윈도우   |
|:----------:|:----------:|
|     ⌘     |   command  |
|     ⌥     |    option  |
|     ⇧      |    shift   |
|     ⌃      |    control |

## IntellJ 단축키 정리 

- alt + 좌우 방향키로 열려있는 탭 이동 가능 (ctrl + tab은 방향키 안 쓰고 탭 이동 가능)
- ctrl + w (w 연타하면 선택 확장됨) <-> ctrl + shift + w (w 연타하면 반대로 선택 영역 축소)

- 인덴트를 하지 않는 것은 가독성을 떨어뜨리고, 장기적으로 유지보수 악영향 주실 수 있음 
  - 인덴트 잘 맞춰서 코딩 하는 습관을 들이는게 좋다

- 파일 내 전체 자동 인덴트
  - ctrl + a (전체 선택) 후 ctrl + alt + i 하면 됨

- 메소드 사용처 찾기 
  - 메소드 위에서 ctrl + b 누르면 목록 뜨고 이동가능 (여러개 인 경우 선택해야 함) 
  - alt + f7 눌러서 에디터 아래에 미리보기 통해 확인도 가능

- 검색시 
  - ctrl + f 로 단어 검색 
  - f3 다음 이동, shift + f3 으로 이전 결과 이동

- 최근 열었던 파일 목록 
  - ctrl + e 
 


|                       |       맥                  |        윈도우          |
|:---------------------:|:-------------------------:|:---------------------:|
| 프로젝트 창 포커스 이동 |      ⌘1                  |     Alt +1            |
| 에디터 창으로 이동      |     ESC                   |     ESC               |
| 프로젝트 창 미리보기    |    space                  |      space            |
| 에디터 창만 남기고 숨김✨|      ⇧⌘F12                |  ctrl + shift + F12   |

| 에디터 탭 창 이동✨      |     ⌃tab                  |    ctrl + tab // tab 으로 선택 |

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
| 자동 들여쓰기 ✨            |    ⌃⌥I               |   ctrl + alt + I         |

| 사용차 찾기               |     ⌥F7              |   alt + F7               |
| 빠른 찾기                 |      ⌘B              |   ctrl + B               |

| 파일내 찾기               |      ⌘F              |  ctrl + F                |
| 찾은 결과내 이동            |   ⌘G, ⌘⇧G           |   F3, shift + F3         |

| *경로내 검색                 |    ⌘⇧F               |   **ctrl + shift + F**  |
| *전체 검색(intellij 기능 포함)|      ⇧2번             |   shift 2번             |

| 최근 파일 열기✨            |     ⌘E               |    ctrl + E              |

|Live template             |                       |     ctrl + j              |
|퀵픽스  ✨ (psvm, sout)  |        ⌥ Enter      |       alt + enter               |

| 코드 이슈 별로 이동      |                       |  F2, shift + f2               |
| import 최적화 ✨          |                       | ctrl + alt + o                |

| 코드 생성✨ (getter, setter) |                       |   alt + insert             |

|override 메소드 자동 완성 ✨ |                       |  ctrl + o                     |
|implement 메소드 자동 완성 ✨|                       |  ctrl + i   (퀵 픽스가 더 빠를 수도)|

| 터미널 창                |   ⌥F12                 |  alt + f12              |

| 구문 완성 🤔(아직 안써봄)|   ⇧⌘Enter             |   shift + ctrl + enter   |

| 파일 내 Replace(키워드)✨        |    ⌘R                 |  **ctrl + R**               |
| 패키지 경로 내 Replace(키워드)✨ |    ⌘⇧R                |  **ctrl + shift + R**        |

| Run anything  (별로👎)   |     ⌃ 2번                |  ctrl 2번               |

| 에디터 실행              |     ⌃⇧R                 |    ctrl + shift + F10   |
| 실행                     |     ⌃R                 |   shift + F10            |
| 실행 종료                |     ⌘F2                |    ctrl + F2            |

| 라인 복사                |     ⌘D                 |   ctrl + D              |
| 라인 삭제                |     ⌘delete           |  ctrl + y                |

| 메소드 파라미터 정보 확인✨ |  ⌘P                   | ctrl + P                 |

| Quick Definition✨(구현부 간단히 확인 가능) |     ⌥space            |  ctrl + shift + I        |
| Quick Document✨        |    F1                  |   ctrl + Q               |

//Action에 검색어 입력하면 단축키 확인 가능
| 기능(action) 찾기 ✨      |     ⇧⌘A               |   shift + ctrl + A       |

| Java Doc                |  양식 맞춰 작성          |   양식 맞춰 작성          |
| 주요 단축키              | help-keymap ref.. 메뉴  |  [Help > keymap ref..] 메뉴   |


## 단축키 응용 
- 클래스 명에 포커스 두고 alt + enter 눌러서 테스트 코드 파일 생성
- 타입변경에 파라미터 타입과 매소드 타입을 구분
- 시그니처 
  - 메소드에 포커스 두고, 접근자, 리턴타입, 파라미터 등 변경 가능

|                       |       맥                  |        윈도우          |
|:---------------------:|:-------------------------:|:---------------------:|
| 테스트 코드 생성       |      ⌥⏎                  |       alt + enter     |
| 테스크 코드 이동       |      ⇧⌘T                 |      ctrl + shift + T |

| 선택된 테스트 코드 실행 |     ⌃⇧R                   |       Ctrl+Shift+F10  |
| 정지                  |     ⌘F2                   |       Ctrl+F2         |

| 리팩토링 - 클래스 이동 |         F6                 |          F6           |
| 리팩토링 - 타입 변경   |          ⇧⌘F6             |  ctrl +shift + F6     |
| 리팩토링 - 시그니처변경 |        ⌘F6               |       ctrl + F6       |
| 리팩토링 - 이름변경✨ |        ⇧F6                 |        shift + F6     |

| 리팩토링 기능 리스트✨|          ⌃T                | ctrl + alt + shift + T|

| 디버깅 - brk pt 설정✨  |         ⌘F8                |        ctrl + F8     |
| 디버깅 - brk pt 보기✨  |       ⌘⇧F                  |  ctrl + shift + F8   |
| 디버깅 모드 실행✨    |          ⌃D                  |     shift + F9       |
| 디버깅 - 데이터 실행  |        ⌃⇧D                   |   마우스로 직접 실행   |

| 디버깅 - step into(메소드 내부 안으로)|    F7                   |   F8                |
| 디버깅 - step over(다음라인)|    F7                   |   F8                |
| 디버깅 - resume program(다음 brk로) |       ⌘⌥R                 |     F9              |

| Git✨                  |        ⌃V                    |        alt + `      |

- 시그니처 == 오버로딩 메소드 생성 
- shift 2번 -> keymap 검색  

## live template 설정 (psvm , sout 과 같은) 
-  메뉴 
- 단축키 확인은 setting(ctrl + alt + s) > 좌측 keymap 메뉴 > 검색 
  - auto formatting(✨) > **alt + ctrl + L**
  - import 정리(✨) > **alt + ctrl + O**
  - macro 설정기능도 있음 
- 최근 열었떤 파일 확인 : ctrl + e
- DMakerService.java에 validateCreateDeveloperRequest() 보면 인텔리제이 리팩토링 기능 활용해서 지역변수 선언해서 좀더 깔끔하게 할 수 있음 
  - 공통 지역변수로 사용할 부분을 선택 > 마우스 오른쪽 클릭 > Refactor > Introduce Variable .. 선택 > 전체 메소드에 대해 선택 
- 테스트 파일 만들기 : **ctrl + shift + t**
- javaDoc : /** + enter
- Override 메소드 확인 및 표출(extends 한 후에) : ctrl + o
- 인터페이스 구현체 목록 확인 : **ctrl+ alt + b**
- break point 관리화면 : ctrl + shift + f8 또는 **shift + shift 누른 후 remove all breakpoints 선택**
- 리 컴파일 : ctrl + shift + f9     // view 파일 수정 후 
- 생성자/getter/setter 등 생성 : **alt + insert** 
- **자동정렬 : ctrl + alt + l **


###### 한글 설정 
[https://goddaehee.tistory.com/248](https://goddaehee.tistory.com/248)

```

※ preference == setting 동일 메뉴

1. 프로젝트 JDK 설정

Project Settings > Project > Project SDK > 원하는 JDK 선택 (없다면 New 로 추가)

 

2. 테마 설정

Preferences > Appearance & Behavior > Appearance > Theme : Darcula 로 설정

 

3. 폰트 변경

Preferences > Editor > Font > Font : MesloLGS NF 선택

 

4. 라인넘버 표시

Preferences > Editor > General > Appearance > Show line numbers

 

5. 공백문자 표시

Preferences > Editor > General > Appearance > Show whitespace 체크 ( 아래3개항목 모두 체크 )

 

6. 대소문자 자동완성 해제

Preferences > Editor > General > Code Completion > Match case 체크 해제

 

7. 스펠링 체크 해제

Preferences > Editor > Inspections > Spelling 체크 해제

 

8. 파일 인코딩 설정

Preferences > Editor > File Encodings > Global Encoding : UTF-8

Preferences > Editor > File Encodings > Project Encoding : 기본 system UTF-8 (프로젝트에 맞는 인코딩 설정)

Preferences > Editor > File Encodings > Properties Files : 기본 system UTF-8 (프로젝트에 맞는 인코딩 설정)

Preferences > Editor > File Encodings > Properties Files : Transparent native-to-ascii conversion 체크 (한글표시)

 

9. 코드 폴딩 해제

Preferences > Editor > General > Code Folding > Imports 체크 해제, One-line methos 체크 해제, "Closures" 체크 해제, Generic constructor and method parameters 체크 해제

 

10. Tomcat 서버 설정

Preferences > Build, Execution, Deployment > Application Servers > + 클릭 > Tomcat Server > Tomcat 설치 디렉토리 지정


11. 원하는 Template 추가
- file메뉴 > setting(ctrl + alt + s) > Editor > Live Template  
- java 부분에 항목 추가(+) 후 Abbreviation 입력창에 내용 기재 
- Template text에 코드 구문 기재
- 하단에 언어로 JAVA 선택 후 저장

12. 함수가 한줄로 표시되는 문제 
- [Settings > Editor > General > Code Folding] 에서 "One-line methods" 체크해제

13. 롬북 플러그인 설치하기 !
- [file > Settings > Plugins]에서 lombok 검색 후 설치하기 
- [file > Settings > Build, Execution, deployment > Compiler > Annotation Processors]에서 Enable annotation processing을 체크해줌

14. Gradle refresh 명령어 
> ./gradlew build --refresh-dependencies // refresh 아이콘이 있음

15. Request Mapping 주소 찾기 
> 인텔리제이 plugin 인 request mapper 설치 
> ctrl + shift + \(백스페이스) 누르고 검색 

[에러]
1. finished with non-zero exit value 1
  # 해결 
    > Setting(ctrl + alt + s) 에서 [build, execution, deployment > Build tools > Gradle] 메뉴 이동 
    > gradle에 build and run using 과 Run tests using을 IntelliJ IDEA로 변경 후 저장 

  # 참고 
  https://yjh5369.tistory.com/entry/intellij-%EC%8B%A4%ED%96%89-%EC%8B%9C-finished-with-non-zero-exit-value-1-%EC%98%A4%EB%A5%98


16. Java Import * 설정 off
  File > Settings > Project Settings > Code Style > Java > Imports 탭 
  import할 때 *을 사용하지 않도록 아래 항목 숫자를 늘려둔다
  ㄴ Class count to use import with '*' : 999
  ㄴ Names count to use static Import with '*' : 999

17. third party javascript library warning 제거

jquery의 $를 인식못하는 등 자꾸 warning을 뿜어내는데, 인터넷에 있는 방법으로는 깔끔하게 처리가 안된다.
File > Settings > Language & Frameworks > Javascript > Libraries > Add
아래 그림과 같이 Attach Diretories에서 javascript library가 모여있는 Diretory를 선택한다. (File만 등록하면 제대로 동작을 안한다 ;;;;)

18. 아래는 못 찾음..
Plug in
Save Actions
아래 옵션 체크

Optimize imports

Add missing @Override annotations

Add a serialVersionUID field for Serializable classes

Remove unneccessary this to field and method

Remove unnecessary semicolon

```

## git bash 연동 
- Setting(ctrl + alt + s) > Tools > Terminal 메뉴 이동
- Shell path 가 default 로 cmd.exe 되어 있는데 아래와 같이 변경
```
"C:\Program Files\Git\bin\sh.exe" -login -i
```

![Alt text](/images/intellij/gitbash연동1.png)

- 저장 후 인텔리제이 재시작 하면 Terminal 탭이 git bash 로 변경되어 있음 

![Alt text](/images/intellij/gitbash연동2.png)

###### 참고
[https://goddaehee.tistory.com/250](https://goddaehee.tistory.com/250 '갓대희의 작은공간')
