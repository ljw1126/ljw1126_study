import java.util.*;

public class MyHeapPractice {
    static ArrayList<Integer> myHeap = null;

    public MyHeapPractice(Integer data){
        this.myHeap = new ArrayList<>();
        this.myHeap.add(null);
        this.myHeap.add(data);  // 1번 인덱스부터 루트노드로 
    }

    // insert시 마지막 인덱스에 넣고 heapify 하기 위한 함수 
    public boolean move_up(int idx){
       if(idx <= 1) return false;

       int parent_idx = idx/2;

       if(this.myHeap.get(parent_idx) < this.myHeap.get(idx)){ // 부모 노드 값이 더 큰 경우 , 기호가 반대이면 최소힙이 됨 
           return true;
       }

       return false;
    }

    public void insert(int data){
       
        if(this.myHeap == null){
            this.myHeap = new ArrayList<>();
            this.myHeap.add(null);
            this.myHeap.add(data);
        }else{
            this.myHeap.add(data);
            int insert_idx = this.myHeap.size() - 1;

            while(move_up(insert_idx)){
                int parent_idx = insert_idx/2;
                Collections.swap(this.myHeap, insert_idx, parent_idx);
                insert_idx = parent_idx;
            }

        }
    }

    // pop() 할 경우 마지막 인덱스 값을 root에 올리고 heapify 하기 위한 함수 
    public boolean move_down(int idx){

        int left_child_idx, right_child_idx;
        left_child_idx = idx * 2; 
        right_child_idx = left_child_idx + 1;

        // case1.자식노드가 둘다 없을때 
        if(left_child_idx >= this.myHeap.size()){
            return false;
        }
        // case2.왼쪽 자식 노드만 있을때 
        else if(right_child_idx >= this.myHeap.size()){
            if(this.myHeap.get(left_child_idx) > this.myHeap.get(idx)){
                return true;
            }
        }
        // case3.자식 노드 둘다 있을때 
        else{
            if(this.myHeap.get(left_child_idx) > this.myHeap.get(right_child_idx)){ //왼쪽 자식 노드가 클때 
                if(this.myHeap.get(left_child_idx) > this.myHeap.get(idx)) return true;
            }else{  // 오른쪽 자식 노드가 클때 
                if(this.myHeap.get(right_child_idx) > this.myHeap.get(idx)) return true;
            }
        }

        return false; 
    }

    public int pop(){
        int popped_data, popped_idx, last_idx, left_child_idx, right_child_idx;
        if(this.myHeap == null){
            return -1;
        }else{
            // 1번 인덱스 값을 pop 하고 마지막 인덱스에 값을 1번 root에 올린 뒤 heapify 함 
            last_idx = this.myHeap.size()-1;
            popped_data = this.myHeap.get(1);
            this.myHeap.set(1, this.myHeap.get(last_idx));   // Miss. Collections.swap 을 해버림.. 
            this.myHeap.remove(last_idx);
            popped_idx = 1;

            while(move_down(popped_idx)){
                left_child_idx = popped_idx*2;
                right_child_idx = left_child_idx +1;

                if(right_child_idx >= this.myHeap.size()){
                    if(this.myHeap.get(left_child_idx) > this.myHeap.get(popped_idx)){
                        Collections.swap(this.myHeap, popped_idx, left_child_idx);
                        popped_idx = left_child_idx;
                    }
                }else{
                    if(this.myHeap.get(left_child_idx) > this.myHeap.get(right_child_idx)){
                        if(this.myHeap.get(left_child_idx) > this.myHeap.get(popped_idx)){
                            Collections.swap(this.myHeap, popped_idx, left_child_idx);
                            popped_idx = left_child_idx;
                        }
                    }else{
                        if(this.myHeap.get(right_child_idx) > this.myHeap.get(popped_idx)){
                            Collections.swap(this.myHeap, popped_idx, right_child_idx);
                            popped_idx = right_child_idx;
                        }
                    }
                }
            }
        }
        
        return popped_data; 
    }


    public static void main(String[] args) {
        MyHeapPractice heapTest = new MyHeapPractice(15);
        heapTest.insert(10);
        heapTest.insert(8);
        heapTest.insert(5);
        heapTest.insert(4);
        heapTest.insert(20);
        System.out.println(heapTest.myHeap); // [null,20,10,15,5,4,8]

        System.out.println(heapTest.pop());  // 20
        System.out.println(heapTest.myHeap); // [null,15,10,8,5,4,]

        System.out.println(heapTest.pop());  // 15
        System.out.println(heapTest.myHeap); // [null,10,5,8,4]
    }
}

