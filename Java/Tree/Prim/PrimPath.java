import java.util.HashMap; 
import java.util.ArrayList; 
import java.util.PriorityQueue; 

public class PrimPath {
    
    public ArrayList<PrimEdge> primFunc(String startNode, ArrayList<PrimEdge> edges){
       
        ArrayList<PrimEdge> mst = new ArrayList<>();
        
        PrimEdge currentEdge, poppedEdge, adjacentEdgeNode;
        ArrayList<PrimEdge> currentEdgeList = new ArrayList<>();
        ArrayList<PrimEdge> candidateEdgeList = new ArrayList<>();
        ArrayList<PrimEdge> adjacentEdgeNodes = new ArrayList<>();
        ArrayList<String> connectedNodes = new ArrayList<>();
        

        HashMap<String, ArrayList<PrimEdge>> adjacentEdges = new HashMap<>(); // 인접한 노드 리스트 
        PriorityQueue<PrimEdge> priorityQueue = new PriorityQueue<>();
        
        // start : 초기화 
        // 단계 1
        for (int idx = 0 ; idx < edges.size() ; idx++){ // 노드 , new ArrayList<PrimEdge>
            currentEdge = edges.get(idx);
            if(!adjacentEdges.containsKey(currentEdge.node1)){
                adjacentEdges.put(currentEdge.node1, new ArrayList<PrimEdge>());
            }

            if(!adjacentEdges.containsKey(currentEdge.node2)){
                adjacentEdges.put(currentEdge.node2, new ArrayList<PrimEdge>());
            }
        }
        // 확인
        System.out.println("확인 adjacentEdges =================");
        for(String key : adjacentEdges.keySet()){
            System.out.println(key + " : "+ adjacentEdges.get(key));
        }

        // 단계 2
        for(int idx = 0 ; idx < edges.size(); idx++){  // 인접한 노드에 대한 정보를 양방향으로 여기서 대입함 
            currentEdge = edges.get(idx);
            currentEdgeList = adjacentEdges.get(currentEdge.node1); // 초기 [] 
            currentEdgeList.add(new PrimEdge(currentEdge.weight, currentEdge.node1, currentEdge.node2));
            currentEdgeList = adjacentEdges.get(currentEdge.node2);
            currentEdgeList.add(new PrimEdge(currentEdge.weight, currentEdge.node2, currentEdge.node1));
        }

        // 확인
        System.out.println("확인 adjacentEdges (value에 currentEdgeList 삽입후 ) =================");
        for(String key : adjacentEdges.keySet()){
            System.out.println(key + " : "+ adjacentEdges.get(key));
        }

        connectedNodes.add(startNode);
        candidateEdgeList = adjacentEdges.getOrDefault(startNode, new ArrayList<PrimEdge>()); // 단계 2에서 인접한 노드를 value에 넣음 
        for(int idx = 0 ; idx < candidateEdgeList.size() ; idx++){
            priorityQueue.add(candidateEdgeList.get(idx)); // 인접한 노드를 우선순위 큐에 저장(정렬 기준에 따라 최소힙)
        }
        
        System.out.println("확인 priorityQueue =================");
        for(PrimEdge pe : priorityQueue){
            System.out.println(pe);
        }

        // end : 초기화 

        /*
            3. 간선 리스트(candidateEdgeList)에서 최소 가중치를 가지는 간선부터 추출해서, ( 우선순위 큐 - 최소힙 활용)
                - 해당 간선에 연결된 인접 정점이 '연결된 노드 집합'에 이미 들어 있다면, 스킵함(cycle 발생을 막기 위함)
                - 해당 간선에 연결된 인접 정점이 '연결된 노드 집합'에 들어 있지 않으면, 해당 간선을 선택하고, 해당 간선 정보를 '최소 신장 트리(mst)'에 삽입
                - 해당 간선에 연결된 인접 정점의 간선들 중, '연결된 노드 집합(connectedNodes)' 에 없는 노드와 연결된 간선들만 간선 리스트(candidateEdgeList) 에 삽입
                    - '연결된 노드 집합(connectedNodes)' 에 있는 노드와 연결된 간선들을 간선 리스트에 삽입해도, 해당 간선은 스킵될 것이기 때문임
                    - 어차피 스킵될 간선을 간선 리스트(candidateEdgeList)에 넣지 않으므로 해서, 간선 리스트(candidateEdgeList)에서 최소 가중치를 가지는 간선부터 추출하기 위한 자료구조 유지를 위한 effort를 줄일 수 있음 (예, 최소힙 구조 사용)
            4. 선택된 간선은 간선 리스트에서 제거
            5. 간선 리스트에 더 이상의 간선이 없을 때까지 3-4번을 반복
        */
        // ArrayList<String> connectedNodes = new ArrayList<>(); 임  , 한 개씩 들어갈때 cycle이 없다고 판단하는 듯 
        int no = 0;
        while(priorityQueue.size() > 0){
            System.out.println(no+".connectedNodes : " + connectedNodes);
            System.out.println(no+".priorityQueue : " + priorityQueue);
            poppedEdge = priorityQueue.poll();
            System.out.println(no+".poppedEdge: " + poppedEdge);
            if(!connectedNodes.contains(poppedEdge.node2)){
                // 해당 edge를 mst에 추가 
                connectedNodes.add(poppedEdge.node2); //처음이라면 A , D 
                mst.add(new PrimEdge(poppedEdge.weight, poppedEdge.node1, poppedEdge.node2));

                adjacentEdgeNodes = adjacentEdges.getOrDefault(poppedEdge.node2, new ArrayList<PrimEdge>()); // 다음 노드에 대한 인접 노드 리스트 가져옴 
                for(int idx = 0 ; idx < adjacentEdgeNodes.size() ; idx++){
                    adjacentEdgeNode = adjacentEdgeNodes.get(idx);
                    if(!connectedNodes.contains(adjacentEdgeNode.node2)){ // 연결 노드 중 가중치가 작은거부터 먼저 확인하겠지 
                        priorityQueue.add(adjacentEdgeNode);
                    }
                }
            }
            no ++;
        }


        System.out.println("종료 ======================================================================");
        // 최종 결과 
        return mst;
    }


    public static void main(String[] args) {
        ArrayList<PrimEdge> myEdges = new ArrayList<>(); 
        myEdges.add(new PrimEdge(7, "A", "B"));
        myEdges.add(new PrimEdge(5, "A", "D"));
        myEdges.add(new PrimEdge(8, "B", "C"));
        myEdges.add(new PrimEdge(9, "B", "D"));
        myEdges.add(new PrimEdge(7, "B", "E"));
        myEdges.add(new PrimEdge(5, "C", "E"));
        myEdges.add(new PrimEdge(7, "D", "E"));
        myEdges.add(new PrimEdge(6, "D", "F"));
        myEdges.add(new PrimEdge(8, "E", "F"));
        myEdges.add(new PrimEdge(9, "E", "G"));
        myEdges.add(new PrimEdge(11, "F", "G")); 
        
        for(PrimEdge pe : myEdges){
            System.out.println(pe);
        }

        PrimPath pp = new PrimPath();
        System.out.println(pp.primFunc("A", myEdges));
    }
}
