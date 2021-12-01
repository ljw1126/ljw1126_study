package algorithm.TwoPointer.recommandEx;

import java.util.*;
import java.io.*;
/*
    두 수의 합 (실버 3) - 투 포인터로 풀이 
    https://www.acmicpc.net/problem/3273

*/
public class ex6_3273practice {
    
    static StringBuilder sb = new StringBuilder();
    static FastReader scan = new FastReader();

    static int n, x;
    static int[] a;

    static void input() {
        // 입력 받기
        n = scan.nextInt();
        a = new int[n+1];

        for(int i=1; i <= n; i++){
            a[i] = scan.nextInt();
        }

        x = scan.nextInt();
        // 입력 배열 정렬하기
        
        Arrays.sort(a, 1, n+1);
    }

    static void pro() {
        int ans = 0, L = 1, R = n;
        // L과 R을 양쪽 끝에서 이동시키면서 정답 계산하기
        while(L<R){
            int sum = a[L] + a[R];
            if(sum == x) ans++;
            
            if(sum > x) R--;
            else L++;
        }

        System.out.println(ans);
    }

    public static void main(String[] args) {
        input();
        //System.out.println(Arrays.toString(a));
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
