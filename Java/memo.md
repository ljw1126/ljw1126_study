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