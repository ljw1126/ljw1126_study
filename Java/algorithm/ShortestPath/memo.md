## 강의자료 
- **백준 사이트 정답 제출시 클래스명은 Main**으로 해야 동작함 ! [https://www.acmicpc.net/problem/문제번호](https://www.acmicpc.net/problem/문제번호)
- 깃허브에 올려둠 
[https://github.com/rhs0266/FastCampus/tree/main/](https://github.com/rhs0266/FastCampus/tree/main/)

## 최단 거리(Shortest Path)란?
- 그래프의 시작점에서 다른 지점까지의 최단 거리 
- BFS 리마인드
  - 탐색(Search) = 시작점에서 간선을 0개 이상 사용해서 갈 수 있는 정점들은 무엇인가?
  - 대표적으로 Depth First Search / Breadth First Search 
  - **BFS**의 경우 => 다른 정점 까지 **최소 이동 횟수도 계산가능**! (한번의 이동 == 간선의 가중치가 1)
- Dijkstra 알고리즘 요약 
  - Input(입력)
    - Graph G(V,E) := Nonnegative-Weighted Graph (가중치가 음수가 아닌 0 이상인)
    - Start Vertes S / Vertices {S1,...} BFS와 동일하게 시작점 한개 또는 여러개multisource이 주어져야함)
  - Output(출력결과)
    - 시작점에서 모든 점까지의 최단 거리 
  - Time Complexity(시간복잡도)
    - O(E log V)
  - 다익스트라 알고리즘에 필요한 정보 
    - dist[i] := 시작점에서 i번 정점까지의 가능한 최단 거리 (기재된 배열)
      - 시작점은 0, 다른건 무한대로 초기화
    - 자료구조 D := {(v,d)|시작점에서 v까지 d만에 갈 수 있음을 확인했다.}
- ※ Dijkstra 알고리즘 순서도 (영상 7분 부터 )

![Alt text](/images/fastcampus/dijkstra.png) 


#### ※ 최단 거리(Shortest Path) 알고리즘 
- **BFS , Dijkstra(다익스트라) 정도만 알면 됨** 
    - 두개 비교시 시간복잡도는 BFS가 Dijkstra(다익스트라) 보다 빠름 

|  이름  |간선의 가중치| 시작점 |  도착점  |시간복잡도|
|:------:|:--------:|:--------:|:-------:|:-------:|
|BFS     |  모두 1  | 한 정점   | 모든 정점| O(V+E)|
|Dijkstra|  ⪰ 0    | 한 정점   | 모든 정점| O(ElogV)|
|Floyd-Warshall|제약x| 모든 정점| 모든 정점| O(V^3)|
|Bellman-Ford|제약x | 한 정점   | 모든 정점| O(VxE)|
|SPFA    |제약x     | 한 정점   | 모든 정점| O(VxE)|
|A*      |⪰ 0      | 한 정점    | 한 정점 | O(b^d)|

- 탐색(search)이란?
  - **시작점**에서 간선을 0개 이상 사용해서 **갈 수 있는 정점**들은 무엇인가? 
  - BFS => 다른 정점까지 **최소 이동 횟수**도 계산가능 !

###### 다익스트라 알고리즘 시간복잡도 O(E log V)
###### 다익스르타 알고리즘 시간복잡도 설명 굉장히 복잡하니 다시 보기 

## 문제 링크 
|번호|명칭|난이도|주소|
|:------:|:--------:|:--------:|:--------------:|
|문제1|최소비용구하기|난이도3|[https://www.acmicpc.net/problem/1916](https://www.acmicpc.net/problem/1916)|
|추천1|최단경로|골드5|[https://www.acmicpc.net/problem/1753](https://www.acmicpc.net/problem/1753)|

## 추천문제 없어서 따로 풀이 기록 
- ex1_1238 - 파티 (골드3) 의 경우 
  - 각 노드에서 X로 가는 최단 경로 
    - => 각 노드별로 X로 가는 최단 경로를 구해야 함(for문)
      - 그러다보니 최대 1000개 노드별 * O(ElogV) 가 걸리는거 ! // 시간 초과 발생가능 1억번씩하니 
      - 그래서 가중치를 뒤집어서 x부터 시작하는 최단 경로를 구하면 
        - 각 노드에서 x로 가는 최단 경로 값이 구해지게 됨 
  - x에서 각 노드로 가는 최단 경로 
    - 입력 받은 edges를 그대로 사용해서 x 번부터 다른 노드까지 최단 경로 구하면 됨 
![Alt text](/images/fastcampus/ex1238_파티_최단경로문제.png) 


- ex2_13549 숨바꼭질3의 경우 
  - 그래프에서 풀었던 문제와 동일
  - 10번은 틀렸던 이유가 *2 , -1, +1 가중치 순서때문에 if문 위치 순서 때문이었음...

- ex3_2665.java (이해 덜됨)
  - https://www.acmicpc.net/problem/2665
  - https://suhyeokeee.tistory.com/117
  - 일반적인 bfs 문제라는데.. 이런식으로도 문제 풀 수 있구나.. 
  - 벽을 변환하지 않고 bfs를 돌면서 벽을 만나면 1씩 증가 시키는구나 
  - boolean[][][] visit = new boolean[N][N][N*N]    // N*N번이 새로 만든 벽 카운트 

#### 문제1. 최소비용 구하기
- 버스비용이 음수가 아니고 시작점/도착점 주어지니 다익스트라 알고리즘 사용하면 됨 ! 
- [최단거리의 특성]
  - 같은 정점을, 두번 방문할 이유가 없다.
  - 따라서 정답은 최대 => 버스 비(10만) * 정점 수 (1천) = 1억 
    - 이하이므로 Integer로 충분하다 // long인 경우 dist 타입, 초기값MAX_VALUE도 바꿔줘야 함  