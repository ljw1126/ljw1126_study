## 1. 알고리즘 기초 

#### 알고리즘(algorithm)이란?
```
 어떠한 문제를 해결하기 위해 정해 놓은 일련의 절차 
```
+ 특히 올바른 알고리즘이란 **어떠한 경우에도 실행 결과가 똑같이 나오는 것**을 말함.
+ 만약 알고리즘의 실행 결과가 어떤 경우에는 맞고 어떤 경우에는 틀리면 올바른 알고리즘이라고 할 수 없음.


#### 파이썬의 변수 알아보기 (p56)
```
- 파이썬에서는 데이터, 함수, 클래스, 모듈, 패키지 등을 모두 객체(Object) 취금함 
- 객체는 자료형(data type)을 가지며 메모리(저장 공간)를 차지함 
- 이러한 특징 때문에 '파이썬의 변수는 값을 갖지 않는다'는 특징이 있음 
  > 변수는 객체를 참조하는 객체에 연결된 이름에 불과함 
  > 모든 객체는 메모리를 차지하고, 자료형뿐만 아니라 식별번호(identity)를 가짐
    ※ 식별 번호는 다른 객체와 구별할 수 있는 객체 고유번호 뜻함

```


###### ※ 실습 1-6 branch2a.py에서 조건문에 사용된 pass 문은 '아무것도 수행하지 말고 그냥 지나치세요'를 뜻하는 키워드임

#### 용어 
```
- 산술 연산자 (operator)
  +,-,> 등과 같은 기호 
- 피연산자 (operand)
  a > b 와 같은 대소 관계 판단하는 식에서 a,b에 해당
- 전역변수 ( global variable )
  함수 외부에서 정의한 변수로, 프로그램 전체에서 사용할 수 있음 
- 지역변수 ( local variable )
  함수 내부에서 정의한 변수로, 함수 내부에서만 사용할 수 있음 
```

#### 함수 
```
1. range()  //p.34
   이터러블 객체를 생성함 ( 이터러블 객체는 '반복할 수 있는 객체'를 말함)
   - range(n) : 0이상 n 미만인 수를 차례로 나열하는 수열
   - rnage(a,b) : a 이상 b 미만인 수를 차례로 나열하는 수열 
   - rnage(a,b,step) : a 이상 b 미만인 수를 step 간격으로 나열하는 수열 

```