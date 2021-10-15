import java.util.*;
import java.io.*;

public class ex_10825template {
    
    static class Elem implements Comparable<Elem>{
        public String name; 
        public int korean, english, math; 

        @Override
        public int compareTo(Elem other){
            // Todo 
            // 국어, 영어, 수학, 이름 값을 가지고 정렬 기준 정의하기
            
            return 0;
        }
    }

    public static void main(String[] args) {
        
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
