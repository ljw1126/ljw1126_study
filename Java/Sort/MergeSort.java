package Sort;

import java.util.*;
// 병합정렬 fastcampus
public class MergeSort {
    //병합정렬 재귀가 그려지지 않음..
    public static ArrayList<Integer>  mergeSplitFunc(ArrayList<Integer> list){
        System.out.println("시작 === "  + list.toString());
        if(list.size() <= 1) return list; 

        int mid = list.size()/2;
     
        ArrayList<Integer> leftArr = mergeSplitFunc(new ArrayList(list.subList(0, mid)));
        System.out.println("leftArr : "+leftArr.toString());
        ArrayList<Integer> rightArr = mergeSplitFunc(new ArrayList(list.subList(mid, list.size())));
        System.out.println("rightArr : " + rightArr.toString());

        return mergeFunc(leftArr, rightArr);
    }


    public static ArrayList<Integer> mergeFunc(ArrayList<Integer> leftArr,ArrayList<Integer> rightArr){
        ArrayList<Integer> mergedList = new ArrayList<>();
        int left_idx = 0;
        int right_idx = 0;

        //case1. 둘다 있을때 
        while(leftArr.size() > left_idx && rightArr.size() > right_idx){
            if(leftArr.get(left_idx) < rightArr.get(right_idx)){
                mergedList.add(leftArr.get(left_idx));
                left_idx++;
            }else{
                mergedList.add(rightArr.get(right_idx));
                right_idx++;
            }
        }

        //case2. leftArr에 데이터가 남았을때 
        while(leftArr.size() > left_idx){
            mergedList.add(leftArr.get(left_idx));
            left_idx++;
        }

        //case3. rightArr에만 데이터가 남았을떄 
        while(rightArr.size() > right_idx){
            mergedList.add(rightArr.get(right_idx));
            right_idx++;
        }

        return mergedList;
    }
   
    public static void main(String[] args) {
        ArrayList<Integer> dataList = new ArrayList<>();

        for(int i = 0 ; i < 5 ; i++){
            dataList.add((int)(Math.random() * 100));
        }
        System.out.println(dataList.toString());

        System.out.println("====================");
        System.out.println(mergeSplitFunc(dataList));

    }
}
