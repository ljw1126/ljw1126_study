
import java.util.*;

public class MyQueuePractice<T> {
    ArrayList<T> queue = new ArrayList<>();

    public void push(T item){
        queue.add(item);
    }

    public T pop(){
        if(queue.isEmpty()) return null;

        return queue.remove(0);
    }

    public boolean isEmpty(){
        return queue.isEmpty();
    }

    public void printAll(){
    
        if(queue.isEmpty()){
            System.out.println("데이터가 없습니다.");
            return;
        }

        for(int i=0;i<queue.size();i++){
            System.out.println(queue.get(i));
        }

    }

    public static void main(String[] args) {
        MyQueuePractice<Integer> myque = new MyQueuePractice<>();
        myque.push(1);
        myque.push(2);
        myque.printAll();
        System.out.println(myque.pop());
        System.out.println(myque.pop());
        myque.printAll();
    }
}
