﻿package algorithm.DynamicProgramming;

import java.util.*;
import java.io.*;

public class ex2_11726 {
    
    static FastReader scan = new FastReader();
    static StringBuilder sb = new StringBuilder();

    static int N;
    static int[] Dy;

    static void input(){
        N = scan.nextInt();
    }

    static void pro() {
        Dy = new int[1005];
        // 초기값 구하기 ( 2 * n 의 직사각형에 대한 경우의 수 )
        Dy[1] = 1;  
        Dy[2] = 2;
        Dy[3] = 3;

        // 점화식을 토대로 Dy 배열 채우기
        for(int i=4 ; i<= 1000; i++){
            Dy[i] = ( Dy[i-1] + Dy[i-2] ) % 10007 ;
        }
        

        System.out.println(Dy[N]);
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
