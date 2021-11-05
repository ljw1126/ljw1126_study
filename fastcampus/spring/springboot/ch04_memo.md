## 실무를 고급지게 만들어주는 기능들
```
# Lombok(롬북)
- "Never write another getter or equals method again"
- Boilerplate code를 줄여주는 도구 
- 생산성 향상에 기여 
- https://projectlombok.org/
- Spring initializer 지원 
- 의존성 추가, 버전 관리 등 빌드 도구 설정에 큰 신경을 쓰지 않아도 됨 (https://start.spring.io/ 에서 dependencies에 lombok검색후 추가후 build.gradle 설정 내용 확인)
  - build.gradle에 추가 
    compileOnly 'org.projectlombok:lombok'
    annotationProcessor 'org.projectlombok:lombok'

# Lombok에서 제일 인기 있는 기능들 
    @Data
        • @Getter + @Setter + @RequiredArgsConstructor + @ToString +
        @EqualsAndHashCode
        • 편함
        • 근데 그래서 조심해야 함
        • @RequiredArgsConstructor >> 스프링 생성자 주입에 잘 어울려서 애용됨
    @Value
        • 불변 객체를 만들때 씀
        • @Getter @FieldDefaults(makeFinal=true, level=AccessLevel.PRIVATE)
        @AllArgsConstructor @ToString @EqualsAndHashCode

# 기타 팁 
    • Java 16 과 호환성 문제가 있음 -> 버전 1.18.20 해결
    • Spring Boot 2.4.4 이하는 버전 수동으로 입력해줄 것
    • https://github.com/projectlombok/lombok/issues/2681
    • Intellij Lombok plugin -> "Enable annotation processor" 자동 적용이라 안 해도 돼요~
    • 자동으로 다 해준다 vs. 디테일을 나도 모르게 바꾼다
    • 프로그래밍 세계는 끊임없이 경쟁과 발전 중
```