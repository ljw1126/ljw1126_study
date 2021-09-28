package Sort;

import java.util.ArrayList;
import java.util.Arrays;

public class QuickSort {

    //분할정복법 
    public ArrayList<Integer> sort(ArrayList<Integer> list){
        if(list.size() <= 1) return list; 
       
        int pivot = list.get(0);
        ArrayList<Integer> leftArr = new ArrayList<>();
        ArrayList<Integer> rightArr = new ArrayList<>();


        for(int i = 1; i < list.size();i++){
            if(list.get(i) > pivot)
                rightArr.add(list.get(i));
            else
                leftArr.add(list.get(i));
        }//

        
        ArrayList<Integer> mergedList = new ArrayList<>();
        mergedList.addAll(sort(leftArr));
        mergedList.addAll(Arrays.asList(pivot));
        mergedList.addAll(sort(rightArr));

        return mergedList;
    }

    public static void main(String[] args) {
        ArrayList<Integer> dataList = new ArrayList<>();    

        for(int i = 1 ; i < 11 ; i++ ){
            dataList.add((int)(Math.random() * 100));
        }

        System.out.println("입력데이터 =================");
        System.out.println(dataList.toString());
        QuickSort qs = new QuickSort();
        System.out.println("결과 =================");
        System.out.println(qs.sort(dataList));
    }
}
