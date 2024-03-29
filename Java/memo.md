﻿## 객체와 인스턴스 비교 
- 비슷한 개념이지만 정확히 구별하면 인스턴스(instance)가 객체(object)보다 큰 의미이다. 객체는 어떤 클래스를 사용해서 만들어진 것을 의미한다. 그리고 그 객체가 메모리에 할당되어 실제 메모리를 차지하는 것을 인스턴스라고 한다.(by Effetive Java)
```java 
/*
  str 은 String 클래스를 사용하여 객체를 선언한 것이다. 
  아직 str 에 문자열이 할당되어 있지 않은 상태이다.
  new 키워드를 사용하여 JVM(Java Virtual Machine)에 데이터가 생성된 것을 보여준다. 
  다시 말해, 객체 str 에 "Hello world"라는 문자열을 할당하였다(instantiate). 
  이렇게 객체를 실제로 메모리에 할당하는 과정을 인스턴스화(instantiate)라고 한다. 
  그리고 이렇게 인스턴스화된 객체를 인스턴스라고 부른다.
*/
String str; 
str = new String("Hello world"); 
```

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
for(String key : hm.keySet()){
  Sytem.out.println(key); // HashMap에 있는 key를 iterator 받아 사용가능 
}

// HashMap 특정키 존재 여부 확인 
import java.util.HashMap;

HashMap<String, ArrayList<Edge>> graph = new HashMap<String, ArrayList<Edge>>();
graph.put("A" , new ArrayList<Edge>());
graph.put("B" , new ArrayList<Edge>());

graph.containsKey("C");  // false 리턴 

// 찾는 key에 대한 value가 없을때, 디폴트 값 반환하기 
graph.getOrDefault("C", new ArrayList<Edge>()); // []




// 8. 우선 순위 큐 
import java.util.PriorityQueue;

PriorityQueue<T> priorityQueue = new PriorityQueue<T>(); // 기본 최소힙? T타입의 정렬기준 따르는듯?
// PriorityQueue 데이터 추가 (add() 와 offer() 둘다 동일하게 데이터를 추가하는 기능을 제공함) : (1) 데이터를 넣기
priorityQueue.add(new Edge(2, "A"));
priorityQueue.add(new Edge(5, "B"));
priorityQueue.offer(new Edge(1, "C"));
priorityQueue.offer(new Edge(7, "D"));

// PriorityQueue 최상단 데이터 확인만 
priorityQueue.peek();

// PriorityQueue 최상단 데이터 꺼낸 후 삭제
Edge edge1 = priorityQueue.poll();
System.out.println(edge1);
System.out.println(priorityQueue);

// 데이터 사이즈 확인 
priorityQueue.size();

//  알고리즘 > ShortestPath 문제 풀이 중 이런게 등장함 (정렬하는 메서드로 보임, 211029)
PriorityQueue<Info> pq = new PriorityQueue<>(Comparator.comparingInt(o -> o.dist));


// 9. MAX, MIN 
int max, min;
max = Integer.MIN_VALUE;
min = Integer.MAX_VALUE; 

int value = 1000;
max = Math.max(max, value); // 기존 max값과 value 값중 최대값으로 갱신
min = Math.min(min, value); // 기존 min값과 value 값중 최소값으로 갱신

// 10. 고정길이 배열 정렬
int[] nums = new int[10]; // 순서 아무렇게 넣었다고 가정 [0,9,,5,1,23,3,1..]

Arrays.sort(nums); // 처음부터 끝까지 정렬 
Arrays.sort(nums, 1, N+1); // 1부터 N 인덱스까지 지정해서 정렬 

System.out.println(Arrays.toString(nums));

// 11. 문자열로 n * n 배열 주어질때 int[][] 형으로 변경하기 
     static void input(){
        N = scan.nextInt();
        map = new int[N][N];
        // 아스키 코드에서 -'0'(48)을 뺌 
        for(int i=0;i<N;i++){
            String str = scan.nextLine();
            for(int j=0;j<str.length();j++){
                map[i][j] = str.charAt(j) - '0';
            }
        }

        visit = new boolean[N][N];
        dist = new int[N][N];
        ans = Integer.MAX_VALUE;
    }

// 12. 인접 리스트로 트리 구성시 트리 노드 삭헤라 경우
        // erased와 그의 부모 사이의 연결을 끊어주기
        for(int i=0 ; i < n ; i++){
            if(child[i].contains(erased)){
                child[i].remove(child[i].indexOf(erased));
            }
        }

// 13. 알파벳 ↔ 숫자 변환 
F:\gitRepository\ljw1126_study\Java\algorithm\Tree\recommandEx\ex1_1991practice.java


// 14. char 로 array 받기 (ex14_10026)
static void input(){
        N = scan.nextInt();
        visit = new boolean[2][N][N];
        arr = new char[N][N];

        for(int i=0;i<N;i++){
          arr[i] = scan.next().toCharArray();
        }
}
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


