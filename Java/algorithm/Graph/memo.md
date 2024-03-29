﻿## 강의자료 
- **백준 사이트 정답 제출시 클래스명은 Main**으로 해야 동작함 ! [https://www.acmicpc.net/problem/문제번호](https://www.acmicpc.net/problem/문제번호)
- 깃허브에 올려둠 
[https://github.com/rhs0266/FastCampus/tree/main/](https://github.com/rhs0266/FastCampus/tree/main/)

## 문제 링크 
|번호|명칭|난이도|주소|
|:------:|:--------:|:--------:|:--------------:|
| 연습1 | DFS와 BFS |기본개념*|[https://www.acmicpc.net/problem/1260](https://www.acmicpc.net/problem/1260)|
| 응용1 | 단지번호 붙이기|난이도2*|[https://www.acmicpc.net/problem/2667](https://www.acmicpc.net/problem/2667)|
| 추천문제 ||||
|격자형|유기농배추|**|[https://www.acmicpc.net/problem/1012](https://www.acmicpc.net/problem/1012)|
||연결요소의개수|*참고|[https://www.acmicpc.net/problem/11724](https://www.acmicpc.net/problem/11724)|
||섬의개수|*|[https://www.acmicpc.net/problem/4963](https://www.acmicpc.net/problem/4963)|
||양|*|[https://www.acmicpc.net/problem/3184](https://www.acmicpc.net/problem/3184)|
|일반그래프|바이러스|*|[https://www.acmicpc.net/problem/2606](https://www.acmicpc.net/problem/2606)|
||경로찾기|bfs솔루션확인(다시풀기추천)|[https://www.acmicpc.net/problem/11403](https://www.acmicpc.net/problem/11403)|
||트리의부모찾기|*힌트(bfs,직접dfs품)|[https://www.acmicpc.net/problem/11725](https://www.acmicpc.net/problem/11725)|
| 응용2 | 물통 | 난이도(4),실버1 |[https://www.acmicpc.net/problem/2251](https://www.acmicpc.net/problem/2251)|
| 응용3 | 연구소 | 난이도(4.5), 삼성(dfs완전탐색후bfs) |[https://www.acmicpc.net/problem/14502](https://www.acmicpc.net/problem/14502)|
| 응용4 | 미로 탐색 | 난이도2 | [https://www.acmicpc.net/problem/2178](https://www.acmicpc.net/problem/2178)|
|추천문제||||
||나이트의 이동|*|[https://www.acmicpc.net/problem/7562](https://www.acmicpc.net/problem/7562)|
||촌수 계산|*|[https://www.acmicpc.net/problem/2644](https://www.acmicpc.net/problem/2644)|
||현명한 나이트|*(dist 최소거리문제)|[https://www.acmicpc.net/problem/18404](https://www.acmicpc.net/problem/18404)|
| 응용5 | 숨바꼭질 | 난이도3 | [https://www.acmicpc.net/problem/1697](https://www.acmicpc.net/problem/1697) | 
|추천문제||||
||케빈 베이컨의 6단계 법칙| 솔루션참고(bfs) |[https://www.acmicpc.net/problem/1389](https://www.acmicpc.net/problem/1389)|
||||[https://www.acmicpc.net/problem/5567](https://www.acmicpc.net/problem/5567)|
| 응용6 | 탈출(고슴도치) | 난이도4~5(삼성, 쉬운)| [https://www.acmicpc.net/problem/3055](https://www.acmicpc.net/problem/3055)|
|추천문제||||
||토마토||[https://www.acmicpc.net/problem/7569](https://www.acmicpc.net/problem/7569)|


 
// 물통 문제의 경우 그래프라는 언급이 없으므로 판단이 필요하여 어려움 
// 추천문제에서 직접 푼 경우 * 표시함
// 경로 찾기 - '플로이드 와샬' 알고리즘 필요 (211203 아니었음 .. bfs로 푸는데 행당 x~y번 노드로 가능한지 구해서 출력하는 방법을 이해 해야 했음)
[https://m.blog.naver.com/PostView.nhn?blogId=ndb796&logNo=221234427842&proxyReferer=https:%2F%2Fwww.google.com%2F](https://m.blog.naver.com/PostView.nhn?blogId=ndb796&logNo=221234427842&proxyReferer=https:%2F%2Fwww.google.com%2F)
// 케빈 베이컨의 6단계 법칙 - '플로이드 와샬' 알고리즘 필요 .. 정답은 구했는데 틀린걸로 표시됨 (211204 확인시 해당 알고리즘 말고 BFS로 로직 정렬하는게 필요했음!)

###### 응용3	- 연구소
- **'Multisource BFS'** 활용해서 문제 풀이함
- 모든 시작점을 전부 Queue에 넣은 상태로 BFS를 시작하면 됨
- **시간 복잡도는 O(V+E)가 유지됨**

## 그래프(Graph)란? (외우기)
- 자료구조로써 Graph = 정점(vertex) + 간선(edge)
  - 간선(Edge) → 무방향(양방향)/방향 + **가중치**
  - deg(x) = 정점 x의 차수(degree), 정점 x에 연결된 간선의 수 
    - ∑deg(x) = 모든 정점의 차수의 합 = 간선의 개수의 2배 ! ( 공식 알 것 !)
- Graph를 컴퓨터에 인식시키는 방식 (상황에 따라 선택사용) , 그래프를 저장하는 대표적인 두가지 방법
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
      - O(min(deg(A),deg(B)))     // 양방향성인 경우 , A가 1이고 B가 3일때 
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

```java
    //x를 갈 수 있다는 걸 알고 방문한 상태
    static void dfs(int start){
        
        //x를 방문했다고 표시 
        visit[start] = true; 
        sb.append(start).append(' ');

        //x에서 갈 수 있는 곳들을 작은 번호부터 모두 방문함
        for(int y : adj[start]){
            //y를 이미 갈 수 있다는걸 알면 굳이 갈 필요없다.
            if(visit[y]) continue;
            //y에서 갈 수 있는 곳을 확인한다 
            dfs(y);
        }
    }

```

#### 너비 우선탐색(BFS, Breadth First Search)
- Queue가 들고 있는 자료의 의미 
  - 방문이 가능한 정점들은 찾을때, Queue에 해당 점정을 넣는다.
    - Queue에 정점이 남았다 → 아직 방문 가능한 점이 남아있다. or 탐색 중이다. 
    - Queue가 비어있다. → 시작점에서 갈 수 있는 모든 점을 찾아냈다! or 탐색이 끝났다!
  - 인접 행렬 사용시 → O(V^2)
  - 인접 리스트 → O( deg(1) + deg(2) + .. + deg(V) ) = O(E)     

```java
    //start에서 시작해서 갈 수 있는 정점들을 모두 탐색하기 
    static void bfs(int start){
        Queue<Integer> que = new LinkedList<>();
        // start는 방문가능한 점이므로que에 넣어줌
        que.add(start); // 초기값 셋팅
        visit[start] = true;
        
        while(!que.isEmpty()){
            int x = que.poll(); // 모든 정점이 한번씩만 등장한다.O(V)
            sb.append(x).append(' ');

            for(int y : adj[x]){ // -----인접행렬O(V) / 인접리스트 O(deg(x))
                if(visit[y]) continue;

                que.add(y);
                visit[y] = true;  // --- visit 체크를 여기서 하지 않고, que.poll() 아래에 할 경우 동일한 Vertex(노드)가 들어가게 됨!
            }
        }
    }

```


#### '최소이동횟수','최단시간' 키워드
- 최소 "이동" 횟수와 관련된 것이기 대문에, 가중치에 대한개념이 없는 문제에서만 생기는 부가효과!
  - 동생을 찾을 수 있는 가장 빠른 시간이 몇 초 후 
  - 이동할 때 지나야 하는 **최소의 칸 수** 
  - 고슴도치가 안전하게 비버의굴로 이동하기 위해 **필요한 최소시간** 
- **때로는 그래프가 없는 문제에서 "정점"과 "간선"의 정의를 만들어서 그래프 문제로 접근해야함(중요!문제에는 그래프라고 설명안함)**