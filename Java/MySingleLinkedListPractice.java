public class MySingleLinkedListPractice<T> {
    // head 부터 순차적으로 노드 검색
    public Node<T> head = null;
    
    // 생성자
    public class Node<T>{
      
    }
    
    //노드 추가 
    public void addNode(T data){
     
    }
    
    // 전체 노드 출력
    public void printAll(){
     
    }
    
    //해당 데이터 가진 노드 리턴 
    public Node<T> search(T data){
        return null;
    }
    
    // 중간에 노드 삽입 
    public void addNodeInside(T data, T isData){
       
    }
    
    // 노드 삭제 ( Integer는 Wrapper클래스 )
    public boolean delNode(T isData){
       return false;
    }// end delNode
    
    public static void main(String[] args) {
        MySingleLinkedListPractice<Integer> MyLinkedList = new MySingleLinkedListPractice<Integer>();

        MyLinkedList.addNode(1);
        MyLinkedList.addNode(2);
        MyLinkedList.addNode(3);
        MyLinkedList.addNode(4);
        MyLinkedList.addNode(5);

        MyLinkedList.printAll();

    }
}
