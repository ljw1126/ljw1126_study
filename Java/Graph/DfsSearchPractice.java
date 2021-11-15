import java.util.*;

public class DfsSearchPractice {

    public ArrayList<String> DfsFunc(HashMap<String, ArrayList<String>> graph, String startNode){
        
        ArrayList<String> visited = new ArrayList<String>();
        ArrayList<String> NonVisit = new ArrayList<String>();

        NonVisit.add(startNode);

        while(NonVisit.size() > 0){
            int last_idx = NonVisit.size()-1;
            String Node = NonVisit.remove(last_idx);

            if(!visited.contains(Node)){
                visited.add(Node);
                NonVisit.addAll(graph.get(Node));
            }
        }

        return visited;
    }


    public static void main(String[] args) {
        HashMap<String, ArrayList<String>> graph = new HashMap<String, ArrayList<String>>();
        //각 노드(key) 별 인접 노드(value) 표현 
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

        DfsSearchPractice ds = new DfsSearchPractice();
        System.out.println(ds.DfsFunc(graph, "A"));
    }
}
