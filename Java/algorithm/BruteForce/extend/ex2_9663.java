package extend;

import java.util.*;
import java.io.*;
/*
    [응용2]	N Queen 		
    https://www.acmicpc.net/problem/9663
    - 대표적인 백트래킹 문제 
    - 불필요한 탐색을 많이 해서 시간초과 발생함 
     ``````````````````````````````````````````````````````````````````````
    [문제 풀이 구조]

    static int N, ans;
    static int[] col; // col[i] : i번 행의 퀸은 col[i]번 열에 놓았다는 기록

    // row번 ~ N번 행에 대해서 가능한 퀸을 놓는 경우의 수 구하기 
    static void rec_func(int row){
        if(row == N+1){ // 각 행마다 하나씩 잘 놓았다. 
            // value가 S랑 같은지 확인하기
            if(validity_check()){
                ans++;      // 갯수만 세면 된다 함 
            }
        }else{
            for(int c=1; c<=N; c++){
                col[row] = c;
                rec_func(row+1);
                col[row] = 0;
            }
        }

    }



    ``````````````````````````````````````````````````````````````````````
*/
public class ex2_9663 {
    static int N, ans;
    static int[] col; // col[i] : i번 행의 퀸은 col[i]번 열에 놓았다는 기록

    static StringBuilder sb = new StringBuilder();

    static void input(){
        FastReader scan = new FastReader();
        N = scan.nextInt();
        col = new int[N+1];
    }

    static boolean attackable(int r1, int c1, int r2, int c2){
        // 열이 같은 경우 수직 상에 존재
        if(c1 == c2) return true;
        // 왼쪽 -> 오른쪽 아래 대각선의 경우 행-열 할 경우 0이됨 
        if(r1-c1 == r2-c2) return true; 
        // 오른쪽 -> 왼쪽 아래 대각선의 경우 행+열 값이 다 같다
        if(r1+c1 == r2+c2) return true;
        return false;
    }

    static boolean validity_check(){ 
        for(int i=1 ; i <= N ; i++){
            // (i, col[i])  i행에 col[i]열 
            for(int j=1; j<i ; j++){
                // (j, col[j])
                if(attackable(i, col[i], j, col[j])){
                    return false;
                }
            }
        }
        return true; 
    }

    // 문제 해결 함수(재귀용법 사용)
    // row번 ~ N번 행에 대해서 가능한 퀸을 놓는 경우의 수 구하기
    static void rec_func(int row){
        if(row == N+1){ // 각 행마다 하나씩 다 놓았을 떄 
            if(validity_check()){ // 서로 공격하는 퀸들이 없는 경우 
                ans++;
            } 
        }else{ 
            for(int c = 1; c <= N; c++){
                col[row] = c;
                rec_func(row+1);
                col[row] = 0;
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
