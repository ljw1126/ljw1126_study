package algorithm.TopologicalSort;

import java.util.*;
import java.io.*;

public class ex2_1005practice211205 {
    
    static StringBuilder sb = new StringBuilder();
    static MyReader scan = new MyReader();

    static int T, N, K, W;
    static ArrayList<Integer>[] adj;
    static int[] Times,Time_done, indeg;

    static void input(){
        N = scan.nextInt(); // 건물 개수 
        K = scan.nextInt(); // 건물 순서 규칙 
        
        indeg = new int[N+1];
        Times = new int[N+1];
        Time_done = new int[N+1];
        adj = new ArrayList[N+1];
        for(int i=1;i<=N;i++){
            adj[i] = new ArrayList<>();
            Times[i] = scan.nextInt();
        }

        for(int i=0;i<K;i++){
            int x = scan.nextInt(), y = scan.nextInt();
            adj[x].add(y);
            indeg[y]++;
        }

        W = scan.nextInt();
    }
    /*
        indegree가 같은 레벨인건 어떻게 알고 
        같은 레벨일때 최대값을 어떻게 더할 수 있을까?
        => 축적하려니 초기값 설정을 못하겠네.. indegree == 0 인 경우가 2개일때 
    */
    static void pro(){

        Queue<Integer> que = new LinkedList<>();

        for(int i=1;i<=N;i++){
            if(indeg[i] == 0) {
                que.add(i);
                Time_done[i] = Times[i];
            }
        }

        while(!que.isEmpty()){
            int x = que.poll();

            for(int y : adj[x]){
                indeg[y]--;
                if(indeg[y] == 0) que.add(y);
                Time_done[y] = Math.max(Time_done[y], Time_done[x] + Times[y]); // 여길거라고 생각은 했는데.. 0이 아니더라도 축적
            }
        }

        sb.append(Time_done[W]).append('\n');

    }
    public static void main(String[] args) {
        T = scan.nextInt();
        for(int t=0;t<T;t++){
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
