# Chapter3. 모든 객체의 공통 메서드

> 습관적으로 사용할 수 있는 Object의 공통 메서드 

## Equals (p52~66)

객체 동일 비교
ㄴ인스턴스 생성시 hashCode 만들어짐 
ㄴ경우에 따라 hashCode가 다를 수 있음

!Equals가 만족해야 하는 것 
1. reflexivity  
   e.equals(x) => true 
2. symmetry, 대칭성
   x.equals(y) => true **then** y.equals(x) => true 
3. transitivity, 추이성
   x.equals(y) => true, y.equals(z) => true **then** x.equals(z) true
4. consistency, 일관성
   x.equals(y) 를 여러번 호출해도 항상 결과는 같음 
5. not null , null이 아닌 참조 값에 대해 null과 비교시 false 여야 함
   if x is not null, then x.equals(null) => false   

> Equals를 매번 정의하지 않아도 되는 경우도 존재함 (논리적 동치성 불필요한 경우)
```java
//#1
public class ClassScore {
    private List<Score> scoreList;
}

//#2
public class Score {
    private String subjectName;  
    private int score; 
}

// 예시가 모호하네..
```

!Equals의 전형적인 검사 패턴
1. == 를 통해 input 이 자기 자신의 참조인지 
2. Instanceof를 통해 input의 타입의 명확한지 
3. 2를 통해 검사한 객체를 올바른 타입으로 형변환 
4. 핵심 필드들이 모두 일치하는지 확인
5. [not null 규칙] if x is not null, then x.euqlas(null) => false 

```java
@Override 
public boolean equals(Object o) {
    if (this == o) return true; 
    if (o == null || getClass() != o.getClass()) return false; 
    LaptopDto laptopDto = (LaptopDto) o;
    return Objects.equals(field, laptopDto.field) && 
                Objects.equals(field2, laptopDto.field2);
}
```


## 정리 

> Item : equals 의 일반 규약을 지켜 재정의하라.
> 
```
# TPO에 맞춰 equals를 재정의 할 지 잘 결정하여 규약에 맞춰 override 할 지 정하라

# Override 시 주의 사항 
1. 만족해야 하는 조건을 만족시켰는가 
2. Equals 를 재정의할 대 hashcode도 재정의 하였는가. 
3. Equals 의 input이 Object 인가 (Overriding 하였는가)
4. 핵심 필드들이 모두 일치하는지 (전체를 다 할 필요 ㅌ)
5. [not null 규칙] if x is not null, then x.euqlas(null) => false 
```

> 내용없음체

---


# equals를 재정의하려거든 hashcode도 함께 재정의하라. (p67 ~ 72)

## 비교 방법의 차이 
```
1. ==
- value compare 
- 즉 primitive type일 때는 value compare, Reference type 일때는 주소 같은지 비교

2. equals() 
- 메서드의 의도 : 같은 객체인지 
- Default : == 과 동일 
- Override 하여 사용

3. hashcode()*
- 논리적으로 같은 객체라면 같은 hashcode를 반환해야 한다. 
```

## 간단한 방식의 hash 

속도가 많이 느려지면 lazy init, or caching 을 고려하자. 
또한 핵심 필드를 누락하지 말 것! 

```java 
// 가장 Simple하게 적용해야 한다면 
@Override 
public int hashCode() {
    int result = message.hashCode();
    return result;
}

// 속도를 고려해야 한다. (Objects 의 경우 속도가 아쉽다🤔)
@Override 
public int hashCode() {
    return Objects.hash(modelName, company);
}

// Objects.hash() , 내부에서 Arrays.hash 출력 
public static int hashCode(Object a[]) {
    if (a == null) return 0;

    int result = 1;

    for (Object element : a) 
        result = 31 * result + 
        (element == null ? 0 : element.hashCodde());

    return result;   
}

```

