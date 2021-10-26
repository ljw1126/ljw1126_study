package algorithm.BinarySearch.recommandEx;

import java.util.*;
import java.io.*;

/*
    https://github.com/rhs0266/FastCampus/blob/main/%EA%B0%95%EC%9D%98%20%EC%9E%90%EB%A3%8C/02-%EC%95%8C%EA%B3%A0%EB%A6%AC%EC%A6%98/05~06-%EC%9D%B4%EB%B6%84%20%ED%83%90%EC%83%89/%EB%AC%B8%EC%A0%9C%EB%B3%84%20%EC%BD%94%EB%93%9C/2512-%EC%98%88%EC%82%B0/solution.java
    # 입력예시 1
    4
    120 110 140 150
    485

    # 출력예시 1
    127

    L   R  | mid*      sum
    0  150   75       300  true
    76 150   113      449  t
    114 150  132      494  false 
    114 131  122      474  t
    123 131  128      486  f 
    123 127  125      480  t
    126 127  126      482  t 
    127 127  127*      484  t 
    128 127 종료  

    -------------------------------------------------------

    # 입력예시 2
    5
    70 80 30 40 100
    450
    # 출력예시 2
    100

    L   R  | mid*      sum
    0  100   50       220 true 
    51 100   75       285 t
    76 100   88       300 t
    89 100   94       314 t
    95 100   97       317 t 
    98 100   99       319 t
    100 100  100*      320 t 
    101 100 종료 

*/
public class ex6_2512solution {
    static FastReader scan = new FastReader();
    static StringBuilder sb = new StringBuilder();

    static int N,M;
    static int[] A;

    static void input() {
        N = scan.nextInt();   // 지방수 
        A = new int[N + 1]; // 각 지방별 예산 요청 
        for (int i = 1; i <= N; i++) {
            A[i] = scan.nextInt();
        }
        M = scan.nextInt();
    }
    /*
        문제 내용 중 

        - 그래서 정해진 총액 이하에서 가능한 한 최대의 총 예산을 다음과 같은 방법으로 배정한다.

        - 1. 모든 요청이 배정될 수 있는 경우에는 요청한 금액을 그대로 배정한다.
        - 2. 모든 요청이 배정될 수 없는 경우에는 특정한 정수 상한액을 계산하여 
             그 이상인 예산요청에는 모두 상한액을 배정한다. 
        
             상한액 이하의 예산요청에 대해서는 요청한 금액을 그대로 배정한다. 

    */
    static boolean determination(int limit) {
       
        //예산을 limit 기준으로 할당했을때 N개 지방에 각각 할당시 기준 M을 충족하는가
        int sum = 0;
        for(int i=1; i <= N ; i++){
           sum += Math.min(A[i], limit);
        }
        return sum <= M ; 
    }

    // R을 요청예상 max_value로 잡아서 했을 경우 
    static void pro() {
        
        int L = 0, R = 0, ans = 0;
        for(int i=1; i<=N; i++) R = Math.max(R, A[i]); // 정렬 자체도 안하네 
        // [L ... R] 범위 안에 정답이 존재한다!

        while(L <= R){
            int mid = (L+R)/2;
            if(determination((int)mid)){
                ans = mid;
                L = mid +1;
            }else{
                R = mid -1;
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
