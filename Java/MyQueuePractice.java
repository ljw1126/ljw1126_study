
import java.util.*;

public class MyQueuePractice<T> {
    private ArrayList<T> queue = new ArrayList<T>();
    
    public void enqueue(T item) {
        
    }
    
    public T dequeue() {
        return null;    
    }
    
    public boolean isEmpty() {
        return false; 
    }
    
    public static void main(String[] args) {
        MyQueuePractice<Integer> mq = new MyQueuePractice<Integer>();
        mq.enqueue(1);
        mq.enqueue(2);
        mq.enqueue(3);
        System.out.println(mq.dequeue());
        System.out.println(mq.dequeue());
        System.out.println(mq.dequeue());        
    }
    
}
