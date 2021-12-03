import java.util.*;
import java.io.*;
/*

    연구소
    https://www.acmicpc.net/problem/14502

    # 입력예시 
    7 7
    2 0 0 0 1 1 0
    0 0 1 0 1 2 0
    0 1 1 0 1 0 0
    0 1 0 0 0 0 0
    0 0 0 0 0 1 1
    0 1 0 0 0 0 0
    0 1 0 0 0 0 0

    # 출력예시 
    27

    ## int[][] blank; // 0인 지대 
    [0, 0]
    [1, 2]     // 1행 2열
    [1, 3]     // 1행 3열 
    [1, 4]
    [1, 7]
    [2, 1]
    [2, 2]
    [2, 4]
    [2, 7]
    [3, 1]
    [3, 4]
    [3, 6]
    [3, 7]
    [4, 1]
    [4, 3]
    [4, 4]
    [4, 5]
    [4, 6]
    [4, 7]
    [5, 1]
    [5, 2]
    [5, 3]
    [5, 4]
    [5, 5]
    [6, 1]
    [6, 3]
    [6, 4]
    [6, 5]
    [6, 6]
    [6, 7]
    [7, 1]
    [7, 3]
    [7, 4]
    [7, 5]
    [7, 6]
    [7, 7]
    [0, 0]
    [0, 0]
    [0, 0]
    [0, 0]
    [0, 0]
    [0, 0]
    [0, 0]
    [0, 0]
    [0, 0]
    [0, 0]
    [0, 0]
    [0, 0]
    [0, 0]
    [0, 0]

    완전탐색과 그래프 탐색이 둘다 사용되어 , 탐색에 대해 좀더 고민할 수 있는 좋은 문제 
*/


public class ex4_14502practice211203 {
    
    static StringBuilder sb = new StringBuilder();
    static MyReader scan = new MyReader();

    static int N,M,B,ans;
    static int[][] graph,blank;
    static boolean[][] visit;
    static int[][] dir = {{-1,0},{1,0},{0,1},{0,-1}};
    
    static void bfs(){
        Queue<Integer> que = new LinkedList<>();
        // 바이러스 위치를 que에 다 담아줌 
        // multisource bfs O(V+E)
        for(int i=0;i<N;i++){
            for(int j=0;j<M;j++){
                visit[i][j] = false;
                if(graph[i][j] == 2) {
                    visit[i][j] = true;
                    que.add(i);
                    que.add(j);
                }
            }
        }

        while(!que.isEmpty()){
            int x = que.poll(), y = que.poll();

            for(int k = 0 ; k < 4 ; k++){
                int dx = x + dir[k][0];
                int dy = y + dir[k][1];

                if(dx < 0 || dy < 0 || dx >= N || dy >= M) continue;
                if(visit[dx][dy]) continue;
                if(graph[dx][dy] != 0 ) continue;

                visit[dx][dy] = true; // 이거 빼먹어서 무한 루프 걸림 
                que.add(dx);
                que.add(dy);
            }
        }

        int result = 0;
        // ans값을 구한 뒤 
        for(int i=0;i<N;i++) for(int j=0; j<M;j++) if( !visit[i][j] &&graph[i][j] == 0) result++;
        ans = Math.max(ans, result);
    }

    // 완전 탐색 용도 
    static void dfs(int idx, int selected){

        if(selected == 3){
            bfs();
            return;
        }
        
        if(idx > B) return;

        graph[blank[idx][0]][blank[idx][1]] = 1;
        dfs(idx+1, selected+1);

        graph[blank[idx][0]][blank[idx][1]] = 0;
        dfs(idx+1, selected);
    }


    static void input(){
        N = scan.nextInt();
        M = scan.nextInt();

        graph = new int[N][M];
        visit = new boolean[N][M];

        for(int i=0; i < N;i++){
            for(int j=0; j< M;j++)
                graph[i][j] = scan.nextInt();
        }
    }

    static void pro(){
    
        blank = new int[N*M+1][2]; // 초기화가 틀림 
       
            for(int i=0; i < N; i++){
                for(int j=0; j < M; j++){
                    if(graph[i][j] != 0) continue;
                    B++;
                    blank[B][0] = i;
                    blank[B][1] = j;
                }
            }

        dfs(1,0);
        System.out.println(ans);
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
            String str ="";
            try {
                str = br.readLine();
            } catch (Exception e) {
                e.printStackTrace();
            }
            return str;
        }


    }


}
