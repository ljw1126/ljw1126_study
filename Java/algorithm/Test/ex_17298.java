package algorithm.Test;

import java.util.*;
import java.io.*;

/*

    오큰수 - 골드 4 
    https://www.acmicpc.net/problem/17298
    
    - 해당 인덱스 값 기준 오른쪽에 있는 내용중 왼쪽에 있는 큰값(없으면-1)
    - 이중 for문 사용시 10^12 라서 시간 초과 발생함 

    참고.
    https://st-lab.tistory.com/196
*/
public class ex_17298 {
    
    static MyReader scan = new MyReader();
    static Stack<Integer> stk = new Stack<>();

    static int N;
    static int[] list;

    static void input(){
        N = scan.nextInt();
        list = new int[N+1];
        for(int i=0;i<N;i++) list[i] = scan.nextInt();
    }

    static void pro(){
        for(int i=0; i < N;i++){
        
        }
    }
    public static void main(String[] args) {
        input();
        pro();
    }

    static class MyReader{
        BufferedReader br;
        StringTokenizer st;

        public MyReader(){
            br = new BufferedReader(new InputStreamReader(System.in));
        }

        String next(){
            while(st == null || !st.hasMoreElements()){
                try {
                    st = new StringTokenizer(br.readLine());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            return st.nextToken();
        }

        int nextInt(){
            return Integer.parseInt(next());
        }
        
        Long nextLong(){
            return Long.parseLong(next());
        }

        String nextLine(){
            String str = "";
            try {
                str = br.readLine();
            } catch (Exception e) {
                e.printStackTrace();
            }
            return str;
        }
    }
}
