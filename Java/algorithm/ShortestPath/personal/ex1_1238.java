package algorithm.ShortestPath.personal;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
    파티 - 골드 3 
    https://www.acmicpc.net/problem/1238

    
    문제)
    N개의 숫자로 구분된 각각의 마을에 한 명의 학생이 살고 있다.
    어느 날 이 N명의 학생이 X (1 ≤ X ≤ N)번 마을에 모여서 파티를 벌이기로 했다. 
    이 마을 사이에는 총 M개의 단방향 도로들이 있고 i번째 길을 지나는데 Ti(1 ≤ Ti ≤ 100)의 시간을 소비한다.
    각각의 학생들은 파티에 참석하기 위해 걸어가서 다시 그들의 마을로 돌아와야 한다. 
    "하지만 이 학생들은 워낙 게을러서 최단 시간에 오고 가기를 원한다.
    이 도로들은 단방향이기 때문에 아마 그들이 오고 가는 길이 다를지도 모른다. "    <==== 이 부분에서 reverse 된 경로 또한 생각할 줄 누가 알았겠는가?,  왔던길을 다시 가겠다로 해석해도 될듯
    N명의 학생들 중 오고 가는데 가장 많은 시간을 소비하는 학생은 누구일지 구하여라.

    ----------------------------
    1. 입력값에 해당 하는 그래프에 대한 최단 dist와 (1,3,4노드에서 2번으로 도착하는 최단시간)
    2. 반대로 그려진 그래프에 대한 최단 dist 를 각각 구함 (2번에서 1,3,4노드에 도착하는 최단시간)
    3. 그리고 두개를 더해서 오고 가는데 가장 오래 걸린 소요시간이 정답 

    N명의 학생 (1 ≤ X ≤ N)    (1 ≤ N ≤ 1,000) ---- 정점
    X번 마을 (=도착지)
    M개의 단방향 도로(1 ≤ M ≤ 10,000) ---- 간선 가중치 
        ( i방향으로 이동하는데 걸리는 시간 (1 ≤ Ti ≤ 100) )
     
    - 최대치 계산 (모든 노드에 대해 다익스트라를 계산하므로) ///xxx
        1000개 노드 번 * O(ElogV) = 1000 * 10000 log 1000 = 10^8 // 1억 , O(ElogV) = 가중치 계산 뜻하는듯??
    // 역방향도 저장해서 무방향 그래프처럼 보겠다.
    [설명이 제대로 안됨]
    1. 각 노드에서 X로 가는거 
        - 이거는 각 노드별로 다익스라 구해서 X에 해당하는 최소값을 뽑아야 함
        - 그래서 1000개 노드별 * O(ElogV) 가 걸리는거 ! // 시간 초과 발생가능 1억번씩하니 
        - 그래서 뒤집는방법을 쓰는데 
    2. X에서 노드로 가는거 
        - 이거는 다익스트라로 구할 수 있는데

        정상적인 edges는 X에서 각 노드로 돌아갈대 값을 구할 수 있고  (1 0 3 7)
        뒤집은 edges는 각 노드에서 X로 갈때 값을 구할 수 있다. !!!!! (4 0 6 3) 

        시작점은 각각 X로 하고 edges만 무방향으로 하면 


*/
import java.util.*;
import java.io.*;

public class ex1_1238 {
    
    static StringBuilder sb = new StringBuilder();
    static MyReader scan = new MyReader();

    static int N,X,M;
    static ArrayList<Edge>[] edges, edges_reverse;


    //가중치 나타 내기 위한 구조체 
    static class Edge{
        int to, weight;

        public Edge(int _to, int _weight){
            this.to = _to;
            this.weight = _weight;
        }
    }

    //현재 vertext까지에 대한 weight 가중치
    static class Info{  
        int vertex, weight;

        public Info(int _vertex, int _weight){
            this.vertex = _vertex;
            this.weight = _weight;
        }
    }

    static void input(){
        N = scan.nextInt(); // N명의 학생 , 노드 
        M = scan.nextInt(); // 간선 수  
        X = scan.nextInt(); // 도착지 번호 

        // 무방향 그래프로 보고 역방향에 대한 가중치도 구해주기 위해, 이렇게 따로 받아서 결과를 구한다함 
      
        edges = new ArrayList[N+1];
        edges_reverse = new ArrayList[N+1];

        for(int i=1 ; i <= N ; i++){
            edges[i] = new ArrayList<>();
            edges_reverse[i] = new ArrayList<>();
        }

        for(int i=0 ; i < M ; i++){
            int from = scan.nextInt()
                ,to = scan.nextInt() 
                ,weight = scan.nextInt();

            edges[from].add(new Edge(to, weight));
            edges_reverse[to].add(new Edge(from, weight));
        }

    }

    static int[] dijkstra(ArrayList<Edge>[] _edges){
        int[] _dist = new int[N+1];

        for(int i=1; i<=N ; i++) _dist[i] = Integer.MAX_VALUE;
        _dist[X] = 0;

        PriorityQueue<Info> pq = new PriorityQueue<>(Comparator.comparingInt(o -> o.weight));
        pq.add(new Info(X,0));
        
        while(!pq.isEmpty()){
            Info info = pq.poll(); // info의 의미는 , 현 노드까지 오는데 weight가 걸렸다

            // 현재노드와 dist값 비교시 의미 없는 경우 
            if(_dist[info.vertex] < info.weight) continue;

            for(Edge e : _edges[info.vertex]){
                if(_dist[info.vertex] + e.weight >= _dist[e.to]) continue;

                _dist[e.to] = _dist[info.vertex] + e.weight;
                pq.add(new Info(e.to, _dist[e.to]));
            }

        }        
        
        return _dist;
    }

    /*
        1. 각 노드에서 X로 가는 최단 경로 => HOW ?! 시간초과 발생가능 .. 위에 작성함
        2. X에서 각 노드로 가는 최단 경로 
    */
    static void pro(){
        
        int[] dist = dijkstra(edges);     // [0, 1, 0, 3, 7]
        int[] dist_reverse = dijkstra(edges_reverse); // [0, 4, 0, 6, 3]
        
        int ans = Integer.MIN_VALUE;

        for(int i=1; i <=N ; i++){
            int sum = dist[i] + dist_reverse[i];
            ans = Math.max(ans, sum);
        }

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
            String str ="";
            try {
                str = br.readLine();
            } catch (Exception e) {
                e.printStackTrace();
            }
            return str;
        }

    }

}
