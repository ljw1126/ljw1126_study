###### git hub 예제 소스 
[https://github.com/snow-in-summer/dmaker](https://github.com/snow-in-summer/dmaker)

#### Lombok
- [lobok.md 참조](/fastcampus/spring/springframework/lombok.md)

#### H2 Database
- 스프링 부트에 내장된 임베디드 데이터 베이스 
- [resources > application.yml] 파일 생성 후 아래 내용 작성 

```
spring:
  h2 :
    console:
      enabled : true
```

- 스프링 부트 실행시 console에 h2 database에 대한 로그 확인 (*매번 변경됨)

```
o.s.b.a.h2.H2ConsoleAutoConfiguration    : H2 console available at '/h2-console'. Database available at 'jdbc:h2:mem:dc823433-c1ff-42f1-bf13-f0478adc6d88'
```

- 주소창에 localhost:8080/h2-console 접속 
- 로그에 있는 jdbc url 복붙하고 로그인하면 됨(아이디 / 비번 x)

<br/>

###### JPA 활용하여 H2 Database에 데이터 넣는 내용 (간단 테스트)

```java 
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@EntityListeners(AuditingEntityListener.class)     // 추가 안할시 @EnableJpaAuditing 제대로 동작 안함  , https://www.baeldung.com/ 참고하기 ! 
public class Developer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;

    @Enumerated(EnumType.STRING)
    private DeveloperLevel developerLevel;

    @Enumerated(EnumType.STRING)
    private DeveloperSkillType developerSkillType;

    private Integer experienceYears;
    private String memberId;
    private String name;
    private Integer age;

    /*
        아래 두 어노테이션(auditing기능용)을 사용하기 위해서는 
        시작점이 되는 *Application.class에 @EnableJpaAuditing 추가 필요 

        @EnableJpaAuditing
        @SpringBootApplication

        // 라이브러리에서 생성일, 수정일 데이터 넣어줄 수 있음 
    */
    @CreatedDate
    private LocalDateTime createdAt;      

    @LastModifiedDate
    private LocalDateTime updatedAt;
}


```

<br/>

- @Entity 정의 후 @Repository 인터페이스 생성 정의하면 jpa 활용한 임시 데이터 저장 가능한 구조 만들 수 있음
```java 
package com.developers.dmaker.repository;

import com.developers.dmaker.entity.Developer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DeveloperRepository extends JpaRepository<Developer, Long> { // 인터페이스 
    /*
        public interface 이름 extends JpaRepository <엔티티 , ID 유형>
    */
}

```

<br/>

- @Service 생성 

```java 
@Service
@RequiredArgsConstructor
public class DMakerService {
    private final DeveloperRepository developerRepository;
    /*
        @Autowired, @Injection 어노테이션 사용시
        Service class가 의존하는 클래스에 대해 종속적이기 때문에 테스트하기 불편한 문제 발생!
        그래서 생성자 방식 나옴
        public DMakerService(DeveloperRepository developerRepository){
            this.developerRepository = developerRepository;
        }
        그런데 서비스에서 생성자로 받야아하는게 추가될수록 수정 번거로워지니
        @RequiredArgsConstructor 통해서 자동으로 생성자 변경되고 할당되도록 함!
        (final이 있으면 무조건 있어야 되기 떄문에 기본 생성자를 만들어 줌 ) 
    */
    public void createDeveloper(){
        //builder 패턴으로 객체 생성
        Developer developer = Developer.builder()
                .developerLevel(DeveloperLevel.JUNIOR)
                .developerSkillType(DeveloperSkillType.FRONT_END)
                .experienceYears(2)
                .name("Olaf")
                .age(30)
                .build();

        developerRepository.save(developer); //JpaRepository 클래스에 정의된 메소드
    }

}
```





###### 단축키
- 미생성 클래스, enum 같은게 있을 경우 : 이름 옆에 커서 두고 alt + enter 
 

###### ch03-10. 간단한 http 테스트 만들기 
- test 폴더 하위에 http 폴더 생성 > dev.create.http 파일 생성 
- dev.create.http 우측 탭에 Examples 통해 post 예제 확인 후 원하는 request 내용 작성

```
POST http://localhost:8080/create-developers             //요청라인
Content-Type: application/json                           //헤더라인

{                               // 한칸 띄우고 데이터가 담긴 body라인 
  "developerLevel": "JUNIOR",
  "developerSkillType" : "FRONT_END",
  "experienceYears": 2,
  "memberId": "Micky",
  "name": "mouse",
  "age" : "28"
}
```

#### ch03_11 상세정보 호출 GET 
```
1. 실행시 콘솔에 sql 과 파라미터 출력되도록 application.yml 설정 수정 

spring:
  h2 :
    console:
      enabled : true
  jpa:              // 요기 부터 추가
    show-sql : true          
    properties:              
      hibernate:
        format_sql: true        
        use_sql_comments : true

참고 주소 - https://www.baeldung.com/sql-logging-spring-boot


2. get으로 정보 구현시 @Entity 사용하지 않고 Dto 신규 생성 
- request 요청시 @Entity 로 된 객체 리스트 반환할 경우 불필요한 정보까지 노출가능! (좋지 않은 패턴)
- transcation 이 없는 상태에서 접근하는 것은 좋지 않은 상태 
- 그래서 Dto로 분리해서 하는게 유연성에 좋다함 (역할 분담하는 듯)

```

#### ch03_12 정보 수정 PUT 
```
※ PUT은 모든 정보 수정 , PATCH는 정보 일부 수정 
- Edit(수정하기) 위해 DMakerService에 비즈니스 로직을 작성하는 validation을 따로 작성시 공통되는 부분이 또 존재하게 됨 
- 그래서 해당 영역 선택 후 마우스 오른쪽 클릭 > Refactor > Extract Method..

        if (developerLevel == DeveloperLevel.SENIOR
                && experienceYears < 10) {
            throw new DMakerException(LEVEL_EXPERIENCE_YEARS_NOT_MATCHED);
        }
        // 2. junior 인데 4년 미만, 10년 이상인 경우
        if (developerLevel == DeveloperLevel.JUNGNIOR
                && (experienceYears < 4 || experienceYears > 10)) {
            throw new DMakerException(LEVEL_EXPERIENCE_YEARS_NOT_MATCHED);
        }

        // 3. 주니어인데 4년 이상
        if (developerLevel == DeveloperLevel.JUNGNIOR && experienceYears > 4) {
            throw new DMakerException(LEVEL_EXPERIENCE_YEARS_NOT_MATCHED);
        }

- validatioan 메소드 리팩토링 함 
- 왜 Developer 객체에 setter만 했는데 update가 되는거지??

```

#### ch03_13 삭제 DELETE 
```
- entity패키지에 RetiredDeveloper.java 생성 후 불필요한거 제거
- 삭제가 아닌, 따로 상태값을 변경해서 저장하도록 @Entity 정의 
- (개인적인 생각) @Transcationl 붙어있을때 save() 또는 setter 동작시 해당 데이터가 데이터베이스에서 갱신되는거 같음! 
- 트랜잭션의 Atomic 특성
```
