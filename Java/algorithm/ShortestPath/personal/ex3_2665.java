package algorithm.ShortestPath.personal;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

/*
    미로 만들기 - 골드 4
    https://www.acmicpc.net/problem/2665

    검은방을 최소 몇개 흰방으로 만들어야 끝까지 갈 수 있는가?
    (없는 경우 0 출력)
*/
public class ex3_2665 {
    
    static MyReader scan = new MyReader();
    static StringBuilder sb = new StringBuilder();

    static int N;
    static boolean[][][] visit;
    static int[][] map;
    static int[][] dir = {{-1,0},{1,0},{0,-1},{0,1}};
    
    static class Node implements Comparable<Node>{
        int x ;
        int y ;
        int cnt; 

        public Node(int _x, int _y, int _cnt){
            this.x = _x;
            this.y = _y;
            this.cnt = _cnt;
        }

        @Override
        public int compareTo(Node o) {
            return cnt - o.cnt;
        }
    }
    // 경로 구하는 애 
    static void bfs(int start_x, int start_y){
        
        // for(int i=0; i<N ;i++){
        //     for(int j=0;j<N;j++){
        //         dist[i][j] = -1;
        //     }
        // }

        PriorityQueue<Node> que = new PriorityQueue();
        que.add(new Node(0,0,0));
        
        while(!que.isEmpty()){
            Node node = que.poll();

            if(node.x == N-1 && node.y == N-1){
                System.out.println(node.cnt);
                return;
            }

            for(int k = 0 ; k < 4 ; k++){
                int dx = node.x + dir[k][0], dy = node.y + dir[k][1];

                if(dx < 0 || dy < 0 | dx >= N || dy >= N) continue;
                
                if(map[dx][dy] == 1 && !visit[dx][dy][node.cnt]){
                    visit[dx][dy][node.cnt] = true;
                    que.add(new Node(dx,dy,node.cnt));
                }
                else if(map[dx][dy] == 0 && !visit[dx][dy][node.cnt]){
                    visit[dx][dy][node.cnt+1] = true;
                    que.add(new Node(dx,dy,node.cnt+1));
                }
            }
        }

    }
    /*
    // 완전 탐색으로 조합 구할 애 
    static void dfs(int start, int cnt){
        bfs(0,0,cnt);
        if(cnt > B || start > B) return;

        map[blank[start][0]][blank[start][1]] = 1;
        dfs(start+1 , cnt+1);

        map[blank[start][0]][blank[start][1]] = 0;
        dfs(start+1 , cnt);
    }
    */
    static void input(){
        N = scan.nextInt();
        map = new int[N][N];
        // 아스키 코드에서 -'0'(48)을 뺌 
        for(int i=0;i<N;i++){
            String str = scan.nextLine();
            for(int j=0;j<str.length();j++){
                map[i][j] = str.charAt(j) - '0';
            }
        }

        visit = new boolean[N][N][N*N];
    }

    static void pro(){
        //벽을 구해야 함 .. 
        // blank = new int[N*N][2];
        // for(int i=0;i<N;i++){
        //     for(int j=0;j<N;j++){
        //         if(map[i][j] == 0){
        //             blank[B][0] = i;
        //             blank[B][1] = j;
        //             B++;
        //         }
        //     }
        // }
        bfs(0,0);
       
    }

    public static void main(String[] args) {
        input();
        pro();
    }

    static class MyReader{
        BufferedReader br ;
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
