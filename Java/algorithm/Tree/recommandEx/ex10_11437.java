package algorithm.Tree.recommandEx;

import java.util.*;
import java.io.*;
/*
    LCA - 골드 3
    https://www.acmicpc.net/problem/11437

    # 예제 입력 1 
    15          // 노드의 개수 N 
    1 2
    1 3
    2 4
    3 7
    6 2
    3 8
    4 9
    2 5
    5 11
    7 13
    10 4
    11 15
    12 5
    14 7
    6           //구하려고 하는 케이스 
    6 11        //두 정점의 가장 가까운 공통 조상 출력
    10 9
    2 6
    7 6
    8 13
    8 15

    # 예제 출력 1 
    2
    4
    2
    1
    3
    1


    부모 노드    [0, -1, 1, 1, 2, 2, 2, 3, 3, 4, 4, 5, 5, 7, 7, 11]
    레벨         [0, 0, 1, 1, 2, 2, 2, 2, 2, 3, 3, 3, 3, 3, 3, 4]
    자식 노드 수 [0, 15, 9, 5, 3, 4, 1, 3, 1, 1, 1, 2, 1, 1, 1, 1]

                    1
            2             3
        4   5    6       7    8
    9 10  11 12        13 14
          15  
            

*/
public class ex10_11437 {
    static MyReader scan = new MyReader();
    static StringBuilder sb = new StringBuilder();

    static int N,M;
    static ArrayList<Integer>[] adj;
    static ArrayList<Question>[] question;
    static int[] parents;
    static int[] depths;

    static class Question{
        int from;
        int to;
        public Question(int _from, int _to){
            this.from = _from;
            this.to = _to;
        }
    }

    static void lca(int a, int b){
        
        int depthA = depths[a];
        int depthB = depths[b];

        while(depthA > depthB){
            a = parents[a];
            depthA = depths[a];
        }

        while(depthA < depthB){
            b = parents[b];
            depthB = depths[b];
        }

        while(a != b){
            a = parents[a];
            b = parents[b];
        }

        sb.append(a).append('\n');
    }


    static void dfs(int start, int par){
        //depths[start] = 1; 

        for(int i : adj[start]){
            if(i == par) continue;
            depths[i] = depths[start] + 1;
            parents[i] = start; // 부모 노드 기록
            dfs(i, start);
            //depths[start] += depths[i]; 
        }
    }

    static void pro(){
        //root는 1번이라고 문제에 명시되어 잇음
        parents[1] = -1;
        dfs(1,-1);      
        //System.out.println(Arrays.toString(parents));
        //System.out.println(Arrays.toString(depths));  
        
        // 구하려고 하는 M개의 공통 조상 찾기
        for(int i=0 ; i < question.length ; i++){
            Question q = question[i].get(0);
            lca(q.from , q.to);
        }
        
        // 정답 출력
        System.out.println(sb);
    }

    static void input(){
        N = scan.nextInt();

        parents = new int[N+1]; // 각 노드별 부모 노드 표시 
        depths = new int[N+1]; // 현재 레벨 표시 
        adj = new ArrayList[N+1];
        for(int i=1; i <= N ; i++) adj[i] = new ArrayList();

        for(int i=1 ; i < N ; i++){
            int a = scan.nextInt(), b = scan.nextInt();
            adj[a].add(b);
            adj[b].add(a);
        }

        // 문제에서 값을 구하고 싶은 두 항목
        M = scan.nextInt();
        question = new ArrayList[M];
        for(int i=0; i < M ; i++){
            int from = scan.nextInt(), to = scan.nextInt();
            question[i] = new ArrayList();
            question[i].add(new Question(from, to));
        }
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
