
import java.util.*;

public class BinarySerachTreePractice2 {
    Node head = null;

    public class Node{
       int value;
       Node left;
       Node right;
       public Node(int value){
           this.value = value;
           this.left = null;
           this.right = null;
       }
    }

    public void insertNode(int value){

        if(this.head == null){
            this.head = new Node(value);
            return;
        }else{

            Node node = this.head; 
            while(node != null){
                if( value < node.value ){
                    if(node.left != null){
                        node = node.left;    
                    }else{ //node.left == null;
                        node.left = new Node(value);
                        break;
                    }
                }else{ // node.value < value 
                    if(node.right != null){
                        node = node.right;
                    }else{ //node.right == null
                        node.right = new Node(value);
                        break;
                    }
                }
            }
        }

    }

    public Node search(int value){
        if(this.head == null){
            return null;
        }else{
            Node node = this.head;
            while(node != null){
                if(value == node.value){
                    return node;
                }else if(value < node.value){
                    node = node.left;
                }else{ // node.value < value
                    node = node.right;
                }
            }
        }
        return null;
    }

    /*
        case1. Node가 하나도 없을때 
        case2-1 Node가 head 하나이고 head가 답일때 
            2-2 head가 아닌경우 search 를 통해 값을 찾거나 못찾았을때
        case3 삭제할 노드가 존재하고 
            3-1 삭제할 노드의 자식 노드가 없을때 
            3-2 삭제할 노드의 자식 노드가 하나만 있을때
            3-3 삭제할 노드의 자식 노드가 두개 있을때 
        [전략] 삭제할 노드의 오른쪽 자식중 가장 작은값을 가져와 parent node와 연결하고 삭제할 노드와 연결되었던 정보를 인수인계함 
    */
    public boolean delete(int value){ 
        boolean searched = false;
        Node curntNode = this.head;
        Node parntNode = this.head;
        if(curntNode == null){ // case1. 삭제할 노드가 없을때 
            return false;
        }else{
            // case2-1 삭제할 노드가 head이고 자식노드가 하나도 없을때 
            if(curntNode.value == value && curntNode.left == null && curntNode.right == null){
                this.head = null;
                return true;
            }
            // case2-2 head가 아닌 노드 중에 삭제할 노드가 있는지 검색 
            while(curntNode != null){
                if( curntNode.left != null &&  value < curntNode.value ){ // 왼쪽인 경우 
                   
                    parntNode = curntNode;
                    curntNode = curntNode.left;
                   
                    if(curntNode.value == value) {
                        searched = true;
                        break; 
                    }
                    
                }else if(curntNode.right != null && curntNode.value < value ) { // 오른쪽인 경우 
                    parntNode = curntNode;
                    curntNode = curntNode.right;

                    if(curntNode.value == value){
                        searched = true;
                        break;
                    }
                }
            }

           
        }

        if(searched){
            // case 3-1 . 삭제할 노드가 존재하고 자식노드가 하나도 없을떄 
            if(curntNode.left == null && curntNode.right == null){
                if(value < parntNode.value){
                    parntNode.left = null;
                }else{
                    parntNode.right = null;
                }
            }else if(curntNode.left != null && curntNode.right == null){ // case 3-2. 삭제할 노드의 왼쪽 자신노드만 존재할때 
                if(value < parntNode.value){
                    parntNode.left = curntNode.left;
                }else{
                    parntNode.right = curntNode.left;
                }
            }else if(curntNode.left == null && curntNode.right != null){ // case 3-3. 삭제할 노드의 오른족 자식노드만 존재할때 
                if(value < parntNode.value){
                    parntNode.left = curntNode.right;
                }else{
                    parntNode.right = curntNode.right;
                }
            }else{
                /*
                case 3-4 : 삭제할 노드에 자식 두개가 있을 경우 
                    삭제할 노드의 오른쪽 자식중에 가장 작은 값을 parent node와 연결해주고 삭제할 노드 정보 인수인계 
                    또한 swap 할 노드의 부모 노드에 대한 정보 처리도 필요 
                    case 3-4-1 : swap할 노드에 오른쪽 자식이 있을 경우 ( 왼쪽이 있을 경우 그게 최소값이므로 ) 
                    case 3-4-2 : swap할 노드가 leaf 노드인 경우  
                */
                Node changeNode = curntNode.right;
                Node changeNodeParent = curntNode.right;

                if( value < parntNode.value){

                    while(changeNode.left != null){
                        changeNodeParent = changeNode;
                        changeNode = changeNode.left;
                    }

                    if(changeNode.right != null){
                        changeNodeParent.left = changeNode.right;
                    }else{
                        changeNodeParent.left = null;
                    }

                    parntNode.left = changeNode;
                    changeNode.left = curntNode.left;
                    changeNode.right = curntNode.right;
                    curntNode = null;

                }else{

                    while(changeNode.left != null){
                        changeNodeParent = changeNode;
                        changeNode = changeNode.left;
                    }

                    if(changeNode.right != null){
                        changeNodeParent.left = changeNode.right;
                    }else{
                        changeNodeParent.left = null;
                    }

                    parntNode.right = changeNode; // 여기만 달랐음.
                    changeNode.left = curntNode.left;
                    changeNode.right = curntNode.right;
                    curntNode = null;
                }
            }

            return true;
        }

        return false;
    }
    /*

            10
          7    15
        6  8 13 18
               16  19
                 17
        HEAD: 10
        HEAD LEFT: 7
        HEAD LEFT LEFT: 6
        HEAD LEFT RIGHT: 8
        HEAD RIGHT: 15
        HEAD RIGHT LEFT: 13
        HEAD RIGHT RIGHT: 18
        HEAD RIGHT RIGHT LEFT: 16
        HEAD RIGHT RIGHT LEFT RIGHT: 17
        HEAD RIGHT RIGHT RIGHT: 19
               10
           7       16                        
          6  8   13 18  
                   17  19 
        HEAD: 10
        HEAD LEFT: 7
        HEAD LEFT LEFT: 6
        HEAD LEFT RIGHT: 8
        HEAD RIGHT: 16
        HEAD RIGHT LEFT: 13
        HEAD RIGHT RIGHT: 18
        HEAD RIGHT RIGHT LEFT: 17
        HEAD RIGHT RIGHT RIGHT: 19

    */

