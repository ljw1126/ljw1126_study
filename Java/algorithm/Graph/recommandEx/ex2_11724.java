package recommandEx;

import java.io.*;
import java.util.*;
/*
   https://www.acmicpc.net/problem/11724

    #문제
    방향 없는 그래프가 주어졌을 때, 연결 요소 (Connected Component)의 개수를 구하는 프로그램을 작성하시오.

    #입력
    첫째 줄에 정점의 개수 N과 간선의 개수 M이 주어진다. (1 ≤ N ≤ 1,000, 0 ≤ M ≤ N×(N-1)/2) 둘째 줄부터 M개의 줄에 간선의 양 끝점 u와 v가 주어진다. (1 ≤ u, v ≤ N, u ≠ v) 같은 간선은 한 번만 주어진다.

    #출력
    첫째 줄에 연결 요소의 개수를 출력한다.

    #입력예시1 
    6 5
    1 2
    2 5
    5 1
    3 4
    4 6

    #출력예시1
    2

    #입력예시2
    6 8
    1 2
    2 5
    5 1
    3 4
    4 6
    5 4
    2 4
    2 3    

    #출력예시2
    1


    문제풀이 참고 >> https://wonit.tistory.com/416
*/
public class ex2_11724 {
    static FastReader scan = new FastReader();
    static StringBuilder sb = new StringBuilder();

    static int N, M;
    static ArrayList<Integer>[] adj;
    static boolean[] visit;

    static void input() {
        N = scan.nextInt();
        M = scan.nextInt();
        adj = new ArrayList[N + 1];
        for (int i = 1; i <= N; i++)
            adj[i] = new ArrayList<>();
        for (int i = 0; i < M; i++) {
            int x = scan.nextInt(), y = scan.nextInt();
            adj[x].add(y);
            adj[y].add(x);
        }
    }

    // x 를 갈 수 있다는 걸 알고 방문한 상태
    static void dfs(int x) {
        // x 를 방문했다.
        visit[x] = true;

        // x 에서 갈 수 있는 곳들을 작은 번호부터 모두 방문한다.
        for (int y : adj[x]) {

            // y 를 이미 갈 수 있다는 사실을 안다면, 굳이 갈 필요 없다.
            if(visit[y]) continue;
            // y에서 갈 수 있는 곳들도 확인 해보자
            dfs(y);
        }
    }

    static void pro() {
        visit = new boolean[N + 1];
        int ans = 0;
        for(int i=1; i <= N ; i++){
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
