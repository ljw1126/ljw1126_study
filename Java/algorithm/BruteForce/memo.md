## 강의자료 
- **백준 사이트 정답 제출시 클래스명은 Main**으로 해야 동작함 ! 
- 깃허브에 올려둠 
[https://github.com/rhs0266/FastCampus/tree/main/](https://github.com/rhs0266/FastCampus/tree/main/)


## Brute Force(완전탐색)
- 문제를 해결하기 위해 확인해야 하는 모든 경우를 전부 탐색하는 방법 
- 그 중에서도 백 트래킹(Back-Tracking)을 통해야 하는 상황을 해결하기 ! 
  - 모든 코테 문제에서 기본적으로 접근해 봐야한다. 많은 연습 필요 ! 
  - 부분점수 얻기 좋음 
  - 전부 탐색하기에 시간 복잡도가 일반적으로 높다.

## 코테에 나오는 완전 탐색 종류 
- N 개 중에서 
  - 1) 중복을 허용해서 
  - 2) 중복없이
- M 개를 
  - A) 순서 있게 나열하기 
  - B) 고르기 

![Alt text](/images/fastcampus/brute_force시간복잡도.png) 

## 완전 탐색은 함수 정의가 50% 
- 아래와 같은 형태로 문제 풀이 wlsdsa
```java
    // Recurrence Function ( 재귀함수 )
    // 만약 M개를 전부 고름 => 조건에 맞는 탐색을 한 가지 성공한 것! 
    // 아음 => k번째부터 M번째 원소를 조건에 맞게 고르는 모든 방법을 시도한다.
    static void rec_func(int k){
        if(k == M + 1){
            // selected[1..M]배열이 새롭게 탐색된 결과
        }else{
            // 1~N까지 k번 원소로 한번씩 정하고,
            // 매번 k+1 번부터 M번 원소로 재귀호출해주기 
        }
    }

    public static void main(String[] args) {
        input();
        // 1번째 원소부터 M번째 원소를 조건에 맞게 고르는 모든 방법을 탐색해줘 
        rec_func();
        System.out.println(sb.toString());
    }

```
## 문제 링크 
|번호|명칭|난이도|주소|
|:------:|:--------:|:--------:|:--------------:|
| 문제1  |N과 M (3)|실버3|[https://www.acmicpc.net/problem/15651](https://www.acmicpc.net/problem/15651)|
| 문제2  |N과 M (1)|실버3|[https://www.acmicpc.net/problem/15649](https://www.acmicpc.net/problem/15649)|
| 문제3  |N과 M (4)|실버3|[https://www.acmicpc.net/problem/15652](https://www.acmicpc.net/problem/15652)|
| 문제4  |N과 M (2)|실버3|[https://www.acmicpc.net/problem/15650](https://www.acmicpc.net/problem/15650)|
| 응용1  |연산자 끼워넣기||[https://www.acmicpc.net/problem/14888](https://www.acmicpc.net/problem/14888)|
| 응용2  |N Queen||[https://www.acmicpc.net/problem/9663](https://www.acmicpc.net/problem/9663)|
| 응용3  |부분 수열의 합||[https://www.acmicpc.net/problem/1182](https://www.acmicpc.net/problem/1182)|
| 응용4 |암호 만들기||[https://www.acmicpc.net/problem/1759](https://www.acmicpc.net/problem/1759)|
| 응용5 |N과 M (9)|실버2|[https://www.acmicpc.net/problem/15663](https://www.acmicpc.net/problem/15663)|
| 연습1 |N과 M (5)|실버3|[https://www.acmicpc.net/problem/15654](https://www.acmicpc.net/problem/15654)|
| 연습2 |N과 M (6)|실버3|[https://www.acmicpc.net/problem/15655](https://www.acmicpc.net/problem/15655)|
| 연습3 |N과 M (7)|실버3|[https://www.acmicpc.net/problem/15656](https://www.acmicpc.net/problem/15656)|
| 연습4 |N과 M (8)|실버3|[https://www.acmicpc.net/problem/15657](https://www.acmicpc.net/problem/15657)|
| 연습5 |N과 M (10)|실버2|[https://www.acmicpc.net/problem/15664](https://www.acmicpc.net/problem/15664)|
| 연습6 |N과 M (11)|실버2|[https://www.acmicpc.net/problem/15665](https://www.acmicpc.net/problem/15665)|
| 연습7 |N과 M (12)|실버2|[https://www.acmicpc.net/problem/15666](https://www.acmicpc.net/problem/15666)|
| 연습8 |||[]()|




## 1번 문제(난이도2) : N과 M (3)
- N 개중 1) 중복을 허용해서 , M개를 A) 순서있게 나열하는 방법
- https://www.acmicpc.net/problem/15651
- N = 4, M = 3일때   (N = 1~4 , 이고 M은 자릿수 , 중복허용)

- 각 칸에 1~4까지 올수 있으므로 4*4*4 = 4^3 = 64 
- 시간 복잡도 : O(N^M) => 7^7 까지 가능하므로 약 82만 개
  - 1초에 1억번 연산 가능하다 하면 구현 가능여부 파악가능 
- 공간 복잡도 : O(M)    // M개 숫자를 저장하므로 

## 2번 문제(난이도2) : N과 M (1)
- N개중 2)중복없이 , M개를 A)순서있게 나열하기 
- https://www.acmicpc.net/problem/15649
- 11이나 22 같은 중복없이 풀이해야 함
- N = 4, M=3      // M 자릿수 , N은 1~4 라는 
- 시간복잡도 O(nPm) // 최대 8! = 40,320
- 공간복잡도 O(M)   // 자릿수가 3개니 

## 3번 문제(난이도2) : N과 M (4)
- N개 중 1) 중복을 허용해서 , M개를 B) 고르기 
- https://www.acmicpc.net/problem/15652
- 고른 수열이 비내림차순이어야 함 
  - 1 2 를 골랐으면 2 1 은 생략하고 다음 숫자 2 2 표현
- N=4, M=3 일때 
  - 4 * 4 * 4 = 4^3 보단 작다
  - 시간복잡도 : O(N^m) => 최대값이 8이므로 8^8 = 1677만보단 작다 
  - 공간복잡도 : O(M)  
  
## 4번 문제(난이도2) : N과 M (2)
- N개 중 2) 중복없이, M개를 B)고르기
- https://www.acmicpc.net/problem/15650
- N=4, M=3 
  - 수학의 조합(Combination)으로 표현가능 
  - 시간복잡도 : O(nCm) 
  - 공간복잡도 : O(M)


#### 참고 
[https://mathbang.net/545](https://mathbang.net/545) -- 순열(nPr)에 대해 

---------------------------------------------------
## 응용 문제 1. 연산자 끼워넣기 -- 14888 
https://www.acmicpc.net/problem/14888
- 출력 설명 중 
```
첫째 줄에 만들 수 있는 식의 결과의 최댓값을, 둘째 줄에는 최솟값을 출력한다. 
연산자를 어떻게 끼워넣어도 항상 -10억보다 크거나 같고, 10억보다 작거나 같은 결과가 나오는 입력만 주어진다. 
또한, 앞에서부터 계산했을 때, 중간에 계산되는 식의 결과도 항상 -10억보다 크거나 같고, 10억보다 작거나 같다.
```
- **int 범위 : -21억 ~ 21억**  이므로 **int형 변수**를 쓰면 된다 ! 
- 시간복잡도 계산 
  - **N-1개의 카드 중에서 중복없이(같은 카드는 한번 사용) N-1개를 순서있게 나열하기**
  - O(nPr)

- 심화 
  - 재귀 호출 완료 후 calculator() 함수 통해 결과값 구하는데, for문을 도는 부분 개선하려함 ( = 최적화하려 함 )
  - 그래서 재귀 호출시  매개변수 인자에 value를 추가하여 바로바로 계산하도록 하도록 수정하여 개선함 ( = 최적화함 ) 

## 응용 문제2. N Queen -- 9663
https://www.acmicpc.net/problem/9663
- 유명한 백트래킹 문제 
- 문제 접근   
  - N = 14 일때 정답? -> 21억을 넘을지도 모른다
  - 일단 int로 정하고 N=14를 입력으로 넣어보고 확인하기
  - N개 중에서 중복을 허용해서 N개를 순서대로 나열하는 모든 경우 탐색하기
  - 시간복잡도 : O(N^M) 이므로 14^14 > 10^16이라 시간초과 발생가능 
- 첫번째 풀이 방식의 문제점 
  - validechark() 통해 불필요한 탐색을 다함으로 시간초과 발생가능!
  - 그래서 애초에 열을 검사할 때 배치가능여부 파악하도록 변경 
  
## 응용 문제3. 부분 수열의 합 -- 1182
https://www.acmicpc.net/problem/1182
- 부분 수열 : 수열의 일부 항을 선택해서 원래 순서대로 나열 
- 진 부분 수열(=아무것도 안고른 경우 뺀 경우)들 중에서 합이 정확히 S가 되는 경우 ( 목표 S = 0 일때) 
- 복잡도 계산 
  -  N <= 20 
    - 부분 수열의 개수 상한 : 2^20 <= 1,048,576 -- 정답변수는 int를 쓰면 된다!
  - 목표값 |s| <= 1,000,000
    - 부분 수열의 합 상한 : 20^1,000,000 -- 합을 기록하는 변수로 int형 쓰면 된다!
  - |Ai| <= 1,000,000
  - 1~N번 원소에 대해 
    - 0: 부분 수열에 포함 x , 1: 부분수열에 포함 o
    - 시간복잡도 O(N^M) = O(2^20)