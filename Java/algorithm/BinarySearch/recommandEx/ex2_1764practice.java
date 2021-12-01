package algorithm.BinarySearch.recommandEx;

import java.util.*;
import java.io.*;
/*
    듣보잡(실버4)
    https://www.acmicpc.net/problem/1764

    String 비교하는거 보고 혼자 품 
*/
public class ex2_1764practice {
    
    static FastReader scan = new FastReader();
    static StringBuilder sb = new StringBuilder();

    static int N, M;
    static String[] A, B;
    static ArrayList<String> ans = new ArrayList<>();

    static void input() {
        N = scan.nextInt(); // 듣도 못한 사람의 수 
        M = scan.nextInt(); // 보도 못한 사람의 수 
        A = new String[N + 1];
        for (int i = 1; i <= N; i++) {
            A[i] = scan.nextLine();
        }

        B = new String[M+1];
        for(int i=1; i<=M;i++){
            B[i] = scan.nextLine();
        }
        
        Arrays.sort(A, 1, N+1);
    }

    static boolean bin_search(String[] A, int L,int R, String X){
        // A[L ... R] 이 정렬되어 있다고 가정했을 때
        
        // 이 안에서 X 를 이분탐색하고, 존재하면 true, 아니면 false 를 return 하는 함수
        while(L <= R){
            int mid = (L+R)/2;
            if(A[mid].equals(X)) return true;
            else if(A[mid].compareTo(X) > 0) R = mid -1;
            else L = mid + 1;
        }


        return false;
    }

    static void pro() {
        // 보도 못한 사람들을 입력 받으면서 듣도 못한 사람들 안에서 찾아주기
        // 정답을 기록해서 사전순으로 출력해주기
        int cnt = 0 ;
        for(int i=1; i<= M;i++){
            if(bin_search(A, 1, N, B[i])){
                ans.add(B[i]);
            }  
        }   

        Collections.sort(ans);
        sb.append(ans.size()).append('\n');
        for(String s : ans) sb.append(s).append('\n');
        System.out.println(sb);
    }

    public static void main(String[] args) {
        input();
        pro();
        //System.out.println(Arrays.toString(A));
        //System.out.println(Arrays.toString(B));
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
