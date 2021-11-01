package algorithm.ShortestPath;

import java.util.*;
import java.io.*;
// 211030 문제 1 풀고 이거는 있길래 직접 품 
public class ex2_1753practice {
    
    
    static FastReader scan = new FastReader();
    static StringBuilder sb = new StringBuilder();
    
    static class Edge{ // 연결된 간선 정보 
        int to, weight; 
        public Edge(int _to, int _weight){
            this.to= _to;
            this.weight = _weight;
        }
    }

    static class Info{
        int idx, dist; 

        public Info(int _idx, int _dist){
            this.idx = _idx;
            this.dist = _dist;
        }
    }

    static int N, M, start, end;
    static int[] dist;
    static ArrayList<Edge>[] edges;

    static void input() {
        N = scan.nextInt();
        M = scan.nextInt();
        start = scan.nextInt();

        dist = new int[N + 1];
        edges = new ArrayList[N + 1];
        for (int i = 1; i <= N; i++) edges[i] = new ArrayList<Edge>();
        for (int i = 1; i <= M; i++) { // 간선 정보를 여기 넣음 
            int u = scan.nextInt(); // from
            int v = scan.nextInt(); // to
            int w = scan.nextInt(); // weight
            edges[u].add(new Edge(v, w));
        }
    }

    static void dijkstra(int start) {
        // 모든 정점까지에 대한 거리를 무한대로 초기화 해주기.
        // ※주의사항※
        // 문제의 정답으로 가능한 거리의 최댓값보다 큰 값임을 보장해야 한다.
        for(int i=0 ; i < N+1 ; i++) dist[i] = Integer.MAX_VALUE;

        // 최소 힙 생성
        PriorityQueue<Info> pq = new PriorityQueue<>(Comparator.comparingInt(o -> o.dist));

        // 시작점에 대한 정보(Information)을 기록에 추가하고, 거리 배열(dist)에 갱신해준다.
        pq.add(new Info(start , 0));
        dist[start] = 0;

        // 거리 정보들이 모두 소진될 때까지 거리 갱신을 반복한다.
        while (!pq.isEmpty()) {
            Info info = pq.poll();
            
            // 꺼낸 정보가 최신 정보랑 다르면, 의미없이 낡은 정보이므로 폐기한다.
            if(dist[info.idx] < info.dist) continue;

            // 연결된 모든 간선들을 통해서 다른 정점들에 대한 정보를 갱신해준다.
            for (Edge e : edges[info.idx]) {
                // e.to 까지 갈 수 있는 더 짧은 거리를 찾았다면 이에 대한 정보를 갱신하고 PQ에 기록해준다.
                if(dist[info.idx] + e.weight >= dist[e.to]) continue;                

                dist[e.to] = dist[info.idx] + e.weight;
                pq.add(new Info(e.to, dist[e.to]));
            }
        }

        for(int i = 1 ; i < N+1 ; i++){
            if(dist[i] == Integer.MAX_VALUE) sb.append("INF").append('\n');
            else sb.append(dist[i]).append('\n'); 
        } 
    }

    static void pro() {
        dijkstra(start);
        System.out.print(sb);
    }

    public static void main(String[] args) {
        input();
        pro();
    }


    static class FastReader {
        BufferedReader br;
        StringTokenizer st;

        public FastReader() {
            br = new BufferedReader(new InputStreamReader(System.in));
        }

        public FastReader(String s) throws FileNotFoundException {
            br = new BufferedReader(new FileReader(new File(s)));
        }

        String next() {
            while (st == null || !st.hasMoreElements()) {
                try {
                    st = new StringTokenizer(br.readLine());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return st.nextToken();
        }

        int nextInt() {
            return Integer.parseInt(next());
        }

        long nextLong() {
            return Long.parseLong(next());
        }

        double nextDouble() {
            return Double.parseDouble(next());
        }

        String nextLine() {
            String str = "";
            try {
                str = br.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return str;
        }
    }

}
