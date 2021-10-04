/*
    - kruskal algorithm에 사용
    - 강의 클래스명은 Edge 였는데 중복이라 변경함 
*/
public class Node implements Comparable<Node>{

    public int weight; // 두 노드간 가중치
    public String nodeV;
    public String nodeU;

    public Node(int weight, String nodeV, String nodeU){
        this.weight = weight;
        this.nodeV = nodeV;
        this.nodeU = nodeU;        
    }

    public String toString(){
        return "(" +this.weight+", "+ this.nodeV + ", "+ this.nodeU+ ")";
    }

    @Override
    public int compareTo(Node o) { // 정렬 기준 
        return this.weight - o.weight;
    }
    
}
