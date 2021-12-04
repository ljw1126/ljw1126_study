package algorithm.ShortestPath;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
    최소비용 구하기 - 난이도3 (골드5)
    https://www.acmicpc.net/problem/1916

    1 ⪯ 도시 수,N ⪯ 1,000          // 정점(vertex)
    1 ⪯ 버스 개수, M ⪯ 100,000     // 간선(edge)
    0 ⪯ 버스 비용 ⪯ 100,000        // 가중치(weight)

    [최단 거리의 특성]
    - 같은 정점을 두번 방문할 이유 x 
    - 따라서 정답은 최대
        버스 비(10만) * 정점 수 (1천) = 1억
        => Integer로 충분하다

    [시간복잡도]
    - O(ElogV) = 10만 * log 1천(=10) = 100만 

    # 예제 입력 1
    5            -- 도시의 개수 N 
    8            -- 버스의 개수 M 
    1 2 2        -- 출발지 목적지 버스비용 (M개 만큼)
    1 3 3
    1 4 1
    1 5 10
    2 4 2
    3 4 1
    3 5 1
    4 5 3
    1 5           -- 출발지 , 도착지

    # 예제 출력 1
    4


    dist[] // 최단거리 가중치 합
    0,0,2,3,1,10 에서
    0,0,2,3,1,4  됨

*/

import java.util.*;
import java.io.*;

public class ex1_1916practice211204 {
    
    static StringBuilder sb = new StringBuilder();
    static MyReader scan = new MyReader();

    // 
    static class Edge{
        public int to,weight;

        public Edge(int _to, int _weight){
            this.to = _to;
            this.weight = _weight;
        }
    }

    static class Info{
        public int idx, dist;

        public Info(){}

        public Info(int _idx, int _dist){
            this.idx = _idx;
            this.dist = _dist;
        }
    }

    static int N, M, start, end;
    static int dist[];
    static ArrayList<Edge>[] edges;

    static void input(){
        N = scan.nextInt(); // 도시의 개수
        M = scan.nextInt(); // 버스의 개수 
        dist = new int[N+1];
        
        // start: 노드와 간선의 가중치를 표현 
        edges = new ArrayList[N+1];
        
        for(int i=1; i<=N; i++)
            edges[i] = new ArrayList<Edge>();

        for(int i=1; i<=M; i++){
            int from = scan.nextInt()
              , to = scan.nextInt()
              , weight = scan.nextInt();

            edges[from].add(new Edge(to,weight));
        }
        // end : 노드와 간선의 가중치를 표현 

        start = scan.nextInt(); // 출발 도시 번호
        end = scan.nextInt(); // 도착 도시 번호 
    }

    static void dijkstra(int start){
        /*
            1. 모든 정점까지에 대한 거리를 무한대로 초기화 해주기 
            ※ 주의 
            문제의 정답으로 가능한 거리의 최댓값보다 큰 값임을 보장해야 함
        */
        for(int i=1; i <=N ; i++) dist[i] = Integer.MAX_VALUE;

        // 최소 힙 생성(dist 작은 값 순으로 정렬)
        PriorityQueue<Info> pq = new PriorityQueue<>(Comparator.comparingInt( o -> o.dist));

        // 시작 점에 대한 정보를 기록에 추가하고 , 거리 배열 (dist)에 시작점을 0으로 초기화 
        pq.add(new Info(start, 0));
        dist[start] = 0;

        while(!pq.isEmpty()){
            Info info = pq.poll();

            // 꺼낸 정보가 최신 정보랑 다르면, 의미없이 낡은 정보이므로 폐기함 
            if(dist[info.idx] < info.dist) continue;

            // 연결된 모든 간선들을 통해서 다른 정점들에 대한 정보를 갱신해준다.
            for(Edge e : edges[info.idx]){
                // 출발지 dist + 다음 노드 가중치 >= 도착치 dist
                if(dist[info.idx] + e.weight >= dist[e.to]) continue;

                dist[e.to] = dist[info.idx] + e.weight;
                pq.add(new Info(e.to, dist[e.to]));
            }
        }
    }

    static void pro(){
        dijkstra(start);
        System.out.println(dist[end]); 
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
        }

    }

}
