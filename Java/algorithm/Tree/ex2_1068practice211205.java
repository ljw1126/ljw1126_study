package algorithm.Tree;

import java.io.*;
import java.util.*;

public class ex2_1068practice211205 {

    static MyReader scan = new MyReader();
    static StringBuilder sb = new StringBuilder();

    static int n , root, erased;
    static ArrayList<Integer>[] adj;
    static int[] leaf;

    // dfs를 이용한 완전탐색 느낌..
    static void dfs(int x , int par){
      
        if(adj[x].isEmpty()){
            leaf[x]++;
            return;
        }

        for(int y : adj[x]){
            if(y == par) continue;
            dfs(y,x);
            leaf[x] += leaf[y];
        }

    }

    static void input(){
        n = scan.nextInt();

        leaf = new int[n];
        adj = new ArrayList[n];

        for(int i=0;i<n;i++) adj[i] = new ArrayList<>();

        for(int i=0;i<n;i++){
            int a = scan.nextInt();
            if(a == -1){
                root = i;
                continue;
            }
            adj[a].add(i);
        }

        erased = scan.nextInt();
    }

    static void pro(){
        // erased 목표에 맞춰 
        for(int i=0; i < n; i++){
            if(adj[i].contains(erased)){
                adj[i].remove(adj[i].indexOf(erased));
            }
        }

        if(erased != root) dfs(root, -1);

        System.out.println(leaf[root]);

    }

    public static void main(String[] args) {
        input();
        pro();
    }

    static class MyReader{
        BufferedReader  br;
        StringTokenizer st ;

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
            String str ="";
            try {
                str= br.readLine();
            } catch (Exception e) {
                e.printStackTrace();
            }
            return str;
        }

    }

}
