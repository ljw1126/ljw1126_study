package algorithm.ShortestPath.personal;

import java.io.*;
import java.util.*;
/*
    211209 직접 생각해서 품 
    https://www.acmicpc.net/problem/4485
*/
public class ex4_4485 {
    
    static StringBuilder sb = new StringBuilder();
    static MyReader scan = new MyReader();

    static ArrayList<Integer>[] adj;
    static int input, N;
    static int[][] dist;
    static int[][] dir = {{-1,0},{0,-1},{1,0},{0,1}};
    static int T = 1;

    static void dijkstra(int start_x, int start_y, int num){

        for(int i=0; i < N ; i++){
            for(int y = 0; y < N ; y++){
                dist[i][y] = Integer.MAX_VALUE;
            }
        }

        Queue<Integer> que = new LinkedList();
        dist[0][0] = adj[0].get(0);
        que.add(start_x);
        que.add(start_y);

        while(!que.isEmpty()){
            int x = que.poll(), y = que.poll();

            for(int i=0;i<4;i++){
                int dx = x + dir[i][0] , dy = y + dir[i][1];

                if(dx < 0 || dy < 0 || dx >= N || dy >= N) continue;
                if(dist[x][y] + adj[dx].get(dy) >= dist[dx][dy]) continue;

                dist[dx][dy] = dist[x][y] + adj[dx].get(dy);
                que.add(dx);
                que.add(dy);
            }
        }

          sb.append("Problem ")
            .append(num)
            .append(": ")
            .append(dist[N-1][N-1])
            .append('\n');
        
    
    }

    public static void main(String[] args) {
        while(true){
            N = scan.nextInt();
            if(N == 0) break;

            dist = new int[N][N];
            adj = new ArrayList[N];

            for(int i=0; i < N ; i++){
                adj[i] = new ArrayList();
                for(int y = 0; y < N ; y++){
                    input = scan.nextInt();
                    adj[i].add(input);
                }
            }

            dijkstra(0,0, T++);
        }
        // 결과 출력
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
