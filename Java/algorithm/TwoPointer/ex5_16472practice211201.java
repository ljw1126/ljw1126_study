package algorithm.TwoPointer;

import java.util.*;
import java.io.*;
/**
    고냥이 - 골드3 ( 투 포인터 문제 ) 
    https://www.acmicpc.net/problem/16472

    시간복잡도 O(N) (1 < N ≤ 26)

    입력)
    첫째 줄에는 인식할 수 있는 알파벳의 종류의 최대 개수 N이 입력된다. (1 < N ≤ 26)
    둘째 줄에는 문자열이 주어진다. (1 ≤ 문자열의 길이 ≤ 100,000) 
    단, 문자열에는 알파벳 소문자만이 포함된다.

    출력)
    첫째 줄에 번역기가 인식할 수 있는 문자열의 최대길이를 출력한다. 

    # 예제 입력 
    2           -- 2개 문자만 사용했을때 문자열의 최대길이
    abbcaccba

    # 예제출력 
    4       --- cacc 해당함
 */
public class ex5_16472practice211201 {
    
    //static StringBuilder sb = new StringBuilder();
    //static FastReader scan = new FastReader();

    static int N;
    static int[] cnt = new int[26];
    static String txt,txt2;

    public static void input(){
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        try{
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            st = new StringTokenizer(br.readLine());
            txt = st.nextToken();
        }catch(IOException ex){
            ex.printStackTrace();
        }
    }
    
    public static void pro(){
        int ans = 0, kinds = 0;
        int alpabet;
        for(int L=0,R=0; R < txt.length();R++){
            
            alpabet = txt.charAt(R) - (int)'a';
            if(cnt[alpabet] == 0) kinds++;
            cnt[alpabet]++;

            while(kinds > N){
                alpabet = txt.charAt(L) - (int)'a';
                cnt[alpabet]--;
                if(cnt[alpabet] == 0) kinds--;
                L++;
            }

            if(R-L+1 > ans) ans = R-L+1;
        }

        System.out.println(ans);
    }
    
    public static void main(String[] args) {
        input();
        //System.out.println(txt.length());
        //pro();
        // A => 65 , a => 97
        // Z => 90 , z => 122   아 ! 알파벳이 26개 ,아래 식으로 구하면 a는 0, z는 25
        //System.out.println( "z".charAt(0) - (int)'a');
    }

    static class FastReader {
        BufferedReader br;
        StringTokenizer st;

        public FastReader() {
            br = new BufferedReader(new InputStreamReader(System.in));
        }

        public FastReader(String s) throws FileNotFoundException {
            br = new BufferedReader(new FileReader(new File(s)));
        }

        String next() {
            while (st == null || !st.hasMoreElements()) {
                try {
                    st = new StringTokenizer(br.readLine());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return st.nextToken();
        }

        int nextInt() {
            return Integer.parseInt(next());
        }

        long nextLong() {
            return Long.parseLong(next());
        }

        double nextDouble() {
            return Double.parseDouble(next());
        }

        String nextLine() {
            String str = "";
            try {
                str = br.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return str;
        }
    }
}
