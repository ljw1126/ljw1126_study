package algorithm.BinarySearch.recommandEx;

import java.util.*;
import java.io.*;
/**
 * 
 *  fastcampus 답안
 *  [숫자 카드2 - 실버4]
    https://www.acmicpc.net/problem/10816

    -- 상한과 하한의 차를 구하는걸 계산 생각못함..
    https://st-lab.tistory.com/267

    A 배열 정렬 결과 
    1번 인덱스 부터 10번 인덱스까지 
    {-10, -10, 2, 3, 3, 6, 7, 10, 10, 10 }

    10
    6 3 2 10 10 10 -10 -10 7 3 // 카운팅 대상 배열 A
    8
    10 9 -5 2 3 4 5 -10 //존재 여부 파악해야 하는 배열 B

    정렬 후 
    1   2  3 4 5 6 7  8  9  10 
    ------------------------------
    -10 -10 2 3 3 6 7 10 10 10 

    1. 찾는 값이 10일 경우 
    ①upper = upper_bound(A,1,N, B[i]);
    L R | mid
    1 10|  5     ---- 10 > A[mid] 이므로 L = mid + 1 
    6 10|  8     -----10 ==  A[mid]  이므로 L = mid +1 
    9 10 | 9   ----- 10 == A[mid] 이므로 L = mid +1 
    10 10 | 10 ------- 10 == A[mid] 이므로 L = mid +1      
    11 10        --------- 종료 ans = R+1 초기값 리턴 11 

   ②lower = lower_bound(A,1,N, B[i]);
    L R | mid 
    1 10| 5    ----- 10 > A[mid] 이므로 L = mid + 1 
    6 10| 8    ------10 <= A[mid] 이므로 R = mid -1; ans = mid; 
    6  8 | 7    ------10  > A[mid] 이므로 L = mid +1 
    8 8 ----------종료 ans = 8 
    결과 11 - 8 = 3 


    2. 찾는 값이 -10일 경우 
    ①upper = upper_bound(A,1,N, B[i]);
    L R  | mid 
    1 10 | 5     ----- A[mid] > -10  이므로 ans = mid, R = mid -1; 
    1  4 | 2    ------ A[mid] == -10 이므로 L = mid +1; 
    3  4 | 3    -------A[mid]  >  -10 이므로 ans = mid , R = mid -1;
    3  2 -----------종료     ans = 3 
    
    ②lower = lower_bound(A,1,N, B[i]);
    L R | mid 
    1 10 | 5      ----- A[mid] > -10   이므로 ans = mid, R = mid -1;
    1  4  | 2      ----- A[mid] >=  -10 이므로 ans = mid, R= mid -1;
    1  1 | 1      ----- A[mid] >= -10 이므로 ans = mid , R = mid -1;
    1 0 -----------종료 ans = 1

    결과 3 -1 = 2


    정렬 후 
    1   2  3 4 5 6 7  8  9  10 
    ------------------------------
    -10 -10 2 3 3 6 7 10 10 10 

    3. 찾는값이 3인 경우 
    ①upper = upper_bound(A,1,N, B[i]);
    L R | mid 
    1 10 | 5 ----- A[mid] == 3 이므로 L = mid + 1;
    6 10 | 8 ----- A[mid] > 3 므로 ans = mid , R = mid -1;
    6 7  | 6 ----- A[mid] > 3 므로 ans = mid , R = mid -1;
    6 5  -------- 종료  ans = 6 
    
    ②lower = lower_bound(A,1,N, B[i]); 
    L R | mid 
    1 10| 5  -------- A[mid] >= 3 , ans = mid, R = mid -1;
    1  4 | 2 --------- A[mid]  <  3 , L = mid + 1;
    3  4 | 3 --------- A[mid] <  3  , L = mid +1 
    4  4 | 4  ---------A[mid] >= 3 , ans = mid , R = mid -1;
    4  3 ------- 종료 ans = 4 
 */
public class ex4_10816practice {
    
    static FastReader scan = new FastReader();
    static StringBuilder sb = new StringBuilder();

    static int N, M;
    static int[] A, B;
    /*
        애초에 넣을떄 counting 배열이나, hashmap사용해서 푸는 방식도 존재함 
    */
    static void input() {
        N = scan.nextInt();
        A = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            A[i] = scan.nextInt();
        }
        
        M = scan.nextInt();
        B = new int[M+1];
        for(int i=1; i<=M;i++){
            B[i] = scan.nextInt();
        }

        // A 에 대해 이분 탐색을 할 예정이니까, 정렬을 미리 해주자.
        Arrays.sort(A, 1, N+1); //이진 탐색 위한 정렬
    }

    static int lower_bound(int[] A, int L, int R, int X){
        int ans = R + 1;
        
        while(L <= R){
            int mid = (L+R)/2;
            if(A[mid] >= X){
                ans = mid;
                R = mid -1;
            }else{
                L = mid +1;
            }
        }
        
        return ans;
    }

    static int uppper_bound(int[] A , int L, int R , int X){
        int ans = R + 1;

        while(L <= R){
            int mid = (L+R)/2;
            if(A[mid] > X){
                ans = mid;
                R = mid -1;
            }else{
                L = mid +1;
            }
        }

        return ans;
    }

    static void pro() {
        for (int i = 1; i <= M; i++) {
            int upper = uppper_bound(A, 1, N, B[i]); // R을 N으로 하니 틀림
            int lower = lower_bound(A, 1, N, B[i]);
            sb.append(upper-lower).append(' ');
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
