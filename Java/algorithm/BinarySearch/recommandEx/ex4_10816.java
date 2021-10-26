package algorithm.BinarySearch.recommandEx;

import java.util.*;
import java.io.*;
/**
 *  [숫자 카드2 - 실버4]
    https://www.acmicpc.net/problem/10816

    -- 상한과 하한의 차를 구하는걸 계산 생각못함..
    https://st-lab.tistory.com/267

    A 배열 정렬 결과 
    1번 인덱스 부터 10번 인덱스까지 
    {-10, -10, 2, 3, 3, 6, 7, 10, 10, 10 }

 */
public class ex4_10816 {
    static FastReader scan = new FastReader();
    static StringBuilder sb = new StringBuilder();

    static int N;
    static int[] A;

    static void input() {
        N = scan.nextInt();
        A = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            A[i] = scan.nextInt();
        }
    }
    // 중복 원소에 대한 길이 = 상한 - 하한 
    static int uppper_bound(int[] A , int L, int R , int X){
        
       while(L < R){
           int mid = (L+R)/2;

           if( X < A[mid]){ // 부등호 방향이 굉장히 헷가리는 문제
               R = mid;
           }else{
               L = mid +1;
           }
       }
       return R;
    }

    static int lower_bound(int[] A, int L, int R, int X){
        while(L < R){
            int mid = (L+R)/2;
 
            if( X <= A[mid]){ // 부등호 방향이 굉장히 헷가리는 문제
                R = mid;
            }else{
                L = mid +1;
            }
        }
        return L;
    }

    static void pro() {
        // A 에 대해 이분 탐색을 할 예정이니까, 정렬을 미리 해주자.
        Arrays.sort(A, 1, N + 1);

        int M = scan.nextInt();
        for(int i=1; i <= M; i++){
            int X = scan.nextInt();
            sb.append(uppper_bound(A,1,N+1,X) - lower_bound(A, 1, N+1, X)).append(' ');
        }

        System.out.println(sb);
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