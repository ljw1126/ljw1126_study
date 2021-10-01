public class Edge implements Comparable<Edge>{

    public int distance;
    public String vertex;

    public Edge(int distance , String vertex){
        this.distance = distance;
        this.vertex = vertex;
    }

    public int getDistance() {
        return this.distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }

    public String getVertex() {
        return this.vertex;
    }

    public void setVertex(String vertex) {
        this.vertex = vertex;
    }
    
    @Override
    public int compareTo(Edge edge){
        return this.distance - edge.distance;
    }

    // System.out.println() 에 객체 호출시 아래 toString()함수 호출함
    public String toString(){
        return "vertex: " + this.vertex + ", distance: " + this.distance;
    }
}
