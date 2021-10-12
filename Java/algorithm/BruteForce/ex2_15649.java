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
public class ex2_15649 {
    
    static int N, M, used;
    static int[] selected;
    static StringBuilder sb = new StringBuilder();

    static void input(){
        FastReader scan = new FastReader();
        N = scan.nextInt();
        M = scan.nextInt();
        selected = new int[M+1];
    }

    // 문제 해결 함수 , 재귀 
    static void rec_func(int k){ // k가 자릿수 뜻함 ( selected 배열에서 0 번은 의미 없음 )
        if( k == M+1 ){ // 재귀 호출시 종료 조건
            for(int i=1; i<=M;i++) sb.append(selected[i]).append(' ');
            sb.append('\n'); // 줄바꿈 
        }else{
            for(int cand = 1 ; cand <= N; cand++){
                //개선된 내용 ex2_advanced.java에함
                boolean isUsed = false;
                
                for( int i=1; i < k ; i++){
                    if(cand == selected[i]) isUsed = true;
                }

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
