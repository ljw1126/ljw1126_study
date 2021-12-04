package algorithm.ShortestPath;
/*
    최단 경로 - 골드 5 
    https://www.acmicpc.net/problem/1753

    정점의 개수 V (1 ≤ V ≤ 20,000)
    간선의 개수 E (1 ≤ E ≤ 300,000)
    시작 정점의 번호 K (1 ≤ K ≤ V)

    (u,v,w)  ==> u  에서 v로 가중치 w 간선이 존재한다. 
                 이때 w는 이하의 자연수다 
    - 최대치 계산 
        정점의 개수 2만 * 10(최대w) = 20만 // dist값은 Integer로 충분 
    - 시간복잡도 
      O(ElogV) = O(30만 log 2만) = O(300만 log 20) // Integer로 충분

*/

import java.util.*;
import java.io.*;

public class ex2_1753 {
    static FastReader scan = new FastReader();
    static StringBuilder sb = new StringBuilder();

    static class Edge {
        public int to, weight;

        public Edge(int _to, int _weight) {
            this.to = _to;
            this.weight = _weight;
        }
    }

    static class Info {
        public int idx, dist;

        public Info() {
        }

        public Info(int _idx, int _dist) {
            this.idx = _idx;
            this.dist = _dist;
        }
    }

    static int V, E, K;
    static int[] dist;
    static ArrayList<Edge>[] edges;

    static void input() {
        V = scan.nextInt(); // 정점개수
        E = scan.nextInt(); // 노드개수
        K = scan.nextInt(); // 시작점 
        dist = new int[V + 1];
        edges = new ArrayList[V + 1];
        for (int i = 1; i <= V; i++) edges[i] = new ArrayList<Edge>();
        for (int i = 1; i <= E; i++) {
            int from = scan.nextInt();
            int to = scan.nextInt();
            int weight = scan.nextInt();
            edges[from].add(new Edge(to, weight));
        }
    }

    static void dijkstra(int start) {
        // 모든 정점까지에 대한 거리를 무한대로 초기화 해주기.
        // ※주의사항※
        // 문제의 정답으로 가능한 거리의 최댓값보다 큰 값임을 보장해야 한다.
        for(int i=0 ; i < V+1 ; i++) dist[i] = Integer.MAX_VALUE;

        // 최소 힙 생성
        PriorityQueue<Info> pq = new PriorityQueue<>(Comparator.comparingInt(o -> o.dist));

        // 시작점에 대한 정보(Information)을 기록에 추가하고, 거리 배열(dist)에 갱신해준다.
        dist[start] = 0;
        pq.add(new Info(start, 0));

        // 거리 정보들이 모두 소진될 때까지 거리 갱신을 반복한다.
        while (!pq.isEmpty()) {
            Info info = pq.poll();
            
            // 꺼낸 정보가 최신 정보랑 다르면, 의미없이 낡은 정보이므로 폐기한다.
            // 현 info 노드까지 오는데 info.dist 걸렸는데 
            // dist[info.idx] 에 이미 값이 있고 이것 보다 크면 쓰레기 정보 
            if(dist[info.idx] < info.dist) continue;

            // 연결된 모든 간선들을 통해서 다른 정점들에 대한 정보를 갱신해준다.
            for (Edge e : edges[info.idx]) {
                // e.to 까지 갈 수 있는 더 짧은 거리를 찾았다면 이에 대한 정보를 갱신하고 PQ에 기록해준다.
                if(dist[info.idx] + e.weight >= dist[e.to]) continue;

                dist[e.to] = dist[info.idx] + e.weight;
                pq.add(new Info(e.to, dist[e.to]));
            }
        }
    }

    static void pro() {
        dijkstra(K);
        for(int i=1;i<=V;i++) {
            if(dist[i] == Integer.MAX_VALUE) sb.append("INF").append('\n');
            else sb.append(dist[i]).append('\n');
        }
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
