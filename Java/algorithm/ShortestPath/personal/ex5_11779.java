package algorithm.ShortestPath.personal;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
/*
    최소 비용 구하기 2 (골드3)
    https://www.acmicpc.net/problem/11779

    도시의 개수 N (1≤n≤1,000)
    버스의 개수 M (1≤m≤100,000)
    버스 비용은 0 ~ 100,000

    # 입력 예시 
    5
    8
    1 2 2
    1 3 3
    1 4 1
    1 5 10
    2 4 2
    3 4 1
    3 5 1
    4 5 3
    1 5

    # 예제 출력 
    4          // 최소비용
    3          // 경로에 포함된 도시 개수  
    1 3 5      // 도시 순서 출력


    첫째 줄에 출발 도시에서 도착 도시까지 가는데 드는 최소 비용을 출력한다.
    둘째 줄에는 그러한 최소 비용을 갖는 경로에 포함되어있는 도시의 개수를 출력한다. 
    출발 도시와 도착 도시도 포함한다.
    셋째 줄에는 최소 비용을 갖는 경로를 방문하는 도시 순서대로 출력한다.
*/
import java.util.*;

public class ex5_11779 {
    
    static StringBuilder sb = new StringBuilder();
    static MyReader scan = new MyReader();
    
    static int N,M,start,end;
    static Dist[] dist;
    static ArrayList<Edge>[] edges;

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

    static class Dist{
        int value;
        int cnt;
        String path;

        public Dist(int _value, int _cnt){
            this.value = _value;
            this.cnt = _cnt;
        }
    }

    static void input(){
        N = scan.nextInt();
        M = scan.nextInt();

        dist = new Dist[N+1];
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
            dist[i] = new Dist(Integer.MAX_VALUE, 0);
        }
        
        PriorityQueue<Info> que = new PriorityQueue<>(Comparator.comparingInt(o -> o.vertex));
        que.add(new Info(start, 0));
        dist[start] = new Dist(0,1);
        dist[start].path = start + "";

        while(!que.isEmpty()){
            Info info = que.poll();

            if(dist[info.vertex].value < info.weight) continue;

            for(Edge e : edges[info.vertex]){
                if(dist[info.vertex].value + e.weight >= dist[e.to].value) continue;

                dist[e.to].value = dist[info.vertex].value + e.weight;
                dist[e.to].cnt = dist[info.vertex].cnt+1;
                dist[e.to].path = dist[info.vertex].path + " " + e.to;
                que.add(new Info(e.to, dist[e.to].value));
            }
        }

        sb.append(dist[end].value).append('\n');
        sb.append(dist[end].cnt).append('\n');
        sb.append(dist[end].path);
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
