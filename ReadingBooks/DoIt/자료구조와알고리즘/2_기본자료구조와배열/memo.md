## 02-1 자료구조와 배열 

"빠른 알고리즘은 많은 메모리를 요구합니다."

#### 소수 (prime number)
```
- 소수는 자신과 1이외의 정수로 나누어 떨어지지 않는 정수 ( = 2부터 n-1까지 나누어 떨어지지 않음 )
  > 어떤 정수 n은 2부터 n-1까지 어떤 정수로도 나누어 떨어지지 않습니다. ( prime.py )
  > (n이하 홀수 범위에서) 2부터 n-1까지 어떤 소수로도 나눠지지 않으면 소수이다. ( prime2.py )
  > n의 제곱근 이하의 어떤 소수로도 나눠지지 않으면 소수이다. ( prime3.py )
    >> 100 의 경우 10^2 기준으로 대칭을 이루므로 10 이하 소수로 나눠서 떨어지는 경우 소수..
- 만약 나누어 떨어지는 정수가 하나 이상 존재하면 그 수는 합성수(composite number)입니다. 
```
###### 예제 prime1.py / prime2.py / prime3.py
###### 자바 코드 참고 : https://2ssue.github.io/algorithm/algorithm_prime/

<br/>

#### 얕은 복사(shallow copy)와 깊은 복사(deep copy)  // p106 
+ 얕은 복사 - 참조값(주소값?)만 복사하는 방식 , 수정시 같이 변경 o
+ 깊은 복사 - 참조값뿐만 아니라 참조하는 객체 자체를 복사, 수정시 같이 변경 x 
```python
# 얕은 복사 
x = [[1,2,3], [4,5,6]]
y = x.copy()  # x를 y로 얕은 복사 ( 참조하는 곳까지 복사됨 )

x[0][1] = 9
print(x) # [[1,9,3],[4,5,6]]
print(y) # [[1,9,3],[4,5,6]]   똑같이 변경됨 

# 깊은 복사 
import copy
x = [[1,2,3], [4,5,6]]
y = copy.deepcopy(x)  # x를 y로 깊은 복사 (참조값 뿐만 아니라 원소/객체를 복사)

x[0][1] = 9
print(x) # [[1,9,3],[4,5,6]]
print(y) # [[1,2,3], [4,5,6]]   y배열은 영향을 받지 않음 

```

<br/>

#### 배열 



##### 배열 패턴 (p68) 
```python

s = [11,22,33,44,55,66,77]
# 1. s[:] , 리스트 s의 원소를 모두 출력함 
print(s[:])   # [11,22,33,44,55,66,77]

# 2. s[:n] , 맨 앞에서부터 n개까지 출력함 
print(s[:3])  # [11,22,33]

# 3. s[i:] , s[i]부터 맨 끝까지 출력함
print(s[3:])  # [44,55,66,77]

# 4. s[-n:] , 맨 끝에서부터 본 n위치에서 끝까지 출력함 
print(s[-3:]) # [55,66,77]

# 5. s[::k] , 맨 앞에서부터 k개씩 건너뛰며 출력함 
print(s[::2]) # [11,33,55,77]

# 6. s[::-1] , 뒤에서부터 -1씩 감소하며 전부 출력 
print(s[::-1]) # [77,66,55,44,33,22,11]

```

##### 내포 표기 생성 (p74)
```python
"""
- 리스트 안에서 for , if문을 사용하여 새로운 리스트 생성하는 기법
- 아래 예제는 numbers 리스트의 원소갑싱 홀수인 경우에 그 원소에 *2 를 하여 새 리스트(twise)를 생성한 예입니다. 
"""

numbers = [1,2,3,4,5]
# 홀수 원소값에 대해서 *2 한 리스트 생성
twise = [ num * 2 for num in numbers if num % 2 == 1] 
print(twise)

# 출력결과
[2,6,10]
```

<br/>

##### 임포트 관련 (p79)
```python
from typing import Any, Sequence 
# Any 는 제약이 없는 임의의 자료형 의미 
# Sequence는 시퀀스 형을 의미 > list형, byte array형, tuple형, bytes형
```

<br/>

##### 모듈 
```
- 파이썬에서는 하나의 스크립트 프로그램을 모듈(module)이라 함 
- 확장자(.py)를 포함하지 않는 파일의 이름 자체를 모듈 이름으로 사용함  ( max.py 의 모듈이름은 max )
- __name__ 과 '__main__' 에서 
  > 스크립트 프로그램이 직접 실행될 떄 변수 __name__ 은 '__main__' 입니다.
  > 스크립트 프로그램이 임포트(import)될 때 변수 __name__ 은 원래의 모듈 이름입니다.
```

<br/>

##### enumerate() 함수
```
- 리스트가 있는 경우 순서와 리스트의 값을 전달하는 기능을 가집니다.
- enumerate는 “열거하다”라는 뜻입니다.
- 이 함수는 순서가 있는 자료형(list, set, tuple, dictionary, string)을 입력으로 받아 인덱스 값을 포함하는 enumerate 객체를 리턴합니다.
- 보통 enumerate 함수는 for문과 함께 자주 사용됩니다.
```
https://wikidocs.net/20792

<br/>

##### 이터러블(iterable, 반복가능) // p86
```
- 문자열, 리스트, 튜플, 집합, 딕셔너리 등의 자료형 객체는 모두 이터러블(iterable) 하다는 공통점이 있음
- 이터러블 객체는 원소를 하나씩 꺼내는 구조
- 이터러블 객체를 내장 함수인 iter()의 인수로 전달하면 그 객체에 대한 이터레이터(iterator, 반복자)를 반환함 
- 이터레이터는 데이터의 나열을 표현하는 객체

  ※ reverse()와 reversed() 차이 
  x = [1,2,3,4,5,6,7]
  
  # 리스트 x의 원소를 역순으로 정렬 , 역순으로 꺼내는 이터레이터를 반환하는 거
  x.reverse()  
 
  # reversed()가 반환하는 이터러블 객체를 list()함수에 넣어 새로운 함수로 만듬
  y = list(reversed(x)) 

```

###### ※ 튜플은 이뮤터블의 속성을 가지므로 튜플 자체 정렬 x 
```
# 튜플 정렬시
- 1단계 : sorted() 함수로 정렬한 원소의 나열에서 새로운 리스트를 생성함
- 2단계 : 생성한 리스트를 튜플로 변환함 

x = (1,3,2)
x = tuple(sorted(x))   # 정렬을 완료한 리스트를 튜플로 변환 
print(x)

```