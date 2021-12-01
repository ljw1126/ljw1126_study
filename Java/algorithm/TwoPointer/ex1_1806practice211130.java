package algorithm.TwoPointer;

import java.util.*;
import java.io.*;

public class ex1_1806practice211130 {
    
    static FastReader scan = new FastReader();
    static StringBuilder sb = new StringBuilder();

    static int n,s;
    static int[] a;


    public static void input(){
        n = scan.nextInt();
        s = scan.nextInt();
        a = new int[n+1];
        for(int i=1 ; i <= n ; i++){
            a[i] = scan.nextInt();
        }
        /*
        System.out.println(n);
        System.out.println(s);
        System.out.println(Arrays.toString(a));
        */
        //System.out.println(a.length);
    }

    public static void pro(){
        int R = 0, sum = 0, ans = n+1;     // ans뜻은 최대값보다 큰 더미값을 뜻하는듯

        for(int L=1; L <= n; L++){
            
            // L - 1을 구간에서 제외하기 
            //if(L > 1) sum -= a[L-1];
            sum -= a[L-1];     // 어차피 0 번 인덱스는 비워져 있음

            // R을 옮길 수 있을때까지 옮기기 
            while(R+1 <= n && sum < s){
                R++;
                // if(R >= a.length) break;
                sum += a[R];
            }

            // [L..R]의 합, 즉 sum이 조건을 만족하면 정답 갱신하기 
            if(sum >= s) ans = Math.min(ans, R - L +1);
        }

        if(ans == n+1) ans = 0;
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
