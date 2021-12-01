package algorithm.BinarySearch.recommandEx;

import java.util.*;
import java.io.*;
/*
    두 수의 합(실버3)
    https://www.acmicpc.net/problem/3273

    - O(N^2) 생각하면 100억이라 시간초과 
    - 이분탐색하면 260만 정도 
      - n (수열의 크기, 정수 갯수) <= 100,000
      - x (구하려는 값의 크기) <= 2,000,000
      - 대략 N개 마다 이분탐색을 해서 log n의 시간이 걸리니 2^17 => 10만 * 17 해서 대략 170만? 

    - 이진탐색 뿐만 아니라 투포인터로도 풀 수 있는 문제인듯?
*/
public class ex3_3273practice {
    
    static FastReader scan = new FastReader();
    static StringBuilder sb = new StringBuilder();

    static int N, Sum;
    static int[] A;

    static void input() {
        N = scan.nextInt();
        A = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            A[i] = scan.nextInt();
        }
        Sum = scan.nextInt();
    }
    /*
    정답임. 이진탐색으로 풀 경우
    static boolean bin_search(int[] A, int L, int R, int X) {
        // A[L ... R] 에서 X 가 존재하면 true, 없으면 false 를 return 하는 함수
        while(L<=R){
            int mid = (L+R)/2;
            if(A[mid] == X) return true;
            else if(A[mid] < X) L = mid +1;
            else R = mid -1;
        }
        return false;
    }

    static void pro() {
        // A 에 대해 이분 탐색을 할 예정이니까, 정렬을 미리 해주자.
        Arrays.sort(A, 1, N+1);

        int ans = 0;
        for (int i = 1; i <= N - 1; i++) {
            // A[i] 를 선택했다. 즉 우린 Sum - A[i] 가 배열에 있는 지 확인해야 한다.
            if (bin_search(A, i+1, N, Sum-A[i])) ans++;
        }
        System.out.println(ans);
    }
    */

    // 투 포인터로 풀 경우 (정답이다)
    /*
        구하려는 값보다 크거나 같으면 R--;
        구하려는 값보다 작으면 L++;
    */
    static void pro(){
        Arrays.sort(A, 1, N+1);
        int ans = 0;
        int L = 1, R = N;
        while(L<R){
            int added = A[L] + A[R];
            if(added == Sum) ans++;
            
            if(added >= Sum) R--;
            else L++;
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
