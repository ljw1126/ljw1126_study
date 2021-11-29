public class MySingleLinkedListPractice<T> {
    public Node<T> head = null;

    // Node 생성자
    public class Node<T>{
        T data;
        Node next;

        public Node(T value){
            this.data = value;
            this.next = null;
        }
    }

    // 노드 추가
    public void addNode(T data){
        if(this.head == null){
            this.head = new Node(data);
            return;
        }else{
            Node<T> node = this.head;
            while(node.next != null){
                node = node.next;
            }
            node.next = new Node(data);
        }
    }

    // 전체 노드 출력 
    public void printAll(){
        /*
        if(this.head == null){
            System.out.println("등록된 데이터가 없습니다.");
            return;
        }else{
            Node<T> node = this.head;
            while(node != null){
                System.out.println(node.data);
                node = node.next;
            }
        }
        */
        if(this.head != null){
            Node<T> node = this.head;
            System.out.println(node.data);
            while(node.next != null){
                node = node.next;
                System.out.println(node.data);
            }
        }
    }

    // 해당 데이터 가진 노드 리턴 
    public Node<T> search(T data){
        if(this.head == null){
            return null;
        }else{
            Node<T> node = this.head;
            while(node != null){
                if(node.data == data){
                    return node;
                }
                node = node.next;
            }
        }
        return null;
    }
    
    // 중간에 노드 삽입(찾은 데이터 next에)
    public void addNodeInside(T data, T isData){//data는 신규 삽입 데이터, isData는 찾는 데이터
        
        if(this.head == null) return;

        Node<T> searchNode = search(isData);
        
        if(searchNode == null){
            this.addNode(data);
            return;
        }else{
            Node<T> nextNode = searchNode.next;
            searchNode.next = new Node(data);
            searchNode.next.next = nextNode;
        }

    }
    
    public boolean delNode(T isData){

        if(this.head == null){
             return false;
        }else{

            if(this.head.data == isData){
                this.head = this.head.next;
                return true;
            }else{
                Node<T> node = this.head;

                while(node.next != null){
                    if(node.next.data == isData){
                        node.next = node.next.next;
                        return true;
                    }
                    node = node.next;
                }
            }

        }

        return false; 
        /* head인 경우를 빼먹음 
        Node<T> CurrentNode = this.head;
        Node<T> ParentNode = this.head;
        while(CurrentNode != null){
            if(CurrentNode.data == isData){
                break; 
            }   
            ParentNode = CurrentNode;
            CurrentNode = CurrentNode.next;
        }

        ParentNode.next = CurrentNode.next;

        return true; 
        */
    }


    public static void main(String[] args) {
        MySingleLinkedListPractice<Integer> ll = new MySingleLinkedListPractice<>();
        ll.addNode(1);
        ll.addNode(2);
        ll.addNode(4);
        ll.addNode(5);

        ll.printAll();
        
        System.out.println("===============================");

        ll.addNodeInside(3, 4);
        ll.printAll();
        System.out.println("===============================");
        ll.delNode(4);
        ll.printAll();
        
    }

}
