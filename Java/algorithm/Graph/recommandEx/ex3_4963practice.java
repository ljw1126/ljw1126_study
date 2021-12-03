package recommandEx;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class ex3_4963practice {
 
    static StringBuilder sb = new StringBuilder();
    static MyReader scan = new MyReader();

    static int h,w, group_cnt;
    static int[][] area;
    static boolean[][] visit;   
    static ArrayList<Integer> group;
    static int[][] dir = {{-1,-1},{-1,0},{-1,1},{0,-1},{0,1},{1,-1},{1,0},{1,1}};


    static void dfs(int x, int y){
        visit[x][y] = true;
        group_cnt++;
        for(int i=0; i <8;i++){
            int dx = x + dir[i][0];
            int dy = y + dir[i][1];

            if(dx < 0 || dy < 0 || dx >= h || dy >= w) continue;
            if(visit[dx][dy]) continue;
            if(area[dx][dy] == 0 ) continue;

            dfs(dx,dy);
        }
    }


    static void pro(){
        group = new ArrayList();
        for(int i=0; i < h; i++){
            for(int j=0; j < w; j++){
                if(!visit[i][j] && area[i][j] == 1){
                    group_cnt = 0;
                    dfs(i,j);
                    group.add(group_cnt);
                }
            }
        }
        sb.append(group.size()).append('\n');
    }

    public static void main(String[] args) {
        while(true){
            w = scan.nextInt(); // 행
            h = scan.nextInt(); // 열
            if( h == 0 && w == 0) break;

            area = new int[h][w];
            visit = new boolean[h][w];

            for(int i=0; i < h; i++){
                for(int j=0; j < w; j++){
                    area[i][j] = scan.nextInt();
                }
            }
            pro();
            // for(boolean[] b : visit){
            //     System.out.println(Arrays.toString(b));
            // }
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
