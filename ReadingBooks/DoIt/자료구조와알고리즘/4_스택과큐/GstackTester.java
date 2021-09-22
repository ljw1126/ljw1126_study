import java.util.Scanner;

public class GstackTester {
   public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Gstack<String> gstk = new Gstack<>(64);

        while(true){
            System.out.println("현제 데이터 수 : " + gstk.size() + "/" + gstk.capacity());
            System.out.println("(1)push (2)pop (3)peek (4)dump (0)종료:");

            int menu = sc.nextInt();
            if(menu == 0) break; 

            String gx;
            switch(menu){
                case 1: //push
                    System.out.println("데이터 입력:");
                    gx = sc.next();
                        try{
                            gstk.push(gx);
                        }catch(Gstack.OverflowInStackException e){
                            System.out.println("스택 가득 참");
                        }
                    break;

                case 2: // pop
                    try{
                        gx = gstk.pop();
                        System.out.println("pop한 데이터 >>" + gx );
                    }catch(Gstack.EmptyInStackException e){
                        System.out.println("스택 비어 있음");
                    }
                    break; 
                
                case 3: // peek
                    try{
                        gx = gstk.peek();
                        System.out.println("peek 데이터 >> " + gx);
                    }catch(Gstack.EmptyInStackException e){
                        System.out.println("스택 비어 있음");
                    }
                    break;
                case 4 : // dump
                    gstk.dump();
                    break;
            }
        }
   } 
}
