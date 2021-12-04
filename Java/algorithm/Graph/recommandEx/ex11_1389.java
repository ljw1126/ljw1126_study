package recommandEx;

import java.util.*;
import java.io.*;
// 플로이드 알고리즘 쓰라는데 .. 정답은 구했는데 틀렸다함 
// https://www.acmicpc.net/problem/1389
/*

    211204 솔루션 참고 
    - 답은 구했는데 for문 안에 for가 여러개 있어서 문제였던거 같은..
    - 어차피 순차적으로 돌면서 케빈 값을 구하니깐 해당 값을 리턴 받아서 갱신시켜주면됨(동일한 케빈값이라도 인덱스 순으로 했기때문에 갱신x)

*/
public class ex11_1389 {

    static FastReader scan = new FastReader();
  
    static int N,M;
    static ArrayList<Integer>[] arr;
    static int[] dist;

    public static void input(){
        N = scan.nextInt();
        M = scan.nextInt();

        arr = new ArrayList[N+1];
        dist = new int[N+1];
        
        for(int i = 1 ; i <= N ; i++) arr[i] = new ArrayList<>(); 

        for(int i = 1 ; i <= M ; i++){ // 친구 관계수가 M인데 N으로 했었음 
            int a = scan.nextInt();
            int b = scan.nextInt();
            arr[a].add(b);
            arr[b].add(a);
        } 
    }

    public static int bfs(int start){
        // 초기화
        Queue<Integer> Q = new LinkedList<>();
        Q.add(start);
        
        for(int i=1; i <= N; i++){
            dist[i] = -1;
        }
        
        dist[start] = 0;
        int ret = 0;

        // 2. BFS 탐색 하면서 거리 계산
        while(!Q.isEmpty()){
            int x = Q.poll();
            ret += dist[x];

            for(int y : arr[x]){
                if(dist[y] != -1) continue; // 이미 방문했다는 거니

                dist[y] = dist[x]+1;
                Q.add(y);
            }
        }

        return ret;
    }

    public static void pro(){
        int minV = bfs(1), minIdx = 1;
        for(int i = 2; i <= N ; i++){
            int v = bfs(i);
            if(minV > v){
                minV = v ;
                minIdx = i;
            }
        }
        System.out.println(minIdx);
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
