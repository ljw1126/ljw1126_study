package algorithm.Tree.recommandEx;

import java.util.*;
import java.io.*;
/*
    노드 사이의 거리 - 골드 5 
    https://www.acmicpc.net/problem/1240 

    직접 풀어봤으나.. 제출시 틀렸다 함
    - root를 구해서 dist를 구한 뒤 빼는 방식은 안되고.. 
    - start, end 입력에 따라 각각 dist를 구하는 방식은 되는 듯 .. (방식만 알고 직접 풀이함.. 정돈이 필요할듯)
*/
public class ex7_1240 {
    
    static MyReader scan = new MyReader();
    static StringBuilder sb = new StringBuilder();

    static int N,M,root;
    static ArrayList<Edge>[] adj;
    static boolean[] visit;
    static int[] dist;

    static class Edge{
        int to;
        int weight;
        public Edge(int _to, int _weight){
            this.to = _to;
            this.weight = _weight;
        }
    }

    static class Info{
        int vertex;
        int weight;

        public Info(int _vertex, int _weight){
            this.vertex = _vertex;
            this.weight = _weight;
        }
    }

    static int bfs(int start, int end){
        dist = new int[N+1];
        Arrays.fill(visit, false);

        Queue<Integer> que = new LinkedList<>();
        que.add(start);
        dist[start] = 0;
        visit[start] = true;

        while(!que.isEmpty()){
            int x = que.poll();

            if(x == end) break;

            for(Edge y : adj[x]){
                if(visit[y.to]) continue;
                dist[y.to] = dist[x] + y.weight;
                visit[y.to] = true;
                que.add(y.to);
            }
        }

        return dist[end];
    }



    static void pro(){
        
        for(int i=0; i < M ; i++){
            int start = scan.nextInt(), end = scan.nextInt();
            sb.append(bfs(start, end)).append('\n');
        }

        System.out.println(sb);
    }

    static void input(){
        N = scan.nextInt();
        M = scan.nextInt();

        visit = new boolean[N+1];
        adj = new ArrayList[N+1];

        for(int i=1; i<=N;i++) adj[i] = new ArrayList();

        for(int i=0; i < N-1 ; i++){
            int from = scan.nextInt()
                ,to = scan.nextInt()
                ,weight = scan.nextInt();

            adj[from].add(new Edge(to, weight));
            adj[to].add(new Edge(from, weight));
        }
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
