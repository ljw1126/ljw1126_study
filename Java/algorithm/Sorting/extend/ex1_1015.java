package extend;
/*
    https://www.acmicpc.net/problem/1015
    ※ 비내림차순이란, 각각의 원소가 바로 앞에 있는 원소보다 크거나 같을 경우를 말한다. 
    만약 그러한 수열이 여러개라면 사전순으로 앞서는 것을 출력한다.

    # 입력예시 
    3
    2 3 1
    # 출력예시 
    1 2 0 

    # 
    배열 A
    [( num : 2 , idx : 0 ), ( num :3 , idx : 1 ), ( num : 1 , idx : 2 )]

    중간 배열 P      ( A 에 0번 인덱스는 P에 0번 인덱스의 값을 보고 B로 감 P[idx] = b_idx , 그냥 A하고 P로 동일한 인덱스 보고 P 값보고 B로 분기)

    [1, 2, 0]

    배열 B (정렬 완료) 
    [( num : 1 , idx : 2 ), ( num : 2 , idx : 0 ), ( num : 3 , idx : 1 )]
*/
import java.util.*;
import java.io.*;

public class ex1_1015 {

    static FastReader scan = new FastReader();
    static StringBuilder sb = new StringBuilder();

    // Obejct 형식의 경우 TIm Sort라서 뭐를 다르게 한다는데     
    static class Elem implements Comparable<Elem>{

        /*
            @param idx  >> A배열의 idx 위치를 기억하는 변수
            @param num  >> A[idx]의 원래 값 
        */
        public int num, idx; 

        @Override
        public int compareTo(Elem other){
            //TO DO 
            //정렬 조건에 맞게 정렬하기 
            // 1. num의 비내림차순
            //if(num != other.num) return num - other.num;
            // 2. num이 같으면 idx 오름차순
            //return idx - other.idx;
 
            // object sort는 tim sort에 stable함 idx정렬은 의미없음(동일한 숫자라도 순서가 보장됨)
            return num - other.num; 
        }

        public String toString(){
            return "( num : " + num + " , idx : " + idx + " )";
        }

    }
    static int N;
    static Elem[] B; // A 배열 필요없음
    static int[] P;
    
    static void input() {
        N = scan.nextInt();

        P = new int[N];
        B = new Elem[N];
        
        for(int i=0; i<N ; i++) {
            B[i] = new Elem();
            B[i].num = scan.nextInt();
            B[i].idx = i; 
        }

        //B = Arrays.copyOf(A, A.length);
    }
    
    static void pro(){
        // TODO : B배열 정렬하기 
        Arrays.sort(B);
        // TODO : B배열의 값을 이용해서 P 배열 채우기 
        // 힌트 B[P[i]] = A[i] , 도저히 모르겠음
        for(int b_idx = 0 ; b_idx < N ; b_idx++){ // 1 2 0 나와야 하는데 
            P[B[b_idx].idx] = b_idx;      
        }
        // TODO : P배열 출력하기
        for(int i : P) sb.append(i).append(' ');
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
