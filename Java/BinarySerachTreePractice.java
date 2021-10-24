public class BinarySerachTreePractice {
    
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
        }else{
            Node findNode = this.head; 
            // while(findNode != null){
            //     if( value < findNode.value ){
            //         findNode = findNode.left; 
            //     }else{
            //         findNode = findNode.right;
            //     }
            // }
            // findNode = new Node(value);  // 이것도 되지 않을까 ,, 디버그 거니 findNode에 null 이 됨

            while(true){ // 있다는걸 이미 전제로 반복문 시작하는거라 

                if(value < findNode.value){
                    if(findNode.left != null){
                        findNode = findNode.left;
                    }else{  // *.left == null 인경우 
                        findNode.left = new Node(value);
                        break;
                    }
                }else{
                    if(findNode.right != null){
                        findNode = findNode.right;
                    }else{ // *.right == null 인 경우 
                        findNode.right = new Node(value);
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
            Node findNode = this.head;

            while(findNode != null){
                if(findNode.value == value){
                    return findNode;
                }else if(value < findNode.value ){
                    findNode = findNode.left;
                }else if(findNode.value < value){
                    findNode = findNode.right;
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
        Node currNode = this.head;
        Node currParentNode = this.head; 

        if(this.head == null){
            return false;
        }else{
            
            if(this.head.value == value && this.head.left == null & this.head.right == null){
                this.head = null;
                return true; 
            }

            while(currNode != null ){
                if(currNode.value == value){
                    searched = true;
                    break;
                }else if ( value < currNode.value){
                    currParentNode = currNode;
                    currNode = currNode.left; 
                }else{
                    currParentNode = currNode;
                    currNode = currNode.right; 
                }
            }

            if(!searched) return false; 
        }

        // 여기는 찾았을 경우 currNode에 해당 노드가 들어있는 상태
        

        Node changeNode;
        Node changeParentNode;

        if(currNode.left == null && currNode.right == null){ // leaf node일때 
            if(value < currParentNode.value){
                currParentNode.left = null;
            }else{
                currParentNode.right = null;
            }
        }else if(currNode.left != null && currNode.right == null){ // 삭제할 노드에 왼쪽 자식만 있을때 
            if(value < currParentNode.value){
                currParentNode.left = currNode.left;
            }else{
                currParentNode.right = currNode.left;
            }
        }else if(currNode.left == null && currNode.right != null){ // 삭제할 노드에 오른쪽 자식만 있을때
            if(value < currParentNode.value){
                currParentNode.left = currNode.right;
            }else{
                currParentNode.right = currNode.right;
            }
        }else{  // 자식이 두개 존재할때
            // 삭제할 Node의 오른족 자식 중, 가장 작은값(가장 왼쪽값)을 삭제할 Node의 parent Node가 가리키도록함
            changeNode = currNode.right;
            changeParentNode = currNode.right;
            
            while(changeNode.left != null){
                changeParentNode = changeNode; 
                changeNode = changeNode.left;
            }

            if( value < currParentNode.value){
                
                if(changeNode.right != null){
                    changeParentNode.left = changeNode.right;
                }else{
                    changeParentNode.left = null;
                }

                currParentNode.left = changeNode;
                changeNode.left = currNode.left;
                changeNode.right = currNode.right;
                changeNode = null;
            }else{

                if(changeNode.right != null){
                    changeParentNode.left = changeNode.right;
                }else{
                    changeParentNode.left = null;
                }

                currParentNode.right = changeNode;
                changeNode.left = currNode.left;
                changeNode.right = currNode.right;
                changeNode = null;
            }




            // while(changeNode.left != null){
            //     changeParentNode = changeNode; 
            //     changeNode = changeNode.left;
            // }

            // // 1. swap하려는 최소 노드가 leaf node 일때 
            // if(changeNode.left == null && changeNode.right == null){
            //     if(value < currParentNode.value){
            //         currParentNode.left = changeNode;

            //         changeNode.left = currNode.left;
            //         changeNode.right = currNode.right;
            //     }else{
            //         currParentNode.right = changeNode;

            //         changeNode.left = currNode.left;
            //         changeNode.right = currNode.right;
            //     }
            //     changeParentNode.left = null;
            //     currNode = null;
            // }else{ // 2.  swap하려는 최소 노드가 오른쪽 자식 노드만 존재할떄 ( 왼쪽은 존재 불가, 존재시 그게 최소값이 되니깐)
            //     if(value < currParentNode.value){
            //         currParentNode.left = changeNode;

            //         changeNode.left = currNode.left;
            //         changeNode.right = currNode.right;
            //     }else{
            //         currParentNode.right = changeNode;

            //         changeNode.left = currNode.left;
            //         changeNode.right = currNode.right;
            //     }
            //     changeParentNode.left = changeNode.right;
            //     currNode = null;
            // }

           



        }

        currNode = null;
        return true;
    }


    public static void main(String[] args) {
        BinarySerachTreePractice myTree = new BinarySerachTreePractice();
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
