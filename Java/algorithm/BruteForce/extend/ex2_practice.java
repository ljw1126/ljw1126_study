package extend;

import java.util.*;
import java.io.*;

public class ex2_practice {
    static int N, ans;
    static int[] col;

    static void input(){
        FastReader scan = new FastReader();
        N = scan.nextInt();
        col = new int[N+1];
    }

    static boolean attackable(int r1, int c1, int r2, int c2){
        if( c1 == c2 ) return true; //수직인경우 
        if( c1-r1 == c2-r2) return true; // ↘
        if( c1+r1 == c2+r2) return true; //↙
        return false; 
    }

    // 문제 해결 함수(재귀용법 사용)
    static void rec_func(int row){
        if(row == N + 1){
           // 결과값 처리 
            System.out.println(Arrays.toString(col));
            ans++;
        }else{  
           // 반복문 내에 재귀용법으로 처리하는 부분
           for(int c = 1 ; c <= N ; c++){
               boolean possible = true;

               for(int i = 1 ; i <= row-1 ; i++){ // 이전 배치한 퀸의 위치와 현 위치를 검사해서 배치 가능여부 파악
                   if(attackable(row, c, i, col[i])) {
                       possible = false; 
                       break;
                   }
               }

               if(possible){ 
                    col[row] = c;
                    rec_func(row+1);
                    col[row] = 0;
               }
           }

        }
    }

   

    public static void main(String[] args) {
        // 1. 입력값 , 초기화 
        input();
        // 2. 함수 실행 
        rec_func(1);
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
