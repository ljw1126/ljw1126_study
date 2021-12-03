import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class ex2_2667practice211203 {
    
    static StringBuilder sb =new StringBuilder();
    static MyReader scan = new MyReader();

    static int N, group_cnt;
    static String[] a;
    static boolean[][] visit;
    static int[][] dir = {{-1,0},{0,-1},{1,0},{0,1}};
    static ArrayList<Integer> group;

    public static void dfs(int x , int y){
        
        group_cnt++;        
        visit[x][y] = true;
        
        for(int i=0; i<4;i++){
            int dx = x + dir[i][0] , dy = y + dir[i][1];
            /*
                1. 방문 여부 확인
                2. 범위 내인지 확인 
                3. 이동 불가능한지 확인 
            */
            if( dx < 0 || dy < 0 || dx >= N|| dy >= N) continue;
            if(visit[dx][dy]) continue;
            if(a[dx].charAt(dy) == '0') continue;

            dfs(dx,dy);
        }

    }

    public static void pro(){
        group = new ArrayList<>();
        for(int i=0; i< N;i++){
            for(int j=0; j<N; j++){
                if(!visit[i][j] && a[i].charAt(j) == '1'){//방문 했는지, 1인지 
                    group_cnt = 0 ;
                    dfs(i,j);
                    group.add(group_cnt);
                }
            }
        }

        Collections.sort(group);
        sb.append(group.size()).append('\n');
        for(int i : group) sb.append(i).append('\n');
        System.out.println(sb);
    }

    public static void input(){
        N = scan.nextInt();
        a = new String[N];
        for(int i=0 ; i<N;i++)
            a[i] = scan.nextLint();

        visit = new boolean[N][N];
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
                    //TODO: handle exception
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

        String nextLint(){
            String str = "";
            try {
                str =br.readLine();
            } catch (Exception e) {
                e.printStackTrace();
            }
            return str;
        }

    }
}
