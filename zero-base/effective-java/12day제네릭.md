# Item26. 로(law) 타입은 사용하지 말라.

## 용어 정리 
// 이미지 

## 만약 type parameter 가 없다면 ? (비권장 코드)
- 타입이 없다면 Object로 간주함 

```java
List test = new ArrayList<>();
test.add("no1");
test.add(1);

// getter으로 가져올때 index에 뭘 넣었는지 모르니 타입캐스팅 에러 발생가능 
```

## unbounded wildcard type 
- 비한전정 와일드 카드. 
- 메서드 경우 권장
```java 
private int add(List<?> s1) { // generic type 
    s1.add(1); // error!
    return 1;
}
```

## 명확하게 타입 추론이 가능하게 하자.
그렇지 않다면 런타임 시 예외가 발생할 수 있다. 

List<Object> 는 명확하게 내가 Object라는 타입을 제시한 것임. 오해 X 
하단과 같이 merge해 버린다면, 다른 타입이 들어왔을 때 런타임 시 언젠가 문제가 발생가능 

```java
# 않좋은 예시 
static List listMerge(List a, List b) { // 타입이 명확하지 않으니 에러 발생가능
    list c = new ArrayList();
    c.addAll(a);
    c.addAll(b);
    return c;
}
```

## 예외적인 사용 예

```java 
// #1. Class 리터럴에는 raw type을 써야 함 
List.class

// #2. 런타임시 제네릭 타입 정보가 지워지기 때문에 다음과 같이 체크 => Set<E> 가 Set.class 으로 인식된다는 듯
if ( a instanceof Set ) {
    Set<?> s = (Set<?>) a; // 와일드 카드 인건 null이 아닌 모든게 들어올 수 있다
    for (Object o : s) {
        if (o instanceof String) { // String 명시

        }
    }
}
```

## Generic Class / Generic Interface 

```java 
List<String> test = new ArrayList<>();

// List : Generic Interface 
public interface List<E> extends Collection<E> {}

// Arrayist : Generic Class 
public class ArrayList<E> extends AbstractList<E>
    implements List<E>, RandomAccess, Cloneable, java.io.Serializable 
```


## 정리 

> Item : 로 타입은 사용하지 말라 

    예외적인 케이스를 제외하면 명확히 타입을 명시하도록 하자 
    처음에 설명한 용어는 꼭 기억 하는 것이 좋다. 

---

# Item27. 비검사 경고를 제거하다 (p161 ~ 163) 

## Compiler가 보내는 warning을 제거하다  

Warning이 print 된다는 것은 말 그대로 경고를 보내는 것이다.

Intellij 에서는 다음과 같이 경고를 보내기도 한다. 
```java 
Set<String> test = new HashSet();
```

만약 안전하다고 확실할 수 있으면 **@SuppressWarnings("unchecker")** 를 통해 경고를 숨기자. 

**내가 경고를 무시한다면**
    서버의 치명적인 에러를 감지하지 못할 수 있다. 
    
    잘못 작성한 코드를 지나칠 수 있다. (기술부채)
    
    내가 만든 많은 warning 을 통해, 정작 봐야할 중요한 warning을 볼 수 없다. 
    (특히 의존성을 추가하여 하는 작업일 경우 많음)

    동료가 나의 warning을 통해 괴로워 할 수 있다.


## 배열보다는 제네릭 

```java 
/**
    Runtime Error 발생 
    - boxing된 long 타입이 object 확장한건데 안됨 
    - 공변이라는게 sub class가 super의 하위 타입이라면 자연스럽게 아래와 같이 되는 거
      Object 배열에 Long 배열을 넣을 수 있음 => 생성자에서는 문제가 없음 

*/
Object[] objects = new Long[1];
objects[0] = "test"; // Long타입 배열에 String을 넣는거라 런타임시 에러발생 


/**
    Complie Error 발생 ( 런타임 전에 확인 가능하게 됨)
    - 컴파일러가 명확하게 체크를 함 
    - 배열은 실체가 있음. 
      런타임할때도 담기로 한 원소의 타입을 인지하고 있음 
      제니릭은 런타임할때 타입 정보가 사라짐 (List objectList = ..)
*/
List<Object> objectList = new ArrayList<Long>();
```

## 정리 

> Item : 배열 대신 리스트를 사용하라

**추가로 알면 좋은 것**
    코딩 테스트가 불러온 미신. 

    Array(배열)가 List 보다 빠르기 때문에 가능한 Array로 문제를 푸는 것이 좋다. 
    -> **따라서 배열을 쓰는 것이 속도에 가장 좋다** (X, 상황에 따라 다름)

    차라리 LinkedList와 ArrayList의 차이점을 잘 알고 쓰는 것이 더 중요하다. 

    코딩 테스트의 환경에서는 Array 가 미세하게 더 유리할 수 있는 있겠지만, 
    우리가 만드는 Product level에서는 ArrayList만으로도 충분한 경우가 대부분이다.  

---


# Item29. 이왕이면 제네릭 타입으로 만들라 (p170 ~ 175) 

## Type parameter Naming Conventions 

E - Element 
K - Key 
N - Number 
T - Type 
V - Value 
S,U,V etc = 2,3,4 type // 타입이 여러개 들어가는 경우 순서대로 표현

```java
// Type parameter 사용한 예시 
@NorepositoryBean 
public interface JpaRespository<T, ID>
```

## Generic Type Example 
```java 
/**
    덧셈 뺄셈에 대한 식을 쭉 나열해 주는 계산기의 표현화면이 있다고 가정하자. 
    다만 실수만, 혹은 정수만 받고 싶다. 

    다른 예외 사항은 예제이기 때문에 무시.
    좋은 예제는 x 
*/
public class Calculator<E> {
    private StringBuilder expression;
    
    public Calculator() {
        expression = new StringBuilder();
    }

    public void add(E e) {
        expression.append("+" + e.toString());
    }

    public void minus(E e) {
        expression.append("-" + e.toString());
    }

    public String expression() {
        if (expression.charAt(0) == '+') {
            return expression.substring(1);
        } 

        return expression.toString();
    }
    
}

```


## 정리 

> Item : 이왕이면 제넥릭 타입으로 만들라

    Object를 이용한 직접 형변환하는 코드 대신 
    제네릭 타입으로 만들어 형변환하는 코드를 없애라. 

    혹시나 기존 형변환 코드가 있다면, 리팩토링 강력추천

---