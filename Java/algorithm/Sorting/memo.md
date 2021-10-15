## 강의자료 
- **백준 사이트 정답 제출시 클래스명은 Main**으로 해야 동작함 ! [https://www.acmicpc.net/problem/문제번호](https://www.acmicpc.net/problem/문제번호)
- 깃허브에 올려둠 
[https://github.com/rhs0266/FastCampus/tree/main/](https://github.com/rhs0266/FastCampus/tree/main/)

## 정렬(Sorting)
- 문제에서는 정렬에 대해 언급하지 않으므로, 간접적으로 파악하고 사용할 줄 알아야 함 
- 정렬의 특성을 이해하고 필요하다면 정렬을 활용할 것 !

###### 정렬 조건 
- 정렬 조건이 필요함
- N개 원소를 정렬하는 것의 시간 복잡도는 약 O(N log N ) 이다. 

| Primitive 원소정렬(In-place Sort) | Object 원소 정렬(Stable-Sort) | 
|:------:|:--------:|
|- Dual-Pivot Quick Sort<br/> - 최선 O(N) <br/> - 최악 O(N^2) <br/> -평균 O(N log N) | - Tim Sort <br/>- 최선 O(N) <br/> - 최악 O(N log N ) <br/> -평균 O(N log N)|

- In-place / Stable 여부를 알아야 함 
  - 정렬 알고리즘이 **In-place(제자리)** 한가?
    - 정렬하는 과정에서 N에 비해 충분히 무시할만한 개수의 메모리만큼만 추가적으로 사용하는가?
  - 정렬 알고리즘이 **Stable(안정)** 한가?
    - 동등한 위상의 원소들의 순서 관계가 보존되는가? 
      - 동일 숫자가 있을때 정렬 후 에도 순서가 보장되는가 (=첫번째 숫자, 두번째 숫자 위치가 동일)

###### 정렬 특성
- 같은 정보들은 인접해 있을 것이다.
- 각 원소마다, 가장 가까운 원소는 자신의 양 옆중에 있다.
- 정렬만 해도 문제가 쉽게 풀리는 경우가 상당히 많다. 

## 문제 링크 
|번호|명칭|난이도|주소|
|:------:|:--------:|:--------:|:--------------:|
| 연습1 |국영수|실버4(1)|[https://www.acmicpc.net/problem/10825](https://www.acmicpc.net/problem/10825)|
| 응용1 |수열 정렬|실버4(2)|[https://www.acmicpc.net/problem/1015](https://www.acmicpc.net/problem/1015)|
| 응용2 |카드|(2)|[https://www.acmicpc.net/problem/11652](https://www.acmicpc.net/problem/11652)|
| 응용3 |화살표 그리기|실버4(2)|[https://www.acmicpc.net/problem/15970](https://www.acmicpc.net/problem/15970)|
| 추가1 |파일 정리||[https://www.acmicpc.net/problem/20291](https://www.acmicpc.net/problem/20291)|
| 추가2 |단어 정렬||[https://www.acmicpc.net/problem/1181](https://www.acmicpc.net/problem/1181)|

## 연습1
[https://www.acmicpc.net/problem/10825](https://www.acmicpc.net/problem/10825)
- 1 ⪯ 모든 점수 ⪯ 100 이므로 Integer면 충분 
- 정렬만 하면 되니깐 O(N log N)
  - N log N ⪯ 100,000 * log 100,000 = 1,600,000 이므로 1초안에 가능한걸 알 수 있음

## 응용1 
[https://www.acmicpc.net/problem/1015](https://www.acmicpc.net/problem/1015)
- 복잡도 계산
  - 배열 정렬 >> O ( N log N )
  - P 배열 구하기 >> 순서대로 채우면 O( N )
  - ◾ 복잡도 >> 시간 O(N log N) , 공간 O( N )

## 응용2 
[https://www.acmicpc.net/problem/11652](https://www.acmicpc.net/problem/11652)
- 입출력 : -2^62 ~ 2^62 
  - int 범위로는 감당 안되니 **long 사용** 
- 1. 가장 쉬운 접근방법 O(N^2)
  - **완전 탐색**으로 각각 카드에 대한 **최대 빈도수 구하면 시간초과니 안하는게 좋음**
- 2. 같은 숫자를 빨리 보는 방법 O(N log N)
  - 정렬 특성 중 **'같은 정보들은 인접해 있을 것이다'** 를 이용
  - 정렬 후 봤을때 앞에 숫자랑 같으면 연속, 앞에 숫자랑 다르면 처음인지 확인 
- 복잡도 계산 
  - 배열 정렬 >> O ( N log N )
  - Counting >> 순서대로 읽으면 O(N)
   - ◾ 복잡도 >> 시간 O(N log N) , 공간 O( N )

- HashMap 자료구조로 풀어도 된다함
- 비슷한 문제로 20291 

|카드 번호(정렬후)|2|2|3|6|6|6|7|7|
|:--------------:|:--------:|:--------:|:--------:|:--------:|:--------:|:--------:|:--------:|:--------:|
| Current Count |1|2|1|1|2|3|1|2|
| Mode Count |1|2|2|2|2|3|3|3|
| Mode |2|2|2|2|2|6|6|6|

Current Count : 지금 보고 있는 숫자가 등장한 횟수
Mode Count : 지금까지의 **최빈값**의 등장 횟수 (연속된 숫자 횟수 중 최대 횟수)
Mode : 지금까지의 **최빈값**


## 응용 3
- 어려움 =
  - 점의 개수  N ⪯ 5,000 
  - O ⪯ 점의 위치 ⪯ 10^5 = 100,000
  - 1 ⪯ 점의 색깔 ⪯ N
- **정답의 최대치를 확인하고 다음 넘어가기 !** ( int냐 long이냐 판단 )
- 복잡도 계산 
  - 가장 쉬운 방법 O(N^2) 의 경우 
    - 한 노드가 조회하는데 O(N) 만큼 걸리고 노드 N개를 해야 하니 O(N^2) 이 되는데 노드가 100,000 개면 100억이니 1초에 절대 안됨 ( 1초에 1억번 연산가능 생각한다면 )
  - 각 점마다, 자신과 가장 가까운 점을 빨리 찾기 를하려면 O( N log N )
    - 정렬 특성 중 각 원소마다, **'가장 가까운 원소는 자신의 양 옆 중에 있다.'**
      - 1. 같은 색깔의 점들만 모아서 보자. (2차원 배열은 O(N^2)이라 arraylist로 각 노드별 배열 구함)
      - 2. 모은 뒤에, 각 점마다 자신과 가장 가까운 것만 찾음
      - 3. 정렬의 특성을 이용하기ㅣ 위해 점 들의 위치를 오름차순 정렬한다 O( N log N )
      - 4. 각 점마다 계산을 진행 
  - 색깔별로 모으기 >> 공간 ArrayList로 O(N)
  - 배열 정렬 >> 정렬은 O(N log N)
  - 정답 계산 >> 점마다 좌우만 보면되니 O(N)만큼 걸린다. 
  - ◾ 복잡도 >> 시간 O(N log N) , 공간 O( N )