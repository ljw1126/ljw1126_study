package algorithm.BinarySearch.recommandEx;

import java.util.*;
import java.io.*;
/*
    이상한 술집 - 실버 3 
    https://www.acmicpc.net/problem/13702
    입력)
    주문한 막걸리 주전자 개수 N,
    사람수 K, 둘째 줄 부터 주전자 용량 주어짐 

    N은 10000이하의 정수이고, K는 1,000,000이하의 정수이다. 
    막걸리의 용량은 231 -1 보다 작거나 같은 자연수 또는 0이다. 
    단, 항상 N ≤ K 이다. 즉, 주전자의 개수가 사람 수보다 많을 수는 없다.
    출력)
    첫째 줄에 K명에게 나눠줄 수 있는 최대의 막걸리 용량 ml 를 출력한다.
    <-> 임의 막걸리 용량을 지정했을때 K명에게 나눠질 수 있는가? YES/NO (최대치 구하기)

    

    # 예제 입력1
    2 3
    702
    429

    # 예제 출력1
    351

    # 예제 입력2
    4 11
    427
    541
    774
    822

    # 예제 출력2
    205

    // 답은 맞는데 왜 틀리다 하지.. 
    // L,R , mid, sum 이런게 long 이여야 함 .. 
    // 정렬한 A 배열의 마지막 값을 R로 지정해서 int로 계산해도 틀렸다 함 
*/
public class ex9_13702 {
    static FastReader scan = new FastReader();
    static StringBuilder sb = new StringBuilder();

    static int N,K;
    static int[] A;

    static void input() {
        N = scan.nextInt();//주전자 수 
        K = scan.nextInt(); // 사람 수 (항상 N <= K )
        A = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            A[i] = scan.nextInt();
        }
    }

    static boolean determination(long ml){
        long sum = 0;
        for(int i=1; i<=N ; i++){
            sum += A[i]/ml;
        }
        return sum >= K; 
    }
    // N 주전자 개수는 10000이하의 정수 
    // K명이 100만 명일때 막걸리용량 최대인 약 10억이라고 가정함
    // 그럴경우 1000씩 나누면 됨 , 결과값 int로도 구해짐 
    static void pro() {
        // A 에 대해 이분 탐색을 할 예정이니까, 정렬을 미리 해주자.
        //Arrays.sort(A, 1, N + 1);
        long L = 0, R = Integer.MAX_VALUE, ans =0;

        while(L<=R){
            long mid = (L+R)/2;
            if(determination(mid)){
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
