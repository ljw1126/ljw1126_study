package algorithm.Tree.recommandEx;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
/*
    # 입력
    입력은 하나 또는 그 이상의 테스트 케이스로 이루어져 있다.

    각 테스트 케이스는 한 줄로 이루어져 있고, 
    프리오더로 순회한 결과와 인오더로 순회한 결과가 공백으로 구분되어져 있다. 
    두 문자열의 길이는 항상 같으며, 26자를 넘지 않는다.

    # 출력
    각 테스트 케이스에 대해서, 입력으로 주어진 트리를 포스트오더로 순회한 결과를 출력한다.

    # 예제 입력 
    DBACEGF ABCDEFG    프리오더(전위순회) 인오더(중위순회)
    BCAD CBAD

    # 예제 출력 
    ACBFGED
    CDAB

    ※ 참고 
    전위 순회한 결과 (루트) (왼쪽 자식) (오른쪽 자식)
    중위 순회한 결과 (왼쪽 자식) (루트) (오른쪽 자식)
    후위 순회한 결과 (왼쪽 자식) (오른쪽 자식) (루트)


*/
public class ex10_6597wait {
    
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
