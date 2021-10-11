

/**
 * BfsSearch
 * - 큐로 구현
 */

import java.util.*;

public class BfsSearch {

    public ArrayList<String> BfsFunc(HashMap<String,ArrayList<String>> graph, String startNode){
        // 1. visited 리스트와 NonVisit 리스트를 만듦 
        ArrayList<String> visited = new ArrayList<String>();
        ArrayList<String> NonVisit = new ArrayList<String>();
        
        // 2. startNode를 NonVisit 리스트에 넣어서 초기화 
        NonVisit.add(startNode);

        // 3. NonVisit.size() > 0 반복문 반복 , 
        // 4. NonVisit.get(0); 에 노드를 visited에 넣고 , 해당 노드의 자식 노드를 NonVisit 리스트에 넣음 
        // Queue의 경우 FIFO  
        while(NonVisit.size() > 0 ){
            String Node = NonVisit.remove(0); 

            if(!visited.contains(Node)){
                visited.add(Node);   
                NonVisit.addAll(graph.get(Node));     // 방문 안했을 경우에만 넣어줘야 함
            }
        }

        return visited;
    }


    public static void main(String[] args) {
        // graph 를 아래와 같은 형태로 구현함

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
        
        BfsSearch bs = new BfsSearch();

        System.out.println(bs.BfsFunc(graph, "A")); // [A, B, C, D, G, H, I, E, F, J]
    }   
}