    public static void main(String[] args) {
        BinarySerachTreePractice2 myTree = new BinarySerachTreePractice2();
        myTree.insertNode(10);
        myTree.insertNode(15);
        myTree.insertNode(13);
        myTree.insertNode(11);
        myTree.insertNode(14);
        myTree.insertNode(18);
        myTree.insertNode(16);
        myTree.insertNode(19);
        myTree.insertNode(17);
        myTree.insertNode(7);
        myTree.insertNode(8);
        myTree.insertNode(6);
        
        System.out.println("HEAD: " + myTree.head.value);
        System.out.println("HEAD LEFT: " + myTree.head.left.value);
        System.out.println("HEAD LEFT LEFT: " + myTree.head.left.left.value);
        System.out.println("HEAD LEFT RIGHT: " + myTree.head.left.right.value);

        System.out.println("HEAD RIGHT: " + myTree.head.right.value);
        System.out.println("HEAD RIGHT LEFT: " + myTree.head.right.left.value);
        System.out.println("HEAD RIGHT RIGHT: " + myTree.head.right.right.value);

        System.out.println("HEAD RIGHT RIGHT LEFT: " + myTree.head.right.right.left.value);
        System.out.println("HEAD RIGHT RIGHT LEFT RIGHT: " + myTree.head.right.right.left.right.value);
        System.out.println("HEAD RIGHT RIGHT RIGHT: " + myTree.head.right.right.right.value);

        System.out.println(myTree.delete(15));
        System.out.println("====================삭제후 ");
        
        System.out.println("HEAD: " + myTree.head.value);
        System.out.println("HEAD LEFT: " + myTree.head.left.value);
        System.out.println("HEAD LEFT LEFT: " + myTree.head.left.left.value);
        System.out.println("HEAD LEFT RIGHT: " + myTree.head.left.right.value);

        System.out.println("HEAD RIGHT: " + myTree.head.right.value);
        System.out.println("HEAD RIGHT LEFT: " + myTree.head.right.left.value);
        System.out.println("HEAD RIGHT RIGHT: " + myTree.head.right.right.value);

        System.out.println("HEAD RIGHT RIGHT LEFT: " + myTree.head.right.right.left.value);
        System.out.println("HEAD RIGHT RIGHT RIGHT: " + myTree.head.right.right.right.value);

        System.out.println(myTree.delete(18));
        System.out.println(myTree.delete(17));
        System.out.println("====================삭제후 ");

        System.out.println("HEAD: " + myTree.head.value);
        System.out.println("HEAD LEFT: " + myTree.head.left.value);
        System.out.println("HEAD LEFT LEFT: " + myTree.head.left.left.value);
        System.out.println("HEAD LEFT RIGHT: " + myTree.head.left.right.value);

        System.out.println("HEAD RIGHT: " + myTree.head.right.value);
        System.out.println("HEAD RIGHT LEFT: " + myTree.head.right.left.value);
        System.out.println("HEAD RIGHT RIGHT: " + myTree.head.right.right.value);
    }
}
