package algorithm.DynamicProgramming.recommandEx;

import java.util.*;
import java.io.*;
/*
    카드 구매하기  - 실버 1
    https://www.acmicpc.net/problem/11052


    # 입력예시 1
    4
    1 5 6 7 
    # 출력예시 1
    10 

    i j|
    ------------
    1 1| dp[1] = dp[0] + money[1] = 1
    2 1| dp[2] = dp[1] + money[1] = 2 
      2|       = dp[0] + money[2] = 5 // dp[2] 최대값 
    3 1| dp[3] = dp[2] + money[1] = 6
      2|       = dp[1] + money[2] = 6  
      3|       = dp[0] + money[3] = 6 // dp[3] 최대값
    4 1| dp[4] = dp[3] + money[1] = 7
      2|       = dp[2] + money[2] = 10 // dp[4] 최대값 
      3|       = dp[1] + money[3] = 7
      4|       = dp[0] + money[4] = 7


    # 입력예시 2
    4
    5 2 8 10 
    # 출력예시 2
    20 

    i j|
    ------------
    1 1| dp[1] = dp[0] + money[1] = 5
    2 1| dp[2] = dp[1] + money[1] = 10 // dp[2] 최대값 
      2|       = dp[0] + money[2] = 2 
    3 1| dp[3] = dp[2] + money[1] = 15 // dp[3] 최대값
      2|       = dp[1] + money[2] = 7  
      3|       = dp[0] + money[3] = 8 
    4 1| dp[4] = dp[3] + money[1] = 20 // dp[4] 최대값 
      2|       = dp[2] + money[2] = 12 
      3|       = dp[1] + money[3] = 13
      4|       = dp[0] + money[4] = 10

      카드 1(i)개를 money[1]에 구매 => 남은 카드수 i-1개 => dp[i-j] + money[j]   //이떄(1<=j<=i)
      카드 2(i)개를 money[2]에 구매 => 남은 카드수 i-2개 => dp[i-j] + money[j]   //i가 2일때 j는 1~2
      카드 3(i)개를 money[3]에 구매 => 남은 카드수 i-3개 => dp[i-j] + money[j]   //i가 3일때 j는 1~3
      카드 4(i)개를 money[4]에 구매 => 남은 카드수 i-4개 => dp[i-j] + money[j]   //i가 4일때 j는 1~4 

      각 dp[idx] 에는 최대값이 들어가게 됨 
    
*/
public class ex5_11052 {
    

    static FastReader scan = new FastReader();
    static StringBuilder sb = new StringBuilder();

    static int N;
    static int[] money, dp;

    static void input(){
        N = scan.nextInt();
        dp = new int[10001];
        money = new int[N+1];
        for(int i=1; i< N+1; i++) money[i] = scan.nextInt();
    }

    static void pro() {
        // 해당 dp 인덱스별로 최대값이 계속 차게됨
        for(int i = 1; i <= N; i++){
            for(int j=1; j <= i ; j++){
                dp[i] = Math.max(dp[i], dp[i-j] + money[j]);
            }
        }
        
        System.out.println(dp[N]);
        System.out.println(Arrays.toString(dp));
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
