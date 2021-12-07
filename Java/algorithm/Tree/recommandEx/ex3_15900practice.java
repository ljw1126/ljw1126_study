package algorithm.Tree.recommandEx;

import java.util.*;
import java.io.*;

/*
    나무 탈출 - 실버 1 
    https://www.acmicpc.net/problem/15900

    참고 블로그 
    https://steady-coding.tistory.com/192
*/
public class ex3_15900practice {
    
    static FastReader scan = new FastReader();
    static StringBuilder sb = new StringBuilder();

    static int ans, n, sum_leaf_depth;
    static ArrayList<Integer>[] adj;    

    static void input(){
        n = scan.nextInt();
        adj = new ArrayList[n+1];
        for(int i=1;i <=n;i++)   
            adj[i] = new ArrayList();

        for(int i=0 ;i<n; i++){
            int a = scan.nextInt(), b = scan.nextInt();
            adj[a].add(b);
            adj[b].add(a);
        }
    }

    // 트리를 순회하면서 sum_leaf_depth 계산
    static void dfs(int x, int prev, int depth) {
        // 리프노드이다..
        if( x != 1 && adj[x].size() == 1) sum_leaf_depth += depth;
        
        for(int y : adj[x]){
            if(y == prev) continue;
            dfs(y, x, depth+1);
        }
    }

    // 1번을 루트 노드
    static void pro() {
        dfs(1,-1,0);
        if(ans %2 == 0) System.out.println("No");
        else System.out.println("Yes");
    }

    public static void main(String[] args){
        input();
        pro();

        // for(int i=1;i<=n;i++){
        //     System.out.println(con[i]);
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
