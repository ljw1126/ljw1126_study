package algorithm.BinarySearch.recommandEx;

import java.util.*;
import java.io.*;
/*
    [예산- 실버3]
    https://www.acmicpc.net/problem/2512
    
    임의의 상한액 X를 기준으로 지방수 N에 각각 배정했을때 제한된 총 예산을 M을 만족하는가 
    
*/
public class ex6_2512 {
    static FastReader scan = new FastReader();
    static StringBuilder sb = new StringBuilder();

    static int N;
    static long M;
    static int[] A;

    static void input() {
        N = scan.nextInt();   // 지방수 
        A = new int[N + 1]; // 각 지방별 예산 요청 
        for (int i = 1; i <= N; i++) {
            A[i] = scan.nextInt();
        }
        M = scan.nextLong();
    }
    /*
        문제 내용 중 

        - 그래서 정해진 총액 이하에서 가능한 한 최대의 총 예산을 다음과 같은 방법으로 배정한다.

        - 1. 모든 요청이 배정될 수 있는 경우에는 요청한 금액을 그대로 배정한다.
        - 2. 모든 요청이 배정될 수 없는 경우에는 특정한 정수 상한액을 계산하여 
             그 이상인 예산요청에는 모두 상한액을 배정한다. 
        
             상한액 이하의 예산요청에 대해서는 요청한 금액을 그대로 배정한다. 

    */
    static boolean determination(int limited) {
        //if( M < limited) return false;
        //예산을 limited 기준으로 할당했을때 N개 지방에 각각 할당시 기준 M을 충족하는가
        long sum = 0;
        int cnt = 0;
        for(int i=1; i <= N ; i++){
            if(limited > A[i]){
                sum += A[i];
                cnt++;
            }else if(limited <= A[i]){
                sum += limited;
                cnt++;
            }
        }

        return cnt == N && sum <= M; // limited 기준으로 N개의 지방에 할당시 예산 M을 만족하는가  
    }

    static boolean checked(int[] A, int max_value){
        int sum = 0;
        for(int i=1; i<= N; i++){
            if(A[i] <= max_value) sum += A[i];
        }
        return sum <= M; 
    }

    static void pro() {
        Arrays.sort(A, 1, N+1);
    
        long L = 0, R = Integer.MAX_VALUE, ans = 0;
        // [L ... R] 범위 안에 정답이 존재한다!
        // 이분 탐색과 determination 문제를 이용해서 answer를 빠르게 구하자!
        int max_value = A[A.length-1];

        if(checked(A, max_value)){
            ans = max_value;
        }else{
            while(L <= R){
                long mid = (L+R)/2;
                if(determination((int)mid)){
                    ans = mid;
                    L = mid +1;
                }else{
                    R = mid -1;
                }
            }
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
