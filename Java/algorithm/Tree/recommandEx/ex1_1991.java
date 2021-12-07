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

    #childs 값 넣을 시 // childs 값 가지고 손으로 해보기 

    [1, 2]       -- 0번인덱스가 A이고 , B,C가 두 자식이라는걸 표현 
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
 public class ex1_1991 {
    static FastReader scan = new FastReader();
    static StringBuilder sb = new StringBuilder();

    static int n;
    static int[][] childs;

    static void input() {
        n = scan.nextInt();
        childs = new int[30][2];
        for (int i = 0; i < n; i++) {
            char curCh = scan.next().charAt(0);
            int cur = (int)(curCh - 'A');
            for (int k = 0; k < 2; k++) {
                char ch = scan.next().charAt(0);
                if (ch != '.') childs[cur][k] = (int)(ch - 'A');
                else childs[cur][k] = -1;
            }
        }
    }

    static void in_order(int x) {
        if (x == -1) return;
        in_order(childs[x][0]);
        sb.append((char)(x + 'A'));
        in_order(childs[x][1]);
    }

    static void pre_order(int x) {
        if (x == -1) return;
        sb.append((char)(x + 'A'));
        pre_order(childs[x][0]);
        pre_order(childs[x][1]);
    }

    static void post_order(int x) {
        if (x == -1) return;
        post_order(childs[x][0]);
        post_order(childs[x][1]);
        sb.append((char)(x + 'A'));
    }

    static void pro() {
        pre_order(0);
        sb.append('\n');
        in_order(0);
        sb.append('\n');
        post_order(0);

        // 정답 출력하기
        System.out.print(sb);
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
