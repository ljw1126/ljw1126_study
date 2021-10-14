package personal;

import java.util.*;
import java.io.*;
import java.lang.reflect.Array;

/*
비내림차순.. 동일한 문제인듯

https://www.acmicpc.net/problem/15657

ex3_15652.java와 동일한데, 이번에 내가한게 좀더 간결한듯?


문제
    N개의 자연수와 자연수 M이 주어졌을 때, 아래 조건을 만족하는 길이가 M인 수열을 모두 구하는 프로그램을 작성하시오. N개의 자연수는 모두 다른 수이다.
    > N개의 자연수 중에서 M개를 고른 수열
    > 같은 수를 여러 번 골라도 된다.
    > 고른 수열은 비내림차순이어야 한다.
        길이가 K인 수열 A가 A1 ≤ A2 ≤ ... ≤ AK-1 ≤ AK를 만족하면, 비내림차순이라고 한다.

*/
public class ex_15657 {
    static int N, M;
    static int[] nums, selected; 

    static StringBuilder sb = new StringBuilder();

    static void input(){
        FastReader scan = new FastReader();
        N = scan.nextInt();
        M = scan.nextInt();

        nums = new int[N+1];
        selected = new int[M+1];

        for(int i=1; i <= N; i++) nums[i] = scan.nextInt();

        Arrays.sort(nums, 1, N+1); // 1부터 N+1 미만 인덱스까지 정렬
    }

    // 문제 해결 함수(재귀용법 사용)
    static void rec_func(int k){
        if(k == M+1){
           // 결과값 처리
           for(int i=1; i <= M ; i++) sb.append(nums[selected[i]]).append(" ");
           sb.append("\n"); 
        }else{  
           // 반복문 내에 재귀용법으로 처리하는 부분, 
           for(int cand = 1 ; cand <= N ; cand ++){
               if(selected[k-1] > cand ) continue;

               selected[k] = cand; 
               rec_func(k+1);
               selected[k] = 0;
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
