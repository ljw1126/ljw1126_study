package algorithm.BinarySearch;

import java.util.*;
import java.io.*;
/*
    [나무 자르기 - 난이도 2]
    https://www.acmicpc.net/problem/2805
    - 최대치 계산해보기 ! ( 여기서 많이 틀림 )
  - 나무의 개수 N = 100만, 필요한 나무의 길이 M = 20억  
    - 1. 정답의 범위 : 0 ~ 10억 
      - 각 나무 별 10억의 크기를 가지고 H = 0 으로 했을때
    - 2. 잘린 나무의 길이 합 ≤ 나무 높이의 총합 = 100만 * 10억 = 10^15
      - **Integer 범위 초과!** , **즉 계산 과정 중의 변수 타입은 long으로 !** 
      
    - 원래 문제 : **원하는 길이 M 만큼 얻을 수 있는 최대 높이 H**는 얼마인가?
    - 뒤집은 문제 : **어떤 높이 H로 잘랐을 때, 원하는 길이 M만큼**을 얻을 수 있는가? (Yes/No)
    - 시간 복잡도 : **O(N)**

*/
public class ex3_2805template {
    static FastReader scan = new FastReader();
    static StringBuilder sb = new StringBuilder();

    static int N, M;
    static int[] A;

    static void input() {
        N = scan.nextInt();
        M = scan.nextInt();
        A = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            A[i] = scan.nextInt();
        }
    }

    static boolean determination(int H) {
        // H 높이로 나무들을 잘랐을 때, M 만큼을 얻을 수 있으면 true, 없으면 false를 return하는 함수
        return false; 
    }

    static void pro() {
        long L = 0, R = 2000000000, ans = 0;
        // [L ... R] 범위 안에 정답이 존재한다!
        // 이분 탐색과 determination 문제를 이용해서 answer를 빠르게 구하자!
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
