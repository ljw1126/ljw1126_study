## 프로젝트 셋팅 
```
※ 아래 블로그 참조 해서 spring tool suit 4 까지 eclipse market 통해 설치하면 됨  
https://hello-bryan.tistory.com/328

// Window 기준
1. [Window - Perferences] 에서 installed 검색 
   > Java - Installed JREs 통해서 지금 설치/설정/사용가능한 jdk 확인가능
2. [Window - Perferences] 에서 Compiler 검색 
   > Java - Compiler 에서 우측 상단 Compiler compliance level 이 사용하려는 jdk 버전과 맞는지 확인(11버전 사용)
3. [Window - Perferences] 에서 encoding 검색 
   > General - Content Types 에서 Text 눌렀을때 하단 Default encoding 설정되있지 않는 경우 UTF-8 설정함
   > 이때 Text 하단 메뉴 중 Java Properties File / Spring Properties File은 다른걸로 설정되있는데 UTF-8로 수정함
4. [Window - Perferences] 에서 workspace 검색 
   > General - Workspace 에서 하단 Text file encoding을 UTF-8로 설정
5. [Window - Perferences] 에서 Gradle 검색 
   > "Automatic Project Synchronization" 체크 후 저장 
```

## 강의 소스 
[https://github.com/springrunner/learn-essences-of-spring](https://github.com/springrunner/learn-essences-of-spring)

- gradle project(v6.6.1) 생성 후 저장소 소스 덮어 씌우기 
  - v6.6 이상은 프로젝트 구성이 멀티 프로젝트 형태로 생성된다함
  - 아래와 같이 설정 후 생성 
![Alt text](/images/springrunner/gradle_setting.png) 