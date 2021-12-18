package recommandEx;
/*
    적록색약 - 골드 5 
    https://www.acmicpc.net/problem/10026

    - visit 를 3차원 배열로 만들어서 하는 것을 생각하지 못함.. (211218 , 오늘 하나 배움)

    ※ 참고 블로그 
    https://girawhale.tistory.com/21
*/

import java.util.*;
import java.io.*;

public class ex14_10026 {
    
    static MyReader scan = new MyReader();
    static StringBuilder sb = new StringBuilder();

    static int N, cnt;
    static boolean[][][] visit;
    static char[][] arr;
    static int[][] dir = {{-1,0},{0,-1},{1,0},{0,1}};

    static void dfs(int k, int x , int y, char txt){
        visit[k][x][y] = true;

        for(int i=0;i<4;i++){
            int dx = x + dir[i][0] , dy = y + dir[i][1];

            if(dx < 0 || dy < 0 || dx >= N || dy >= N) continue;
            if(visit[k][dx][dy]) continue;
            if(txt != arr[dx][dy]) continue;

            dfs(k,dx,dy,txt);
        }
    }

    static void pro(){
        for(int k=0; k < 2; k++){
            int ans = 0;
            for(int i=0;i<N;i++){
                for(int j=0;j<N;j++){
                    if(!visit[k][i][j]){
                        dfs(k,i,j, arr[i][j]);
                        ans++;
                    }
                    if(arr[i][j] == 'G') arr[i][j] = 'R';
                }
            }
            sb.append(ans).append(" ");
        }
        
        System.out.println(sb);
    }

    static void input(){
        N = scan.nextInt();
        visit = new boolean[2][N][N];
        arr = new char[N][N];

        for(int i=0;i<N;i++){
          arr[i] = scan.next().toCharArray();
        }
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
