package algorithm.DynamicProgramming.recommandEx;

import java.util.*;
import java.io.*;
/*
    카드 구매하기  - 실버 1
    https://www.acmicpc.net/problem/11052
*/
public class ex5_11052 {
    

    static FastReader scan = new FastReader();
    static StringBuilder sb = new StringBuilder();

    static int N;
    static int[] money, dp;

    static void input(){
        N = scan.nextInt();
        dp = new int[10001];
        money = new int[N+1];
        for(int i=1; i< N+1; i++) money[i] = scan.nextInt();
    }

    static void pro() {
        
        
        for(int i = 1; i <= N; i++){
            for(int j=1; j < i ; j++){
                dp[i] = Math.max(dp[i], dp[i-j]);
            }
        }

    }

    public static void main(String[] args) {
        input();
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
