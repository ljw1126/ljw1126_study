package algorithm.TwoPointer;

import java.util.*;
import java.io.*;
/*
    좋다(골드4)
    https://www.acmicpc.net/problem/1253
    
    직접푼거는 틀림(211201)
*/
public class ex4_1253practice {
    
    static StringBuilder sb = new StringBuilder();
    static FastReader scan = new FastReader();

    static int N;
    static int[] Ai; 

    public static void input(){
        N = scan.nextInt();
        Ai = new int[N+1];
        for(int i=1 ; i<=N ;i++){
            Ai[i] = scan.nextInt();
        }
    }

    // target_idx 번쨰 원소가 서로 다른 두 수의 합으로 표현이 되는가?
    static boolean func(int target_idx){
        int L=1, R = N;
        int target = Ai[target_idx];
        while(L < R){
            // int sum = Ai[L] + Ai[R];

            // if(sum == target) return true;
            // else if(sum > target) R--;
            // else L++;

            if(L == target_idx) L++;
            else if(R == target_idx) R--;
            else{
                
                if(Ai[L]+Ai[R] == target) return true;
                else if(Ai[L] + Ai[R] > target) R--;
                else L++;

            }

        }
        return false;
    }

    public static void pro(){
        //최소, 최대를 빠르게 알기 위한 정렬 
        Arrays.sort(Ai, 1, N+1); // 1~N번 인덱스까지 정렬 

        int ans = 0;
        for(int i=1; i<=N;i++){
            //i번째 원소가 서로 다른 두 수의 합으로 표현되는가?
            if(func(i)) ans++;
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
