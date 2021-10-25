package algorithm.BinarySearch;

import java.util.*;
import java.io.*;
/*
    [공유기 설치]
    https://www.acmicpc.net/problem/2110

    - 문제가 말이 굉장히 이상함 ( https://devlog-wjdrbs96.tistory.com/267 ) 
    - (Point)임의의 거리를 두었을때 C개의 공유기를 설치가능한가? Yes/No 
      - C개를 설치가능한 최대치 거리를 구함

      - [최대치 계산]
        - 제일 멀리 설치해보면 정답은 10억 이하 => Integer 로 가능 !
        - [키워드]
        - C개의 공유기를 N개의 집에 적당히 설치해서, 가장 인접한 **두 공유기 사이의 거리를 최대로 하는 프로그램**을 작성하시오.
        - 원래 문제 : C개의 공유기를 설치했을때, 최대 인접거리(D)는 얼마인가?
        - 뒤집은 문제 : 어떤 거리(D)만큼은 거리를 둘때, 공유기 C 개를 설치할 수 있는가? Yes/No
        - Yes가 나온 거리 중에 가장 큰값이 정답 
        - 그리트 기법으로 생각해서 왼쪽 부터 설치 !
            - **정렬 필요 !**
            - O(N)
        - 원래 문제 : 뒤집은 문제를 모든 D마다 (1~10억)해보면 마지막 yes가 정답
        - 시간복잡도 : O(뒤집은 문제 * log 10억) = O(N*logN) = N * 30    // log10억이 29정도, log 30억 31정도 
    - [정리]
    - 1. 주어진 집들을 정렬하기 => O(N log N)        --- skip 하면 안됨! 입력값이 정렬되어서 오는 보장 x 
    - 2. D를 정해서 결정 문제 한번 풀기 => O(N)
    - 3. 정답의 범위를 이분 탐색하면서 풀기 => O(logX)번 반복할 것 
    - 4. 총 시간 복잡도 : O(NlogN + NlogX)      // 이때 X는 10억에 해당 
*/
public class ex4_2110practice {
    static FastReader scan = new FastReader();
    static StringBuilder sb = new StringBuilder();

    static int N, C;
    static int[] A;

    static void input() {
        N = scan.nextInt();
        C = scan.nextInt();
        A = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            A[i] = scan.nextInt();
        }
    }

    static boolean determination(int D) {
        // D 만큼의 거리 차이를 둔다면 C 개 만큼의 공유기를 설치할 수 있는가?
        // 제일 왼쪽 집부터 가능한 많이 설치해보자!
        // D 만큼의 거리를 두면서 최대로 설치한 개수와 C 를 비교하자.
        int cnt = 1, last = A[1];
        for(int i=2 ; i <= N; i++){
            if(A[i] - last >= D){
                cnt++;
                last = A[i];
            }
        }
        return cnt >= C;
    }

    static void pro() {
        // determination을 빠르게 하기 위해서 정렬해주자.
        Arrays.sort(A, 1, N+1);
        
        int L = 1, R = 1000000000, ans = 0;
        // [L ... R] 범위 안에 정답이 존재한다!
        // 이분 탐색과 determination 문제를 이용해서 answer를 빠르게 구하자!
        while(L<=R){
            int mid = (L+R)/2;
            if(determination(mid)){
                ans = mid; 
                L = mid +1;
            }else{
                R = mid -1;
            }
        }

        System.out.println(ans);
    }

    public static void main(String[] args) {
        input();
        pro();
    }


    static class FastReader {
        BufferedReader br;
        StringTokenizer st;

        public FastReader() {
            br = new BufferedReader(new InputStreamReader(System.in));
        }

        public FastReader(String s) throws FileNotFoundException {
            br = new BufferedReader(new FileReader(new File(s)));
        }

        String next() {
            while (st == null || !st.hasMoreElements()) {
                try {
                    st = new StringTokenizer(br.readLine());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return st.nextToken();
        }

        int nextInt() {
            return Integer.parseInt(next());
        }

        long nextLong() {
            return Long.parseLong(next());
        }

        double nextDouble() {
            return Double.parseDouble(next());
        }

        String nextLine() {
            String str = "";
            try {
                str = br.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return str;
        }
    }
}
