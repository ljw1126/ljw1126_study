package recommandEx;

import java.util.*;
import java.io.*;

/*
    안전 영역 - 실버 1
    https://www.acmicpc.net/problem/2468

    높이는 1이상 100 이하의 정수이다
    211221 최소 연결개수는 1이기 때문에 ans 값을 1로 초기화 해야 했음..
*/
public class ex16_2468 {
    
    static MyReader scan = new MyReader();
    static StringBuilder sb = new StringBuilder();

    static int N, max_h; 
    static int[][] areas;
    static boolean[][] visit;
    static int[][] dir = {{-1,0},{0,-1},{1,0},{0,1}};

    static void input(){

        N = scan.nextInt();
        areas = new int[N][N];

        for(int i=0;i<N;i++){
            for(int j=0;j<N;j++){
                int h = scan.nextInt();
                areas[i][j] = h;
                max_h = Math.max(max_h, h);
            }
        }
    }

    static void dfs(int x , int y, int h){
        visit[x][y] = true;

        for(int i=0;i<4;i++){
            int dx = x + dir[i][0], dy = y + dir[i][1];
            
            if(dx < 0 || dy < 0 || dx >= N || dy >= N) continue;
            if(visit[dx][dy]) continue;
            if(areas[dx][dy] <= h) continue;

            dfs(dx,dy,h);
        }
    }

    static void pro(){
        int ans = 1; // 최소 연결개수 1이여야 됨..
        int area;
        
        for(int h=1; h <= max_h; h++){
            
            area = 0;
            visit = new boolean[N][N];

            for(int i=0; i < N; i++){
                for(int j=0; j < N ; j++){
                    if(areas[i][j] > h && !visit[i][j]){
                        dfs(i,j,h);
                        area++;
                    }
                }
            }

            ans = Math.max(ans , area);
        }
        System.out.println(ans);
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
