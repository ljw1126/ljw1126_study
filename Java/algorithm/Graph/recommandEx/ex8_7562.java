package recommandEx;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/*
    나이트 이동 - 실버 2
    https://www.acmicpc.net/problem/7562

    # 예제 입력 1 
    3      // 테스트 케이스 T 
    8      // I * I 
    0 0    // 시작점
    7 0    // 도착위치
    100    // I*I
    0 0    // 시작점
    30 50  // 도착위치
    10
    1 1
    1 1

    211204 직접품 
    = 상태가 있고 ------------> 변한 상태 
                이동하는 경우수 (나이트는 6가지 방향으로 이동가능 = 간선이 6개이다)
    = 현재 상태에서 나이트가 특정 위치(간선)으로 이동했을때 조건 만족(방문여부, 범위)할 경우, 그 위치를 또 탐색한다 (BFS, 거리측정)             
                
    .. 매번 테스트 마다 배열을 신규 생성하게 되었는데 .. 그경우 150328KB 소모됨 
    >> I * I 사이즈가 다 달라서 .. false 값만 바꿔주는 행위를 못하네 ..     
*/
public class ex8_7562 {
    
    static StringBuilder sb = new StringBuilder();
    static MyReader scan = new MyReader();

    static int T, I, start_x, start_y, end_x,end_y; // T : 테스트 케이스 , I : I * I 메트릭스 
    static int[][] matric,dist;
    static boolean[][] visit;
    static int[][] dir = {{-2,-1},{-1,-2},{1,-2},{2,-1},{2,1},{1,2},{-1,2},{-2,1}}; // 좌표가 틀렸었음 

    
    static void input(){
        I = scan.nextInt();
        matric = new int[I][I];
        visit = new boolean[I][I];
        dist = new int[I][I];
        //시작점
        start_x = scan.nextInt();
        start_y = scan.nextInt();
        //도착점
        end_x = scan.nextInt();
        end_y = scan.nextInt();
    }

    static void pro(){
        Queue<Integer> chess = new LinkedList<>();
        chess.add(start_x);
        chess.add(start_y);
        visit[start_x][start_y] = true;

        while(!chess.isEmpty()){

            int x = chess.poll();
            int y = chess.poll();

            for(int k=0 ; k < 8 ; k++){
                int dx = x + dir[k][0] , dy = y + dir[k][1];

                if(dx < 0 || dy < 0 || dx >= I || dy >= I) continue;
                if(visit[dx][dy]) continue;

                visit[dx][dy] = true;
                dist[dx][dy] = dist[x][y] + 1;
                chess.add(dx);
                chess.add(dy);
            }
        }
        sb.append(dist[end_x][end_y]).append('\n');
    }

    public static void main(String[] args) {
        T = scan.nextInt();
        for(int t = 0 ; t < T ; t++){
            input();
            pro();
        }
        
        System.out.println(sb);
    }

    static class MyReader{
        BufferedReader br ; 
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
