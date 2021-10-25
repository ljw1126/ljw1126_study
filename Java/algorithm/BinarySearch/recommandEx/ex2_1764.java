package algorithm.BinarySearch.recommandEx;

import java.util.*;
import java.io.*;
/*
    듣보잡(실버4)
    https://www.acmicpc.net/problem/1764

    - 정답은 구했는데 틀렸다 하네 또 
*/
public class ex2_1764 {
     static FastReader scan = new FastReader();
    static StringBuilder sb = new StringBuilder();

    static int N, M;
    static String[] A,ans;
    static void input() {
        N = scan.nextInt();
        M = scan.nextInt();
        A = new String[N+1];
        for (int i = 1; i <= N; i++) {
            A[i] = scan.nextLine();
        }

       // result = new ArrayList<>();
    }

    static boolean binarySearch(String[] A , int L, int R, String SearchData){
        while(L<=R){
            int mid = (L+R)/2;
            if(A[mid].equals(SearchData)){
                return true;
            }else if(A[mid].compareTo(SearchData) > 0){
                R = mid -1;
            }else{
                L = mid +1;
            }
        }

        return false; 
    }

    static void pro() {
        int ansCnt = 0;
        ans = new String[N+1];
        Arrays.sort(A, 1, N+1);
        for(int i=1; i<=M;i++){
            String X = scan.nextLine();
            if(binarySearch(A,1,N,X)) ans[++ansCnt] = X;
        }
        Arrays.sort(ans, 1, ansCnt +1); //이걸 정렬 안해줘서 내가 푼거 틀렸다함 .. Collections.sort(result); 하고 똑같이 하면 풀림 // 사전순 출력
        sb.append(ansCnt).append('\n');
        for(int i=1; i <= ansCnt ; i++) sb.append(ans[i]).append('\n');
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
