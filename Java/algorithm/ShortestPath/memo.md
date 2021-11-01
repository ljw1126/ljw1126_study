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


#### 다익스트라 알고리즘 시간복잡도 O(E log V)


## 문제 링크 
|번호|명칭|난이도|주소|
|:------:|:--------:|:--------:|:--------------:|
|문제1|최소비용구하기|난이도3|[https://www.acmicpc.net/problem/1916](https://www.acmicpc.net/problem/1916)|
|추천1|최단경로|골드5|[https://www.acmicpc.net/problem/1753](https://www.acmicpc.net/problem/1753)|


#### 문제1. 최소비용 구하기
- 버스비용이 음수가 아니고 시작점/도착점 주어지니 다익스트라 알고리즘 사용하면 됨 ! 
- [최단거리의 특성]
  - 같은 정점을, 두번 방문할 이유가 없다.
  - 따라서 정답은 최대 => 버스 비(10만) * 정점 수 (1천) = 1억 
    - 이하이므로 Integer로 충분하다 // long인 경우 dist 타입, 초기값MAX_VALUE도 바꿔줘야 함  