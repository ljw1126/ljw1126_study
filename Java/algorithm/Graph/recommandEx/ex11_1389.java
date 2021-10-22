package recommandEx;

import java.util.*;
import java.io.*;
// 플로이드 알고리즘 쓰라는데 .. 정답은 구했는데 틀렸다함 
// https://www.acmicpc.net/problem/1389
public class ex11_1389 {

    static FastReader scan = new FastReader();
  
    static int N,M,ans,min;
    static boolean[] visit;
    static ArrayList<Integer>[] arr;
    static int[] dist;

    public static void input(){
        N = scan.nextInt();
        M = scan.nextInt();

        min = Integer.MAX_VALUE;
        arr = new ArrayList[N+1];
        
        for(int i = 1 ; i <= N ; i++) arr[i] = new ArrayList<>(); 

        for(int i = 1 ; i <= N ; i++){
            int a = scan.nextInt();
            int b = scan.nextInt();
            arr[a].add(b);
            arr[b].add(a);
        } 
       
        // for(int i = 1 ; i <= N ; i++){
        //     System.out.println(arr[i]);
        // }
    }

    public static void bfs(int start){
        Queue<Integer> Q = new LinkedList<>();
        Q.add(start);
        
        dist = new int[N+1];
        visit = new boolean[N+1];
        visit[start] = true; 

        while(!Q.isEmpty()){
            int x = Q.poll();

            for(int y : arr[x]){
                if(visit[y]) continue;
                if(x == y) continue; 

                dist[y] = dist[x]+1;
                visit[y] = true;
                Q.add(y);
            }
        }

        int sum = 0 ;
        for(int i=1 ; i <= N ; i++){
             sum += dist[i];   
        } 
        if( min > sum ){
            min = Math.min(min,sum); 
            ans = start;
        }
    }

    public static void pro(){
        for(int i = 1; i <= N ; i++){
            bfs(i);
        }
        System.out.println(ans);
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
