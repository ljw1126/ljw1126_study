# Item15. 클래스와 멤버의 접근 권한을 최소화하라. (p96~101) 

## Public class 의 instance field  
1. public 으로 열 경우 Thread safe 하지 못하다. (Lock 류의 작업을 걸 수 없음)
2. 꼭 필요한 상수라면 예외적으로 public static final로 공개할 수 있다. 
    ex) ZERO_BASE_PAGE 
3. [주의사항] public static final Thing[] VALUES = {...} 는 수정 가능 (객체는 주소값/참조값을 가지니..)    


```java
public class Capsule {
    private String name;
    private int cost; 

    public float getDoloCost() {
        return 1050/cost;
    }
    public int getWonCost() {
        return cost;     
    }
}
```

## 배열의 두 가지 해결책

### 배열을 private으로 만들고, 불변 리스트를 추가 
1. 읽기 전용으로 response 
2. 원본이 바뀔 경우 같이 변경됨 

```java 
private static final PAGE[] PAGE_INFO = {...};
public static final List<PAGE> VALUES = Collections.unmodifiableList(Arrays.asList(PAGE_INFO));
```

### 배열을 private로 두고, 복사본을 반환하는 public method 
1. 해당 시점으로 clone됨 
2. 원본이 바뀔 경우 같이 변경되지 않음 

```java
public static final PAGE[] values() {
    return PAGE_INFO.clone();
}
```

## 캡슐화가 유용한 시나리오 케이스 
```java 
/*
    최초에는 name과 price를 response 할 수 있었음 
    허나 내부 정책이 바뀌어 내부에선 price 대신 cost라는 용어를 사용하고자 함 
    밖에는 그대로 price로 노출이 되어야 함.
    내부에 어떤 데이터로 관리를 하고 있는지 알 수 없게 한다.

    현실적으로 deprecate하고 점차 없애는 방법도 있음
*/
public class ItemInfo {
    public String name; 
    public String price;
}
```

조금 더 strict한 규정을 적용하고 싶다면 name의 setter를 없앨 수 있다. 
- final로 표현하고 set 없애는 것도 동일
(코드 통해 name은 생성자 통해 생성가능하고 변경 불가하다는 것을 표현 )
```java 
public class ItemInfo {
    public String name; 
    public String price;

    public ItemInfo(String name, int price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getPrice() {
        return price;
    }
}
```


## 정리 

> Item : 클래스와 멤버의 접근 권한을 최소화하라.
> Item : public class 에서는 get method 를 통해 필드에 접근하라 

생각해볼만한 점 
    캡슐화는 꼭 한번 다시 점검해 보자.
    접근제어자는 습관적으로 항상 최소로 사용하자.

---

# Item17. 변경 가능성을 최소화하라. 

## Immutable class 규칙 
1. 상태 변경 method (ex. setter) 제공하지 않는다. 
2. Class 확장하지 않도록 한다. (ex. final class)
3. 모든 field 를 final로 선언한다. 
4. 모든 field 를 private로 선언한다. 
5. 자신을 제외하고는 아무도 가변 컴포넌트에 접근할 수 없도록 한다.

```java 
// ex. 자신을 제외하고는 아무도 가변 컴포넌트에 접근할 수 없도록 한다.
@Getter 
class AddressInfo {
    private String address;
}

@AllArgsConstructor
final class User {
    private final String phone;
    private final List<AddressInfo> addressInfoList; 

    public List<String> getAddressInfo() {
        // 내부에 가변 컴포넌트인 주소 정보를 숨겼다. / 해당 getter로 해당 정보만 제공하겠다
        return addressInfo.stream()
            .map(AddressInfo::getAddress).collect(Collectors.toList());
    }
}

```

## BigInteger (Immutable class example)

```java 
BigInteger bigInteger = new BigInteger("10000");
System.out.println(bigInteger.add(new BigInteger("100"))); // 10100, 2번 조건에 따라 새로운 인스턴스 반환😀
System.out.println(bigInteger); // 10000
```

### Immutable class의 조건 
1. Thread safe 
2. failure atomicity - 예외가 발생 후에도 유효한 상태.
3. 값이 다르면 무조건 독립적인 객체로 생성되어야 함. 

### 중간 단계(객체가 완성 중인 상태)를 극복하기 위한 방법 
Static factory method를 통해 new instance를 생성해 response 
ex) StringBuilder 

## Immutable Vo
JPA 같은 ORM 사용시 Entity 클래스로만 꼭 받아야되는 건 아님 
Immutable한 VO에 담을 수 도 있음

```java 
@Entity 
@Setter
@Getter
public class Person {
    @Id 
    private Long id;
    private String name; 
    private float height;
}

// Setter가 없음
@Getter
public class PersonVo { // Immutable 
    private final String name;
    private final float height; 
    private PersonVo(String name, float height) {
        this.name = name;
        this.height = height;
    }

    // 값이 변하지 않는 객체라면 다음과 같이 불변성을 강제하는 것 또한 방법이다.
    public static final PersonVo from(Person p) {
        return new PersonVo(p.getName(), p.getHeight());
    }
}
```

## 정리 

> Item : 변경 가능성을 최소화 하라.

