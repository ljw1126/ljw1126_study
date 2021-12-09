package algorithm.ShortestPath.personal;

import java.util.*;
import java.io.*;
/*
    문제 링크 
    https://www.acmicpc.net/problem/11779

    ※ 경로 구할때 다른 방법 참고
    https://for-development.tistory.com/110

    route라는 배열로 parent를 저장하고 ArrayList에 담아서 역으로 출력..

*/
public class ex5_11779other {
    static StringBuilder sb = new StringBuilder();
    static MyReader scan = new MyReader();
    
    static int N,M,start,end;
    static int[] dist, route;
    static ArrayList<Edge>[] edges;
    static ArrayList<Integer> routes;

    static class Edge{
        int to, weight;
        
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

    static void input(){
        N = scan.nextInt();
        M = scan.nextInt();
        
        route = new int[N+1];
        Arrays.fill(route, Integer.MAX_VALUE);

        dist = new int[N+1];
        edges = new ArrayList[N+1];

        for(int i=1; i <= N ;i++){
            edges[i] = new ArrayList();
        }

        for(int i=0 ; i<M ; i++){
            int from = scan.nextInt()
                , to = scan.nextInt()
                , weight = scan.nextInt();
            
            edges[from].add(new Edge(to, weight));
        }

        start = scan.nextInt();
        end = scan.nextInt();

    }

    static void pro(){
        
        for(int i=1;i<N+1;i++) {
            dist[i] = Integer.MAX_VALUE;
        }
        
        PriorityQueue<Info> que = new PriorityQueue<>(Comparator.comparingInt(o -> o.vertex));
        que.add(new Info(start, 0));
        dist[start] = 0;
        route[start] = 0;

        while(!que.isEmpty()){
            Info info = que.poll();

            if(dist[info.vertex] < info.weight) continue;

            for(Edge e : edges[info.vertex]){
                if(dist[info.vertex] + e.weight >= dist[e.to]) continue;

                dist[e.to] = dist[info.vertex] + e.weight;
                route[e.to] = info.vertex;
                que.add(new Info(e.to, dist[e.to]));
            }
        }

        int node = end;
        routes = new ArrayList();
        while(node != 0){
            routes.add(node);
            node = route[node];
        }

        sb.append(dist[end]).append('\n');
        sb.append(routes.size()).append('\n');
        for(int i= routes.size()-1 ; i >= 0; i--){
            sb.append(routes.get(i)).append(" ");
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
