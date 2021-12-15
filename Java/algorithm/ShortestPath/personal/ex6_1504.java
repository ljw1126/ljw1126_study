package algorithm.ShortestPath.personal;

import java.util.*;
import java.io.*;
/*  

    특정한 최단 경로 - 골드 4 
    https://www.acmicpc.net/problem/1504

    # 예제 입력 
    4 6       // 정점수N 간선수 E
    1 2 3
    2 3 3
    3 4 1
    1 3 5
    2 4 5
    1 4 4
    2 3       // 2번 3번 노드를 통과해서 1에서 4번(N) 노드로 가는 최단 경로의 길이 구하라 

    # 예제 출력
    7         // 답이 없으면 -1

    참고 블로그 -- 10분 고민하고 참고 
    https://steady-coding.tistory.com/82

    ※ 해당 패턴에 대해 각각 구해서 더한다는 아이디어
       INTEGER.MAXVALUE 더하면 에러 나니 가중치 최대치를 정해서 해야함 !
    (1) 1 -> v1 -> v2 -> N
    (2) 1 -> v2 -> v1 -> N

*/
public class ex6_1504 {

    static MyReader scan = new MyReader();
    static StringBuilder sb = new StringBuilder();

    static int N,E,v1,v2;
    static int[] dist;
    static ArrayList<Edge>[] adj;
    static boolean[] visit;
    static int INF = 200000000; // 간선의 최대 개수 20만, 가중치 최댓값 1000

    static class Edge{
        int to, weight;
        public Edge(int _to, int _weight){
            this.to = _to;
            this.weight = _weight;
        }
    }

    static class Info{
        int vertex;
        int dist;

        public Info(int _vertex , int _dist){
            this.vertex = _vertex;
            this.dist = _dist;
        }
    }

    static void input(){
        N = scan.nextInt(); // 정점의 개수 
        E = scan.nextInt(); // 간선의 개수 

        visit = new boolean[N+1];
        dist = new int[N+1];
        adj = new ArrayList[N+1];
        for(int i=1; i <= N ; i++ ) adj[i] = new ArrayList();

        for(int i=0; i < E ; i++){
            int from = scan.nextInt();
            int to =  scan.nextInt();
            int weight = scan.nextInt();
            adj[from].add(new Edge(to, weight));
            adj[to].add(new Edge(from, weight));
        }

        v1 = scan.nextInt(); // 확인 경로 1
        v2 = scan.nextInt(); // 확인 경로 2
    }

    static int dijkstra(int start, int target){
        for(int i=1; i <= N; i++) dist[i] = INF;
        dist[start] = 0;

        PriorityQueue<Info> que = new PriorityQueue<>(Comparator.comparingInt(o -> o.dist));
        que.add(new Info(start,0));

        while(!que.isEmpty()){
            Info info = que.poll();

            if(dist[info.vertex] < info.dist) continue;
            
            for(Edge eg : adj[info.vertex]){
                if(dist[info.vertex] + eg.weight >= dist[eg.to]) continue;
                
                dist[eg.to] = dist[info.vertex] + eg.weight;
                que.add(new Info(eg.to, dist[eg.to]));
            }

        }

        return dist[target];
    }

    static void pro(){
        
        int param1 = 0;
        param1 += dijkstra(1,v1);
        param1 += dijkstra(v1,v2);
        param1 += dijkstra(v2,N);       // 여기서 더하는데 INTEGER.MAXVALUE하면 당연히 터지지 ..

        int param2 = 0;
        param2 += dijkstra(1,v2);
        param2 += dijkstra(v2,v1);
        param2 += dijkstra(v1,N);

        int ans = -1;
        if(param1 < INF && param2 < INF ) ans = Math.min(param1, param2);

        System.out.println(ans);
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
            while( st == null || !st.hasMoreElements()){
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
