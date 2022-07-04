## Constructor와 Static Factory Method 의 비교 (8~13)

> parameter 1개인 경우 from(), 여러 개인 경우 of() 로 naming 할 것을 권장함

```java
public class Laptop {
    private String modelName;
    private String company;

    // Constructor :: 생성자
    public Laptop(String modelName, String company) {
        this.modelName = modelName;
        this.company = company;
    }

    // 이름을 가진 Static Factory Method 
    public static Laptop ofModelNameAndCompany(String modelName, String company) {
        Laptop laptop = new Laptop();
        laptop.company = company;
        laptop.modelName = modelName;
        return laptop;
    }
}
```

## Static Factory Method 를 사용하는 이유/장점 ?
- API request, response 처리해줄 때 convert 하는 용도로 사용

> response 든 request든 entity 자체를 쓰는건 지양하는게 좋다. Annotaion을 사용해서 제외도 할 수 있지만 명시적으로 구분하는걸 권장

```java 
public class LaptopForm {
    private String name;
    private String corp;
}


@PostMapping(value = "/add")
public LaptopDto addLapTop(@RequestBody LaptopForm laptopForm) {
    // .. 생략
}

// 1. Form to Entity Class ( API request parameter)
public class Laptop {
    private Long id;
    private String modelName;
    private String laptop;

    // 이름을 가진 Static Factory Method :: Laptop 으로 convert
    public static Laptop from(LaptopForm laptopForm) {
        Laptop laptop = new Laptop();
        laptop.company = laptopForm.getModelName();
        laptop.modelName = laptopForm.getCompany();
        return laptop;
    }
}

// 2. Entity to Dto Class ( API response parameter ) :: 필요하지 않은 entity 요소 제거 가능 효과 ! 
public class LaptopDto {
    private String modelName;
    private String companyName;

    public static LaptopDto from(Laptop laptop) {
        LaptopDto laptopDto = new LaptopDto();
        laptopDto.company = laptop.getCompany();
        laptopDto.modelName = laptop.getModelName();
        return laptopDto;
    }
}
```

## 정리 

> item : Constructor 대신 Static Factory Method 를 고려하라

장점
  1. 이름을 가질 수 있다. 
  2. Simple하고 명확하게 사용가능
  3. 인스턴스를 매번 생성할 필요 x (Singleton 의 경우 한 객체, Flyweight의 경우 multiple 한 객체를 다룸)
    - Flyweight pattern - Collection Object 
    - Singleton pattern - Single Object 
단점
  1. Static Factory method 만 제공하면 Constructor가 없을 수 있어 상복 받은 Class 만들 수 없다 (장점이 될 때도 있음)
  2. 프로그래머에게 인지가 잘 되지 않을 수 있다.

--- 

# Item2. 많은 parameter가 있는 Constructor는 Builder를 고려하다 (p14 ~ 22)

```Java
// Example 
public class NutritionFacts {
    private final int servingSize;
    private final int servings;
    private final int calories;
    private final int fat;
    private final int solium;
    // .. etv.
}
```
- Pattern1. 여러 개의 Constructor를 둔다 
  - 필드(속성) 늘어나면 계속해서 Constructor가 늘어나는데 사용자에게 명시적이지 않음
- Pattern2. Java beans Patten (Setter)
  - 생성자 선언하고 setter 처리하게 될 경우 코드가 길어짐, 읽기도 어려움(=루즈해진다고 표현함)
  - 개인적으로 Entity에 대한 무분별한 Setter를 사용하게 될 경우 유지보수 어려움 발생가능

#### Builder를 직접 만들었을 때(lombok 사용 x)

```Java
public class NutritionFacts {
    private final int servingSize;
    private final int servings;
    private final int calories;

    public static class Builder {
        private final int servingSize;
        private final int servings;
        private int calories = 0;
        
        public Builder(int servingSize, int servings) {
            this.servingSize = servingSize;
            this.servings = servings;
        }

        public Builder calories(int calories) {
            this.calories = calories;
            return this;
        }

        public NutritionFacts build() {
            return new NutritionFacts(this);
        }
    }
}
```

#### Lombok을 통하면 훨씬 더 Simple 해 질 수 있다.
- 참고) lombok에 대한 property 디자인 패턴이 존재한다 함

```Java 
@Data 
@Builder(builderMethodName = "hiddenBuilder")
public class NutritionFacts {
    private final int servingSize;
    private final int servings;
    // 해당 어노테이션 통해 builder 패턴 사용시 default 값 지정 가능
    @Builder.Default private final int calories = 0;
    @Builder.Default private final int fat = 0;
    @Builder.Default private final int solium = 0;
    //.. etc 

    public static NutritionFactsBuilder builder(int servingSize, int servings) {
        return hiddenBuilder().servingSize(servingSize).servings(servings);
    }
}
```

## 정리 

> Item : 많은 parameter가 있는 constructor는 Builder를 고려하라 

장점 
  상속받은 Class의 Builder가 정의한 builder 메서드가 
  상위 메서드의 타입을 return 하는 것이 아닌 자신의 타입을 return 함

단점 
  1. Builder를 항상 만들어야 하기 때문에 생성 비용이 무조건 생김 
  2. 점층적 생성자 패턴(Argument를 여러개 가진 Constructor)보다 장황하여 적은 갯수의 
     Parameter일 경우 오히려 좋지 않을 수 있다. 
  3. 라인이 추가될 수 있다.    

