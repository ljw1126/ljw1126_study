package algorithm.TopologicalSort.recommand;

import java.util.*;
import java.io.*;
/*
    음악프로그램 - 골드2
    https://www.acmicpc.net/problem/2623

    참고 블로그
    https://steady-coding.tistory.com/87

    # 입력 예시 (DAG, 사이클 없는 경우)
    6 3
    3 1 4 3
    4 6 2 5 4
    2 3 2   

    # 출력 예시 (size가 6) 
    6
    2
    1
    5
    4
    3

    ## 사이클 발생하는 경우 
    6 3
    3 1 4 3
    4 6 2 5 4
    2 2 3      // 요기 2->3 이 사이클 발생시킴 , 사이즈 2에서 끊김
*/
public class ex1_2623 {
    
    static StringBuilder sb =new StringBuilder();
    static MyReader scan = new MyReader();

    static int N,M;
    static ArrayList<Integer>[] adj;
    static int[] indegree;
    
    // 위상정렬 문제인데 , 그래프를 그릴때 사이클 있는지 확인해야 함 
    // 위상정렬은 DAG (direct acyclic graph)
    static void input(){
        N = scan.nextInt();
        M = scan.nextInt();
        
        indegree = new int[N+1];
        adj = new ArrayList[N+1];
        for(int i=1;i<=N;i++)
            adj[i] = new ArrayList<>();

        for(int i=0; i < M;i++){

            int size = scan.nextInt();
            int parent = scan.nextInt();
            for(int j = 1; j < size ; j++){
                int child = scan.nextInt();
                adj[parent].add(child);
                indegree[child]++;
                parent = child;
            }
        }
    }

    static int topologicalSort(){
        ArrayList<Integer> result = new ArrayList<>();
        Queue<Integer> que = new LinkedList();
        for(int i=1;i<=N;i++){
            if(indegree[i] == 0) que.add(i);
        }

        while(!que.isEmpty()){
            int x = que.poll();
            sb.append(x).append('\n');
            result.add(x); // 요 부분 통해서 사이클 발생 여부 확인 함

            for(int y : adj[x]){
                indegree[y]--;
                if(indegree[y] == 0) que.add(y);
            }
        }
        return result.size();
    }


    static void pro(){
        //그래프가 사이클을 돌 경우.. DAG가 아니니 계산이 안됨
        int result = topologicalSort();

        if(result != N) System.out.println(0);
        else System.out.println(sb);
    
        System.out.println("사이즈:" + result);
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

        String nextLing(){
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
