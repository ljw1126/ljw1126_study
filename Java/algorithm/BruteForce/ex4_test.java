import java.util.*;
import java.io.*;
/*
    https://www.acmicpc.net/problem/15650
    -  N개 중 2) 중복없이, M개를 B)고르기
    -  N=4, M=3 
    - 수학의 조합(Combination)으로 표현가능 
    - 시간복잡도 : O(nCm) 
    - 공간복잡도 : O(M)

    # 입력 예시 1
        4 2  
    # 출력 예시 1
        1 2
        1 3
        1 4
        2 3
        2 4
        3 4

    # 입력 예시 2
        4 4
    # 출력 예시 2
        1 2 3 4

*/
public class ex4_test {
    
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
    // 반복문 2중 반복되서 좋지 않음 
    static void rec_func(int k){
        if(k == M+1){
            for(int idx = 1 ; idx <= M ; idx ++) sb.append(selected[idx]).append(' ');
            sb.append('\n');
        }else{
            for(int cand=1 ; cand <= N; cand++){
                // 같거나, 앞에 자릿수보다 작은 경우 
                boolean isUsed = false;
                for(int i=1; i <k; i++)
                    if(cand == selected[i] || cand < selected[i]) isUsed = true;
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
