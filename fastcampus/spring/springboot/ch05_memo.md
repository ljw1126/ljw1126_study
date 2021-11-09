## Spring Native 
```

해당 강의에서는 spring native image를 만들어서 dokcer에 올려 실행하는 내용 설명함

# Native 란 ? (아래 연관 키워드)
    • GraalVM
    • AOT
    • High performance
    • Reduced memory consumption

# GraalVM 이란 
    "Run Programs Faster Anywhere"
    • Oracle Labs + 여러 협력사, 대학 연구실 협력 개발
    • 2012년 처음 세상에 공개, 2019. 5. 9. 첫 GA
    • 기존 C++로 만든 Hotspot JVM(20년,오라클에서)의 개발 한계(오래되고, 개발자 찾기 힘들어짐)를 극복하기 위한 Meta-circular JVM(java언어로 만든 JVM, 파이썬에는 파이썬 JVM존재)
    • 성능, 클라우드 환경, 다양성을 고려
    • https://www.graalvm.org/
    • 메모리 5배 적게 , 시작시간 50배 빨라짐 

# AOP란 
    "미리 기계어로 번역한다"
    • vs. JIT(just-in-time) - 기계어 번역 시점이 언제인가?
        • JIT: 중간 언어 (바이트코드) -> 기계어 (runtime)
        • AOT: 중간 언어 (바이트코드) -> 기계어 (compile time)
    • vs. Static Compiler - 무엇을 기계어로 번역하는가?
        • Static Compiler: 소스 코드 -> 기계어 (compile time)
        • AOT: 중간 언어 -> 기계어 (compile time)
    • 기계어 번역이 끝나 있으므로 속도가 더 빠름
    • 런타임에서 컴파일러를 필요로 하지 않아 더 가벼움

    ## Native image 만들어지는데 
    • AOT compiler 를 이용해 native image 빌드
    • 정적 분석 과정을 포함
    • 네이티브 바이너리 결과물은 즉시 실행 가능한 기계 코드 전체를 포함 - JVM 불필요
    • 다른 네이티브 이미지와 링크 가능
    • 더 빠른 성능, 적은 메모리 소모
    • 클라우드 네이티브 애플리케이션 배포에 효과적일 것으로 기대

    ## Spring Native로 할 수 있는 일 
    • 경량 docker container생성 

    ## 설치해야 하는 것들 
    • 1. sdkman 
      - 공식 사이트 https://sdkman.io/
        $ curl -s "https://get.sdkman.io" | bash
      - 에러1) Please install zip on your system using your favourite package manager.
        - 참고 http://dalinaum.github.io/java/2021/03/07/multiple-jdks-2.html
        - sdkman을 설치하기 위해서는 zip 유틸리티가 필요함
          - 윈도우 powershell 실행후 아래 명령어 실행 
            Set-ExecutionPolicy RemoteSigned -scope CurrentUser
            iwr -useb get.scoop.sh | iex
            scoop install zip
          - git bash 나 터미널 다시 켜서 설치  // Enjoy!! 뜨면 성공 !!
            $ curl -s "https://get.sdkman.io" | bash
            $ source "$HOME/.sdkman/bin/sdkman-init.sh"
            $ sdk list java 또는 sdk version 확인 되면 정상 설치 확인 됨 !
           
    • 2. GraalVM
      - sdkman 설치 후 git bash 로 아래 명령어 실행 
        $ sdk list java 
        $ sdk install java 11.0.12-open   // graalVM 설치하려니 안되서 우선... 
        $ sdk current 
          No candidates are in use
        $ sdk use java 11.0.12-open 
          Using java version 11.0.12-open in this shell.
        $ sdk current 
          Using:

          java: 11.0.12-open

          

        
    • 3. docker
      - 참고 - https://www.lainyzine.com/ko/article/a-complete-guide-to-how-to-install-docker-desktop-on-windows-10/#google_vignette
      - window powershell 실행후 아래 명령어 입력 
        $ dism.exe /online /enable-feature /featurename:VirtualMachinePlatform /all /norestart
        $ dism.exe /online /enable-feature /featurename:Microsoft-Windows-Subsystem-Linux /all /norestart
      - wsl_update_x64.exe 실행 및 설치 
      - Hyper-V 설정 활성화 (제어판)
      - 재시작 docker 아이콘 실행 
        // $ wsl --set-default-version 2 
      - cmd 창에서 확인용 명령어 입력
        $ docker run hello-world
      [docker rootless 모드 설정]
      



    ### 명령어 
    > sdk       // 명령어 확인 
    > sdk list java
    > sdk install 

```

## Testcontainers
```
1. build.gradle에 testcontainer 의존성 추가 및 설정 추가 
2. 


```


## Config Server
```


```


## Config Client
```


```

