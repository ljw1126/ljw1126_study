

import java.util.*;
import java.io.*;

public class ex1_1260practice {
    
    static FastReader scan = new FastReader();
    static StringBuilder sb = new StringBuilder();

    static int N, M, V;
    static ArrayList<Integer>[] adj;
    static boolean[] visit;

    static void input() {
        N = scan.nextInt(); // 정점의 수 
        M = scan.nextInt(); // 간선의 수 
        V = scan.nextInt(); // 시작점
        
        /* TODO */
        // 인접 행렬의 경우 adj = new int[N+1][N+1];
        adj = new ArrayList[N+1];
        for(int i=1; i <= N ; i++){
            adj[i] = new ArrayList<Integer>();
        }
        // 인접행렬의 경우 adj[x][y] = 1; adj[x][y] = 1; 처리함 , int 배열의 경우 기본 0인듯
        for(int i=0; i < M; i++){
            int a = scan.nextInt(), b = scan.nextInt();
            adj[a].add(b);
            adj[b].add(a);
        }
        
       // System.out.println(Arrays.toString(adj));
        for(int i= 1; i <=N ; i++)
            Collections.sort(adj[i]);
        
    }

    // x 를 갈 수 있다는 걸 알고 방문한 상태
    static void dfs(int start) {
        /* TODO */
        visit[start] = true; 
        sb.append(start).append(' ');

        for(int y : adj[start]){
            //if(adj[start][y] == 0) continue;    // 인접배열사용시
            if(visit[y]) continue;
            dfs(y);
        }
    }

    // start 에서 시작해서 갈 수 있는 정점들을 모두 탐색하기
    static void bfs(int start) {
        Queue<Integer> que = new LinkedList<>();
        /* TODO */
        que.add(start);
        visit[start] = true;

        while(!que.isEmpty()){
            int vertex = que.poll();
            sb.append(vertex).append(' ');
            for(int i: adj[vertex]){
                //if(adj[start][i] == 0) continue;    // 인접배열사용시
                if(visit[i]) continue; 

                visit[i] = true;
                que.add(i);
            }
        }
    }

    static void pro() {
        // DFS, BFS 결과 구하기
        /* TODO */
        visit = new boolean[N+1];
        dfs(V);
        /* TODO */
        sb.append('\n');
        for(int i=1; i<=N;i++) visit[i] = false;
        bfs(V);

        System.out.println(sb);
    }

    public static void main(String[] args) {
        input();
        pro();
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
