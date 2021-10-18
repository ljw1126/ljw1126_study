﻿package extend;

import java.util.*;
import java.io.*;

public class ex1_practice {
    static int N, max, min;
    static int[] nums, operators;

    static StringBuilder sb = new StringBuilder();

    static void input(){
        FastReader scan = new FastReader();
        N = scan.nextInt();
        nums = new int[N+1];
        operators = new int[5];

        for(int i = 1; i<N+1 ; i++) nums[i] = scan.nextInt();
        for(int i = 1; i<=4 ; i++) operators[i] = scan.nextInt();

        max = Integer.MIN_VALUE;
        min = Integer.MAX_VALUE;
    }


    static int cal(int operand1, int operator, int operand2){
        if(operator == 1)    // +
            return operand1 + operand2;
        else if(operator == 2) // -
            return operand1 - operand2;
        else if(operator == 3) // *
            return operand1 * operand2;
        else //  /
            return operand1 / operand2;
    }

    static void rec_func(int k, int value){
        if( k == N){ // 모든 연산자를 나열하는 방법 찾았을때 
            min = Math.min(min, value);
            max = Math.max(max, value);
        }else{
            for(int cand=1; cand <= 4 ; cand++){ // cand 값이 operator 연산자 인덱스 번호니깐
                if(operators[cand] >= 1){
                    operators[cand]--;
                    rec_func(k+1, cal(value, cand, nums[k+1]));
                    operators[cand]++;
                }
            }
        }
    }

    public static void main(String[] args) {
        input();

        rec_func(1, nums[1]);
        sb.append(max).append('\n').append(min);
        System.out.println(sb.toString());
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