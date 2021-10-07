## 상태공간트리(State Space Tree)
- 상태공간 : 해답을 탐색하기 위한 탐색 공간 
- 상태공간트리 : 탐색 공간을 트리 형태의 구조로 '암묵적'으로 해석 

## 백트래킹 기법 
- 상태공간트리를 깊이우선탐색으로 탐색 // 이때 전체 탐색(단순)하면 브루트 포스법이라 함
- 방문 중인 노드에서 더 하위 노드로 가면 해답이 없을 경우 
  - 해당 노드의 하위 트리를 방문하지 않고 부모 노드로 되돌아 감(backtrack)
- **유망함(Promising)**
  - 방문 중인 노드에서 하위 노드가 해답 발견할 가능성이 있으면 유망 (promising)
  - 하위 노드에서 해답을 발견할 가능성이 없으면 유망하지 않음 (non promising)
- **가지치기(pruning)**
  - 백트래킹 : 상태공간트리를 DFS로 탐색 
  - 방문중인 노드가 유망(promising)한지 체크
  - 만약 유망하지 않으면, 부모 노드로 되돌아감 
  - 유망하지 않으면 하위 트리를 가지치기함 
    - 가지치기한 상태 = 방문한 노드의 방문하지 않는 하위트리(pruned state)
- **백트래킹 알고리즘의 구현**
  - 상태공간트리를 실제로 구현할 필요는 없음
  - 현재 조사중인 가지의 값에 대해 추적만 하면 됨
  - 상태공간트리는 암묵적으로 존재한다고 이해하면 됨 
  
  
