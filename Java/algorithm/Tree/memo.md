## 강의자료 
- **백준 사이트 정답 제출시 클래스명은 Main**으로 해야 동작함 ! [https://www.acmicpc.net/problem/문제번호](https://www.acmicpc.net/problem/문제번호)
- 깃허브에 올려둠 
[https://github.com/rhs0266/FastCampus/tree/main/](https://github.com/rhs0266/FastCampus/tree/main/)


## Tree 란 ( 외우기 )
- Tree = V + E + 아래 특성 중 2개이상 만족할때 
  - **모두가 연결**되어 있는 그래프 
    - 어떤 두 점을 골라도 간선을 타고 사이를 이동 가능 
  - **사이클이 존재하지 않음** 
  - 정점 개수 | V | = 간선 개수 | E | + 1      
    - **정점 개수가 간선개수 보다 하나 더 많아아 함**

#### Rooted Tree 
- 구성요소
  - Node
  - Root
  - Depth
  - Parent, Child, Ancestor(조상), Sibling(형제) 
    - Ancestor는 자신과 Root 사이의 관계
  - Leaf Node(단말 노드)

#### 트리(Tree) 문제의 keyword 
- 즉 모든 두 정점 사이에 이들을 잇는 경로가 존재하면서 사이클이 존재하지 않는 경우만 고려한다. 
- 즉 마을과 마을 사이를 직접 잇는 N-1 개의 길이 있으며, 모든 마을은 연결되어 있다. 
- 문제는 일반적인 그래프가 아니라 **트리**(연결되어 있고 사이클이 없는 그래프)라고 명시하는 경우
- 그래프를 저장하는 대표적인 두가지 방법 
  - 인접행렬(Adjacency Matrix) : 사용안함
  - 인접리스트(Adjacency List) : 대부분 트리는 요걸 씀 

|         | 인접행렬  |인접 리스트|
|:------:|:--------:|:--------:|
|A와 B를 잇는 간선 존재 여부 확인| **O(1)** | O(min(deg(A),deg(B))) |
|A와 연결된 모든 정점 확인| O(\|V\|) | **O(deg(A))** |
|공간 복잡도| O(\|V\|^2) | **O(\|E\|)** |
// V가 10만 개면 인접행렬은 100억이 되는데 , 인접리스트는 간선 수(9만9999) 만 되니 공간복잡도 면에서 인접리스트를 상용함


## 문제 링크 
|번호|명칭|난이도|주소|
|:------:|:--------:|:--------:|:--------------:|
|문제11725|트리의 부모 찾기|2(그래프에서품)|[https://www.acmicpc.net/problem/11725](https://www.acmicpc.net/problem/11725)|
|추천문제||||
|1991|트리순회|솔루션확인|[https://www.acmicpc.net/problem/1991](https://www.acmicpc.net/problem/1991)|
|5639|이진검색트리||[https://www.acmicpc.net/problem/5639](https://www.acmicpc.net/problem/5639)|
|15900|나무탈출||[https://www.acmicpc.net/problem/15900](https://www.acmicpc.net/problem/15900)|
|20364|부동산다툼||[https://www.acmicpc.net/problem/20364](https://www.acmicpc.net/problem/20364)|
|3584|가장 가까운 공통 조상||[https://www.acmicpc.net/problem/3584](https://www.acmicpc.net/problem/3584)|
|1240|노드 사이의 거리||[https://www.acmicpc.net/problem/1240](https://www.acmicpc.net/problem/1240)|
|9489|사촌||[https://www.acmicpc.net/problem/9489](tps://www.acmicpc.net/problem/9489)|
|문제1068|트리|2|[https://www.acmicpc.net/problem/1068](https://www.acmicpc.net/problem/1068)|
|추천문제||||
|15681|트리와 쿼리||[https://www.acmicpc.net/problem/15681](https://www.acmicpc.net/problem/15681)|
|14267|회사 문화1|*해설보고수정|[https://www.acmicpc.net/problem/14267](https://www.acmicpc.net/problem/14267)|

// 추천 문제 어려움 .. 트리 순회 .. 
#### 추천문제 
- 추천문제1 트리 순회 ( DFS 문제 , 데이터 표현이 어려움 )
  - 이거는 입력 값이 문자열로 주어지니 .. (char)아스키 코드 -> int index 값 , int index 값 -> 아스키 코드(char) 변환하는게 처음이었음
  - (int)(A - 'A') => 0 
  - (char)(0 + 'A') => 'A'
  - int[] child = new int[30][2] 에 트리 입력값을 표현, ('.'은 -1)
- 추천문제2 5639. 이진검색트리 ( DFS 문제 )
  - 솔루션 방법은 추후에 공부예정 
  - 블로그 참고) BST 검색 트리용 클래스 만들어서 , 이진 트리 구성 후 전위 순회, 중위 순회, 후위 순위 다 해봄 (간단)
- 추천문제3 15900. 나무탈출 ( DFS 문제 )
  - 리프노드 합이 홀이냐 짝이냐 구하는거 
  - 1이 루트라고 했기때문에 DFS를 돌때 1이 아니고, 인접리스트 사이즈가 1인 경우 리프노드

- 추천문제4(직접추가)
  - 상근이의 여행 - 실버 4
  - https://www.acmicpc.net/problem/9372

- 추천문제5(직접추가, 직접품) 
  - 트리와 쿼리 - 골드 5 // 서브트리 , 부모노드 구하는 거에 대한 설명이 문제 아래에 있어서 좋은 문제 
  - https://www.acmicpc.net/problem/15681

- 추천문제 6  // 직접 품 
  - 가장 가까운 공통 조상 - 골드 4 
  - https://www.acmicpc.net/problem/3584

- 추천문제7  // 직접품
  - 노드 사이의 거리 - 골드 5 
  - https://www.acmicpc.net/problem/1240 


#### 문제1. 트리의 부모찾기(11725)      --- 10/21 bfs로 문제를 풀었었음
- 1. 인접리스트로 저장하기
- 2. Root 말고는 아무것도 정답을 구하지 못한 상태로 시작
- 3. 정점 X가 parent를 안다면 자신의 자식Children을 찾을 수 있다. 
  - 어떻게? 연결된 것들 중 Parent를 제외한 모든 것들!
- 4. Root부터 차례대로 문제를 해결해보자
- **트리는 DFS로 문제를 해결하는게 쉽단다 ( BFS 사용해도 시간 복잡도는 O(V+E) )**

#### 문제2. 트리 (1068) --- 동적프로그래밍 관련해서 중요하다함
- Root를 시작으로 하는 그래프 탐색문제 
- 탐색알고리즘 : BFS or DFS ( **DFS로 풀이 함** )
  - 인접 리스트를 쓴다면 **O(V+E)**
  - DFS가 매우 쉽게 구현할 수 있다. 
- 단말 노드의 개수 ≦ 전체 정점의 개수 ≦ N
  - Integer 범위 안에 들어온다
- 접근
  - 1. 정점X가 지워진다.
    - 그래프에서 정점이 사라진다?
    - 정점과 이어진 간선들이 모두 사라진다.
    - 정점 X의 부모에서 X로 가는 간선을 **삭제 or 무시** 하자 !
  - 2. 트리의 단말 노드(leaf node)의 개수?
    - 트리 = root 노드와 연결된 정점들의 그래프 
    - **트리의 단말노드 = root 노드에서 탐색할 수 있는 단말 노드의 개수**
    - root를 시작점으로 하는 그래프 탐색알고리즘 BFS or DFS 활용 !
- **새로운 용어 : subtree**
  - Root tree에서 어떤 정점X의 Subtree란?
    - X와 그의 모든 자손들을 포함하는 트리 -> X가 새로운 Root가 된다.
- (point)트리에서의 **큰 문제**와 **작은 문제** 
  - 큰 문제 = 트리 안에 있는 단말 노드의 개수 
  - 작은 문제 = root의 자식 노드들의 subtree 안에 있는 단말 노드의 개수 
- 접근3 . 단말 노드의 개수를 세는 법
  - leaf[x] = x를 root로 하는 subtree에 있는 단말 노드의 개수 
    - x가 단말 노드인 경우 => leaf[x] = 1      // 그림이랑 설명 봐야 함 
    - 아닌 경우
      - x의 자식들에 대해 leaf를 먼저 계산 후 다 더해주면 됨 
- leaf[x]를 계산하는 방법 (강의 26분)
  - root에서 dfs를 한다면?
    - 어떤 노드 X에서 자식 노드 Y에 대한 탐색을 끝내고 돌아오면 
    - leaf[Y]값이 계산되어 왔을테니, leaf[X]에 leaf[Y] 를 누적해주면 된다. 
  - **DFS(x)를 구현한다면 x의 subtree에 대해 leaf[x]를 계산해주는 함수가 될 것이다!** 


##### 참고  
https://minhamina.tistory.com/80         -- 삼성sw 합격자 블로그 , 트리 순회 구현에 대해