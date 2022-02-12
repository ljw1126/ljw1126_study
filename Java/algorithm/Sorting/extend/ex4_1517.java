package extend;
/*
    버블 소트 - 플래티넘 5
    https://www.acmicpc.net/problem/1517   
    
    211218 n^2으로 풀 경우 시간초과 발생함 -> 병합정렬로 다 푸네?
*/

import java.util.*;
import java.io.*;

public class ex4_1517 {
    
    static MyReader scan = new MyReader();
    static StringBuilder sb = new StringBuilder();

    static int N,ans;
    static int[] a;

    static void input(){
        N = scan.nextInt();
    
        a = new int[N];
        for(int i=0;i<N;i++) a[i] =scan.nextInt();
    }

    static void swap(int[] _a, int idx1, int idx2){
        int temp = _a[idx1];
        _a[idx1] = _a[idx2];
        _a[idx2] = temp;
    }

    static void stdBubbleSort(){
          // 버블 정렬 수행 
          int left = 0;
          int right = a.length-1;
          int last = right;
          while(left < right){
  
              for(int i=right ; i > left ; i--){
                  if(a[i] < a[i-1]) {
                      swap(a, i, i-1);
                      last = i;
                      ans++;
                  }
              }
  
              left = last;
  
              for(int j=left; j < right ; j++){
                  if(a[j] > a[j+1]){
                      swap(a, j , j+1);
                      last = j;
                      ans++;
                  }
              }
              right = last;
          }
  
    }

    static void pro(){

        // 결과 출력
        System.out.println(ans);
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
