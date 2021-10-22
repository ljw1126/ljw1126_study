package algorithm.Tree.recommandEx;

import java.util.*;
import java.io.*;
/*
    https://www.acmicpc.net/problem/14267

    영선회사에는 매우 좋은 문화가 있는데, 
    바로 상사가 직속 부하를 칭찬하면 그 부하가 부하의 직속 부하를 
    연쇄적으로 칭찬하는 내리 칭찬이 있다. 
    즉, 상사가 한 직속 부하를 칭찬하면 그 부하의 모든 부하들이 칭찬을 받는다.

    모든 칭찬에는 칭찬의 정도를 의미하는 수치가 있는데, 
    이 수치 또한 부하들에게 똑같이 칭찬 받는다.

    직속 상사와 직속 부하관계에 대해 주어지고, 
    칭찬에 대한 정보가 주어질 때, 각자 얼마의 칭찬을 받았는지 출력하시오.

    - 트리 문제 이고 , dfs를 사용 
    - 시간복잡도 계산은.. 
    # 예제 입력 
    5 3               -- n = 5명의 직원과 m = 3 번의 칭찬이 있다 .
    -1 1 2 3 4        -- 트리구조로 직속 상사와 부하관계 나타냄
    2 2               -- 칭찬 시작(m번) 하위 한테도 칭찬이 넘어감
    3 4
    5 6

    # 예제 출력
    0 2 6 6 12


    211022. 문제 풀었는데 시간초과 발생..
            해설 답안봤을대 칭찬 점수를 미리 할당하고 dfs 돌면서 누적시킴
            O(n+m)

*/
public class ex9_14267test {
    
    static FastReader scan = new FastReader();
    static StringBuilder sb = new StringBuilder();

    static int n,m,root;
    static ArrayList<Integer>[] employees;
    static int[] scores; 

    public static void input(){
        n = scan.nextInt(); // 인원 수 
        m = scan.nextInt(); // 칭찬 횟수 

        scores = new int[n+1];
        employees = new ArrayList[n+1];
        for(int i = 0 ; i < n+1 ; i++ ) employees[i] = new ArrayList<>();
        
        for(int i = 1 ; i <= n ; i++){
            int input = scan.nextInt();
            
            if(input == -1) continue;

            employees[input].add(i);
        }

        // 칭찬 어떻게 처리? -- 시간초과 발생
        // for(int i = 0 ; i < m ; i++) recommand[i] = new ArrayList<>();
        // for(int i = 0 ; i < m ; i++){
        //     int a = scan.nextInt() , b = scan.nextInt();
        //     recommand[i].add(a);
        //     recommand[i].add(b);
        // }
        // 애초에 칭찬을 누적시키고 시작 
        for(int i=1; i<=m; i++){
            int x = scan.nextInt() , v = scan.nextInt();
            scores[x] += v;
        }
        
    }

    public static void pro(){
        dfs(1);
        for(int i=1; i < n+1 ; i++) sb.append(scores[i]).append(' ');
        System.out.println(sb);
    }

    public static void dfs(int x){ // x번에게 score만큼 칭찬
        for(int y : employees[x]){
            scores[y] += scores[x];
            dfs(y);
        }
    }

    public static void main(String[] args) {
        input();
        pro();
        //for(ArrayList<Integer> i : employees) System.out.println(i.toString());
        //System.out.println(Arrays.toString(scores));
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
