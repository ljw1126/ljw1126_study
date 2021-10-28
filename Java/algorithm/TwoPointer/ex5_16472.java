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
public class ex5_16472 {

    static FastReader scan = new FastReader();
    static StringBuilder sb = new StringBuilder();

    static int N, kind;
    static String A;
    static int[] cnt;

    static void input() {
        N = scan.nextInt();
        A = scan.nextLine();
        cnt = new int[26];
    }

    static void add(char x) {  // x 라는 알파벳 추가
        cnt[x-'a']++;
        if(cnt[x-'a'] == 1) kind++;
    }

    static void erase(char x) {  // x 라는 알파벳 제거
        cnt[x-'a']--;
        if(cnt[x-'a'] == 0) kind--;
    }

    static void pro() {
        int len = A.length(), ans = 0;
        for (int R = 0, L = 0; R < len; R++) {
            // R 번째 문자를 오른쪽에 추가       
            add(A.charAt(R));
            // 불가능하면, 가능할 때까지 L을 이동
            // while(true){
                
            //     for(int i=0 ; i< 26;i++){
            //         if(cnt[i] !=0) kind++;
            //     }

            //     if(kind <= N) break;
                
            //     erase(A.charAt(L));
            //     L++;
            // }
            // 테크닉 추가 
            while(kind > N){
                erase(A.charAt(L++));
            }

            // 정답 갱신
            ans = Math.max(ans, R-L+1);
        }
        System.out.println(ans);
    }

    public static void main(String[] args) {
        input();
        pro();
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