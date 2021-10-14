package extend;

import java.util.*;
import java.io.*;

public class ex3_practice {
    
    static int N, S, ans;
    static int[] nums; 

    static StringBuilder sb = new StringBuilder();

    static void input(){
        FastReader scan = new FastReader();
        N = scan.nextInt();
        S = scan.nextInt();
        nums = new int[N+1];
        for(int i= 1; i <= N ; i++) nums[i] = scan.nextInt();
    }

    // 문제 해결 함수(재귀용법 사용)
    static void rec_func(int k, int value){
        if(k == N+1 ){
           // 결과값 처리 
           if(S == value)ans++;
        }else{  
           // 반복문 내에 재귀용법으로 처리하는 부분
            rec_func(k+1, value + nums[k]);
            rec_func(k+1, value);
        
        }
    }

    public static void main(String[] args) {
        // 1. 입력값 , 초기화 
        input();
        // 2. 함수 실행 
        rec_func(1,0);
        if( S == 0 ){
            ans--;
        }
        System.out.println(ans); //3. 최종 출력

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
