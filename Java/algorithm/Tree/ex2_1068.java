package algorithm.Tree;
/*
    트리 
    https://www.acmicpc.net/problem/1068


    큰 문제의 정답을 작은 문제 풀이 통해 구한다. 
    leaf[x] := x를 root로 하는 subtree에 있는 단말 노드의 개수 
    - x가 단말 노드인 경우 => leaf[x] = 1
    - 아닌 경우 -> x의 자식들에 대해 leaf 를 먼저 계산한다면?
                -> leaf[x] = Σleaf[x의 자식 노드들]

    * 단말 노드의 개수를 세는 법 ( leaf[x] 를 계산하는 방법 )
        - root에서 dfs를 한다면?
            어떤 노드 x에서 자식 노드 y에 대한 탐색을 끝내고 돌아오면 leaf[y]값이 계산되어
            있으니, leaf[x] 에 leaf[y]를 누적해주면 됨 
        - DFS(x) : x의 subtree에 대해 leaf[x]를 계산해주는 함수
            26분 강의 그림 참고 
        - 인접리스트 쓴다면 O(V+E) , DFS 사용하는게 좋다함 
*/
import java.util.*;
import java.io.*;

public class ex2_1068 {
    
    static FastReader scan = new FastReader();
    static StringBuilder sb = new StringBuilder();

    static int n, root, erased;
    static ArrayList<Integer>[] child;
    static int[] leaf;
    // 입력값에서 바로 부모 정보를 알려줌 
    /*
        # 입력 예시 
        5
        -1 0 0 1 1
        2
        # 출력 예시 
        2

        # 입력 예시 
        5
        -1 0 0 1 1
        1
        # 출력 예시 
        1
    */
    static void input() {
        n = scan.nextInt();
        /* TODO */
        child = new ArrayList[n];
        leaf = new int[n]; // 초기 선언 못함
        for(int i = 0 ; i < n ; i++) child[i] = new ArrayList<>(); 
        for(int i= 0 ; i < n ; i ++){ // 인접 리스트로 트리를 표현못함 
            int par = scan.nextInt();
            if( par == -1){
                root = i ; 
                continue;
            }
            child[par].add(i);
        }
        erased = scan.nextInt(); 
    }

    // dfs(x, par) := 정점 x 의 부모가 par 였고, Subtree(x) 의 leaf 개수를 세주는 함수
    static void dfs(int x, int par) {
        /* TODO */
        if(child[x].isEmpty()){ // ArrayList가 비어 있다 = 자식이 없으니 단말노드
            leaf[x]++; // leaf[x] = 1;
        }

        for(int y : child[x]){
            if(y == par) continue;
            dfs(y,x);
            leaf[x] += leaf[y];
        }

    }

    static void pro() {
        // erased와 그의 부모 사이의 연결을 끊어주기
        /* TODO */
        for(int i=0 ; i < n ; i++){
            if(child[i].contains(erased)){
                child[i].remove(child[i].indexOf(erased));    // *.indexOf( "찾을 특정 문자" , "시작할 위치" )
            }
        }

        // erased 가 root 인 예외 처리하기
        /* TODO */
        if(root != erased) dfs(root , -1); 
        
        // 정답 출력하기
        /* TODO */
        System.out.println(leaf[root]);
    }

    public static void main(String[] args) {
        input();
        pro();
        System.out.println(Arrays.toString(leaf));
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
