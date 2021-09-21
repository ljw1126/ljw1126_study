## 자료구조 

### 1. Container 타입

#### a. dict 타입 (dictionary : 사전)
> { key1: value1, key2: value2 }

##### 객체 생성 방법 
```python 
a = {} # 리터럴 표기법으로 생성
a = dict() # dict 클래스를 통한 생성 
```

```python
# dict 타입 객체 선언 
personal_info = {
  'name': 'aldonza',
  'age': 20,
  'nickname': 'dulcinea'
}

print(personal_info['name'])
print(personal_info['age'])
print(personal_info['nickname'])

# 실행결과
aldonza
20
dulcinea
```
**※ 만약 dict안에 찾으려는 값이 없다면 에러가 발생합니다.**


```python
# dict 클래스로 dict 객체 생성하는 방법 
personal_info = dict(name='aldonza', age=20, nickname='dulcinea')

print(personal_info['name'])
print(personal_info['age'])
print(personal_info['nickname'])

# 실행결과
aldonza
20
dulcinea
```

+ dict는 또 iterable객체입니다.
+ 즉, 반복가능한 객체입니다. 따라서 for문을 통해서 반복가능합니다. (for문 반복시 key값 가져옴)

```python

personal_info = {
  'name': 'aldonza',
  'age': 20,
  'nickname': 'dulcinea'
}

for key in personal_info:
     print(key)

# 1.for 출력결과 
name
age
nickname

for key in personal_info:
     print(personal_info[key])

# 2.for 출력결과
aldonza
20
dulcinea
```


#### b. set 타입  // 수학에서 집학과 같은 방식으로 동작하는 타입 
> {1,2,3,4}

##### set 객체 생성 방법 
```python
# {} 중괄호로 생성시 dict 타입이 생성됨 
null_set = {}
type(null_set)
# 출력결과
<class 'dict'>

# 그래서 set() 클래스를 사용해서 생성해야 함 
null_set = set()
type(null_set)
# 출력결과
<class 'set'>
```

+ set도 dict타입처럼 순서를 가지지 않기 때문에, 결과값은 다를 수 있습니다.
+ set객체는 set타입임과 동시에 또한 iterable타입입니다.(for문에 반복가능)

```python
for el in {1, 2, 3, 4}:
    print(el)

# 출력결과 ( set 또한 순서를 보장하지 않음)
1
2
3
4
```

+ Container 클래스에서 상속받은 함수에 대한 내용은 생략함

#### Container 객체 특성 
> - 파이썬 내장 자료형 dict/set/str타입은 동시에 Container 타입이다.
> - 즉, Container 클래스에서 정의된 특정 속성을 dict/set/str타입이 상속받아 구현됨 
> - 특1) container는 iterable객체, 즉 반복가능한 객체입니다.
> - 특2) 순서를 보장하지 않음 (<-> Sequence타입의 경우 순서 보장)
> - 참고 : <https://wikidocs.net/22260>


### 2. Sequence
Container타입들중에 순서를 가진 객체를 Sequence객체라고 합니다. ( 모든 Sequence객체는 Container객체입니다. )
><https://wikidocs.net/22289>

#### List 타입 vs Tuple 타입 

||list타입|tuple타입|
|:---:|:------:|:------:|
|객체생성|array = [] </br> array = list() | tuple = 1,2,3,'4',5.0 </br> tuple = () |
|공통점|iterable 객체(반복문 출력 가능)<br/>순서 가짐<br/>인덱싱으로 값 호출|
|차이점|mutable 객체</br>(데이터 수정/삭제/추가 가능)|imutable 객체</br>(데이터 수정/삭제/추가 불가)|

##### ※ str,int,float 타입들도 immutable 객체이다.

---

### 덱 (deque:double-ended queue, 양방향 대기열 덱, p178)   
```
- 맨 앞과 맨 끝 양쪽에서 데이터를 모두 삽입/삭제 가능한 자료구조
- 2개의 포인터를 사용하여 양쪽에서 삭제/삽입 가능
- 큐와 스택을 합친 형태라고 생각하면 됨 
```