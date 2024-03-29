﻿package algorithm.BinarySearch;

import java.util.*;
import java.io.*;
/*
    [먹을 것인가 먹힐 것인가]
    https://www.acmicpc.net/problem/7795

    - O(N^2) >> N이 최대 100,000이 므로 100억 시간초과함 
    - 즉, A[left]를 정했을때, -A[left]랑 가까운 값을 빨리 찾자
      - 정렬해보기 ! O(NlogN)걸림
        - 이점) 이분 탐색 가능 / 가장 가까운 원소를 탐색 가능
    - [정리]
      - 시간복잡도 계산시 
        1. 배열 정렬 한번 => O(NlogN)
        2. 모든 원소마다 left로 정하고, -A[left]를 이분탐색으로 검색하기 
           => O(NlogN)
        3. 총 시간 복잡도 : O(NlogN)
        
    # 입력예시 1 
    2               -- 테스트 개수 
    5 3             -- 테스트의 N, M    
    8 1 7 3 1       -- N
    3 6 1           -- M
    3 4
    2 13 7
    103 11 290 215
*/
public class ex1_7795 {

    static FastReader scan = new FastReader();
    static StringBuilder sb = new StringBuilder();

    static int N, M;
    static int[] A, B;

    static void input() {
        N = scan.nextInt();
        M = scan.nextInt();
        A = new int[N + 1];
        B = new int[M + 1];
        for (int i = 1; i <= N; i++) {
            A[i] = scan.nextInt();
        }
        for (int i = 1; i <= M; i++) {
            B[i] = scan.nextInt();
        }
    }

    static int lower_bound(int[] A, int L, int R, int X) {
        // B[L...R] 에서 A 미만의 수(A 보다 작은 수) 중 제일 오른쪽 인덱스를 return 하는 함수
        // 그런 게 없다면 L - 1 을 return 한다
        int result = L - 1;
        while(L <= R){ // 만약 B[L...R] 중 A 이하의 수가 없다면 L - 1 을 return 한다.
            int mid = (L+R)/2;
            if( A[mid] < X){
                result = mid; 
                L = mid + 1;
            }else if(A[mid] >= X){
                R = mid -1;
            }
        }
        
        return result;
    }

    static void pro() {
        // B 배열에 대해 이분탐색을 할 예정이니까, 정렬을 해주자!
        // TODO
        Arrays.sort(B,1,M+1);

        int ans = 0;
        for (int i = 1; i <= N; i++) {
            // A[i] 를 선택했을 때, B 에서는 A[i]보다 작은 게 몇 개나 있는 지 count하기
            ans += lower_bound(B, 1, M , A[i]);
        }
        System.out.println(ans);
    }

    public static void main(String[] args) {
        int TT;
        TT = scan.nextInt();
        for (int tt = 1; tt <= TT; tt++) {
            input();
            pro();
        }
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