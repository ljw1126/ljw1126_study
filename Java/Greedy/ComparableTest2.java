import java.util.ArrayList;

import java.util.*;

public class ComparableTest2 {
    
    public static void main(String[] args) {
        
        ArrayList<Student> list = new ArrayList<>(); // 
        
        list.add(new Student(2, "홍길동", 96));
        list.add(new Student(1, "이순신", 89));
        list.add(new Student(3, "김철수", 50));
        list.add(new Student(4, "최영희", 70));
        
        Collections.sort(list); // Student 클래스에서 구현한 Comparable 인터페이스 함수 기준으로 정렬함

        for(Student obj : list){
            System.out.println(obj.getNo() + ", "+ obj.getName()+ ", " + obj.getScore());
        }
        /*
            1, 이순신, 89
            2, 홍길동, 96
            3, 김철수, 50
            4, 최영희, 70
        */
        // 성적순으로 정렬하고 싶을 경우 
        Collections.sort(list, new Comparator<Student>(){
            @Override
            public int compare(Student o1, Student o2) {
                //성적 순으로 오름차순
                /*
                    3, 김철수, 50
                    4, 최영희, 70
                    1, 이순신, 89
                    2, 홍길동, 96
                */
                // if(o1.getScore() > o2.getScore()){
                //     return 1 ;
                // }else if(o1.getScore() < o2.getScore()){
                //     return -1;
                // }else{
                //     return 0;
                // }
                //내림차순
                /*
                    2, 홍길동, 96
                    1, 이순신, 89
                    4, 최영희, 70
                    3, 김철수, 50 
                */
                if(o1.getScore() > o2.getScore()){
                    return -1 ;
                }else if(o1.getScore() < o2.getScore()){
                    return 1;
                }else{
                    return 0;
                }
            } 
        });
        for(Student obj : list){
            System.out.println(obj.getNo() + ", "+ obj.getName()+ ", " + obj.getScore());
        }


        int[] nums = {3,4,5,7,1,2};
        Arrays.sort(nums);
        for(int i : nums) System.out.print(i + " ");

        ArrayList<String> arr = new ArrayList<>(Arrays.asList("마","다","가","나"));
        Collections.sort(arr);
        for(String j : arr) System.out.print(j + " ");
        
    }
}
