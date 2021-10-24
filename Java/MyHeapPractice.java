import java.util.*;

public class MyHeapPractice {
    static ArrayList<Integer> myHeap = null;

    public MyHeapPractice(Integer data){
        myHeap = new ArrayList<>();
        myHeap.add(null);
        myHeap.add(data);
    }

    // insert시 마지막 인덱스에 넣고 heapify 하기 위한 함수 
    public boolean move_up(int inserted_idx){
       
        if(inserted_idx <= 1) return false;

       Integer parent_idx = inserted_idx / 2; 
    
       if(this.myHeap.get(parent_idx) > this.myHeap.get(inserted_idx)){
           return false;
       }else{
           return true;
       }  

    }

    public void insert(int data){
        Integer inserted_idx , parent_idx;

        if(this.myHeap == null){
            myHeap = new ArrayList<>();
            myHeap.add(null);
            myHeap.add(data);
        }else{ // 여기서 부터 기억 덜남 
            this.myHeap.add(data);
            inserted_idx = this.myHeap.size()-1;
            while(move_up(inserted_idx)){
                parent_idx = inserted_idx / 2;
                Collections.swap(this.myHeap, inserted_idx, parent_idx);
                inserted_idx = parent_idx;
            }
        }
    }

    // pop() 할 경우 마지막 인덱스 값을 root에 올리고 heapify 하기 위한 함수 
    public boolean move_down(int popped_idx){
        Integer left_child_idx, right_child_idx;
        left_child_idx = popped_idx * 2;
        right_child_idx = left_child_idx + 1;

        // case1. 왼쪽 자식 노드가 존재하지 않을 경우 
        if(left_child_idx >= this.myHeap.size()){
            return false; 
        }
        // case2. 오른쪽 자식 노드만 없을때 (왼쪽 자식이 존재할때)
        else if(right_child_idx >= this.myHeap.size()){ 
            if(this.myHeap.get(left_child_idx) > this.myHeap.get(popped_idx))
                return true;
            else 
                return false; 
        }
        // case3. 자식이 둘다 존재할떄 
        else{
            // 힙의 경우 자식이 어느쪽이 더 큰지 구별이 필요함 
            if(this.myHeap.get(left_child_idx) > this.myHeap.get(right_child_idx)){
                if(this.myHeap.get(left_child_idx) > this.myHeap.get(popped_idx))
                    return true;
                else    
                    return false;
            }else{
                if(this.myHeap.get(right_child_idx) > this.myHeap.get(popped_idx))
                return true;
            else    
                return false;
            }
        }
    }

    public int pop(){
        Integer popped_data, popped_idx, left_child_idx, right_child_idx;
        if(this.myHeap == null){
            return -1;
        }else{
            // 루트(1번 인덱스) 값을 꺼내고 제일 마지막 값을 
            int last_idx = this.myHeap.size()-1;
            popped_data = this.myHeap.get(1);
            this.myHeap.set(1, this.myHeap.get(last_idx));
            this.myHeap.remove(last_idx); 
            popped_idx = 1;
            // 마지막 인덱스 값이 루트(1번)에 올라갔으니 제자리를 찾아가도록..heapify 
            while(move_down(popped_idx)){
                left_child_idx = popped_idx * 2; 
                right_child_idx = left_child_idx + 1;

                // if(this.myHeap.get(left_child_idx) > this.myHeap.get(popped_idx)){
                //     Collections.swap(this.myHeap, popped_idx, left_child_idx);
                //     popped_idx = left_child_idx;
                // }else{
                //     Collections.swap(this.myHeap, popped_idx, right_child_idx);
                //     popped_idx = right_child_idx;
                // }
                //자식 둘다 없을 경우 false이므로 반복문 돌지 않음
                //왼쪽 자식만 존재할떄 
                if(right_child_idx >= this.myHeap.size()){ 
                    if(this.myHeap.get(left_child_idx) > this.myHeap.get(popped_idx)){
                        Collections.swap(this.myHeap, popped_idx, left_child_idx);
                        popped_idx = left_child_idx;
                    }
                        
                }
                // case3. 자식이 둘다 존재할떄 
                else{
                    // 힙의 경우 자식이 어느쪽이 더 큰지 구별이 필요함 
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
        System.out.println(heapTest.myHeap);

        System.out.println(heapTest.pop());
        System.out.println(heapTest.myHeap);
    }
}

