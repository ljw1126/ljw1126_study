package recommandEx;

import java.util.*;
import java.io.*;

/**
  https://www.acmicpc.net/problem/4963

  # 입력예시 1
    1 1
    0
    2 2
    0 1
    1 0
    3 2
    1 1 1
    1 1 1
    5 4
    1 0 1 0 0
    1 0 0 0 0
    1 0 1 0 1
    1 0 0 1 0
    5 4
    1 1 1 0 1
    1 0 1 0 1
    1 0 1 0 1
    1 0 1 1 1
    5 5
    1 0 1 0 1
    0 0 0 0 0
    1 0 1 0 1
    0 0 0 0 0
    1 0 1 0 1
    0 0

  # 출력예시1
    0
    1
    1
    3
    1
    9


  문제) - 이번 문제는 '대각선'도 취급함
  한 정사각형과 가로, 세로 또는 '대각선'으로 연결되어 있는 사각형은 걸어갈 수 있는 사각형이다. 
  두 정사각형이 같은 섬에 있으려면, 한 정사각형에서 다른 정사각형으로 걸어서 갈 수 있는 경로가 있어야 한다. 지도는 바다로 둘러싸여 있으며, 지도 밖으로 나갈 수 없다.

  입력)
  입력은 여러 개의 테스트 케이스로 이루어져 있다. 각 테스트 케이스의 첫째 줄에는 지도의 너비 w와 높이 h가 주어진다. w와 h는 50보다 작거나 같은 양의 정수이다.
  둘째 줄부터 h개 줄에는 지도가 주어진다. 1은 땅, 0은 바다이다.
  입력의 마지막 줄에는 0이 두 개 주어진다.

  // 격자형 그래프는 dfs로 푼다 ( for문과 재귀호출로 )
  case1. 방문했는지 확인
  case2. 벽인지 확인 
  case3. 이동할 다음 범위가 기준 범위를 벗어난 경우 
  는 처리하면 안됨!
 */
public class ex3_4963 {

    static FastReader scan = new FastReader();
    static StringBuilder sb = new StringBuilder();

    static ArrayList<Integer> result;
    static int group_cnt,w,h;
    static int[][] points = {{-1,-1},{-1,0},{-1,1},{0,-1},{0,1},{1,-1},{1,0},{1,1}};
    static boolean[][] visit;
    static int[][] graph;

    public static void input(){
        while(true){
            w = scan.nextInt(); // 열
            h = scan.nextInt(); // 행 

            if( w == 0 && h == 0 ) break; 

            graph = new int[h][w];
            visit = new boolean[h][w];
                
            for(int i=0 ; i < h ; i++){
                for(int j=0 ; j < w ; j++){
                    graph[i][j] = scan.nextInt();
                }
            }

            //for(int[] g : graph) System.out.println(Arrays.toString(g));
            pro();
        }
    }
    
    public static void dfs(int x , int y){
        visit[x][y] = true;
        group_cnt ++;
        for(int k = 0 ; k < 8; k++){
            int nx = x + points[k][0] , ny = y + points[k][1];

            // h가 행, w가 열 
            if(nx < 0 || ny < 0 || nx >= h || ny >= w) continue;
            if(visit[nx][ny]) continue;
            if(graph[nx][ny] == 0 ) continue;

            dfs(nx,ny);
        }
    }

    public static void pro(){
        result = new ArrayList<>();
        for(int i=0;i<h ;i++){
            for(int j=0; j<w ; j++){
                if(visit[i][j]) continue;
                if(graph[i][j] == 0 ) continue;
                group_cnt = 0;
                dfs(i,j);
                result.add(group_cnt);
            }
        } 

        sb.append(result.size()).append('\n');
    }

    public static void main(String[] args) {
        input();
        System.out.println(sb);    
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