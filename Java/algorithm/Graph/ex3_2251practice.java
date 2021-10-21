import java.util.*;
import java.io.*;

public class ex3_2251practice {
    
    //물통의 현재 상태와 물을 붓는 행위를 관리하는 구조체 
    public static class State{
        int[] X;
        public State(int[] _X){
            X = new int[3];
            for(int i=0;i<3;i++) X[i] = _X[i];
        }

        // from 물통에서 to 물통으로 물을 옮긴다. ( Limit는 처음 입력값)
        public State move(int from, int to, int[] Limit){
            int[] nX = new int[]{X[0],X[1],X[2]};

            if(X[from] + X[to] >= Limit[to]){ // 물이 넘친다면
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

    public static void input(){
        limit = new int[3]; // 초기 물통 용량 
        for(int i=0;i<3;i++) limit[i] = scan.nextInt();
        visit = new boolean[201][201][201]; // 800만
        possible = new boolean[201];
    }

    public static void bfs(int x1, int x2, int x3){
        Queue<State> q = new LinkedList<>();
        
        visit[x1][x2][x3] = true;
        q.add(new State(new int[] {x1,x2,x3} ));
        // BFS 과정 시작 
        while(!q.isEmpty()){
            State st = q.poll();
            
            if(st.X[0] == 0) possible[st.X[2]] = true; // 구하고자 하는 값( 첫번째가 비었을때 세번째 통의 값)
            
            for(int from = 0 ; from < 3 ; from++){
                for(int to = 0 ; to < 3 ; to++){
                    if(from == to) continue; // 자기자신을 swap할 필요없음

                    State nxt = st.move(from, to, limit);
                    if(!visit[nxt.X[0]][nxt.X[1]][nxt.X[2]]){ // 조회하지 않은 경우
                        visit[nxt.X[0]][nxt.X[1]][nxt.X[2]] = true;
                        q.add(nxt);
                    }
                }
            }
        }
    }

    public static void pro(){
        bfs(0,0, limit[2]); // 앞에 두개는 비워져있고, 세번째만 차 있을때
        // 정답 계산하기 
        /* TODO */
        for(int i=0; i <= limit[2]; i++)
            if(possible[i]) sb.append(i).append(' ');
        
        System.out.println(sb);
    }

    public static void main(String[] args) {
        input();
        pro();
        // for(boolean[][] arr : visit){
        //     System.out.println(Arrays.toString(arr));
        // }
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
