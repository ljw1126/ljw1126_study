
import java.util.*;

public class MyStackPractice<T> {
    private ArrayList<T> stack = new ArrayList<T>();
    
    public void push(T item) {
    }
    
    public T pop() {
        return null;
    }
    
    public boolean isEmpty() {
        return false;
    }
    
    public static void main(String[] args) {
        MyStackPractice<Integer> ms = new MyStackPractice<Integer>();
        ms.push(1);
        ms.push(2);
        System.out.println(ms.pop());
        ms.push(3);
        System.out.println(ms.pop());
        System.out.println(ms.pop());        
    }
}
