public class MyDoubleLinkedListPractice <T> {
    
    public Node<T> head = null;
    public Node<T> tail = null;

    public class Node<T>{
        T data;
        Node<T> prev;
        Node<T> next;

        public Node(T value){
            this.data = value;
        }
    }

    public void addNode(T data){
        if(this.head == null){
            this.head = new Node(data);
            this.tail = this.head;
            return;
        }else{
            Node<T> node = this.head;
            
            while(node.next != null){
                node = node.next;
            }

            node.next = new Node(data);
            node.next.prev = node; // 신규 생성 node의 prev
            this.tail = node.next;
            return;
        }
    }

    //순 방향 검색
    public Node<T> searchFromHead(T isData){

        if(this.head != null){
            Node<T> node = this.head;
            
            if(node.data == isData){
                return node;
            }else{
                while(node.next != null){
                    if(node.next.data == isData){
                        return node.next;
                    }
                    node = node.next;
                }
            }
        }
        return null;
    }

    //역 방향 검색 
    public Node<T> searchFromTail(T isData){

        if(this.tail != null){
            Node<T> node = this.tail;
            if(node.data == isData){
                return node;
            }else{
                while(node.prev != null){
                    if(node.prev.data == isData){
                        return node.prev;
                    }
                    node = node.prev;
                }
            }
        }
        return null;
    }

    // 데이터를 임의 노드 앞에 노드를 추가하는 메서드 추가하기 
    public boolean insertToFront(T existedData, T addData){

        if(this.head == null){
            this.head = new Node<T>(addData);
            this.tail = this.head;
            return true;
        }else{
            if(this.head.data == existedData){ // head 앞에 넣는 경우 
                Node<T> temp = this.head;
                this.head = new Node(addData);
                this.head.next = temp;
                temp.prev = this.head;
                return true;
            }else{
                Node<T> CurntNode = this.head.next;
                Node<T> PrntNode = this.head;

                while(CurntNode != null){
                    if(CurntNode.data == existedData){
                        PrntNode.next = new Node(addData);
                        PrntNode.next.prev = PrntNode;
                        PrntNode.next.next = CurntNode;
                        CurntNode.prev = PrntNode.next; 
                        return true;
                    }
                    PrntNode = CurntNode;
                    CurntNode = CurntNode.next;
                }

            }
        }

        return false;
    }
   
    /*
        case1. 첫번째 노드 삭제시 
        case2. 마지막 노드 삭제시 
        case3. 중간 노드 삭제시
    */
    public void removeFirst(){
        if(this.head != null){
            this.head.next.prev = null;
            this.head = this.head.next;
        } 
    }

    public void removeLast(){
        if(this.tail != null){
            this.tail.prev.next = null;
            this.tail = this.tail.prev;
        }
    }

    public boolean remove(T isData){
        
        if(this.head != null){
            //case1. this.head 노드 한 개뿐이고, 해당 노드가 삭제할 노드 인 경우
            if(this.head.data == isData && this.head.next == null){
                removeFirst();
                this.tail = null;
                return true;
            //case2. this.head 노드가 삭제할 노드고, 다른 노드가 존재할때 
            }else if(this.head.data == isData && this.head.next != null){
                removeFirst();
                return true;
            }else{
                // 중간 노드 삭제할 경우, 마지막 노드 삭제할 경우 
                Node<T> curntNode = this.head.next;
                Node<T> parntNode = this.head;    
            
                while(curntNode != null){
                    // case3-1 중간노드 일 경우 
                    if(curntNode.data == isData && curntNode.next != null){
                        parntNode.next = curntNode.next;
                        curntNode.next.prev = parntNode;
                        return true;
                    // case3-2 마지막 노드 일 경우     
                    }else if(curntNode.data == isData && curntNode.next == null){
                        removeLast();
                        return true;
                    }
         
                    parntNode = curntNode;
                    curntNode = curntNode.next;
                }
            
            }
        }

        return false;
    }



    public void printFromHead(){
        if(this.head != null){
            Node<T> headNode = this.head;
            System.out.println(headNode.data);
            while(headNode.next !=null){
                headNode = headNode.next;
                System.out.println(headNode.data);
            }
        }
    }

    public void printFromTail(){
        if(this.tail != null){
            Node<T> tailNode = this.tail;
            System.out.println(tailNode.data);
            while(tailNode.prev != null){
                tailNode = tailNode.prev;
                System.out.println(tailNode.data);
            }
        }
    }

    public static void main(String[] args) {
        MyDoubleLinkedListPractice<Integer> MyLinkedList = new MyDoubleLinkedListPractice();

        MyLinkedList.addNode(1);
        MyLinkedList.addNode(2);
        MyLinkedList.addNode(3);
        MyLinkedList.addNode(4);
        MyLinkedList.addNode(5);
        // MyLinkedList.remove(3);
        // MyLinkedList.remove(2);
        // MyLinkedList.remove(1);
        // MyLinkedList.remove(5);
        MyLinkedList.remove(1);
        MyLinkedList.printFromHead();
        System.out.println("----------------");
        MyLinkedList.printFromTail();
        System.out.println("----------------");
        System.out.println(MyLinkedList.head.prev);
        System.out.println(MyLinkedList.head.data);
        System.out.println(MyLinkedList.head.next);
        System.out.println(MyLinkedList.tail.prev);
        System.out.println(MyLinkedList.tail.data);
        System.out.println(MyLinkedList.tail.next);

        MyLinkedList.addNode(6);
        MyLinkedList.printFromHead();
        System.out.println("----------------");
        MyLinkedList.printFromTail();
        System.out.println("----------------");
        /*
        MyLinkedList.printFromHead();
        System.out.println("----------------");
        MyLinkedList.printFromTail();
        System.out.println("----------------");

        MyLinkedList.insertToFront(3, 2);
        MyLinkedList.printFromHead();
        System.out.println("----------------");
        MyLinkedList.printFromTail();
        System.out.println("----------------");

        MyLinkedList.insertToFront(6, 2);
        MyLinkedList.insertToFront(1, 0);
        MyLinkedList.printFromHead();
        System.out.println("----------------");
        MyLinkedList.printFromTail();
        System.out.println("----------------");

        MyLinkedList.addNode(6);
        MyLinkedList.printFromHead();
        System.out.println("----------------");
        MyLinkedList.printFromTail();
        System.out.println("----------------");
        */
    }

}
