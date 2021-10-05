
import java.util.HashMap;
import java.util.ArrayList; 
import java.util.PriorityQueue; 

public class ImprovedPrim {
        // 마치 다익스르타 알고리즘 처럼 구현 
        public ArrayList<Path> ImprovedPrimFunc(HashMap<String, HashMap<String, Integer>> graph, String startNode){
            ArrayList<Path> mst = new ArrayList<>();
            Integer totalWeight = 0 ; //총 간성 가중치 합 (확인용)
            Edge edgeObject, poppedEdge, linkedEdge;
            HashMap<String,String> mstPath = new HashMap<>();
            HashMap<String,Edge> keysObjects = new HashMap<>();
            PriorityQueue<Edge> keys = new PriorityQueue<>();
            HashMap<String,Integer> linkedEdges = new HashMap<>(); // 인접한 노드 담을 객체 

            // 초기화 
            for(String key : graph.keySet()){
                if(key == startNode){
                    edgeObject = new Edge(key, 0);
                    mstPath.put(key,key);
                }else{
                    edgeObject = new Edge(key, Integer.MAX_VALUE);
                    mstPath.put(key,null);
                }

                keys.add(edgeObject);
                keysObjects.put(key, edgeObject);
            }
            System.out.println(" 초기화 PriorityQueue<Edge> keys ==========================");
            System.out.println(keys);
            System.out.println(" 초기화 HashMap<String,Edge> keysObjects ==========================");
            System.out.println(keysObjects);
            System.out.println(" 초기화 HashMap<String,String> mstPath ==========================");
            System.out.println(mstPath);

            // 211005 이해 덜됨 
            while(keys.size() > 0){ 
                poppedEdge = keys.poll();
                keysObjects.remove(poppedEdge.node);

                mst.add(new Path( mstPath.get(poppedEdge.node) , poppedEdge.node, poppedEdge.weight));
                totalWeight += poppedEdge.weight;

                linkedEdges = graph.get(poppedEdge.node);
                
                //인접 노드 순회 
                for(String adjacent : linkedEdges.keySet()){ 
                    if(keysObjects.containsKey(adjacent)){
                        linkedEdge = keysObjects.get(adjacent);

                        if(linkedEdges.get(adjacent) < linkedEdge.weight){
                            linkedEdge.weight = linkedEdges.get(adjacent);
                            mstPath.put(adjacent, poppedEdge.node);

                            keys.remove(linkedEdge);
                            keys.add(linkedEdge);
                        }
                    }
                }
            }


            System.out.println("종료 ==========================================");
            // 종료 
            return mst;
        }
        public static void main(String[] args) {
            // 노드 , 인접 노드 정의
            HashMap<String, HashMap<String, Integer>> mygraph = new HashMap<String, HashMap<String, Integer>>();

            HashMap<String, Integer> edges;
            edges = new HashMap<String, Integer>();
            edges.put("B", 7);
            edges.put("D", 5);
            mygraph.put("A", edges);

            edges = new HashMap<String, Integer>();
            edges.put("A", 7);
            edges.put("D", 9);
            edges.put("C", 8);
            edges.put("E", 7);
            mygraph.put("B", edges);

            edges = new HashMap<String, Integer>();
            edges.put("B", 8);
            edges.put("E", 5);
            mygraph.put("C", edges);

            edges = new HashMap<String, Integer>();
            edges.put("A", 5);
            edges.put("B", 9);
            edges.put("E", 7);
            edges.put("F", 6);
            mygraph.put("D", edges);

            edges = new HashMap<String, Integer>();
            edges.put("B", 7);
            edges.put("C", 5);
            edges.put("D", 7);
            edges.put("F", 8);
            edges.put("G", 9);
            mygraph.put("E", edges);

            edges = new HashMap<String, Integer>();
            edges.put("D", 6);
            edges.put("E", 8);
            edges.put("G", 11);
            mygraph.put("F", edges);

            edges = new HashMap<String, Integer>();
            edges.put("E", 9);
            edges.put("F", 11);
            mygraph.put("G", edges);

            /*
                {
                    A={B=7, D=5}, B={A=7, C=8, D=9, E=7}, 
                    C={B=8, E=5}, 
                    D={A=5, B=9, E=7, F=6}, 
                    E={B=7, C=5, D=7, F=8, G=9}, 
                    F={D=6, E=8, G=11}, 
                    G={E=9, F=11}
                }
            */

            ImprovedPrim iprim = new ImprovedPrim();
            System.out.println(iprim.ImprovedPrimFunc(mygraph, "A"));

        }
}