주의사항 
    습관적으로 setter를 만들지 말자.  (setter 가 불필요할 수 있다.)
    Class는 꼭 필요한 경우가 아니면 불변 이어야 한다. 
    - 특히 단순한 Value Object는 더 그러하다. (immutable 하는 게 좋다함)
    모든 클래스가 불변일 수 없지만, 변경할 수 있는 부분을 최대한 줄여보자. 

---

# Item18. 상속보다는 컴포지션을 사용하라(p114~121) 

## 상속의 주의사항 
상속은 캡슐화를 깨드린다. 

다음은 HashSet의 내부의 일부를 복원해 본 코드이다. 
주목해야 할 점은 addAll은 add를 호출한다. 

```java
// 교재 예제 동일
public class HashSet<E> {
    public boolean add(E e) {... 중략}
    public boolean addAll(Collection<? extends E> c) { // 내부 구조 모를 경우, 생각대로 동작 안 할 수 있음
        c.forEach(d -> add(d));
        return true;
    }
}

public class InstrumentedHashSet<E> extends HashSet<E> {
    private int cnt = 0;

    @Override
    public boolean add(E e) {
        cnt++;
        return super.add(e);
    }

    public boolean addAll(Collection<? extends E>) {
        cnt += c.size();
        return super.addAll(c); // 결국 add 호출 해서 한번 더 카운트 됨..
    }
}
```

## Composite patter (composite : 합성, 영상 25분쯤)
ex. 화상 회의 생각하고 작성했다함(구글 밋 같은)
```java 
// Base component 
interface Contact {
    void join();
}

// Leaf 들 
class Person implements Contact {
    private String name;
    public void join(){
        //입장요청
    }
}

class ShareNote implements Contact {
    private String name;
    public void join() {
        // 조건 확인 후 바로 화면 공유
    }
}

// Composite 
class ConferenceRoom implements Contact {
    private List<Contact> contacts;
    public void join() {
        // contacts를 join 시킨다. 
    }
}

```

## 정리 

> Item : 상속보다는 컴포지션을 사용하라.

    상속은 캡슐화를 해칠 수 있기 때문에 pure한 is-a관계일 때만 써야 한다. 
    james gosling 또한 여러 지점에서 상속에 대해 wanr 하는 모습들이 꽤 있엇다. 
    Wrapper class(Drawer)가 class를 확장하여 사용하는 것보다 더 견고하다.

---

# Item19. 상속을 고려해 설계하고 문서화하라. 그러지 않았다면 상속을 금지하다.(p122~129) 

> 어지간하면 하지 마라.

## 상속을 금지하는 방법 
Class를 final로 선언하는 법 

```java
final public class ProhibitInheritance {
}
```

모든 생성자를 private or package-private로 선언하고, public static factory 로 만드는 법.

```java
@Getter
public class ProhibitInheritance {
    private int sum;
    private ProhibitInheritance() {}
    private ProhibitInheritance(int sum) { this.sum = sum; }

    public static ProhibitInheritance getInstance() {
        return new ProhibitInheritance();
    }
}

```

## 올바르게 상속을 고려하려면 
@implSpec을 통해 상속할 때 필요한 내용을 서술한다. 

```java 
/**
* @implSpec 해당 method 는 blah~blash~
*/
public void draw(String color) {
    for(Shape shape : shapes) {
        shape.draw(color);
    }
}
```

Clone과 readObject 모두 직/간접적으로 재정의 가능 method를 호출 해서는 안된다. 

**검증방법** : 하위 Object를 만들어 테스트 해 보면 좋다.


> 상속보다 인터페이스를 권장함

## 올바르게 상속을 고려하지 못한 경우 (잘못된 예제)
```java
/**
* Construcotr 는 직/간접적으로 Override 가능한 method를 call하면 안된다.
*/

public class Super {
    public Super() {
        overrideMe();
    }

    public void overrideMe() {};
}

public class Sub extends Super {
    private final Instant instant;
    public Sub() {
        instant = Instant.now();
    }

    @Override
    public void overrideMe() {
        System.out.println(instant);  
    }
}

// 값은 null -> 그리고 시간 값 출력 
```


## 정리 

> Item : 상속을 고려해 설계하고 문서화하라. 그러지 않았다면 상속을 금지하라. 


```
- 왠만하면 Interface를 통한 구현을 추천한다. 

- 경험상 주석을 열심히 달아 놓아도, 대다수의 개발자들은 문제가 터졌을 때나 그것을 확인할 
확률이 높다.(누구나 바쁘다면, 그럴 수 잇다. 그 사람의 잘못이 아니다. 비난하지 말자)

- Final class보다는 coding rule을 정할 때 **왠만하면 상속을 피한다. 라고 협의** 후
해당 규칙을 **코드 리뷰에 반영**하는 편이 좀 더 현실적일 수 있다. 
개발자들은 해야 할 일이 늘어나는 것을 좋아하지 않는다.

- 변수 몇개가 겹친다고 해서 꼭 상속을 통한 확장을 해야 한다는 것을 의미하는 것은 아니다.

- Next Generation(다음사람)이 여러분이 생각한 대로 행동할 수 있다는 보장이 없다.
```

---