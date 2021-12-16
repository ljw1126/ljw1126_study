package algorithm.ShortestPath.personal;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/*
    특정 거리의 도시 찾기 - 실버 2
    https://www.acmicpc.net/problem/18352

    # 입력
    첫째 줄에 도시의 개수 N, 도로의 개수 M, 거리 정보 K, 출발 도시의 번호 X가 주어진다. 
    (2 ≤ N ≤ 300,000, 1 ≤ M ≤ 1,000,000, 1 ≤ K ≤ 300,000, 1 ≤ X ≤ N) 
    둘째 줄부터 M개의 줄에 걸쳐서 두 개의 자연수 A, B가 공백을 기준으로 구분되어 주어진다. 
    이는 A번 도시에서 B번 도시로 이동하는 단방향 도로가 존재한다는 의미다. (1 ≤ A, B ≤ N) 단, A와 B는 서로 다른 자연수이다.

    # 출력
    X로부터 출발하여 도달할 수 있는 도시 중에서, 
    최단 거리가 K인 모든 도시의 번호를 한 줄에 하나씩 오름차순으로 출력한다.
    이 때 도달할 수 있는 도시 중에서, 최단 거리가 K인 도시가 하나도 존재하지 않으면 -1을 출력한다.

    # 예제 입력 1
    4 4 2 1
    1 2
    1 3
    2 3
    2 4

    # 예제 출력 1
    4

    # 예제 입력 2
    4 3 2 1
    1 2
    1 3
    1 4

    # 예제 출력 2
    -1
    
    # 예제 입력 3
    4 4 1 1
    1 2
    1 3
    2 3
    2 4

    # 예제 출력 3
    2
    3
*/

import java.util.*;
import java.io.*;

public class ex7_18352 {
    
    static MyReader scan = new MyReader();
    static StringBuilder sb = new StringBuilder();

    static int N,M,K,X;
    static int[] dist;
    static ArrayList<Integer>[] adj;

    static void input(){
        N = scan.nextInt(); // 도시 개수 (=노드 수)
        M = scan.nextInt(); // 도로 개수 (=간선 수)
        K = scan.nextInt(); // 거리 정보 (X에서 이동했을때 K만큼의 최소 이동경로 값을 가지는 노드 번호를 출발)
        X = scan.nextInt(); // 출발 도시 번호 

        adj = new ArrayList[N+1];
        dist = new int[N+1];

        for(int i=1; i <= N ; i++) adj[i] = new ArrayList();

        for(int i=0;i<M;i++){
            int a = scan.nextInt(), b = scan.nextInt();
            adj[a].add(b);
        }

    }

    static void bfs(int start){
        Arrays.fill(dist, Integer.MAX_VALUE);
        Queue<Integer> que = new LinkedList();
        que.add(start);
        dist[start] = 0;

        while(!que.isEmpty()){
            int x = que.poll();

            for(int y : adj[x]){
                if(dist[x] + 1 >= dist[y]) continue;

                dist[y] = dist[x] + 1;
                que.add(y);
            }
        }
    }

    static void pro(){
        bfs(X);
        
        boolean cheked = false;
        
        for(int i=1 ; i<=N;i++){
            if(dist[i] == K) {
                sb.append(i).append('\n');
                cheked = true;
            }
        }
        
        if(!cheked) sb.append(-1).append('\n');
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
