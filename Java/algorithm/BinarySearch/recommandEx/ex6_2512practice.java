package algorithm.BinarySearch.recommandEx;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

import javax.imageio.IIOException;

/*
    [예산- 실버3]
    https://www.acmicpc.net/problem/2512
    
    임의의 상한액 X를 기준으로 지방수 N에 각각 배정했을때 제한된 총 예산을 M을 만족하는가 
    
    지방 수 최대 1만 
    지방 별 요청 최대 10만 이하 
    최대치 > 10^9 = 10억이니 

    직접품인데 두번째 예제에서 무한루프.. // 해결함
*/
public class ex6_2512practice {
    
    static StringBuilder sb = new StringBuilder();
    static MyReader scan = new MyReader();

    static int N,M;
    static int[] a;

    static void input(){
        N = scan.nextInt();
        a = new int[N+1];

        for(int i=1; i<=N;i++){
            a[i] = scan.nextInt();
        }

        M = scan.nextInt();
    }

    static boolean func(int limit){
        
        int sum = 0;
        // 이런 노하우가..
        for(int i=1; i<=N; i++){
            // if(a[i] > money) sum += money;
            // else sum += a[i];
            sum += Math.min(a[i], limit);
        }

        return sum <= M;
    }

    static void pro(){
        int L = 0 , R = 0, ans = 0;
        // 이런 노하우가.
        for(int i=1; i<= N ;i++){
            R = Math.max(R, a[i]);
        }

        while(L<=R){
            int mid = (L+R)/2;
            if(func(mid)){
                ans = mid; 
                L = mid + 1; 
            }else{
                R = mid - 1;
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

        MyReader(){
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

        Long nextLong(){
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
