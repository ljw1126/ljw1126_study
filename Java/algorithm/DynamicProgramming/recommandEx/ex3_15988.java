package algorithm.DynamicProgramming.recommandEx;

import java.util.*;
import java.io.*;
/*
    1,2,3 더하기 3 - 실버 2
    https://www.acmicpc.net/problem/15988

    int[] Dy 선언해서 했는데 틀렸다 해서 long[] 하니 됨 (211101)
*/
public class ex3_15988 {
    
    static FastReader scan = new FastReader();
    static StringBuilder sb = new StringBuilder();
    // int[] 로 하면 틀렸다함
    static long[] Dy;

    static void pro() {
        int T = scan.nextInt();
        
        Dy = new long[1000001];
        Dy[1] = 1;
        Dy[2] = 2;
        Dy[3] = 4;

        for(int i = 4 ; i < 1000001; i++ ){
            Dy[i] = (Dy[i-1] + Dy[i-2] + Dy[i-3])%1000000009;
        }

        for(int tt = 1 ; tt <= T ; tt++){
            int N = scan.nextInt();
            sb.append(Dy[N]).append('\n');
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
