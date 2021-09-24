import java.util.Comparator;

public class LinkedListEx<E> {
    private Node<E> head;  //머리노드 , 기준 노드 
    private Node<E> crnt;  //선택노드 (검색, 삭제 등 용도로)
    
    class Node<E>{
        private E data; //데이터 
        private Node<E> next; // 다음 노드 가르키는 포인터
    
        //생성자
        Node(E data, Node<E> next){
            this.data = data;
            this.next = next; 
        }
    }

    //생성자
    public LinkedListEx(){
        head = crnt = null;
    }

    //노드 검색 
    /*
        Comparator<? super E> c 에 대해 
        - 클래스 T(또는 클래스 T의 슈퍼클래스)로 생성한 두 객체의 
          대소 관계를 판단하기 위한 comparator이다. 
        - comparator안에는 compare() 함수가 있음 
          - o1 > o2 이면 양수 반환
          - o1 < o2 이면 음수 반환
          - o1 == o2 이면 0 반환 

    */
    public E search(E obj, Comparator<? super E> c){
        Node<E> ptr = head;    //head부터 스캔  

        while(ptr != null){
            if(c.compare(obj, ptr.data) == 0){ //검색 성공 
                crnt = ptr;
                return ptr.data;
            }     
            ptr = ptr.next;   // 다음노드 이동
        }
        
        return null; //검색 실패 
    }


    // 머리에 노드 삽입 
    public void addFirst(E obj){
        Node<E> ptr = head; 
        head = crnt = new Node<E>(obj, ptr);
    }

    //꼬리에 노드 삽입 
    public void addLast(E obj){
        if(head == null){ // 리스트가 비어 있으면
            addFirst(obj);
        }else{
            Node<E> ptr = head; 
            
            while(ptr.next != null)
                ptr = ptr.next;
            ptr.next = crnt = new Node<E>(obj, null); // 꼬리 노드는 null 가르킴

        }
    }

    //머리 노드 삭제 
    public void removeFirst(){
        if(head != null) head = crnt = head.next;
    }

    //꼬리 노드 삭제 
    public void removeLast(){
        if(head != null){
            if(head.next == null){ //노드가 한개인 경우
                removeFirst();
            }else{
                Node<E> ptr = head;      // head부터 스캔 
                Node<E> prevNode = head; // 스캔 중인 노드의 앞족 노드

                while(ptr != null){
                    prevNode = ptr;
                    ptr = ptr.next;
                }

                prevNode.next = null; //next만 null로 해주면 됨 
                crnt = prevNode;
            }
        }
    }

    //특정 노드 p 삭제 
    public void remove(Node p){
        if(head != null){
            if( p == head ){
                removeFirst();
            }else{
                Node<E> ptr = head; // head부터 스캔 

                while(ptr.next != p){
                    ptr = ptr.next;
                    if(ptr == null) return; //p가 리스트에 없음
                }

                ptr.next = p.next; //이전 노드의 next를 p의 next로 갱신
                crnt = ptr; 
            }
        }
    }

    //선택 노드 삭제 
    public void removeCurrentNode(){
        remove(crnt);
    }

    //모든 노드 삭제 
    public void clear(){
        while(head != null)
            removeFirst();
        crnt = null;
    }

    //crnt 포인터(선택노드)를 하나 뒤로 이동
    public boolean next(){
        if(crnt == null || crnt.next == null)
            return false;   //이동 불가 
        crnt = crnt.next;
        return true; 
    } 

    //crnt포인터(현재 선택노드) data 출력 
    public void printCurrentNode(){
        if(crnt == null)
            System.out.println("선택한 노드가 없음");
        else
            System.out.println(crnt.data);
    }

    //모든 노드 출력 
    public void dump(){
        Node<E> ptr = head ; 
        while(ptr != null){
            System.out.println(ptr.data); // Data 에 toString 연결되는데?
            ptr = ptr.next;
        }
    }


}
