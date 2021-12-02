import java.util.*;
import java.io.*;
/*
    숨바꼭질 (난이도 3)
    https://www.acmicpc.net/problem/1697

    O < 수빈이의 위치 N < 100,000
    O < 동생의 위치 K < 100,000

    최대치 : N=10만, K=0일때 -1씩이동시 10만초
    
    수빈이가 동생을 찾을 수 있는 "가장 빠른 시간"이 몇 초 후인지?
    => 하지만 BFS 하려고 해도 graph가 주어지지 않았음.. how?

    - 정점 
        - 보통 문제에서 표현하는 "하나의 상태"가 "하나의 정점"
        - 이 문제는 "점의 번호"가 곧 "정점의 번호"
    - 간선 
        - 이동을 의미하는 것을 간선으로 표현하기 
        - 이 문제는 각 점마다 -1 , +1 , *2 인 점을 향하는 "방향성 간선"
    [Graph 창조해보기]
    - 간선 하나를 타고 이동하는 행위 => 1초 동안 이동하는 행위 
    - 가장 빨리 동생을 찾는 방법 => 최소 개수로 간선 이동하는 방법 

    정점 : 10만개 
    간선 : 10만개 * 3 // 3방향 이동하니 

    BFS를 사용하므로 시간, 공간 복잡도는 모두 O(10^5)
*/
public class ex6_1697 {
    static FastReader scan = new FastReader();
    static StringBuilder sb = new StringBuilder();

    static int N, K;
    static int[] dist;
    static boolean[] visit;

    static void input() {
        N = scan.nextInt();
        K = scan.nextInt();
        visit = new boolean[100005];
        dist = new int[100005];
    }

    // 숨바꼭질 시작~
    static void bfs() {
        Queue<Integer> Q = new LinkedList<>();
        Q.add(N);
        visit[N] = true;
        dist[N] = 0;
        /* TODO */

        // BFS 과정 시작
        /* TODO */
        while(!Q.isEmpty()){
            int x = Q.poll();
            
            // 정점마다 3개의 간선 가짐(+1, -1 , *2)
            int y = x +1;
            if(0 <= y && y <= 100000 && !visit[y]){
                visit[y] = true;
                dist[y] = dist[x]+1;
                Q.add(y);
            }
            
            y = x - 1;
            if(0 <= y && y <= 100000 && !visit[y]){
                visit[y] = true;
                dist[y] = dist[x]+1;
                Q.add(y);
            }
            
            y = x * 2;
            if(0 <= y && y <= 100000 && !visit[y]){
                visit[y] = true;
                dist[y] = dist[x]+1;
                Q.add(y);
            }
        }
    }

    static void pro() {
        bfs();
        System.out.println(dist[K]);
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
