package algorithm.TopologicalSort.recommand;
/*
    영우는 사기꾼? - 골드 4 
    https://www.acmicpc.net/problem/14676

    2,3 번 건물은 1번이 있어야 건설 할 수 있고 
    4번 건물을 2,3번이 있어야 건설 할 수 있음 
    그런데 1번이 없어도 2,3번이 있다면 4번 건설 할수 있음

    N : 건물 종류 수 
    M : 건물 사이 관계수 
    K : 게임 정보의 개수 

    (1 ≤ N, M, K ≤ 100,000)
    M줄 만큼
         X Y --> X를 건설해야 Y를 건설 할 수 있다는 뜻 
    K 줄에 걸려 게임정보 주어짐 
        1 a(영우가 a번 건물을 1개 건설함)
        2 a(영우의 a번 건물이 1개 파괴됨)

*/

import java.util.*;
import java.io.*;

public class ex3_14676practice {
    
    static MyReader scan = new MyReader();
    static StringBuilder sb = new StringBuilder();

    static int N, M, K;
    static ArrayList<Integer>[] adj;
    static int[] indegree, cnt, statification;

    
    // 자식은 부모가 건설되어 있는걸 어떻게 알지?
    // 자식은 부모가 파괴된걸 어떻게 알지?
    static void pro(){
        boolean abnormal = false;
        while (K-- > 0) {
            int t = scan.nextInt(), x = scan.nextInt();
            if (t == 1){
                // x 의 선행 건물들이 모두 지어졌는 지 확인하기
                if(statification[x] < indegree[x]){
                    abnormal = true; 
                    break;
                } 
                
                // x 의 개수 증가
                cnt[x]++;

                // x 가 처음 지어진 것이라면 x가 영향을 주는 건물들에 "너희의 선행 건물 중 하나가 처음 지어졌어" 라고 알려주기
                if(cnt[x] == 1)
                    for(int y : adj[x]){
                        statification[y]++;
                    }
            }
            else{
                // x 라는 건물이 한 개 이상 지어져 있는 지 확인하기
                if(cnt[x] == 0) {
                    abnormal = true; 
                    break;
                }
                
                // x 의 개수 감소
                cnt[x]--;

                // x 가 더 이상 남아있지 않다면, "너희의 선행 건물 중 하나가 사라졌어" 라고 알려주기
                if(cnt[x] == 0){
                    for(int y : adj[x]){
                        statification[y]--;
                    }
                }
            }
        }
        if (abnormal) System.out.println("Lier!");
	    else System.out.println("King-God-Emperor");
    }

    static void input(){
        N = scan.nextInt(); // 건물 종류 수
        M = scan.nextInt(); // 건물 사이 관계 수 
        K = scan.nextInt(); // 게임 정보의 개수 ( 명령어 1  a = a를 건설, 2 a = a를 파괴)

        indegree = new int[N+1];
        cnt = new int[N+1]; //+추가 
        statification = new int[N+1]; //+추가 
        adj = new ArrayList[N+1];
        
        for(int i=1;i<=N;i++) adj[i] = new ArrayList();

        for(int j=1;j<=M;j++){
            int x = scan.nextInt(), y = scan.nextInt();
            adj[x].add(y);
            indegree[y]++; // 이거 빼먹음
        }
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

        String nextLing(){
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
