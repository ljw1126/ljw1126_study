
import java.util.*;
import java.io.*;

/*
    https://www.acmicpc.net/problem/1260
*/
public class ex1_1260 {

    static FastReader scan = new FastReader();
    static StringBuilder sb = new StringBuilder();

    static int N, M, V;
    static ArrayList<ArrayList<Integer>> graph;
    static ArrayList<Integer> list;
    
    //x를 갈 수 있다는 걸 알고 방문한 상태
    static void dfs(int x){
        /* To do */
        visit[x] = true; 
        sb.append(x).append(' ');
        for(int y=1; y <= N ; y++){
            if(adj[x][y] == 0) continue;
            if(visit[y] == 1) continue; 
            dfs(y);
        }
    }

    //start에서 시작해서 갈 수 있는 정점들을 모두 탐색하기 
    static void bfs(int x){
        Queue<Integer> que = new LinkedList<>();
        /* To do */
        que.add(x);
        visit[x] = true;
        
        while(!que.isEmpty()){
            x = que.poll();
            sb.append(x).append(' ');

            for(int y=1; y<=N; y++){
                if(adj[x][y] == 0) continue;
                if(visit[y] == 1) continue;
                
            }
        }
    }

    static void pro(){
        visit = new boolean[N+1];
        dfs(V);
        sb.append('\n');

        
        //모든 x에 대해서 adj[x] 정렬하기 
        /* To do */

        //DFS, BFS 결과 구하기
        /* To do */

        //결과 출력
        System.out.println();
    }

    static void input(){
        N = scan.nextInt();
        M = scan.nextInt();
        V = scan.nextInt(); 

        graph = new ArrayList<>();
        
        for(int i=1; i <= M ; i++) {
            list = new ArrayList<>();
            list.add(scan.nextInt());
            list.add(scan.nextInt());
            graph.add(list);
        }
    }

    public static void main(String[] args) {
        input();
        System.out.println(graph);
    }

    static class FastReader{
        BufferedReader br;
        StringTokenizer st; 

        public FastReader(){
            br = new BufferedReader(new InputStreamReader(System.in));
        }

        public FastReader(String s) throws FileNotFoundException{
            br = new BufferedReader(new FileReader(new File(s)));
        }

        String next(){
            while(st == null || !st.hasMoreElements()){
                try{
                    st = new StringTokenizer(br.readLine());
                }catch(IOException e){
                    e.printStackTrace();
                }
            }
            return st.nextToken();
        }

        int nextInt(){
            return Integer.parseInt(next());
        }

        long nextLong(){
            return Long.parseLong(next());
        }

        double nextDouble(){
            return Double.parseDouble(next());
        }

        String nextLine(){
            String str = "";
            try{
                str = br.readLine();
            }catch(IOException e){
                e.printStackTrace();
            }
            return str;
        }
    }

}
