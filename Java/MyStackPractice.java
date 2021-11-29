
import java.util.*;

public class MyStackPractice<T> {
    
    private ArrayList<T> stack = new ArrayList<>();

    public void push(T item){
        stack.add(item);
    }

    public T pop(){
        if(stack.isEmpty()){
            return null;
        }
        return stack.remove(stack.size()-1);
    }

    public boolean isEmpty(){
        return stack.isEmpty();
    }

    public void printAll(){
        if(stack.isEmpty()){
            System.out.println("데이터 없음");
        }else{
            for(int i= stack.size()-1 ; i >= 0 ; i--){
                System.out.println(stack.get(i));
            }
        }
    }

    public static void main(String[] args) {
        MyStackPractice<Integer> msp = new MyStackPractice<>();
        msp.push(1);
        msp.push(2);
        msp.push(3);
        msp.printAll();
        System.out.println(msp.pop());
        System.out.println(msp.pop());
        System.out.println(msp.pop());
    }
}