#### N Queen 실행 내용 출력 
```
dfsFunc(4 , 0 , []);
N : 4, currentRow : 0, currentCandidate : []
isAvailable=========================================
candidate : []
currentRow : 0
currentCol : 0     // for문 실행 x 
isAvailable() ======= true


dfsFunc(4 , 1 , [0]);
N : 4, currentRow : 1, currentCandidate : [0]
isAvailable=========================================
candidate : [0]
currentRow : 1
currentCol : 0
isAvailable() ======= false
isAvailable=========================================
candidate : [0]
currentRow : 1
currentCol : 1
isAvailable() ======= false
isAvailable=========================================
candidate : [0]
currentRow : 1
currentCol : 2
isAvailable() ======= true


N : 4, currentRow : 2, currentCandidate : [0, 2]
isAvailable=========================================
candidate : [0, 2]
currentRow : 2
currentCol : 0
isAvailable() ======= false
isAvailable=========================================
candidate : [0, 2]
currentRow : 2
currentCol : 1
isAvailable() ======= false
isAvailable=========================================
candidate : [0, 2]
currentRow : 2
currentCol : 2
isAvailable() ======= false
isAvailable=========================================
candidate : [0, 2]
currentRow : 2
currentCol : 3
isAvailable() ======= false
isAvailable=========================================
candidate : [0]
currentRow : 1
currentCol : 3
isAvailable() ======= true


N : 4, currentRow : 2, currentCandidate : [0, 3]
isAvailable=========================================
candidate : [0, 3]
currentRow : 2
currentCol : 0
isAvailable() ======= false
isAvailable=========================================
candidate : [0, 3]
currentRow : 2
currentCol : 1
isAvailable() ======= true


N : 4, currentRow : 3, currentCandidate : [0, 3, 1]
isAvailable=========================================
candidate : [0, 3, 1]
currentRow : 3
currentCol : 0
isAvailable() ======= false
isAvailable=========================================
candidate : [0, 3, 1]
currentRow : 3
currentCol : 1
isAvailable() ======= false
isAvailable=========================================
candidate : [0, 3, 1]
currentRow : 3
currentCol : 2
isAvailable() ======= false
isAvailable=========================================
candidate : [0, 3, 1]
currentRow : 3
currentCol : 3
isAvailable() ======= false
isAvailable=========================================
candidate : [0, 3]
currentRow : 2
currentCol : 2
isAvailable() ======= false
isAvailable=========================================
candidate : [0, 3]
currentRow : 2
currentCol : 3
isAvailable() ======= false
isAvailable=========================================     // 0 번행까지 올라간 다음 idx = 1 실행 
candidate : []
currentRow : 0
currentCol : 1
isAvailable() ======= true


N : 4, currentRow : 1, currentCandidate : [1]
isAvailable=========================================
candidate : [1]
currentRow : 1
currentCol : 0
isAvailable() ======= false
isAvailable=========================================
candidate : [1]
currentRow : 1
currentCol : 1
isAvailable() ======= false
isAvailable=========================================
candidate : [1]
currentRow : 1
currentCol : 2
isAvailable() ======= false
isAvailable=========================================
candidate : [1]
currentRow : 1
currentCol : 3
isAvailable() ======= true


N : 4, currentRow : 2, currentCandidate : [1, 3]
isAvailable=========================================
candidate : [1, 3]
currentRow : 2
currentCol : 0
isAvailable() ======= true


N : 4, currentRow : 3, currentCandidate : [1, 3, 0]
isAvailable=========================================
candidate : [1, 3, 0]
currentRow : 3
currentCol : 0
isAvailable() ======= false
isAvailable=========================================
candidate : [1, 3, 0]
currentRow : 3
currentCol : 1
isAvailable() ======= false
isAvailable=========================================
candidate : [1, 3, 0]
currentRow : 3
currentCol : 2
isAvailable() ======= true


N : 4, currentRow : 4, currentCandidate : [1, 3, 0, 2]
결과 =====================
[1, 3, 0, 2]

isAvailable=========================================
candidate : [1, 3, 0] 
currentRow : 3
currentCol : 3 // 얘가 어디서 나오는거지??
isAvailable() ======= false
isAvailable=========================================
candidate : [1, 3]
currentRow : 2
currentCol : 1
isAvailable() ======= false
isAvailable=========================================
candidate : [1, 3]
currentRow : 2
currentCol : 2
isAvailable() ======= false
isAvailable=========================================
candidate : [1, 3]
currentRow : 2
currentCol : 3
isAvailable() ======= false
isAvailable=========================================  // 부모노드(0번행) 까지 올라간 다음 idx = 2 루프 실행 
candidate : []
currentRow : 0
currentCol : 2
isAvailable() ======= true


N : 4, currentRow : 1, currentCandidate : [2]
isAvailable=========================================
candidate : [2]
currentRow : 1
currentCol : 0
isAvailable() ======= true


N : 4, currentRow : 2, currentCandidate : [2, 0]
isAvailable=========================================
candidate : [2, 0]
currentRow : 2
currentCol : 0
isAvailable() ======= false
isAvailable=========================================
candidate : [2, 0]
currentRow : 2
currentCol : 1
isAvailable() ======= false
isAvailable=========================================
candidate : [2, 0]
currentRow : 2
currentCol : 2
isAvailable() ======= false
isAvailable=========================================
candidate : [2, 0]
currentRow : 2
currentCol : 3
isAvailable() ======= true


N : 4, currentRow : 3, currentCandidate : [2, 0, 3]
isAvailable=========================================
candidate : [2, 0, 3]
currentRow : 3
currentCol : 0
isAvailable() ======= false
isAvailable=========================================
candidate : [2, 0, 3]
currentRow : 3
currentCol : 1
isAvailable() ======= true


N : 4, currentRow : 4, currentCandidate : [2, 0, 3, 1]
결과 =====================
[2, 0, 3, 1]


isAvailable=========================================
candidate : [2, 0, 3]
currentRow : 3
currentCol : 2
isAvailable() ======= false
isAvailable=========================================
candidate : [2, 0, 3]
currentRow : 3
currentCol : 3
isAvailable() ======= false
isAvailable=========================================
candidate : [2] // 왜 이렇게 되지?? 종이에 해답을 찾음 
currentRow : 1
currentCol : 1
isAvailable() ======= false
isAvailable=========================================
candidate : [2]
currentRow : 1
currentCol : 2
isAvailable() ======= false
isAvailable=========================================
candidate : [2]
currentRow : 1
currentCol : 3
isAvailable() ======= false
isAvailable=========================================
candidate : []
currentRow : 0
currentCol : 3
isAvailable() ======= true


N : 4, currentRow : 1, currentCandidate : [3]
isAvailable=========================================
candidate : [3]
currentRow : 1
currentCol : 0
isAvailable() ======= true


N : 4, currentRow : 2, currentCandidate : [3, 0]
isAvailable=========================================
candidate : [3, 0]
currentRow : 2
currentCol : 0
isAvailable() ======= false
isAvailable=========================================
candidate : [3, 0]
currentRow : 2
currentCol : 1
isAvailable() ======= false
isAvailable=========================================
candidate : [3, 0]
currentRow : 2
currentCol : 2
isAvailable() ======= true


N : 4, currentRow : 3, currentCandidate : [3, 0, 2]
isAvailable=========================================
candidate : [3, 0, 2]
currentRow : 3
currentCol : 0
isAvailable() ======= false
isAvailable=========================================
candidate : [3, 0, 2]
currentRow : 3
currentCol : 1
isAvailable() ======= false
isAvailable=========================================
candidate : [3, 0, 2]
currentRow : 3
currentCol : 2
isAvailable() ======= false
isAvailable=========================================
candidate : [3, 0, 2]
currentRow : 3
currentCol : 3
isAvailable() ======= false
isAvailable=========================================
candidate : [3, 0]
currentRow : 2
currentCol : 3
isAvailable() ======= false
isAvailable=========================================
candidate : [3]
currentRow : 1
currentCol : 1
isAvailable() ======= true


N : 4, currentRow : 2, currentCandidate : [3, 1]
isAvailable=========================================
candidate : [3, 1]
currentRow : 2
currentCol : 0
isAvailable() ======= false
isAvailable=========================================
candidate : [3, 1]
currentRow : 2
currentCol : 1
isAvailable() ======= false
isAvailable=========================================
candidate : [3, 1]
currentRow : 2
currentCol : 2
isAvailable() ======= false
isAvailable=========================================
candidate : [3, 1]
currentRow : 2
currentCol : 3
isAvailable() ======= false
isAvailable=========================================
candidate : [3]
currentRow : 1
currentCol : 2
isAvailable() ======= false
isAvailable=========================================
candidate : [3]
currentRow : 1
currentCol : 3
isAvailable() ======= false

```