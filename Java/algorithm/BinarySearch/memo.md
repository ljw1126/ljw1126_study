## 강의자료 
- **백준 사이트 정답 제출시 클래스명은 Main**으로 해야 동작함 ! [https://www.acmicpc.net/problem/문제번호](https://www.acmicpc.net/problem/문제번호)
- 깃허브에 올려둠 
[https://github.com/rhs0266/FastCampus/tree/main/](https://github.com/rhs0266/FastCampus/tree/main/)

## 이분/이진 탐색(Binary Search)    -- up/down 게임
- **정렬이 보장되는 배열**에서 기준 x를 가지고 **범위를 이분**하면서 탐색하는 방법
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


## 매개 변수 탐색(Parametric Search)  -- 구현 구조는 동일
- [핵심]
  - 정답을 "매개변수(parameter)"로 만들고 Yes/No문제(결정문제)로 바꿔보기
  - 모든 값에 대해서 yes/no를 채웠다고 생각(가정)했을때, 정렬된 상태인가?
  - Yes/No 결정하는 문제를 풀기!
- **문제를 거꾸로 푸는 것이기 때문에 통찰력 요구** 
  - 최근 코테에 빈도가 높아 중요하기때문에, 훈련이 많이 필요한 알고리즘!
- 자주 하는 실수 
  - 1위. 매개 변수에 대한 결정이 No Yes 꼴(정렬된 상태)이 아닌데 이분 탐색하는 경우.
  - 2위. L,R,M,Result 변수의 정의를 헷갈려서 부등호 등을 잘못쓰는 경우
  - 3위. L,R 범위를 잘못 설정하거나 Result의 초기값을 잘못 설정하는 경우
- 키워드
  - **'~의 최댓값을 구하시오' , '~의 최솟값을 구하시오'**

## 문제 링크 
|번호|명칭|난이도|주소|
|:------:|:--------:|:--------:|:--------------:|
|문제1||먹을 것인가 먹힐 것인가|[https://www.acmicpc.net/problem/7795](https://www.acmicpc.net/problem/7795)|
|추천문제||||
|추천1|수찾기|직접 품(2)|[https://www.acmicpc.net/problem/1920](https://www.acmicpc.net/problem/1920)|
|추천2|듣보잡|직접 품|[https://www.acmicpc.net/problem/1764](https://www.acmicpc.net/problem/1764)|
|추천3|두 수의 합|직접 품(투포인터됨)|[https://www.acmicpc.net/problem/3273](https://www.acmicpc.net/problem/3273)|
|추천4|숫자카드2|실버4(상하검색후 차로구하는데 부등호 어려움)*|[https://www.acmicpc.net/problem/10816](https://www.acmicpc.net/problem/10816)|
|문제2|두 용액|난이도3(이해안됨)|[https://www.acmicpc.net/problem/2470](https://www.acmicpc.net/problem/2470)|
|||||
|매개변수탐색문제||||
|문제3|**나무 자르기**||[https://www.acmicpc.net/problem/2805](https://www.acmicpc.net/problem/2805)|
|추천문제||||
|추천5|랜선 자르기|실버3(R범위틀림)|[https://www.acmicpc.net/problem/1654](https://www.acmicpc.net/problem/1654)|
|추천6|예산|실버3(직접한건어설픔)*|[https://www.acmicpc.net/problem/2512](https://www.acmicpc.net/problem/2512)|
|문제4|**공유기 설치**||[https://www.acmicpc.net/problem/2110](https://www.acmicpc.net/problem/2110)|
|추천문제||||
|추천7|기타🎸 레슨|문제 이해안됨|[https://www.acmicpc.net/problem/2343](https://www.acmicpc.net/problem/2343)|
|추천8|용돈 관리|문제 이해안됨|[https://www.acmicpc.net/problem/6236](https://www.acmicpc.net/problem/6236)|
|추천9|이상한 술집|(long타입..)|[https://www.acmicpc.net/problem/13702](https://www.acmicpc.net/problem/13702)|
|추천10|어두운 굴다리|실버5(공식이해안됨)|[https://www.acmicpc.net/problem/17266](https://www.acmicpc.net/problem/17266)|
|추천11|K번째수(고난도,4/5)||[https://www.acmicpc.net/problem/1300](https://www.acmicpc.net/problem/1300)|
|추천12|날카로운눈(고난도,4/5)||[https://www.acmicpc.net/problem/1637](https://www.acmicpc.net/problem/1637)|

// 추천 11,12는 하면 좋다함. 안하고 넘어가도 일단은 괜찮음(어렵기때문에)

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
    - result = A[left+1 ... N](오른쪽구간) 에서 X = -A[left]이상의 원소 중 가장 왼쪽 위치(만약 없다면 N+1)
      - A[result -1] 와 A[result] 중에 X랑 가장 가까운 원소가 있다!
      - 대신 result -1 과 result 중에 left+1 이상 N 이하인 것만 가능한 원소이다.
  - 시간복잡도 
    - 1. 배열정렬 한번 => O(NlogN)
    - 2. 모든 원소마다 left로 정하고, -A[left]를 이분 탐색하기 => O(NlogN)
    - 3. 총 시간 복잡도 : O(NlogN)

#### 문제3. 나무 자르기 --------- 최대치 계산하고 해야 함 ! 
[https://www.acmicpc.net/problem/2805](https://www.acmicpc.net/problem/2805)
- 나무의 수 N , 원하는 나무의 길이 M 
  - (1 ≤ N ≤ 1,000,000, 1 ≤ M ≤ 2,000,000,000)   
- 최대치 계산해보기 ! ( 여기서 많이 틀림 )
  - 나무의 개수 N = 100만, 필요한 나무의 길이 M = 20억  
    - 1. 정답의 범위 : 0 ~ 10억 
      - 각 나무 별 10억의 크기를 가지고 H = 0 으로 했을때
    - 2. 잘린 나무의 길이 합 ≤ 나무 높이의 총합 = 100만 * 10억 = 10^15
      - **Integer 범위 초과!** , **즉 계산 과정 중의 변수 타입은 long으로 !** 
- 원래 문제 : **원하는 길이 M 만큼 얻을 수 있는 최대 높이 H**는 얼마인가?
- 뒤집은 문제 : **어떤 높이 H로 잘랐을 때, 원하는 길이 M만큼**을 얻을 수 있는가? (Yes/No)
  - 시간 복잡도 : **O(N)**
- [핵심]
  - 정답을 "매개변수(parameter)"로 만들고 Yes/No문제(결정문제)로 바꿔보기
  - 모든 값에 대해서 yes/no를 채웠다고 생각(가정)했을때, 정렬된 상태인가?
  - Yes/No 결정하는 문제를 풀기!

H | 0 | 1 | 2 | 3 | 4 | ... |15|16|....| 20 | 
------------------------------------------------
   Yes                       Yes| No        
  
- 원래 문제 : 뒤집은 문제를 모든 H마다(0~20억)해보면 마지막 yes가 정답 
  - 시간복잡도 : O(뒤집은 문제 * log 20억) = O(N * log X) = N*31
  -                나무마다 log 20억번 연산만 해주면 됨 
- [정리]
  - 1. H를 정해서 결정 문제 한 번 풀기 => O(N)
  - 2. 정답의 범위를 이분 탐색하면서 풀기 => O(logX)번 반복할 것 
  - 3. 총 시간 복잡도 : O(NlogX)

#### 문제4 공유기 설치 (난이도 3)        -- 문제가 이해 안됨 
[https://www.acmicpc.net/problem/2110](https://www.acmicpc.net/problem/2110)
- 집의 개수  N (2 ≤ N ≤ 200,000) , 공유기의 개수 C(2 ≤ C ≤ N), 집의 좌표 xi (0 ≤ xi ≤ 1,000,000,000)
- [최대치 계산]
  - 제일 멀리 설치해보면 정답은 10억 이하 => Integer 로 가능 !
- [키워드]
  - C개의 공유기를 N개의 집에 적당히 설치해서, 가장 인접한 **두 공유기 사이의 거리를 최대로 하는 프로그램**을 작성하시오.
- 원래 문제 : C개의 공유기를 설치했을때, 최대 인접거리(D)는 얼마인가?
- 뒤집은 문제 : 어떤 거리(D)만큼은 거리를 둘때, 공유기 C 개를 설치할 수 있는가? Yes/No
  - Yes가 나온 거리 중에 가장 큰값이 정답 
  - 그리트 기법으로 생각해서 왼쪽 부터 설치 !
    - **정렬 필요 !**
    - O(N)
  - 원래 문제 : 뒤집은 문제를 모든 D마다 (1~10억)해보면 마지막 yes가 정답
  - 시간복잡도 : O(뒤집은 문제 * log 10억) = O(N*logN) = N * 30    // log10억이 29정도, log 30억 31정도 
- [정리]
  - 1. 주어진 집들을 정렬하기 => O(N log N)        --- skip 하면 안됨! 입력값이 정렬되어서 오는 보장 x 
  - 2. D를 정해서 결정 문제 한번 풀기 => O(N)
  - 3. 정답의 범위를 이분 탐색하면서 풀기 => O(logX)번 반복할 것 
  - 4. 총 시간 복잡도 : O(NlogN + NlogX)      // 이때 X는 10억에 해당 