package extend;

import java.util.*;
import java.io.*;
/*
    [응용1] 연산자 끼워넣기		
    https://www.acmicpc.net/problem/14888
    [개선]
    - ex1_14888.java의 경우 탐색완료시 매번 계산해야 됨
    - 그래서 rec_func()에 매개변수에 value를 넘겨주면서 계산 시키면서 하도록함 
    - 그 결과 calculator() 반복문을 돌지 않기때문에 시간 개선됨 
*/
public class ex1_advanced {
    static int N, max, min;
    static int[] nums, operators, order; 

    static StringBuilder sb = new StringBuilder();

    static void input(){
        FastReader scan = new FastReader();
        N = scan.nextInt();
        
        nums = new int[N+1];
        operators = new int[5]; // 연산자는 총 4개이므로
        //order = new int[N+1]; // 바로 꼐산하니 필요없어짐

        for(int i=1; i<= N; i++) nums[i] = scan.nextInt();
        for(int i=1; i<= 4; i++) operators[i] = scan.nextInt();

        max = Integer.MIN_VALUE;
        min = Integer.MAX_VALUE;
    }
    
    // 피연산자 2개와 연산자 1개 주어졌을대 게산해주는 함수 (수정)
    static int calculator(int operand1, int operator, int operand2){
        //nums, order 
        if(operator == 1)
            return operand1 + operand2;
        else if(operator == 2) // -
            return operand1 - operand2;
        else if(operator == 3) // *
            return operand1 * operand2;
        else// /
            return operand1 / operand2;    
    }




    // 문제 해결 함수(재귀용법 사용)
    // order[1...N-1] 에 연산자들이 순서대로 저장될 것이다.
    static void rec_func(int k, int value){
        if(k == N){ // 모든 연산자들을 전부 나열하는 방법을 찾은 상태
            // 정한 연산자 순서대로 계산해서 정답을 갱신하기
            // 완성된 식에 맞게 계산을 해서 정답에 갱신하는 작업 필요
            max = Math.max(max, value);
            min = Math.min(min, value);
        }else{ // k번째 연산자는 무엇을 선택할 것인가?
            // 4가지의 연산자들 중에 뭘 쓸 것인지 선택하고 재귀호출하기 
            // k 번째 연산자는 무엇을 선택할 것인가?
            for(int cand=1; cand<=4 ; cand++){
                if(operators[cand] >= 1){
                    operators[cand]--;
                    //order[k] = cand;
                    rec_func(k+1, calculator(value, cand, nums[k+1]));
                    //함수가 끝난 뒤 
                    operators[cand]++;
                    //order[k] = 0;
                }
            }
        }
    }

    public static void main(String[] args) {
        // 1. 입력값 , 초기화 
        input();
        // 2. 함수 실행 
        // >> 1번째 원소부터 M번째 원소를 조건에 맞게 고르는 모든 방법을 탐색해줘
        rec_func(1, nums[1]);
        // 3. 최종 결과 출력
        sb.append(max).append('\n').append(min);
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
