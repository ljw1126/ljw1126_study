package algorithm.ShortestPath.personal;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
    서강그라운드 - 골드 4 
    https://www.acmicpc.net/problem/14938

    # 입력
    첫째 줄에는 지역의 개수 n (1 ≤ n ≤ 100)과 예은이의 수색범위 m (1 ≤ m ≤ 15), 길의 개수 r (1 ≤ r ≤ 100)이 주어진다.

    둘째 줄에는 n개의 숫자가 차례대로  각 구역에 있는 아이템의 수 t (1 ≤ t ≤ 30)를 알려준다.

    세 번째 줄부터 r+2번째 줄 까지 길 양 끝에 존재하는 지역의 번호 a, b, 그리고 길의 길이 l (1 ≤ l ≤ 15)가 주어진다.

    # 출력
    예은이가 얻을 수 있는 최대 아이템 개수를 출력한다.

    # 예제 입력 
    5 5 4
    5 7 8 2 3
    1 4 5
    5 2 4
    3 2 3
    1 2 3

    # 예제 출력 
    23

    //211221 정답은 구했지만 틀렸다함.. 부등호 문제인지, dist 초기화 문제인지 수정하니 맞다함
*/

import java.util.*;
import java.io.*;

public class ex9_14938 {
    
   
    static MyReader scan = new MyReader();
    
    static int n,m,l;
    static int[] t;
    static int[] dist;
    static ArrayList<Edge>[] edges;
    
    static class Edge{
        int to, weight;
        public Edge(int _to, int _weight){
            this.to = _to;
            this.weight = _weight;
        }
    }

    static class Info{
        int vertex, dist;
        public Info(int _vertex, int _dist){
            this.vertex = _vertex;
            this.dist = _dist;
        }
    }


    static void input(){
        n = scan.nextInt(); 
        m = scan.nextInt(); 
        l = scan.nextInt(); 
        
        t = new int[n+1];
        dist = new int[n+1];
        
        for(int i=1;i<=n;i++) t[i] = scan.nextInt();

        edges = new ArrayList[n+1];
        for(int i=0;i<=n;i++) edges[i] = new ArrayList();

        for(int i=1;i<=l;i++){
            int from = scan.nextInt(),
                to = scan.nextInt(),
                weight = scan.nextInt();

            edges[from].add(new Edge(to, weight));
            edges[to].add(new Edge(from, weight));
        }
    }


    static int func(int start){
        int cal = 0;
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[start] = 0;
        
        PriorityQueue<Info> que = new PriorityQueue<>(Comparator.comparingInt(o->o.dist));
        que.add(new Info(start,0));

        while(!que.isEmpty()){
            Info info = que.poll();

            if(dist[info.vertex] < info.dist) continue;

            for(Edge e : edges[info.vertex]){
                if(info.dist + e.weight > dist[e.to]) continue;

                dist[e.to] = info.dist + e.weight;
                que.add(new Info(e.to, dist[e.to])); 
            }
        }


        for(int i=1; i <=n ; i++){
            if(dist[i] <= m) cal += t[i];
        }

        return cal;
    }   

    static void pro(){
        int ans = 0;
        for(int i=1; i<=n;i++){
            ans = Math.max(ans, func(i));
        }
        System.out.println(ans);
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
