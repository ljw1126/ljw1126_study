package recommandEx;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/*
    바이러스 - 실버3 // 211203
    https://www.acmicpc.net/problem/2606

    1번에서 시작해서 감염되는 컴퓨터 수 

    입력)
    첫째 줄에는 컴퓨터의 수가 주어진다. 
    컴퓨터의 수는 100 이하이고 각 컴퓨터에는 1번 부터 차례대로 번호가 매겨진다. 둘째 줄에는 네트워크 상에서 직접 연결되어 있는 컴퓨터 쌍의 수가 주어진다. 이어서 그 수만큼 한 줄에 한 쌍씩 네트워크 상에서 직접 연결되어 있는 컴퓨터의 번호 쌍이 주어진다.

    출력)
    1번 컴퓨터가 웜 바이러스에 걸렸을 때, 
    1번 컴퓨터를 통해 웜 바이러스에 걸리게 되는 컴퓨터의 수를 첫째 줄에 출력한다.

    # 입력예시 
    7      -- 컴퓨터 수 
    6      -- 연결된 쌍수 
    1 2
    2 3
    1 5
    5 2
    5 6
    4 7
    
    # 출력예시 
    4      - 1번 제외한 감염된 컴퓨터 수 

    ※ 인접 리스트로 나타낼 경우 
    1 - 2,5
    2 - 1,3,5
    3 - 2
    4 - 7
    5 - 1,2,6
    6 - 5
    7 - 4 


*/

import java.util.*;
import java.io.*;

public class ex5_2606practice {
    
    static StringBuilder sb = new StringBuilder();
    static MyReader scan = new MyReader();

    static int C, N;
    static boolean[] visit;
    static ArrayList<Integer>[] adj;

    static void pro(){
        Queue<Integer> que = new LinkedList<>();

        que.add(1);
        visit[1] = true;

        while(!que.isEmpty()){
            int x = que.poll();

            for(int y : adj[x] ){
                if(visit[y]) continue;

                visit[y] = true;
                que.add(y);
            }
        }

        int result = 0 ;
        for(int k = 1; k <= C ; k++) if(visit[k] == true) result++;
        System.out.println(result-1);

    }

    static void input(){
        C = scan.nextInt();
        N = scan.nextInt();

        adj = new ArrayList[C+1];
        visit = new boolean[C+1];
        for(int i=1 ; i <=C ; i++)
            adj[i] = new ArrayList();

        for(int i=0 ; i < N ; i++){
            int a = scan.nextInt();
            int b = scan.nextInt();

            adj[a].add(b);
            adj[b].add(a);
        }
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
