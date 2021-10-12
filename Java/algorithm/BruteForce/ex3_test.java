import java.util.*;
import java.io.*;
/*
    실버3 . 
    https://www.acmicpc.net/problem/15652
    - N개 중 1) 중복을 허용해서 , M개를 B) 고르기 
    - 시간복잡도 : O(N^M) => 최대값이 8이므로 8^8 = 1677만보단 작다 
    - 공간복잡도 : O(M)  
    # 입력예시 1 
        4 2
    # 출력예시 1
        1 1
        1 2
        1 3
        1 4
        2 2
        2 3
        2 4
        3 3
        3 4
        4 4
    # 입력예시 2
        3 3
    # 출력예시 2    
        1 1 1
        1 1 2
        1 1 3
        1 2 2
        1 2 3
        1 3 3
        2 2 2
        2 2 3
        2 3 3
        3 3 3
*/
public class ex3_test {
    static int N, M;
    static int[] selected;
    static StringBuilder sb = new StringBuilder();

    static void input(){
        FastReader scan = new FastReader();
        N = scan.nextInt();
        M = scan.nextInt();
        selected = new int[M+1];
    }

    // 문제 해결 함수 , 재귀 
    // 정답은 맞지만 이중 for문이므로 좋지 않음 
    static void rec_func(int k){
        if(k == M+1){
            for(int i=1; i<=M;i++) sb.append(selected[i]).append(' ');
            sb.append('\n');
        }else{
            for(int cand = 1; cand <= N; cand++){
                boolean isUsed = false; 
                for(int idx =1; idx <k ; idx++)
                    if(cand < selected[idx]) isUsed = true;
                
                if(!isUsed){
                    selected[k] = cand; 
                    rec_func(k+1);
                    selected[k] = 0;
                }
            }
        }
    }

    public static void main(String[] args) {
        // 1. 입력값 , 초기화 
        input();
        // 2. 함수 실행 
        rec_func(1);
        System.out.println(sb.toString()); //3. 최종 출력

    }

    static class FastReader{
        BufferedReader br;
        StringTokenizer st; 

        public FastReader(){
            br = new BufferedReader(new InputStreamReader(System.in));
        }

        public FastReader(String s) throws FileNotFoundException{
            br = new BufferedReader(new FileReader(new File(s)));
        }

        String next(){
            while(st == null || !st.hasMoreElements()){
                try{
                    st = new StringTokenizer(br.readLine());
                }catch(IOException e){
                    e.printStackTrace();
                }
            }
            return st.nextToken();
        }

        int nextInt(){
            return Integer.parseInt(next());
        }

        long nextLong(){
            return Long.parseLong(next());
        }

        double nextDouble(){
            return Double.parseDouble(next());
        }

        String nextLine(){
            String str = "";
            try{
                str = br.readLine();
            }catch(IOException e){
                e.printStackTrace();
            }
            return str;
        }


    }
}
