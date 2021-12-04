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
public class ex6_1697practice {
    
    static StringBuilder sb = new StringBuilder();
    static MyReader scan = new MyReader();

    static int N,K,ans, x,y,z;
    static boolean[] visit;
    static int[] dist;

    static void bfs(int start){
        Queue<Integer> que = new LinkedList<>();
        que.add(start);
        visit[start] = true;

        while(!que.isEmpty()){
            int x = que.poll();

            // 정점마다 3개의 간선을 가짐 (-1,+1,*2)
            int y = x +1;
            if(0 <= y && y <=100000 && !visit[y]){
                visit[y] = true;
                dist[y] = dist[x] + 1;
                que.add(y);
            }

            y = x -1;
            if(0 <= y && y <=100000 && !visit[y]){
                visit[y] = true;
                dist[y] = dist[x] + 1;
                que.add(y);
            }

            y = x * 2;
            if(0 <= y && y <=100000 && !visit[y]){
                visit[y] = true;
                dist[y] = dist[x] + 1;
                que.add(y);
            }
        }
    }

    static void pro(){
        bfs(N);
        System.out.println(dist[K]);
    }

    static void input(){
        N = scan.nextInt();
        K = scan.nextInt();

        dist = new int[100005];
        visit = new boolean[100005];
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
