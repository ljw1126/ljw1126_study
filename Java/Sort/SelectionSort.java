package Sort;
import java.util.*;

public class SelectionSort {
 
    public ArrayList<Integer> sort(ArrayList<Integer> list){

        int n = list.size();
        int lowest_idx;
        for(int i = 0 ; i < n-1 ; i++){
            lowest_idx = i;
            for(int j = i+1 ; j < n ; j++){
                if(list.get(lowest_idx) > list.get(j)) lowest_idx = j ;
            }
            Collections.swap(list, i, lowest_idx);
        }

        return list;
    }

    public static void main(String[] args) {
        ArrayList<Integer> testList = new ArrayList<>(); // 1.7부터 타입추론 되서 <> 안 넣어도 됨
        for(int i = 0 ; i < 100 ; i++){
            testList.add((int)(Math.random() * 100));
        }

        SelectionSort ss = new SelectionSort();
        System.out.println(ss.sort(testList)); // 결과확인 
    }
}
