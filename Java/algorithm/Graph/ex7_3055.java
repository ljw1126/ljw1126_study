import java.util.*;
import java.io.*;
/*
    R행, C열 
    '.' 비어있는 곳 
    '*' 물이 차있는 지역 
    'X' 돌 
    'D' 비버의 굴 
    'S' 고슴도치의 위치

    티떱숲의 지도가 주어졌을 때, 고슴도치가 안전하게 비버의 굴로 이동하기 위해 
    필요한 최소 시간을 구하는 프로그램을 작성하시오.

    고슴도치는 물이 찰 예정인 칸으로 이동할 수 없다. 
    즉, 다음 시간에 물이 찰 예정인 칸으로 고슴도치는 이동할 수 없다. 
    이동할 수 있으면 고슴도치가 물에 빠지기 때문이다. 
    
    https://www.acmicpc.net/problem/3055

*/
public class ex7_3055 {
    
    static FastReader scan = new FastReader();
    static StringBuilder sb = new StringBuilder();

    static int N, M;
    static String[] a;
    static int[][] dist_water, dist_hedgehog;
    static boolean[][] visit;
    static int[][] dir = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
    static ArrayList<Integer> group;

    static void input() {
        N = scan.nextInt();
        M = scan.nextInt();
        a = new String[N];
        for (int i = 0; i < N; i++)
            a[i] = scan.nextLine();
        visit = new boolean[N][M];
        dist_water = new int[N][M];
        dist_hedgehog = new int[N][M];
    }

    // 모든 물들을 시작으로 동시에 BFS 시작!
    // Multisource BFS 적용 , 모든 물을 동시에 Queue에 넣고 시작하면 단 한번의 BFS, O(N^2)
    // 각 격자에 물이 잠기는 빠른 시간을 구할 수 있다. 
    static void bfs_water() {
        Queue<Integer> Q = new LinkedList<>();
        // 모든 물의 위치를 Q에 전부 넣어주기!
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                // dist_water 와 visit 배열 초기화
                dist_water[i][j] = -1;
                visit[i][j] = false;
                
                if (a[i].charAt(j) == '*') { // 물이 차있는 지역 
                    Q.add(i);
                    Q.add(j);
                    dist_water[i][j] = 0;
                    visit[i][j] = true;
                }
            }
        }

        // BFS 과정 시작
        /* TODO */
        // 물이 해당하는 칸에 도달하기까지 걸리는 시간 (최소시간) = 물에서 시작하는 BFS를 하자 ! 
        while(!Q.isEmpty()){
            int x = Q.poll(), y = Q.poll();
            for(int k = 0; k < 4; k++){
                int nx = x + dir[k][0] , ny = y + dir[k][1];
                if(nx < 0 || ny < 0 || nx >= N || ny >= M) continue;
                if(a[nx].charAt(ny) != '.') continue;
                if(visit[nx][ny]) continue;

                Q.add(nx);
                Q.add(ny);
                visit[nx][ny] = true;
                dist_water[nx][ny] = dist_water[x][y] +1;
            }
        }
    }

    // 고슴도치를 시작으로 동시에 BFS 시작!
    static void bfs_hedgehog() {
        Queue<Integer> Q = new LinkedList<>();
        // 고슴도치 위치를 Q에 넣어주기!
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                // dist_hedgehog 와 visit 배열 초기화
                dist_hedgehog[i][j] = -1;
                visit[i][j] = false;
                
                if (a[i].charAt(j) == 'S') { // 고슴도치 시작점 찾을 경우 
                    Q.add(i);
                    Q.add(j);
                    dist_hedgehog[i][j] = 0;
                    visit[i][j] = true;    
                }
            }
        }
        
        // BFS 과정 시작 ( 탐색 조건이 .. 물에도 안잠기고 벽도 아닌칸에 고슴도치가 갈 수 있다. )
        while(!Q.isEmpty()){
            int x = Q.poll() , y = Q.poll();
            for(int k=0 ; k < 4 ; k++){
                int nx = x + dir[k][0] , ny = y + dir[k][1];
                if(nx < 0 || ny < 0 || nx >= N || ny >= M) continue; // 지도를 벗어나는 곳으로 가는가?
                if(visit[nx][ny]) continue; // 이미 방문한 적이 있는 곳인가?
                if(a[nx].charAt(ny) != '.' && a[nx].charAt(ny) != 'D') continue; // 갈 수 있는 칸인지 확인해야 한다.
                if (dist_water[nx][ny] != -1 && dist_water[nx][ny] <= dist_hedgehog[x][y] + 1) continue;  // 물에 잠기지는 않는가?

                visit[nx][ny] = true;
                dist_hedgehog[nx][ny] = dist_hedgehog[x][y] + 1;
                Q.add(nx);
                Q.add(ny);
            }
        }
    }

    static void pro() {
        // 각 칸마다 물에 닿는 시간 계산하기
        bfs_water();
        for(int[] i : dist_water) System.out.println(Arrays.toString(i));
        System.out.println();
        // 고슴도치가 물을 피해 탐색할 수 있는 공간 찾기
        bfs_hedgehog();
        for(int[] i : dist_hedgehog) System.out.println(Arrays.toString(i));
        // 탈출구 'D' 에 대한 결과를 통해 정답 출력하기
        for (int i=0;i<N;i++){
            for (int j=0;j<M;j++){
                if (a[i].charAt(j) == 'D'){
                   if(dist_hedgehog[i][j] == -1 ) System.out.println("KAKTUS");
                   else System.out.println(dist_hedgehog[i][j]);
                }
            }
        }
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
