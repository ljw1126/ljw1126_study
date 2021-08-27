## 6 정렬 알고리즘 

### [정렬 알고리즘]

#### 정렬(Sorting)이란?
``` 
 - 이름, 학번, 학점 등의 키(key)를 항목값의 대소 관계에 따라 데이터 집합을 일정한 순서로 바꾸어 늘어놓는 작업을 말함
 - 오름차순(ascending order) 정렬 : 작은 데이터 > 큰 데이터로 나열
   내림차순(descending order) 정렬 : 큰 데이터 > 작은 데이터로 나열 
 - 내부 정렬 : 정렬할 모든 데이터를 하나의 배열에 저장할 수 있는 경우에 사용하는 알고리즘
   외부 정렬 : 정렬할 데이터가 많아서 하나의 배열에 저장할 수 없는 경우에 사용하는 알고리즘 
   
 ※ 정렬 알고리즘의 핵심은 교환/선택/삽입 ! 
```

#### 시간 복잡도 
| 명칭          | 시간 복잡도    | 
| ------------- |:-------------:| 
| 버블 정렬| O(n^2) |
| 선택 정렬| O(n^2) |  
| 삽입(셔틀) 정렬| O(n^2) |  
<br/>




#### 1. 버블 정렬(Bubble sort, p221) 
```
 - 이웃한 두 원소의 대소 관계를 비교하여 필요에 따라 교환을 반복하는 알고리즘
 - 단순 교환 정렬이라고도 함  
 - 버블 정렬은 1칸 이상 떨어져 있는 원소를 교환하는 것이 아니라 서로 이웃한 원소만 교환하여 안정적
 
   (n-1) + (n-2) + ... + 1 = n(n-1)/2

```

| 예제          | 비교          | 
| ------------- |:-------------:| 
| bubble_sort| 비교21번/교환8번|
| bubble_sort2     | 비교18번/교환8번 |  
| bubble_sort3      | 비교12번/교환6번     |    
| bubble_shaker_sort.py | 비교10번/교환6번      |   

<br/>

###### ※ 출력문에 {a[i]:2} 의 뜻은 '우측정렬 후 좌측 2 공백해라'는 뜻 ( {} 포맷팅 )
><https://greendreamtrre.tistory.com/262>

<br/>
###### 파이썬 if 조건문 간단하게 표현하기 
```python
#1.일반적인 if .. else .. 문 

condition = True

if condition:
    x = 1
else:
    x = 0

print(x)

# 간단한 if .. else ..문  ( 참결과 if 조건문 else 거짓결과 )

condition = True

x = 1 if condition else 0

print(x)

---

# 2. 일반적인 if .. elif .. else 문
a = 30

if a > 100:
  print('Too Big')
elif a > 10:
  print('Big')
else:
  print('Small')

# 간단한 if .. elif .. else 문
a = 30

print('Too Big') if a > 100 else print('Big') if a > 10 else print('Small')

```
><https://codetorial.net/tips_and_examples/simple_if.html>

<br/>

#### 2. 단순 선택 정렬 (straight selection sort , p237)
```
 - 가장 작은 원소부터 선택해 알맞은 위치로 옮기는 작업을 반복하며 정렬하는 알고리즘 
 [방법]
  1) 아직 정렬하지 않은 부분에서 값이 가장 작은 원소 a[min]을 선택함 ( i (0 ~ len(array)-1일때 j는 i+1 ~ len(array)-1 만큼 돌며 작은 idx 구함)
  2) a[min]과 맨 앞에 있는 원소를 교환함 
 
 ' 중복값 있을시 중복된 값으로 정렬이 필요없는 데이터의 위치를 교환하므로 
 이 알고리즘은 안정적이지 않음 '
```

<br/>

#### 3. 단순 삽입 정렬 (straight insertion sort , p240)
```
- 주목한 원소보다 더 앞쪽에서 알맞은 위치로 삽입하며 정렬하는 알고리즘 
- 단순 선택 정렬과 비슷해 보이지만 값이 가장 작은 원소를 선택하지 않는다는 점이 다름
- 단순 삽입 정렬은 "셔틀 정렬(shuttle sort)"이라고도 합니다. 
```
##### ※ 단순 삽입 정렬 알고리즘은 파이썬 표준 라이브러리에서 bisect모듈의 insort()함수로 제공함 (예제 binary_insort.py)