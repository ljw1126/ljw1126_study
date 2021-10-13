package extend;

import java.util.*;
import java.io.*;
/*
    [응용1] 연산자 끼워넣기		
    https://www.acmicpc.net/problem/14888

    ``````````````````````````````````````````````````````````````````````
    [문제 풀이 구조]

    static int N, max, min;
    static int[] nums, operators, order; 

    //order[1...N-1] 에 연산자들이 순서대로 저장될 것이다. 
    static void rec_func(int k){
        if(k == N+1){ // 모든 연산자들을 전부 나열하는 방법을 찾은 상태
            // 정한 연산자 순서대로 계산해서 장답을 갱신하기 
        }else{ // k번째 연산자는 무엇을 선택할 것인가?
            //4가지 연산자들 중에 뭘 쓸 것인지 선택하고 재귀호출하기 
        }

    }



    ``````````````````````````````````````````````````````````````````````

*/
public class ex1_14888 {
    static int N, max, min;
    static int[] nums, operators, order; 

    static StringBuilder sb = new StringBuilder();

    static void input(){
        FastReader scan = new FastReader();
        N = scan.nextInt();
        
        nums = new int[N+1];
        operators = new int[5]; // 연산자는 총 4개이므로
        order = new int[N+1];

        for(int i=1; i<= N; i++) nums[i] = scan.nextInt();
        for(int i=1; i<= 4; i++) operators[i] = scan.nextInt();

        max = Integer.MIN_VALUE;
        min = Integer.MAX_VALUE;
    }
    
    // 완성된 식에 맞게 계산해서 정답에 갱신하는 작업
    static int calculator(){
        //nums, order 
        int value = nums[1];
        for(int i=1;i<=N-1;i++){
            //value order[i] nums[i+1]
            if(order[i] == 1) // +
                value += nums[i+1];
            if(order[i] == 2) // -
                value -= nums[i+1];
            if(order[i] == 3) // *
                value *= nums[i+1];
            if(order[i] == 4) // /
                value /= nums[i+1];
        }
        return value;
    }




    // 문제 해결 함수(재귀용법 사용)
    // order[1...N-1] 에 연산자들이 순서대로 저장될 것이다.
    static void rec_func(int k){
        if(k == N){ // 모든 연산자들을 전부 나열하는 방법을 찾은 상태
            // 정한 연산자 순서대로 계산해서 정답을 갱신하기
            // 완성된 식에 맞게 계산을 해서 정답에 갱신하는 작업 필요
            int value = calculator();
            max = Math.max(max, value);
            min = Math.min(min, value);
        }else{ // k번째 연산자는 무엇을 선택할 것인가?
            // 4가지의 연산자들 중에 뭘 쓸 것인지 선택하고 재귀호출하기 
            // k 번째 연산자는 무엇을 선택할 것인가?
            for(int cand=1; cand<=4 ; cand++){
                if(operators[cand] >= 1){
                    operators[cand]--;
                    order[k] = cand;
                    rec_func(k+1);
                    //함수가 끝난 뒤 
                    operators[cand]++;
                    order[k] = 0;
                }
            }
        }
    }

    public static void main(String[] args) {
        // 1. 입력값 , 초기화 
        input();
        // 2. 함수 실행 
        // >> 1번째 원소부터 M번째 원소를 조건에 맞게 고르는 모든 방법을 탐색해줘
        rec_func(1);
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
