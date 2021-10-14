package personal;

import java.util.*;
import java.io.*;

public class ex_15666 {
    static int N, M;
    static int[] nums, selected; 

    static StringBuilder sb = new StringBuilder();

    static void input(){
        FastReader scan = new FastReader();
        N = scan.nextInt();
        M = scan.nextInt();

        nums = new int[N+1];
        selected = new int[M+1];

        for(int i=1; i <= N; i++) nums[i] = scan.nextInt();

        Arrays.sort(nums, 1, N+1);
    }

    // 문제 해결 함수(재귀용법 사용)
    static void rec_func(int k){
        if(k == M+1){
           // 결과값 처리
           for(int i=1; i<=M ; i++) sb.append(nums[selected[i]]).append(" ");
           sb.append("\n"); 
        }else{  
           // 반복문 내에 재귀용법으로 처리하는 부분
           int last_cand = 0 ;
           for(int cand = 1 ; cand <= N ; cand++){
               if(last_cand == nums[cand]) continue;
               if(selected[k-1] > cand) continue;

               last_cand = nums[cand];
               selected[k] = cand;
               rec_func(k+1);
               selected[k] = 0;
           }
        }
    }

    public static void main(String[] args) {
        // 1. 입력값 , 초기화 
        input();
        // 2. 함수 실행 
        rec_func(1);
        System.out.println(sb.toString()); //3. 최종 출력

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
