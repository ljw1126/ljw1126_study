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
 