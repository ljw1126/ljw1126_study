package algorithm.Tree.recommandEx;

import java.util.StringTokenizer;

/*

    가장 가까운 공통 조상 - 골드 4 
    https://www.acmicpc.net/problem/3584

    [고민할 부분]
    - 루트 노드가 없네?
      - 자식노드 설정할때 A -> B 에서 B로 가는게 없는게 루트노드
    - 공통 조상 중 깊은 노드는 어디?
    - 다시든 생각이 .. 각 노드의 레벨을 찾아야 하는게 아닐까?

*/

import java.util.*;
import java.io.*;

public class ex6_3584 {
    
    static MyReader scan = new MyReader();
    static StringBuilder sb = new StringBuilder();

    static int N, start_a, start_b, root_node;
    static ArrayList<Integer>[] adj;
    static boolean[] findRoot;
    static int[] parents, depths;

    static void bfs(int start, int par){
        depths = new int[N+1];
        parents = new int[N+1];
        parents[start] = -1;
        depths[start] = 1;

        Queue<Integer> que = new LinkedList();
        que.add(start);

        while(!que.isEmpty()){
            int x = que.poll();

            for(int y : adj[x]){
                //if(y == par) continue;
                depths[y] = depths[x] + 1;
                parents[y] = x;
                que.add(y);
            }
        }
    }

    static void lca(int a, int b){
        int depthA = depths[a];
        int depthB = depths[b];

        while(depthA > depthB){
            a = parents[a]; 
            depthA--;
        }

        while(depthB > depthA){
            b = parents[b];
            depthB--;
        }

        while(a != b){
            a = parents[a];
            b = parents[b];
        }
        
        sb.append(a).append('\n');

    }

    static void pro(){
        bfs(root_node, -1);
        //System.out.println(Arrays.toString(parents));
        //System.out.println(Arrays.toString(depths));
        lca(start_a, start_b);
        
    }

    static void input(){
        N = scan.nextInt();
        
        findRoot = new boolean[N+1];
        adj = new ArrayList[N+1];
        for(int i=1 ; i <= N ; i++) adj[i] = new ArrayList();

        for(int i=0; i < N-1; i++){
            int a = scan.nextInt(), b = scan.nextInt();
            adj[a].add(b);  
            //adj[b].add(a);
            findRoot[b] = true;
        }

        start_a = scan.nextInt();
        start_b = scan.nextInt();

        // 루트 노드 찾기 ! 
        for(int i=1; i <= N ; i++){
            if(!findRoot[i]) {
                root_node = i;
                break;
            }
        }
    
    }

    public static void main(String[] args) {
        int T = scan.nextInt();
        for(int t = 0 ; t < T ; t++){
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
