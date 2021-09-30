
import java.util.*;

public class ComparableTest {
    public static void main(String[] args) {
        ArrayList<SoccerPlayer> playerList = new ArrayList<>();

        playerList.add(new SoccerPlayer("메시", "공격수", 23));
        playerList.add(new SoccerPlayer("호날두", "공격수", 25));
        playerList.add(new SoccerPlayer("줄라탄", "공격수", 26));
        playerList.add(new SoccerPlayer("박지성", "미드필더", 30));
        playerList.add(new SoccerPlayer("오스카", "미드필더", 21));
        playerList.add(new SoccerPlayer("기안", "공격수", 33));

        //SoccerPlayer 클래스에서 정의내린 compareTo()에 따라 이름 순으로 정렬됨 
        Collections.sort(playerList); // 해당 객체 클레스에 Comparable 인터페이스 상속/구현 하지 않으면 에러 뜸 
        /*  // 이름순 
            기안
            메시
            박지성
            오스카
            줄라탄
            호날두
        */
        for(SoccerPlayer obj : playerList){
            System.out.println(obj.getName() + "," + obj.getPosition() + "," +obj.getAge());
        }

        // 객체의 기본 정렬기준외에 다른 기준으로 정렬하고자 할때 Comparator 사용 
        Collections.sort(playerList, new Comparator<SoccerPlayer>(){

            @Override
            public int compare(SoccerPlayer o1, SoccerPlayer o2) {
                // TODO Auto-generated method stub
                return 0;
            }
            
        });

        for(SoccerPlayer obj : playerList){
            System.out.println(obj.getName() + "," + obj.getPosition() + "," +obj.getAge());
        }

    }    
}
