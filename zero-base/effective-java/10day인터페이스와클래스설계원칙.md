# Item20. Abstract class 보다는 inerface를 우선하라
Interface를 정말 잘 이해하고 있다고 생각하는가.
Interface를 진심으로 공감하고 있는가 
현업에서의 상황 가정 

**클래스의 확장(extends)은 하나만 가능** (추상 클래스 또한)
<-> 인터페이스의 구현(implements)은 여러개 가능

상속한다 ?
    클래스가 인터페이스들을 구현한다.
    클래스 인터페이스가 다른 인터페이스로 확장한다.(?)
    클래스가 다른 클래스를 확장한다.

```java
public class Sub extends Super implements Serializable, Cloneable {...}

// 인터페이스는 클래스와 달리 다중 상속 가능, 인터페이스 자체도 상속됨 
public interface Sub extends Serializable, Cloneable {...}
```

참고 - https://simuing.tistory.com/entry/JAVA-Test114-%EC%9D%B8%ED%84%B0%ED%8E%98%EC%9D%B4%EC%8A%A4Interface-%EC%A0%95%EC%9D%98?category=647994

## Example. 배달 앱을 상상해 보자. 
```java
public class Restaurant extends Delibary {}
```

    포장 주문의 필요성이 생김, 하지만 한 class 밖에 상속이 불가능한걸?
    하지만 interface로 만들면 다중상속이 가능하지 ! 

```java
public class Restaurant implements Delibariable, Packable {}
```

    하지만 나는 실체가 있는 method 가 필요했다고!
    그래서 Java 8에서 default method가 등장 !!

```java
public interface Packable {
    default void packOrder() {
        System.out.println("포장 주문이 들어왔습니다.");
    }
}
```

#### Default method의 Diamond 문제 주의 (영상. 8분 54초)

>Duplicate **default** methods named order with the **parameters()** and () are 
>inhrited from the types Delibariable Interface and Packable Interface 

인터페이스 두개에 void order()가 있고 구현하는 클래스에서 order를 override한다면 
두개 중 어느 인터페이스의 order인지 구분 지을 수 없어서 컴파일 에러 발생 
-> 만약 구현하는 클래스에 method가 구현되어 있지 않다면 문제없음

## Skeletal implementation (추상 골격 구현)
1. interface로 뼈대를 만들고 
2. Abstract class 로 필요한 구현을 모두 마친 후 
3. Subclass 로 마무리 하는 것 

주의사항 
    Object method (equals, toString ...) 는 default 메서드로 제공하면 안됨! 

## 정리 

> Item : 상속을 고려해 설계하고 문서화하라. 그러지 않았다며 상속을 금지하라. 

    머릿속에서 항상 Interface를 먼저 떠올리자. 
    뼈대를 만들 때에는 default method가 대안이 될 수 있다.    

---

# Item21. 인터페이스는 구현하는 쪽을 생각해 설계하라

## 역지사지로 생각하자. 
- Test code를 반드시 짜자 (여러가지 발생 할 수 있는 case들에 대해서)

- Interface를 implements 한 class를 만들어서도 테스트를 해 보자. 

- 생각해보자, 과연 당신의 Next Generation(다음에 코드 볼 사람)은 당신이 의도한 대로 사용하고, 
확장하여 사용하겠는가 ?  // 오해를 발생시키지 않는지, 특정 도메인에 대한 지식이 없어도 이해 할 수 있도록 설계하는게 좋다

## Constant static final 은 anti pattern 
```java
/**
    클래스 내부에서 사용하는 상수는 내부 구현에 해당된다. 
    오히려 사용자에게 혼란을 줄 수 있다. 
    차라리 클래스에 static final 로 추가하는 것이 더 낫다. 
*/
public class OrderService {
    public static final double SECOND_TO_MIN = 60.0;
}

/**
    만약 여러 곳에서 사용해야 할 값이라면 util class 
*/
public class TimeConvertUtil {
    public static final double SECOND_TO_MID = 60.0;
    public static double secondToMin(double second) {
        return second / SECOND_TO_MIN;
    }
}
```

