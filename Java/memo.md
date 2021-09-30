## 유용한 함수
```java
// 1. 배열 swap
import java.util.Collections;

Collections.swap(list, 인덱스a, 인덱스b); // 해당 list에서 인덱스 a와 b를 swap 함 

import java.util.ArrayList;
import java.util.Arrays;

// 2.특정 리스트에서 sublist 생성
ArrayList<Integer> leftArr = new ArrayList<>();
ArrayList<Integer> rightArr = new ArrayList<>();

leftArr = new ArrayList<Integer>(dataList.subList(0,2)); // 0~1인덱스까지 뽑아서 새로운 배열 생성

rightArr = new ArrayList<Integer>(dataList.subList(2,5));// 2부터 4인덱스까지 뽑아서 새로운 배열 생성 

// 3. 바로 배열을 생성하는 문법 
ArrayList<Integer> list2 = null;
list2 = new ArrayList<Integer>(Arrays.asList(4,1,2,3,5));


// 4. 일반 배열을 ArrayList로 변환하기
int[] arr = {4,1,2,3,5};
ArrayList<Integer> list = new ArrayList<Integer>(Arrays.asList(arr));

// 5. 배열 정렬 하는 class method 
import java.util.Collections;
// 이진 탐색은 이미 정렬되어 있는 배열에서 검색하기 때문에 , 정렬이 선행 되야 함 
Collections.sort(ArrayList객체); // 해당 ArrayList객체는 정렬이 됨

// 6. ArrayList에 값 존재 여부 
if(list.contains("A")) // A라는 값의 존재 여부를 boolean 값으로 리턴함 

// 7. HashMap

HashMap<K,V> hm = new HashMap<>(); // 1.7타입추론 추가 
hm.put("키", "값"); // 키와 값 형태로 값 저장
hm.get("키"); // 값 가져오기 
hm.remove("키"); // 키값 제거 
// iterator 사용가능한걸로 알고 있음

```


## Comparable과 Comparator 인터페이스
- Comparable 와 Comparator 는 둘 다 인터페이스로, 정렬 기준을 구현하기 위해 사용됨
  - Comparable 인터페이스는 compareTo() 메서드를 override 해서 구현
    -일반적으로는 **정렬할 객체**에 implements 로 Comparable 인터페이스를 추가하여 구현
- Comparator 인터페이스는 compare() 메서드를 override 해서 구현
  - 일반적으로는 **별도 클래스를 정의해서 구현**하며, 따라서, 동일 객체에 다양한 정렬 기준을 가진 클래스를 작성 가능

```
-일반적인 객체에 Comparble 인터페이스 상속 후 compareTo() 함수를 구현해야 정렬됨 
  - Wrapper 클래스 배열은 Arrays.sort(String[]) 먹힘 
  - Collections 클래스의 경우 Collections.sort(ArrayList) 먹힘 

- 요약 
Comparable - 객체 스스로에게 부여하는 한 가지 기본 정렬 규칙을 설정하는 것이 목적
Comparator - 기본 정렬 규칙과 다르게 원하는대로 정렬 기준을 지정하고 싶을 때(다른 정렬 기준을 사용하고 싶을 때) 사용

- 예시 
  [Java > Greedy > ComparableTest.java 와 FractionalKnapsackProblem.java 참고]
```

##### 참고 블로그 
https://cwondev.tistory.com/15
https://www.daleseo.com/java-comparable-comparator/
https://ifuwanna.tistory.com/232
https://hee96-story.tistory.com/75


## VScode 단축키 
```
1. 인터페이스 상속시 오버라이드 함수 표시 하는 방법
    - 인터페이스 명 선택 후 crtl + . 
2. import 한번에 다 하기 
    - Alt + Shift + O
3. getter / setter 생성하기 
    - 해당 속성 드래그 해서 선택 
    - F1 또는 다른 단축키 
    - getter 검색하면 generator 나옴 
```