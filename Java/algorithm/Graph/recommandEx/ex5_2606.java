package recommandEx;


/*
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
// 직접 품 
public class ex5_2606 {

    static FastReader scan = new FastReader();
    static StringBuilder sb = new StringBuilder();

    static int N,M,group_cnt;
    static boolean[] visit;
    static ArrayList<Integer>[] graph;

    public static void input(){
        N = scan.nextInt();
        M = scan.nextInt();

        visit = new boolean[N+1];
        graph = new ArrayList[N+1];
        for(int i=0; i < N+1 ;i++) graph[i] = new ArrayList<>();

        for(int i=0; i < M ;i++){
            int a = scan.nextInt(), b = scan.nextInt();
            graph[a].add(b);
            graph[b].add(a);
        }
    }
    public static void dfs(int start){
        group_cnt++;
        visit[start] = true;
        for(int y : graph[start]){
            if(visit[y]) continue;
            dfs(y);
        }
    }
    public static void pro(){
        dfs(1);
        System.out.println(group_cnt-1);
    }
    public static void main(String[] args) {
        input();
        pro();
    }

    static class FastReader{
        BufferedReader br;
        StringTokenizer st; 

        public FastReader(){
            br = new BufferedReader(new InputStreamReader(System.in));
        }

        public FastReader(String s) throws FileNotFoundException{
            br = new BufferedReader(new FileReader(new File(s)));
        }

        String next(){
            while(st == null || !st.hasMoreElements()){
                try{
                    st = new StringTokenizer(br.readLine());
                }catch(IOException e){
                    e.printStackTrace();
                }
            }
            return st.nextToken();
        }

        int nextInt(){
            return Integer.parseInt(next());
        }

        long nextLong(){
            return Long.parseLong(next());
        }

        double nextDouble(){
            return Double.parseDouble(next());
        }

        String nextLine(){
            String str = "";
            try{
                str = br.readLine();
            }catch(IOException e){
                e.printStackTrace();
            }
            return str;
        }
    }
    
}