## Static import 
```java 
// #1. 일반적인
import ex.ch4.TimeConvertUtil;

// .. 
TImeConvertUtil.SECOND_TO_MIN

// #2. 정적 import 
import static ex.ch4.TimeConvertUtil.SECOND_TO_MIN;

// ..
SECOND_TO_MIN
```
    두 가지 방법에서 개인적으로 전자의 방법 추천함 👍 
    길어지긴 해도 비슷한 이름의 상수값이 (enum 도 똑같다) import 될 경우 혼란을 일으킬 수 있고, 
    심지어 같은 값이 있는 경우도 아주 가끔이지만 있다. 

    코드를 줄이기 위해 후자를 사용하려는 의도가 많지만, 언급한 것처럼 
    **혼란을 일으킬 수 있으니** 조심하자.


## 정리 

> Item : 상속을 고려해 설계하고 문서화하라. 그러지 않았다면 상속을 금지하라

    인터페이스를 타입을 정리하는 용도로만 쓰고, 상수 공개용으로 사용하지 마라. 
    필요하다면 Class에 담아라. 

    Properties에 값을 선언해 두고 injection 하는 것도 방법일 수 있다.

---

# Item23. 태그 달린 클래스보다는 클래스 계층구조를 활요하라(p142~145) 

## Shape로 태그를 달아 생성자에 따라 변화하는 하이브리드  

```java
/**
    코드 양의 이유로 변수의 캡슐화를 하지 않은 클래스 
*/
public class Figure {
    enum Shape {RECTANGLE, CIRCLE}; // enum 타입
    final Shape shape; // 태그 필드 -- 타입으로 클래스 구분은 좋지 않다🤔, 인스턴스의 타입을 알려면 shape을 꺼내봐야 하니 좋지 않다. 
    double length;
    double width;
    double radius;

    // 원인 경우와 아닌 경우 불필요한 필드가 생성됨
    // 여러 implementation이 섞여 있음
    Figure(double radius) {
        shape = Shape.CIRCLE;
        this.radius = radius;
    }

    Figure(double length, double width) { // 사각형
        shape = Shape.RECTANGLE;
        this.length = length;
    }

    double area() {
        switch (shape) { // 분기 처리를 위한 switch 
            case RECTANGLE : return length + width;
            case CIRCLE : return Math.PI * (radius * radius);
            default : throw new AssertionError(shape);
        }
    }
}

```

## Type로 태그를 달아 생성자에 따라 변화하는 배달앱 

클래스 계층 구조를 만드리 위한 예제 코드 (tag)

```java 
public class User {
    enum Type {RESTAURANT, CUSTOMER, DELIVERY_PERSON}
    final Type type;
    
    String location; // 음식점과 고객만 필요한 필드 (음식점과 고객 주소지)
    // 배달원의 위치
    double latitude; 
    double longitude;
    boolean order(String info) {
        /**
            배달원일 경우 가까운 위치로 주문 설정 
            레스토랑일 경우 주문 자동 수락 
            고객일 경우 카드로 결제 후 주문 수락 
        */
    }
}
```

## Class 계층 구조로 변환

Tag 버전에 비해 훨씬 더 명료해졌다. 
굳이 위에 처럼 Type으로 구분하지 않고 각 class에 필요한 필드 선언해서 사용하면 명확해짐

```java 
abstract class User { // 인터페이스로 정의해도 상관 x
    abstract boolean order(String info);
}

class Customer extends User {
    @Override 
    boolean order(String info) {
        // 카드로 결제 후 주문 수락
        return false;
    }
}

class DeliveryPerson extends User {
    @Override 
    boolean order(String info) {
        // 배달 수락 버튼 누를 시 승락
        return false;
    }
}

```


