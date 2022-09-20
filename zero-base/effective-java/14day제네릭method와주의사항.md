# Item30. 이왕이면 제네릭 메서드로 만들라(p176~180) 

## Type safe한 Generic method (장점)

```java
List<String> stringList = List.of("T1", "T2", "T3");
// ... 내부코드
static <E> List<E> of(E e1, E e2, E e3) {
    return new ImmutableCollections.ListN<>(e1, e2, e3);
}

// ... 1.  동작하지 않는 예제 , Object는 제네릭으로 캐스팅 되지 않음
private static UnaryOperator<Object> IDENTIFY_FN = (t) -> t;

public static <T> UnaryOperator<T> identityFunction() {
    return IDENTIFY_FN;
    // 2. 유형이 다르기 때문에 에러 발생
}

// 3. 타입 캐스팅으로 그나마 동작함
public static <T> UnaryOperator<T> identityFunction() {
    return (UnaryOperator<T>) IDENTIFY_FN;
}

```

## Type 한정 

Method의 parameter 타입을, Interface의 Type의 한정한다.

```java
interface Comparable<T> {
    int compare(T o);
}
```

## 정리 

> Item : 이왕이면 제네릭 메서드로 만들라


```
generic 타입과 같이 형변환 해야하는 method보다 
generic method가 더 안전하고, 심지어 사용하기도 쉽다. 

형변환 해야 하는 메서드는 generic하게 만들자.
```

---


# Item31. 한정적 와일드 카드(Bounded Wildcard)를 사용해 API 유연성을 높여라 (p181~190)  

## Bounded Wildcard 설명을 위한 예제 코드

```java
public class Stack<E> {
    public static final int DEFAULT_SIZE = 20;
    private int size; 
    private E[] elements;
    public Stack() {
        elements = (E[]) new Object[DEFAULT_SIZE];
        size = 0;
    }
    public E push(E item) {
        elements[++size] = item;
        return item;
    }
    public void pushAll(Iterable<E> src) { // 여기서 문제 발생 ! (아래 참고)
        for (E e : src) push(e);
    }
}
```

## Bounded Wildcard Example

