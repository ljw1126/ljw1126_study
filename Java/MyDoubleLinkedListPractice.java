public class MyDoubleLinkedListPractice <T> {
    public Node<T> head = null;
    public Node<T> tail = null;
    
    public class Node<T>{
        T data;
        Node<T> prev;
        Node<T> next;

        public Node(T data){
            this.data = data;
            this.prev = null;
            this.next = null;
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
            }
            node.next = new Node<T>(data);
            node.next.prev = node;
            this.tail = node.next;
        }
    }
    
    public void printAll(){
        if(this.head == null){
            System.out.println("데이터가 없습니다.");
        }else{
            Node<T> node = this.head;
            System.out.println(node.data);
            while(node.next != null){
                System.out.println(node.next.data);
                node = node.next;
            }
        }
    }
    //순방향 검색 
    public T searchFromHead(T isData){

        Node<T> node = this.head; 
      
        if(node == null){
            return null; 
        }else{
            while(node != null){
                if(node.data == isData){
                    return node.data;
                }
                node = node.next;
            }
        }
      
        return null;
    }
    // 역방향 검색
    public T searchFromTail(T isData){
        
        Node<T> node = this.tail; 
      
        if(node == null){
            return null;
        }else{
            while(node != null){
                if(node.data == isData){
                    return node.data;
                }
                node = node.prev;
            }
        }
        
        return null;
    }
    
    // 데이터를 임의 노드 앞에 노드를 추가하는 메서드 추가하기
    public boolean insertToFront(T existedData, T addData){
        Node<T> node = this.head; 
        
        if(node == null){
            this.head = new Node<T>(addData);
            this.tail = this.head;
            return true;
        }else if(node.data == existedData){ // head의 data인 경우 , 여기 틀림 
            this.head = new Node<T>(addData); 
            this.head.next = node; 
            this.head.next.prev = this.head;
            this.tail = this.head.next; 
            return true; 
        }else{

            Node<T> nextNode = node.next;

            while(nextNode != null){

                if(nextNode.data == existedData){
                    Node<T> prevNode = nextNode.prev;

                    prevNode.next = new Node<T>(addData);
                    prevNode.next.prev = prevNode;
                    prevNode.next.next = nextNode;
                    nextNode.prev = prevNode.next;
                    return true;
                }
                
                nextNode = nextNode.next;
            }
        }
        
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
