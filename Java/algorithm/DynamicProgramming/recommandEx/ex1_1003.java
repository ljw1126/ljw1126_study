package algorithm.DynamicProgramming.recommandEx;

import java.util.*;
import java.io.*;
/**
    피보나치 함수 - 실버3
    https://www.acmicpc.net/problem/1003

    손으로 표를 작성해보니 
      0 1 2 3 4 5 6 7  8  9  10
      -------------------------
      0 1 1 2 3 5 8 13 21 34 35
      
      6을 구할 경우 5 8  
      1을 구할 경우 0 1 
      10을 구할 경우 34 35 
      이렇게 값이 구해지길래 dp테이블을 채운 후 호출하도록함 
      
      시간복잡도 - O(N) 
      직접품(211101)
 */
public class ex1_1003 {

    static FastReader scan = new FastReader();
    static StringBuilder sb = new StringBuilder();

    static int[] dp;

    static void pro() {
        int T = scan.nextInt();
        dp = new int[41];
        dp[0] = 0;
        dp[1] = 1;

        for(int i=2; i<41;i++){
            dp[i] = dp[i-1] + dp[i-2];
        }

        for (int tt = 1; tt <= T; tt++) {
            int N = scan.nextInt();
            if(N == 0) sb.append(1).append(' ').append(0).append('\n');
            else sb.append(dp[N-1]).append(' ').append(dp[N]).append('\n');
        }
        
    }

    public static void main(String[] args) {
        pro();
        System.out.println(sb);
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