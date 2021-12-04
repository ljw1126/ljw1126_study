package algorithm.ShortestPath.personal;

import java.util.*;
import java.io.*;

/*
    숨바꼭질3 (골드5)

    https://www.acmicpc.net/problem/13549
*/
public class ex2_13549 {
    
    static MyReader scan = new MyReader();
    static StringBuilder sb = new StringBuilder();
    /*
    정답임
    static int N,X,ans;
    static boolean[] visit;

    static class Node {
        int idx, time;

        public Node(int _idx, int _time){
            this.idx = _idx;
            this.time = _time;
        }
    }
    
        //10번은 틀렸던 이유가 *2 , -1, +1 가중치 위치 문제 때문이었음...
    
    static void dijkstra(int start){

        Queue<Node> que = new LinkedList();
        que.add(new Node(start, 0));
        visit[start] = true;
  
        while(!que.isEmpty()){
            Node n = que.poll();
          
            if(n.idx == X){
                ans = n.time;
                break;
            }

            if(0 <= n.idx  * 2 && n.idx  * 2 <= 100000 && !visit[n.idx  * 2]){
                visit[n.idx  * 2] = true;
                que.add(new Node(n.idx  * 2, n.time));
            }
 
            if(0 <= n.idx -1 && n.idx -1 <= 100000 && !visit[n.idx -1]){
                visit[n.idx -1] = true;
                que.add(new Node(n.idx -1, n.time +1 ));
            }

            if(0 <= n.idx + 1 && n.idx + 1 <= 100000 && !visit[n.idx + 1]){
                visit[n.idx + 1] = true;
                que.add(new Node(n.idx + 1, n.time +1));
            }

           
        }

        System.out.println(ans);
    }

    static void input(){
        N = scan.nextInt();
        X = scan.nextInt();
        visit = new boolean[100001];
    }

    static void pro(){
        dijkstra(N);
    }
    */
    static int N,K;
    static boolean[] visit;
    static int[]dist;

    static void input(){
        N = scan.nextInt();
        K = scan.nextInt();
        visit = new boolean[100005];
        dist = new int[100005];
    }

    static void bfs(){
        Queue<Integer> que = new LinkedList<>();
        que.add(N);
        visit[N] = true;
        dist[N] = 0;
        // 가중치 순서가 *2, -1, +1 .. 안지키면 틀림
        while(!que.isEmpty()){
            int x = que.poll();

            int y = x * 2; 
            if(0<= y && y <=100000 && !visit[y]){
                visit[y] = true;
                dist[y] = dist[x]; // 0초 순간이동
                que.add(y);
            }

            y = x -1;
            if(0<= y && y <=100000 && !visit[y]){
                visit[y] = true;
                dist[y] = dist[x] + 1;  // 1초 걸림
                que.add(y);
            }

            y = x +1;
            if(0<= y && y <=100000 && !visit[y]){
                visit[y] = true;
                dist[y] = dist[x] + 1; // 1초 걸림
                que.add(y);
            }
        }

    }

    static void pro(){
        bfs();
        System.out.println(dist[K]);
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
