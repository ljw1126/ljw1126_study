## 기록 

```
# 1. build.gradle 의존성 추가 
    
    implementation 'org.springframework.boot:spring-boot-starter-thymeleaf'
    implementation 'nz.net.ultraq.thymeleaf:thymeleaf-layout-dialect'

# 2. application.properties 
    
    spring.thymeleaf.cache=false


# 3. 인텔리제이 설정 변경
    ## 3-1 
    > ctrl + alt + s  (setting 메뉴) 
      > thy 검색 inspections메뉴에서 Thymeleaf 검사 항목 모두 체크 해제 
    
    ## 3-2 Unknown html tag th:block  
    >

```



###### ※ 3. 인텔리제이 설정 변경 이미지 
![Alt text](/images/frontend/thymeleaf설정해제.png)
