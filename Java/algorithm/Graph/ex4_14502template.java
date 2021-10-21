
import java.util.*;
import java.io.*;

public class ex4_14502template {
    
    static void bfs(){
        Queue<Integer> que = new LinkedList<>();
        // 모든 바이러스가 시작점으로 가능하니깐, 전부 큐에 넣어준다.

        //BFS 과정

        //탐색이 종료된 시점이니, 안전 영역의 넓이를 계산하고 정답을 갱신한다
    }

    //idx 번째 빈 칸에 벽을 세울지 말지 결정해야 하고, 이전까지 selected_cnt 개의 벽을 세웠다.
    static void dfs(int idx, int selected_cnt){
        if(selected_cnt == 3){ //3개의 벽을 모두 세운 상태

            return;
        }
        if(idx > B) return; // 더이상 세울 수 있는 벽이 없는 상태
    }
}
