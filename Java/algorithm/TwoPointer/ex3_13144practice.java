package algorithm.TwoPointer;

import java.util.*;
import java.io.*;
/*
    List of Unique Numbers - 골드 3
    https://www.acmicpc.net/problem/13144

    그러니깐 투포인트 방식으로 카운트 배열 하나 사용해서 
    L 을 1~ 배열길이만큼 반복문 돌떄 
    각각 R이 중복값이 안나오는 때까지 돌려서 길이를 구해 축적을 하면됨 

    ex1_1806하고 로직 동일하게 작성해봄 
*/
public class ex3_13144practice {
   
    static StringBuilder sb = new StringBuilder();
    static FastReader scan = new FastReader();

    static int N;
    static int[] a,cnt;

    public static void input(){
        N = scan.nextInt();
        a = new int[N+1];
        cnt = new int[N+1];
        for(int i=1 ; i <= N ; i++ ) a[i] = scan.nextInt();
        
    }

    public static void pro(){
        long ans = 0;

        for(int L=1, R=0; L<=N; L++){
            
            // L을 옮겨주면서 A[L]의 개수를 감소시킨다.
            if(L > 1) cnt[a[L-1]]--;
            
            // R을 옮길 수 있는 만큼 옮긴다.
            while( R+1 <= N && cnt[a[R+1]] == 0){
                R++;
                cnt[a[R]]++;
            }

            // 정답을 갱신한다.
            ans += R - L + 1;
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
