﻿
import java.util.*;

import javax.sound.sampled.SourceDataLine;

import java.io.*;

// 격자형 그래프는 DFS 활용 ! 
public class ex2_2667practice {
    
    static FastReader scan = new FastReader();
    static StringBuilder sb = new StringBuilder();

    static int N, group_cnt;
    static String[] a;
    static boolean[][] visit;
    static int[][] dir = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
    static ArrayList<Integer> group;

    static void input() {
        N = scan.nextInt();
        a = new String[N];
        for (int i = 0; i < N; i++)
            a[i] = scan.nextLine();
        visit = new boolean[N][N];
    }

    // x, y 를 갈 수 있다는 걸 알고 방문한 상태
    static void dfs(int x, int y) {
        // 단지에 속한 집의 개수 증가, visit 체크 하기
        group_cnt++;
        visit[x][y] = true;

        // 인접한 집으로 새로운 방문하기
        for(int i=0 ; i < 4 ; i++){
            int dx = x + dir[i][0];
            int dy = y + dir[i][1];
            // 1. 이동할 방향이 범위 초과인경우
            // 2. 이미 방문한적 있는 경우
            // 3. 0인 경우 
            if(dx < 0 || dy < 0 || dx >= N || dy >= N) continue;
            if(a[dx].charAt(dy) == '0') continue;
            if(visit[dx][dy]) continue;

            dfs(dx, dy);
        }
    }

    static void pro() {
        group = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (!visit[i][j] && a[i].charAt(j) == '1') {
                    // 갈 수 있는 칸인데, 이미 방문처리 된, 즉 새롭게 만난 단지인 경우!
                    group_cnt = 0;
                    dfs(i,j);
                    group.add(group_cnt);
                }
            }
        }

        // 찾은 단지의 정보를 출력하기
        sb.append(group.size()).append('\n');
        Collections.sort(group);
        for(int i : group)
            sb.append(i).append('\n');

        System.out.println(sb);
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
