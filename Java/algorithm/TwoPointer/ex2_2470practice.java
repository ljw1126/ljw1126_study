package algorithm.TwoPointer;

import java.util.*;
import java.io.*;

public class ex2_2470practice {
    
    static StringBuilder sb = new StringBuilder();
    static FastReader scan = new FastReader();

    static int N, sum;
    static int[] a;

    public static void input(){
        N = scan.nextInt();
        a = new int[N+1];
        for(int i=1; i < N+1; i++){
            a[i] = scan.nextInt();
        } 
    }

    public static void pro(){
        Arrays.sort(a, 1, N+1); // 1번 인덱스 ~ N번 인덱스까지 정렬 

        int best_sum = Integer.MAX_VALUE; // 최대치로 설정
        int v1 = 0, v2 = 0, L = 1 , R = N;
        
        while(L < R){
            //L==R인 상황이면 용액이 한개 뿐이므로, L<R일때까지만 반복함 
            sum = a[L] + a[R];
            
            if(Math.abs(sum) < best_sum){
                v1 = a[L];
                v2 = a[R];
                best_sum = Math.abs(sum);
            }

            if(sum > 0) R--;
            else L++;
        }

        //결과 출력 
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
