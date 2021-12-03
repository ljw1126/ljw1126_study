package recommandEx;
/*
    플로이드 와샬 알고리즘 
    https://steady-coding.tistory.com/94
    https://m.blog.naver.com/PostView.nhn?blogId=ndb796&logNo=221234427842&proxyReferer=https:%2F%2Fwww.google.com%2F

    # 입력예시
    3
    0 1 0
    0 0 1
    1 0 0

    # 출력예씨 
    1 1 1
    1 1 1
    1 1 1


    K = 0 일때 
        i = 0
            j = 0 >> a[0][0] == 1 && a[0][0] == 1  
            j = 1 >> a[0][0] == 1 && a[0][1] == 1  
            j = 2 >> false
        i = 1  
            j = 0 >> a[1][0] == 1 && a[0][0] == 1   
            j = 1 >> a[1][0] == 1 && a[0][1] == 1  
            j = 2 >> a[1][0] == 1 && a[0][2] == 1  
        i = 2 
            j = 0 >> a[2][0] == 1 && a[0][0] == 1   
            j = 1 >> a[2][0] == 1 && a[0][1] == 1  
            j = 2 >> a[2][0] == 1 && a[0][2] == 1  
    K = 1 일때 
        i = 0
            j = 0 >> a[0][1] == 1 && a[1][0] == 1  
            j = 1 >> a[0][1] == 1 && a[1][1] == 1  
            j = 2 >> a[0][1] == 1 && a[1][2] == 1  
        i = 1  
            j = 0 >> a[1][1] == 1 && a[1][0] == 1   
            j = 1 >> a[1][1] == 1 && a[1][1] == 1  
            j = 2 >> a[1][1] == 1 && a[1][2] == 1  
        i = 2 
            j = 0 >> a[2][1] == 1 && a[1][0] == 1   
            j = 1 >> a[2][1] == 1 && a[1][1] == 1  
            j = 2 >> a[2][1] == 1 && a[1][2] == 1   
    K = 2 일때 
        i = 0
            j = 0 >> a[0][2] == 1 && a[2][0] == 1  
            j = 1 >> a[0][2] == 1 && a[2][1] == 1  
            j = 2 >> a[0][2] == 1 && a[2][2] == 1  
        i = 1  
            j = 0 >> a[1][2] == 1 && a[2][0] == 1   
            j = 1 >> a[1][2] == 1 && a[2][1] == 1  
            j = 2 >> a[1][2] == 1 && a[2][2] == 1  
        i = 2 
            j = 0 >> a[2][2] == 1 && a[2][0] == 1   
            j = 1 >> a[2][2] == 1 && a[2][1] == 1  
            j = 2 >> a[2][2] == 1 && a[2][2] == 1   
    ========================================================= 풀이 안되서 포기 한듯.. 
    
    경로찾기 - 실버 1 
    https://www.acmicpc.net/problem/11403

    211203 솔루션 확인함


    # 입력 예시 1
    3
    0 1 0
    0 0 1
    1 0 0

    # 출력 예시 1
    1 1 1
    1 1 1
    1 1 1
    
    1행 1열 부터 시작한다고 했을때 
    1 -> 2 
    2 -> 3
    3 -> 1  간선이 존재하는 걸 이해함 

    정점 i에서 정점 j로 가는 경로가 있으면(=1)      // 행에 대해 bfs를 실행해서 연결된 노드를 확인, 그래서 i , j 가 1이면 해당 행의 j번째를 visit [true]
     i번째 줄에  j 번 숫자를 1로 아니면 0으로 표기하시오 // boolean[] visit 배열을 true가 되어 있으면 한줄씩 출력 
    
    bfs(1)에서
        int x = 1 일때 j (1~3) 반복문 돌면 
            adj[1][2] == 1 이기 때문에 visit[2] = true; 그리고 que.add(2)
        int x = 2 일때 j (1~3) 반복문 돌면
            adj[2][3] == 1 이기 때문에 visit[3] = true; 그리고 que.add(3)
        int x = 3 일때 j (1~3) 반복문 돌면 
            adj[3][1] == 1 이기 때문에 visit[1] = true; 그리고 que.add(1) 종료     // 1 1 1
    bfs(2)에서 
        int x = 2 일때 반복문 돌면서 
            adj[2][3] == 1 이기 때문에 visit[3] = true, 그리고  que.add(3);
        int x = 3 일때 
            adj[3][1] == 1 이기 때문에 visit[1] = true, 그리고 que.add(1);
        int x = 1 일때 
            adj[1][2] == 1 이기 때문에 visit[2] = true, 그리고 que.add(2); 종료    // 1 1 1 
    bfs(3)에서 
        int x = 3 일때 
            adj[3][1] == 1 이기 때문에 visit[1] = true, 그리고 que.add(1); 
        int x = 1 일때 
            adj[1][2] == 1 이기 때문에 visit[2] = true, 그리고 que.add(2);
        int x = 2 일때 반복문 돌면서 
            adj[2][3] == 1 이기 때문에 visit[3] = true, 그리고  que.add(3); 종료  // 1 1 1

    정점 i에서 정점 j로 갈 수 있는가에 대해 이렇게 표현했구나 .. 
    
    ---------------------------------------------

    # 입력 예시 2
    7
    0 0 0 1 0 0 0
    0 0 0 0 0 0 1
    0 0 0 0 0 0 0
    0 0 0 0 1 1 0
    1 0 0 0 0 0 0
    0 0 0 0 0 0 1
    0 0 1 0 0 0 0

    # 출력 예시 2 
    1 0 1 1 1 1 1
    0 0 1 0 0 0 1
    0 0 0 0 0 0 0
    1 0 1 1 1 1 1
    1 0 1 1 1 1 1
    0 0 1 0 0 0 1
    0 0 1 0 0 0 0


    입력예시에서 
    1 -> 4
    2 -> 7 
    4 -> 5 
    4 -> 6 
    5 -> 1
    6 -> 7
    7 -> 3

    1번째 행을 검사했을때 1번노드부터 4,5,6,1,7,3 갈 수 있음 
    2번째 행을 검사했을때 2번노드부터 7,3만 갈수 있음 
    3번째 행을 검사했을때 3번노드부터 갈수 없음 
    4번째 행을 검사했을때 4번노드부터 5,6,1,7,3,4 갈수 있음 
    5번째 행을 검사했을때 5번노드부터 1,4,5,6,7,3 갈 수 있음 
    6번째 행을 검사했을때 6번노드부터 7,3, 갈 수 있음 
    7번째 행을 검사했을때 7번노드부터 3 갈 수 있음 

*/
import java.util.*;
import java.io.*;

public class ex6_11403 {
    
    static StringBuilder sb = new StringBuilder();
    static FastReader scan = new FastReader();

    static int N;
    static int[][] adj;
    static boolean[] visit;

    static void bfs(int start){
        Queue<Integer> que = new LinkedList();
        //visit[start] = false; // 안해도 될거 같은데..  예외) i -> j로 가는게 있을때 true 가 됨 
        que.add(start);
        for(int i=1;i<=N;i++)
            visit[i] = false;

        while(!que.isEmpty()){
            int x = que.poll();

            for(int y=1; y <=N; y++){
                if(adj[x][y] == 0) continue;
                if(visit[y]) continue;

                visit[y] = true;
                que.add(y);
            }
        }

        for(int i=1 ; i <= N ; i++) sb.append(visit[i] ? 1: 0).append(' ');
        sb.append('\n');
    }


    static void pro(){
        for(int i=1 ; i <= N ;i++)
            bfs(i);
        
        System.out.println(sb);
    }

    static void input(){
        N = scan.nextInt();
        adj = new int[N+1][N+1];
        for(int i=1;i<=N;i++){
            for(int j=1;j<=N;j++)
                adj[i][j] = scan.nextInt();
        }
        visit = new boolean[N+1];
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
