
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
public class ex7_3055practice {
    

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
    static void bfs_water() {
        Queue<Integer> Q = new LinkedList<>();
        // 모든 물의 위치를 Q에 전부 넣어주기!
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                // dist_water 와 visit 배열 초기화
                visit[i][j] = false; 
                dist_water[i][j] = -1;
                /* TODO */
                if (a[i].charAt(j) == '*') {
                    Q.add(i);
                    Q.add(j);
                    visit[i][j] = true;
                    dist_water[i][j] = 0;
                }
            }
        }

        // BFS 과정 시작
        while(!Q.isEmpty()){
            int x = Q.poll(), y = Q.poll();

            for(int k=0 ; k < 4 ; k++){
                int nx = x + dir[k][0] , ny = y + dir[k][1];
                if(nx < 0 || ny < 0 || nx >= N || ny >= M) continue;
                if(visit[nx][ny]) continue;
                if(a[nx].charAt(ny) != '.') continue; // 빈공간이 아닌 경우 물이 퍼질 수 없음

                visit[nx][ny] = true;
                dist_water[nx][ny] = dist_water[x][y] + 1;
                Q.add(nx);
                Q.add(ny);
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
                visit[i][j] = false; 
                dist_hedgehog[i][j] = -1;
                
                if (a[i].charAt(j) == 'S') { //고슴도치 위치 찾을 경우 
                    visit[i][j] = true;
                    dist_hedgehog[i][j] = 0;
                    Q.add(i);
                    Q.add(j);
                }
            }
        }
        
        // BFS 과정 시작
        while(!Q.isEmpty()){
            int x = Q.poll(), y = Q.poll();

            for(int k=0 ; k < 4 ; k++){
                int nx = x + dir[k][0] , ny = y + dir[k][1];
                if(nx < 0 || ny < 0 || nx >= N || ny >= M) continue;
                if(visit[nx][ny]) continue;
                if(a[nx].charAt(ny) != '.' && a[nx].charAt(ny) != 'D' ) continue; 
                if(dist_water[nx][ny] != -1 && dist_water[nx][ny] <= dist_hedgehog[x][y] + 1 ) continue;

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
        for(int[] i : dist_water ) System.out.println(Arrays.toString(i));

        // 고슴도치가 물을 피해 탐색할 수 있는 공간 찾기
        bfs_hedgehog();
        for(int[] i : dist_hedgehog ) System.out.println(Arrays.toString(i));

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
