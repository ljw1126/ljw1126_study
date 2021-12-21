package algorithm.Test;

import java.util.*;
import java.io.*;
/*
     문자열 집합 - 실버 3
    https://www.acmicpc.net/problem/14425

    211221 트리 유형으로 분류 되어 있는데, 간단하게 품 
*/
public class ex_14425 {

    static MyReader scan = new MyReader();
    static ArrayList<String> list = new ArrayList<>();
    
    static int N,M;
    
    static void input(){
        N = scan.nextInt();
        M = scan.nextInt();

        for(int i=0;i<N;i++){
            list.add(scan.nextLine());
        }
    }

    static void pro(){
        int ans = 0;
        for(int i=0;i<M;i++){
            if(list.contains(scan.nextLine())) ans++;
        }
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
