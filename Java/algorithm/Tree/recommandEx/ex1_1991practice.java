package algorithm.Tree.recommandEx;

import java.util.*;
import java.io.*;
/*
    트리 순회 - 실버 1 
    https://www.acmicpc.net/problem/1991


            A
        B       C
      D       E   F
                    G

    # 예제 입력 1 
    7
    A B C
    B D .
    C E F
    E . .
    F . G
    D . .
    G . .

    # 예제 출력 
    ABDCEFG       ---전위 순회한 결과 (루트) (왼쪽 자식) (오른쪽 자식)
    DBAECFG       ---중위 순회한 결과 (왼쪽 자식) (루트) (오른쪽 자식)
    DBEGFCA       ---후위 순회한 결과 (왼쪽 자식) (오른쪽 자식) (루트)

    #childs 값 넣을 시 

    [1, 2]
    [3, -1]
    [4, 5]
    [-1, -1]
    [-1, -1]
    [-1, 6]
    [-1, -1]
    [0, 0]
    [0, 0]
    [0, 0]
    [0, 0]
    [0, 0]
    [0, 0]
    [0, 0]
    [0, 0]
    [0, 0]
    [0, 0]
    [0, 0]
    [0, 0]
    [0, 0]
    [0, 0]
    [0, 0]
    [0, 0]
    [0, 0]
    [0, 0]
    [0, 0]
    [0, 0]
    [0, 0]
    [0, 0]
    [0, 0]

*/
public class ex1_1991practice {
    
    static MyReader scan = new MyReader();
    static StringBuilder sb = new StringBuilder();

    static int n;
    static int[][] childs;

    static void input() {
        n = scan.nextInt();
        childs = new int[30][2];

        for(int i=0; i < n ; i++){
            char a = scan.next().charAt(0);
            int na = (int)(a-'A');
            for(int j=0;j<2;j++){
                char b = scan.next().charAt(0);
                if(b == '.') childs[na][j] = -1; 
                else childs[na][j] = (int)(b-'A');
            }
        }
    }
    
    //전위 순회한 결과 : ABDCEFG // (루트) (왼쪽 자식) (오른쪽 자식)
    static void pre_order(int x) {
        if( x  == -1) return;
        sb.append((char)(x + 'A')); // 숫자를 영문 변환
        pre_order(childs[x][0]);
        pre_order(childs[x][1]);
    }
    
    //중위 순회한 결과 : DBAECFG // (왼쪽 자식) (루트) (오른쪽 자식)
    static void in_order(int x) {
        if( x  == -1) return;
        in_order(childs[x][0]);
        sb.append((char)(x + 'A')); // 숫자를 영문 변환
        in_order(childs[x][1]);
    }
    
    //후위 순회한 결과 : DBEGFCA // (왼쪽 자식) (오른쪽 자식) (루트)
    static void post_order(int x) {
        if( x  == -1) return;
        post_order(childs[x][0]);
        post_order(childs[x][1]);
        sb.append((char)(x + 'A')); // 숫자를 영문 변환
    }

    static void pro() {
        pre_order(0);
        sb.append('\n');
        in_order(0);
        sb.append('\n');
        post_order(0);

        System.out.println(sb);
    }

    public static void main(String[] args) {
        input();
        pro();
        // System.out.println('A' - (int)'A');
        // System.out.println('B' - (int)'A');
        // System.out.println('Z' - (int)'A');
    
        //for(int[] i : childs) System.out.println(Arrays.toString(i));
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
