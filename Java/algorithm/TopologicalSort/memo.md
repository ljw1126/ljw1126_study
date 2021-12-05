## 강의자료 
- **백준 사이트 정답 제출시 클래스명은 Main**으로 해야 동작함 ! [https://www.acmicpc.net/problem/문제번호](https://www.acmicpc.net/problem/문제번호)
- 깃허브에 올려둠 
[https://github.com/rhs0266/FastCampus/tree/main/](https://github.com/rhs0266/FastCampus/tree/main/)


## 위상 정렬(Topological Sort)
- Directed Acyclic Graph(DAG)
- 단방향, 사이클 이루지 않은, 그래프 
- 아이디어 : Q: "제일 먼저 올 수 있는 정점은?" / A:"들어오는 간선"이 없는 정점(Indegree == 0)
- [정리] 
  - 1. 정점들의 Indegree, Indeg[1..N] 계산하기
  - 2. 들어오는 간선이 0개인(Indeg[i] == 0) 정점들을 찾아서 자료구조 D에 넣기 
  - 3. D가 빌 때까지  
    - 3-1. D에서 원소 X를 꺼내서 **'정렬'** 시키기
    - 3-2. Graph 에서 정점X **'제거'** 하기
    - 3-3. **'새롭게 정렬 가능한 점'** 을 찾아서 D에 넣기 
  - **O(|V| + |E|)**


## 문제 링크 
|번호|명칭|난이도|주소|
|:------:|:--------:|:--------:|:--------------:|
|문제1| 줄 세우기 |난이도 3|[https://www.acmicpc.net/problem/2252](https://www.acmicpc.net/problem/2252)|
|추천문제||||
|추천1|음악 프로그램|블로그참고(거의 기본)|[https://www.acmicpc.net/problem/2623](https://www.acmicpc.net/problem/2623)|
|추천2|Strahler 순서|솔루션확인|[https://www.acmicpc.net/problem/9470](https://www.acmicpc.net/problem/9470)|
|추천3|영우는 사기꾼?|솔루션확인|[https://www.acmicpc.net/problem/14676](https://www.acmicpc.net/problem/14676)|
|||||
|문제2|ACM Craft|난이도3(계산용배열 초기화.. 혼자 못품)|[https://www.acmicpc.net/problem/1005](https://www.acmicpc.net/problem/1005)|
|추천문제||||
|추천4|게임 개발||[https://www.acmicpc.net/problem/1516](https://www.acmicpc.net/problem/1516)|
|추천5|작업||[https://www.acmicpc.net/problem/2056](https://www.acmicpc.net/problem/2056)|
|추천6|장난감 조립||[https://www.acmicpc.net/problem/2637](https://www.acmicpc.net/problem/2637)|

#### 추천 문제 
- 추천1. 음악 프로그램
  - 동일한 위상정렬 문제인데, **사이클이 발생하는 경우를 파악해서 처리하는걸 이해 못함** 
  - 아래 이미지에서 사이클 발생시 1,6 을 pop해서 indegree[자식]--; 하더라도 0이 되는게 없으니 종료되버림..

![Alt text](/images/fastcampus/ex2623_음악프로그램_위상정렬.png) 

- 추천2. Strahler 순서 (골드3)
  - **문제 조건 말이 이상함**
  - 뒤늦게 이해함 .. 
    - "강의 근원인 노드의 순서는 1이다." == 들어오는 노드가 없는 노드의 경우 순서가 1
    - "나머지 노드는 그 노드로 들어오는 강의 순서 중 가장 큰 값을 i라고 했을 때, 들어오는 모든 강 중에서 Strahler 순서가 i인 강이 1개이면 순서는 i, 2개 이상이면 순서는 i+1이다." == 해당 노드에 들어오는 값 중에 가장 순서가 큰 i가 1개 이면 i 이고 , 2개 이상이면 i+1 로 순서 지정 

![Alt text](/images/fastcampus/ex9470_위상정렬_강의순서.png) 

- 추천3. 영우는 사기꾼?
  - 솔루션 풀이 확인함 
  - satification , cnt, indegree 배열이 필요.. 위상정렬은 필요없는듯..

#### 문제1. 줄 세우기 
[https://www.acmicpc.net/problem/2252](https://www.acmicpc.net/problem/2252)
- 1 ⪯ 학생수(N) ⪯ 32,000    
- 1 ⪯ 키 관계 ⪯ 100,000 
- 학생들 간에 키(위상) 관계가 주어지고, 이에 맞게 줄을 세운다.
- Graph를 정의해보고 위상 정렬을 통해 문제를 해결해보자! 
  - 정점(V) := i번 학생이 곧 i번 정점 
  - 간선(E) := x번 학생이 y번 학생보다 먼저 서야 한다.
- Graph를 만들고 나면 위상정렬(Topological Sort) 시작 => 인접 리스트를 쓴다면 O(V+E)

#### 문제2. ACM Craft ----- 테스트 케이스가 존재하는 문제(배열 초기화 조심!)
[https://www.acmicpc.net/problem/1005](https://www.acmicpc.net/problem/1005)
- 건물의 개수 N (2 ≤ N ≤ 1000) 
- 건물간의 건설규칙 총 수 K (1 ≤ K ≤ 100,000)
- 최대치 계산 
  - 가장 오랜시간을 가정하면 **10^3 * 10^5 = 10^8 이므로 Integer범위로 충분**하다.
- 접근 - 각 건물을 완성하는 시간은?
  - T(done)[X] = max(T(done)[X의 선행 작업] + T[X])   
- 전제 조건 
  - T(done)[X]를 계산하기 위해서는 X의 선행 작업들에 대해서 T(done)이 먼저 계산되어야 한다!
  - T(done)배열을 위상 정렬 순서로 계산하면 된다.
- 같은 레벨에 있는 노드의 경우 해당 노드 전체가 처리 되기 전까지 넘어가지 못한다 == 이전 노드레벨의 max 값이 곧 다음 노드 레벨에 더해지게 된다.