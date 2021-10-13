package extend;

import java.util.*;
import java.io.*;

/*
    [응용3]	부분 수열의 합		
    https://www.acmicpc.net/problem/1182
    - 부분 수열 : 수열의 일부 항을 선택해서 원래 순서대로 나열 
    - 진 부분 수열 : 아무것도 안 고른 경우는 뺀 수열 ( 값을 입력안한 )
    - 복잡도 계산 
        -  N <= 20 
            - 부분 수열의 개수 상한 : 2^20 <= 1,048,576 -- 정답변수는 int를 쓰면 된다!
        - 목표값 |s| <= 1,000,000
            - 부분 수열의 합 상한 : 20개* 1,000,000(최대값) -- 합을 기록하는 변수로 int형 쓰면 된다!
        - |Ai| <= 1,000,000
        - 1~N번 원소에 대해 
            - 0: 부분 수열에 포함 x , 1: 부분수열에 포함 o
            - 시간복잡도 O(N^M) = O(2^20)
    ``````````````````````````````````````````````````````````````````````
    ※ 진 부분 집합 이란?
        - 전체 집합의 부분집합 중에서 자기 자신의 집합을 제외한 나머지 부분집합을 말함 
        - 예를 들어 집합A={3,4,6}이 있을 때, 진부분집합은 ø .{3}, {4}, {6}, {3,4}, {3,6}, {4,6} 총 7개이다.
          진부분집합의 개수(N), 전체집합의 원소개수(n)라 할 때, 진부분집합의 개수는 N = 2n-1 이다.
   
    ``````````````````````````````````````````````````````````````````````
    [문제 풀이 구조]

    static int N, S, ans; 
    static int[] nums;

    // k번째 원소를 포함시킬 지 정하는 함수 
    // value := k-1 번째 원소까지 골라진 원소들의 함 
    static void rec_func(int k , int value){
        if(k == N+1){ // 부분 수열을 하나 완성 시킨 상태 
            // value가 S랑 같은지 확인하기
        }else{
            // k번째 원소를 포함시킬 지 결정하고 재귀호출 해주기 
        }

    }



    ``````````````````````````````````````````````````````````````````````
*/
public class ex3_1182 {
    static int N, S, ans; 
    static int[] nums;

    static StringBuilder sb = new StringBuilder();

    static void input(){
        FastReader scan = new FastReader();
        N = scan.nextInt();
        S = scan.nextInt();
        nums = new int[N+1];
        for(int i = 1 ; i <= N ; i++) nums[i] = scan.nextInt();
    }

    // k번째 원소를 포함시킬 지 정하는 함수 
    // value := k-1 번째 원소까지 골라진 원소들의 함 
    static void rec_func(int k , int value){
        if(k == N+1){ // 부분 수열을 하나 완성 시킨 상태 
            // value가 S랑 같은지 확인하기
            if(value == S) ans++;
        }else{
            // k번째 원소를 포함시킬 지 결정하고 재귀호출 해주기 
            // Include하고 싶은 경우 
            rec_func( k+1, value + nums[k]);
            // not Include 인 경우 
            rec_func( k+1, value);
        
        }

    }

    public static void main(String[] args) {
        // 1. 입력값 , 초기화 
        input();
        // 2. 함수 실행 
        rec_func(1,0);
        // ans 가 정말 "진 부분집합"만 다루는지 확인하기 
        // s가 0이면 ans가 1이 올라가게 됨 
        if(S == 0){ 
            ans--;
        }
        System.out.println(ans); //3. 최종 출력

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
