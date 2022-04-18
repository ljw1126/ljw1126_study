package recommandEx;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
/*
    텀 프로젝트 - 골드 3 
    https://www.acmicpc.net/problem/9466


    자기자신을 선택하거나, 루프를 이루는 경우에만 그룹을 

    # 입력
    첫째 줄에 테스트 케이스의 개수 T가 주어진다. 
    각 테스트 케이스의 첫 줄에는 학생의 수가 정수 n (2 ≤ n ≤ 100,000)으로 주어진다. 
    각 테스트 케이스의 둘째 줄에는 선택된 학생들의 번호가 주어진다. (모든 학생들은 1부터 n까지 번호가 부여된다.)

    # 출력
    각 테스트 케이스마다 한 줄에 출력하고, 각 줄에는 프로젝트 팀에 속하지 못한 학생들의 수를 나타내면 된다.

    # 예제 입력 1
    2
    7
    3 1 3 7 3 4 6      // 인덱스 1번은 3을 가르키고, 인덱스 3번은 3 자기자신 가르킴
    8
    1 2 3 4 5 6 7 8
*/
public class ex12_9466 {
    
    static MyReader scan = new MyReader();
    static StringBuilder sb = new StringBuilder();

    static void pro(){

    }

    static void input(){

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
                try {
                    st = new StringTokenizer(br.readLine());
                } catch (Exception e) {
                    e.printStackTrace();
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
            try {
                str = br.readLine();
            } catch (Exception e) {
                e.printStackTrace();
            }
            return str;
        }

    }

}
