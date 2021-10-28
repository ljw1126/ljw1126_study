package algorithm.TwoPointer.recommandEx;

import java.util.*;
import java.io.*;
/*
    귀여운 라이언 - 실버 3 
    https://www.acmicpc.net/problem/15565

*/
public class ex3_15565 {
    
    static FastReader scan = new FastReader();
    static StringBuilder sb = new StringBuilder();

    static int N,K;
    static int[] a;

    static void input() {
        N = scan.nextInt();
        K = scan.nextInt();
        a = new int[N+1];
        for(int i=1; i<=N; i++) a[i] = scan.nextInt();
    }
    // 직접 품 - 통과 (211028)
    static void pro(){
        int R = 0, sum = 0, ans = Integer.MAX_VALUE, cnt = 0 ;
        for(int L = 1; L <= N ; L++){
            
            if(a[L-1] == 1) cnt--;

            while(R+1 <= N && cnt < K){
                R++;
                if(a[R] == 1) cnt++;
            }

            if(cnt == K && ans > R - L + 1) ans = Math.min(ans, R - L + 1);
        }
        if(ans == Integer.MAX_VALUE) ans = -1;
        System.out.println(ans);
    }

    /*
    // 해설답안 내용인데 거의 비슷함 sum--; 해주는게 밑에 있고 ans = -1일때 처리해주는게 좀 다름
    static void pro() {
        int right = 0, ans = -1, sum = 0;
        for (int left = 1; left <= N; left++) {
            while (right < N && sum < K) {
                right++;
                if (A[right] == 1) sum++;
            }

            if (sum == K) {
                if (ans == -1) ans = right - left + 1;
                ans = Math.min(ans, right - left + 1);
            }

            if (A[left] == 1) sum--;
        }

        System.out.println(ans);
    }


    */

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
