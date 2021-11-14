public class MySingleLinkedListPractice<T> {
    // head 부터 순차적으로 노드 검색
    public Node<T> head = null;
    
    // 생성자
    public class Node<T>{
        T data;
        Node<T> next;
        public Node(T data){
            this.data = data;
            this.next = null;
        }

    }
    
    //노드 추가 
    public void addNode(T data){
        if(this.head == null){
            this.head = new Node<T>(data);
        }else{
            Node<T> node = this.head; 
            while(node.next != null){
                node = node.next; 
            }
            node.next = new Node<T>(data);
        }
    }
    
    // 전체 노드 출력
    public void printAll(){
        if(this.head == null){
            System.out.println("데이터가 없습니다.");
        }else{
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
            Node<T> node = this.head;
            while(node != null){
                if(node.data == data)
                    return node; 
                node = node.next;
            }
        }
        return null;
    }
    
    // 중간에 노드 삽입 (data : 신규 삽입데이터 , isData 찾는 데이터)
   
    public void addNodeInside(T data, T isData){ 
       Node<T> searched = search(isData);
       
       // 찾는 노드가 없는 경우 
       if(searched == null){ 
           this.addNode(data);
       }else{
           // 찾는 노드가 존재하는 경우 (searched 노드의 뒤에 삽입 )
           Node<T> snextNode = searched.next; 
           searched.next = new Node<T>(data);
           searched.next.next = snextNode;
       }
    }
    
    // 노드 삭제 ( Integer는 Wrapper클래스 )
    public boolean delNode(T isData){
        Node<T> node = this.head;
        // 0. head가 null 인경우
        if(node == null){
            return false;
        }else{
            // head가 내가 삭제하려는 노드일때 
            if(node.data == isData){
                this.head = this.head.next;
                return true;
            }else{

                Node<T> parentNode = node; 
                Node<T> nextNode = node.next; 
                
                while(nextNode != null){
                    if(nextNode.data == isData){
                        parentNode.next = nextNode.next;
                        return true;  
                    }
                    parentNode = nextNode;
                    nextNode = nextNode.next;
                }

            }    

        }

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

        System.out.println("==============");
        // 3 삭제 
        MyLinkedList.delNode(3);
        MyLinkedList.printAll();

        System.out.println("==============");
        // 4 삽입 
        MyLinkedList.addNodeInside(4,4);
        MyLinkedList.addNodeInside(6,4);
        MyLinkedList.printAll();

    }
}