## Lombok을 사용하는 방법도 있다. 
전체 코드와 변환 되는 것은 Google 에 Lombok EqualsAndHashCode를 검석해 
**Projectlombok(공식 사이트)**의 링크를 확인할 것 🤝
[https://projectlombok.org/features/Data](https://projectlombok.org/features/Data)

특히나 @Data를 사용하고 있다면, Data는 다음과 같은 것들을 포함한다. 
@Getter @Setter **@RequiredArgsConstructor** @ToString @EqualsAndHashCode

```java 
@EqualsAndHashCode 
public class EqualsAndHashCodeExample {
    private transient int transientVar = 10;
    private String name;
    // ..
    @EqualsAndHashCode.Exclude private int id; 
}
```

## 정리 

> Item : equals를 재정의하려거든 hashcode도 함께 재정의하라

**equals & hashcode 요약**
1. equals는 필요할 때 적재 적소에 활용하자. 
2. equals를 override한다면 hashcode의 override 는 option이 아닌 필수이다. 
3. Lombok을 사용한다면 @Data, @EqualsAndHashcode는 반드시 동작 원리를 이해하도록 한다. 
   
---


# Item13. toString을 항상 Override하라 (p73~76) 

## toString의 default value 
className@16진수 hashcode 

## toString의 일반 규약 
간결하고 사람이 읽기 쉬운 형태의 유익한 정보

## Lombok의 @ToString 
//프로젝트 롬북 사이트에 @ToString 메뉴얼이 안 보임.

```java
@AllArgsConstructor 
@ToString
public class Latop {
    private String name;
    private String company;
}

// 불필요한 변수 제외하고 ToString사용하고 싶은 경우
@AllArgsConstructor 
@ToString(exclude = {"company"})
public class Laptop {
    private String name;
    private String company;
}

// 명시적으로 제외해주는게 유지보수 측면에서 좀 더 좋다고 생각👍 (클래스 위에 선언하는 것도 괜찮)
public class Laptop {
    @ToString.Exclude private String name;
    private String company;
}
```

## 정리 

> Item : toString을 항상 override 하라.

```
# toString() summary
1. 로그를 찍을 일이 있을 것 같으면 귀찮아하지 말고 toString을 overriding 하자. 
2. 전부 다 toString으로 찍지 말고, 필요한 것 위주로 작성하라 
3. Lombok은 toString을 만드리 귀찮은 개발자들이 성실하게 toString을 구현하도록 유인할 수 있다. 
4. Lombok 사용 시 주요 옵션은 공식 홈페이지에서 확인할 것
```

---

# Item13. clone 재정의는 주의해서 사용하라 (p77~87) 

## 배열 copy
```java
int[] a = {1, 2, 3, 4};
int[] b = a; // Shallow copy 얕은 복사 
b = a.clone(); // deep copy 깊은 복사 (완전하진 않음 💣)
```

## Copy시 주의사항 

> 객체를 복사하는 경우 reference 주소를 참조 복사 수행 하다보니, 속성 값은 깊은 복사 못해 그대로 연결되는 경우 발생 

```java 
Laptop[] a = {new Laptop("그램 16인치", "삼성")};
Laptop[] b = a.clone();
b[0].setCompany("LG");     // a[0] 의 company도 LG로 되어 있음 ; chaining 해서 완전히 내부 내용 카피 x 
```

잘 복사한 것 같지만 Object의 reference value를 참조했기 때문에 
**a[0] == b[0] 즉 둘이 같은 객체를 가르키고 있음**

## Why not use clone 
객체의 복사본을 생성해 반환한다. 복사의 정확한 뜻은 크래스에 따라 다를 수 있고, 일반적인 의도는 다음과 ㄱ ㅏㅌ다. 

```
x.clone() != x  // true 
x.clone().getClass() == x.getClass() // true 
x.clone().equals(x)      // 일반적으로 true

A               a.clone()이 A 객체를 response 


B extends A     b.clone() 실행 -> super.clone(), 즉 a.clone 을 실행한 결과를 가져 옴 
                -> b는 b 객체를 반환해야 하는데 super의 결과는 A객체임 

```

## 쉬운 길이 있는데 돌아가지 말자 
객체 생성 2가지 패턴 (굳이 필요할 때 아래 두 방식 중 선택사용)
```java 
// conversion Constructor 
public Yum(Yum yum) {....} ;

// conversion Factory
public static Yun newInstance(Yum yum) {....};
```


## 정리 

> Item : clone 재정의는 주의해서 사용하라

**Clone은** 
    Primitive type의 배열이 아니면 쓰지 말자. 
    Copy Constructor or Copy Factory Method 를 활용하라. 
    Cloneable을 확장하지 마라.

---

# Item14. Comparable 을 구현할지 고려하라. (p87~94)

## compareTo 의 규약 (equals 와 비슷) 

객체와 주어진 객체의 순서를 비교한다. 

이 객체가 주어진 객체보다 작으면 음의 정수, 같으면 0, 크면 양의 정수를 반환. (-1, 0, 1)
비교할 수 없을 땐 **ClassCaseException**

```java
// # 복잡해 보이지만 하나씩 뜯어보면 상식적인 이야기이다. 👍
sgn(x.compareTo(y)) == - sgn(y.compareTo(x))
x.compareTo(y) > 0 && y.compareTo(z) > 0 then. x.compareTo(z) > 0 
x.compareTo(y) == 0 then sgn(x.compareTo(z)) == sgn(x.compareTo(z))
// 권고는 아니지만 Recommend 
x.compareTo(y) == then x.equals(y) == true
```

## Java 7 이후의 compare 
```java 
// 과거 (자바7 미만)
1 < 2;    // 정수 비교 
Double.compare(1.2, 3.4); // double 비교 
Float.compare(1.2f, 3.4f); // 실수 비교

// 자바 7 이후 
Integer one = 1;
one.compareTo(2);
```

## 정렬 예제 

조건 : 
    1순위 : 나이 순 정렬 
    2순위 : 키 순 정렬 
    3순위 : 이름 순 정렬 

```java 
public class Person {
    private int age;
    private String name;
    private double height; 
}

// comopareTo 함수 구현 
public int compareTo(Person p) {
    int result = Integer.compare(age, p.age); 
    // auto boxing (primitive인데 굳이.. , 함정) 
    // 부등호를 통해 대수 비교 더 적절하 수도 (함정)
    // 근데 뜯어보면 static 함수이고 실수 비교시 오류 방지 차원에서 
    if (result == 0) {
        result = Double.compare(height, p.height); // 메소드를 열어보자✨
    }

    if (result == 0) {
        result = name.compareTo(p.name);
    }

    return result;
}

// compareTo의 또다른 구현 (Comparator 생성 method) , java8 이상, 좀 더 간결 👍
private static final Comparator<Person> COMPARATOR = 
    Comparator.comparingInt(Person::getAge)
    .thenComparaingDouble(Person::getHeight)
    .thenComparing(person -> person.getName());

public int compareTo(Person p) {
    return COMPARATOR.compare(this, p);
}


```


## 정리 

> Item : Comparable 을 구현할지 고려하라

```
필요하다면 적절하게 Comparable를 구현하여 compareTo의 이점을 누릴 수 있다. 

하지만 정렬의 기준이 고정적이 아니라면, 다른 방식(ex. Method, service를 통한 조건별 ordering)을 고려해 볼 수 있다. 
```