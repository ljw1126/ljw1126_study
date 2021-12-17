## 정렬 알고리즘 
```
- 정렬 알고리즘의 핵심 요소는 교환,삽입,선택이며 대부분 정렬 알고리즘은 이 세가지 요소를 응용한 것이다.
```

#### 시간 복잡도 

|   명칭    |최선|최악|
|:---------:|:---:|:---:|
|Bubble     |O(N^2)|O(N^2)|
|Selection  |O(N^2)|O(N^2)|
|Inserttion |O(N^2)|O(N^2)|
|Quick      |O(NlogN)|O(n^2)|
|Merge      |O(NlogN)|O(NlogN)|
|Heap       |O(NlogN)|O(NlogN)|



#### stable sort, in-place sort ?
- stable sort(안정된 정렬)
  - 같은 키 값인 요소의 순서가 정렬 전후에도 유지됨
```
# stable 한 경우
    • Bubble Sort(버블)
    - 앞/뒤의 값을 대소 관계를 비교해서 교환하는 정렬방식
    - 값이 동일한 경우 순서 변경 x ( = 고로 stable )

    • Insertion sort(삽입)

    • Merge Sort(병합)
    - 두 배열 A, B를 병합시, 같은 값이 나온 경우 앞의 그룹 우선 순위 줄 경우
    
    • Counting Sort(계수)

# stable 하지 않는 경우 
    • Quick Sort 
    • Heap Sort 
    • Selection Sort 
    
```
- in-place sort()
  - 추가적인 메모리 저장 공간이 거의 안드는 정렬
```
# inplace 한 경우 
    • Insertion Sort 
    • Selection Sort 
    • Bubble Sort
    • Heap Sort 
    • Quick Sort 
    • Shell Sort 

# inplace 하지 않는 경우 
    • Merge Sort 
    • Counting Sort 
```

#### 참고 자료 
[https://ko.wikipedia.org/wiki/%EC%A0%95%EB%A0%AC_%EC%95%8C%EA%B3%A0%EB%A6%AC%EC%A6%98#%ED%80%B5_%EC%A0%95%EB%A0%AC](https://ko.wikipedia.org/wiki/%EC%A0%95%EB%A0%AC_%EC%95%8C%EA%B3%A0%EB%A6%AC%EC%A6%98#%ED%80%B5_%EC%A0%95%EB%A0%AC)
[https://yabmoons.tistory.com/250](https://yabmoons.tistory.com/250)