> p31 ~ 50 읽기


# Item6. 불필요한 객체 생성 금지  

## Boxing type 대신 Primitive Type을 권장함  

```java
public static long sum() {
    Long sum = 0L;    // 💣
    for(long i = 0; i <= Integer.MAX_VALUE; i++) {
        sum += i;
    }
    return sum;
}

public static long sum() {
    long sum = 0L;    // ✨ 약 10배정도 빠른 실험 결과 나타냄(책 실험 기준)
    for(long i = 0; i <= Integer.MAX_VALUE; i++) {
        sum += i;
    }
    return sum;
}

```

    불필요한 Long 인스턴스가 계속 생성됨으로써 loss가 발생 가능
    Boxing type을 남용하지 않도록 주의하는 것 뿐만 아니라, 의도치 않은 Auto Boxing 을 조심하라.

## Util Class 에서 또한 primitive type을 권장한다. 
- 참 거짓을 reponse 하는데 Boxing type을 사용하는 것은 낭비일 수 있다.
- validation을 확인시 null이 나올 확률은 거의 희박하다고 생각가능 

```java 
public class PhonePatternUtil {
    private final String pattern = "블라";
    //..
    public boolean isValid(String phone) {
        //..
    }
}
```

## 그렇다고 항상 primitive type이 옳은 것은 아니다. 
- 하지만 이 말이 항상 primitive type을 사용해야 한다는 것은 아니다. 
- 대표적인 Null case 에 대해 알아보자 

**물건의 가격이라고 생각해보자**
```java
// price가 0인 것과 null 인 것의 의미는 다르다.
int price;       // default : 0 , primitive type은 null 표시 못함
Integer price;   // default : null 
```

   0의 의미는 증점품(서비스, 공짜) 등의 사유로 0원일 수도 있고,      
   Null 의 경우 가격이 아직 정해지지 않았다 생각될 수 있다. 

## 주의해야 할 내장 Method 


> s.matchs() -> String 클래스의 matches를 호출하는데 결국 Pattern 의 matcher를 호출

**의도치 않게 인스턴스를 계속 생성하게 됨💣**

> 메서드 내부 명세를 잘 보길!!

## 정리 

> Item : 

장점 
  내용없음

단점 
  내용없음 

```


```

> 내용없음체

---


Method Area 
    - Stack에 primitive type 들이 쌓임
    - 객체의 경우 Heap에 저장되고 Method Area Stack에는 reference를 가짐 



deprecate 
> 호환성을 위해 제공은 하고 있지만 더이상 쓰지 말라. 언젠가 없어질 테니 .. 라는 읨 

자바는 객체에 대한 소멸자를 제공함
- 태초부터 finalizer가 있었는데 이제 안쓴다 
- GC발생시 해당 메서드를 실행하게 됨//GC가 언제 일어날지 아무도 모름 
  Stack에서 run()을 가지고 있다가, run에 대한 stack area에서 
  heap 영역에 있는 Item8객체에 대한 reference를 가지고 있다가 reference끊어짐 
- GC 가 실행되면 finalize()가 실행되야 하는데 안됨 
  - finalize() method 가 GC 발생시 제어하려는 의도였지만


에러 발생시 처리가 안될 수 잇으니 또 한겹 씌워 오버라이딩 처럼 하느데 
- 이러면 실제 에러가 어디서 발생했는데 추적하기가 어렵다.💩

자바 7부터 Try-with-resources 등장 
- finally의 역하릉ㄹ 하는 close() 메서드
- catch block과는 다른 얘기니 혼동 조심

Cleaner with Try-with-resources 
- 필수는 아니지만 구현해두면 좋다
- 영상처리, 채팅같은 서비스에서 사용하지 않을까 예상 