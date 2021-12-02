package algorithm.TopologicalSort;

import java.util.*;
import java.io.*;
/*
    줄 세우기 - 골드 2       // 그래프를 유추해야함
    https://www.acmicpc.net/problem/2252 

    # 예제 입력 1
    3 2
    1 3
    2 3

    # 예제 출력 1
    1 2 3
    
    # 예제 입력 2
    4 2
    4 2
    3 1

    # 예제 출력 2
    4 2 3 1

    학생들 간에 위상 관계가 주어지고, 이에 맞게 줄을 세운다.
    Graph를 정의해보고 위상 정렬을 통해 문제를 해결해보자!

    정점(V) := i번 학생이 곧 i번 정점
    간선(E) := x번 학생이 y번 학생보다 먼저 서야 한다. 

*/
public class ex1_2252practice {
    
    static FastReader scan = new FastReader();
    static StringBuilder sb = new StringBuilder();

    static int N, M;
    static int[] indeg;
    static ArrayList<Integer>[] adj;

    static void input() {            
        // Adjacent List 생성 및 indegree 계산하기
        N = scan.nextInt();
        M = scan.nextInt();
        
        indeg = new int[N+1];
        adj = new ArrayList[N+1];
        
        for(int i=1;i<N+1;i++) adj[i] = new ArrayList<Integer>();
        for(int i=0;i<M;i++){
            int x = scan.nextInt(), y = scan.nextInt();
            adj[x].add(y);
            indeg[y]++; // 간선의 개수만큼 시간 걸림
        }

    
    }
    /*
        1. 정점들의 Indegree 계산하기 ( Indeg[1..N] )
        2. 들어오는 간선이 0개인 (Indeg[i] == 0) 정점들을 찾아서 자료구조 D에 넣기 
        3. D가 빌때까지 
            3-1. D에서 원소 x를 꺼내서 '정렬'시키기
            3-2. Graph에서 정점X '제거'하기    ----- x(arraylist)와 연결된 간선에 대해 indeg에 축적을 해뒀으니 그걸 - 해줌 
            3-3. '새롭게 정렬가능한 점'을 찾아서 D에 넣기 

            ※ Deque 는 양방향 Queue란다
    */
    static void pro() {
        Deque<Integer> queue = new LinkedList<>();
        // 제일 앞에 "정렬될 수 있는" 정점 찾기 ( indeg 들어오는게 0인 정점을 선별)
        for(int i=1; i<=N ;i++){
            if(indeg[i] == 0) queue.add(i);
        }

        // 정렬될 수 있는 정점이 있다면?
        // 1. 정렬 결과에 추가하기
        // 2. 정점과 연결된 간선 제거하기
        // 3. 새롭에 "정렬 될 수 있는" 정점

        // 아래 방법은 O( |V|^2 )
        // while(!queue.isEmpty()){
        //     int vertex = queue.poll();
        //     sb.append(vertex).append(' ');
           
        //     for(int y: adj[vertex]){
        //         indeg[y]--;
        //     }

        //     for(int i : adj[vertex]){
        //         if(indeg[i] != 0) continue;
        //         queue.add(indeg[i]); 
        //     }
        // }
        // 예제 출력 2 결과가 안 맞음 
        // while(!queue.isEmpty()){
        //     int vertex = queue.poll();
        //     sb.append(vertex).append(' ');
            
        //     for(int i=0; i < adj[vertex].size(); i++){
        //         indeg[adj[vertex].get(i)]--;
        //         if(indeg[adj[vertex].get(i)] == 0) queue.add(adj[vertex].get(i)); 
        //     }
            
        // }

        while(!queue.isEmpty()){
            int x = queue.poll();
            sb.append(x).append(' ');
            for(int y : adj[x]){
                indeg[y]--; // indeg[y]에 x가 들어가 있었으니 
                if(indeg[y] == 0) queue.add(y);
            }
        }

        System.out.println(sb);
    }

    public static void main(String[] args) {
        input();
        pro();
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
