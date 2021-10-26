package algorithm.BinarySearch.recommandEx;

import java.util.*;
import java.io.*;
/*
    [어두운 굴다리-실버5]        --- 난이도 실버 5 아닌거 같음/조건식 이해 못함(211026)
    https://www.acmicpc.net/problem/17266

    입력)
    첫 번째 줄에 굴다리의 길이 N 이 주어진다. (1 ≤ N ≤ 100,000)
    두 번째 줄에 가로등의 개수 M 이 주어진다. (1 ≤ M ≤ N)
    다음 줄에 M 개의 설치할 수 있는 가로등의 위치 x 가 주어진다. (0 ≤ x ≤ N)
    가로등의 위치 x는 오름차순으로 입력받으며 가로등의 위치는 중복되지 않으며, 정수이다.

    출력)
    굴다리의 길이 N을 모두 비추기 위한 가로등의 최소 높이를 출력한다
    <-> 임의 높이 H로 가로등을 정했을떄 굴다리 길이 N을 모두 비출 수 있는가?

    # 예제입력 1
    5
    2
    2 4

    # 예제출력 1
    2

    # 예제입력 2
    3
    1
    0

    # 예제출력 0

*/
public class ex10_17266 {
    static FastReader scan = new FastReader();
    static StringBuilder sb = new StringBuilder();

    static int N,M;
    static int[] A;

    static void input() {
        N = scan.nextInt(); // 총 다리 길이 
        M = scan.nextInt(); // 설치 가로등 개수
        A = new int[N + 1]; // 설치가로등 위치
        for (int i = 1; i <= M; i++) {
            A[i] = scan.nextInt();
        }
    }
    // 임의 높이 H가 주어졌을때 총 다리 길이 N을 커버 가능한가?
    // 제출하면 틀렸다함
    // static boolean determination(int H){
    //     int min = Integer.MAX_VALUE, max = Integer.MIN_VALUE;
    //     for(int i=1; i <= M ; i++){
    //         if(min > A[i]-H) min = A[i]-H;
    //         if(max < A[i]+H) max = A[i]+H;
    //     }

    //     if(min < 0 ) min = 0;

    //     return (max - min) >= N;
    // }

    //해설답안  -- 도대체 이걸 어떻게 생각한거지??
    static boolean determination(int height){
        int last = 0; //밝혀진 마지막 위치, 이게 왜 나와??
        for(int i=1; i<=M;i++){
            // 이 공식은 또 뭐야?
            if(A[i] - last <= height){
                last = A[i] + height;
            }else{
                return false; 
            }
        }
        return last >= N;
    }

    static void pro() {
        int L=0, R = N, ans = N;
        
        //Arrays.sort(A, 1, M+1);// 이분탐색으 ㄹ위한 정렬 뺴먹음 

        while(L <= R){
            int mid =(L+R)/2;
            if(determination(mid)){
                ans = mid;
                R = mid -1;
            }else{
                L = mid +1;
            }
        }

        System.out.println(ans);
    }

    public static void main(String[] args) {
        input();
        pro();
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
