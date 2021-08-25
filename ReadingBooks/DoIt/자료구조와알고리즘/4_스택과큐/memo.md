## 4. 스택과 큐 
> 데이터를 임시 저장하는 기본 자료구조


### 4-1. 스택 (stack)
```
- 스택은 데이터를 임시 저장할 때 사용하는 자료구조
- 데이터 의 입출력 순서는 LIFO (Last In First Out) 방식
```

###### 고정 길이 스택 구현 예제 - fixed_stack.py
#### 예외처리 
```python
# try문
    try : 스위트
    except 예외 : 스위트 작성 
    else : 스위트 (생략가능)
    finally:스위트 (생략가능)

# try-finally문 
    try : 스위트
    finally : 스위트 

# raise문을 통한 예외처리 
## 파이썬에서는 raise문으로 프로그램의 예외처리를 의도적으로 내보낼 수 있음 ( 참고. fixed_stack.py / p160 )

```

#### 클래스에서 self 란?
```
self는 객체의 인스턴스 그 자체를 말한다. 
즉, 객체 자기 자신을 참조하는 매개변수인 셈이다. 
객체지향 언어는 모두 이걸 메소드에 안보이게 전달하지만, 
파이썬은 클래스의 메소드를 정의할 때 self를 명시한다. 
메소드를 불러올 때 self는 자동으로 전달된다. 
self를 사용함으로 클래스내에 정의한 멤버에 접근할 수 있게된다.
```
><https://velog.io/@magnoliarfsit/RePython-1.-self-%EC%9D%B4%ED%95%B4%ED%95%98%EA%B8%B0>

#### 파이썬 bool 타입 
```
bool 타입은 숫자의 경우 0 만이 거짓이 되고, 0이 아니면 참이 된다. 
bool() 안에 문자형이나 컬렉션 타입들이 있을 경우 비어있으면 거짓이 되고 값이 있으면 참이된다
```
><https://cycorld.gitbooks.io/python/content/datatype.html>

#### Enum에 대해 ( 작성예정 )
```
https://greendreamtrre.tistory.com/358
```

#### print(*s, sep = '    ', end = '') 출력방법
```
https://www.daleseo.com/python-lists-print/
```


### 4-2. 큐(Queue)
```
- 스택과 같이 데이터를 임시 저장할 때 사용하는 자료구조
- 데이터 의 입출력 순서는 FIFO (First In First Out) 방식
```
#### 링 버퍼(ring buffer)로 큐 구현 (p170)
```
- 배열 안의 원소를 옮기지 않는 큐 
- 배열 맨 끝의 원소 뒤에 맨앞의 원소가 연결되는 자료구조
- 이때 
  프런트(front) : 맨 앞 원소의 인덱스
  리어(rear) : 맨 끝 원소 바로 뒤의 인덱스(다음 인큐되는 데이터가 저장되는 위치 가르킴)
- 원소를 옮길 필요없이 front와 rear의 값을 업데이트하는 것만으로 인큐와 디큐를 수행할 수 있음 
- 시간 복잡도 O(1) 
```