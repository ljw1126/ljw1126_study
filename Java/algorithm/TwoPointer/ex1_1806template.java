package algorithm.TwoPointer;

import java.util.*;
import java.io.*;

public class ex1_1806template {
    
    static FastReader scan = new FastReader();
    static StringBuilder sb = new StringBuilder();

    static int n,S;
    static int[] a;

    static void input() {
        n = scan.nextInt();
    }

    static void pro(){
        int R = 0, sum = 0, ans = n+1;
        for(int L = 1; L <= n ; L++){
            // L - 1을 구간에서 제외하기 

            // R을 옮길 수 있을때까지 옮기기

            // [L..R]의 합, 즉 sum이 조건을 만족하면 정답 갱신하기 
        }

        //ans 값을 보고 불가능 판단하기 
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
