import java.util.*;
import java.io.*;
/*
    실버3 .
    https://www.acmicpc.net/problem/15649
    - 1부터 N까지 자연수 중에서 중복 없이 M개를 고른 수열 ( 순열 )
    - 시간복잡도 O(nPr) = O(N!) //인데 N!보다는 적음 
    - 공간복잡도 O(M) 

    # 입력 예시 1
        4 2
    # 출력 예시 1
        1 2
        1 3
        1 4
        2 1
        2 3
        2 4
        3 1
        3 2
        3 4
        4 1
        4 2
        4 3
    # 입력 예시 2 
        4 4
    # 출력 예시 2
        1 2 3 4
        1 2 4 3
        1 3 2 4
        1 3 4 2
        1 4 2 3
        1 4 3 2
        2 1 3 4
        2 1 4 3
        2 3 1 4
        2 3 4 1
        2 4 1 3
        2 4 3 1
        3 1 2 4
        3 1 4 2
        3 2 1 4
        3 2 4 1
        3 4 1 2
        3 4 2 1
        4 1 2 3
        4 1 3 2
        4 2 1 3
        4 2 3 1
        4 3 1 2
        4 3 2 1


*/
public class ex2_advanced {
    static int N, M;
    static int[] selected,used;
    static StringBuilder sb = new StringBuilder();

    static void input(){
        FastReader scan = new FastReader();
        N = scan.nextInt();
        M = scan.nextInt();
        selected = new int[M+1];  // selected에서 0번 인덱스는 의미 x, 1번부터 첫짜리 생각함
        used = new int[N+1]; // 1~N까지 정수에 대한 사용여부
    }

    // 문제 해결 함수 , 재귀 
    static void rec_func(int k){ // k = 자릿수 뜻함 k번째 
        if(k == M + 1){
            for(int idx = 1; idx <= M ; idx++) sb.append(selected[idx]).append(' ');
            sb.append('\n'); // 줄바꿈
        }else{
            for(int cand = 1; cand <= N; cand++){
               
                if(used[cand] == 1) continue;
               
                selected[k] = cand; used[cand] = 1;
                
                rec_func(k+1);  
                
                selected[k] = 0; used[cand] = 0;
               
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
