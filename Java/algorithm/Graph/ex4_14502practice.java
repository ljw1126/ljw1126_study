

import java.util.*;
import java.io.*;

public class ex4_14502practice {
    
    static FastReader scan = new FastReader();

    static int N,M,B,ans;
    static int[][] blank, graph;
    static boolean[][] visit;
    static int[][] dir = {{-1,0},{0,-1},{1,0},{0,1}};

    //Multisource BFS , O(V+E) 시간복잡도 가진다는데..
    static void bfs(){
        Queue<Integer> que = new LinkedList<>();
        // 모든 바이러스가 시작점으로 가능하니깐, 전부 큐에 넣어준다.
        for(int i=1; i <= N ; i++){
            for(int j=1; j<=M ; j++){
                visit[i][j] = false; 
                if(graph[i][j] == 2){ // 바이러스라면
                    visit[i][j] = true;
                    que.add(i);
                    que.add(j);
                } 
            }
        }
        //BFS 과정
        while(!que.isEmpty()){
            int x = que.poll();
            int y = que.poll();

            for(int k=0; k<4 ;k++){
                int nx = x + dir[k][0], ny = y+ dir[k][1];

                /*
                    case가 아닌경우 dfs처리 하지 않음 ( 여기서는 단순히 visit[nx][ny] = true 만 )
                */
                if( nx < 1 || ny < 1 || nx > N || ny > M ) continue; 
                if(graph[nx][ny] != 0 ) continue;
                if(visit[nx][ny]) continue;

                visit[nx][ny] = true;
                que.add(nx);
                que.add(ny);
            }
        }

        //탐색이 종료된 시점이니, 안전 영역의 넓이를 계산하고 정답을 갱신한다
        int cnt = 0 ;
        for(int i=1; i<= N ; i++) 
            for(int j=1; j <= M ; j++)
                if(!visit[i][j] && graph[i][j] == 0) cnt++;
        ans = Math.max(ans, cnt);
    }

    //idx 번째 빈 칸에 벽을 세울지 말지 결정해야 하고, 이전까지 selected_cnt 개의 벽을 세웠다.
    static void dfs(int idx, int selected_cnt){
        if(selected_cnt == 3){ //3개의 벽을 모두 세운 상태
            bfs();
            return;
        }
        if(idx > B) return; // 더이상 세울 수 있는 벽이 없는 상태
    
        graph[blank[idx][0]][blank[idx][1]] = 1;
        dfs(idx+1, selected_cnt+1);

        graph[blank[idx][0]][blank[idx][1]] = 0;
        dfs(idx+1, selected_cnt);
    }

    static void input(){
        N = scan.nextInt();
        M = scan.nextInt();

        graph = new int[N+1][M+1];
        for(int i=1; i <= N ; i++){
            for(int j=1; j<=M ; j++){
                graph[i][j] = scan.nextInt();
            }
        }
        visit = new boolean[N+1][M+1];
        blank = new int[N*M+1][2];

        //for(int[] i : graph) System.out.println(Arrays.toString(i));
    }

    static void pro(){
        // 0인 지점을 blank[][] 배열에 정리함 
        // 이때 0의 갯수를 전역변수에 카운트 올림 
        for(int i=1; i <= N ; i++){
            for(int j=1; j<=M ; j++){
                if(graph[i][j] != 0) continue;
                B++;
                blank[B][0] = i; // 맞는지 확인 필요
                blank[B][1] = j;
            }
        }

        //for(int[] i : blank) System.out.println(Arrays.toString(i));

        dfs(1,0);
        System.out.println(ans);
    }

    /*
        1. 벽 3개를 세우는 조합을 구함 
        2. 벽 3개를 구했을때마다 bfs로 바이러스를 퍼뜨림
        3. 안전한 지역 구해서 최대 갯수 Math.max(ans, cnt); 갱신
    */
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
