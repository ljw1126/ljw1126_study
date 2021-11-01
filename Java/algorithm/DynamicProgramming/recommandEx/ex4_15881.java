package algorithm.DynamicProgramming.recommandEx;

import java.util.*;
import java.io.*;

/*
    1,2,3 더하기 6 - 실버 1
    https://www.acmicpc.net/problem/15991
*/
public class ex4_15881 {
    
    static FastReader scan = new FastReader();
    static StringBuilder sb = new StringBuilder();
    // int[] 하면 틀렸다 함 
    static long[] Dy;

    static void preprocess() {
        Dy = new long[100001];
        // 초기값 구하기
        Dy[0] = 1;
        Dy[1] = 1;
        Dy[2] = 2;  // 1 + 1 , 2  
        Dy[3] = 2;  // 1 + 1 + 1 , 3
        Dy[4] = 3;  // 1*4 , 2+2 , 1+2+1
        Dy[5] = 3;  // 1*5 , 2+1+2, 1+3+1
        // Dy[6] , Dy[7] 인 경우 6가지 
        // Dy[8], Dy[9] 인 경우 11가지  -- 8 일때 경우의 수 구하는데 생각 다 하지 못함..
        // 점화식을 토대로 Dy 배열 채우기 
        for(int i=6; i < 100001;i++){
            Dy[i] = (Dy[i-6] + Dy[i-4] + Dy[i-2])%1000000009;
        }
    }

    static void pro() {
        int T = scan.nextInt();
        for (int tt = 1; tt <= T; tt++) {
            int N = scan.nextInt();
            sb.append(Dy[N]).append('\n');
        }
        System.out.print(sb);
    }

    public static void main(String[] args) {
        preprocess();
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
