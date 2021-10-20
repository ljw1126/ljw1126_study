import java.util.LinkedList;
import java.util.Queue;

public class ex3_2251templata {
    
    //물통의 현재 상태와 물을 붓는 행위를 관리하는 구조체 
    class State{
        int[] X;
        State(int[] _X){
            X = new int[3];
            for(int i=0; i<3; i++) X[i] = _X[i];
        }

        State move(int from, int to, int[] Limit){
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

    static void bfs(int x1, int x2, int x3){
        Queue<State> q = new LinkedList<>();

        // BFS 과정 시작
    }

    static void pro(){
        //bfs(0,0, Limit[2]);
        // 정답 계산하기 
        /* TODO */
    }
}
