package algorithm.BinarySearch.recommandEx;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
    [랜선 자르기 - 실버 3]
    https://www.acmicpc.net/problem/1654

    K개의 랜선을 가지고 있을때 , 필요한 N개의 랜선을 만들 수 있는 최대 길이 H는 얼마인가?
    <-> 임의의 길이 H를 지정했을때, 필요한 N개의 랜선을 만들 수 있는가?
    
    # 입력 
    첫째 줄에는 오영식이 이미 가지고 있는 랜선의 개수 K, 
    그리고 필요한 랜선의 개수 N이 입력된다. 
    K는 1이상 10,000이하의 정수이고, N은 1이상 1,000,000이하의 정수이다. 
    그리고 항상 K ≦ N 이다. 그 후 K줄에 걸쳐 이미 가지고 있는 
    각 랜선의 길이가 센티미터 단위의 정수로 입력된다. 랜선의 길이는 231-1보다 
    작거나 같은 자연수이다.

    # 입력 예시
    4 11
    802
    743
    457
    539

    # 출력 예시 
    200

    // 정답은 구하는데 틀렸다네
    // R의 범위가 100만이라고 생각했는데 Integer.MAX_VALUE라니 되네 ;; 
    // 그리고 조건 구할떄 if문 필요없이 몫 다 더해서 필요한 개수 K가 되는지 비교하면 됨 
*/
import java.io.*;
import java.util.*;

public class ex5_1654practice {

    static StringBuilder sb = new StringBuilder();
    static MyReader scan = new MyReader();

    static int K, N;
    static int[] a;

    static void input(){
        K = scan.nextInt();
        N = scan.nextInt();

        a = new int [K+1];
        for(int i=1; i <= K ; i++){
            a[i] = scan.nextInt();
        }
    }

    static boolean func(int H){
        long sum = 0;
        for(int i=1; i<=K;i++){
            if(a[i]/H > 0) sum += a[i]/H;
        }

        return sum >= N;
    }

    static void pro(){
        int L=0, R = Integer.MAX_VALUE, ans = 0;
        
        while(L<=R){
            int mid = (L+R)/2;
            if(func(mid)){
                ans = mid;
                L = mid + 1;
            }else{
                R = mid -1;
            }
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
                try{
                    st = new StringTokenizer(br.readLine());
                }catch(IOException ex){
                    ex.printStackTrace();
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

        String nextLine(){
            String str = "";
            try{
                str = br.readLine();
            }catch(IOException ex){
                ex.printStackTrace();
            }
            return str;
        }
    }
    
}
