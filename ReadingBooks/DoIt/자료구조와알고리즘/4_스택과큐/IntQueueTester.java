import java.util.Scanner;

public class IntQueueTester {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        IntQueue iq = new IntQueue(64); // 배열 크기 64인 큐 

        while(true){
            System.out.println("현제 데이터 수 : " + iq.size() + "/" + iq.capacity());
            System.out.println("(1)enque (2)deque (3)peek (4)dump (5)clear (6)search (0)종료:");

            int menu = sc.nextInt();
            if(menu == 0) break; //종료

            switch(menu){
                case 1: 
                        try{
                            System.out.println("데이터 입력 : ");
                            iq.enque(sc.nextInt());
                        }catch(IntQueue.OverflowIntQueueException ex){
                            System.out.println("큐가 가득참");
                        }
                        break;
                case 2: 
                        try{
                            System.out.println("디큐 값 >> " + iq.deque());
                        }catch(IntQueue.EmptyIntQueueException ex){
                            System.out.println("데이터 없음");
                        }
                        break;
                case 3: 
                        try {
                            System.out.println("피크 값 >>" + iq.peek());
                        }catch(IntQueue.EmptyIntQueueException ex){
                            System.out.println("데이터 없음");
                        }
                        break;
                case 4: 
                        iq.dump();
                        break;
                case 5: 
                        iq.clear();
                        break;
                case 6 : //search 
                        try{
                            System.out.println("검색 결과(큐의 n번째) >> " + iq.search(sc.nextInt()));
                        }catch(IntQueue.EmptyIntQueueException ex){
                            System.out.println("데이터 없음");
                        }
                        break;
            }    
        }
    }
}
