package recommandEx;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class ex2_11724practice {
    
    static StringBuilder sb = new StringBuilder();
    static MyReader scan = new MyReader();

    static int N,M,ans;
    static ArrayList<Integer>[] adj;
    static boolean[] visit;

    static void input(){
        N = scan.nextInt();
        M = scan.nextInt();
        adj = new ArrayList[N+1];

        for(int i=1 ; i<=N;i++)
            adj[i] = new ArrayList();

        for(int i=0;i<M;i++){
            int x = scan.nextInt(), y = scan.nextInt();
            adj[x].add(y);
            adj[y].add(x);
        }
        
    }

    static void dfs(int start){
        visit[start] = true;

        for(int i: adj[start]){
            if(visit[i]) continue;
            dfs(i);
        }
    }


    static void pro(){
        
        visit = new boolean[N+1];
        
        for(int i=1; i<=N;i++){
            if(visit[i]) continue;
            dfs(i);
            ans++;
        }
        
        System.out.println(ans);
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
