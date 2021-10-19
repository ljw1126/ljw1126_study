/**
 * BinarySerachTree
 */
public class BinarySerachTree {

    public Node head = null;

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

    public boolean insertNode(int value){

        if(head == null){
           head = new Node(value);
        }else{
            Node findNode = head; 
            while(true){

                if( value < findNode.value){
                    if(findNode.left != null){
                        findNode = findNode.left;
                    }else{
                        findNode.left = new Node(value);
                        break;
                    }
                }else{
                    if(findNode.right != null){
                        findNode = findNode.right; 
                    }else{
                        findNode.right = new Node(value);
                        break;
                    }
                } // end else
            }   // end while
        }
        return true; 
    }

    public Node search(int value){
       
        if(head == null){
            return null;
        }else{
            Node findNode = head; 
            while(findNode != null){
                if( value == findNode.value ){
                    return findNode;
                }else if( value < findNode.value){
                    findNode = findNode.left;
                }else{
                    findNode = findNode.right;
                }
            }
            
            return null;
        }
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
        Node currParentNode = head;
        Node currNode = head; 

        if(head == null){
            return false; 
        }else{
            if( head.value == value && head.left == null && head.right == null){
                head = null;
                return true; 
            }

            while(currNode != null){
                if(currNode.value == value){
                    searched = true; 
                    break;
                }else if(value < currNode.value){
                    currParentNode = currNode;
                    currNode = currNode.left;
                }else{
                    currParentNode = currNode;
                    currNode = currNode.right;
                }
            }

            if(!searched) return false;      // 해당 노드가 존재하지 않는 경우 
        }

        //전체가 searched == true 이기 때문에 처리 반드시 하게 됨 
        //case1 . 삭제할 노드가 leaf node 일때
        if(currNode.left == null && currNode.right == null){
            if( value < currParentNode.value){
                currParentNode.left = null;
            }else{
                currParentNode.right = null; 
            }
          
        //case2. 삭제할 노드에 자식노드가 1개 있을때 ( 왼쪽/오른쪽 구분)
        }else if(currNode.left != null && currNode.right == null){
            if( value < currParentNode.value ){ // 삭제할 노드가 부모의 왼쪽에 있을때
                currParentNode.left = currNode.left;
            }else{ // 삭제할 노드가 부모의 오른쪽에 있을때 
                currParentNode.right = currNode.left; 
            } 
        }else if(currNode.left == null && currNode.right != null){
            if( value < currParentNode.value){ // 삭제할 노드가 부모의 왼쪽에 있을때
                currParentNode.left = currNode.right;
            }else{  // 삭제할 노드가 부모의 오른쪽에 있을때 
                currParentNode.right = currNode.right;
            }            
        //case3. 삭제할 노드에 자식 노드가 2개있을때
        //[전략] 삭제할 노드의 오른쪽 자식 중 가장 작은값(최하단 왼쪽값)구함 
        //       이때 swap할 노드의 자식이 오른쪽에 있는 경우와 , leaf node인 경우가 있음
        }else{
            Node changeNode; // 삭제할 노드의 오른쪽 자식 중 가장 작은값
            Node changeParentNode;
            if( value < currParentNode.value){ // 삭제할 노드가 부모 노드의 왼쪽에 있을때
                    changeNode = currNode.right;
                    changeParentNode = currNode.right; 

                    while(changeNode.left != null){ // 여기 틀림 
                        changeParentNode = changeNode;
                        changeNode = changeNode.left;
                    }

                    // swap할 노드의 왼쪽에 자식이 존재할시 그게 최소값이므로 
                    // 오른쪽에만 자식이 존재하거나 , 자식이 없거나 둘 중 하나 임 

                    if(changeNode.right != null){
                        changeParentNode.left = changeNode.right;
                    }else{
                        changeParentNode.left = null;
                    }

                    currParentNode.left = changeNode;
                    changeNode.left = currNode.left;
                    changeNode.right = currNode.right;
                    currNode = null;
                
            }else{ // 삭제할 노드가 부모 노드의 오른쪽에 있을때 
                
                    changeNode = currNode.right;
                    changeParentNode = currNode.right; 

                    while(changeNode.left != null){
                        changeParentNode = changeNode;
                        changeNode = changeNode.left;
                    }

                    // swap할 노드의 왼쪽에 자식이 존재할시 그게 최소값이므로 
                    // 오른쪽에만 자식이 존재하거나 , 자식이 없거나 둘 중 하나 임 
                    if(changeNode.right != null){ 
                        changeParentNode.left = changeNode.right;
                    }else{
                        changeParentNode.left = null;
                    }

                    currParentNode.right = changeNode;
                    changeNode.left = currNode.left;
                    changeNode.right = currNode.right;
                    currNode = null;
                
            }
        }
        return true; 
    }

    public static void main(String[] args) {
        BinarySerachTree myTree = new BinarySerachTree();
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
        System.out.println(myTree.delete(15));
        System.out.println("HEAD: " + myTree.head.value);
        System.out.println("HEAD LEFT: " + myTree.head.left.value);
        System.out.println("HEAD LEFT LEFT: " + myTree.head.left.left.value);
        System.out.println("HEAD LEFT RIGHT: " + myTree.head.left.right.value);

        System.out.println("HEAD RIGHT: " + myTree.head.right.value);
        System.out.println("HEAD RIGHT LEFT: " + myTree.head.right.left.value);
        System.out.println("HEAD RIGHT RIGHT: " + myTree.head.right.right.value);

        System.out.println("HEAD RIGHT RIGHT LEFT: " + myTree.head.right.right.left.value);
        System.out.println("HEAD RIGHT RIGHT RIGHT: " + myTree.head.right.right.right.value);
    }
}

