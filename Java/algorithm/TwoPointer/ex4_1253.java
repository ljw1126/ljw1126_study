﻿package algorithm.TwoPointer;

import java.util.*;
import java.io.*;
/*
    좋다 - 골드 4
    https://www.acmicpc.net/problem/1253

    원소의 개수 N = 6  (1 ≤ N ≤ 2,000)
    5 , 1, -3, 6 , 3, -5 
    // 두개 숫자 조합시 배열내에 만들 수 있는 숫자는 3개가 있다 
    
*/
public class ex4_1253 {
    
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

    // target_idx 번째 원소가 서로 다른 두 수의 합으로 표현이 되는가?
    static boolean func(int target_idx) {
        int L = 1, R = N;
        int target = A[target_idx];
        while (L < R) {
            // int sum = A[L] + A[R];
            // if(sum == target) return true; 

            // if(sum > target) R--;
            // else L++;
            if(L== target_idx) L++; // 사용하면 안되는 숫자니깐 
            else if(R == target_idx) R--;
            else{
                if(A[L] + A[R] > target) R--;
                else if(A[L] + A[R] == target) return true;
                else L++;
            }
        }
        return false;
    }

    static void pro() {
        // 최소, 최대를 빠르게 알기 위한 정렬
        /* TODO */
        Arrays.sort(A, 1, N+1);

        int ans = 0;
        for (int i = 1; i <= N; i++) {
            // i 번째 원소가 서로 다른 두 수의 합으로 표현이 되는가?
            /* TODO */
            if(func(i)) ans++;
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
