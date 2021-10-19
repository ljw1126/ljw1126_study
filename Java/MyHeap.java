
import java.util.ArrayList;
import java.util.Collections;

public class MyHeap {
    
    static ArrayList<Integer> myHeap = null;

    public MyHeap(Integer data){
        myHeap = new ArrayList<Integer>();
        myHeap.add(null);
        myHeap.add(data);
    }

    // insert시 마지막 인덱스에 넣고 heapify 하기 위한 함수 
    public boolean move_up(int idx){
        if(idx <= 1) return false;  // root를 1로 지정하고 해당 처리 추가 안함

        int parent_idx = idx / 2;  // 부모 노드 구하는 공식 틀려서 수정
        
            if(this.myHeap.get(parent_idx) < this.myHeap.get(idx) ){
                return true; 
            }
        
        return false; 
    }

    public void insert(int data){
        if(this.myHeap == null){
            myHeap = new ArrayList<Integer>();
            myHeap.add(null);
            myHeap.add(data);
        }else{
            
            this.myHeap.add(data);
            int insert_idx = myHeap.size() - 1;
            int parent_idx;

            while(this.move_up(insert_idx)){
                parent_idx = insert_idx / 2; // 부모 노드 구하는 공식 틀려서 수정
                Collections.swap(this.myHeap, insert_idx, parent_idx);
                insert_idx = parent_idx;
            }    
           
        }
    }

    // pop() 할 경우 마지막 인덱스 값을 root에 올리고 heapify 하기 위한 함수 
    public boolean move_down(int idx){
        Integer left_child_idx , right_child_idx;
        
        left_child_idx = idx * 2;
        right_child_idx = left_child_idx + 1;

        // case 1. 왼쪽 자식 노드도 없을때 (자식노드가 하나도 없을때, 완전이진트리에, 왼쪽부터 채워지므로 오른쪽만 있을 수 없음)
        if(left_child_idx >= this.myHeap.size()){
            return false; 
        }
        // case 2. 오른쪽 자식 노드만 없을때 (왼쪽만 있을때)
        else if(right_child_idx >= this.myHeap.size()){
            if(this.myHeap.get(idx) < this.myHeap.get(left_child_idx)){
                return true;
            }
            return false; 
        }
        // case 3. 자식 노드 두개다 있을때 
        else{
            // 왼쪽 오른쪽 크기 비교 후
            if(this.myHeap.get(left_child_idx) > this.myHeap.get(right_child_idx)){
                if(this.myHeap.get(idx) < this.myHeap.get(left_child_idx)){
                    return true;
                }
                return false;
            }else{
                if(this.myHeap.get(idx) < this.myHeap.get(right_child_idx)){
                    return true;
                }
                return false;
            }
        }
    }

    public int pop(){
        Integer returned_data, popped_idx, left_child_idx, right_child_idx;

        if(this.myHeap == null){
            return -1;
        }else{
            int last_idx = this.myHeap.size()-1;
            returned_data = this.myHeap.get(1); // 1번 인덱스를 root로 함 
            this.myHeap.set(1, this.myHeap.get(last_idx));
            this.myHeap.remove(last_idx);
            popped_idx = 1;

            while(this.move_down(popped_idx)){ // case1은 여기서 걸러짐 
                left_child_idx = popped_idx * 2 ;
                right_child_idx = left_child_idx + 1;
                
                //move_down에 case1은 return false이므로 case2,3만 처리하면됨
                //case2. 왼쪽자식만 있을때
                if(right_child_idx >= this.myHeap.size()){
                    if(this.myHeap.get(popped_idx) < this.myHeap.get(left_child_idx)){
                        Collections.swap(this.myHeap, popped_idx, left_child_idx);
                        popped_idx = left_child_idx;
                    }
                }
                //case3. 둘다 있을때
                else{
                    if(this.myHeap.get(left_child_idx) > this.myHeap.get(right_child_idx)){
                        if(this.myHeap.get(popped_idx) < this.myHeap.get(left_child_idx)){
                            Collections.swap(this.myHeap, popped_idx, left_child_idx);
                            popped_idx = left_child_idx; 
                        }
                    }else{
                        if(this.myHeap.get(popped_idx) < this.myHeap.get(right_child_idx)){
                            Collections.swap(this.myHeap, popped_idx, right_child_idx);
                            popped_idx = right_child_idx; 
                        }
                    }
                }
            }   
        }
        return returned_data;
    }


    public static void main(String[] args) {
        MyHeap heapTest = new MyHeap(15);
        heapTest.insert(10);
        heapTest.insert(8);
        heapTest.insert(5);
        heapTest.insert(4);
        heapTest.insert(20);
        System.out.println(heapTest.myHeap);

        System.out.println(heapTest.pop());
        System.out.println(heapTest.myHeap);
    }
}
