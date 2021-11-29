
import java.util.*;

public class MyHeapPractice211129 {
    static ArrayList<Integer> myHeap = null;

    public MyHeapPractice211129(int data){
        this.myHeap = new ArrayList<Integer>();
        this.myHeap.add(null);
        this.myHeap.add(data); // 1번 인덱스를 root 노드로 함 
    }

    // insert시 마지막 인덱스에 넣고 heapify하기 위한 함수 
    public boolean move_up(int idx){

        if(idx <= 1) return false; // 여기 부등호 

        //최대힙 가정
        int parent_idx = idx/2;
        if(this.myHeap.get(parent_idx) < this.myHeap.get(idx)){
            return true;
        }

        return false;
    }

    public void insert(int data){
        
        if(this.myHeap == null){
            this.myHeap = new ArrayList<Integer>();
            this.myHeap.add(null);
            this.myHeap.add(data);
        }else{
            int parent_idx;
            this.myHeap.add(data);
            int added_idx = this.myHeap.size() - 1;   // 여기 사이즈 

            while(move_up(added_idx)){
                parent_idx = added_idx/2;
                Collections.swap(this.myHeap, parent_idx, added_idx);
                added_idx = parent_idx;
            }
        }
    }

    // pop() 할때, 마지막 인덱스 값을 root에 올리고 heapify 하기 위한 함수 
    public boolean move_down(int idx){

        if(this.myHeap.size() <= idx) return false;

        int left_child_idx, right_child_idx;
        left_child_idx = idx * 2;
        right_child_idx = left_child_idx + 1;

        /*
            case1. 자식이 없을 경우 
            case2. 왼쪽 자식만 존재할 경우 
            case3. 자식 둘다 존재할 경우 
        */
        if(this.myHeap.size() <= left_child_idx){
            return false;
        // 왼쪽 자식만 존재할 경우 
        }else if(this.myHeap.size() <= right_child_idx){
            if(this.myHeap.get(left_child_idx) > this.myHeap.get(idx)){
                return true;
            }
        // 자식 둘다 존재할 경우 
        }else{
            if(this.myHeap.get(left_child_idx) > this.myHeap.get(right_child_idx)){
                if(this.myHeap.get(left_child_idx) > this.myHeap.get(idx)) return true;
            }else{
                if(this.myHeap.get(right_child_idx) > this.myHeap.get(idx)) return true;
            }
        }

        return false;
    }

    public int pop(){

        int left_child_idx, right_child_idx;

        if(this.myHeap.size() > 0){

            int popped_data = this.myHeap.get(1);
            int last_idx_data = this.myHeap.remove(this.myHeap.size() - 1);
            this.myHeap.set(1, last_idx_data);
            int inserted_idx = 1;
            /*
                왼쪽 자식 값만 있을때와 
                자식 노드 두개 다 있을때만 비교하면 됨 
            */
            while(move_down(inserted_idx)){
                left_child_idx = inserted_idx * 2;
                right_child_idx = left_child_idx + 1;
                if(this.myHeap.size() <= right_child_idx){
                    if(this.myHeap.get(left_child_idx) > this.myHeap.get(inserted_idx)){
                        Collections.swap(this.myHeap, inserted_idx, left_child_idx );
                        inserted_idx = left_child_idx;
                    }
                }else{
                    if(this.myHeap.get(left_child_idx) > this.myHeap.get(right_child_idx)){
                        if(this.myHeap.get(left_child_idx) > this.myHeap.get(inserted_idx)){
                            Collections.swap(this.myHeap, inserted_idx, left_child_idx);
                            inserted_idx = left_child_idx;
                        }
                    }else{
                        if(this.myHeap.get(right_child_idx) > this.myHeap.get(inserted_idx)){
                            Collections.swap(this.myHeap, inserted_idx, right_child_idx);
                            inserted_idx = right_child_idx;
                        }
                    }
                }
                
            }   

            return popped_data;
        }

        return -1;
    }

    public static void main(String[] args) {
        MyHeapPractice211129 heapTest = new MyHeapPractice211129(15);
        heapTest.insert(10);
        heapTest.insert(8);
        heapTest.insert(5);
        heapTest.insert(4);
        heapTest.insert(20);
        System.out.println(heapTest.myHeap); // [null,20,10,15,5,4,8]

        System.out.println(heapTest.pop());  // 20
        System.out.println(heapTest.myHeap); // [null,15,10,8,5,4]
    }
}
