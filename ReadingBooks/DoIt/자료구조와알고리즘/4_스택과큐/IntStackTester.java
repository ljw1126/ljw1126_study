import java.util.Scanner;

public class IntStackTester {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        IntStack stk = new IntStack(64); // 크기 64임 배열 선언 
        

        while(true){
            System.out.println("현제 데이터 수 : " + stk.size() + "/" + stk.capacity());
            System.out.println("(1)push (2)pop (3)peek (4)dump (0)종료:");

            int menu = sc.nextInt();
            if(menu == 0) break; 

            int x;
            
            switch(menu){
                case 1: //push
                    System.out.println("데이터 입력:");
                    x = sc.nextInt();
                   
                        try{
                            stk.push(x);
                        }catch(IntStack.OverflowInStackException e){
                            System.out.println("스택 가득 참");
                        }
                    break;

                case 2: // pop
                    try{
                        x = stk.pop();
                        System.out.println("pop한 데이터 >>" + x );
                    }catch(IntStack.EmptyInStackException e){
                        System.out.println("스택 비어 있음");
                    }
                    break; 
                
                case 3: // peek
                    try{
                        x = stk.peek();
                        System.out.println("peek 데이터 >> " + x);
                    }catch(IntStack.EmptyInStackException e){
                        System.out.println("스택 비어 있음");
                    }
                    break;
                case 4 : // dump
                    stk.dump();
                    break;
            }
        }
    }
}
