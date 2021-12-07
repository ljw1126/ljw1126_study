package algorithm.Tree.recommandEx;
/*
    이진 검색 트리 
    https://www.acmicpc.net/problem/5639


    # 예제 입력 
    50
    30
    24
    5
    28
    45
    98
    52
    60

    # 예제 출력
    5
    28
    24
    45
    30
    60
    52
    98
    50

*/

import java.io.*;
import java.util.*;

public class ex2_5639 {
    static FastReader scan = new FastReader();
    static StringBuilder sb = new StringBuilder();

    static ArrayList<Integer> a;

    static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input = "";
        a = new ArrayList<>();
        while ((input = br.readLine()) != null){
            a.add(Integer.parseInt(input));
        }
    }

    
    // 현재 Subtree가 a[l...r] 일 때, 후위 순회를 하는 함수.
    static void traverse(int l, int r) {
        if (l > r) return;
        int mid = r;  // 왼쪽과 오른쪽 subtree를 가르는 기준 위치를 나타내는 변수
        for (int i = l + 1; i <= r; i++) {
            if (a.get(i) > a.get(l)) {
                mid = i - 1;
                break;
            }
        }

        // 후위 순회
        traverse(l + 1, mid);
        traverse(mid + 1, r);
        sb.append(a.get(l)).append('\n');
    }

    static void pro() {
        traverse(0, a.size() - 1);
        System.out.print(sb);
    }

    public static void main(String[] args) throws IOException {
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
