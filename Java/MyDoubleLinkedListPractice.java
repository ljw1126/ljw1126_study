public class MyDoubleLinkedListPractice <T> {
    public Node<T> head = null;
    public Node<T> tail = null;
    
    public class Node<T>{
        
    }
    
    public void addNode(T data){
        
    }
    
    public void printAll(){
        
    }
    //순방향 검색 
    public T searchFromHead(T isData){
        return null;
    }
    // 역방향 검색
    public T searchFromTail(T isData){
        return null;
    }
    
    // 데이터를 임의 노드 앞에 노드를 추가하는 메서드 추가하기
    public boolean insertToFront(T existedData, T addData){
        return  false;
    }
    
    
    public static void main(String[] args) {
        MyDoubleLinkedListPractice<Integer> MyLinkedList = new MyDoubleLinkedListPractice<Integer>();

        MyLinkedList.addNode(1);
        MyLinkedList.addNode(2);
        MyLinkedList.addNode(3);
        MyLinkedList.addNode(4);
        MyLinkedList.addNode(5);
        MyLinkedList.printAll();
        System.out.println("----------------");

        MyLinkedList.insertToFront(3, 2);
        MyLinkedList.printAll();
        System.out.println("----------------");

        MyLinkedList.insertToFront(6, 2);
        MyLinkedList.insertToFront(1, 0);
        MyLinkedList.printAll();
        System.out.println("----------------");

        MyLinkedList.addNode(6);
        MyLinkedList.printAll();
    }
    
}
