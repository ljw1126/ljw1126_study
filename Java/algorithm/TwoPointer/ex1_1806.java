package algorithm.TwoPointer;

import java.util.*;
import java.io.*;
/*
    [부분합 - 골드 4]
    https://www.acmicpc.net/problem/1806

    문제)
    10,000 이하의 자연수로 이루어진 길이 N짜리 수열이 주어진다. 
    이 수열에서 연속된 수들의 부분합 중에 그 합이 S 이상이 되는 것 중, 
    가장 짧은 것의 길이를 구하는 프로그램을 작성하시오.

    입력)
    첫째 줄에 N (10 ≤ N < 100,000)과 S (0 < S ≤ 100,000,000)가 주어진다. 
    둘째 줄에는 수열이 주어진다. 수열의 각 원소는 공백으로 구분되어져 있으며, 10,000이하의 자연수이다.

    출력)
    첫째 줄에 구하고자 하는 최소의 길이를 출력한다. 
    만일 그러한 합을 만드는 것이 불가능하다면 0을 출력하면 된다.

    # 예제 입력 1
    10 15
    5 1 3 5 10 7 4 9 2 8

    # 예제 출력 1
    2

*/
public class ex1_1806 {

    static FastReader scan = new FastReader();
    static StringBuilder sb = new StringBuilder();

    static int n,S;
    static int[] a;

    static void input() {
        n = scan.nextInt(); // 원소 개수 
        S = scan.nextInt(); // 부분합 목표값
        a = new int[n+1];
        for(int i=1; i< n+1;i++) a[i] = scan.nextInt();
    }

    static void pro(){
        int R = 0, sum = 0, ans = n+1;
        for(int L = 1; L <= n ; L++){
            // L - 1을 구간에서 제외하기 
            sum -= a[L-1];

            // R을 옮길 수 있을때까지 옮기기
            while(R+1 <= n && sum < S){
                R++;
                sum += a[R];
            }

            // [L..R]의 합, 즉 sum이 조건을 만족하면 정답 갱신하기 
            if(sum >= S){
                ans = Math.min(ans, R - L + 1);
            }
        }

        //ans 값을 보고 불가능 판단하기 
        if(ans == n + 1) ans = 0; //한번도 mid 값이 갱신 안된 경우

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
