
package extend;

import java.util.*;
import java.io.*;

public class ex3_15970 {
    static int N;
    static ArrayList<Integer>[] a;

    static FastReader scan = new FastReader();

    static void input(){
        N = scan.nextInt();
        a = new ArrayList[N+1];
        for(int color = 1; color <= N; color++){
            a[color] = new ArrayList<Integer>();
        }
        for(int i=1; i<=N; i++){
            int coord, color; 
            coord = scan.nextInt();
            color = scan.nextInt();
            // Todo : color인 색깔의 점이 coord에 놓여있음 
            a[color].add(coord);
        }
    }


    static int toLeft(int color , int idx ){
        if( idx == 0 )  // 왼쪽에 더 이상 점이 없는 상태
            return Integer.MAX_VALUE;
        else
            return a[color].get(idx) - a[color].get(idx-1);
    }

    static int toRight(int color , int idx){
        if( idx+1 == a[color].size() ) 
            return Integer.MAX_VALUE;
        else 
            return a[color].get(idx+1) - a[color].get(idx);
    }

    static void pro(){
        // Todo : 색깔별로 정렬하기 
        for(int color = 1; color <= N ; color++){
            Collections.sort(a[color]);
        }

        int ans = 0;
        for(int color = 1; color <= N ; color++){
            //Todo : 색깔 별로, 각 점마다 가장 까가운 점 찾아주기
            if( a[color].size() > 0 ){
                // 인덱스 기준 left, right 거리값 구해서 작은 값을 더해줌 
                for(int i = 0; i < a[color].size() ; i++){
                    int left_distance = toLeft(color, i);
                    int right_distance = toRight(color, i);
                    if(left_distance > right_distance) 
                        ans += right_distance; 
                    else 
                        ans += left_distance; 
                }
            }
            
        }

        // 정답 출력하기 
        System.out.println(ans);
    }

    public static void main(String[] args) {
        input();
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
