package recommandEx;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/*
    현명한 나이트 - 실버 1
    https://www.acmicpc.net/problem/18404

    - matrix 크기 N, M (1 ≤ N ≤ 500, 1 ≤ M ≤ 1,000) 
    - 최대치 계산 
      - full scan 때리면 500 * 10000 = 50만 
      - 그리고 나이트 이동 경우수가 6개 
      - 그럼 50만 * 6 = 300만 // 1초 연산 가능 
        - 접점이 50만개    O(50만)
        - 간선이 50만개 * 6 = 300만 이라는 뜻     // O(50만 * 6) = O(300만)
        - 결국 BFS든 , DFS든 둘다 O(50만)

    - 결국에 최단 이동거리 계산하는 문제다
      - 기준 나이트 좌표가 주어질때, 목표로 하는 나이트가 잡히는 건 최소 몇번째인지 값 구해서 표출  

    # 입력 예시 1
    5 3     // martrix 크기 
    2 4     // 기준 이동해야 할 나이트 좌표 
    3 2     // 잡아야할 목표 좌표 1
    3 5
    4 5

    # 출력 예시 1 
    1 2 1 

    211204 직접 품 
*/
public class ex10_18404 {
    
    static StringBuilder sb = new StringBuilder();
    static MyReader scan = new MyReader();
    
    static int N,M,start_x,start_y;
    static int[][] matrix, dist, target;
    static boolean[][] visit;
    static int[][] dir = {{-2,-1},{-2,+1},{-1,-2},{-1,2},{1,-2},{1,2},{2,-1},{2,1}};

    static void bfs(int a, int b){
        Queue<Integer> que = new LinkedList<>();
        que.add(a);
        que.add(b);
        visit[a][b] = true;
        dist[a][b] = 0;

        while(!que.isEmpty()){
            int x = que.poll(), y = que.poll();

            for(int i=0; i<8;i++){
                int dx = x + dir[i][0];
                int dy = y + dir[i][1];

                if(dx < 1 || dy < 1 || dx >= N +1 || dy >= N +1) continue;
                if(visit[dx][dy]) continue;

                visit[dx][dy] = true;
                dist[dx][dy] = dist[x][y] + 1;
                que.add(dx);
                que.add(dy);
            }

        }

    }

    /*
        5 3 
        2 4 입력시 dist 구한 결과
        [0, 0, 0, 0, 0, 0]
        [0, 2, 1, 2, 3, 4]
        [0, 3, 2, 3, 0, 3]   // 2행 4열 시작점
        [0, 2, 1, 2, 3, 2]
        [0, 3, 4, 1, 2, 1]
        [0, 2, 3, 2, 3, 2]

    */
    static void pro(){
        bfs(start_x, start_y);
        //for(int[] i : dist) System.out.println(Arrays.toString(i));
        for(int[] i : target) sb.append(dist[i[0]][i[1]]).append(' ');

        System.out.println(sb);
    }

    static void input(){
        N = scan.nextInt(); // matrix 크기 N * N
        M = scan.nextInt(); // 잡을 타켓 갯수 

        matrix = new int[N+1][N+1];
        dist = new int[N+1][N+1];
        visit = new boolean[N+1][N+1];

        start_x = scan.nextInt();
        start_y = scan.nextInt();

        target = new int[M][2]; // M개만큼 행을 만들고 2개씩 넣으면 되니
        // target 좌표는 넘겨둠 
        for(int i=0;i<M;i++){
            target[i][0] = scan.nextInt();
            target[i][1] = scan.nextInt();
        }
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
