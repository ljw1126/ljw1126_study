﻿package personal;

import java.util.*;
import java.io.*;
/*
    ※ ex4_15650.java와 동일
    문제
        N개의 자연수와 자연수 M이 주어졌을 때, 아래 조건을 만족하는 길이가 M인 수열을 모두 구하는 프로그램을 작성하시오. N개의 자연수는 모두 다른 수이다.
            > N개의 자연수 중에서 M개를 고른 수열
            > 고른 수열은 오름차순이어야 한다.
    입력
        첫째 줄에 N과 M이 주어진다. (1 ≤ M ≤ N ≤ 8)
        둘째 줄에 N개의 수가 주어진다. 입력으로 주어지는 수는 10,000보다 작거나 같은 자연수이다.

    출력
        한 줄에 하나씩 문제의 조건을 만족하는 수열을 출력한다. 중복되는 수열을 여러 번 출력하면 안되며, 각 수열은 공백으로 구분해서 출력해야 한다.
        수열은 사전 순으로 증가하는 순서로 출력해야 한다.

    # 입력 예시 1
        3 1
        4 5 2
    # 출력 예시 1
        2
        4
        5
    # 입력 예시 2
        4 2
        9 8 7 1
    # 출력 예시2
        1 7
        1 8
        1 9
        7 8
        7 9
        8 9
    # 입력 예시 3 
        4 4
        1231 1232 1233 1234
    # 출력 예시 3
        1231 1232 1233 1234
*/
public class ex_15655 {
    static int N, M;
    static int[] nums, selected;

    static StringBuilder sb = new StringBuilder();

    static void input(){
        FastReader scan = new FastReader();
        N = scan.nextInt();
        M = scan.nextInt();

        nums = new int[N+1];
        selected = new int[M+1];
        //used = new int[N+1];
        for(int i = 1; i <= N ; i++) nums[i] = scan.nextInt();

        Arrays.sort(nums, 1, N+1); // 오름차순 정렬 
    }

    // 문제 해결 함수(재귀용법 사용)
    static void rec_func(int k){
        if(k == M+1){
           // 결과값 처리 
           for(int i = 1 ; i <= M ; i++) sb.append(nums[selected[i]]).append(' ');
           sb.append('\n');
        }else{  
           // 반복문 내에 재귀용법으로 처리하는 부분
           for(int cand = selected[k-1] +1 ; cand <= N; cand++ ){
               //if(used[cand] == 1) continue;
             
               selected[k] = cand;  //used[cand] = 1;
               rec_func(k+1);
               selected[k] = 0 ;    //used[cand] = 0;
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
