﻿

import java.util.*;
import java.io.*;

public class ex1_1260practice {
    
    static FastReader scan = new FastReader();
    static StringBuilder sb = new StringBuilder();

    static int N, M, V;
    static ArrayList<Integer>[] adj;
    static boolean[] visit;

    static void input() {
        N = scan.nextInt(); // 정점의 개수
        M = scan.nextInt(); // 간선의 개수
        V = scan.nextInt(); // 시작할 정점의 번호 
        adj = new ArrayList[N + 1];
        visit = new boolean[N + 1];
        for (int i = 1;i <= N; i++)
            adj[i] = new ArrayList<>();
        //입력으로 주어지는 간선은 양방향이다.
        for (int i=0; i < M ; i++){
            int a = scan.nextInt(), b = scan.nextInt();
            adj[a].add(b);
            adj[b].add(a);
        }

    }

    // x 를 갈 수 있다는 걸 알고 방문한 상태
    static void dfs(int x) {
        
        visit[x] = true;
        sb.append(x).append(" ");

        for(int n : adj[x]){
            if(visit[n]) continue;
            dfs(n);
        }
    }

    // start 에서 시작해서 갈 수 있는 정점들을 모두 탐색하기
    static void bfs(int start) {
        Queue<Integer> que = new LinkedList<>();
       
        que.add(start);
        visit[start] = true;

        while(!que.isEmpty()){
            int x = que.poll();
            sb.append(x).append(" ");

            for(int y : adj[x]){
                if(visit[y]) continue;
                que.add(y);
                visit[y] = true;
            }
            
        }
    }

    static void pro() {
        // 모든 x에 대해서 adj[x] 정렬하기
        for(int i =1 ; i<=N ; i++){
            Collections.sort(adj[i]);
        }
   
        // DFS, BFS 결과 구하기
        dfs(V);

        sb.append("\n");
        for(int i=1 ; i<=N; i++) visit[i] = false;
        
        bfs(V);
        
        System.out.println(sb);
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
