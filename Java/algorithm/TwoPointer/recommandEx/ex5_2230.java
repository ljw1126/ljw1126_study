﻿package algorithm.TwoPointer.recommandEx;

import java.util.*;
import java.io.*;

/*
    수 고르기 - 골드 5
    https://www.acmicpc.net/problem/2230
*/
public class ex5_2230 {
    static StringBuilder sb = new StringBuilder();
    static FastReader scan = new FastReader();

    static int N, M;
    static int[] a;

    static void input() {
        N = scan.nextInt();
        M = scan.nextInt();
        a = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            a[i] = scan.nextInt();
        }
    }

    static void pro() {
        // 투 포인터 기법을 쓰기 위해서 정렬 해주기
        Arrays.sort(a,1,N+1);
        /*
        직접풀어서 답은 구했는데 틀림
        int L=1, R = N, ans = Integer.MAX_VALUE;
        int sum;
        
        while(L<R){
            sum = Math.abs(a[L] - a[R]);
            if(sum >= M){
                ans = Math.min(ans, sum);
            }

            if(sum >= M) R--;
            else L++;
        }
     
        System.out.println(ans);
        */

        /*
            틀린 내용 
            - while문의 부등호 a[R] - a[L] <= M 로 하는 바람에 틀렸음
            - 배열 값이 0이상이기 떄문에 Math.abs()를 사용할 필요 x 
        */
        int R = 1, ans = Integer.MAX_VALUE;
        for (int L = 1; L <= N; L++) {
            // 필요한 만큼 R을 오른쪽으로 이동 시키기
            while(R+1 <= N && a[R] - a[L] < M){
                R++;
            }
         
            if(a[R] - a[L] >= M){
                 ans = Math.min(ans, a[R] - a[L]);
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
