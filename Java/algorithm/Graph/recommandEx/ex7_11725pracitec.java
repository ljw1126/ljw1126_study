package recommandEx;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
    트리의 부모찾기 - 실버 2 
    https://www.acmicpc.net/problem/11725

    #입력예시 1
    7
    1 6
    6 3
    3 5
    4 1
    2 4
    4 7

    #출력예시   // 1번 생략 
    4
    6
    1
    3
    1
    4

    1 - 6,4
    2 - 4
    3 - 5,6
    4 - 1,2,7
    5 - 3
    6 - 1,3
    7 - 4

    211203 앞에는 bfs로 풀었으나 이번에는 dfs로 풀어봄..(정답입니다.)

*/

import java.util.*;
import java.io.*;

public class ex7_11725pracitec {
    static StringBuilder sb = new StringBuilder();
    static MyReader scan = new MyReader();

    static int N;
    static boolean[] visit;
    static int[] parent;
    static ArrayList<Integer>[] adj;
    
    static void input(){
        N = scan.nextInt();
        visit = new boolean[N+1];
        adj = new ArrayList[N+1];
        parent = new int[N+1];

        for(int i=1; i<=N;i++) adj[i] = new ArrayList<>();

        for(int i=0; i<N-1;i++){ // N-1개
            int x = scan.nextInt();
            int y = scan.nextInt();

            adj[x].add(y);
            adj[y].add(x);    
        }
    }

    static void dfs(int start, int parnt){

        visit[start] = true;
        parent[start] = parnt;

        for(int i : adj[start]){
            if(visit[i]) continue;
            dfs(i, start);            
        }
    }


    static void pro(){
        dfs(1,0);
        for(int i=2; i <= N;i++) sb.append(parent[i]).append('\n');
        System.out.println(sb);
    }

    public static void main(String[] args) {
        input();
        pro();
    }

    static class MyReader{
        BufferedReader br ;
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
