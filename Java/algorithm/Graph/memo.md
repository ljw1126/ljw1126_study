## 강의자료 
- **백준 사이트 정답 제출시 클래스명은 Main**으로 해야 동작함 ! [https://www.acmicpc.net/problem/문제번호](https://www.acmicpc.net/problem/문제번호)
- 깃허브에 올려둠 
[https://github.com/rhs0266/FastCampus/tree/main/](https://github.com/rhs0266/FastCampus/tree/main/)

## 문제 링크 
|번호|명칭|난이도|주소|
|:------:|:--------:|:--------:|:--------------:|
| 연습1 | DFS와 BFS ||[https://www.acmicpc.net/problem/1260](https://www.acmicpc.net/problem/1260)|
||||[]()|
||||[]()|
||||[]()|

## 그래프(Graph)란?
- 자료구조로써 Graph = 정점(vertex) + 간선(edge)
  - 간선(Edge) → 무방향(양방향)/방향 + **가중치**
  - deg(x) = 정점 x의 차수(degree), 정점 x에 연결된 간선의 수 
    - ∑deg(x) = 모든 정점의 차수의 합 = 간선의 개수의 2배 ! ( 공식 알 것 !)
- Graph를 컴퓨터에 인식시키는 방식 (상황에 따라 선택사용) 
  - 1. 인접 행렬(Adjacency Matrix)
    - 2차원 배열 형태로 표현 
    - int[][] adj = int new [V][V];
    - **O(V^2)만큼 공간 필요**
    - A에서 B로 이동가능? 가중치 얼마? **O(1)에 해결 가능**
    - 정점 A에서 갈 수 있는 정점들은?  **O(V) 시간으로 해결가능(1번행 A라 할때)**
    - 만약 V = 10만 , E = 50만 일때 
      - V^2 = 100억 = 10 G(기가)    , 100억개의 공간/원소 필요 
      - Integer type으로 잡게 되면 40 G ? 계산이상함;;;
      - 실제 사용은 간선의 개수만큼 사용 > 공간 효율이 낮다 함 
  - 2. 인접 리스트(Adjacency List)
    - '연결된 것만 기록하자'
    - ArrayList<ArrayList<Intger>> adj;
    - **O(E)만큼의 공간필요** // 정점 x에 연결된 간선의 수 (=차수) 
      - V = 10만 , **E = 50만**
        - 5 * 10 ^5 = 500K 
    - A에서 B로 이동가능? 가중치 얼마?
      - O(min(deg(A),deg(B)))     // 양방향성인 경우 
      - O(deg(A)) //방향성인 경우 
    - 정점A에서 갈수 있는 점점들은?
      - O(deg(A)) 

###### 포인트
|         | 인접행렬  |인접 리스트|
|:------:|:--------:|:--------:|
|A와 B를 잇는 간선 존재 여부 확인| **O(1)** | O(min(deg(A),deg(B))) |
|A와 연결된 모든 정점 확인| O(\|V\|) | **O(deg(A))** |
|공간 복잡도| O(\|V\|^2) | **O(\|E\|)** |

## 탐색(Search)
- **시작점**에서 간선을 0개 이상 사용해서 **갈 수 있는 정점들은** 무엇인가?
  - 컴퓨터는 인접 리스트같은 자료구조로 확인가능 
  - 1. **깊이 우선 탐색(Depth First Search)**
  - 2. **너비 우선 탐색(Breadth First Search)**


#### 깊이 우선 탐색(DFS, Depth First Search)
- 인접 행렬 사용시 → O(V^2)
- 인접 리스트 → O( deg(1) + deg(2) + .. + deg(V) ) = O(E)     // V가 많으면 인접 리스트 사용하는게 좋음 

#### 너비 우선탐색(BFS, Breadth First Search)
- Queue가 들고 있는 자료의 의미 
  - 방문이 가능한 정점들은 찾을때, Queue에 해당 점정을 넣는다.
    - Queue에 정점이 남았다 → 아직 방문 가능한 점이 남아있다. or 탐색 중이다. 
    - Queue가 비어있다. → 시작점에서 갈 수 있는 모든 점을 찾아냈다! or 탐색이 끝났다!
  - 인접 행렬 사용시 → O(V^2)
  - 인접 리스트 → O( deg(1) + deg(2) + .. + deg(V) ) = O(E)     
