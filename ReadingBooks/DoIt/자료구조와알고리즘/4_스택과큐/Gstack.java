public class Gstack<E> {
    private int max; // 스택 용량 
    private int ptr; // 스택 포인터 
    private E[] stk; // 스택 본체 
    
    // 예외 : 스택이 비어있음 
    public static class EmptyInStackException extends RuntimeException{
        public EmptyInStackException(){}
    }
    // 예외 : 스택이 가득 참 
    public static class OverflowInStackException extends RuntimeException{
        public OverflowInStackException(){}
    }
    //생성자  -- https://brunch.co.kr/@oemilk/143 참고 
    @SuppressWarnings("unchecked")
    public Gstack(int capacity){
        ptr = 0;
        max = capacity;
        try {
            stk =(E[])new Object[max];   // 스택 초기화
        } catch (OutOfMemoryError e) {
            max = 0;            
        }
    }

    // push : 데이터 삽입 
    public E push(E x) throws OverflowInStackException{
        if(ptr >= max) throw new OverflowInStackException(); //스택 가득찬 경우 
        return stk[ptr++] = x;
    }

    // pop: 스택에서 데이터를 꺼냄 (마지막 값부터)
    public E pop() throws EmptyInStackException{
        if (ptr <= 0) throw new EmptyInStackException(); // 스택이 비어있음
        return stk[--ptr]; //ptr을 감소시키고
    }

    // peek : 스택 정상에 있는 데이터를 확인 
    public E peek() throws EmptyInStackException{
        if(ptr <= 0 ) throw new EmptyInStackException(); // 스택이 비어있음
        return stk[ptr-1];
    }

    // indexOf : 검색 메서드 
    public int indexOf(E x){
        for(int i = ptr -1; i >= 0; i--){
            if(stk[i].equals(x))
                return i;   // 검색 성공 
        }
        return -1; // 검색 실패
    }

    // clear : 스택의 모든 요소를 삭제  
    public void clear(){
        ptr = 0 ; // 스택 배열 초기화시 메모리 낭비하니 그냥 포인터 0으로 초기화
    }

    // capacity : 스택의 용량 확인 
    public int capacity(){
        return max;
    }

    // size : 데이터 수를 확인하는 메서드 
    public int size(){
        return ptr; 
    }

    // isEmpty : 스택이 비어 있는지 검사하는 메서드 
    public boolean isEmpty(){
        return ptr <= 0;
    }

    // isFull : 스택이 가득 찼는지 확인
    public boolean isFull(){
        return ptr >= max;
    }
    
    // dump : 스택 안의 모든 데이터 출력(꼭대기부터 출력)
    public void dump(){
        if (ptr <= 0 ){
            System.out.println("스택이 비어 있음");
        }else{
            for(int i=0;i < ptr; i++)
                System.out.print(stk[i] + " ");
            System.out.println();
        }
    }
}
