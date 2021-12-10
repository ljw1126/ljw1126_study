package algorithm.Tree.recommandEx;

import java.util.StringTokenizer;

/*
    트리와 쿼리 - 골드 5 // 서브트리 , 부모노드 구하는 거에 대한 설명이 문제 아래에 있어서 좋은 문제 
    https://www.acmicpc.net/problem/15681

    #문제
    간선에 가중치와 방향성이 없는 임의의 루트 있는 트리가 주어졌을 때, 아래의 쿼리에 답해보도록 하자.

    정점 U를 루트로 하는 서브트리에 속한 정점의 수를 출력한다.
    만약 이 문제를 해결하는 데에 어려움이 있다면, 하단의 힌트에 첨부한 문서를 참고하자.

    #입력
    트리의 정점의 수 N과 루트의 번호 R, 쿼리의 수 Q가 주어진다. (2 ≤ N ≤ 105, 1 ≤ R ≤ N, 1 ≤ Q ≤ 105)

    이어 N-1줄에 걸쳐, U V의 형태로 트리에 속한 간선의 정보가 주어진다. (1 ≤ U, V ≤ N, U ≠ V)

    이는 U와 V를 양 끝점으로 하는 간선이 트리에 속함을 의미한다.

    이어 Q줄에 걸쳐, 문제에 설명한 U가 하나씩 주어진다. (1 ≤ U ≤ N)

    입력으로 주어지는 트리는 항상 올바른 트리임이 보장된다.

    #출력
    Q줄에 걸쳐 각 쿼리의 답을 정수 하나로 출력한다.

                5      
            4        6 
        3         7  8  9 
      1   2         

      parents [0, 3, 3, 4, 5, -1, 5, 6, 6, 6]
      subtree [0, 1, 1, 3, 4, 9, 4, 1, 1, 1]


    #예제 입력 
    9 5 3   // 트리 정점수 N , 루트번호 R, 쿼리수 Q 
    1 3     // N-1줄 걸쳐 U,V 형태로 트리의 간선 정보 주어짐 (U,V를 양 끝점으로 하는 간선이 트리에 속함을 의미)
    4 3
    5 4
    5 6
    6 7
    2 3
    9 6
    6 8
    5       // Q줄에 걸쳐 문제에 설명한 U가 하나씩 주어짐 
    4
    8

    # 예제 출력 
    9
    4
    1

    211210 문제 설명 차근하게 읽고 직접 품 
    
*/

import java.io.*;
import java.util.*;

public class ex5_15681 {
    
    static MyReader scan = new MyReader();
    static StringBuilder sb = new StringBuilder();

    static int[] parents;
    static int[] subtree;
    static int N,R,Q;
    static ArrayList<Integer>[] adj;
    static int[] question;

    static void input(){
        N = scan.nextInt(); // 트리 정점 수 
        R = scan.nextInt(); // 루트 번호 
        Q = scan.nextInt(); // 쿼리 수 
        
        parents = new int[N+1];
        subtree = new int[N+1];
        
        adj = new ArrayList[N+1];
        for(int i=1; i <= N ; i++) adj[i] = new ArrayList();
    
        for(int i=0; i < N-1; i++){
            int u = scan.nextInt(), v = scan.nextInt();
            adj[u].add(v);
            adj[v].add(u);
        }

        question = new int[Q];
        for(int i=0 ; i < Q ; i++){
            question[i] = scan.nextInt();
        }
    }

    static void dfs(int x, int par){
 
        subtree[x] = 1; 

        for(int y : adj[x]){
            if(y == par) continue;
            parents[y] = x;
            dfs(y,x);
            subtree[x] += subtree[y];  
        }

    }

    static void pro(){
        parents[R] = -1;
        dfs(R, -1);
        for(int i : question){
            sb.append(subtree[i]).append('\n');
        }
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
