package algorithm.ShortestPath.personal;

import java.util.*;
import java.io.*;
/*
    택배 - 골드 4 
    https://www.acmicpc.net/problem/1719

    - 그러니깐 최단거리 값이 되는 부모노드가 들어가야 한다 이말이네 

    # 예제 입력 1 
    6 10      // n,m ( n: 집하장의 개수 200이하 / m : 집하장의 경로 수 10000이하)
    1 2 2
    1 3 1
    2 4 5
    2 5 3
    2 6 7
    3 4 4
    3 5 6
    3 6 7
    4 6 4
    5 6 2

    # 예제 출력 1
    - 2 3 3 2 2            // 1행 6열에 2의 뜻이 1->2->5->6 으로 가면 최단인데 자기자신 제외한 첫번째 거쳐가는 노드를 말하네 
    1 - 1 4 5 5
    1 1 - 4 5 6
    3 2 3 - 6 6
    2 2 3 6 - 6
    5 5 3 4 5 -


    211216 직접 품(힌트로 부모노드를 가르켜야 한다는거 읽음) 
           ,플로이드 와샬 알고리즘으로 푸는 방법있다함


*/
public class ex8_1719 {
    
    static MyReader scan = new MyReader();
    static StringBuilder sb = new StringBuilder();

    static ArrayList<Edge>[] adj;
    static int n,m;
    static int[] dist,paths;

    static class Edge{
        int to;
        int weight;

        public Edge(int _to, int _weight){
            this.to = _to;
            this.weight = _weight;
        }
    }

    static class Info{
        int vertex, dist, parent;

        public Info(int _vertex, int _dist, int _parent){
            this.vertex = _vertex;
            this.dist = _dist;
            this.parent = _parent;
        }
    }


    static void input(){
        n = scan.nextInt();
        m = scan.nextInt();

        dist = new int[n+1];
        paths = new int[n+1];
        
        adj = new ArrayList[n+1];
        for(int i=1; i<=n ; i++) adj[i] = new ArrayList();

        for(int i=0; i< m; i++){
            int from = scan.nextInt()
                , to = scan.nextInt()
                , weight = scan.nextInt();
            adj[from].add(new Edge(to, weight));
            adj[to].add(new Edge(from, weight));
        }
    }

    static void bfs(int start){
        
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[start] = 0;

        PriorityQueue<Info> pq = new PriorityQueue<>(Comparator.comparingInt(o->o.dist));
        pq.add(new Info(start, 0, -1));

        while(!pq.isEmpty()){
            Info info = pq.poll();

            if(dist[info.vertex] < info.dist) continue;

            for(Edge e : adj[info.vertex]){
                if(dist[info.vertex] + e.weight >= dist[e.to]) continue;
               
                dist[e.to] = dist[info.vertex] + e.weight;

                if(info.parent == -1){
                    paths[e.to] = e.to;
                    pq.add(new Info(e.to, dist[e.to], e.to));
                }else {
                    paths[e.to] = info.parent;
                    pq.add(new Info(e.to, dist[e.to], info.parent));
                }
            }
        }
        
        System.out.println("확인용 : " + start);
        System.out.println(Arrays.toString(dist));
    }

    static void pro(){
        for(int i=1; i <= n ; i++){ // 1번~n까지 집하장 최단 경로 구함 
            bfs(i);
            for(int j=1;j<=n;j++){
                if(i==j) sb.append('-').append(" ");
                else sb.append(paths[j]).append(" ");
            }
            sb.append('\n');
        }
        
        System.out.println(sb);
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
