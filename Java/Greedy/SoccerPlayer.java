import java.util.Set;

import java.util.*;

public class SoccerPlayer implements Comparable<SoccerPlayer>{
    private String name;
    private String position;
    private int age; 

    // Comparable 인터페이스 상속시 정렬기준 정의 해줘야 Collections.sort(리스트) 동작함
    @Override 
    public int compareTo(SoccerPlayer player){
        //return player.getName().compareTo(this.name); // 이름 내림차순 정렬됨 (ㅎ부터)
        return this.name.compareTo(player.getName()); // 이름 오름차순으로 정렬됨(ㄱ부터)
        //return player.getAge() - this.age; // 나이 내림차순으로 정렬됨
        //return this.age - player.getAge(); // 나이 오름차순으로 정렬됨
    }

    public SoccerPlayer(String name, String position, int age){
        this.name = name; 
        this.position = position ;
        this.age = age; 
    }

    public int getAge() {
        return age;
    }

    public String getPosition() {
        return position;
    }

    public String getName() {
        return name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    public void setPosition(String position) {
        this.position = position;
    }
}
