package algorithm.TwoPointer.recommandEx;

import java.util.*;
import java.io.*;

/*
    수 고르기(골드5) - 풀었던거 같은??
    https://www.acmicpc.net/problem/2230

*/
public class ex5_2230practice {
    
    static StringBuilder sb = new StringBuilder();
    static FastReader scan = new FastReader();

    static int n, M;
    static int[] a;

    static void input() {
        n = scan.nextInt(); // n개 원소 중 
        M = scan.nextInt(); // M이상이면서 가장 작은 차이 출력해야함 
        a = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            a[i] = scan.nextInt();
        }
    }

    static void pro() {
        // 투 포인터 기법을 쓰기 위해서 정렬 해주기
        Arrays.sort(a, 1, n+1);

        int R = 1, ans = Integer.MAX_VALUE;
        for (int L = 1; L <= n; L++) {
            // 필요한 만큼 R을 오른쪽으로 이동 시키기
            while( R+1 <= n && a[R] - a[L] < M){
                R++;
            }

            // 정답 갱신하기
            if(a[R] - a[L] >= M) ans = Math.min(ans, a[R] - a[L]);
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
