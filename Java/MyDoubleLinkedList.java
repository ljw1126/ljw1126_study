public class MyDoubleLinkedList<T> {
    public Node<T> head = null;
    public Node<T> tail = null;
    
    public class Node<T>{
        T data;
        Node<T> prev = null;
        Node<T> next = null;
        
        public Node(T data){
            this.data = data;
        }
    }
    
    public void addNode(T data){
        if(this.head == null){
            this.head = new Node<T>(data);
            this.tail = this.head;
        }else{
            Node<T> node = this.head;
            
            while(node.next != null){
                node = node.next;
            } // nexe = null 인 노드 찾음 
            
            node.next = new Node<T>(data);
            node.next.prev = node; // 새로 생성 node.prev = 현재 node; 
            this.tail = node.next; // 여기 빼먹음 
        }
    }
    
    public void printAll(){
        if(this.head != null){
            Node<T> node = this.head ; 
            System.out.println(node.data);
            while(node.next != null){
                node = node.next;
                System.out.println(node.data);                
            }
        }
    }
    //순방향 검색 
    public T searchFromHead(T isData){
        if(this.head == null){ // 데이터 없음
            return null;
        }else{
            Node<T> node = this.head;
            while(node != null){
                if(node.data == isData){
                    return node.data;
                }else{
                    node = node.next ; // 마지막은 null이니
                }
            }
            return null; // 데이터 없음
        }
    }
    // 역방향 검색
    public T searchFromTail(T isData){
        if(this.head == null){
            return null;
        }else{
            Node<T> node = this.tail; 
            while(node != null){
                if(node.data == isData){
                    return node.data;
                }else{
                    node = node.prev;   // head까지 search하면 null
                }
            }
            return null;
        }
    }
    
    // 데이터를 임의 노드 앞에 노드를 추가하는 메서드 추가하기
    public boolean insertToFront(T existedData, T addData){
        
        if(this.head == null){// 데이터가 없는 경우 
            this.head = new Node<T>(addData);
            this.tail = this.head;
            return true;
        }else if(this.head.data == existedData){//head 앞에 넣는 경우
            Node<T> newHead = new Node<T>(addData);
            newHead.next = this.head; // prev 초기화시 null 
            this.head = newHead;
            return true;
        }else{ // 노드 중간에 삽입하는 경우
            Node<T> node = this.head;
            while(node != null){
                if(node.data == existedData){
                    Node<T> nodePrev = node.prev;
                    
                    nodePrev.next = new Node<T>(addData); //앞에 노드의 next에 
                    nodePrev.next.next = node;

                    nodePrev.next.prev = nodePrev; //신규생성node의 prev
                    node.prev = nodePrev.next; // 검색한 node에 prev는 신규노드
                    return true;
                }else{
                    node = node.next;
                }
            }
            return false; // 데이터가 존재하지 않는 경우
        }
        
        
    }
    
    
    public static void main(String[] args) {
        MyDoubleLinkedList<Integer> MyLinkedList = new MyDoubleLinkedList<Integer>();

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
