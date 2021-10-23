package extend;
/*

    - ※ 해쉬맵 자료구조를 이용해서 풀 수도 있음 
    - 정렬을 이용해서 풀도록 함 

    # 예제 입력 1 
    5
    1
    2
    1
    2
    1

    # 예제 출력 1
    1

    # 예제 입력 2 
    6
    1
    2
    1
    2
    1
    2

    # 예제 출력 2 
    1
*/
import java.util.*;
import java.io.*;

public class ex2_11652 {
    static FastReader scan = new FastReader();
    static StringBuilder sb = new StringBuilder();

    static long[] a;
    static int N;

    static void input(){
        N = scan.nextInt();
        a = new long[N]; 

        for(int i=1; i<=N; i++) a[i] = scan.nextLong();
        
    }

    static void pro(){
        // Sort 정렬하기 
        Arrays.sort(a , 1, N+1);  // 1 ~ N + 1 미만 인덱스까지 정렬
        // mode:최빈값, modeCnt: 최빈값 등장 횟수, curCnt : 현재 값(a[1])의 등장횟수
        long mode = a[1];
        int modeCnt = 1, curCnt = 1; 

        // Todo
        // 2번 원소부터 차례대로 보면서, 같은 숫자가 이어서 나오고 있는지, 새로운 숫자가 나왔는 지를 판단하여
        // curCnt를 갱신해주고, 최빈값을 갱신하는 작업 
        for(int i= 2 ; i <= N ; i++){
            // 이전값과 동일한 경우
            if( a[i-1] == a[i]){
                curCnt += 1;
            }else{
                curCnt = 1; 
            } 

            if(curCnt > modeCnt) {
                modeCnt = curCnt; 
                mode = a[i]; 
            }

            System.out.printf("curCnt: %d modeCnt: %d mode : %d",curCnt,modeCnt,mode);
            System.out.println();
        }
        // 정답 출력하기 (= mode 값 출력해주면 됨)
        System.out.println(mode);

    }

    public static void main(String[] args) {
        input();
        System.out.println(Arrays.toString(a));
        pro();
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
