import java.util.*;

public class BfsSearchPractice {

    public ArrayList<String> bfsFunc(HashMap<String, ArrayList<String>> graph, String startNode){

        ArrayList<String> visited = new ArrayList<>();
        ArrayList<String> NonVisit = new ArrayList<>();

        NonVisit.add(startNode);

        while(NonVisit.size() > 0){
            String Node = NonVisit.remove(0);

            if(!visited.contains(Node)){
                visited.add(Node);
                NonVisit.addAll(graph.get(Node));
            }
        }

        return visited;
    }

    public static void main(String[] args) {
        HashMap<String,ArrayList<String>> graph = new HashMap<String,ArrayList<String>>();

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
        //예상 A B C D G H I E F J 

        BfsSearchPractice bfs = new BfsSearchPractice();
        System.out.println(bfs.bfsFunc(graph, "A")); // 결과 [A, B, C, D, G, H, I, E, F, J]
    }
}
