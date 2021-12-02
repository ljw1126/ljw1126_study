import java.util.*;
import java.io.*;

//Multisource BFS 활용!
/*
    1. 벽을 세우고 (Combination 조합 , 중복 x , 순서 x )     -- 완전 탐색
    2. 바이러스를 퍼뜨리고                -- bfs 그래프 탐색
    3. 안전한 영역 계산 


    접근 - 정리 
    1. bC3 가지 경우 만큼 직접 벽을 세운다(완전탐색) -> 약 41K번 
    2. 매번 직접 "탐색"을 통해서 바이러스로부터 안전한 구역 확인하기 -> O(T) => O(N^2)
    3. 탐색 방법에 따라 O(41K * T) => O(41K * N^2) = 41K * 64 = 260만 만큼의 시간이 걸릴 것이다.
*/
public class ex4_14502 {
    
    static FastReader scan = new FastReader();
    static StringBuilder sb = new StringBuilder();

    static int N,M,B,ans;
    static int[][] A, blank;
    static boolean[][] visit;
    static int[][] dir = {{-1,0},{0,-1},{1,0},{0,1}};

    //2.바이러스 퍼뜨리기
    static void bfs(){
        Queue<Integer> que = new LinkedList<>();
        // 모든 바이러스가 시작점으로 가능하니깐, 전부 큐에 넣어준다.
        for(int i=1; i<=N ; i++){
            for(int j=1 ; j<=M ; j++){
                visit[i][j] = false; 
                if(A[i][j] == 2){// 바이러스 위치라면
                    que.add(i);
                    que.add(j);
                    visit[i][j] = true;
                }
            }
        }


        //BFS 과정
        while(!que.isEmpty()){
            int x = que.poll(), y = que.poll();
            for(int k=0 ; k < 4 ; k++){
                int nx = x + dir[k][0], ny = y + dir[k][1];
                
                if(nx < 1 || ny < 1 || nx > N || ny > M) continue; // 1,1 부터 시작 
                if(A[nx][ny] != 0) continue;
                if(visit[nx][ny]) continue;

                visit[nx][ny] = true;
                que.add(nx);
                que.add(ny);
            }
        }

        //3. 안전영역 넓이 계산
        //탐색이 종료된 시점이니, 안전 영역의 넓이를 계산하고 정답을 갱신한다
        int cnt = 0;
        for(int i=1;i<=N;i++) for(int j=1;j<=M;j++) if( A[i][j] == 0 && !visit[i][j] ) cnt++;
        ans = Math.max(ans, cnt);
    }

    //idx 번째 빈 칸에 벽을 세울지 말지 결정해야 하고, 
    //이전까지 selected_cnt 개의 벽을 세웠다.
    //blank[][]가 0이 들어있는 빈칸에 해당
    static void dfs(int idx, int selected_cnt){ // 완전 탐색 부분
        if(selected_cnt == 3){ //3개의 벽을 모두 세운 상태
            bfs();
            return;
        }
        if(idx > B) return; // 더이상 세울 수 있는 벽이 없는 상태, B가 0의 좌표 총 갯수

        A[blank[idx][0]][blank[idx][1]] = 1;
        dfs(idx+1 , selected_cnt +1);

        A[blank[idx][0]][blank[idx][1]] = 0;
        dfs(idx+1 , selected_cnt);
        
    }

    static void input(){
        N = scan.nextInt();
        M = scan.nextInt();
        A = new int[N+1][M+1];
        blank = new int[N*M +1][2]; // 0이 표기된 공간 (x,y) 좌표 나타내기 위한 배열, N*M , // (1,1) 부터 시작함
        visit = new boolean[N+1][M+1];

        for(int i=1; i <= N ; i++)
            for(int j=1; j<=M ; j++)
                A[i][j] = scan.nextInt();
    }

    // 0은 빈칸, 1은 벽, 2는 바이러스
    static void pro(){
        // 모든 빈칸 위치를 먼저 모아놓자.
        for(int i=1; i<=N; i++){
            for(int j=1; j<=M; j++){
                if(A[i][j] == 0){ // 빈칸인 경우 .. 이해 안됨 
                    B++;
                    blank[B][0] = i;
                    blank[B][1] = j;
                }
            }
        }
        // 0 의 좌표 [0,0],[0,1] ... 이런게 blank에 들어감
        //for(int[] i : blank) System.out.println(Arrays.toString(i));

        // 벽을 3개 세우는 모든 방법을 확인하자
        dfs(1,0); // 시작점을 1,1로 잡아서 그럼
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
