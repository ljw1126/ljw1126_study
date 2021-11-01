package algorithm.DynamicProgramming.recommandEx;

import java.util.*;
import java.io.*;
/*
    파보나치 수 5 - 브론즈 2 
    https://www.acmicpc.net/problem/10870

    기본 문제.. dp 배열 초기화, 값 미리 할당 후 호출하니 
    O(N)시간복잡도를 가진다
*/
public class ex2_10870 {
    
    static FastReader scan = new FastReader();
    static StringBuilder sb = new StringBuilder();

    static int[] dp;

    static void pro() {
  
        int N = scan.nextInt();

        dp = new int[21];
        dp[0] = 0;
        dp[1] = 1;

        for(int i=2; i<21;i++){
            dp[i] = dp[i-1] + dp[i-2];
        }
        
        System.out.println(dp[N]);
       
    }

    public static void main(String[] args) {
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
