
// 깊이 우선 탐색의 경우 stack으로 구현함
import java.util.*; 

public class DfsSearch {

    public ArrayList<String> DfsFunc(HashMap<String, ArrayList<String>> graph, String startNode){

        // 1. 두 배열을 만듦 , visited는 방문 리스트 , NonVisit은 방문 안한 노드 
        ArrayList<String> visited = new ArrayList<String>();
        ArrayList<String> NonVisit = new ArrayList<String>();
        int last_idx;
        // BFS와 마찬가지로 초기값 넣어줌 
        NonVisit.add(startNode);
        // 2. NonVisit 에 아무것도 없을대가지 반복문 
        // 3. 이때 Stack 방식으로 해야 하므로 인덱스 마지막 값을 구해서 해야 함
        while(NonVisit.size() > 0 ){
            last_idx = NonVisit.size() - 1;
            String Node = NonVisit.remove(last_idx);

            if(!visited.contains(Node)){ // 방문 안했을 경우에만 처리해야 연산 덜함
                visited.add(Node);
                NonVisit.addAll(graph.get(Node));
            }
        }

        return visited;
   }
    public static void main(String[] args) {
        HashMap<String, ArrayList<String>> graph = new HashMap<String, ArrayList<String>>(); // 1.7부터 추가된 타입추론       
        //각 노드(key) 별 인접한 노드(value)표현
        graph.put("A", new ArrayList<String>(Arrays.asList("B","C")));
        graph.put("B", new ArrayList<String>(Arrays.asList("A","D")));
        graph.put("C", new ArrayList<String>(Arrays.asList("A","G","H","I")));
        graph.put("D", new ArrayList<String>(Arrays.asList("B","E","F")));
        graph.put("E", new ArrayList<String>(Arrays.asList("D")));
        graph.put("F", new ArrayList<String>(Arrays.asList("D")));
        graph.put("G", new ArrayList<String>(Arrays.asList("C")));
        graph.put("H", new ArrayList<String>(Arrays.asList("C")));
        graph.put("I", new ArrayList<String>(Arrays.asList("C","J")));
        graph.put("J", new ArrayList<String>(Arrays.asList("I")));

        System.out.println(graph);

        DfsSearch ds = new DfsSearch();
        System.out.println(ds.DfsFunc(graph, "A")); // [A, C, I, J, H, G, B, D, F, E]
        
    }
}
