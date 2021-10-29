package algorithm.TopologicalSort;

import java.util.*;
import java.io.*;



/*
    ACM Craft - 골드 3 
    https://www.acmicpc.net/problem/1005

    -(이해돕기)같은 레벨에 두 건물이 있을때 두 건물이 다 지어지는 순간 다음 건물 건설 가능하다

    # 예제 입력 1 
    2                     -- 테스트 케이스 T    
    4 4                   -- 첫째 줄에 건물의 개수 N과 건물간의 건설순서 규칙의 총 개수 K
    10 1 100 10           -- 건물 별 짓는데 걸리는 시간
    1 2                   -- 건설 순서 X Y
    1 3
    2 4
    3 4
    4                     -- 승리하기 위해 건설해야 하는 건물번호 W 
    8 8                   -- (테스트2) 건물의 개수 N과 건물간의 건설순서 규칙의 총 개수 K
    10 20 1 5 8 7 1 43
    1 2
    1 3
    2 4
    2 5
    3 6
    5 7
    6 7
    7 8
    7

    # 예제 출력 1
    120
    39


    211029 설명 듣고 직접 풀어서 맞춤
           해설답안 차이점은 초기에 indeg == 0 처리 할때 T_done 값도 초기셋팅 하는걸 생각못함
*/

public class ex2_1005 {
    
    static FastReader scan = new FastReader();
    static StringBuilder sb = new StringBuilder();

    static int N, M;
    static int[] indeg, T_done, T;
    static ArrayList<Integer>[] adj;

    static void input() {
        N = scan.nextInt();
        M = scan.nextInt();
        adj = new ArrayList[N + 1];
        indeg = new int[N + 1];
        T = new int[N + 1];
        T_done = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            adj[i] = new ArrayList<>();
            T[i] = scan.nextInt();
        }
        for (int i = 0; i < M; i++) {
            int x = scan.nextInt(), y = scan.nextInt();
            adj[x].add(y);
            // indegree 계산하기
            indeg[y]++;
        }
    }

    static void pro() {
        Deque<Integer> queue = new LinkedList<>();
        // 제일 앞에 "정렬될 수 있는" 정점 찾기
        for (int i = 1; i <= N; i++)
            if (indeg[i] == 0) {
                queue.add(i);
                T_done[i] = T[i];
            }

        // 위상 정렬 순서대로 T_done 계산을 함께 해주기
        while (!queue.isEmpty()) {
            int x = queue.poll();
            for (int y : adj[x]) {
                indeg[y]--;
                if (indeg[y] == 0) queue.add(y);
                T_done[y] = Math.max(T_done[y], T_done[x] + T[y]);
            }
        }
        int W = scan.nextInt();
        System.out.println(T_done[W]);
    }

    public static void main(String[] args) {
        int Q = scan.nextInt();
        while (Q > 0) {
            Q--;
            input();
            pro();
        }
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
