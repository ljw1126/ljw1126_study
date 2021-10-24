import java.util.*;

public class MyHeapTemplate {
    static ArrayList<Integer> myHeap = null;

    public MyHeapTemplate(Integer data){
    }

    // insert시 마지막 인덱스에 넣고 heapify 하기 위한 함수 
    public boolean move_up(int idx){
       return false;
    }

    public void insert(int data){
        
    }

    // pop() 할 경우 마지막 인덱스 값을 root에 올리고 heapify 하기 위한 함수 
    public boolean move_down(int idx){
        return false; 
    }

    public int pop(){
        return 0; 
    }


    public static void main(String[] args) {
        MyHeapTemplate heapTest = new MyHeapTemplate(15);
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
