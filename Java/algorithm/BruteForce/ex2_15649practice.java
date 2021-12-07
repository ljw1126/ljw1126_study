
import java.util.*;
import java.io.*;
/*
    N과 M(1)
    https://www.acmicpc.net/problem/15649

    중복 x , 순서 o

    시간 복잡도 : O(nPm) = O ( 4! / 2!) = 12 => 최대치 O(8!/0!) = 40,320
    공간 복잡도 : O(M);

    _ _
    4*3

    # 예제 입력 
    4 2

    # 예제 출력 
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

*/
public class ex2_15649practice {
    static int N, M;
    static int[] selected, used;
    static StringBuilder sb = new StringBuilder();

    static void input(){
        FastReader scan = new FastReader();
        N = scan.nextInt();
        M = scan.nextInt();
        selected = new int[M+1];
        used = new int[N+1];
    }

    // 문제 해결 함수 , 재귀 
    static void rec_func(int k){ // k가 자릿수 뜻함 ( selected 배열에서 0 번은 의미 없음 )
        if( k == M+1 ){ // 재귀 호출시 종료 조건
            for(int i=1;i<=M;i++) sb.append(selected[i]).append(' ');
            sb.append('\n');
            return;
        }else{
            for(int cand = 1 ; cand <= N; cand++){
                
                if(used[cand] == 1) continue;

                selected[k] = cand; 
                used[cand] = 1;
                
                rec_func(k+1);
                
                selected[k] = 0; 
                used[cand] = 0;
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
