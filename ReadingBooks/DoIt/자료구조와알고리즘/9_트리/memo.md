## 9. 트리 

#### 9-1. 트리구조 
```
# 용어 
1. 트리 (tree)
    - 데이터 사이의 계층 관계를 표현 
    - 노드(node)와 가지(edge)로 구성 

2. 루트(root)
    - 트리의 가장 위쪽에 있는 노드 말함 

3. 리프(leaf)
    - 가장 아래쪽에 있는 노드를 말함 
    - 리프는 단말노드(terminal node), 외부노드(external node)라고 함 
    - 물리적으로 가장 밑에 위치한다는 의미가 아니라 더 이상 뻗어 나가는 자식 노드가 없는 마지막 노드 

4. 비단말 노드 (non-terminal node)
    - 리프는 제외한 노드(루트 포함)를 말함 
    - 내부 노드(internal node) 라고도 함

5. 자식(child)
    - 어떤 노드와 가지가 연결되었을때 아래쪽 노드를 자식이라 함

6. 부모(parent)
    - 어떤 노드와 가지가 연결되었을 때 가장 위쪽에 있는 노드를 부모라고 함

7. 형재(sibling)
    - 부모가 같은 노드를 형제라 함 

8. 조상(ancestor)
    - 어떤 노드에서 위쪽으로 가지를 따라가면 만나는 모든 노드를 조상이라 함 

9. 자손(descendant)
    - 어떤 노드에서 아래쪽으로 가지를 따라가면 만나는 모든 노드를 자손이라 함 

10. 레벨(level)
    - 루트에서 얼마나 멀리 떨어져 있는지를 나타내는 것 
      > 루트의 경우 0 레벨이고 아래로 1씩 증가 

11. 차수(degree)
    - 각 노드가 갖는 자식의 수

12. 높이(height)
    - 루트(0)에서 가장 멀리 있는 리프까지의 거리 

13. 서브트리(subtree)
    - 어떤 노드를 루트로 하고, 그 자손으로 구성된 트리를 서브트리라 함 

14. 빈 트리(None tree)
    - 노드와 가지가 전혀 없는 트리를 빈 트리, 널 트리(null tree) 라고 함
```
- **모든 노드의 차수가 n 이하인 트리를 n진 트리라고 함** 

```
# 순서트리와 무순서 트리 (p379)
    - 형제 노드의 순서 관계가 있으면 순서 트리(ordered tree) 
    - 구별하지 않으면 무순서 트리 (unordered tree)

# 순서 트리의 검색 
    1. 너비 우선 검색(breadth-first search)  // p379 
        - 폭 우선 검색, 가로 검색, 수평 검색 이라고도 함 
        - 낮은 레벨 부터 왼쪽에서 오른쪽으로 검색하고, 한 레벨에서 검색을 마치면 다음 레벨로 내려가는 방식
          // 0 레벨 root 부터 

    2. 깊이 우선 검색(depth-first search)   // p380
        - 세로 검색, 수직 검색이라고도 함 
        - 리프에 도달할 때까지 아래쪽으로 내려가면서 검색하는 것을 우선으로 하는 방법 
        - 리프에 도달해서 더 이상 검색할 곳이 없으면 일단 부모 노드로 돌아가고 그 뒤 자식 노드로 내려감 
        
        2-1. 전위 순회(preorder)
            노드 방문 -> 왼쪽 자식 -> 오른쪽 자식
            >> A - B - D - H - E - I - J - C - F - K - L - G 
        2-2. 중위 순회(inorder)
            왼쪽 자식 -> 노드 방문 -> 오른쪽 자식 
            >> H - D - B - I - E - J - A - K - F - L - C - G
        2-3. 후위 순회(postorder)
            왼쪽 자식 -> 오른쪽 자식 -> 노드 방문
            >> H - D - I - J - E - B - K - L - F - G - C - A

```

<br/>

#### 9-2. 이진 트리와 이진 검색 트리 (p382)
```
# 이진 검색 트리의 노드 조건
    - 왼쪽 서브트리의 노드의 키 값은 자신의 노드 키값보다 작아야함 
    - 오른쪽 서브 트리의 노드의 키 값은 자신의 노드 키값보다 커야함

* 이진 검색 트리를 중위 순회의 깊이 우선 검색으로 스캔하면 다음과 같이 노드의 키값을 오름차순으로 얻을 수 있음 
* 이진 검색 트리 특징 
    - 구조 단순 
    - 중위 순회의 깊이 우선 검색을 통하여 노드값을 오름차순으로 얻을 수 있음 
    - 이진 검색과 비슷한 방식으로 아주 빠르게 검색가능
    - 노드 삽입하기 쉬움 
```
- 손으로 그려보는게 그나마 이해가 됨 
- remove() 이해하는게 중요 
  - 자식 서브트리의 루트 노드 삭제시 보통 하위 왼쪽 노드 > 그 하위 왼쪽 노드의  맨 끝 오른쪽 자식 과 swap을 함  
