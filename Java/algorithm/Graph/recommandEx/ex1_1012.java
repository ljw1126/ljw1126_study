package recommandEx;
/*
    https://www.acmicpc.net/problem/1012

    # 예제 입력1 
    2
    10 8 17
    0 0
    1 0
    1 1
    4 2
    4 3
    4 5
    2 4
    3 4
    7 4
    8 4
    9 4
    7 5
    8 5
    9 5
    7 6
    8 6
    9 6
    10 10 1
    5 5

    # 예제 출력1
    5
    1

    # 예제 입력 2
    1
    5 3 6
    0 2
    1 2
    2 2
    3 2
    4 2
    4 0

    # 예제 출력 2
    2

    
    입력의 첫 줄에는 테스트 케이스의 개수 T가 주어진다. 
    그 다음 줄부터 각각의 테스트 케이스에 대해 
    첫째 줄에는 배추를 심은 
    배추밭의 가로길이 M(1 ≤ M ≤ 50)과 세로길이 N(1 ≤ N ≤ 50), 
    그리고 배추가 심어져 있는 위치의 개수 K(1 ≤ K ≤ 2500)이 주어진다. 

    그 다음 K줄에는 배추의 위치 X(0 ≤ X ≤ M-1), Y(0 ≤ Y ≤ N-1)가 주어진다. 
    두 배추의 위치가 같은 경우는 없다.

*/

import java.util.*;
import java.io.*;
// 유기농 배추 - 직접품 맞음
// 격자형 그래프 문제의 경우 DFS로 문제를 푸는게 좋다 ! (stack 형인데 for문과 재귀로 풀 수 있다.)
public class ex1_1012 {
    
    static FastReader scan = new FastReader();
    static StringBuilder sb = new StringBuilder();

    static int T,M,N,K, group_cnt;
    static int[][] areas; // 배추밭 
    static boolean[][] visit; // 방문여부
    static int[][] points = {{-1,0},{0,-1},{1,0},{0,1}};
    static ArrayList<Integer> group;
    

    public static void input(){
        T = scan.nextInt(); // 테스트의 개수
        for(int t = 0 ; t < T ; t++){    
            M = scan.nextInt(); // 배추밭의 가로길이 
            N = scan.nextInt(); // 배추밭의 세로길이
            K = scan.nextInt(); // 배추가 심어져 있는 위치 개수
        
            areas = new int[M][N];
            visit = new boolean[M][N];

            for(int i=0; i< K ;i++){ 
                int x = scan.nextInt(), y = scan.nextInt();
                areas[x][y] = 1;
            }

            pro(0,0);
        }
        //for(int[] i : areas) System.out.println(Arrays.toString(i));
    }

    public static void dfs(int x , int y){
        group_cnt ++;
        visit[x][y] = true;

        for(int k = 0 ; k < 4 ; k++){
            int nx = x + points[k][0] , ny = y + points[k][1];
            if( nx < 0 || ny < 0 || nx >= M || ny >= N) continue;
            if(areas[nx][ny] == 0 ) continue;
            if(visit[nx][ny]) continue;

            dfs(nx,ny);
        }
    }

    public static void pro(int x, int y ){
        group = new ArrayList<Integer>();
        
        for(int i=0 ; i < M ; i++){
            for(int j=0; j < N ; j++){
                if(areas[i][j] == 0) continue;
                if(visit[i][j]) continue; 
                group_cnt = 0;
                dfs(i,j);
                group.add(group_cnt);
            }
        }

        sb.append(group.size()).append('\n');
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
