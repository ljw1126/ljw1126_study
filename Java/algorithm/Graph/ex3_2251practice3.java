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

    200 * 200 * 200 = 800만가지 
    A -> B
    A -> C
    B -> A
    B -> C
    C -> A
    C -> B 
    넣는 가지 수가 존재함 

    정점은 200^3 
    간선의 개수는 200^3 * 6가지 

    상태 --------------> 변한상태 
         물을 붓는 행위(=간선) 
*/

import java.util.*;
import java.io.*;

public class ex3_2251practice3 {
    
    static MyReader scan = new MyReader();
    static StringBuilder sb = new StringBuilder();

    static int[] limit;
    static boolean[][][] visit;
    static boolean[] possible;

    static class State{
        int[] X;
        public State(int[] _X){
            X = new int[3];
            for(int i= 0 ; i < 3; i++) X[i] = _X[i];
        }

        public State move(int from, int to, int[] Limit){
            int[] nX = new int[]{X[0],X[1],X[2]};

            if(nX[from] + nX[to] >= Limit[to]){//용량 초과하는 경우 
                nX[from] -= Limit[to] - nX[to];
                nX[to] = Limit[to];
            }else{
                nX[to] += nX[from];
                nX[from] = 0;
            }

            return new State(nX);
        }
    }


    static void bfs(int x1, int x2, int x3){
        Queue<State> que = new LinkedList<>();
        que.add(new State(new int[]{x1,x2,x3}));
        visit[x1][x2][x3] = true;

        while(!que.isEmpty()){
            State x = que.poll();

            if(x.X[0] == 0) possible[x.X[2]] = true;

            // 경우의 수 6가지 (from == to 는 빼니)
            for(int from=0 ; from < 3 ; from++){
                for(int to=0 ; to < 3 ; to++){
                    if(from == to) continue;

                    State nx = x.move(from, to, limit);
                    if(!visit[nx.X[0]][nx.X[1]][nx.X[2]]){
                        que.add(nx);
                        visit[nx.X[0]][nx.X[1]][nx.X[2]] = true;
                    }
                }
            }

        }

    }

    static void pro(){
        bfs(0,0,limit[2]);
        for(int i=0 ; i <= limit[2]; i++){ // 정렬할 필요도 없네 
            if(possible[i]) sb.append(i).append(' ');
        }
        System.out.println(sb);
    }

    static void input(){
        limit = new int[3];
        for(int i=0;i<3;i++) limit[i] = scan.nextInt();
        visit = new boolean[201][201][201];
        possible = new boolean[201];
    }

    public static void main(String[] args) {
        input();
        pro();        
    }

    static class MyReader{
        BufferedReader br;
        StringTokenizer st;

        public MyReader(){
            br = new BufferedReader(new InputStreamReader(System.in));
        }

        String next(){
            while(st == null || !st.hasMoreElements()){
                try {
                    st = new StringTokenizer(br.readLine());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            return st.nextToken();
        }

        int nextInt(){
            return Integer.parseInt(next());
        }

        Long nextLong(){
            return Long.parseLong(next());
        }

        String nextLine(){
            String str = "";
            try {
                str = br.readLine();
            } catch (Exception e) {
                e.printStackTrace();
            }
            return str;
        }

    }


}
