﻿package algorithm.Tree;

import java.util.*;
import java.io.*;

public class ex2_1068practice {
    
    static FastReader scan = new FastReader();
    static StringBuilder sb = new StringBuilder();

    static int n, root, erased;
    static ArrayList<Integer>[] child;
    static int[] leaf;

    static void input() {
        n = scan.nextInt();
        leaf = new int[n];
        child = new ArrayList[n];
        for(int i=0 ; i < n ; i++) child[i] = new ArrayList<>();

        for(int i=0; i < n; i++){
            int data = scan.nextInt();
            
            if(data == -1){
                root = i;
                continue; 
            } 
            
            child[data].add(i);
        }

        erased = scan.nextInt();
    }

    // dfs(x, par) := 정점 x 의 부모가 par 였고, Subtree(x) 의 leaf 개수를 세주는 함수
    static void dfs(int x, int par) {
        /* TODO */
        if(child[x].isEmpty()){
            leaf[x]++;
            return;
        }

        for(int y : child[x]){
            if(y == par) continue;
            dfs(y,x);
            leaf[x] += leaf[y];
        }
    }

    static void pro() {
        // erased와 그의 부모 사이의 연결을 끊어주기
        /* TODO */
        for(int i=0 ; i < n ; i++){
            if(child[i].contains(erased)){
                child[i].remove(child[i].indexOf(erased));
            }
        }

        // erased 가 root 인 예외 처리하기
        /* TODO */
        if(root != erased) dfs(root,-1);
        
        // 정답 출력하기
        /* TODO */
        System.out.println(leaf[root]);
    }

    public static void main(String[] args) {
        input();
        //System.out.println(Arrays.toString(child));
        pro();
        //System.out.println(Arrays.toString(child));
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
