## 함수형 프로그래밍의 응용 
### 9.1 Scope Closurer Curry 
```
# Scope(스코프/유효범위) 
  - 변수에 접근할 수 있는 범위 
  - 함수 안에 함수가 있을대 내부 함수에서 외부 함수에 있는 변수에 접근이 가능하다.
    (lexical scope). 그 반대는 불가능하다.
  // 예시 
  ※ 일반적으로 메서드는 호출이 끝나면, 메서드 안에 선언된 변수는 사라짐

# Closure
  - 내부 함수가 존재하는 한 내부 함수가 사용한 외부 함수의 변수들 역시 계속 존재한다. 
    이렇게 lexical scope 를 포함하는 함수를 closure라 한다. 
  - 이때 내부 함수가 사용한 외부 함수의 변수들은 내부 함수 선언 당시로부터 변할 수 없기 때문에 
    final 로 선언되어 있지 않아도 암묵적으로 final로 취급한다.
  //예시 

# Curry 
  - 여러 개의 매개변수를 받는 함수를 중첩된 여러 개의 함수로 쪼개어 매개 변수를 한 번에 받지 않고 
    여러 단계에 걸쳐 나눠 받을 수 있게 하는 기술 
  //예시  

```

### 9.2 Lazy Evaluation_edited 
```
# Lazy Evaluation 
  - Lambda의 계산은 그 결과값이 필요할 때가 되어서야 계산된다. 
  - 이를 이용하여 불필요한 계산을 줄이거나 해당 코드의 실행 순서를 의도적으로 미룰 수 있다. 
    > 실행 순서 미룸으로써 필요할 때 호출하여 사용하거나(최적화 연관), 아예 호출 x 수도 있음

※ Stream에서는 *.collect() 가 발생할때까지 내부 연산을 미룸 ! 
   예제 보면 Befor collect가 먼저 호출되고 pick , After collect가 호출됨

```

### Function Composition (함수 합성)     // reduce라던가 인터페이스 복습이ㅣ 필요 
- 여러 개의 함수를 합쳐 하나의 새로운 함수로 만드는 것
```
java.util.function.Function 

<V> Function<V,R> compose(Function<? super V, ? extends T> before)
<V> Function<T,V> andThen(Function<? super R, ? extends V> after)

※ compose는 해석이 반대라, andThen을 주로 사용한다 함

```