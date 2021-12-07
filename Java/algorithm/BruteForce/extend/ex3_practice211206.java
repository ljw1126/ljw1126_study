package extend;

import java.util.*;
import java.io.*;

public class ex3_practice211206 {
    
    static StringBuilder sb = new StringBuilder();
    static MyReader scan = new MyReader();

    static int N,S,ans;
    static int[] nums;

    static void input(){
        N = scan.nextInt();
        S = scan.nextInt();

        nums = new int[N+1];
        for(int i=1;i<=N;i++){
            nums[i] = scan.nextInt();
        }
    }
  
    static void rec_func(int k, int value){
        if(k == N+1){
            if(value == S) ans++;
        }else{

            rec_func(k+1, value+nums[k]);
            rec_func(k+1, value);
        }
    }

    public static void main(String[] args) {
        input();
        rec_func(1,0);
        if(S == 0){ // 아무것도 고르지 않은 경우에 ans = 1이므로 
            ans--;
        }
        System.out.println(ans);
    }

    static class MyReader{
        BufferedReader  br;
        StringTokenizer st ;

        public MyReader(){
            br = new BufferedReader(new InputStreamReader(System.in));
        }

        String next(){
            while(st == null || !st.hasMoreElements()){
                try {
                    st = new StringTokenizer(br.readLine());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            return st.nextToken();
        }

        int nextInt(){
            return Integer.parseInt(next());
        }

        Long nextLong(){
            return Long.parseLong(next());
        }

        String nextLine(){
            String str ="";
            try {
                str= br.readLine();
            } catch (Exception e) {
                e.printStackTrace();
            }
            return str;
        }

    }
}
