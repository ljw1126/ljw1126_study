## 테스트 코드 
- 원하는 클래스명 선택 후 **ctrl + shift + t** 눌러서 테스트 클래스 생성
- junit : java의 unit 테스트를 위한 프레임워크 
- @SpringBootTest 추가시 테스트 할때도 실제 실행환경과 동일하게 bean을 applcation에 등록하고 사용할 수 있게 됨 
- 간단한 테스트는 가능한데, db 데이터가 필요한 경우 격리성이 부족해짐 
- 격리성을 올리기 위한 방법으로 Mockito 사용됨 
  - spring boot starter에 내장되어 있음 
  - @SpringBootTest 지우고 @ExtendWith(MockitoExtension.class) 변경
  - @Autowired로 의존성 주입했던 것을 @InjectMocks로 변경 
  - 필요한 Repository에 대해 @Mock 어노테이션 추가 후 선언 
  - 테스트 코드에서 Mocking(=더미)을 하는데 

#### service 테스트 할 경우 
```java

@ExtendWith(MockitoExtension.class)
class DMakerServiceTest {
    
    //mock 객체를 생성한다.
    @Mock
    private DeveloperRepository developerRepository;

    @Mock
    private RetiredDeveloperRepository retiredDeveloperRepository;

    // @Mock이 붙은 목객체(repository)를 @InjectMocks이 붙은 객체에 주입
    @InjectMocks 
    private DMakerService dMakerService;
    
    @Test
    public void testSomething(){
        //alt + enter , mockito 라이브러리 함수 
        given(developerRepository.findByMemberId(anyString()))
                .willReturn(Optional.of(Developer.builder()
                                .developerLevel(SENIOR)
                                .developerSkillType(FRONT_END)
                                .experienceYears(12)
                                .statusCode(StatusCode.EMPLOYED)
                                .name("name")
                                .age(30)
                                .build()
                        ));
        /*
            anyString()으로 memberId를 줬고 getDeveloperDetail() 실행시
            findByMemberId(memberId) 동작하므로 willReturn 에 의해 결과값이 주어짐
        */
        DeveloperDetailDto developerDetail = dMakerService.getDeveloperDetail("memberId");

        // assertEquals()는 junit api
        assertEquals(SENIOR, developerDetail.getDeveloperLevel());
        assertEquals(FRONT_END, developerDetail.getDeveloperSkillType());
        assertEquals(12, developerDetail.getExperienceYears());
    }
}


```

#### Controller 테스트하는 경우 
```java
package com.developers.dmaker.controller;

import com.developers.dmaker.dto.DeveloperDto;
import com.developers.dmaker.entity.DeveloperLevel;
import com.developers.dmaker.service.DMakerService;
import com.developers.dmaker.type.DeveloperSkillType;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(DMakerController.class)
class DMakerControllerTest {

    @Autowired
    private MockMvc mockMvc; // 호출을 가상으로

    @MockBean
    private DMakerService dMakerService; // 해당 컨트로럴가 해당 서비스를 의존하고 있으므로

    protected MediaType contentType =
            new MediaType(MediaType.APPLICATION_JSON.getType(),
                    MediaType.APPLICATION_JSON.getSubtype(),
                    StandardCharsets.UTF_8);

    @Test
    void getAllDevelopers() throws Exception{
        DeveloperDto juniorDeveloperDto = DeveloperDto.builder()
                        .developerSkillType(DeveloperSkillType.BACK_END)
                        .developerLevel(DeveloperLevel.SENIOR)
                        .memberId("meberId1")
                        .build();

        DeveloperDto seniroDeveloperDto = DeveloperDto.builder()
                .developerSkillType(DeveloperSkillType.FRONT_END)
                .developerLevel(DeveloperLevel.JUNIOR)
                .memberId("meberId2")
                .build();
        // dMakerService.getAllEmployedDevelopers() 호출시 Arrays.asList(juniorDeveloperDto,seniroDeveloperDto) 리턴하겠다.
        given(dMakerService.getAllEmployedDevelopers())
                .willReturn(Arrays.asList(juniorDeveloperDto,seniroDeveloperDto));

        /*
            perform() > 가짜로 /developers 를 json 호출 수행
            andExpect() > 결과값 예상
            andDo(print()) > 좀더 상세한 명세 출력

            표현식 $.[0].developerSkillType  , 배열 0번 인덱스의 developerSkillType 값
        */
        mockMvc.perform(get("/developers").contentType(contentType))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(
                        jsonPath("$.[0].developerSkillType",
                                is(DeveloperSkillType.BACK_END.name())))
                .andExpect(
                        jsonPath("$.[0].developerLevel",
                                is(DeveloperLevel.SENIOR.name())))
                .andExpect(
                        jsonPath("$.[1].developerSkillType",
                                is(DeveloperSkillType.FRONT_END.name())))
                .andExpect(
                        jsonPath("$.[1].developerLevel",
                                is(DeveloperLevel.JUNIOR.name())));

    }

}

```


#### 에러 : Caused by: java.lang.IllegalArgumentException: JPA metamodel must not be empty!
- 참고 
  - https://xlffm3.github.io/spring%20&%20spring%20boot/JPAError/