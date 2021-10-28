package algorithm.TwoPointer.recommandEx;

import java.util.*;
import java.io.*;
/*
    배열 합치기 - 실버 5 
    https://www.acmicpc.net/problem/11728

    첫째 줄에 배열 A의 크기 N, 배열 B의 크기 M이 주어진다. (1 ≤ N, M ≤ 1,000,000)

    둘째 줄에는 배열 A의 내용이, 셋째 줄에는 배열 B의 내용이 주어진다. 
    배열에 들어있는 수는 절댓값이 109보다 작거나 같은 정수이다.

    -- 병합정렬 같은거 

    입력)
    첫째 줄에 배열 A의 크기 N, 배열 B의 크기 M이 주어진다. (1 ≤ N, M ≤ 1,000,000)

    둘째 줄에는 배열 A의 내용이, 
    셋째 줄에는 배열 B의 내용이 주어진다. 
    배열에 들어있는 수는 절댓값이 109보다 작거나 같은 정수이다.

    # 예제 입력 1 
    2 2      -- a배열 크기와 b 배열의 크기가 2 
    3 5
    2 9

    # 예제 출력 1
    2 3 5 9

    # 예제 입력 2
    2 1
    4 7
    1
    
    # 예제 출력 
    1 4 7 

*/

public class ex4_11728 {

    static StringBuilder sb = new StringBuilder();
    static FastReader scan = new FastReader();

    static int n, m;
    static int[] a, b;

    static void input() {
        n = scan.nextInt();
        m = scan.nextInt();
        a = new int[n + 1];
        b = new int[m + 1];
        for (int i = 1; i <= n; i++) {
            a[i] = scan.nextInt();
        }
        for (int i = 1; i <= m; i++) {
            b[i] = scan.nextInt();
        }
    }
    // 병합정렬 같은거네 
    static void pro() {
        int L = 1, R = 1;
        // a와 b를 앞에서부터 하나씩 추출해서 출력한다. 단, 둘 다 비어있지 않은 경우와 그것이 아닌 경우를 잘 나누자.
       
        while( L < n +1 && R < m +1  ){
            if( a[L] > b[R] ) {
                sb.append( b[R] ).append(' ');
                R++;
            }else{
                sb.append( a[L] ).append(' ');
                L++;
            }
        }

        while(L < n+1){
            sb.append( a[L] ).append(' ');
            L++; 
        }

        while(R < m +1){
            sb.append( b[R] ).append(' ');
            R++;
        }
        
        System.out.println(sb);
    }

    /*
    //해설답안 -- 병합정렬 처럼 푼게 맞음
    static void pro() {
        int L = 1, R = 1;
        // a와 b를 앞에서부터 하나씩 추출해서 출력한다. 단, 둘 다 비어있지 않은 경우와 그것이 아닌 경우를 잘 나누자.
        while (L <= n && R <= m){
            if (a[L] <= b[R]) sb.append(a[L++]).append(' ');
            else sb.append(b[R++]).append(' ');
        }
        while (L <= n) sb.append(a[L++]).append(' ');
        while (R <= m) sb.append(b[R++]).append(' ');
        
        System.out.println(sb);
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
