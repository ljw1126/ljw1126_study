package exam;

import java.util.*;

public class TN_exam2 {
    
    static int[] mark = {1,2,3,1};
    static int[] dist;
    static boolean[] visit;
    static int num = 2000000000;
    static int cnt;
    
    //static int[] mark = {1,2,3,4,5,6,7};
    //static int[] mark = {1,7,0,0,0,0,0,8,9,1};
    static int loop = 0;
    static boolean[] findLoop;

    static void dfs(int start, int par, int _cnt){
        
        // 지정된 번호와 마지막 번호를 부른 사람이 같을때(par) , 마지막 번호 부른 사람(mark[par])이 지정하는 사람 출력
        if(num == _cnt) { 
            System.out.println(mark[par]); 
            return;
        } 

        if(!visit[start]){
            //dist[start] = dist[par] + 1;
            visit[start] = true; 
            dfs(mark[start], start, ++_cnt);
        }else{ // 루프를 찾을 경우 
            System.out.println("============================");
            System.out.println("수정한 로직으로 구할 경우");
            findLoop = new boolean[mark.length];
            cycle(mark[start]); 
            //result(start,(num-dist[par])%loop);
            result(start,(num-_cnt)%loop);
        }
    }

    // 루프 시작점에서 cnt 번 호출 되었을때가 정답
    static void result(int start, int _cnt){
        if(_cnt == 0){
            System.out.println(start);
            return;
        } 
        _cnt--;
        result(mark[start], _cnt);
    }

    // 루프 노드의 갯수를 카운트함(loop)
    static void cycle(int vertex){
        if(findLoop[vertex]) return;
        findLoop[vertex] = true;
        loop++;
        cycle(mark[vertex]);
    }

    public static void main(String[] args) {
        //System.out.println("확인용");
        System.out.println(1999999996%3);
        //초기화 
        visit = new boolean[mark.length];
        //dist = new int[mark.length];

        //dist[0] = 1;
        cnt = 1;
        visit[0] = true;
        long start_time = System.currentTimeMillis();
            dfs(mark[0], 0, cnt);
        long end_time = System.currentTimeMillis();
        System.out.println("실행시간(ms):" + (end_time - start_time));
        //System.out.println(Arrays.toString(dist));
        System.out.println("============================");
        System.out.println("단순 반복문으로 구할 경우:");
        start_time = System.currentTimeMillis();
            System.out.println(bfs(0,0,num, mark));
        end_time = System.currentTimeMillis();
        System.out.println("실행시간(ms):" + (end_time - start_time));
        
    }


    static int bfs(int start, int cnt, int _limit , int[] peoples){

        int ans = -1;    
        Queue<Integer> que = new LinkedList();
        que.add(peoples[start]);
        cnt++;

        while(!que.isEmpty()){
            int x = que.poll();
            
            if(cnt == _limit ) {
                ans = x;
                break;
            }

            que.add(peoples[x]);
            cnt++;            
        }

        return ans;
    }

    

}