> 강의에서는 파라미터 3,4개까지 일반 생성자 사용한다함, Team Convenstion 하는 것을 권장      

---

# Item3. private construcotr 나 enum Type으로 singleton임을 보증하라(p23-25)

> Singleton Pattern 찾아보기

#### 대표적인 Singleton Pattern ( public static final )

INSTANCE가 초기화되고 나면 고정이 됨

```java 
public class Speaker {
    public static final Speaker INSTANCE = new Speaker(); // 상수처럼 변하지 않는 속성 명시. 대문자.
    private Speaker() {} // private로 의도하지 않은 객체 생성 막을 수 있다. 
}
```

#### 대표적인 Singleton Pattern ( private static final )

getInstance()를 통해 instance를 가져올 수 있다.

```java
public class Speaker {
    private static final Speaker INSTANCE = new Speaker();
    private Speaker() {}

    public static Speaker getInstance() { // 해당 method로 인스턴스 가져옴, method 줄이 추가 되었지만 나름 이점있다함(?)
        return INSTANCE;
    } 
}
```

// 강의 30분 쯤 뭐라고 하는지 모르겠음 😅

#### 대표적인 Singleton Pattern ( static factory )

상황에 따라 synchronized 나, lazy하게 instance를 생성하는 방벙도 있다.

```java
public class Speaker {
    private static final Speaker instance;
    private Speaker() {}

    public static synchronized Speaker getInstance() {
        if(instance == null) {
            instance = new Speaker();
        }

        return INSTANCE;
    } 
}
```

#### Enum Type으로 Singleton Pattern을 사용할 수 있다. 

```java
public enum Speacker {
    INSTANCE;
    private String message;
    
    public Speaker getInstance() {
        return INSTANCE;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return messge;
    }
} 

// 실행코드 예시 
Speaker speaker1 = Speaker.INSTANCE.getInstance();
speaker1.setMessage("안내 방송 중입니다.");

Speaker speaker2 = Speaker.INSTANCE;
System.out.println(speaker1.getMessage()); // 안내 방송 중입니다.
System.out.println(speaker1.getMessage()); // 안내 방송 중입니다.
```
> 직렬화 상황이나 reflection 공격 방어 가능하다는 장점있다. (?)


## 정리 

> Item : Construcotr나 enum Type으로 Singleton임을 보증하라.

```
Singleton pattern에 대해 알아보았고, 항상 잘 숙지해 두자. ✨

각 방법의 차이점에 대해 인지해 두고 이를 상황에 맞게 선택해 사용할 수 잇도록 하자.

Enum 방식은 팀원들과 함께 고려해보기!
```

--- 

# Item4. Instance를 막는 Util Class 

잘못된 예제 
```
public class PatternUtil {

    private static final String PATTERN = "블라블라";

    private PatternUtil() {} // 되어 있는데 왜 잘못된 예제라 하는거지??

    public static boolean isEmailValid(String email) {
        return email.matches(PATTERN);
    }

    public String getPattern() {
        return PATTERN;
    }
}
```

## 정리 

> item : Instance화를 막으려면 private constructor 를 사용하라.

```
누군가(나를 포함한)를 믿지 못할 상황이 있고, Human error를 방지하기 위해 
private constructor를 권장한다. (코드 적는데 많은 노력이 필요한 것도 아니고, 적는다고 해서 해치지 않아요.😉 오히려 꼼꼼하다고 생각할 수 있다.👨‍💻)

일반적으로 Util class처럼 관용적으로 Instance화를 하지 않을 거라고 모두가 이미 알고 있는 경우는 생략 하기도 한다.
```


--- 

# Item5. Resource를 직접 명시하지 말고, Dependency Injection을 사용하라 

## @Configuration 

@Configuration은 @Compoent를 포함하고 있고,
@Component 가 Singleton이기에 자연스럽게 @Configuration 또한 Singleton!

```java
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Component
public @interface Configuration{
    // ...
}
```

## Config class 내부에 직접 정의하여 사용 x 

여러 자원을 활용해야 할 경우 적합하지 x 
ex) live, dev, local 같은 환경별로 property 값이 달라져야 할 때 하드코딩 형태로 선언해두는 건 적합하지 않다. 

## Property Injection 

application.yml에 등록된 정보 
```
test:
    address : '부산시 xx구'
```

```java 
@Configuration 
public class TestConfig {     // spring context 실행시 해당 bean 이 생성되면서 주입됨 
    @Value("${test.address}")
    private String address;         
}
```

## Constructor Injection의 경우 Test, flexibility를 높일 수 있다. 

고정해 놓은 값에 비해 훨씬 유연해진 class (pattern Injection 가능)
Test code 작성시 injection 하기도 편리하다.

```java
public class PhonePatternChecker {
    private final String pattern;

    public PhonePatternChecker(String pattern) {
        this.pattern = pattern;
    }

    public boolean isValid(String phone) {
        //.. some business logic
    }
}

```

## 정리 

> Item5 : Resource를 직접 명시하지 말고 DI를 사용하라

#### 생각해 봐야 할 것 
```
Config Class를 생각없이 사용하고 있지는 않았는지 
Singleton을 이해하고 있는지 
DI(Dependency Injection) 를 잘 이해하고 있는지 
```


---

