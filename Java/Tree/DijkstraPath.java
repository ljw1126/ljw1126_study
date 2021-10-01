import java.util.*;
// 최단 경로 문제 -단일 출발(single-source), 다익스트라 알고리즘 사용
public class DijkstraPath {

    public HashMap<String,Integer> dijkstraFunc(HashMap<String, ArrayList<Edge>> graph, String start) {
        HashMap<String, Integer> distances = new HashMap<String, Integer>();
        for(String key : graph.keySet()){
            distances.put(key, Integer.MAX_VALUE);
        }
        distances.put(start,0); // 시작점은 0으로 셋팅

        PriorityQueue<Edge> priorityQueue = new PriorityQueue<Edge>();
        priorityQueue.add(new Edge(distances.get(start), start)); // (거리, vertex)

        Edge edgeNode, adjacentNode;
        int currentDistance , weight, distance;
        String currentNode, adjacent;
        ArrayList<Edge> nodeList;

        // 다익스트라 알고리즘 작성 
        while(priorityQueue.size() > 0){
            edgeNode = priorityQueue.poll();  // 우선 순위 큐에서 꺼냄 
            currentDistance = edgeNode.distance; // int 
            currentNode = edgeNode.vertex; // String , 노드임 

            if(distances.get(currentNode) < currentDistance){// 여기 덜 이해 
                continue;
            }

            nodeList = graph.get(currentNode);
            for(int idx = 0 ; idx < nodeList.size() ; idx++){
                adjacentNode = nodeList.get(idx);
                adjacent = adjacentNode.vertex;
                weight = adjacentNode.distance;
                distance = currentDistance + weight;

                if(distance < distances.get(adjacent)){ // 키 값 매개변수에 currentNode 잚못 넣었었음 
                    distances.put(adjacent, distance);
                    priorityQueue.add(new Edge(distance, adjacent));
                }
            }
        }


        return distances;
    }



    public static void main(String[] args) {
        // 그래프 정의 
        HashMap<String, ArrayList<Edge>> graph = new HashMap<String, ArrayList<Edge>>();
        graph.put("A", new ArrayList<Edge>(Arrays.asList(new Edge(8, "B"), new Edge(1, "C"), new Edge(2, "D"))));
        graph.put("B", new ArrayList<Edge>());
        graph.put("C", new ArrayList<Edge>(Arrays.asList(new Edge(5, "B"), new Edge(2, "D"))));
        graph.put("D", new ArrayList<Edge>(Arrays.asList(new Edge(3, "E"), new Edge(5, "F"))));
        graph.put("E", new ArrayList<Edge>(Arrays.asList(new Edge(1, "F"))));
        graph.put("F", new ArrayList<Edge>(Arrays.asList(new Edge(5, "A"))));

        DijkstraPath dp = new DijkstraPath();
        System.out.println(dp.dijkstraFunc(graph, "A"));
        System.out.println(dp.dijkstraFunc(graph, "B")); //B의 경우 다른 노드로 향하는 edge가 없음 
    }
}
