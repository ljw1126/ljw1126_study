
import java.util.*;
import java.io.*;

public class ex5_2178practice211204 {
    
    static StringBuilder sb = new StringBuilder();
    static MyReader scan = new MyReader();

    static int N,M;
    static String[] adj;
    static int[][] dist;
    static boolean[][] visit;
    static int[][] dir = {{0,1},{1,0},{-1,0},{0,-1}};
    /*
        1은 이동가능한 칸
        0은 이동불가능한 칸 
    */
    static void BFS(int x , int y){
        Queue<Integer> que = new LinkedList<>();
        que.add(x);
        que.add(y);
        visit[x][y] = true;
        dist[x][y] = 1;

        while(!que.isEmpty()){
            int xx = que.poll();
            int yy = que.poll();

            for(int k=0 ; k < 4 ; k++){
                int dxx = xx + dir[k][0];
                int dyy = yy + dir[k][1];

                if( dxx < 0 || dyy < 0 || dxx >= N || dyy >= M) continue;
                if(visit[dxx][dyy]) continue;
                if(adj[dxx].charAt(dyy) == '0') continue;

                visit[dxx][dyy] = true;
                dist[dxx][dyy] = dist[xx][yy] + 1;
                que.add(dxx);
                que.add(dyy);
            }
        }
    }

    static void pro(){
        BFS(0,0);
        //for(int[] i : dist) System.out.println(Arrays.toString(i));
        System.out.println(dist[N-1][M-1]);
    }

    static void input(){
        N = scan.nextInt();
        M = scan.nextInt();

        adj = new String[N];
        dist = new int[N][M];
        visit = new boolean[N][M];

        for(int i=0; i < N ; i++)
            adj[i] = scan.nextLine();
    
    }

    public static void main(String[] args) {
        input();
        pro();
    }

    static class MyReader{
        BufferedReader br;
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
