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

```