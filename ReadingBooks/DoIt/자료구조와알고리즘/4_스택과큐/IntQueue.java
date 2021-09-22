public class IntQueue {
    private int max; // 큐 용량
    private int num; // 현재 데이터수 
    private int[] que; // 큐 본체 
    private int front; // 첫 번째 요소 커서 , 데이터를 꺼내는 인덱스
    private int rear;  // 마지막 요소 커서 , 데이터를 넣는 인덱스

    // 예외 : 큐가 비어 있는 경우
    public class EmptyIntQueueException extends RuntimeException{
        public EmptyIntQueueException(){}
    }

    // 예외 : 큐가 가득 찬 경우 
    public class OverflowIntQueueException extends RuntimeException{
        public OverflowIntQueueException(){}
    }

    // 생성자 
    public IntQueue(int capacity){
        num = front = rear = 0 ;
        max = capacity; 
        try{
            que = new int[capacity];
        }catch(OutOfMemoryError ex){ //생성 불가시 
            max = 0;
        }
    }

    // 큐에 데이터를 인큐 (삽입)
    public int enque(int x) throws OverflowIntQueueException{
        if(num >= max) throw new OverflowIntQueueException();
        // 데이터 삽입
        que[rear++] = x;
        num ++; 

        if ( rear == max ) rear = 0;
        
        return x;
    }

    // 큐에서 데이터를 디큐(꺼내기)
    public int deque() throws EmptyIntQueueException{
        if(num <= 0) throw new EmptyIntQueueException();

        int x = que[front++];
        num--;

        if (front == max) front = 0;
        
        return x;
    }

    // 큐에서 peek 값 확인(front 데이터 확인)
    public int peek() throws EmptyIntQueueException{
        if(num<=0) throw new EmptyIntQueueException();

        return que[front];
    }

    // 큐에서 x를 검색하여 인덱스 반환(검색실패시 -1)
    public int indexOf(int x){
        for(int i = 0; i < num ; i++){
            int idx = (i+front) % max;
            if(que[idx] == x) return idx; // 검색 성공
        }
        return -1;  // 검색실패
    }
    // 생성한 배열 그대로 사용, 변수 값만 초기화해서 다시 시작
    public void clear(){
        num = rear = front = 0;  
    }

    //큐의 용량 
    public int capacity(){
        return max; 
    }
    
    // 큐에 실제로 쌓여있는 데이터 수 
    public int size(){
        return num;
    }

    //큐가 비어있는 지 여부 
    public boolean isEmpty(){
        return num <= 0;
    }

    //큐가 가득 찼는지 여부 
    public boolean isFull(){
        return num >= max;
    }

    //큐 안이 모든 데이터 출력하기 
    public void dump(){
        if(num <= 0) throw new EmptyIntQueueException();

        for(int i=0 ; i < num ; i++){
            System.out.print(que[(i+front) % max] + " ");
        }
        System.out.println();
    }

    //연습문제(p160)
    //큐에서 x를 검색하여 머리부터 몇 번 째인가(찾지 못하면 -1)를 반환
    public int search(int x){
        if(num <= 0 ) throw new EmptyIntQueueException();

        for(int i = 0; i < num; i++){
            if( x == que[((i+front) % max)] ) return i+1;
        }

        return -1;
    }
}
