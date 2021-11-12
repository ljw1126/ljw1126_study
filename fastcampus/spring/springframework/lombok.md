## Lombok 


#### @Setter, @Getter 

- 속성명, 속성타입 지정해주면 lombok통해 getter, setter 자동 생성 및 호출 가능

```java 
@Setter
@Getter
public class DevDto {

    String name;
    Integer age;
    LocalDateTime startAt;

}
```

```java
class DevDtoTest { // junit 테스트
    @Test
    void test(){
        DevDto devDto = new DevDto();
        devDto.setName("snow");
        devDto.setAge(22);
        devDto.setStartAt(LocalDateTime.now());
        
        System.out.println(devDto.getName());
        System.out.println(devDto.getAge());
        System.out.println(devDto.getStartAt());
    }
}

```

<br/>

#### @ToString
- lombok 통해 해당 어노테이션 선언시 toString() 오버라이드 한 것 처럼 객체의 속성/속성값 확인 가능 
```java 
@Setter
@Getter
@ToString
public class DevDto {

    String name;
    Integer age;
    LocalDateTime startAt;

}
```

```java 
class DevDtoTest {
    @Test
    void test(){
        DevDto devDto = new DevDto();
        devDto.setName("snow");
        devDto.setAge(22);
        devDto.setStartAt(LocalDateTime.now());

        System.out.println(devDto); // DevDto(name=snow, age=22, startAt=2021-11-12T14:30:09.878263100)
        
    }
}


```

<br/>

#### @NoArgsConstructor / @AllArgsConstructor / @RequiredArgsConstructor
- @NoArgsConstructor 
  - 매개변수 없는 default 생성자 
- @AllArgsConstructor
  - 매개변수에 모든 속성/필드 존재하는 생성자 
- @RequiredArgsConstructor
  - 필수 속성이 존재하는 생성자 

<br/>

#### @Data 
-  @Getter @Setter @RequiredArgsConstructor @ToString @EqualsAndHashCode 내용을 지원 
-  **사용하지 않는/불필요한 기능도 들어가기 때문에 실무에서는 잘 사용하지 않음**
  
```java
@Data
public class DevDto {
    String name;
    Integer age;
    LocalDateTime startAt;
}
```
- @Data 어노테이션에 마우스 오른쪽 클릭 > Refactor > Delombok 누르면 아래와 같이 생성됨 
  
```java 
public class DevDto {
    String name;
    Integer age;
    LocalDateTime startAt;

    // 빈 생성자
    public DevDto() {}

    //getter, setter 생략.. 

    public boolean equals(final Object o) {...}

    protected boolean canEqual(final Object other) {...}

    public int hashCode() {...}

    public String toString() {
        return "DevDto(name=" + this.getName() + ", age=" + this.getAge() + ", startAt=" + this.getStartAt() + ")";
    }
}

```
<br/>

#### @Builder 

- builder pattern 의 장점 
  - 1. 필요한 데이터만 설정할 수 있음 
  - 2. 유연성을 확보할 수 있음 
  - 3. 가독성을 높일 수 있음
  - 4. 불변성을 확보할 수 있음 
- [https://mangkyu.tistory.com/163](https://mangkyu.tistory.com/163 '빌더 패턴(Builder Pattern)을 사용해야 하는 이유')

```java 
@Setter
@Getter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class DevDto {
    String name;
    Integer age;
    Integer experienceYears;
    LocalDateTime startAt;
}
```

- builder 디자인 패턴 사용하여 테스트 

```java
class DevDtoTest {
    @Test
    void test(){
        final DevDto devDto = DevDto.builder()
                        .name("snow")
                        .age(21)
                        .startAt(LocalDateTime.now())
                        .experienceYears(3)
                        .build();

        System.out.println(devDto);
    }
}

```
<br/>

#### @Slf4j 
- 예전에는 log4j 사용시 객체 선언 해줘야 되었는데, 어노테이션만 선언하면 바로 사용가능
```java 
@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Slf4j
public class DevDto {
    String name;
    Integer age;
    Integer experienceYears;
    LocalDateTime startAt;

    public void printLog(){
        log.info(getName());
    }
}
```

<br/>

#### @UtilityClass
- 유틸리티 클래스에 적용하는 어노테이션
- 정적 메소드 선언해서 공통 활용하는 용도 
```java
@UtilityClass
public class DevUtil {
    // static 한 함수를 선언해서 활용 ( ex. 날짜 포맷 변환 ) 
}
```
