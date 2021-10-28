package algorithm.TwoPointer.recommandEx;

import java.util.*;
import java.io.*;
/*
    수열 - 실버 3 
    https://www.acmicpc.net/problem/2559

    첫째 줄에는 두 개의 정수 N과 K가 한 개의 공백을 사이에 두고 순서대로 주어진다. 
    첫 번째 정수 N은 온도를 측정한 전체 날짜의 수이다. 
    N은 2 이상 100,000 이하이다. 
    두 번째 정수 K는 합을 구하기 위한 연속적인 날짜의 수이다. 
    K는 1과 N 사이의 정수이다. 
    둘째 줄에는 매일 측정한 온도를 나타내는 N개의 정수가 빈칸을 사이에 두고 주어진다. 
    이 수들은 모두 -100 이상 100 이하이다. 

    - 최대치 생각시 >> 최대값 100으로 100,000(N일) 했을대 10,000,000 라서 integer로 가능 ! 
    - N일이 있을때 K개의 숫자를 더해서 최대값을 구하는 문제다 

    # 예제 입력 1 
    10 2
    3 -2 -4 -9 0 3 7 13 8 -3       -- 13,8 더했을때

    # 예제 출력 1 
    21

    # 예제 입력 2
    10 5
    3 -2 -4 -9 0 3 7 13 8 -3

    # 예제 출력 2 
    31                  --- 0,3,7,13,8 
*/
public class ex2_2559 {
    
    static FastReader scan = new FastReader();
    static StringBuilder sb = new StringBuilder();

    static int n,k;
    static int[] a;

    static void input() {
        n = scan.nextInt(); // 주어진 날짜 수 
        k = scan.nextInt(); // 몇개의 날짜를 더해서 구할지 
        a = new int[n+1];
        for(int i = 1; i < n+1; i++) a[i] = scan.nextInt();
    }

    static void pro(){
        int R = 0, ans = -100*n, sum = 0;
        
        //for(int L = 1; L <= n ; L++){ // 구간 범위 지정하는게 틀렸네 나는 
        for (int L = 1; L + k - 1 <= n; L++) {    
            sum -= a[L-1]; 
            // R을 옮길 수 있을때까지 옮기기
            // while( R+1 <= n && R-L+1 < k ){
            //     R++;
            //     sum += a[R];
            // }
            while( R+1 <= L+k -1)
                sum += a[++R];

            ans = Math.max(ans, sum);

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
