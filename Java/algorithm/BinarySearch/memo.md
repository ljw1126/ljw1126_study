## 강의자료 
- **백준 사이트 정답 제출시 클래스명은 Main**으로 해야 동작함 ! [https://www.acmicpc.net/problem/문제번호](https://www.acmicpc.net/problem/문제번호)
- 깃허브에 올려둠 
[https://github.com/rhs0266/FastCampus/tree/main/](https://github.com/rhs0266/FastCampus/tree/main/)

## 이분/이진 탐색(Binary Search)
- **정렬이 보장되는 배열**에서 기준 x를 가지고 **범위를 '이분'**하면서 탐색하는 방법
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


## 문제 링크 
|번호|명칭|난이도|주소|
|:------:|:--------:|:--------:|:--------------:|
|문제1||먹을 것인가 먹힐 것인가|[https://www.acmicpc.net/problem/7795](https://www.acmicpc.net/problem/7795)|