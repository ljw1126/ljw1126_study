import java.util.Comparator;
import java.util.Scanner;

public class LinkedListTester {
    static Scanner stdIn = new Scanner(System.in);

    //데이터(회원번호+이름)
    static class Data{
        static final int NO = 1;   // 번호를 입력 받습니까?
        static final int NAME = 2; // 이름을 입력 받습니까?

        private Integer no;
        private String name; 

        //문자열 반환
        public String toString(){ // dump()에 sysout에서 왜 요개 출력되지 ;; 
            return "[" + no + "] " + name;
        }

        //데이터 입력함 
        void scanData(String guide, int sw){
            System.out.println(guide + "할 데이터를 입력하세요.");

            if((sw & NO) == NO){
                System.out.println("번호 : ");
                no = stdIn.nextInt();
            }
            if((sw & NAME) == NAME){
                System.out.println("이름 : ");
                name = stdIn.next();
            }
        }

        // 회원번호로 순서를 매기는 comparator 
        public static final Comparator<Data> NO_ORDER = new NoOrderComparator();

        private static class NoOrderComparator implements Comparator<Data>{
            public int compare(Data d1, Data d2){
                return (d1.no > d2.no) ? 1 : (d1.no < d2.no) ? -1 : 0;
            }
        }

        // 이름으로 순서를 매기는 comparator 
        public static final Comparator<Data> NAME_ORDER = new NameOrderComparator();

        private static class NameOrderComparator implements Comparator<Data>{
            public int compare(Data d1, Data d2){
                return d1.name.compareTo(d2.name);
            }
        }
    } // end Data 


    //메뉴 열거형 
    enum Menu{
        ADD_FIRST("머리에 노드삽입"),
        ADD_LAST("꼬리에 노드삽입"),
        RMV_FIRST("머리노드 삭제"),
        RMV_LAST("꼬리노드 삭제"),
        RMV_CRNT("선택노드 삭제"),
        CLEAR("모든노드 삭제"),
        SEARCH_NO("번호로 검색"),
        SEARCH_NAME("이름으로 검색"),
        NEXT("다음선택노드 이동"),
        PRINT_CRNT("선택노드 출력"),
        DUMP("모든노드 출력"),
        TERMINATE("종료");

        private final String message; // 출력할 문자열 

        static Menu MenuAt(int idx){ // 서수가 idx인 열거를 반환
            for(Menu m : Menu.values())
                if(m.ordinal() == idx)
                    return m;
            return null;
        }

        Menu(String string){    // 생성자 
            message = string; 
        }

        String getMessage(){    // 출력할 문자열을 반환
            return message; 
        }

    }// end num

    //메뉴 선택
    static Menu SelectMenu(){
        int key;
        do{
            for(Menu m : Menu.values()){
                System.out.printf("(%d) %s", m.ordinal(), m.getMessage());
                if((m.ordinal() %3) == 2 && 
                    m.ordinal() != Menu.TERMINATE.ordinal())
                    System.out.println();    // 메뉴 출력시 줄바꿈  
            }//end : for 
            System.out.println(" : ");
            key = stdIn.nextInt(); //입력받기 
        }while(key < Menu.ADD_FIRST.ordinal() || key > Menu.TERMINATE.ordinal() );

        return Menu.MenuAt(key);
    }

    //main실행문
    public static void main(String[] args) {
        Menu menu; //메뉴
        Data data; //추가용 데이터 참조 
        Data ptr; //검색용 데이터 참조 
        Data temp = new Data(); //입력용 데이터 

        //리스트 생성 
        LinkedListEx<Data> list = new LinkedListEx<Data>(); // jdk 1.7 타입 추론 
    
        do{
            switch(menu = SelectMenu()){

                case ADD_FIRST:     //머리에 노드 삽입 
                    data = new Data();
                    data.scanData("머리에 삽입", Data.NO | Data.NAME);
                    list.addFirst(data);
                    break;

                case ADD_LAST : //꼬리에 노드 삽입 
                    data = new Data();
                    data.scanData("꼬리에 삽입", Data.NO | Data.NAME);
                    list.addLast(data);
                    break;

                case RMV_FIRST :   // 머리 노드를 삭제  
                    list.removeFirst();
                    break;    

                case RMV_LAST : //꼬리 노드를 삭제 
                    list.removeLast();
                    break; 
                
                case RMV_CRNT : //현재 선택노드 삭제
                    list.removeCurrentNode();
                    break; 

                case SEARCH_NO : //회원번호로 검색 
                    temp.scanData("검색", Data.NO);
                    ptr = list.search(temp, Data.NO_ORDER);
                    if(ptr == null)
                        System.out.println("조회된 결과가 없음");
                    else 
                        System.out.println("검색 성공:"+ptr);
                    break;

                case NEXT: // 현재 선택노드 포인터 다음으로 이동
                    list.next();
                    break; 
                
                case PRINT_CRNT: // 현재 선택 노드의 데이터 출력
                    list.printCurrentNode();
                    break; 

                case DUMP: // 모드 노드를 리스트 순서로 출력
                    list.dump();
                    break;

                case CLEAR: // 모든 노드를 삭제 
                    list.clear();
                    break;
                
            }


        }while(menu != Menu.TERMINATE);
    }//end main 

}
