/*
    미로탐색
    https://www.acmicpc.net/problem/2178
    0 <= 지도의 크기, N,M <= 100
    정답의 최대치 : O(N^2)   // 밟게 되는 최대 개수 , 100^2 이기때문에 Integer로 충분 
*/
import java.util.*;
import java.io.*;

public class ex5_2178 {
    
    static FastReader scan = new FastReader();
    static StringBuilder sb = new StringBuilder();

    static int N,M;
    static String[] adj;
    static boolean[][] visit;
    static int[][] dist;
    static int[][] dir = {{-1,0},{0,-1},{1,0},{0, 1}};

    public static void input(){
        N = scan.nextInt();
        M = scan.nextInt();

        visit = new boolean[N][M];
        dist = new int[N][M];
        adj = new String[N];
        
        for(int i=0 ; i < N ; i++) adj[i] = scan.nextLine();
    }

    

    static void bfs(int x , int y ){
        //1. dist 배열 초기화 
        for(int i=0; i<N ; i++){
            for(int j=0; j < M ; j++){
                dist[i][j] = -1;
            }
        }

        //2. (x,y)를 Q에 넣어주고, visit 표시와 dist 값 초기화 
        Queue<Integer> q = new LinkedList<>();
        q.add(x);
        q.add(y);
        dist[x][y] = 1;       // 이걸로 visit 배열 대신 할 수 있으니 이것만 가지고도 가능(테크닉)
        visit[x][y] = true;   // 헷갈리지 않게 visit 배열 사용 

        //3. BFS 과정 시작 (multisource BFS)
        while(!q.isEmpty()){
            x = q.poll();
            y = q.poll();
            
            for(int k=0; k<4;k++){
                int nx = x + dir[k][0];
                int ny = y + dir[k][1];
                /*
                    case1. 이동하려는 좌표가 범위를 벗어나는지
                    case2. 이동하려는 좌표가 막혔는지
                    case3. 이동하려는 좌표가 이미 방문했는지 
                */
                if( nx < 0 || ny < 0 || nx >= N || ny >= M) continue;
                if(adj[nx].charAt(ny) == '0') continue;
                if(visit[nx][ny]) continue; // dist 로 대체 가능 

                q.add(nx);
                q.add(ny);
                visit[nx][ny] = true;
                dist[nx][ny] = dist[x][y] + 1;
            }
        }


    }

    public static void pro(){
        // 시작점이 (0,0)인 탐색 시작 
        bfs(0,0);
        // (N-1, M-1) 까지 필요한 최소 이동 횟수 출력 
        System.out.println(dist[N-1][M-1]);   
    }

    public static void main(String[] args) {
        input();
        pro();
        //System.out.println(Arrays.toString(adj));
        for(int[] arr : dist) System.out.println(Arrays.toString(arr));
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
