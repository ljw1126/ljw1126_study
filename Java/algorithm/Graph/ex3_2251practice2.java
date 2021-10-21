
import java.util.*;
import java.io.*;

public class ex3_2251practice2 {
    
    public static class State{
        int[] X; 
        public State(int[] _X){
            X = new int[3];
            for(int i=0 ; i < 3 ; i++) X[i] = _X[i];
        }

        public State move(int from, int to, int[] limit){
            int[] nX = new int[]{ X[0], X[1], X[2]};

            if(nX[from] + nX[to] >= limit[to]){
                nX[from] -= limit[to] - nX[to];
                nX[to] = limit[to];
            }else{
                nX[to] += nX[from];
                nX[from] = 0;
            }

            return new State(nX);
        }
    }

    static FastReader scan = new FastReader();
    static StringBuilder sb = new StringBuilder();
    static boolean[][][] visit;
    static boolean[] possible;
    static int[] limit;

    public static void bfs(int a, int b, int c){
        Queue<State> que = new LinkedList<>();
        visit[a][b][c] = true;
        que.add(new State(new int[]{a, b, c}));

        while(!que.isEmpty()){
            
            State st = que.poll();
            
            if(st.X[0] == 0) possible[st.X[2]] = true;

            for(int from = 0 ; from < 3 ; from++){
                for(int to = 0 ; to < 3 ; to++){
                    if(from == to) continue;
                    State mst = st.move(from, to, limit);     // 위에 poll()한 State를 써야하는ㄴ데 

                    if(!visit[mst.X[0]][mst.X[1]][mst.X[2]]){
                        visit[mst.X[0]][mst.X[1]][mst.X[2]] = true; 
                        que.add(mst);
                    }
                }
            }
        }
    }

    public static void input(){
        limit = new int[3];
        for(int i=0; i< 3 ; i++) limit[i] = scan.nextInt();
        
        visit = new boolean[201][201][201];
        possible = new boolean[201];
    }

    public static void pro(){
        bfs(0,0, limit[2]);
        // 첫번째가 0 일때 세번재 값을 구하는게 문제 해답 , 그걸 possible에 true 표기해서 하는 거고 
        for(int i=0 ; i <= limit[2] ; i++){
            if(possible[i]) sb.append(i).append(' ');
        }

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
