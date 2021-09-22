import java.util.Scanner;

public class GqueueTester {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Gqueue<String> gq = new Gqueue<>(64);

        while(true){
            System.out.println("현제 데이터 수 : " + gq.size() + "/" + gq.capacity());
            System.out.println("(1)enque (2)deque (3)peek (4)dump (5)clear (6)search (0)종료:");

            int menu = sc.nextInt();
            if(menu == 0) break; //종료

            switch(menu){
                case 1: 
                        try{
                            System.out.println("데이터 입력 : ");
                            gq.enque(sc.next());
                        }catch(Gqueue.OverflowGenricQueueException ex){
                            System.out.println("큐가 가득참");
                        }
                        break;
                case 2: 
                        try{
                            System.out.println("디큐 값 >> " + gq.deque());
                        }catch(Gqueue.EmptyGenericQueueException ex){
                            System.out.println("데이터 없음");
                        }
                        break;
                case 3: 
                        try {
                            System.out.println("피크 값 >>" + gq.peek());
                        }catch(Gqueue.EmptyGenericQueueException ex){
                            System.out.println("데이터 없음");
                        }
                        break;
                case 4: 
                        gq.dump();
                        break;
                case 5: 
                        gq.clear();
                        break;
                case 6 : //search 
                        try{
                            System.out.println("검색 결과(큐의 n번째) >> " + gq.search(sc.next())); 
                        }catch(Gqueue.EmptyGenericQueueException ex){
                            System.out.println("데이터 없음");
                        }
                        break;
            }    
        }
   } 
}
