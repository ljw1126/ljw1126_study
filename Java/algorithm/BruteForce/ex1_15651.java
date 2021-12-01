import java.io.*;
import java.util.*;
/*
    실버3 .
    https://www.acmicpc.net/problem/15651
    - N 개중 1) 중복을 허용해서 , M개를 A) 순서있게 나열하는 방법
     - 시간복잡도 O(N^M)      // 중복허용하니 
    - 공간복잡도 O(M)         // 3자리 
*/
public class ex1_15651 {
    
    static int N, M;
    static int[] selected;
    static StringBuilder sb = new StringBuilder();

    static void input(){
        FastReader scan = new FastReader();
        N = scan.nextInt();
        M = scan.nextInt();
        selected = new int[M+1];
    }

    // Recurrence Function ( 재귀함수 )
    // 만약 M개를 전부 고름 => 조건에 맞는 탐색을 한 가지 성공한 것! 
    // 아직 M개를 고르지 않음 => k번째부터 M번째 원소를 조건에 맞게 고르는 모든 방법을 시도한다.
    static void rec_func(int k){
        System.out.println("k : " + k);
        for(int i : selected) System.out.println("select : " + i );
        if(k == M + 1){ // 재귀 종료 조건 
            // selected[1..M]배열이 새롭게 탐색된 결과
            for(int i=1; i<=M ; i++) sb.append(selected[i]).append(' ');
            sb.append('\n');
        }else{
            // 1~N까지 k번 원소로 한번씩 정하고,
            // 매번 k+1 번부터 M번 원소로 재귀호출해주기 

            for(int cand = 1 ; cand <= N; cand++){
                selected[k] = cand; 
                // k+1번 ~ M 번을 모두 탐색하는 일을 해야 하는 상황 
                rec_func(k+1);
                selected[k] = 0;
            }

        }
    }

    public static void main(String[] args) {
        // 0. main에 전체를 작업하지 않는다 함 (습관)
        // 1. 입력부분
        input();

        // 2. 함수로 정의한 모듈 부분
        // 1번재 원소부터 M 번째 원소를 조건에 맞는 모든 방법을 찾아줘
        rec_func(1);
        System.out.println(sb.toString());
    }

    static class FastReader{
        BufferedReader br;
        StringTokenizer st; 

        public FastReader(){
            br = new BufferedReader(new InputStreamReader(System.in));
        }

        public FastReader(String s) throws FileNotFoundException{
            br = new BufferedReader(new FileReader(new File(s)));
        }

        String next(){
            while(st == null || !st.hasMoreElements()){
                try{
                    st = new StringTokenizer(br.readLine());
                }catch(IOException e){
                    e.printStackTrace();
                }
            }
            return st.nextToken();
        }

        int nextInt(){
            return Integer.parseInt(next());
        }

        long nextLong(){
            return Long.parseLong(next());
        }

        double nextDouble(){
            return Double.parseDouble(next());
        }

        String nextLine(){
            String str = "";
            try{
                str = br.readLine();
            }catch(IOException e){
                e.printStackTrace();
            }
            return str;
        }


    }
}
