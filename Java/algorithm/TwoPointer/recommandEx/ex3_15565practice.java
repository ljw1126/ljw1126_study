package algorithm.TwoPointer.recommandEx;

import java.util.*;
import java.io.*;

public class ex3_15565practice {
    
    static FastReader scan = new FastReader();
    static StringBuilder sb = new StringBuilder();

    static int N, K;
    static int[] A;

    static void input() {
        N = scan.nextInt();
        K = scan.nextInt();
        A = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            A[i] = scan.nextInt();
        }
    }
    
    // 라이언 인형 : 1 , 어피치 인형 2
    // 라이언 인형이 K개 이상이 되는 집합중 가장 작은 집합 크기 
    static void pro() {
        int right = 0, ans = Integer.MAX_VALUE, sum = 0; // ans 가 -1 이었는데.. 
        for (int left = 1; left <= N; left++) {
            // [left ... ] 와 같이 left 부터 시작하는 연속 부분 수열 중에서
            // 라이언 인형이 K 개가 되는 최소 right 위치까지 right를 이동 시키기
            while(right + 1 <= N && sum < K){
                if(A[++right] == 1) sum++;
            }

            if (sum == K) {
                ans = Math.min(ans, right-left+1);
            }

            // 이후에 left를 오른쪽으로 한 칸 이동시킬 것이므로 라이언 인형 개수 조정하기
            if(A[left] == 1) sum--;     
        }

        if(ans == Integer.MAX_VALUE) ans = -1;
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
