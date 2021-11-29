public class BinarySerachTreePractice211129 {
    
    Node head = null;
    
    public class Node{
        int value;
        Node left = null;
        Node right = null;

        public Node(int value){
            this.value = value;
        }
    }

    public void insertNode(int value){

        if(this.head == null){
            this.head = new Node(value);
            return;
        }else{
            Node node = this.head;
            while(node != null){
                if(value < node.value){ // 왼쪽 

                    if(node.left == null){
                        node.left = new Node(value);
                        return;
                    }else{ // node.left != null;
                        node = node.left;
                    }

                }else{ // node.value < value , 오른쪽
                   
                    if(node.right == null){
                        node.right = new Node(value);
                        return;
                    }else{
                        node = node.right;
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
                if(node.value == value){
                    return node;
                }else if(value < node.value){ // 왼쪽 
                    node = node.left;
                }else{ // node.value < value ,오른쪽
                    node = node.right;
                }
            }

            return null;
        }
    }
    /*
        case1-1 노드가 하나도 없을때   
        case1-2 삭제할 노드가 head이고 자식이 없을떄 
        case2-1 삭제할 노드가 리프 노드일때 
        case2-2 삭제할 노드에 왼쪽 자식만 있을 경우
        case2-3 삭제할 노드에 오른쪽 자식만 있을 경우 
        case3. 삭제할 노드에 자식 노드가 두개 있을 경우 
            case3-1 오른쪽 자식 중 가장 작은 값(왼쪽)에 자식이 없을때 
            case3-2 오른쪽 자식 중 가장 작은 값(왼쪽)에 오른쪽 자식이 있을때
               
    */
    public boolean delete(int value){

        boolean search = false;
        Node curntNode = null;
        Node parntNode = null;

        if(this.head == null){
            return false;
        }else{

            if(this.head.value == value && this.head.left == null 
                && this.head.right == null){
                    this.head = null;
                    return true;
            }

            parntNode = this.head;
            curntNode = this.head;
            while(curntNode != null){

                if(curntNode.value == value){
                    search = true;
                    break;
                }else if(value < curntNode.value){
                    parntNode = curntNode;
                    curntNode = curntNode.left;
                }else{
                    parntNode = curntNode;
                    curntNode = curntNode.right;
                }
            }
        }

        if(search){

            //삭제할 노드가 leaf 노드 일때 
            if(curntNode.left == null && curntNode.right == null){
                if(value < parntNode.value){
                    parntNode.left = null;
                }else{
                    parntNode.right = null;
                }
            //삭제할 노드에 왼쪽 자식만 존재할때 
            }else if(curntNode.left != null && curntNode.right == null){
                if(value < parntNode.value){
                    parntNode.left = curntNode.left;
                }else{
                    parntNode.right = curntNode.left;
                }
            //삭제할 노드에 오른쪽 자식만 존재할때 
            }else if(curntNode.left == null && curntNode.right != null){
                if(value < parntNode.value){
                    parntNode.left = curntNode.right;
                }else{
                    parntNode.right = curntNode.right;
                }
            // 삭제할 노드에 자식 노드가 2개 존재할때 
            }else{  
                // point. 오른쪽 자식 노드중 가장 작은값(가장 왼쪽값) 찾음
                // 이때 가장 작은값이 leaf 노드 인 경우와
                // 가장 작은값의 오른쪽에 자식이 있는 경우로 나뉨(왼쪽 자식이 존재시 그게 최소값)
                
                Node nParntNode = curntNode.right;
                Node nCurntNode = curntNode.right;
                
                while(nCurntNode.left != null){
                    nParntNode = nCurntNode;
                    nCurntNode = nCurntNode.left;
                }

                if(nCurntNode.right == null){
                    nParntNode.left = null;     
                }else{
                    nParntNode.left = nCurntNode.right;
                }

                // 찾은 값을 부모 노드에 부착 하고 인수 인계 
                if(value < parntNode.value){
                    parntNode.left = nCurntNode;
                }else{
                    parntNode.right = nCurntNode;
                }
                
                nCurntNode.left = curntNode.left;
                nCurntNode.right = curntNode.right;
                curntNode = null;
                
            }   
            
            return true; // 애초에 search == true이므로 
        }


        return false;
    }

    public static void main(String[] args) {
        BinarySerachTreePractice211129 myTree = new BinarySerachTreePractice211129();
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
