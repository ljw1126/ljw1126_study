
import java.util.*;

public class MyStackTemplate<T> {
    private ArrayList<T> stack = new ArrayList<T>();
    
    public void push(T item) {
    }
    
    public T pop() {
        return null;
    }
    
    public boolean isEmpty() {
        return false;
    }
    
    public void printAll() { // LIFO 
    }

    public static void main(String[] args) {
        MyStackTemplate<Integer> ms = new MyStackTemplate<Integer>();
        ms.push(1);
        ms.push(2);
        System.out.println(ms.pop());
        ms.push(3);
        System.out.println(ms.pop());
        System.out.println(ms.pop());        
    }
}
