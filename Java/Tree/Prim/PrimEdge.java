public class PrimEdge implements Comparable<PrimEdge> {
    public int weight; 
    public String node1; 
    public String node2;

    public PrimEdge(int weight, String node1, String node2){
        this.weight = weight;
        this.node1 = node1;
        this.node2 = node2; 
    }

    public String toString(){
        return "( weight : "+this.weight+", node1 : "+this.node1+", node2 : "+this.node2+")";
    }

    @Override
    public int compareTo(PrimEdge pedge){   //weight 기준 오름 차순 정렬 
        return this.weight - pedge.weight;
    }
}
