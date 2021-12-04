package recommandEx;

/*
    촌수 계산 - 실버 2 
    https://www.acmicpc.net/problem/2644

    # 예제 입력 1
    9       // 전체 사람의 수 n
    7 3     // 촌수 계산해야 하는 서로 다른 두 사람의 번호 
    7       // 부모 자식 관계 수 m
    1 2
    1 3
    2 7
    2 8
    2 9
    4 5
    4 6

    # 예제 출력 1
    3

    # 예제 입력 1
    9
    8 6
    7
    1 2
    1 3
    2 7
    2 8
    2 9
    4 5
    4 6

    # 예제 출력 1 
    -1

    (211204 직접품)부모 자식 노드 관계가 1이기 때문에 시작점 노드의 dist는 0인게 맞음 

*/

import java.util.*;
import java.io.*;

public class ex9_2644 {
    
    static StringBuilder sb = new StringBuilder();
    static MyReader scan = new MyReader();

    static int n, start, end, m;
    static int[] dist;
    static boolean[] visit;
    static ArrayList<Integer>[] adj;


    static void input(){
        n = scan.nextInt(); // 전체 사람의 수 
        start = scan.nextInt(); // 촌수 계산해야 하는 a
        end = scan.nextInt(); //  촌수 계산해야 하는 b
        m = scan.nextInt();   // 입력받을 부모 자식 관계 수 
    
        adj = new ArrayList[n+1];
        for(int i=1; i <=n ; i++)
            adj[i] = new ArrayList<>();

        for(int i=0; i < m ; i++){
            int x = scan.nextInt(), y = scan.nextInt();
            adj[x].add(y);
            adj[y].add(x);
        }

        dist = new int[n+1];
        visit = new boolean[n+1];
    }
    /*
        부모 자식 관계가 1촌이기 때문에 dist 시작점에 0인게 맞음
    */
    static void pro(int s){
        Queue<Integer> que = new LinkedList<>();
        visit[s] = true;
        //dist[s] = 1;
        que.add(s);

        while(!que.isEmpty()){
            int x = que.poll();

            for(int y : adj[x]){
                if(visit[y]) continue;

                dist[y] = dist[x] +1;
                visit[y] = true;
                que.add(y);
            }
        }
    }

    public static void main(String[] args) {
        input();
        pro(start);
        if(dist[end] == 0) System.out.println(-1);
        else System.out.println(dist[end]);
        //System.out.println("=======");
        //for(int i : dist) System.out.println(i);
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
