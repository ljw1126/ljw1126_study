
import java.util.*;
import java.io.*;

// 격자형 그래프는 DFS 활용 ! 
public class ex2_2667practice {
    
    static FastReader scan = new FastReader();
    static StringBuilder sb = new StringBuilder();

    //변수 선언
    static int N, group_cnt; 
    static String[] graph;
    static boolean[][] visit;
    static ArrayList<Integer> result;
    static int[][] point = {{-1,0},{0,-1},{1,0},{0,1}};

    public static void input(){
        N = scan.nextInt();
        graph = new String[N];
        visit = new boolean[N][N];

        for(int i = 0 ; i < N ; i++) graph[i] = scan.nextLine();
    }
    public static void dfs(int i, int j){
        group_cnt++;
        visit[i][j] = true;

        for(int k=0; k < 4; k++){
            int nx = i + point[k][0];
            int ny = j + point[k][1];

            /*
                case1. 좌표 범위를 벗어났을때 
                case2. 해당 좌표값이 0 일때 
                case3. 방문했을때 
            */
            if( nx < 0 || ny < 0 || nx >= N || ny >= N) continue;
            if(graph[nx].charAt(ny) == '0') continue; // 요걸 잊어서 틀림
            if(visit[nx][ny]) continue;

            dfs(nx,ny);

        }

    }
    public static void pro(){
        result = new ArrayList<>();
        for(int i=0 ; i < N ; i++){
            for(int j=0; j < N; j++){
                if(!visit[i][j] && graph[i].charAt(j) == '1'){
                    group_cnt = 0;
                    dfs(i,j);
                    result.add(group_cnt);
                }
            }
        }

        Collections.sort(result);
        sb.append(result.size()).append('\n');
        for(int x : result) sb.append(x).append('\n');
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
