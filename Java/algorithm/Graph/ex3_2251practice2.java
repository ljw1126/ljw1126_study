
import java.util.*;
import java.io.*;
/*
    https://www.acmicpc.net/problem/2251
    [물통]

    문제)
    각각 부피가 A, B, C(1≤A, B, C≤200) 리터인 세 개의 물통이 있다. 
    처음에는 앞의 두 물통은 비어 있고, 세 번째 물통은 가득(C 리터) 차 있다. 
    이제 어떤 물통에 들어있는 물을 다른 물통으로 쏟아 부을 수 있는데, 
    이때에는 한 물통이 비거나, 다른 한 물통이 가득 찰 때까지 물을 부을 수 있다. 
    이 과정에서 손실되는 물은 없다고 가정한다.

    이와 같은 과정을 거치다보면 세 번째 물통(용량이 C인)에 담겨있는 
    물의 양이 변할 수도 있다. 
    첫 번째 물통(용량이 A인)이 비어 있을 때, 세 번째 물통(용량이 C인)에 담겨있을 수 있는 
    물의 양을 모두 구해내는 프로그램을 작성하시오.

    입력)
    첫째 줄에 세 정수 A, B, C가 주어진다.

    출력)
    첫째 줄에 공백으로 구분하여 답을 출력한다. 각 용량은 오름차순으로 정렬한다.

    # 예제 입력 1 
    8 9 10

    # 예제 출력 1
    1 2 8 9 10
*/
public class ex3_2251practice2 {
    /*
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
    */

    static StringBuilder sb = new StringBuilder();
    static FastReader scan = new FastReader();
    
    static int[] limit;
    static boolean[] possible;
    static boolean[][][] visit; // 200 * 200 * 200 byte = 8MB

    // 물통의 현재 상태와 물을 붓는 행위를 관리하는 구조체 
    public static class State{
        int[] X;
        public State(int[] _X){
            X = new int[3];
            for(int i=0;i<3;i++) X[i] = _X[i];
        }

        //from 물통에서 to 물통으로 물을 옮긴다.(limit는 처음 입력값)
        public State move(int from, int to, int[] Limit){
            int[] nX = new int[]{X[0],X[1],X[2]};
            /*
                8 9 10 
                8 0 2
                0 8 2 여기서 이렇게 된다면  nX[from] = nX[from] - (limit[to] - X[to])
                0 9 1
            */
            if(X[from] + X[to] >= Limit[to]){ //물이 넘친다면.
                nX[from] -= Limit[to] - X[to]; //  to의 리미트 - to의 현재값 = 남은 값
                nX[to] = Limit[to];
            }else{
                nX[to] += nX[from];
                nX[from] = 0;
            }

            return new State(nX);
        }

    }
    /*
           상태            ->                    변화된 상태 
                 물을 붓는 행위를 간선이라 생각
    */
    public static void bfs(int x1, int x2, int x3){
        Queue<State> q = new LinkedList<>();

        visit[x1][x2][x3] = true;
        q.add(new State(new int[]{x1,x2,x3}));

        while(!q.isEmpty()){
            State st = q.poll();

            if(st.X[0]==0) possible[st.X[2]] = true;//A통이 0일때 C의 값 구하는게 목표

            for(int from = 0 ; from < 3 ; from ++){
                for(int to = 0 ; to < 3 ; to++){
                    if(from == to) continue; // 자기 자신은 할 필요 없음 

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
        for(int i=0 ; i <= limit[2] ; i++){
            if(possible[i]) sb.append(i).append(' ');
        }
    }

    static void input(){
        limit = new int[3];
        for(int i=0; i < 3 ; i++) limit[i] = scan.nextInt();
        visit = new boolean[201][201][201];
        possible = new boolean[201];
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