## 정리 

> Item : 상속을 고려해 설계하고 문서화하라. 그저리 않다면 상속을 금지하라

    태그 달린 클래스를 써야 하는 상황은 거의 없다.

    혹시나 이미 오래된 레거시 시스템 등의 이류를 통해 태그 달린 클래스가 먼저 
    떠오른다면, 계층 구조로 대처하는 방법을 생각해보자 

    이미 태그가 달려 있다면, 계층 구조로 리팩토링 하는 것을 생각해 보자.

---


# Item24. 맴버 클래스는 되도록 static으로 만들라(p146~149) 

## Nested(중첩) class - 맴버 클래스 

Nested(중첩) class : 클래스안에 클래스가 있는 클래스 

Nested 되어 있는 member class가 독립적으로 존재할 수 없으며
바깥 instance 없이는 생성할 수 없어야 함.

```java
@Data
public class User {
    private String name;
    private Address address;

    @Data
    public class Address {
        String zipcode;
    }

    public String getUserName() {
        // 의미가 있는 메서드는 아님
        // 접근 범위 설명 위해 추가함
        return name;
    }
}

///
User user = new User();
user.new Address();        // ?
```

## Nested class - static member class 

Nested 되어 있는 member class(맴버 클래스)가 독립적으로 존재할 수 있음. 

- 기본적으로 맴버 클래스는 상쉬 클래스를 가져가야 함 -> 불필요한 메모리를 좀 더 차지함
- 그래서 일반 맴버 클래스로 얻을 수 있는 이득이 분명하지 않으면 **static 맴버 클래스**를 권장함

```java
public class Customer {
    private int age;
    private Address address;

    public String printBarCode() {
        return address.fullAddress + address.zipcode;
    }

    private static class Address { // static 맴버 클래스 
        private String fullAddress;
        private String zipcode; 
    }
}
```

## Nested class - Anonymous class (익명 클래스)

Nested 되어 있는 member class(맴버 클래스)가 독립적으로 존재할 수 있음

```java 
// ex1.
public interface MyName {
    public int getAge();
}

// ... 실행 code 
MyName myName = new MyName() {
    private int age;
    @Override
    public int getAge() {
        return age;
    }
}

// .. ex2
public abstract class MyName {
    public int getAge() {
        return 0;
    }
}
// OR 
public class MyName {
    public int getAge() {
        return 0;
    }
}

// ... 실행코드 
MyName myName = new MyName() {
    private int age;
    @Override
    public int getAge() {
        return age;
    }
}

```

## Nested class - Local class 

Local variable 을 선언할 수 있는 곳에 선언하여 사용 (사용할 일은 거의 없다함.)
ex) method body 

```java 
public void getName() {
    class Name {
        public int age;
    }
}

```


## 정리 

> Item : 맴버 클래스는 되도록 static으로 만들다

Nested class 
    Method 밖에서 사용할 것이다 - member class 
        그중 member class가 바깥 instance를 참고한다 -> non-static 
        그외 -> static 
    딱 한 곳에서 사용 && 사전 struct가 있다 -> Annonymous(익명) class    
    그 외 -> local class

---


# Item25. 톱레벨 클래스는 한 팔이에 하나만 담으라 (p150~152) 

## Top level class (interface) 

A top level class is a class that is not a nested class - 공식 문서 

Top level class (interface)는 한 파일에 하나만 담아라.

public class 가 파일에 하나만 가능하다고 해서 public 이 아닌 top level class 를 여러개 한 파일에 넣지 말자. 

써야 한다면? Nested class(중첩 클래스)로 사용하라. 


## 정리 

> Item : 톱 레벨 클래스는 한 파일에 하나만 담으라 

    컴파일러가 한 클래스에 대한 여러 정의를 만드는 것을 막을 수 있다. 
    즉 컴파일러가 어느 순서로 컴파일을 하던 일관성이 유지된다.


---