> 불공변 : 변할 수 없는
[https://scshim.tistory.com/531]('https://scshim.tistory.com/531')

**컴파일 에러 !**
    불공변(invariant)이기 때문에 자기 타입만 허용
```java 
Stack<Number> numberStack = new Stack<>();     // Number로 형을 선언했는데 , 밑에는 Integer를 넣어버리니.. 
Iterable<Integer> integers = List.of(1, 2);
numberStack.pushAll(integers); // 위에 소스로 정해져 있으면 에러 발생
```

**해결책은**
    제네릭 (E) 를 extends한 와일드 카드를 입력으로 받겠다. 

```java
public void pushAll(Iterable<? extends E> src) { // 한정적 와일드 카드, E를 확장한(E로 감싸고 있는) 타입
    for (E e : src) push(e);
}
```

## Bounded Wildcard example 

> super는 자식이 부모를 바라 볼때 사용

제네릭 상위 클래스인 와일드 카드를 Collection으로 받겠다!
```java
// 선언
public void popAll(Collection<? super E> dst) {
    ...
}

// 사용 
Stack<Number> numberStack = new Stack<>(); 
Collection<Object> integers = List.of(1, 2); // Number의 super인 Object    
numberStack.popAll(integers); // Number(E)의 상위 클래스인 Collection<Object> 을 받을 수 있다. 
```

## Bounded Wildcard example 
여러 개 여도 같은 방식으로 사용하면 된다.

```java
public static <E> Set<E> union(Set<? extends E> s1, Set<? extends E> s2) {
    ...
}

// 자바 8이상 코드 
Set<Integer> integers = Set.of(1,3,5);
Set<Double> doubles = Set.of(2.0, 4.0);
Set<Number> numbers = union(integers, doubles);

// 자바 7 까지의 코드 
Set<Number> numbes = Union.<Number>union(integers, doubles);
```

## Target Typing 

우리는 이미 묵시적으로 target typing 을 사용하고 있다. 
```java 
Stack<Number> numberStack = new Stack<>();
```

왼쪽의 타입으로, 오른쪽 타입을 추론할 수 있다. 이것을 **target typing** 이라 한다.
```java
Set<Integer> integers = Set.of(1,3,5);
Set<Double> doubles = Set.of(2.0, 4.0);
Set<Number> numbers = union(integers, doubles);
```

자바 7 까지는 명시적 타입 인수를 사용해야 한다. 
올바르게 타입을 추론할 수 없기 때문. Target typing 이 8부터 지원 
```java 
Set<Number> numbes = Union.<Number>union(integers, doubles);
```


## 정리 

> Item : 한정적 와일드 카드를 사용해 API 유연성을 높여라 

Bounded Wildcard를 알고는 있지만 자세히 들여다 본 적은 없을 확률이 높다. 
아래 표와 함께 다시 한번 점검하자.

![Alt text](/images/zero-base/effective-java/%EC%A0%9C%EB%84%A4%EB%A6%AD-%EC%9A%A9%EC%96%B4%EC%A0%95%EB%A6%AC.png)

--


# Item32. 제네릭과 가변 인수를 함께 쓸 때는 신중하라. (p191~197)

## Variadic Arguments(가변 인수) 

Method의 argument의 개수를 **클라이언트가 조절할 수 있게 한다.**
또한 반드시 **한개의** 가변 인수만을 사용해야 하며 **맨 마지막 Argument로** 사용해야 한다.

```java
static void mergeAll(List<String>... stringLists) {} // 한 개, 마지막 위치해 있어야함! (다른 타입, 변수를 컴파일어가 구분 x )
```

위의 코드를 풀어 쓰면 다음과 같은 의미이다.
( 아래 코드는 컴파일 불가, 컴파일 가능하려면 List[] stringLists = {one, two, three})

```java 
static void mergeAll(List<String> one, List<String> two, List<String> three) {
    List<String>[] stringLists = {one, two, three};
}
```

## Heap Pollution (힙 오염)

```java 
// alert : Possible heap pollution from paramterized vararg type
static <T> List<T> flattern(List<? extends T>...lists) {
    return null;
}

List[] test = {List.of(1), List.of(2)}; // True
List<Integer>[] test2 = {List.of(1), List.of(2)}; // Error 
```
읽기만 하면 괜찮은데 안전하기 위해선 제네릭 배열에 아무것도 저장하거나 덮어쓰지 말고, 
배열의 참조 밖으로 노출시키지 말아야 한다.

가변인수를 사용시 Heap 오염을 주의해야 함

## Remove Warning 

    @SuppressWarnings (컴파일 경고 숨기기)
        실무에서 현실과 타협해야 되는 순간이 있을때, 
        조금더 좋아질 수 있는 가능성이 있지 않을가 싶을때 

    @SafeVarargs (메서드의 타입 안정성을 보장함)

## 예제 해설 (실패 케이스)

> 실제로 코드 작성해서 debug 해보기 !! 👨‍💻

```java 
String[] attributes = pickTwo("안녕", "hello", "곤니치와");

public static <T> T[] pickTwo(T a, T b, T c) {
    return toArray(a, b);  // 2. Object[] args 반환
}

public static <T> T[] toArray(T... args) {
    return args;  // 1. String[] args = [...]; 반환
}

// 성공 예제 
String[] attributes = toArray("안녕", "나는");
```


## 정리 

> Item : 제네릭과 가변 인수를 함께 쓸 때는 신중하라

```
제네릭 배열에 아무것도 저장하거나 덮어쓰지 말고,
배열의 참조를 밖으로 노출시키지 말아야 한다. 

제네릭과 가변인수를 함께 사용할 때에는 궁합이 잘 맞지 않으니 조심하자 

아니면 이중 리스트 같은 것도 해답이 될 수 있다.
```

---


# Item33. 타입 안정* 이종 컨테이너를 고려하라 (p198~205) 

## 타입 안정 이종 컨테이너  

Key 가 wildcard type 

```java
public class Favorites {
    // Key가 와일드 카드
    private Map<Class<?>, Object> favorites = new HashMap<>();
    public <T> void putFavorite(Class<T> type, T instance) {
        favorites.put(Objects.requireNonNull(type), instance);
    }
    public <T> T getFavorite(Class<T> type) {
        return type.cast(favorites.get(type));
    }
}
```

Get 할 때 요청받은 타입의 Value를 찾아 cast 하여 response 함 
(Map에서 꺼내올 때는 Object)

## 동적 형 변환 -- 이해 못함


```java 
public <T> void putFavorite(Class<T> type, T instance) {
    favorites.put(Objects.requireNonNull(type), type.cast(instance));
}

//..
Favorites favorites = new Favorites();
Game game = favorites.putFavorite(Game.class, new Game());

```
(장점) 타입 안정적이다 , 휴먼 에러 방지 가능
(약점) 클래스 객체를 제네릭이 아닌 타입으로 넘기게 되면 안정성이 깨질 수 있다.

## 슈퍼 타입 토큰 (ParameterizedTypeReference)

TypeReference, ParameterizedTypeReference 등이 있다. 

```java
RestTemplate rt = new RestTemplate();
List<String> test = rt.exchange("http://localhost:8080", HttpMethod.GET, 
            null, new ParameterizedTypeReference<List<String>()>{}).getBody();
```

> 토비의 스프링 - 슈퍼타입 토큰 영상(2시간)이 있으니 동작원리 이해 하는데 도움 될 수 있다


## 정리 

> Item : 타입 안정 이종컨테이너를 고려하라

```
타입 안정 이종컨테이너에 대해 알아 보았다. 

슈퍼 타입 토큰에 대한 것은 다루기에 긴 내용이니 
꼭 여러 글들과 블로그를 한번 확인 하는 것을 추천한다.
```

---