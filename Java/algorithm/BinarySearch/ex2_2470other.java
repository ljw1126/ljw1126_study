package algorithm.BinarySearch;

import java.util.*;
import java.io.*;
// https://velog.io/@hyunjkluz/%EB%B0%B1%EC%A4%802470-%EB%91%90-%EC%9A%A9%EC%95%A1-Java
// 답은 맞는데 틀려다 함..
public class ex2_2470other {
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

    // 답은 구하는데 틀렸다 함
    static void pro() {
        // A 에 대해 이분 탐색을 할 예정이니까, 정렬을 미리 해주자.
        Arrays.sort(A, 1, N + 1);

        int best_sum = Integer.MAX_VALUE;
        int v1 = 0, v2 = 0;
        int L = 1 , R = A.length - 1;
       
        while(L <= R){

            int sum = A[L] + A[R];

            //0에 더 가까운 수를 찾기 위함이니깐 절대값으로 변경
            if(Math.abs(sum) < best_sum){
                best_sum = Math.abs(sum);
                v1 = A[L];
                v2 = A[R];
            }

            // 이분 탐색할 것 = 배연 안에서 숫자들의 합
            // 합이 0보다 크다는 것 = end번째 수의 절대값이 start번째 수의 절대값보다 크다는 의미
            if(sum > 0){
                R--;
            }else{
                L++;
            }
        }

        sb.append(v1).append(' ').append(v2);
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
