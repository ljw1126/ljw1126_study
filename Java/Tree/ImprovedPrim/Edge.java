// Edge(간선 가중치) 기준으로 계산하기 보다 Vertex(노드) 로 계산하는 알고리즘 
// 최단 경로 알고리즘 처럼 우선순위 큐 와 결과 배열 활용 
public class Edge implements Comparable<Edge>{
    public String node; // 인접한 노드 명 
    public int weight;  // 인접한 노드에 대한 가중치 

    public Edge(String node, int weight){
        this.weight = weight;
        this.node = node; 
    }

    public String toString(){
        return "( weight : "+this.weight+", node: "+this.node +")";
    }

    @Override
    public int compareTo(Edge edge){
        return this.weight - edge.weight;
    }
}
