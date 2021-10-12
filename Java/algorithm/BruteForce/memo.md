## 강의자료 
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
|번호|주소|
|:------:|:--------------:|
|문제1|[https://www.acmicpc.net/problem/15651](https://www.acmicpc.net/problem/15651)|
|문제2|[https://www.acmicpc.net/problem/15649](https://www.acmicpc.net/problem/15649)|
|문제3|[https://www.acmicpc.net/problem/15652](https://www.acmicpc.net/problem/15652)|
|문제4|[https://www.acmicpc.net/problem/15650](https://www.acmicpc.net/problem/15650)|


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