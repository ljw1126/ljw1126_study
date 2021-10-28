package algorithm.TwoPointer.recommandEx;

import java.util.*;
import java.io.*;
/*
    두 수의 합 - 실버3 ( 이진 탐색으로 풀었었으 )
    https://www.acmicpc.net/problem/3273
*/
public class ex6_3273 {
    static FastReader scan = new FastReader();
    static StringBuilder sb = new StringBuilder();

    static int N,X,cnt;
    static int[] A;

    static void input() {
        N = scan.nextInt();
        A = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            A[i] = scan.nextInt();
        }
        X = scan.nextInt();
    }

    static void pro() {
        // 최소, 최대 원소를 빠르게 찾기 위해서 정렬을 미리 해주자.
        Arrays.sort(A, 1, N + 1);

        int L = 1, R = N;

        while (L < R){  
            int sum = A[L] + A[R];

            if(sum == X){
                cnt++;
            }
            // 작은 값과 큰 값을 더했는데 크다면, 나머지 더해도 어차피 클테니 R을 줄여야함 (X가 mid라면)
            // 작은 값과 큰 값을 더했는데 작다면, 나머지 더해도 어차피 작을테니 L을 올려야함(X가 mid라면)
            if(sum >= X)
                R--;
            else 
                L++;
        }
        
        System.out.println(cnt);
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
