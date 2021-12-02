
import java.util.*;
import java.io.*;

/*
    https://www.acmicpc.net/problem/1260

    인접행렬 사용시 
    시간/공간 : O(V^2)

    인접리스트 사용시
    시간 : O(ElogE)    //문제 내용대로 작은 간선부터 해야하므로 정렬해야함 
    공간 : O(E)

*/
public class ex1_1260 {

    static FastReader scan = new FastReader();
    static StringBuilder sb = new StringBuilder();

    static int N, M, V;
    static ArrayList<Integer>[] adj;
    static boolean[] visit;

    //x를 갈 수 있다는 걸 알고 방문한 상태
    static void dfs(int start){
        
        //x를 방문했다고 표시 
        visit[start] = true; 
        sb.append(start).append(' ');

        //x에서 갈 수 있는 곳들을 작은 번호부터 모두 방문함
        for(int y : adj[start]){
            //y를 이미 갈 수 있다는걸 알면 굳이 갈 필요없다.
            //if(adj[x][y] == 0) continue; //인접행렬사용시
            if(visit[y]) continue;
            //y에서 갈 수 있는 곳을 확인한다 
            dfs(y);
        }
    }

    //start에서 시작해서 갈 수 있는 정점들을 모두 탐색하기 
    static void bfs(int start){
        Queue<Integer> que = new LinkedList<>();
        // start는 방문가능한 점이므로que에 넣어줌
        que.add(start); // 초기값 셋팅
        visit[start] = true;
        
        while(!que.isEmpty()){
            int x = que.poll();
            sb.append(x).append(' ');

            for(int y : adj[x]){
                if(visit[y]) continue;

                que.add(y);
                visit[y] = true;
            }
        }
    }

    static void pro(){
        visit = new boolean[N+1]; // default 는 전부 false
        dfs(V);
        sb.append('\n');
        for(int i=1; i<=N;i++) visit[i] = false;
        bfs(V);
        System.out.println(sb);
    }

    static void input(){
        N = scan.nextInt();
        M = scan.nextInt();
        V = scan.nextInt(); 

        adj = new ArrayList[N+1];
        for(int i=1; i<= N ; i++)
            adj[i] = new ArrayList<>();
        
        for(int i=0; i < M ; i++) {
            int x = scan.nextInt(), y= scan.nextInt();
            adj[x].add(y);
            adj[y].add(x);
        }

        for(int i=1; i<= N ; i++)
            Collections.sort(adj[i]);

    }

    public static void main(String[] args) {
        input();
        pro();
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
