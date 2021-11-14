public class MySingleLinkedList<T> {

    // head 부터 순차적으로 노드 검색
    public Node<T> head = null;
    
    // 생성자
    public class Node<T>{
        T data;
        Node<T> next = null;
        //생성자
        public Node(T data){
            this.data = data;
        }
    }
    
    //노드 추가 
    public void addNode(T data){
        if(head == null){
            head = new Node<T>(data);
        }else{
            Node<T> node = this.head;
            while(node.next != null){ // 포인터 이동 , 끝의 노드까지 가도록 만듦
                node = node.next;
            }
            node.next = new Node<T>(data);
        }
    }
    
    // 전체 노드 출력
    public void printAll(){
        if(head != null){
            Node<T> node = this.head;
            System.out.println(node.data);
            while(node.next != null){
                node = node.next; 
                System.out.println(node.data);
            }
        }
    }
    
    //해당 데이터 가진 노드 리턴 
    public Node<T> search(T data){
        if(this.head == null){
            return null;
        }else{
            Node<T> node = this.head; // head부터 순차적으로 검색
            while(node != null){
                if(node.data == data){
                    return node;
                }
                node = node.next;
            }
            return null;  // 전체 검색했는데 없다면 
        }
    }
    
    // 중간에 노드 삽입 
    public void addNodeInside(T data, T isData){
        Node<T> searchedNode = this.search(isData);
        
        if(searchedNode == null){ // 찾는 데이터가 없으면 LinkedList 맨 뒤에 삽입
            this.addNode(data); 
        }else{
            Node<T> nextNode = searchedNode.next; // 기존에 가르키던 next
            searchedNode.next = new Node<T>(data); // 새로 삽입되는 노드 
            searchedNode.next.next = nextNode;     
        }
    }
    
    // 노드 삭제 ( Integer는 Wrapper클래스 )
    public boolean delNode(T isData){
        if(this.head == null){ // 데이터 없음 
            return false; 
        }else{
            Node<T> node = this.head;
            
            if(node.data == isData){ // 헤드인 경우 
                this.head = this.head.next;
                return true;
            }else{
               while(node.next != null){
                    if(node.next.data == isData){
                        node.next = node.next.next;
                        return true;
                    }
                    node = node.next;
               }//end while 
                
                return false; 
            }// end else
            
            
        }// end else 
    }// end delNode
    
    public static void main(String[] args) {
        MySingleLinkedList<Integer> MyLinkedList = new MySingleLinkedList<Integer>();

        MyLinkedList.addNode(1);
        MyLinkedList.addNode(2);
        MyLinkedList.addNode(3);
        MyLinkedList.addNode(4);
        MyLinkedList.addNode(5);

        MyLinkedList.printAll();

    }
}
