package recommandEx;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class ex1_1012practice211203 {
    
    static StringBuilder sb = new StringBuilder();
    static MyReader scan = new MyReader();

    static int T,M,N,K,group_cnt;
    static int[][] area;
    static boolean[][] visited;
    static int[][] dir = {{-1,0},{0,-1},{1,0},{0,1}};

    static void dfs(int x , int y){

        visited[x][y] = true;

        for(int k=0; k < 4 ;k++){
            int dx = x + dir[k][0] , dy = y + dir[k][1];

            /*
                1. 범위내인지
                2. 방문여부
                3. 0이라서 이동 불가한지 
            */

            if(dx < 0 || dy < 0 || dx >= M || dy >= N) continue;
            if(visited[dx][dy]) continue;
            if(area[dx][dy] == 0) continue;

            dfs(dx,dy);
        }

    }

    static void pro(){
        group_cnt = 0;
        for(int i=0;i < M;i++){
            for(int j=0; j < N;j++){
                if(!visited[i][j] && area[i][j] == 1){
                    group_cnt++;
                    dfs(i,j);
                }
            }
        }
        sb.append(group_cnt).append('\n');
    }

    static void input(){
        
        M = scan.nextInt(); // 가로길이
        N = scan.nextInt(); // 세로길이
        K = scan.nextInt(); // 배추가 심어져 있는 위치 갯수
    
        area = new int[M][N];    
        visited = new boolean[M][N];

        for(int i=0 ; i < K;i++){
            int a = scan.nextInt(), b = scan.nextInt();
            area[a][b] = 1;
        }
    }

    public static void main(String[] args) {
        T = scan.nextInt(); // 테스트 수
        for(int i=0;i<T;i++){
            input();
            pro();
        }
        System.out.println(sb);
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
            String str ="";
            try {
                str = br.readLine();
            } catch (Exception e) {
                e.printStackTrace();
            }
            return str;
        }
    }
}
