package extend;

import java.util.*;
import java.io.*;
/*
    M가 숫자를 입력받아 N개로 구성된 문자를 구하는데 
    - (조건)최소 한개의 모음(a,e,i,o,u  -- 아에이오우)과 최소 두개의 자음으로 구성 되야 함 
    
    ※ 조건을 잘 찾자 !! 
*/
public class ex4_practice {
    static int N,M;
    static char[] chars;
    static int[] selected;

    static StringBuilder sb = new StringBuilder();

    static void input(){
        FastReader scan = new FastReader();
        N = scan.nextInt();  // 선택해야 하는 문자 갯수
        M = scan.nextInt();  // 주어진 문자 개수
        
        chars = new char[M+1];
        selected = new int[N+1]; 
       
        String[] tokens = scan.nextLine().split(" ");
       
        for(int i = 1; i <= M ; i++){
            chars[i] = tokens[i-1].charAt(0);
        }
        
        Arrays.sort(chars, 1, M+1); // 정렬한다는 생각은 맞음 [, a, c, i, s, t, w]
       
    }

    static boolean isVowel(char x){ // vowel. 모음 ( ㅏ , ㅔ , ㅣ , ㅗ , ㅜ .. )
        return x == 'a' || x == 'e' || x == 'i' || x == 'o' || x == 'u';
    }

    static void rec_func(int k){
        // 조건, 최소 한개의 모음과 / 최소 두개의 자음으로 구성되야 함 
        if(k == N+1){ // vowel 모음(a,e,i,o,u) , consonant 자음(ㄱㄴㄷㄹ...)
            int vowel = 0, consonant = 0;
            // 모음 1개 이상 , 자음 2개 이상인지 검사
            for(int i = 1; i <= N; i++){
                if(isVowel(chars[selected[i]])) vowel++;
                else consonant++;
            }

            if(vowel >=1 && consonant >= 2){
                for(int i=1; i <= N; i++) sb.append(chars[selected[i]]);
                sb.append('\n');
            }
        }else{
            for(int cand = selected[k-1] + 1 ; cand <= M ; cand++){
                selected[k] = cand;
                rec_func(k+1);
                selected[k] = 0 ;
            }
        }
    }

    public static void main(String[] args) {
        // 1. 입력값 , 초기화 
        input();
        //2. 함수 실행 
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
