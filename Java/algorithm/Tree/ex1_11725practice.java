﻿package algorithm.Tree;

import java.util.*;
import java.io.*;

public class ex1_11725practice {
    static FastReader scan = new FastReader();
    static StringBuilder sb = new StringBuilder();

    static int n;
    static ArrayList<Integer>[] adj;
    static int[] parent;
    

    static void input() {
        n = scan.nextInt();
        parent = new int[n+1];
        // 인접 리스트 구성하기
        adj = new ArrayList[n+1];
        for(int i=1;i<=n;i++)
            adj[i] = new ArrayList();

        for(int i=1;i<n;i++){
            int a = scan.nextInt(), b = scan.nextInt();
            adj[a].add(b);
            adj[b].add(a);
        }    
    }

    // dfs(x, par) := 정점 x 의 부모가 par 였고, x의 children들을 찾아주는 함수
    static void dfs(int x, int par) {
        
        parent[x] = par;

        for(int y : adj[x]){
            if(parent[y] != -1) continue;
            dfs(y, x);
        }

    }

    static void pro() {
        // 1 번 정점이 ROOT 이므로, 여기서 시작해서 Tree의 구조를 파악하자.
        for(int i=1;i<=n;i++) parent[i] = -1;
        dfs(1,0);

        // 정답 출력하기
        for(int i=2;i<=n;i++)
            sb.append(parent[i]).append('\n');

        System.out.println(sb);
    }

    public static void main(String[] args) {
        input();
        pro();

        // for(int i=1;i<=n;i++){
        //     System.out.println(adj[i]);
        // }
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
