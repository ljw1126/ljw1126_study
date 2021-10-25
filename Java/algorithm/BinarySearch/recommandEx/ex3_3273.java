package algorithm.BinarySearch.recommandEx;

import java.util.*;
import java.io.*;
/*
    두 수의 합(실버3)
    https://www.acmicpc.net/problem/3273

    - O(N^2) 생각하면 100억이라 시간초과 
    - 이분탐색하면 260만 정도 
*/
public class ex3_3273 {
    static FastReader scan = new FastReader();
    static StringBuilder sb = new StringBuilder();

    static int N,sum;
    static int[] A;

    static void input() {
        N = scan.nextInt();
        A = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            A[i] = scan.nextInt();
        }
        //X = scan.nextInt();
        sum = scan.nextInt();
    }
    //내가 한 것도 정답이지만 
    // static boolean BinarySearch(int[] A , int std , int L, int R, int X){

    //     while(L<=R){
    //         int mid = (L+R)/2;
    //         if(std + A[mid] == X){
    //             return true;
    //         }else if(std + A[mid] > X){
    //             R = mid - 1;
    //         }else{
    //             L = mid + 1;
    //         }
    //     }
    //     return false; 
    // }


    // static void pro() {
    //     int ans = 0;
    //     Arrays.sort(A,1,N+1); // 이분 탐색 가능해짐 

    //     for(int i=1; i< N+1;i++){
    //         if(BinarySearch(A,A[i],i+1,N,X)) ans++;
    //     }
    //     System.out.println(ans);
    // }

    static boolean bin_search(int[] A, int L, int R, int X){
        while(L<=R){
            int mid = (L+R)/2;
            if(A[mid] == X) 
                return true; 

            if(A[mid] < X)
                L = mid +1;
            else
                R = mid -1;
        }
        return false;
    }

    static void pro(){
        Arrays.sort(A,1,N+1);
        int ans = 0;
        for(int i=1; i< N+1;i++){
            if(bin_search(A,i+1,N,sum - A[i])) ans++;   // 뺏을때 값이 있냐 없냐
        }
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
