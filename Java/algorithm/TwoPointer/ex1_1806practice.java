package algorithm.TwoPointer;

import java.util.*;
import java.io.*;

public class ex1_1806practice {
    
    static FastReader scan = new FastReader();
    static StringBuilder sb = new StringBuilder();

    static int n,s;
    static int[] a;

    static void input() {
        n = scan.nextInt();
        s = scan.nextInt();

        a = new int[n+1];
        for(int i=1; i < n+1; i++){
            a[i] = scan.nextInt();
        }

    }

    static void pro(){
        int R = 0, sum = 0, ans = n+1;
        // 내가 한거 정답은 맞는데 제출시 틀렸다함
        // for(int L = 1; L <= n ; L++){
        //     // L - 1을 구간에서 제외하기 
        //     sum -= a[L-1];

        //     if(sum < s){        -- 일단 while문 있으니 if문 필요없음
        //         // R을 옮길 수 있을때까지 옮기기
        //         while(R < n+1 && sum < s){ // R+1 <=n 조건식과 순서가 조금 틀렸음
        //             sum += a[R];
        //             R++;
        //         }
        //     }   
        //     // [L..R]의 합, 즉 sum이 조건을 만족하면 정답 갱신하기
        //     // 다 계산할 필요없이 조건문이 있어야 했음  
        //     ans = Math.min(ans, R-L +1); 
        // }

        for(int L=1; L <=n ; L++){
            sum -= a[L-1];
            while(R+1 <= n && sum < s){
                R++;
                sum += a[R];
            }

            if(sum >= s){
                ans = Math.min(ans, R-L+1);
            }
        }

        //ans 값을 보고 불가능 판단하기 
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
