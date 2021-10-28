package algorithm.TwoPointer.recommandEx;

import java.util.*;
import java.io.*;

/*
    수들의 합2 - 실버 2 
    https://www.acmicpc.net/problem/2003

    N개의 수 , i번째 수부터 j번째 수 까지의 합 M 
    N(1 ≤ N ≤ 10,000), M(1 ≤ M ≤ 300,000,000)

    //ex1_1806.java 부분합과 문제가 거의 동일함 
    
    # 입력예시 1
    4 2
    1 1 1 1

    # 출력예시 1
    3

    # 입력예시 2
    10 5
    1 2 3 4 2 5 3 1 1 2         -- 2,3/5/3,1,1 해당함

    # 출력예시 2
    3       
*/
public class ex1_2003 {
    static FastReader scan = new FastReader();
    static StringBuilder sb = new StringBuilder();

    static int n,m;
    static int[] a;

    static void input() {
        n = scan.nextInt();
        m = scan.nextInt();

        a = new int[n+1];
        for(int i=1; i<n+1 ;i++) a[i] = scan.nextInt();
    }

    static void pro(){
        int R = 0, ans = 0;
        long sum = 0 ;
        for(int L = 1; L <= n ; L++){
            
            sum -= a[L-1];

            while(R+1 <= n && sum < m){
                R++;
                sum += a[R];
            }

            if(sum == m) ans++;
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
