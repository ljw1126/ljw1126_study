
import java.util.*;
import java.io.*;


public class ex3_2667 {

//물통의 현재 상태와 물을 붓는 행위를 관리하는 구조체 
    public static class State{
        int[] X;
        public State(int[] _X){
            X = new int[3];
            for(int i=0; i<3; i++) X[i] = _X[i];
        }

        public State move(int from, int to, int[] Limit){
            //from 물통에서 to 물통으로 물을 옮긴다. 
            int[] nX = new int[]{X[0],X[1],X[2]};
            
            /* TODO */
            if(X[from] + X[to] >= Limit[to]){
                nX[from] -= Limit[to] - X[to];
                nX[to] = Limit[to];
            }else{
                nX[to] += nX[from];
                nX[from] = 0;
            }

            return new State(nX);
        }
    }


    static FastReader scan = new FastReader();
    static StringBuilder sb = new StringBuilder();

    static int[] limit; 
    static boolean[] possible;
    static boolean[][][] visit;

    static void input(){
        limit = new int[3];
        for(int i=0;i<3;i++) limit[i] = scan.nextInt();
        visit = new boolean[205][205][205];
        possible = new boolean[205];
    }

    

    static void bfs(int x1, int x2, int x3){
        Queue<State> q = new LinkedList<>();
        // 1. visit 체크 
        visit[x1][x2][x3] = true;
        // 2. 초기값 셋팅 
        q.add(new State( new int[] {x1, x2, x3} ));
        // BFS 과정 시작
        while(!q.isEmpty()){
            State st = q.poll();
            if(st.X[0] == 0 ) possible[st.X[2]] = true;
            for(int from = 0 ; from <3 ; from++){
                for(int to = 0 ; to < 3; to++){
                    if(from == to) continue; // 같은 거라면 안해도 됨 
                    State nxt = st.move(from, to, limit);

                    if(!visit[nxt.X[0]][nxt.X[1]][nxt.X[2]]){
                        visit[nxt.X[0]][nxt.X[1]][nxt.X[2]] = true;
                        q.add(nxt);
                    }
                }
            }
        }
 
    }

    static void pro(){
        bfs(0,0, limit[2]);
        // 정답 계산하기 
        /* TODO */
        for(int i=0; i<= limit[2]; i++){
            if(possible[i]) sb.append(i).append(' ');
        }

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


