package algorithm.TopologicalSort.recommand;

import java.util.*;
import java.io.*;
/*

    https://www.acmicpc.net/problem/9470

    초기 설정시 
    	        1	2	3	4	5	6	7
                -------------------------
    indegree	0	0	2	2	1	0	3
    order	    1	1	0	0	0	1	0
    maxCnt	    1	1	0	0	0	1	0
                                
    que {} // 직접 쳐서 큐를 넣고 밑에 초기값 복붙 수정해가면서 해보길 

    pop = 1							
                1	2	3	4	5	6	7
                -------------------------
    indegree	0	0	1*	2	1	0	3
    order	    1	1	1*	0	0	1	0
    maxCnt  	1	1	1*	0	0	1	0
                                
    pop=2							
                1	2	3	4	5	6	7
                -------------------------
    indegree	0	0	0(추가)	2	1	0	3
    order	    1	1	1	0	0	1	0
    maxCnt	    1	1	2*	0	0	1	0
                                
    pop=6							
                1	2	3	4	5	6	7
                -------------------------
    indegree	0	0	0	1*	1	0	2*
    order	    1	1	1	1*	0	1	1*
    maxCnt	    1	1	2	1*	0	1	1*
                                
    pop=3				    4,5추가됨			
                1	2	3	4	5	6	7
                -------------------------
    indegree	0	0	0	0*	0*	0	2
    order	    1	1	2*	2*	2*	1	1
    maxCnt	    1	1	2	1*	1*	1	1
                                
    pop=4							
                1	2	3	4	5	6	7
                -------------------------
    indegree	0	0	0	0	0	0	1*
    order	    1	1	2	2	2	1	2*
    maxCnt	    1	1	2	1	1	1	1*
                                
    pop=5			                   큐에 7 추가 				
                1	2	3	4	5	6	7
                -------------------------
    indegree	0	0	0	0	0	0	0*
    order	    1	1	2	2	2	1	2*
    maxCnt	    1	1	2	1	1	1	2*
                                
    pop=7							
                1	2	3	4	5	6	7
                -------------------------
    indegree	0	0	0	0	0	0	0
    order	    1	1	2	2	2	1	3*
    maxCnt	    1	1	2	1	1	1	2


*/

public class ex2_9470practice {

    static FastReader scan = new FastReader();
    static StringBuilder sb = new StringBuilder();

    static int K, N, M;
    static int[] indeg, order, maxCnt;
    static ArrayList<Integer>[] adj;

    static void input() {
        K = scan.nextInt();
        N = scan.nextInt();
        M = scan.nextInt();
        adj = new ArrayList[N + 1];
        indeg = new int[N + 1];
        order = new int[N + 1];
        maxCnt = new int[N + 1];
        for (int i = 1; i <= N; i++)
            adj[i] = new ArrayList<>();

        for (int i = 0; i < M; i++) {
            int x = scan.nextInt(), y = scan.nextInt();
            adj[x].add(y);
            indeg[y]++;
        }
    }

    static void pro() {
        Deque<Integer> queue = new LinkedList<>();
        // 제일 앞에 "정렬될 수 있는" 정점 찾기
        for(int i=1;i<=N;i++){
            if(indeg[i] == 0){
                queue.add(i);
                order[i] = maxCnt[i] = 1;
            }
        }

        // Strahler 순서를 고려해서 위상정렬을 수행하자.
        int ans = 0;
        
        while(!queue.isEmpty()){
            int x = queue.poll();

            if(maxCnt[x] >= 2) order[x]++;
            ans = Math.max(ans, order[x]);

            for(int y : adj[x]){
                indeg[y]--;
                if(indeg[y] == 0) queue.add(y);

                if(order[y] == order[x]) maxCnt[y]++;
                else if(order[y] < order[x]){
                    order[y] = order[x];
                    maxCnt[y] = 1;
                }
            }

        }


        System.out.println(K + " " + ans);
    }

    public static void main(String[] args) {
        int T = scan.nextInt();
        while (T-- > 0) {
            input();
            pro();
        }
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
