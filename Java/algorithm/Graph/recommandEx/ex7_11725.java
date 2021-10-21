package recommandEx;


import java.util.*;
import java.io.*;
// 힌트를 찾아봄 , 손으로 그려보고 bfs로 해결하는 방법 찾아봄 
/*

    #입력예시 1
    7
    1 6
    6 3
    3 5
    4 1
    2 4
    4 7

    #출력예시   // 1번 생략 
    4
    6
    1
    3
    1
    4

    1 - 6,4
    2 - 4
    3 - 5,6
    4 - 1,2,7
    5 - 3
    6 - 1,3
    7 - 4

*/
// 힌트 찾아보고 직접 풀어봄 
public class ex7_11725 {
    
    static FastReader scan = new FastReader();
    static StringBuilder sb = new StringBuilder();

    static int[] parent;
    static boolean[] visit;
    static ArrayList<Integer>[] tree;
    static int N;

    public static void input(){
        N = scan.nextInt();

        parent = new int[N+1];
        visit = new boolean[N+1];
        tree = new ArrayList[N+1];

        for(int i = 1; i <=N ; i++) tree[i] = new ArrayList<Integer>();
       
        for(int i=0 ; i < N-1 ; i++){
            int a = scan.nextInt(), b = scan.nextInt();
            tree[a].add(b);
            tree[b].add(a);
        }
        
        for(ArrayList<Integer> arr : tree) System.out.println(arr);    
        System.out.println(Arrays.toString(parent));
        
    }

    public static void pro(){
        bfs(1);
        for(int i=2; i <= N ; i++) System.out.println(parent[i]);
    }
    
    public static void bfs(int start){
        Queue<Integer> que = new LinkedList<>();
        visit[start] = true;
        parent[start] = start;
        que.add(start);

        while(!que.isEmpty()){
            int x = que.poll();
            for(int y : tree[x]){
                if(visit[y]) continue;
                if(parent[y] != 0) continue;

                parent[y] = x;
                visit[y] = true;
                
                que.add(y);
            }
        }
    }

    public static void dfs(int start){

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
