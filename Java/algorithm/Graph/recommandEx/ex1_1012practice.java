package recommandEx;

import java.util.*;
import java.io.*;

public class ex1_1012practice {
    
    static FastReader scan = new FastReader();
    static StringBuilder sb = new StringBuilder();

    static int T,M,N,K, group_cnt;
    static boolean[][] visit;
    static ArrayList<Integer> group;
    static int[][] areas;
    static int[][] dir = {{1,0},{0,1},{-1,0},{0,-1}};

    public static void input(){
        T = scan.nextInt();
        for(int i=0 ; i < T ; i++){
            M = scan.nextInt();
            N = scan.nextInt();
            K = scan.nextInt();

            visit = new boolean[M][N];
            areas = new int[M][N];


            for(int j = 0; j < K ; j++){
                int x = scan.nextInt(), y = scan.nextInt();
                areas[x][y] = 1;
            }

            pro();
        }
    }

    public static void dfs(int x , int y){
        group_cnt++;
        visit[x][y] = true;

        for(int i=0;i<4;i++){
            int dx = x + dir[i][0];
            int dy = y + dir[i][1];

            if(dx < 0 || dy < 0 || dx >= M || dy >= N) continue;
            if(visit[dx][dy]) continue; 
            if(areas[dx][dy] == 0 ) continue;
            
            dfs(dx, dy);
        }
    }

    public static void pro(){
        group = new ArrayList<Integer>();

        for(int i=0 ; i < M ; i++){
            for(int j=0; j < N ; j++){
                if(!visit[i][j] && areas[i][j] == 1){
                    group_cnt = 0 ;
                    dfs(i,j);
                    group.add(group_cnt);
                }
            }
        }

        sb.append(group.size()).append('\n');

    }

    public static void main(String[] args) {
        input();
        System.out.println(sb);
    }

    static class FastReader {
        BufferedReader br;
        StringTokenizer st;

        public FastReader() {
            br = new BufferedReader(new InputStreamReader(System.in));
        }

        public FastReader(String s) throws FileNotFoundException {
            br = new BufferedReader(new FileReader(new File(s)));
        }

        String next() {
            while (st == null || !st.hasMoreElements()) {
                try {
                    st = new StringTokenizer(br.readLine());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return st.nextToken();
        }

        int nextInt() {
            return Integer.parseInt(next());
        }

        long nextLong() {
            return Long.parseLong(next());
        }

        double nextDouble() {
            return Double.parseDouble(next());
        }

        String nextLine() {
            String str = "";
            try {
                str = br.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return str;
        }
    }


}
