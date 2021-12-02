import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;


public class ex1_1260practice211202 {
    
    static StringBuilder sb = new StringBuilder();
    static MyReader scan = new MyReader();

    static int N,M,V;
    static ArrayList<Integer>[] adj;
    static boolean[] visited;

    // 깊이 우선 탐색, stack 생각
    static void dfs(int start){
        visited[start] = true;
        sb.append(start).append(' ');
        
        for(int i : adj[start]){
            if(visited[i]) continue;

            dfs(i);
        }
    }

    // 넓이 우선 탐색, queue 생각
    static void bfs(int start){
        Queue<Integer> que = new LinkedList();
        que.add(start);
        visited[start] = true;

        while(!que.isEmpty()){
            int x = que.poll();
            sb.append(x).append(' ');
            for(int j : adj[x]){
                if(visited[j]) continue;

                que.add(j);
                visited[j] = true;
            }
        }

    }


    public static void pro(){
        visited = new boolean[N+1];
        dfs(V);
        for(int i=1;i<=N;i++) visited[i] = false;
        sb.append("\n");
        bfs(V);
        System.out.println(sb); // 결과 출력
    }

    public static void input(){
        N = scan.nextInt();
        M = scan.nextInt();
        V = scan.nextInt();
        adj = new ArrayList[N+1];

        for(int i=1;i<=N;i++)
            adj[i] =  new ArrayList<>();

        for(int i=1;i<=M;i++){
            int a = scan.nextInt(), b = scan.nextInt();
            adj[a].add(b);
            adj[b].add(a);
        }

        for(int i=1; i<=N;i++)
            Collections.sort(adj[i]);

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
