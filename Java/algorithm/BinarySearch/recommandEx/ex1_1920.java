package algorithm.BinarySearch.recommandEx;

import java.util.*;
import java.io.*;
/*

   첫째 줄에 자연수 N(1 ≤ N ≤ 100,000)이 주어진다. 
   다음 줄에는 N개의 정수 A[1], A[2], …, A[N]이 주어진다. 
   다음 줄에는 M(1 ≤ M ≤ 100,000)이 주어진다. 
   다음 줄에는 M개의 수들이 주어지는데, 
   이 수들이 A안에 존재하는지 알아내면 된다. 
   모든 정수의 범위는 -231 보다 크거나 같고 231보다 작다.

   - O(N^2) 생각해보면 100억이기때문에 시간초과 발생가능 
   - 이분 탐색 사용시 
     - 정렬 O(NlogN)
     - 각 숫자를 돌면서 이분 탐색하면 O(NlogN)
     - 따라서 시간복잡도 O(NlogN)걸림

     https://www.acmicpc.net/problem/1920


     # 입력예시 
     5
     4 1 5 2 3
     5
     1 3 7 9 5

     # 출력예시
     1
     1
     0
     0
     1
*/
public class ex1_1920 {
    static FastReader scan = new FastReader();
    static StringBuilder sb = new StringBuilder();

    static int N, M;
    static int[] A;

    static void input() {
        N = scan.nextInt();
        A = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            A[i] = scan.nextInt();
        }

    }
    // 이게 왜 틀렸다는 건지.. if문 검사해서 그런가?
    // static int binarySearch(int X ) {
    //     int L = 1, R = A.length-1;

    //     int idx = 0;
    //     while(L<=R){
    //         int mid = (L+R)/2;
    //         if(X >= A[mid]){
    //             idx = mid;
    //             L = mid + 1;
    //         }else if(X < A[mid]){
    //             R = mid - 1;
    //         }
    //     }

    //     if(A[idx] == X) {
    //         return 1;
    //     }else{
    //         return 0;
    //     } 
    // }

    static boolean binarySearch(int[] A, int L, int R, int X){
        while(L<=R){
            int mid = (L+R)/2;
            if(A[mid] == X){
                return true;
            }else if(A[mid] < X){
                L = mid +1;
            }else{
                R = mid -1;
            }
        }
        return false;
    }

    static void pro() {
        int M = scan.nextInt();
        Arrays.sort(A, 1, N+1);
        // 이분 탐색을 실행해서 존재 여부 확인 
        for(int i = 1 ; i <= M ; i++){
            int X = scan.nextInt();
            if(binarySearch(A, 1, N, X))sb.append(1).append('\n');
            else sb.append(0).append('\n');
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
