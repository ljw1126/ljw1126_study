package algorithm.Tree.recommandEx;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/*
    상근이의 여행 - 실버 4
    https://www.acmicpc.net/problem/9372


    # 예제 입력 
    2       //테스트 케이스 T(T ≤ 100)
    3 3     //N(2 ≤ N ≤ 1 000)과 비행기의 종류 M(1 ≤ M ≤ 10 000) 
    1 2     //M개의 줄에 a와 b 쌍들이 입력된다. a와 b를 왕복하는 비행기가 있다는 것을 의미한다. (1 ≤ a, b ≤ n; a ≠ b) 
    2 3
    1 3
    5 4
    2 1
    2 3
    4 3
    4 5
    
    # 예제 출력 
    2
    4

*/

import java.util.*;
import java.io.*;

public class ex4_9372 {
    
    static MyReader scan = new MyReader();
    static StringBuilder sb = new StringBuilder();
    
    static int T,N,M,cnt;
    static ArrayList<Integer>[] adj;
    static boolean[] visit;

    static void bfs(int start){

        Queue<Integer> que = new LinkedList<>();
        que.add(start);
        visit[start] = true;

        while(!que.isEmpty()){
            int x = que.poll();
           
            for(int y : adj[x]){
               if(visit[y]) continue;
               cnt++;
               visit[y] = true;
               que.add(y);
            }

        }
    }   

    static void pro(){
        cnt = 0 ;
        bfs(1);
        sb.append(cnt).append('\n');
    }

    static void input(){
        
        N = scan.nextInt(); // 국가 수 
        M = scan.nextInt(); //비행기 수

        adj = new ArrayList[N+1];
        for(int i=1 ; i <=N ; i++) adj[i] = new ArrayList();
 
        for(int i=0 ; i < M ; i++){
            int a = scan.nextInt() , b = scan.nextInt();
            adj[a].add(b);
            adj[b].add(a);
        }

        visit = new boolean[N+1];
    }
    
    public static void main(String[] args) {
        T = scan.nextInt();
        for(int t=0 ; t<T ; t++){
            input();
            pro();
        }
        System.out.println(sb);
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

        String nextLine(){
            String str = "";
            try {
                str = br.readLine();
            } catch (Exception e) {
                e.printStackTrace();
            }
            return str;
        }

        int nextInt(){
            return Integer.parseInt(next());
        }

        Long nextLong(){
            return Long.parseLong(next());
        }



    }

}
