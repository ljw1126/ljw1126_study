
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
public class ex3_2251 {
    

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
        visit = new boolean[201][201][201];
        possible = new boolean[201];
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
            for(int from = 0 ; from <3 ; from++){ // a,b,c 사이즈가 다를 뿐 3개인건 똑같으니
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
