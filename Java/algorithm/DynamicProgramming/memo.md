## 강의자료 
- **백준 사이트 정답 제출시 클래스명은 Main**으로 해야 동작함 ! [https://www.acmicpc.net/problem/문제번호](https://www.acmicpc.net/problem/문제번호)
- 깃허브에 올려둠 
[https://github.com/rhs0266/FastCampus/tree/main/](https://github.com/rhs0266/FastCampus/tree/main/)

## 동적 프로그래밍(Dynamic Programming)이란?
- Dynamic := 동적인, 변화가는 / Programming := 문제를 해결하는 
- 문제의 크기를 변화하면서 정답을 계산하는데, **작은 문제의 결과를 이용해서 큰 문제의 정답을 빠르게 계산하는 알고리즘**
- 1. 문제가 원하는 정답을 찾기 위해 가장 먼저 완전 탐색(Brute-Force Search) 접근을 시도해본다.
- 2. 근데, 완전 탐색 과정에서 탐색하게 되는 경우가 **지나치게 많아서** 도저히 안될 것 같다. 
- 3. 이럴때 모든 경우를 **빠르게** 탐색하는 방법으로 Dynamic Programming 접근을 시도해볼 수 있다.
  - => **규격화**된 문제 풀이 순서를 외워서 훈련해야 함
- [규격화 된 풀이 순서]
  - 1. 풀고 싶은 가짜 문제 정의
  - 2. 가짜 문제를 풀면 진짜 문제를 풀 수 있는가?
  - 3. **초기값**은 어떻게 되는가?
  - 4. 점화식 구해내기 
  - 5. **진짜 문제** 정답 출력하기
- [동적 프로그래밍의 장점]
  - 규격화 되어 있음 
  - 방법만 찾으면 코딩량은 작다
- [동적 프로그래밍의 단점]
  - 문제 의도를 파악해서 점화식 스스로 찾아내야 함 

## 문제 링크 
|번호|명칭|난이도|주소|
|:------:|:--------:|:--------:|:--------------:|
|문제1|1,2,3 더하기|난이도2|[https://www.acmicpc.net/problem/9095](https://www.acmicpc.net/problem/9095)|
|문제2|2 x N 타일링|난이도2|[https://www.acmicpc.net/problem/11726](https://www.acmicpc.net/problem/11726)|
|추천문제||||
|추천1|피보나치 함수|쉬움|[https://www.acmicpc.net/problem/1003](https://www.acmicpc.net/problem/1003)|
|추천2|피보나치 수 5|쉬움|[https://www.acmicpc.net/problem/10870](https://www.acmicpc.net/problem/10870)|
|추천3|1,2,3 더하기 3|동일문제|[https://www.acmicpc.net/problem/15988](https://www.acmicpc.net/problem/15988)|
|추천4|1,2,3 더하기 6|경우실수|[https://www.acmicpc.net/problem/15991](https://www.acmicpc.net/problem/15991)|
|추천5|카드 구매하기|실버1(이해불가)|[https://www.acmicpc.net/problem/11052](https://www.acmicpc.net/problem/11052)|
|추천6|암호 코드||[https://www.acmicpc.net/problem/2011](https://www.acmicpc.net/problem/2011)|
|||||
|문제3|계단오르기|난이도2|[https://www.acmicpc.net/problem/2579](https://www.acmicpc.net/problem/2579)|
|추천문제||||
|추천7|RGB거리||[https://www.acmicpc.net/problem/1149](https://www.acmicpc.net/problem/)|
|추천8|포도주 시식||[https://www.acmicpc.net/problem/2156](https://www.acmicpc.net/problem/)|
|추천9|이친수||[https://www.acmicpc.net/problem/2193](https://www.acmicpc.net/problem/)|
|추천10|스티커||[https://www.acmicpc.net/problem/9465](https://www.acmicpc.net/problem/)|
|추천11|동물원||[https://www.acmicpc.net/problem/1309](https://www.acmicpc.net/problem/)|
|추천12|줄어들지 않아||[https://www.acmicpc.net/problem/2688](https://www.acmicpc.net/problem/)|
|||||
|문제4|오르막수|난이도3|[https://www.acmicpc.net/problem/11057](https://www.acmicpc.net/problem/11057)|

#### 문제1. 1,2,3 더하기 
[https://www.acmicpc.net/problem/9095](https://www.acmicpc.net/problem/9095)
- [풀이]
  - if) 완전 탐색 접근 할 경우
    - N이 커질수록 탐색해야 하는 경우가 엄청 많아짐 => 엄청 느려짐 
  - [Dynamic Programming 접근]
  - 1) 풀고 싶은 가짜 문제 정의
    - hint) 진짜 문제 먼저 써보기 
      - 진짜문제 := 주어진 N에 대해서 N을 1,2,3 의 합으로 표현하는 경우의 수 
      - 가짜문제 := Dy[i] = i 를 1,2,3의 합으로 표현하는 경우의 수 //다이나믹 배열 
  - 2) 가짜 문제를 풀면 진짜 문제를 풀 수 있는가?
    - Dy 배열을 가득 채울 수만 있다면? 진짜 문제에 대한 대답은 Dy[N] 이다. // 해당 배열이 정답값이라고 가정
  - 3) 초기값은 어떻게 되는가?
    - 초기값: 쪼개지 않아도 풀 수 있는 '작은 문제'들에 대한 정답 
  - 4) 점화식 구해내기 
    - 1. Dy[i] 계산에 필요한 탐색 경우를 공통점끼리 묶어 내기(partitioning)
    - 2. 묶어낸 부분의 정답을 Dy 배열을 이용해서 빠르게 계산해보기 
        - 찾아낸 점화식 => **Dy[i] = Dy[i-1] + Dy[i-2] + Dy[i-3]**
        - 4번지는 1,2,3번지 값을 더하면 구해짐 
  - 5) **진짜 문제** 정답 출력하기
- 완전 탐색을 통해 모든 경우를 세면 정답의 개수만큼의 시간이 걸리지만, Dy배열을 1번지부터 N번지까지 채우는 것은 O(N) 이라는 시간복잡도면 충분하다!
- 다수의 테스트 케이스를 처리하기 전에 모든 N에 대해 정답을 구해놓자!

#### 문제2. 2 x N 타일링
[https://www.acmicpc.net/problem/11726](https://www.acmicpc.net/problem/11726)
- [풀이]
  - if) 완전 탐색 접근할 경우 
    - => N이 커질수록 탐색해야 하는 경우가 엄청 많아진다.
  - [Dynamic Programming 접근]
    - 1) 풀고 싶은 가짜 문제 정의 
      - hint) 진짜 문제 먼저 써보기   
        - 진짜 문제 := 주어진 N에 대해서 2 x N 타일링 경우의 수 
        - 가짜 문제 := Dy[i] = 2 x i 타일링 경우의 수 
    - 2) 가짜 문제를 풀면 진짜 문제를 풀 수 있는가?
      - Dy 배열을 가득 채울 수만 있다면? 진짜 문제에 대한 대답은 Dy[N]이다.
    - 3) 초기값은 어떻게 되는가?
      - 초기값 : 쪼개지 않아도 풀 수 있는 "작은 문제"들에 대한 정답
      - 손으로 풀 수 있다면 , 손으로 풀 수 있는 결과 값을 셋팅 
    - 4) 점화식 구하기 // 손으로 그려보자..
      - 1. Dy[i] 계산에 필요한 탐색 경우를 공통점끼리 묶어내기(Partitioning)
        - 예) 마지막에 뭐가 있나?
      - 2. 묶어낸 부분의 정답을 Dy배열을 이용해서 빠르게 계산해보기 
        - ( 2 * (i-2) 를 만들고 타일 붙이는 경우의 수 ) + (2 * (i-1) 을 만들고 타일 붙이는 경우의 수 ) 
        - Dy[i] = Dy[i-1] + Dy[i-2] % 10007  // 문제에 10007 나눈 나머지를 구해라 함
    - 결국 O(N)만에 구할 수 있다.
    - 5) **진짜 문제** 정답 출력하기

#### 계단오르기
[https://www.acmicpc.net/problem/2579](https://www.acmicpc.net/problem/2579)
- 정답의 최대치 
  - N개의 계단을 모두 밟는 다고 치더라도 (문제에서는 안되지만)
    - N * 최대 점수 = 300 * 10000 = 300만 // Integer범위 충분
  - 1칸 또는 2칸씩 밟아서 꼭 도착지점에 도착해야 함(이때 연달아 1칸씩 해서 3칸은 x)
- [풀이]
  - if) 완전 탐색 접근 
    - 완전 탐색 접근을 통해서 모든 경우를 직접 하나하나 찾아내 보자.
    - 본 문제에서 "경우"란, 조건을 만족하게 계단을 올라 도착까지 가는 방법을 의미한다. 
  - [Dynamic Programming 접근]
    - 1) 풀고 싶은 가짜 문제 정의 - 필요한 정보를 문제에 추가하기 
      - 진짜 문제 := N번째 계단에 도착하며 얻는 최대 점수 
      - 가짜 문제 
        - Dy[i][0] := **i-1번째 계단은 밟지 않고**, i번째 계단에 도착하여 얻는 최대 점수 
        - Dy[i][1] := **i-1번째 계단을 밟고서**, i번재 계단에 도착하여 얻는 최대점수 
    - 2) 가짜 문제를 풀면 진짜 문제를 풀 수 있는가?
      - Dy 배열을 가득 채울 수만 있다면? 진짜 문제에 대한 대답은 
      - max(Dy[N][0], Dy[N][1])
    - 3) 초기값은 어떻게 되는가?
      - 초기값 : 쪼개지 않아도 풀 수 있는 "작은 문제" 들에 대한 정답 
    - 4) 점화식 구하기 
      - 1. Dy[i][0], Dy[i][1] 계산에 필요한 탐색 경우를 공통점끼리 묶어 내기(Partitioning)
      - 2. 묶어낸 부분의 정답을 Dy 배열을 이용해서 빠르게 계산해보기 
        - Dy[i][0] = max( Dy[i-2][1] + A[i] , Dy[i-2][0] + A[i])  // 두칸 올라오는 경우
        - Dy[i][1] = Dy[i-1][0] + A[i]     // 한칸 올라오는 경우
    - 완전 탐색을 통해 모든 경우를 세면 정답의 개수만큼의 시간이 걸리지만, Dy 배열을 1번지부터 N번지 까지 채우는 것은 O(N)이라는 시간 복잡도면 충분하다!

|    i   |   0    |   1    |   2    |   3    |    4   |   5    |    6   |
|:------:|:------:|:------:|:------:|:------:|:------:|:------:|:------:|
| A[i]   |   0    |   10   |   20   |   15   |    25  |   10   |   20   |
|Dy[i][0]|   -*   |   10*  |   20   |   25   |    55* |   45   |   75*  |
|Dy[i][1]|   -    |    -   |   30*  |   35   |    50  |   65   |   65   |


- [심화탐구(역추적, Backtrack) , 난이도3]      -- 직접 짜 보기 
  - 코테에서 많이 요구하지 않음, 다만 동적 프로그래밍 이해했다고 말할려면 이정도는 해야 한다.
    - 6) 최대값이 75인건 알겠는데, 실제로 어떻게 이동해야 할까?
        - come 배열에 어디서 왔는지 기록  
        - Dy[i][0] = max( Dy[i-2][1] + A[i] , Dy[i-2][0] + A[i])  , come[i][0] = { (i-2,1) / (i-2,0)}
        - Dy[i][1] = Dy[i-1][0] + A[i]  , come[i][0] = (i,0)
        - Table을 채워 나갈때에 기록을 함께 한다면 "실제 방법"도 찾을 수 있다. 
        - 이를 역추적, 혹은 Backtrack이라고 한다. 


#### 문제4. 오르막수 
[https://www.acmicpc.net/problem/11057](https://www.acmicpc.net/problem/11057)
- [정답의 최대치]
  - 정답은 10,007로 나눈 나머지로 출력해야 한다. 
  - 즉, 문제를 푸는 과정에서 계속 나눈 나머지만 가지고 있다면 Integer 범위로도 충분함
- [풀이]
  - 완전 탐색 접근을 통해서 모든 경우를 직접 하나하나 찾아내 보자.
  - 본 문제에서 "경우"란, 길이에 맞는 오르막 수를 전부 하나하나 찾는다는 것이다. 
  - [Dynamic Programming 접근]
    - 1) 풀고 싶은 가짜 문제 정의      
      - 진짜 문제 := 길이가 N인 오르막 수의 개수
      - 가짜 문제 := Dy[i] = 길이가 i인 오르막 수의 개수
      - 해보면 안된다함 // 길이만 가지고 작은 길이 , 큰길이 