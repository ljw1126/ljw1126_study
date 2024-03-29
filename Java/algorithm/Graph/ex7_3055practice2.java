﻿import java.util.*;
import java.io.*;
/*
    R행, C열 
    '.' 비어있는 곳 
    '*' 물이 차있는 지역 
    'X' 돌 
    'D' 비버의 굴 
    'S' 고슴도치의 위치

    티떱숲의 지도가 주어졌을 때, 고슴도치가 안전하게 비버의 굴로 이동하기 위해 
    필요한 최소 시간을 구하는 프로그램을 작성하시오.

    고슴도치는 물이 찰 예정인 칸으로 이동할 수 없다. 
    즉, 다음 시간에 물이 찰 예정인 칸으로 고슴도치는 이동할 수 없다. 
    이동할 수 있으면 고슴도치가 물에 빠지기 때문이다. 
    
    https://www.acmicpc.net/problem/3055
*/
public class ex7_3055practice2 {
    
    static FastReader scan = new FastReader();
    static StringBuilder sb = new StringBuilder();

    static boolean[][] visit;
    static int R,C;
    static int[][] dist_water, dist_hedgehog;
    static String[] fields;
    static int[][] dir = {{-1,0},{0,-1},{1,0},{0,1}};

    public static void input(){
        R = scan.nextInt();
        C = scan.nextInt();
        
        visit = new boolean[R][C];
        fields = new String[R];
        for(int i = 0 ; i < R ; i++) fields[i] = scan.nextLine();
        
        dist_water = new int[R][C];
        dist_hedgehog = new int[R][C];

    }

    public static void dist_water(){
        Queue<Integer> que = new LinkedList<>();
        //물이 찰 곳을 찾음 
        for(int i=0;i<R;i++){
            for(int j=0;j<C;j++){
                //visit 초기화 생략 
                visit[i][j] = false;
                dist_water[i][j] = -1;

                if(fields[i].charAt(j) == '*'){
                    visit[i][j] = true;
                    dist_water[i][j] = 0;
                    que.add(i);
                    que.add(j);
                }
            }
        }

        while(!que.isEmpty()){
            int x = que.poll(), y = que.poll();

            for(int i=0;i<4;i++){
                int dx = x + dir[i][0] , dy = y + dir[i][1];

                if(dx < 0 || dy < 0 || dx >= R || dy >= C) continue;
                if(visit[dx][dy]) continue;
                if(fields[dx].charAt(dy) != '.' ) continue;

                visit[dx][dy] = true;
                dist_water[dx][dy] = dist_water[x][y] + 1; 
                que.add(dx);
                que.add(dy);

            }
        }


    }

    public static void dist_hedgehog(){
        // 초기화 
        Queue<Integer> que = new LinkedList<>();
        
        // 초기화 
        for(int i=0;i<R;i++){
            for(int j=0;j<C;j++){
                //visit 초기화 생략 
                visit[i][j] = false;
                dist_hedgehog[i][j] = -1;

                if(fields[i].charAt(j) == 'S'){
                    visit[i][j] = true;
                    dist_hedgehog[i][j] = 0;
                    que.add(i);
                    que.add(j);
                }
            }
        }
        // BFS로 최단 시간 구하기 
        // 중요 ) 고슴도치는 물이 찰 예정인 칸으로 이동할 수 없다. 즉, 다음 시간에 물이 찰 예정인 칸으로 고슴도치는 이동할 수 없다.
        while(!que.isEmpty()){
            int x = que.poll(), y = que.poll();

            for(int k=0;k<4;k++){
                int dx = x + dir[k][0];
                int dy = y + dir[k][1];

                if(dx < 0 || dy < 0 || dx >= R || dy >= C) continue;
                if(visit[dx][dy]) continue;
                if(fields[dx].charAt(dy) != '.' && fields[dx].charAt(dy) != 'D') continue;
                if(dist_water[dx][dy] != -1 && dist_water[dx][dy] <= dist_hedgehog[x][y] + 1) continue;
                
                visit[dx][dy] = true;
                que.add(dx);
                que.add(dy);
                dist_hedgehog[dx][dy] = dist_hedgehog[x][y] + 1;

            }
        }        
    }

    public static void pro(){
        // 물이 차오를 시간을 미리 구함 (Multisource BFS로)
        dist_water();
        //for(int[] i : dist_water) System.out.println(Arrays.toString(i));
        // 고슴도치가 물이 차오르기 전  이동가능한 최단 시간 구함 ( Multisource BFS로 , dist_water와 고려해야함)
        dist_hedgehog();
        // 고슴도치 이동경로 구한 결과값에 대해 결과 출력
        
        for(int i=0; i<R;i++){
            for(int j=0; j < C ; j++){
                if(fields[i].charAt(j) == 'D'){
                    if(dist_hedgehog[i][j] == -1) sb.append("KAKTUS");
                    else sb.append(dist_hedgehog[i][j]);
                }
            }
        }

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
