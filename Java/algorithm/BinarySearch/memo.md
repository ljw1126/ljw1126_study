## 강의자료 
- **백준 사이트 정답 제출시 클래스명은 Main**으로 해야 동작함 ! [https://www.acmicpc.net/problem/문제번호](https://www.acmicpc.net/problem/문제번호)
- 깃허브에 올려둠 
[https://github.com/rhs0266/FastCampus/tree/main/](https://github.com/rhs0266/FastCampus/tree/main/)

## 이분/이진 탐색(Binary Search)
- **정렬이 보장되는 배열**에서 기준 x를 가지고 **범위를 '이분'**하면서 탐색하는 방법
  - **시간복잡도 O(log N)**
  - 이분 탐색은 정렬의 특성을 극대화함
- 이분 탐색 변수 셋팅 
  - L : 탐색할 가치가 있는 범위의 왼쪽 끝 인덱스 
  - R : 탐색할 가치가 있는 범위의 오른쪽 끝 인덱스 
    - M = (L+R)/2 중간 위치 인덱스 
  - result : 탐색한 x의 위치 
  - 탐색목포 : x 이하의 원소중에 가장 오른쪽에 있는 원소
- 자주 하는 실수 
  - 3위. L,R 범위를 잘못 설정하거나 result 초기값을 잘못 설정하는 경우
  - 2위. L,R,M,Result 변수의 정의를 헷갈려서 부등호 등을 잘못 쓰는 경우
  - 1위. 입력된 배열에 바로 이분 탐색을 하는데, 정렬을 하지 않는 경우


## 문제 링크 
|번호|명칭|난이도|주소|
|:------:|:--------:|:--------:|:--------------:|
|문제1||먹을 것인가 먹힐 것인가|[https://www.acmicpc.net/problem/7795](https://www.acmicpc.net/problem/7795)|
|추천문제||||
|추천1|수찾기||[https://www.acmicpc.net/problem/1920](https://www.acmicpc.net/problem/1920)|
|추천2|듣보잡||[https://www.acmicpc.net/problem/1764](https://www.acmicpc.net/problem/1764)|
|추천3|두 수의 합||[https://www.acmicpc.net/problem/3273](https://www.acmicpc.net/problem/3273)|
|추천4|숫자카드2||[https://www.acmicpc.net/problem/10816](https://www.acmicpc.net/problem/10816)|
|문제2|두 용액|난이도3(이해안됨)|[https://www.acmicpc.net/problem/2470](https://www.acmicpc.net/problem/2470)|
|||||
|||||


#### 문제1. 먹을 것인가 먹힐 것인가 ( 난이도 2 )
[tps://www.acmicpc.net/problem/7795](tps://www.acmicpc.net/problem/7795)
- A(N개)는 그대로 두고 B(M개)를 정렬 후 이진 탐색하게 되면 시간 복잡도 O(N log M) 으로 단축 됨 ( 정렬 안하면 O(NM) )
  - 1. B 배열 정렬 한번 => O (M log M)
  - 2. 모든 A의 원소마다, B 배열에 대해 이분 탐색 필요 => O (N log M)
  - 3. 총 시간 복잡도 => O((N+M)logM)

#### 문제2. 두 용액 ( 난이도3 )
[https://www.acmicpc.net/problem/2470](https://www.acmicpc.net/problem/2470)
- 최대치 확인 
  - 두수의 합으로 가능한 범위 : -20억 ~ 20억 
  - Integer의 범위 : -21억 4748만 3648 ~ 21억 4748만 3648
- 가장 쉬운 방법 O(N^2) 
  - N이 10만이니 1초에 100억번 걸리니 시간초과 발생! 
- 빠른 방법 O(N log N)
  - 왼쪽 용액(A[left])을 골랐을때, 오른쪽 용액은?
  - A[left]와 더해서 0에 가장 가까우려면?
    - A[left]와 가까울수록 좋습니다.
    - **즉, A[left]를 정했을때, -A[left]랑 가장 가까운 걸 빨리 찾자!**  
      - **정렬의 특성 중 '각 원소마다 가장 가까운 원소는 자신의 양옆 중에 있다'**
  - 정렬해보기 O(N log N) - 발생하는 이점 
    - 1. 이분 탐색 사용가능!
    - 2. 가장 가까운 원소를 탐색 가능
  - [-99 , -2, -1, 4, 98] 이 배열에 정렬되어 있을때      // 요 아래가 이해 안됨
    - result = A[left+1 ... N] 에서 X = -A[left]이상의 원소 중 가장 왼쪽 위치(만약 없다면 N+1)
      - A[result -1] 와 A[result] 중에 X랑 가장 가까운 원소가 있다!
      - 대신 result -1 과 result 중에 left+1 이상 N 이하인 것만 가능한 원소이다.
  - 시간복잡도 
    - 1. 배열정렬 한번 => O(NlogN)
    - 2. 모든 원소마다 left로 정하고, -A[left]를 이분 탐색하기 => O(NlogN)
    - 3. 총 시간 복잡도 : O(NlogN)