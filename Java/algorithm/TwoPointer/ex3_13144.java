package algorithm.TwoPointer;

import java.util.*;
import java.io.*;
/*
    List of Unique Numbers (골드3, 난이도4)
    https://www.acmicpc.net/problem/13144
*/
public class ex3_13144 {
    static FastReader scan = new FastReader();
    static StringBuilder sb = new StringBuilder();

    static int N;
    static int[] A, cnt;

    static void input() {
        N = scan.nextInt();
        A = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            A[i] = scan.nextInt();
        }
        cnt = new int[100000 + 1];
    }

    static void pro() {
        long ans = 0;
        // R = 0으로 시작하는 이유 모르겠음 
        for (int L=1, R=0; L<=N; L++){  // L 마다 R 을 최대한 옮겨 줄 계획이다.
            // R 을 옮길 수 있는 만큼 옮긴다.
            /* TODO */
            while(R+1 <=N && cnt[A[R+1]] == 0){ // 조건식 틀렸었음
                R++; // 이것도 순서 디테일 
                cnt[A[R]]++;
            } 

            // 정답을 갱신한다
            /* TODO */
            ans += R - L +1;       // 해당 공식을 유추하지 못함
            
            // L 을 옮겨주면서 A[L] 의 개수를 감소시킨다.
            /* TODO */
            cnt[A[L]]--;          // A[L]을 감소시켜야 하는데, 멍청하게 cnt[L]-- 함
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
