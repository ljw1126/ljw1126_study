package recommandEx;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
    숫자판 점프 - 실버2 
    https://www.acmicpc.net/problem/2210

    # 예제입력 
    1 1 1 1 1
    1 1 1 1 1
    1 1 1 1 1
    1 1 1 2 1
    1 1 1 1 1

    # 예제출력 
    15

    // 6을 구하는게 아니라 중복되지 않은 6자리 숫자 갯수를 구하는거네 

    참고블로그 
    https://codingtalk.tistory.com/119
*/

import java.util.*;
import java.io.*;

public class ex15_2210 {
    
    static MyReader scan = new MyReader();
    static StringBuilder sb = new StringBuilder();

    static ArrayList<String> ans = new ArrayList<>();
    static int[][] arr;
    static int[][] dir = {{-1,0},{0,-1},{1,0},{0,1}};

    static void input(){
        arr = new int[5][5];
        for(int i=0;i<5;i++){
            for(int j=0;j<5;j++){
                arr[i][j] = scan.nextInt();
            }
        }
    }
    
    static void dfs(int x, int y, int depth, String s){
        
        if(depth == 6){
            if(!ans.contains(s)){
                ans.add(s);
            }
            return;
        }

        for(int i=0; i < 4; i++){
            int dx = x + dir[i][0] , dy = y + dir[i][1];

            if(dx < 0 || dy < 0 || dx >= 5 || dy >= 5) continue;

            dfs(dx,dy, depth + 1, s + arr[dx][dy]);
        }
    }

    static void pro(){
        for(int i=0 ; i < 5 ; i++){
            for(int j=0; j < 5; j++){
                dfs(i,j,1,arr[i][j] + "");
            }
        }
        
        System.out.println(ans.size());
    }
    public static void main(String[] args) {
        input();
        pro();

    }

    static class MyReader{
        BufferedReader br;
        StringTokenizer st;

        public MyReader(){
            br = new BufferedReader(new InputStreamReader(System.in));
        }

        String next(){
            while( st == null || !st.hasMoreElements()){
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
            String str = "";
            try {
                str = br.readLine();
            } catch (Exception e) {
                e.printStackTrace();
            }

            return str; 
        }
    }
}
