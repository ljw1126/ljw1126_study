package recommandEx;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
    양 - 실버2    // 211203
    https://www.acmicpc.net/problem/3184

    . 은 필드 
    # 은 울타리
    o 는 양 
    v 는 늑대 

    o > v 인 경우 o(양)만 살아남음
    o <= v 인 경우 v(늑대)만 살아남음 
    
    수평,수직으로만 움직일 수 있음 

    입력)
    첫 줄에는 두 정수 R과 C가 주어지며(3 ≤ R, C ≤ 250), 각 수는 마당의 행과 열의 수를 의미한다.
    다음 R개의 줄은 C개의 글자를 가진다. 이들은 마당의 구조(울타리, 양, 늑대의 위치)를 의미한다.

    출력)
    하나의 줄에 아침까지 살아있는 양과 늑대의 수를 의미하는 두 정수를 출력한다.

    # 입력예시 1 
    6 6
    ...#..
    .##v#.
    #v.#.#
    #.o#.#
    .###.#
    ...###

    # 출력예시 1    // 양 0 마리, 늑대 2마리 
    0 2
*/
public class ex4_3184practice {
    
    static StringBuilder sb = new StringBuilder();
    static MyReader scan = new MyReader();

    static int R,C,v,o;
    static String[] areas; 
    static boolean[][] visit;
    static int[][] dir = {{-1,0},{0,-1},{1,0},{0,1}};

    static void dfs(int x , int y){
        visit[x][y] = true;

        if(areas[x].charAt(y) == 'o') o++;
        else if(areas[x].charAt(y) == 'v') v++;
        
        for(int k=0;k<4;k++){
            int dx = x + dir[k][0];
            int dy = y + dir[k][1];

            if(dx < 0 || dy < 0 || dx >= R || dy >= C) continue;
            if(visit[dx][dy]) continue;
            if(areas[dx].charAt(dy) == '#') continue;
            
            dfs(dx,dy);
        }
    }


    static void pro(){
        int result_o = 0, result_v = 0;
        for(int i=0;i<R;i++){
            for(int j=0; j<C;j++){
                if(!visit[i][j] && areas[i].charAt(j) != '#'){
                    v = 0; o = 0 ; // v는 늑대, o는 양 
                    dfs(i,j);
                    if( o > v) result_o += o;
                    else result_v += v;
                }
            }
        }

        sb.append(result_o).append(' ').append(result_v);
        System.out.println(sb);
    }

    static void input(){
        R = scan.nextInt();
        C = scan.nextInt();

        visit = new boolean[R][C];
        areas = new String [R];
        
        for(int i=0 ; i<R;i++)
            areas[i] = scan.nextLine();
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
            while( st == null || !st.hasMoreElements()){
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
            String str ="";
            try {
                str = br.readLine();
            } catch (Exception e) {
                e.printStackTrace();
            }
            return str;
        }
    }


}
