
import java.util.*;
import java.io.*;
/*
    https://www.acmicpc.net/problem/10825
    # 정렬 순서 
    - 국어 점수가 감소하는 순서로
    - 국어 점수가 같으면 영어 점수가 증가하는 순서로
    - 국어 점수와 영어 점수가 같으면 수학 점수가 감소하는 순서로
    - 모든 점수가 같으면 이름이 사전 순으로 증가하는 순서로 (단, 아스키 코드에서 대문자는 소문자보다 작으므로 사전순으로 앞에 온다.)
    
    # 입력 
    첫째 줄에 도현이네 반의 학생의 수 N (1 ≤ N ≤ 100,000)이 주어진다. 
    둘째 줄부터 한 줄에 하나씩 각 학생의 이름, 국어, 영어, 수학 점수가 공백으로 구분해 주어진다

    # 입력예시 
    12
    Junkyu 50 60 100
    Sangkeun 80 60 50
    Sunyoung 80 70 100
    Soong 50 60 90
    Haebin 50 60 100
    Kangsoo 60 80 100
    Donghyuk 80 60 100
    Sei 70 70 70
    Wonseob 70 70 90
    Sanghyun 70 70 80
    nsj 80 80 80
    Taewhan 50 60 90

    # 출력예시 
    Donghyuk
    Sangkeun
    Sunyoung
    nsj
    Wonseob
    Sanghyun
    Sei
    Kangsoo
    Haebin
    Junkyu
    Soong
    Taewhan
*/
public class ex_10825 {
    
    static FastReader scan = new FastReader();
    static StringBuilder sb = new StringBuilder(); // 나는 StringBuffer()를 주로 썻네

    static class Elem implements Comparable<Elem>{
        public String name; 
        public int korean, english, math; 

        @Override
        public int compareTo(Elem other){
            // Todo 
            // 국어, 영어, 수학, 이름 값을 가지고 정렬 기준 정의하기
            // 국어 점수 내림차순 
            if(korean != other.korean) return other.korean - korean; 
            // 영어 점수 오름차순 
            if(english != other.english) return english - other.english;
            // 수학 점수 내림차순
            if(math != other.math) return other.math - math;
            return name.compareTo(other.name);
        }
    }

    static int N;
    static Elem[] a;

    static void input(){
        N = scan.nextInt();
        a = new Elem[N];
        for(int i=0; i< N; i++){
            a[i] = new Elem();
            a[i].name = scan.next();
            a[i].korean = scan.nextInt();
            a[i].english = scan.nextInt();
            a[i].math = scan.nextInt();
        }
    }
    
    static void pro(){
        //기준을 통해 정렬하기 
        Arrays.sort(a);

        for(int i= 0 ; i<a.length ; i++){
            sb.append(a[i].name).append('\n');
        }
        System.out.println(sb.toString());
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
