
import java.util.Collections;
import java.util.ArrayList; 
import java.util.HashMap; 
import java.util.Arrays;

public class KruskalPath {


    static HashMap<String, String> parent = new HashMap<String,String>();
    static HashMap<String, Integer> rank = new HashMap<String,Integer>();

    // 초기화 함수 
    public static void makeSet(String node){
        parent.put(node, node);
        rank.put(node, 0);
    }

    // 부모 노드 찾기 
    public static String find(String node){
        // path compression 기법 
        if(parent.get(node) != node){
            parent.put(node, find(parent.get(node)));// 최상위 루트 노드로 자식 노드들 부모 노드가 변경 , 랭크가 압축됨
        }

        return parent.get(node); // 루트 노드 리턴
    }

    // 그룹(집합, 트리) 합치기 
    public static void union(String nodeV , String nodeU){
        String root1 = find(nodeV);
        String root2 = find(nodeU);

        // union-by-rank 기법
        // 높이가 다를때는 높이가 큰쪽에 붙여라 
        // 높이가 같을때는 한쪽을 높여 준 다음에 붙여라  
        if(rank.get(root1) > rank.get(root2)){
            parent.put(root2, root1); //root2의 부모를 root1로 하여 붙임 
        }else{
            parent.put(root1, root2); // root1의 부모를 root2로 
            if(rank.get(root1) == rank.get(root2)){
                rank.put(root2, rank.get(root2) + 1);
            }
        }
    }


    public static void main(String[] args) {
        // 전체 노드 정보
        //ArrayList<String> vetices = new ArrayList<String>(Arrays.asList("A","B","C","D","E","F","G"));
        ArrayList<String> vetices = new ArrayList<String>(Arrays.asList("1","2","3","4"));
        // 노드 별 간선 가중치 정보
        ArrayList<Node> nodes = new ArrayList<Node>();
        // nodes.add(new Node(7, "A", "B"));
        // nodes.add(new Node(5, "A", "D"));

        // nodes.add(new Node(7, "B", "A"));
        // nodes.add(new Node(8, "B", "C"));
        // nodes.add(new Node(9, "B", "D"));
        // nodes.add(new Node(7, "B", "E"));

        // nodes.add(new Node(8, "C", "B"));
        // nodes.add(new Node(5, "C", "E"));

        // nodes.add(new Node(5, "D", "A"));
        // nodes.add(new Node(9, "D", "B"));
        // nodes.add(new Node(7, "D", "E"));
        // nodes.add(new Node(6, "D", "F"));

        // nodes.add(new Node(7, "E", "B"));
        // nodes.add(new Node(5, "E", "C"));
        // nodes.add(new Node(7, "E", "D"));
        // nodes.add(new Node(8, "E", "F"));
        // nodes.add(new Node(9, "E", "G"));

        // nodes.add(new Node(6, "F", "D"));
        // nodes.add(new Node(8, "F", "E"));
        // nodes.add(new Node(11, "F", "G"));

        // nodes.add(new Node(9, "G", "E"));
        // nodes.add(new Node(11, "G", "F"));
        nodes.add(new Node(1, "1", "2"));
        nodes.add(new Node(2, "1", "4"));

        nodes.add(new Node(1, "2", "1"));
        nodes.add(new Node(3, "2", "3"));
        nodes.add(new Node(2, "2", "4"));

        nodes.add(new Node(3, "3", "2"));
        nodes.add(new Node(4, "3", "4"));

        nodes.add(new Node(2, "4", "1"));
        nodes.add(new Node(2, "4", "2"));
        nodes.add(new Node(4, "4", "3"));

        ArrayList<Node> mst = new ArrayList<Node>();
        Node currentNode;

        // 1. 초기화 
        for(int idx = 0 ; idx < vetices.size() ; idx++ ){
            makeSet(vetices.get(idx));
        }

        System.out.println("parent : "+ parent);
        System.out.println("rank : " + rank);


        // 2. 간선 정보 정렬 (가중치 오름차순)
        Collections.sort(nodes);

        for(Node nd : nodes){
        System.out.println(nd);
        }

        // 3. 작은 weight 부터 사이클 생성 여부 판단하여 MST 결과 생성 
        for(Node obj : nodes){
            if(find(obj.nodeV) != find(obj.nodeU)){
                union(obj.nodeV, obj.nodeU);
                mst.add(obj);
            }
        }

        // 4. 최종 결과 MST
        System.out.println(mst);
    }
}
