
import java.util.*;
import java.io.*;
//visit 만들지 않고 해보기 
// O(N^2)     >> 10,000
public class ex5_2178practice {
    
    static FastReader scan = new FastReader();
    static StringBuilder sb = new StringBuilder();

    static int N,M;
    static String[] graph;
    static int[][] dist;
    static int[][] points = {{-1,0},{0,-1},{1,0},{0,1}};

    //입력값 초기화
    public static void input(){
        N = scan.nextInt();
        M = scan.nextInt();
        
        graph = new String[N];
        for(int i=0 ; i < N ; i++) graph[i] = scan.nextLine();

        dist = new int[N][M];
    }

    public static void bfs(int x, int y){
         // 1. dist 와 que 초기화 
        for(int i=0 ; i < N ; i++){
            for(int j=0; j < M; j++){ 
                dist[i][j] = -1;
            }
        }
        
        Queue<Integer> que = new LinkedList<>();
        que.add(x);
        que.add(y);
    
        dist[x][y] = 1;

        while(!que.isEmpty()){
            int a = que.poll();
            int b = que.poll();

            for(int k=0; k < 4; k++){
                int na = a + points[k][0];
                int nb = b + points[k][1];

                if(na < 0 || nb < 0 || na >= N || nb >= M) continue;
                if(graph[na].charAt(nb) == '0') continue;
                if(dist[na][nb] >= 1) continue; 

                dist[na][nb] = dist[a][b] + 1;
                que.add(na);
                que.add(nb);
            }
        }
    }

    //로직 동작 
    public static void pro(){
        bfs(0,0);
        //최종결과 출력
        System.out.println(dist[N-1][M-1]);
    }

    public static void main(String[] args) {
        input();
        pro();
        for(int[] i : dist) System.out.println(Arrays.toString(i));
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
